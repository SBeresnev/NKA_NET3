package nla.local.services.impl;


import nla.local.dao.exceptions.DaoErrorCode;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.operations.Operation;
import nla.local.pojos.orders.*;
import nla.local.pojos.subjects.OPerson;
import nla.local.pojos.subjects.Person;
import nla.local.services.IDeclService;
import nla.local.util.CodeGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;


/**
 * Created by beresnev on 09.03.2015.
 */

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class DeclServiceImp extends BaseServiceImp<Decl> implements IDeclService {

    private static final Logger logger = Logger.getLogger(DeclServiceImp.class);

    @Autowired
    private CodeGenerator scg;

    @Override
    public List<Decl> getAllOrders() throws ServiceDaoException {
        // logger.info("Called method service getAllOrder");
        List<Decl> dcl = null;

        dcl = super.getAll(Decl.class);


        return dcl;
    }

    @Override
    public List<Decl> getOrderByUser(OPerson oUser) throws ServiceException {
        try {
            return getDeclsByUser(oUser);
        } catch (DaoException e) {
            throw new ServiceException(e, "Can not cast" + Decl.class.toString() + " to " + Journal.class.toString());
        }
    }

    @Override
    public Journal castToJournal(Decl decl) throws ServiceException {
        // logger.info("Called method service castToJournal");
        try {
            Journal jr = new Journal();

            jr.setDecl_id(decl.getDecl_id());

            jr.setDeclarants(decl.getDeclarants());

            jr.setDecldate(decl.getDecldate());

            jr.setDeclnumber(decl.getDeclnumber());

            jr.setOperations(new LinkedList<Operation>(decl.getOperations()));

            DeclResolution dr = decl.getDclresolution() != null ? getLast(decl.getDclresolution()) : null;

            jr.setDeclResolution(dr);

            jr.setDeclObjects(null);

            jr.setDocuments(null);

            return jr;

        } catch (Exception ex) {
            //logger.error(ex);
            throw new ServiceException(ex, "Can not cast" + Decl.class.toString() + " to " + Journal.class.toString());
        }
    }

    @Override
    public List<Journal> getJournal(OPerson oUser) throws ServiceDaoException, ServiceException {

        // logger.info("Called method service getJournal");

        List<Journal> journalDecl = new ArrayList<Journal>();

        try {
            Set<Decl> declSet = new HashSet(getDeclsByUser(oUser));
            for (Decl decl : declSet) {
                Journal jr = castToJournal(decl);

                if (jr != null && decl != null) journalDecl.add(jr);
            }
        } catch (DaoException e) {
            throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_005, null);
        }
        return journalDecl;
    }

    @Override
    public List<Decl> getDeclsByUser(OPerson oPerson) throws ServiceDaoException {

        /*logger.info("Called method service getDeclsByUser");*/

        List<Decl> ret_val = null;

        DetachedCriteria query = DetachedCriteria.forClass(Decl.class).createCriteria("oUsers").add(Restrictions.eq("oPerson", oPerson));

        ret_val = super.getCriterion(query);

        return ret_val;
    }

    public Decl addNewEmptyDecl(OPerson person, CatalogItem declResolutionType) throws ServiceDaoException {
        //logger.info("Called method service addNewEmptyDecl");

        Decl decl = new Decl();

        final DeclUser declUser = new DeclUser();

        declUser.setDate_in(new Date());

        declUser.setoPerson(person);

        declUser.setDecls(new HashSet<Decl>());

        final Set<DeclUser> set = new HashSet();

        set.add(declUser);

        decl.setoUsers(set);

        decl.setDeclarants(new HashSet<Declarant>());

        decl.setDecldate(new Date());

        decl.setDecltype(1);

        decl.setUrgency(0);

        final DeclResolution dr = new DeclResolution();

        dr.setResolutionType(declResolutionType);

        dr.setoPerson(person);

        dr.setResolutionDate(new Date());

        decl.setDclresolution(new HashSet<DeclResolution>() {{
            add(dr);
        }});

        this.add(decl);

        return decl;
    }

    public Decl addNewDeclarantInDecl(Person person, Integer type, Long idDecl, Long[] clients) throws ServiceDaoException {
        // logger.info("Called method service addNewDeclarantInDecl");

        // logger.info("Called method service addNewDeclarantInDecl");

        Decl dcl = get(Decl.class, idDecl);

        if (type == 1) {
            Declarant declarant = new Declarant();

            declarant.setDecl_id(idDecl);

            declarant.setDeclrepr_type(type);

            declarant.setPerson(person);

            dcl.getDeclarants().add(declarant);

        } else {
            List<Declarant> declarants = new ArrayList<Declarant>();
            for (Long clientId : clients) {
                Declarant declarant = new Declarant();

                declarant.setDecl_id(idDecl);

                declarant.setDeclrepr_type(type);

                declarant.setPerson(person);

                Declarant declarant1 = getSelectedDeclarant(dcl.getDeclarants(), clientId);

                if (declarant1 != null) declarant.setParentPerson(declarant1);

                declarants.add(declarant);

            }
            dcl.getDeclarants().addAll(declarants);
        }

        update(dcl);

        // super.getBaseDao().getSession().persist(declarant);

        // Decl dcl = get(Decl.class, idDecl);

        return dcl;
    }

    @Override
    public Decl deleteDeclarantInDecl(Serializable declarantId, Serializable idDecl) throws ServiceDaoException {

        final Serializable fdeclarantId = declarantId;

        Decl dc = get(Decl.class, idDecl);

        Declarant declarant = CollectionUtils.find(dc.getDeclarants(), new Predicate() {
            public boolean evaluate(Object o) {
                Declarant c = (Declarant) o;
                return c.getDeclarantId().equals(fdeclarantId);
            }
        });

        if (declarant != null && declarant.getDecl_id().equals(idDecl)) {

            if (dc.getDeclarants().contains(declarant))
                dc.getDeclarants().remove(declarant);

            this.update(dc);

        }

        return dc;

    }

    public Decl deleteDeclarantInDecl(final Long[] declarantId, Serializable idDecl) throws DaoException {

        Decl dc = get(Decl.class, idDecl);

        List<Declarant> declarants = new ArrayList<Declarant>(CollectionUtils.select(dc.getDeclarants(), new Predicate() {
            public boolean evaluate(Object o) {
                Declarant c = (Declarant) o;
                return Arrays.asList(declarantId).contains(c.getDeclarantId());
            }
        }));
        dc.getDeclarants().removeAll(declarants);
        this.update(dc);
        return dc;

    }


    @Override
    public Decl get(Serializable id) throws ServiceDaoException {

        return super.get(Decl.class, id);

    }

    @Override
    public Decl setStatus(CatalogItem catalogItem, Long declId, OPerson user) throws ServiceDaoException {
        //  logger.info("Called method service setStatus");
        Decl decl = get(declId);

        DeclResolution declResolution = new DeclResolution();
        declResolution.setResolutionType(catalogItem);

        declResolution.setResolutionDate(new Date());

        declResolution.setoPerson(user);

        declResolution.setDecl_id(decl.getDecl_id());

        Set set = decl.getDclresolution();

        set.add(declResolution);

        decl.setDclresolution(set);

        this.update(decl);

        return decl;
    }

    private DeclResolution getLast(Set<DeclResolution> declResolutions) {
        //logger.info("Called method service getLast");
        DeclResolution declResolution = null;

        for (DeclResolution resolution : declResolutions) {

            if (declResolution == null) declResolution = resolution;
            else
                declResolution = declResolution.getResolutionDate().after(resolution.getResolutionDate()) ? declResolution : resolution;

        }
        return declResolution;
    }

    @Override
    public Decl changeDeclType(Long declId) throws ServiceDaoException {

        Decl decl = get(declId);

        decl.setDecltype(decl.getDecltype() == 0 ? 1 : 0);

        update(decl);

        return decl;

    }

    @Override
    public Decl changeDeclUser(Long declId, Long decluser_id, OPerson user) throws ServiceDaoException {
        return null;
    }

    private Declarant getSelectedDeclarant(Set<Declarant> declarants, Long id) {
        Declarant d = null;
        for (Declarant declarant : declarants) {
            if (declarant.getDeclarantId().equals(id)) d = declarant;
        }
        return d;
    }

}
