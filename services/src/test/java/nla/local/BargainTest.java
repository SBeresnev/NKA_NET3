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
    private CatalogServiceImp catalogService;

    private Integer GLOBAL_COUNT_INTDEX = 1;

    private List<CatalogItem> rightTypeList;
    private List<CatalogItem> rightEntytyTypeList;
    private List<CatalogItem> rightCountTypeList;

    @Test
    public void RightTestController() throws ServiceDaoException, ServiceException {

       // baseClean.RightClean();

        rightTypeList = catalogService.getCatalogItemsByTyp(20);
        rightEntytyTypeList = catalogService.getCatalogItemsByTyp(1);
        rightCountTypeList = catalogService.getCatalogItemsByTyp(21);

        long startTime = System.nanoTime();

        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;

        System.out.println("That took " + duration + " milliseconds");

    }

}
