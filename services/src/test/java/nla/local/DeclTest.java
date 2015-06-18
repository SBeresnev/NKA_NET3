package nla.local;

import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.orders.*;
import nla.local.pojos.subjects.OPerson;
import nla.local.pojos.subjects.PPerson;
import nla.local.pojos.subjects.Person;
import nla.local.services.impl.BaseServiceImp;
import nla.local.services.impl.CatalogServiceImp;
import nla.local.services.impl.DeclServiceImp;
import nla.local.services.impl.subjects.OSubjectServiceImp;
import nla.local.services.impl.subjects.PSubjectServiceImp;
import nla.local.util.BaseClean;
import nla.local.util.CodeGenerator;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Created by beresnev on 09.03.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-services.xml","classpath:beans-dao.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class DeclTest {

    private static Logger log = Logger.getLogger(DeclTest.class);

    @Autowired
    public BaseClean baseClean;

    @Autowired
    public DeclServiceImp dsi;

    @Autowired
    @Qualifier("baseServiceImp")
    public BaseServiceImp bsi;

    @Autowired
    public OSubjectServiceImp oService;

    @Qualifier("PSubjectServiceImp")
    @Autowired
    public PSubjectServiceImp pService;

    @Autowired
    public CodeGenerator scg;

    @Autowired
    public CatalogServiceImp commonDict;

    private List<CatalogItem> resolutionType;

    Integer GLOBAL_INDEX = 5;


    @Before
    public void setDecl() throws ServiceDaoException {

      //  baseClean.DeclClean();

        resolutionType = commonDict.getCatalogItemsByTyp(Integer.decode(CatalogConstants.RESOLUTION_TYP));

    }

    @Test
    public void OrderTestController() throws DaoException, ServiceException {

         // AddDecl();

         // AddNewDecl();

          GetDecl();

         // GetJournal();

         // UpdateResolutionDecl();

         // UpdateDeclarantDecl();

         // UpdateDeclUser();

    }

    public void UpdateDeclUser() throws ServiceDaoException {

        DetachedCriteria dc = DetachedCriteria.forClass(DeclUser.class);

        DetachedCriteria ocr = DetachedCriteria.forClass(OPerson.class).add(Restrictions.sqlRestriction("rownum < 10"));

        List<DeclUser> du_list =  bsi.getCriterion(dc);

        List<OPerson> op_list = oService.getCriterion(ocr);

        Random r = new Random();

        int Rbo = r.nextInt(op_list.size()-1);

        OPerson op = op_list.get(Rbo);

        Rbo = r.nextInt(du_list.size()-1);

        DeclUser du_one = du_list.get(Rbo);

        dsi.changeDeclUser(du_one.getDecl_id(),du_one.getDecluser_id(),op);

        op_list.get(Rbo);

    }


    public void UpdateDeclarantDecl() throws ServiceDaoException {

        DetachedCriteria dc = DetachedCriteria.forClass(Declarant.class).add(Restrictions.isNotNull("parentPerson"));

        List<Declarant> decl_before = bsi.getCriterion(dc);

        Random r = new Random();

        int Rbp = r.nextInt(decl_before.size() - 1);

        if(decl_before.size() > 1) {

            Declarant declar_todel = decl_before.get(Rbp);

            Declarant declar_toupd = decl_before.get(Rbp).getParentPerson();

            Decl decl_del = dsi.deleteDeclarantInDecl(declar_todel.getDeclarantId(), declar_todel.getDecl_id());

            List<Declarant> decl_after =  bsi.getCriterion(dc);

            assert((decl_before.size()-decl_after.size()) == 1);

        }


    }

    /********************************************************************************/
    public void UpdateResolutionDecl() throws ServiceDaoException {

        DetachedCriteria dc = DetachedCriteria.forClass(DeclResolution.class);

        List<DeclResolution> resol_list =  bsi.getCriterion(dc);

        Random r = new Random();

        int Rbp = r.nextInt(resol_list.size() - 1);

        DeclResolution resol = resol_list.get(Rbp);

        Decl dcl = dsi.get(resol.getDecl_id());

        int iResol = r.nextInt(resolutionType.size()-1);

        dcl.getDclresolution().iterator().next().setResolutionType(resolutionType.get(iResol));

        dsi.update(dcl);

    }

    /********************************************************************************/
    public Set<Declarant> manyRepreoneDecl( List<PPerson> pp)
    {
        Set<Declarant> dclarnt = new HashSet<Declarant>();

        Random r = new Random();

        int Rbp = r.nextInt(pp.size()-3);

        Person p_first =  pp.get(Rbp);
        Person p_second =  pp.get(Rbp+1);
        Person p_third =  pp.get(Rbp+2);

        Declarant dcl_one = new Declarant();
        Declarant dcl_two = new Declarant();
        Declarant dcl_third = new Declarant();

        dcl_one.setPerson(p_first);
        dcl_two.setPerson(p_second);
        dcl_third.setPerson(p_third);


        dcl_two.setDeclrepr_type(2);
        dcl_third.setDeclrepr_type(2);

        dcl_two.setParentPerson(dcl_one);
        dcl_third.setParentPerson(dcl_one);

        dclarnt.add(dcl_two);
        dclarnt.add(dcl_third);

        return dclarnt;

    }

    /********************************************************************************/
    public Set<Declarant> oneRepreoneDecl( List<PPerson> pp)
    {
        Set<Declarant> dclarnt = new HashSet<Declarant>();

        Random r = new Random();

        int Rbp = r.nextInt(pp.size()-2);

        Person p_first =  pp.get(Rbp);
        Person p_second =  pp.get(Rbp+1);

        Declarant dcl_one = new Declarant();
        Declarant dcl_two = new Declarant();

        dcl_one.setPerson((Person) pp.get(Rbp));
        dcl_two.setPerson((Person) pp.get(Rbp+1));

        dcl_two.setDeclrepr_type(2);
        dcl_two.setParentPerson(dcl_one);

        dclarnt.add(dcl_two);

        return dclarnt;

    }

    /********************************************************************************/
    public Set<Declarant> oneRepremanyDecl( List<PPerson> pp)
    {
        Set<Declarant> dclarnt = new HashSet<Declarant>();

        Random r = new Random();

        int Rbp = r.nextInt(pp.size()-3);

        Person p_first =  pp.get(Rbp);
        Person p_second =  pp.get(Rbp+1);
        Person p_third =  pp.get(Rbp+2);

        Declarant dcl_one = new Declarant();
        Declarant dcl_two = new Declarant();
        Declarant dcl_third = new Declarant();
        Declarant dcl_forth = new Declarant();

        dcl_one.setPerson(p_first);
        dcl_two.setPerson(p_first);

        dcl_third.setPerson(p_third);
        dcl_forth.setPerson(p_second);

        dcl_one.setDeclrepr_type(2);
        dcl_two.setDeclrepr_type(2);

        dcl_one.setParentPerson(dcl_third);
        dcl_two.setParentPerson(dcl_forth);

        dclarnt.add(dcl_one);
        dclarnt.add(dcl_two);

        return dclarnt;

    }

    public void AddNewDecl() throws ServiceDaoException, ServiceException {

        DetachedCriteria pcr = DetachedCriteria.forClass(PPerson.class).add(Restrictions.sqlRestriction("rownum < 10"));
        DetachedCriteria ocr = DetachedCriteria.forClass(OPerson.class).add(Restrictions.sqlRestriction("rownum < 10"));

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        List<OPerson> op = oService.getCriterion(ocr);
        List<PPerson> pp = pService.getCriterion(pcr);

        Random r = new Random();

        /****************************************************************************************/

        for (int i = 0; i < GLOBAL_INDEX ; i++) {
            Set<DeclUser> sdu = new HashSet<DeclUser>();
            Set<Declarant> dclarnt = new HashSet<Declarant>();

            int Rbo = r.nextInt(op.size()-1);

            DeclUser du_one = new DeclUser();
            cal.add(Calendar.DATE, -Rbo);
            du_one.setDate_in(cal.getTime());
            du_one.setoPerson(op.get(Rbo));
            sdu.add(du_one);


            Decl dcl = dsi.addNewEmptyDecl(op.get(Rbo),resolutionType.get(0));

        }


    }

    public void AddDecl() throws ServiceDaoException, ServiceException {

        DetachedCriteria pcr = DetachedCriteria.forClass(PPerson.class).add(Restrictions.sqlRestriction("rownum < 10"));
        DetachedCriteria ocr = DetachedCriteria.forClass(OPerson.class).add(Restrictions.sqlRestriction("rownum < 10"));

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();

        List<OPerson> op = oService.getCriterion(ocr);
        List<PPerson> pp = pService.getCriterion(pcr);

        Random r = new Random();

        /****************************************************************************************/

        for (int i = 0; i < GLOBAL_INDEX ; i++) {
            Set<DeclUser> sdu = new HashSet<DeclUser>();
            Set<Declarant> dclarnt = new HashSet<Declarant>();

            Decl dcl = new Decl();

            int Rbo = r.nextInt(op.size()-1);

            DeclUser du_one = new DeclUser();
            cal.add(Calendar.DATE, -Rbo);
            du_one.setDate_in(cal.getTime());
            du_one.setoPerson(op.get(Rbo));
            sdu.add(du_one);


            /*********************Declarant**************************************************/

            int randomNum = r.nextInt(3) + 1;

            if(randomNum == 1) dcl.setDeclarants(oneRepreoneDecl(pp));
            if(randomNum == 2) dcl.setDeclarants(manyRepreoneDecl(pp));
            if(randomNum == 3) dcl.setDeclarants(oneRepremanyDecl(pp));


            /******************************DeclUser*****************************************/

            dcl.setoUsers(sdu);
            cal.add(Calendar.DATE, -Rbo);
            dcl.setDecldate(cal.getTime());

            /**************************DeclResolution***************************************/


            final DeclResolution dr = new DeclResolution();
            dr.setResolutionType(resolutionType.get(0));
            dr.setResolutionDate(cal.getTime());

            dr.setoPerson(op.get(Rbo));

            /********************************************************************************/

            dcl.setDclresolution(new HashSet<DeclResolution>() {{add(dr);}});
            dcl.setDecltype(1);
            dcl.setUrgency(0);

            dsi.add(dcl);


        }

    }

    public void GetDecl() throws ServiceException, ServiceDaoException {

       // List<Decl> ld = dsi.getAll(Decl.class);

       // Decl dc = dsi.get(1052);

        //Assert.notNull(ld, " List<Decl> ld was null");

    }

    public void GetJournal() throws ServiceException, ServiceDaoException {

        DetachedCriteria du = DetachedCriteria.forClass(DeclUser.class);

        List<DeclUser> ldc = bsi.getCriterion(du);

        org.junit.Assert.assertNotNull(ldc);

        Random r = new Random();

        int Rbp = r.nextInt(ldc.size() - 1);

        DeclUser get_declar = ldc.get(Rbp);

        List<Journal> mp = dsi.getJournal(get_declar.getoPerson());

        org.junit.Assert.assertNotNull(mp);


    }

}