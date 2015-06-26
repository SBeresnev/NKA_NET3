package nla.local;

import nla.local.exception.ServiceException;
import nla.local.pojos.address.Address_dest;
import nla.local.pojos.address.Address_src;
import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.object.Object_dest;
import nla.local.pojos.object.Object_src;
import nla.local.services.IAddressService;
import nla.local.services.impl.CatalogServiceImp;
import nla.local.util.BaseClean;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;

import nla.local.exception.ServiceDaoException;
import nla.local.services.IObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by beresnev on 29.05.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-services.xml","classpath:beans-dao.xml"})
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class ObjectTest{

    @Autowired
    public BaseClean baseClean;

    @Autowired
    private IObjectService osi;

    @Autowired
    private IAddressService asi;

    @Autowired
    private CatalogServiceImp catalogService;

    @Test
    public void ObjectTestController() throws ServiceDaoException, ServiceException {

        baseClean.ObjectClean();

        long startTime = System.nanoTime();

        bindObjectbyAddressCommon();

        bindObjectbyInventoryNumCommon();

        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;

        System.out.println("That took " + duration + " milliseconds");

    }


    // Поиск новых адресов через adr_num
    public void bindObjectbyAddressCommon() throws ServiceDaoException, ServiceException {

        Address_dest adr_dest = asi.getdestbyIDs(null, Long.valueOf(878782));

        if (adr_dest == null) {

            adr_dest =  asi.convertSrctoDest(asi.getsrcbyID(null, Long.valueOf(878782), 3).get(0));

        }

        List<Object_dest> ret_val_dest =  osi.findObjectbyAddressCommon( Arrays.asList(adr_dest.getAdr_num()));

         int i = 0;

            for( Object_dest dst_src : ret_val_dest )
            {

                dst_src.setReadiness(100);

                dst_src.setConserv(0);

                dst_src.setReg_type(1);

                dst_src.setStatus(1);

                List<CatalogItem>  obj_pup = catalogService.getCatalogItemsByTyp(Integer.decode(CatalogConstants.USE_PURPOSE));

                CatalogItem  objectType = CollectionUtils.find(obj_pup, new Predicate() {
                    public boolean evaluate(Object o) {
                        CatalogItem c = (CatalogItem) o;
                        return c.getCode_name().toLowerCase().contains("сооружение");
                    }
                });

                dst_src.setAddress_dest(adr_dest);

                dst_src.setUse_purpose(objectType);

                dst_src.setAdr_num(adr_dest.getAdr_num());

                ret_val_dest.set(i,dst_src);

                i++;

            }



        if(ret_val_dest.size() > 0) { osi.bindObject(ret_val_dest.get(0)); }

    }


    public void bindObjectbyInventoryNumCommon() throws ServiceDaoException, ServiceException {

        List<Object_dest> ret_val_dest = (List<Object_dest>) osi.findObjectbyInventoryNumCommon(11, 3, 0);

        if (ret_val_dest.size() >0 ) {

            ret_val_dest.get(0).setReadiness(50);

            ret_val_dest.get(0).setConserv(0);

            ret_val_dest.get(0).setReg_type(1);

            ret_val_dest.get(0).setStatus(1);

            ret_val_dest.get(0).setOoper(120);

            List<CatalogItem> obj_pup = catalogService.getCatalogItemsByTyp(Integer.decode(CatalogConstants.USE_PURPOSE));

            CatalogItem objectType = CollectionUtils.find(obj_pup, new Predicate() {
                public boolean evaluate(Object o) {
                    CatalogItem c = (CatalogItem) o;
                    return c.getCode_name().toLowerCase().contains("сооружение");
                }
            });

            ret_val_dest.get(0).setUse_purpose(objectType);

        }

        osi.bindObject(ret_val_dest.get(0));

    }


}