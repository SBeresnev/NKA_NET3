package nla.local;

import nla.local.pojos.address.Address_dest;
import nla.local.pojos.address.Address_src;
import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import nla.local.services.IAddressService;
import nla.local.services.ICatalogService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import nla.local.exception.ServiceDaoException;
import nla.local.util.CodeGenerator;
import nla.local.pojos.address.Ate;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by beresnev on 30.04.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-services.xml","classpath:beans-dao.xml"})
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class AddressTest {

    private static Logger log = Logger.getLogger(AddressTest.class);

    @Autowired
    private IAddressService asi;

    @Autowired
    public ICatalogService catalogService;

    @Autowired
    private CodeGenerator scg;

    private List<CatalogItem> objectPlceDictList;

    private List<CatalogItem> objectTypeDictList;


    @Before
    public void setAddress() throws ServiceDaoException {

        objectPlceDictList = catalogService.getCatalogItemsByTyp(Integer.decode(CatalogConstants.OBJECT_PLACE));

        objectTypeDictList = catalogService.getCatalogItemsByTyp(Integer.decode(CatalogConstants.OBJECT_TYP));

    }

    @Test
    public void AdddressTestController() throws  ServiceDaoException {

        long startTime = System.nanoTime();

       // AteTest();

       // treeExpand();

        addressTest();

        bindAddress();


        long endTime = System.nanoTime();

        long duration = (endTime - startTime)/1000000;

        log.info("That took " + duration + " milliseconds");

        System.out.println("That took " + duration + " milliseconds");


    }

    public void addressTest() throws  ServiceDaoException {

        List<Ate> test_ate = asi.findATEbyName("г. Минск");

        Ate ate = asi.findATEbyId(test_ate.get(0).getAte_id());

        CatalogItem objectPlce = CollectionUtils.find(objectPlceDictList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("в населенном пункте");
            }
        });

        CatalogItem objectType = CollectionUtils.find(objectTypeDictList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("часть строения");
            }
        });


        List<Address_src> test_address = asi.findHomeAddress(ate.getAte_id(), "некр", 35, null ,177,1);

        assertTrue(test_address.size() == 1);

        objectType = CollectionUtils.find(objectTypeDictList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("строение");
            }
        });


        objectPlce = CollectionUtils.find(objectPlceDictList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("железной");
            }
        });

        test_address = asi.findAddress(test_ate.get(0).getAte_id(),"Коляд", objectType.getCode_id(), objectPlce.getCode_id(), null, null);

        assertTrue(test_address.size() > 2);

    }

    public void treeExpand() throws  ServiceDaoException {

        List<Ate> test_ate = asi.findATEbyParentId(null); // get all regions

        Ate ate = CollectionUtils.find(test_ate, new Predicate() {
            public boolean evaluate(Object o) {
                Ate c = (Ate) o;
                return c.getAte_name().matches(".*(?iu:МОГИЛЕВ).*");
            }
        });

        test_ate = asi.findATEbyName("Чаусский р-н");

        test_ate = asi.findATEbyParentLike(test_ate.get(0).getAte_id(), "Высокое");

        Assert.assertFalse(test_ate.size() == 0);

        test_ate.clear();

        test_ate = asi.findATEbyParentId(null);

        test_ate = asi.findATEbyParentId(test_ate.get(0).getAte_id());

        test_ate = asi.findATEbyParentId(test_ate.get(0).getAte_id());

        /*24382 Сидоровский с/с*/
        test_ate = asi.findATEbyParentLike(test_ate.get(0).getAte_id(), "Лют");

        Assert.assertFalse(test_ate.size() != 2); /* две деревни */

        test_ate = asi.expandATE(test_ate.get(0).getTree_ids());

        Assert.assertFalse(test_ate.size() != 4);

        test_ate.clear();

    }

    public void AteTest() throws  ServiceDaoException {

        List<Ate> la_one = null;

        List<Object> la_cnt = null;

        la_one = asi.findATEbyParentId(null);

        Assert.assertFalse(la_one.size() != 7);

        la_one.clear();

    }



    public void bindAddress() throws  ServiceDaoException {

        List<Address_src> test_address = asi.getsrcbyID(null, Long.valueOf(3129691), 2);

        Address_dest adr_dst_t = asi.convertSrctoDest(test_address.get(0));

        asi.bindAddress(adr_dst_t);

        //List<Address_src> test_address = asi.findHomeAddress(17030, "румян", 17, 8);
        //List<Address_src> test_address = asi.findHomeAddress(25595, "гриш", 7, null);

    }

}
