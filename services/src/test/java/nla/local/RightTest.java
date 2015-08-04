package nla.local;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.object.Object_dest;
import nla.local.pojos.rights.Right;
import nla.local.pojos.rights.RightOwner;
import nla.local.pojos.subjects.PPerson;
import nla.local.services.IObjectService;
import nla.local.services.IRightService;
import nla.local.services.impl.BaseServiceImp;
import nla.local.services.impl.CatalogServiceImp;
import nla.local.services.impl.subjects.PSubjectServiceImp;
import nla.local.util.BaseClean;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
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
    public BaseClean baseClean;

    @Qualifier("baseServiceImp")
    @Autowired
    public BaseServiceImp bsi;

    @Autowired
    public IRightService rsi;


    @Autowired
    @Qualifier("PSubjectServiceImp")
    public PSubjectServiceImp pService;

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

        baseClean.RightClean();

        rightTypeList = catalogService.getCatalogItemsByTyp(20);
        rightEntytyTypeList = catalogService.getCatalogItemsByTyp(1);
        rightCountTypeList = catalogService.getCatalogItemsByTyp(21);

        long startTime = System.nanoTime();

      //  generateSingleRight();


      //  generateSingleRightPass();

          generatesharedRight();

       // findbySubjectId();

      //   getRightbyObject();

        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;

        System.out.println("That took " + duration + " milliseconds");

    }


    public void generateSingleRightPass()  throws ServiceDaoException, ServiceException
    {

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


        List<Object_dest> ret_val_dest = (List<Object_dest>) ios.findObjectbyInventoryNumCommon(11, 3, 0);

        List<PPerson> lp = pService.findByFIOType("дженкинс", null, null, null, 110);


        /////-------------------------------------set Rights-----------------------------------------------//////

        Right rgt = new Right();

        RightOwner orgt = new RightOwner();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        rgt.setRight_type(rightType);

        rgt.setRight_entyty_type(directType);

        rgt.setRight_count_type(rightCountType);

        rgt.setBegin_date(cal.getTime());

        rgt.setIs_needed(0);

        rgt.setComments("Object number = " + 0);

        rgt.setOoper_id(152);

        rgt.setBindedObj(ret_val_dest.get(0));

        rgt.setStatus(1);


        /////-------------------------------------set RightOwner-----------------------------------------------//////

        int p_index  = r.nextInt(lp.size() - 1);

        orgt.setOoper_id(rgt.getOoper_id());

        orgt.setStatus(1);

        orgt.setDate_in(cal.getTime());

        orgt.setOwner(lp.get(p_index));

        orgt.setNumerator_part(1);

        orgt.setDenominator_part(1);

        /////---------------------------------bind ------------------------------------------------------------//////

        orgt.setRight(rgt);

       // rgt.setRight_owner_lst( new HashSet<RightOwner>(Arrays.asList(orgt)));

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        rsi.passSingleRight(orgt);//.addRightOwner(orgt);

    }

    public void findbySubjectId() throws ServiceDaoException {

        List<Right> lrt = rsi.findbySubject(1046);

        List<RightOwner> lrt_own_ = rsi.getRightbyObjectOwn("минск, авиации 14", null);

        lrt_own_ = rsi.findbyObjectPersonOwn(new Long[] {Long.valueOf(82)},1046);


    }

    public void getRightbyObject() {
        try {

            Set<Right> lr = new HashSet<Right>(rsi.getRightbyObject("минск, авиации 14", null));

            long endTime = System.nanoTime();

        } catch (ServiceDaoException e) {
            e.printStackTrace();
        }

    }

    public void generatesharedRightPass() throws ServiceDaoException, ServiceException {


        CatalogItem   countType = CollectionUtils.find(rightCountTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("право одного лица");
            }
        });


        Set<Right> r_lrt = new HashSet<Right>(rsi.findbyrightCountType(countType));//(rsi.findbySubject(f_subject_id));

        Right lrt = r_lrt.iterator().next();

        final Integer f_subject_id = lrt.getRight_owner_lst().iterator().next().getOwner().subjectId;

        List<PPerson> lp = pService.findByFIOType("дженкинс", null, null, null, 110);

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

        Set<RightOwner> sro = lrt.getRight_owner_lst();

        RightOwner rown = CollectionUtils.find(sro, new Predicate() {
            public boolean evaluate(Object o) {
                RightOwner c = (RightOwner) o;
                return c.getOwner().getSubjectId() == f_subject_id.intValue();
            }
        });

        /***************************init right***************************************************************/

        Right rgt = new Right();

        rgt.setRight_type(rightType);

        rgt.setRight_entyty_type(directType);

        rgt.setRight_count_type(rightCountType);

        rgt.setBegin_date(cal.getTime());

        rgt.setIs_needed(0);

        rgt.setComments("Object number ");

        rgt.setOoper_id(152);

        rgt.setObject_entity_id(lrt.getObject_entity_id() );

        rgt.setStatus(1);

        /***************************** update parent owner **********************************************************/

        rown.setDate_out(cal.getTime());

        rown.setOoper_id(156);

        rown.setStatus(0);

        rsi.updateRightOwner(rown);

        /******************************* add right********************************************************/

        /* Можно раскоменчивавть, а можно и нет*/
        // rsi.addRight(rgt);

        /***************************init shred owners***************************************************************/

        for (int i = 0 ; i< 3; i++)
        {
            RightOwner orgt = new RightOwner();

            orgt.setOoper_id(152);

            orgt.setStatus(1);

            orgt.setDate_in(cal.getTime());

            orgt.setOwner(lp.get(i));

            orgt.setNumerator_part(1);

            orgt.setDenominator_part(3);

            orgt.setParent_owner(rown);

            orgt.setRight(rgt);

            rsi.addRightOwner(orgt);

        }

    }


    public void generatesharedRight() throws ServiceDaoException, ServiceException {

        CatalogItem   countType = CollectionUtils.find(rightCountTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("право одного лица");
            }
        });


        Set<Right> r_lrt = new HashSet<Right>(rsi.findbyrightCountType(countType));//(rsi.findbySubject(f_subject_id));

        Right lrt = r_lrt.iterator().next();

        final Integer f_subject_id = lrt.getRight_owner_lst().iterator().next().getOwner().subjectId;

         List<PPerson> lp = pService.findByFIOType("дженкинс", null, null, null, 110);

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

        Set<RightOwner> sro = lrt.getRight_owner_lst();

        RightOwner rown = CollectionUtils.find(sro, new Predicate() {
            public boolean evaluate(Object o) {
                RightOwner c = (RightOwner) o;
                return c.getOwner().getSubjectId() == f_subject_id.intValue();
            }
        });

        /***************************init right***************************************************************/

        Right rgt = new Right();

        rgt.setRight_type(rightType);

        rgt.setRight_entyty_type(directType);

        rgt.setRight_count_type(rightCountType);

        rgt.setBegin_date(cal.getTime());

        rgt.setIs_needed(0);

        rgt.setComments("Object number ");

        rgt.setOoper_id(152);

        rgt.setObject_entity_id(lrt.getObject_entity_id() );

        rgt.setStatus(1);

        /***************************** update parent owner **********************************************************/

        rown.setDate_out(cal.getTime());

        rown.setOoper_id(156);

        rown.setStatus(0);

        rsi.updateRightOwner(rown);

        /******************************* add right********************************************************/

        /* Можно раскоменчивавть, а можно и нет*/
       // rsi.addRight(rgt);

        /***************************init shred owners***************************************************************/

       for (int i = 0 ; i< 3; i++)
       {
           RightOwner orgt = new RightOwner();

           orgt.setOoper_id(152);

           orgt.setStatus(1);

           orgt.setDate_in(cal.getTime());

           orgt.setOwner(lp.get(i));

           orgt.setNumerator_part(1);

           orgt.setDenominator_part(3);

           orgt.setParent_owner(rown);

           orgt.setRight(rgt);

           rsi.addRightOwner(orgt);

       }

    }

}
