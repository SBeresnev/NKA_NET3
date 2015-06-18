package nla.local;

import nla.local.exception.ServiceException;
import nla.local.pojos.address.Address_dest;
import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.object.Object_dest;
import nla.local.pojos.object.Object_src;
import nla.local.services.IAddressService;
import nla.local.services.impl.CatalogServiceImp;
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
    private IObjectService osi;

    @Autowired
    private IAddressService asi;

    @Autowired
    private CatalogServiceImp catalogService;

    @Test
    public void ObjectTestController() throws ServiceDaoException, ServiceException {

        long startTime = System.nanoTime();

       // bindObjectbyAddress();

        bindObjectbyInventoryNum();

        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;

        System.out.println("That took " + duration + " milliseconds");

    }


    public void bindObjectbyAddress() throws ServiceDaoException, ServiceException {

        // Поиск новых адресов через adr_num

        CatalogItem ci = new CatalogItem();

        Address_dest adr_dest = asi.getdestbyIDs(null, Long.valueOf(3129691));

        List<Object_dest> ret_val_dest = (List<Object_dest>) osi.findObjectbyAddress(Object_dest.class, Arrays.asList(adr_dest.getAddress_id()));

        if(ret_val_dest.size() == 0) {

            List<Object_src> ret_val_src = (List<Object_src>) osi.findObjectbyAddress(Object_src.class, Arrays.asList(adr_dest.getAdr_num()));

            for( Object_src ret_val : ret_val_src )
            {

                Object_dest odt = osi.convertSrctoDest(ret_val);

                odt.setObj_id_inv(ret_val.getObj_id());

                odt.setReadiness(100);

                odt.setConserv(0);

                odt.setReg_type(1);

                odt.setStatus(1);

                List<CatalogItem>  obj_pup = catalogService.getCatalogItemsByTyp(Integer.decode(CatalogConstants.USE_PURPOSE));

                CatalogItem  objectType = CollectionUtils.find(obj_pup, new Predicate() {
                    public boolean evaluate(Object o) {
                        CatalogItem c = (CatalogItem) o;
                        return c.getCode_name().toLowerCase().contains("сооружение");
                    }
                });

                odt.setUse_purpose(objectType);


                odt.setAdr_num(adr_dest.getAdr_num());

                odt.setAddress_dest(adr_dest); // Не обязательно, хорошо для контроллера сразу вернуть и адресс



                ret_val_dest.add(odt);

            }

        } else {

           ret_val_dest.get(0).setSquare(179);

           ret_val_dest.get(0).setRoomscount(550);

           ret_val_dest.get(0).setOoper(ret_val_dest.get(0).getOoper()+1);

        }

        if(ret_val_dest.size() > 0) { osi.bindObject(ret_val_dest.get(0)); }

    }



    public void bindObjectbyInventoryNum() throws ServiceDaoException, ServiceException {

        //Поиск новых адресов через adr_num
        //List<Object_src> ret_val_src = (List<Object_src>) osi.findObjectbyAddress(Object_src.class, Arrays.asList(((Integer) 840884).longValue()));

        List<Object_dest> ret_val_dest = (List<Object_dest>) osi.findObjectbyInventoryNum(Object_dest.class, 11, 3, 0);

        if (ret_val_dest.size() == 0 ) {


            List<Object_src> ret_val_src = (List<Object_src>) osi.findObjectbyInventoryNum(Object_src.class, 11, 3, 0);

            for (Object_src ret_val : ret_val_src) {

                Object_dest odt = osi.convertSrctoDest(ret_val);

                odt.setObj_id_inv(ret_val.getObj_id());

                odt.setReadiness(100);

                odt.setConserv(0);

                odt.setReg_type(1);

                odt.setStatus(1);

                odt.setOoper(12);

                List<CatalogItem> obj_pup = catalogService.getCatalogItemsByTyp(Integer.decode(CatalogConstants.USE_PURPOSE));

                CatalogItem objectType = CollectionUtils.find(obj_pup, new Predicate() {
                    public boolean evaluate(Object o) {
                        CatalogItem c = (CatalogItem) o;
                        return c.getCode_name().toLowerCase().contains("сооружение");
                    }
                });

                odt.setUse_purpose(objectType);

                //odt.setBound_id(0);

                odt.setAdr_num(ret_val.getAddress_id());

                ret_val_dest.add(odt);

            }

        } else {


            for( Object_dest ret_val : ret_val_dest ) {

                Address_dest ade = asi.getdestbyIDs(ret_val.getAddress_id(),null);

                ret_val.setAddress_dest(ade);

            }
            ret_val_dest.get(0).setSquare(179);

            ret_val_dest.get(0).setOoper(ret_val_dest.get(0).getOoper()+1);

            /*
            ret_val_dest.get(0).setSquare(179);

            ret_val_dest.get(0).setRoomscount(550);

            ret_val_dest.get(0).setOoper(ret_val_dest.get(0).getOoper()+1);
            */


            }

        osi.bindObject(ret_val_dest.get(0));

    }

}