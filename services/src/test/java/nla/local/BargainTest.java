package nla.local;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.bargain.Bargain;
import nla.local.pojos.bargain.BargainContent;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.object.Object_dest;
import nla.local.pojos.rights.Right;
import nla.local.pojos.rights.RightOwner;
import nla.local.pojos.subjects.PPerson;
import nla.local.services.IBargainService;
import nla.local.services.IObjectService;
import nla.local.services.IRightService;
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
public class BargainTest {

    @Autowired
    public BaseClean baseClean;

    @Autowired
    public IRightService rsi;

    @Autowired
    public IObjectService osi;

    @Autowired
    public IBargainService bsi;


    @Autowired
    private CatalogServiceImp catalogService;

    private Integer GLOBAL_COUNT_INTDEX = 1;

    private List<CatalogItem> bargainEntytyType;
    private List<CatalogItem> bargainType;


    @Test
    public void RightTestController() throws ServiceDaoException, ServiceException {

       // baseClean.RightClean();

        bargainEntytyType = catalogService.getCatalogItemsByTyp(35);
        bargainType = catalogService.getCatalogItemsByTyp(4);

        long startTime = System.nanoTime();

        addBargain();

        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;

        System.out.println("That took " + duration + " milliseconds");

    }


    public void addBargain() throws ServiceDaoException, ServiceException {

        BargainContent brg_cont = new BargainContent();

        ///*************************************Bargain initialization******************************************************************///
        Bargain brg = new Bargain();

        CatalogItem rightcounttype = CollectionUtils.find(bargainType, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("рента");
            }
        });

        brg.setStatus(1);
        brg.setAffiliates(0);
        brg.setBargain_condition("");
        brg.setBargain_type(rightcounttype);
        brg.setDoc_id(159);

        brg.setRegistred(0);
        brg.setDone(0);

        brg.setRelatives(0);
        brg.setCurrency_complex(1658);
        brg.setPrice_value_complex(1568);
        brg.setVat_complex(1863);
        brg.setOoper_id(156);

        brg.setCount_object(0);


        ///**************************************Bargain Content init*****************************************************************///


        List<CatalogItem> rightEntytyTypeList = catalogService.getCatalogItemsByTyp(21);

        CatalogItem  directType = CollectionUtils.find(rightEntytyTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("долевое право");
            }
        });


         Set<RightOwner> r_lrt = new HashSet<RightOwner>(rsi.findbyrightCountType(directType));

         RightOwner row = CollectionUtils.find( r_lrt, new Predicate() {
                public boolean evaluate(Object o) {
                    RightOwner c = (RightOwner) o;
                    return (c.getDate_out() == null && c.getStatus() == 1);

                }
            });

        brg_cont.setBindedRight(row);

        CatalogItem bargainentyty = CollectionUtils.find(bargainEntytyType, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("право");
            }
        });



        brg_cont.setBargain_entity_type(bargainentyty);

        brg_cont.setRight_entity_id(row.getRight_owner_id());

        brg_cont.setStatus(1);

        brg_cont.setOoper_id(156);

        brg_cont.setBargain(brg);

        bsi.addBargain(brg_cont);

    }

}
