package nla.local;

import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.address.Address_dest;
import nla.local.pojos.address.Address_src;
import nla.local.pojos.dict.*;
import nla.local.pojos.object.Object_dest;
import nla.local.pojos.object.Object_src;
import nla.local.pojos.operations.EntityType;
import nla.local.pojos.operations.Operation;
import nla.local.pojos.orders.Decl;
import nla.local.pojos.subjects.OPerson;
import nla.local.pojos.subjects.Person;
import nla.local.services.*;
import nla.local.services.impl.BaseServiceImp;
import nla.local.services.impl.CatalogDependencyServiceImp;
import nla.local.services.impl.CatalogServiceImp;
import nla.local.services.impl.subjects.OSubjectServiceImp;
import nla.local.util.BaseClean;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;

import nla.local.exception.ServiceDaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    public IDeclService ids;

    @Autowired
    public BaseServiceImp baseServiceImp;

    @Autowired
    public IOperationsService operSrv;

    @Autowired
    private IObjectService osi;

    @Autowired
    private IAddressService asi;

    @Autowired
    private ICatalogService catalogService;

    @Autowired
    private ICatalogDependencyService catalogServiceDep;

    @Qualifier("OSubjectServiceImp")
    @Autowired
    private ISubjectService<OPerson> oPers;

    @Autowired
    private OSubjectServiceImp oServices;

    @Test
    public void ObjectTestController() throws DaoException, ServiceException {

        //baseClean.ObjectClean();

        long startTime = System.nanoTime();

        bindObjectbyAddressCommon();

        bindObjectbyInventoryNumCommon();

        updateObject();

        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;

        System.out.println("That took " + duration + " milliseconds");

    }


    public void updateObject() throws ServiceDaoException {

       List<Object_dest> lod =  osi.findbyobjDestid((long) 96);

        if (lod.size() > 0) {

            Object_dest ode = lod.get(0);

            Operation opr = ode.getOoper();


            CatalogItem ci = catalogService.getCatalogItem(61, 3);

            opr.setOperType(ci);


            ci = catalogService.getCatalogItem(62, 4);

            opr.setOperSubtype(ci);


            ci = catalogService.getCatalogItem(63, 1170);

            opr.setReason(ci);


            ode.setOoper(opr);

            osi.update(ode);

        }

    }

    // Поиск новых адресов через adr_num
    public void bindObjectbyAddressCommon() throws ServiceDaoException, ServiceException {

        Address_dest adr_dest = asi.getdestbyIDs(null, Long.valueOf(878782));

        if (adr_dest == null) {

            adr_dest =  asi.convertSrctoDest(asi.getsrcbyID(null, Long.valueOf(878782), 3).get(0));

        }

        List<Object_dest> ret_val_dest =  osi.findObjectbyAddressCommon( Arrays.asList(adr_dest.getAdr_num()));

        Operation oper = new Operation();

        oper = getOperField(61, 1, 62, 1, 63, 1010);

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

                dst_src.setOoper(oper);

                ret_val_dest.set(i,dst_src);

                i++;

            }

        if(ret_val_dest.size() > 0) { osi.bindObject(ret_val_dest.get(0)); }

    }

    public Operation getOperField(int operType, int operCode, int operSubtype, int operSubcode, int resonType, int ReasonCode ) throws ServiceDaoException {


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


        cp.setAnalytic_type(resonType);

        cp.setCode_id(ReasonCode);

        ci = catalogService.getCatalogItem(cp);

        oper.setReason(ci);

        baseServiceImp.getBaseDao().getSession().evict(ci);



        oper.setRegDate(new Date ());

        List<OPerson> ops = oServices.findOffUser("", "", "", null, "", null);

        Person prs = ops.get(0);


        oper.setExecutor(prs);

        oper.setStatus(1);


        return oper ;
    }

    public void bindObjectbyInventoryNumCommon() throws ServiceDaoException, ServiceException {

        Operation oper = new Operation();

        oper = getOperField(61, 1, 62, 1, 63, 1010);


       // List<Object_dest> od = osi.findbyobjDestid((long) 174);

        List<Object_dest> ret_val_dest = (List<Object_dest>) osi.findObjectbyInventoryNumCommon(3, 3, 0);


        if (ret_val_dest.size() >0 ) {

            ret_val_dest.get(0).setReadiness(50);

            ret_val_dest.get(0).setConserv(0);

            ret_val_dest.get(0).setReg_type(1);

            ret_val_dest.get(0).setStatus(1);

            ret_val_dest.get(0).setOoper(oper);


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