package nla.local;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.dict.CatalogPk;
import nla.local.pojos.object.Object_dest;
import nla.local.pojos.operations.EntityType;
import nla.local.pojos.operations.Operation;
import nla.local.pojos.orders.Decl;
import nla.local.pojos.rights.Right;
import nla.local.pojos.rights.RightOwner;
import nla.local.pojos.subjects.OPerson;
import nla.local.pojos.subjects.PPerson;
import nla.local.pojos.subjects.Person;
import nla.local.services.IDeclService;
import nla.local.services.IObjectService;
import nla.local.services.IRightService;
import nla.local.services.ISubjectService;
import nla.local.services.impl.BaseServiceImp;
import nla.local.services.impl.CatalogServiceImp;
import nla.local.services.impl.subjects.OSubjectServiceImp;
import nla.local.services.impl.subjects.PSubjectServiceImp;
import nla.local.util.BaseClean;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.SerializationUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by beresnev on 25.06.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-services.xml","classpath:beans-dao.xml"})
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class RightTest {

    @Autowired
    public IDeclService ids;

    @Autowired
    public BaseServiceImp baseServiceImp;

    @Autowired
    public BaseClean baseClean;

    @Qualifier("baseServiceImp")
    @Autowired
    public BaseServiceImp bsi;

    @Autowired
    public IRightService rsi;

    @Autowired
    private OSubjectServiceImp oServices;


    @Autowired
    @Qualifier("PSubjectServiceImp")
    public PSubjectServiceImp pService;

    @Qualifier("OSubjectServiceImp")
    @Autowired
    private ISubjectService<OPerson> oPerson;


    @Autowired
    public IObjectService ios;

    @Autowired
    private CatalogServiceImp catalogService;

    private Integer GLOBAL_COUNT_INTDEX = 1;

    private List<CatalogItem> rightTypeList;
    private List<CatalogItem> rightEntytyTypeList;
    private List<CatalogItem> rightCountTypeList;

    @Test
    public void RightTestController() throws ServiceDaoException, ServiceException {

       /*
       baseClean.RightClean();

        baseClean.ObjectClean();

        baseClean.OperationsClean(); */

        rightTypeList = catalogService.getCatalogItemsByTyp(20);
        rightEntytyTypeList = catalogService.getCatalogItemsByTyp(1);
        rightCountTypeList = catalogService.getCatalogItemsByTyp(21);

        long startTime = System.nanoTime();

       // generateSingleRightPass();

      //  generateSharedRightPass();

        splitsharedRight();

       // passsharedRight();

       // findbySubjectId();

       // getRightbyObject();

        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;

        System.out.println("That took " + duration + " milliseconds");

    }


    public void generateSingleRightPass()  throws ServiceDaoException, ServiceException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();

        Random r = new Random();

        CatalogItem  rightType = CollectionUtils.find(rightTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("собств");
            }
        });

        CatalogItem  directType = CollectionUtils.find(rightEntytyTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("объект");
            }
        });

        CatalogItem   rightCountType = CollectionUtils.find(rightCountTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("право одного лица");
            }
        });


        List<Object_dest> ret_val_dest = (List<Object_dest>) ios.findObjectbyInventoryNumCommon(1, 2, 100);

        ret_val_dest.get(0).setReg_type(1);

        ret_val_dest.get(0).setStatus(1);


        Operation opr = getOperField(61,1,62,1,63,1010);

        ret_val_dest.get(0).setOoper(opr);

        List<PPerson> lp = pService.getAll();//findByFIOType("дженкинс", null, null, null, 110);

        /////-------------------------------------set Rights-----------------------------------------------//////

        Right rgt = new Right();

        RightOwner orgt = new RightOwner();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        rgt.setRight_type(rightType.getCode_id());

        rgt.setRight_entyty_type(directType.getCode_id());

        rgt.setRight_count_type(rightCountType.getCode_id());

        rgt.setBegin_date(cal.getTime());

        rgt.setIs_needed(0);

        rgt.setComments("Object number" );

        rgt.setBindedObj(ret_val_dest.get(0));

        rgt.setStatus(1);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        opr = getOperField(61,10,62,1,63,3100);

        rgt.setOoper(opr);

        /////-------------------------------------set RightOwner-----------------------------------------------//////

        int p_index  = r.nextInt(lp.size() - 1);

        orgt.setStatus(1);

        orgt.setDate_in(cal.getTime());

        orgt.setOwner(lp.get(p_index));

        orgt.setNumerator_part(1);

        orgt.setDenominator_part(1);

        opr = getOperField(61,10,62,1,63,3270);

        orgt.setOoper(opr);

        /////---------------------------------bind ------------------------------------------------------------//////

        orgt.setRight(rgt);

       // rgt.setRight_owner_lst( new HashSet<RightOwner>(Arrays.asList(orgt)));

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        rsi.passSingleRight(orgt);//.addRightOwner(orgt);

    }

    public void generateSharedRightPass() throws ServiceDaoException, ServiceException {

        CatalogItem   countType = CollectionUtils.find(rightCountTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("право одного лица");
            }
        });


        Set<RightOwner> r_lrt = new HashSet<RightOwner>(rsi.findbyrightCountType(countType.getCode_id()));//(rsi.findbySubject(f_subject_id));

        final Integer f_subject_id = r_lrt.iterator().next().getOwner().subjectId;

        List<PPerson> lp = pService.getAll();//findByFIOType("дженкинс", null, null, null, 110);

        /***********************************************************************************/

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();

        Random r = new Random();

        CatalogItem  rightType = CollectionUtils.find(rightTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("собств");
            }
        });

        CatalogItem  directType = CollectionUtils.find(rightEntytyTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("доля в праве");
            }
        });

        CatalogItem   rightCountType = CollectionUtils.find(rightCountTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("долевое право");
            }
        });

        /*************************find parent owner**********************************************************/

        Long object_id = r_lrt.iterator().next().getRight().getObject_entity_id();

        Long [] r_id = new Long [] {r_lrt.iterator().next().getRight().getRight_id()};

        Set<RightOwner> sro =  new HashSet<RightOwner>(rsi.getRightOwnersbyRight(r_id));

        RightOwner rown = CollectionUtils.find(sro, new Predicate() {
            public boolean evaluate(Object o) {
                RightOwner c = (RightOwner) o;
                return c.getOwner().getSubjectId() == f_subject_id.intValue();
            }
        });

        /***************************init right***************************************************************/

        Right rgt = new Right();

        rgt.setRight_type(rightType.getCode_id());

        rgt.setRight_entyty_type(directType.getCode_id());

        rgt.setRight_count_type(rightCountType.getCode_id());

        rgt.setBegin_date(cal.getTime());

        rgt.setIs_needed(0);

        rgt.setComments("Object number ");

        rgt.setObject_entity_id(object_id);

        rgt.setStatus(1);

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Operation opr = getOperField(61,10,62,1,63,2250);

        rgt.setOoper(opr);

        /***************************** update parent owner **********************************************************/

        rown.setOoper(opr);

        rsi.updateRightOwner(rown);

        /******************************* add right********************************************************/

        /* Можно раскоменчивавть, а можно и нет*/
        // rsi.addRight(rgt);

        /***************************init shred owners***************************************************************/

       opr = getOperField(61,10,62,1,63,3100);

       for (int i = 0 ; i< 3; i++)
        {
            RightOwner orgt = new RightOwner();

            orgt.setOoper(opr);

            orgt.setStatus(1);

            orgt.setDate_in(cal.getTime());

            orgt.setOwner(lp.get(i));

            orgt.setNumerator_part(1);

            orgt.setDenominator_part(3);

            orgt.setParent_owner(rown.getRight_owner_id());

            orgt.setRight(rgt);

            orgt.setOoper(opr);

            rsi.addRightOwner(orgt);

        }

    }

    //////////// Соединяем один ко многим //////////////////
    public void splitsharedRight()  throws ServiceDaoException, ServiceException {

        CatalogItem   countType = CollectionUtils.find(rightCountTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("долевое право");
            }
        });


        CatalogItem  rightType = CollectionUtils.find(rightTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("собств");
            }
        });

        CatalogItem  directType = CollectionUtils.find(rightEntytyTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("доля в праве");
            }
        });

        CatalogItem   rightCountType = CollectionUtils.find(rightCountTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("долевое право");
            }
        });


        /***************************init right***************************************************************/

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();

        Random r = new Random();

        Set<RightOwner> r_lrt = new HashSet<RightOwner>(rsi.findbyrightCountType(1));//(rsi.findbySubject(f_subject_id));

        RightOwner par_row = r_lrt.iterator().next();

        Long object_id = r_lrt.iterator().next().getRight().getObject_entity_id();

        List<PPerson> lp = pService.findByFIOType("дженкинс", null, null, null, 110);

        /**************************** Parent owner update ****************************************************/

        Operation opr = getOperField(61,5,62,3,63,1200);

        par_row.setOoper(opr);

        par_row.setStatus(0);

        par_row.setDate_out(cal.getTime());

        /*****************************************************************************************************/

        Right rgt = new Right();

        rgt.setRight_type(rightType.getCode_id());

        rgt.setRight_entyty_type(directType.getCode_id());

        rgt.setRight_count_type(rightCountType.getCode_id());

        rgt.setBegin_date(cal.getTime());

        rgt.setIs_needed(0);

        rgt.setComments("Object number ");

        rgt.setObject_entity_id(object_id);

        rgt.setStatus(1);

        opr = getOperField(61,10,62,1,63,3100);

        rgt.setOoper(opr);

        /******************************* add right********************************************************/

        /* Можно раскоменчивавть, а можно и нет*/
        // rsi.addRight(rgt);

        /***************************init shred owners***************************************************************/

        List<RightOwner> own_list = new ArrayList<RightOwner>();


        for (int i = 0 ; i< 3; i++)
        {
            RightOwner orgt = new RightOwner();

            orgt.setOoper(opr);

            orgt.setStatus(1);

            orgt.setDate_in(cal.getTime());

            orgt.setOwner(lp.get(i));

            orgt.setNumerator_part(1);

            orgt.setDenominator_part(3);

            orgt.setRight(rgt);

            own_list.add(orgt);


        }

        rsi.splitSharedRight(own_list, par_row);

    }

    //////////// Соединяем много прав в один //////////////////
    public void passsharedRight()  throws ServiceDaoException, ServiceException {


        CatalogItem   countType = CollectionUtils.find(rightCountTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("долевое право");
            }
        });


        CatalogItem  rightType = CollectionUtils.find(rightTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("собств");
            }
        });

        CatalogItem  directType = CollectionUtils.find(rightEntytyTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("доля в праве");
            }
        });

        CatalogItem   rightCountType = CollectionUtils.find(rightCountTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("долевое право");
            }
        });

        Operation opr = getOperField(61,10,62,1,63,3100);

        /***************************init right***************************************************************/

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();

        Random r = new Random();

        Set<RightOwner> r_lrt = new HashSet<RightOwner>(rsi.findbyrightCountType(countType.getCode_id()));//(rsi.findbySubject(f_subject_id));

        Long object_id = r_lrt.iterator().next().getRight().getObject_entity_id();

        List<PPerson> lp = pService.findByFIOType("дженкинс", null, null, null, 110);

        r_lrt = new HashSet<RightOwner>(rsi.getRightOwnersbyRight(new Long[] { r_lrt.iterator().next().getRight().getRight_id()}));

        /***************************************************************************************************/

        Right rgt = new Right();

        rgt.setRight_type(rightType.getCode_id());

        rgt.setRight_entyty_type(directType.getCode_id());

        rgt.setRight_count_type(rightCountType.getCode_id());

        rgt.setBegin_date(cal.getTime());

        rgt.setIs_needed(0);

        rgt.setComments("Object number ");

        rgt.setOoper(opr);

        rgt.setObject_entity_id(object_id);

        rgt.setStatus(1);

        RightOwner orgt = new RightOwner();

        orgt.setOoper(opr);

        orgt.setStatus(1);

        orgt.setDate_in(cal.getTime());

        orgt.setOwner(lp.get(0));

        orgt.setRight(rgt);

        opr = getOperField(61,10,62,1,63,3100);

        orgt.setOoper(opr);
        /***************************init shred owners ***************************************************************/

        HashMap<RightOwner,RightOwner> h_own_list =  new HashMap<RightOwner,RightOwner>();

        /***************************set compatibility between parent and child owner. Fill Hash*********************************/

        for (RightOwner parent_owner :  r_lrt)
        {
            parent_owner.setStatus(0);

            parent_owner.setDate_out(cal.getTime());


            RightOwner child_own = new RightOwner();

            child_own = SerializationUtils.clone(orgt) ;

            child_own.setNumerator_part(parent_owner.getNumerator_part());

            child_own.setDenominator_part(parent_owner.getDenominator_part());

            child_own.setOoper(opr);

            child_own.setStatus(1);

            h_own_list.put(parent_owner,child_own);


        }

        rsi.passSharedRight(h_own_list);

    }

    public void generateLimitations() throws ServiceDaoException, ServiceException {

        Operation opr = getOperField(61,10,62,1,63,3100);

        CatalogItem  directType = CollectionUtils.find(rightEntytyTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("право");
            }
        });

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();

        Right rght = new Right();

        rght.setStatus(1);

        rght.setBegin_date(cal.getTime());

        rght.setOoper(opr);

        rght.setIs_needed(0);

        rght.setRight_count_type(null);

        rght.setRight_entyty_type(directType.getCode_id()); //200

        CatalogItem  rightType = CollectionUtils.find(rightTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("ограничения (обременения) прав, установленные законодательством");
            }
        });

        rght.setRight_type(rightType.getCode_id());

        DetachedCriteria dc =  DetachedCriteria.forClass(Right.class);

        dc.add(Restrictions.eq("status",1));

        List<Right> all_right = rsi.getCriterion(dc);

        rght.setLimit_righ(all_right.get(0).getRight_id());

        rght.setObject_entity_id(all_right.get(0).getObject_entity_id());

        rsi.addRight(rght);

    }

    /////////// Поиск прав //////////////////////////////////////
    public void findbySubjectId() throws ServiceDaoException {

        List<RightOwner> lrt_own_ = rsi.getRightbyObjectAddr("минск, авиации 14", null);

        lrt_own_ = rsi.getRightbyObjectPerson(new Long[]{Long.valueOf(255)}, null);

    }

    public void getRightbyObject() {
        try {


            Set<RightOwner> lr = new HashSet<RightOwner>(rsi.getRightbyObjectAddr("минск, авиации 14", null));

            long endTime = System.nanoTime();

        } catch (ServiceDaoException e) {
            e.printStackTrace();
        }

    }


    public Operation getOperField(int operType, int operCode, int operSubtype, int operSubcode, int reasonType, int ReasonCode ) throws ServiceDaoException {


        DetachedCriteria dc_crit =  DetachedCriteria.forClass(Decl.class).add(Restrictions.sqlRestriction("rownum = 1"));

        List<Decl> dec_list = ids.getCriterion(dc_crit);


        Operation oper = new Operation();

        oper.setDeclId(dec_list.get(0).getDecl_id());

        oper.setEntytyType(EntityType.toInt(EntityType.OBJECT));

        oper.setOperDate(new Date());


        CatalogPk cp = new CatalogPk();

        cp.setAnalytic_type(operType);

        cp.setCode_id(operCode);

        CatalogItem ci = catalogService.getCatalogItem(cp);                         // формирование

        oper.setOperType(ci);

        baseServiceImp.getBaseDao().getSession().evict(ci);


        cp.setAnalytic_type(operSubtype);

        cp.setCode_id(operSubcode);

        ci = catalogService.getCatalogItem(cp);

        oper.setOperSubtype(ci);

        baseServiceImp.getBaseDao().getSession().evict(ci);


       // cp.setAnalytic_type(resonType);

       // cp.setCode_id(ReasonCode);

        //ci = catalogService.getCatalogItem(cp);

        ci = catalogService.getCatalogItem(63,3100);

        oper.setReason(ci);

        baseServiceImp.getBaseDao().getSession().evict(ci);



        oper.setRegDate(new Date ());

        List<OPerson> ops = oServices.findOffUser("", "", "", null, "", null);

        Person prs = ops.get(0);

        oper.setExecutor(prs);

        oper.setStatus(1);


        return oper ;
    }



}
