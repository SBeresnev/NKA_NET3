package nla.local.services.impl;


import nla.local.dao.BaseDao;
import nla.local.dao.exceptions.DaoErrorCode;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.dict.Dict;
import nla.local.pojos.dict.EnumDict;
import nla.local.pojos.orders.Decl;
import nla.local.pojos.orders.DeclResolution;
import nla.local.pojos.orders.DeclUser;
import nla.local.pojos.orders.Journal;
import nla.local.pojos.subjects.OPerson;
import nla.local.pojos.subjects.Person;
import nla.local.services.IOrderService;
import nla.local.util.CodeGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by beresnev on 09.03.2015.
 */

@Service
@Transactional
public class OrderServiceImp  extends BaseDao<Decl> implements IOrderService {

    @Autowired
    private CodeGenerator scg;

    private BaseDao bd;

    @Autowired
    public OrderServiceImp(SessionFactory sessionFactory)
    {
        super(sessionFactory);

        bd = new BaseDao(sessionFactory);


    }


    @Override
    public void add(Decl decl) throws ServiceDaoException {

        try {

            super.add(decl);

        } catch (DaoException e) { e.printStackTrace(); }

    }

    @Override
    public List<Decl> getAllOrder() throws ServiceException
    {

        List<Decl> dcl = null;

        try {

            dcl = super.getAll(Decl.class);

        } catch (DaoException e) {e.printStackTrace();}

        return dcl;


    }

    public Journal castToJournal(Decl decl) throws ServiceException
    {
        Journal jr = null;

         try {

             jr = new Journal();

            jr.setDecl_id(decl.getDecl_id());

            jr.setDeclarants(decl.getDeclarants());

            jr.setDecldate(decl.getDecldate());

            jr.setDeclnumber(decl.getDeclnumber());


            DeclResolution dr = CollectionUtils.find(decl.getDclresolution()
                    , new Predicate() {
                public boolean evaluate(Object o) {
                    DeclResolution c = (DeclResolution) o;
                    return (c.getResolutionDateOut() == null);
                }
            });


            jr.setDeclResolution(decl.getDclresolution().iterator().next());

            jr.setDeclObjects(null);

            jr.setDocuments(null);

         } catch (Exception ex)

         {throw  new ServiceException(ex, "Can not cast" + Decl.class.toString() + " to " + Journal.class.toString());}

        return jr;
    }


    @Override
    public Map<Journal,Decl> getJournal(OPerson oUser) throws ServiceDaoException, ServiceException {

        /*get all Orders by declUser with ResolutionDateOut is null*/

       /* DetachedCriteria dcDeclUser = DetachedCriteria.forClass(DeclUser.class)
                .createCriteria("oUsers").add(Restrictions.eq("oPerson", oUser));*/

        DetachedCriteria dcDecl = DetachedCriteria.forClass(Decl.class);

        DetachedCriteria dc_one = dcDecl.createCriteria("oUsers").add(Restrictions.eq("oPerson", oUser));
       // DetachedCriteria dc_two = dcDecl.createCriteria("dclresolution").add(Restrictions.eq("ResolutionDateOut", oUser));


        Map<Journal,Decl> journalDeclMap = new HashMap<Journal, Decl>();

        try {


            List<Decl> declList = super.getCriterion(dcDecl);

            for ( Decl decl: declList)
            {
                Journal jr =  new Journal();

                jr = castToJournal(decl);

                if (jr != null && decl != null )

                       journalDeclMap.put(jr,decl);
            }

        } catch (DaoException e) { throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_005, null);}

        return journalDeclMap;

    }


    @Override
    public void postOrder(Decl decl) throws ServiceException {

    }
}
