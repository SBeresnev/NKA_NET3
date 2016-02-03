package nla.local;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.bargain.Bargain;
import nla.local.pojos.bargain.BargainContent;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.dict.CatalogPk;

import nla.local.pojos.object.Object_dest;
import nla.local.pojos.operations.EntityType;
import nla.local.pojos.operations.Operation;
import nla.local.pojos.orders.Decl;
import nla.local.pojos.rights.RightOwner;
import nla.local.pojos.subjects.OPerson;
import nla.local.pojos.subjects.Person;
import nla.local.services.IBargainService;
import nla.local.services.IDeclService;
import nla.local.services.IObjectService;
import nla.local.services.IRightService;
import nla.local.services.impl.BaseServiceImp;
import nla.local.services.impl.CatalogServiceImp;
import nla.local.services.impl.subjects.OSubjectServiceImp;
import nla.local.util.BaseClean;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
    private OSubjectServiceImp oServices;

    @Autowired
    public BaseClean baseClean;

    @Autowired
    public BaseServiceImp baseServiceImp;

    @Autowired
    public IRightService rsi;

    @Autowired
    public IObjectService osi;

    @Autowired
    public IBargainService bsi;

    @Autowired
    public IDeclService ids;


    @Autowired
    private CatalogServiceImp catalogService;

    private Integer GLOBAL_COUNT_INTDEX = 1;

    private List<CatalogItem> bargainEntytyType;
    private List<CatalogItem> bargainType;


    @Test
    public void BargainTestController() throws ServiceDaoException, ServiceException {

       // baseClean.BargainClean();

        bargainEntytyType = catalogService.getCatalogItemsByTyp(35);

        bargainType = catalogService.getCatalogItemsByTyp(4);

        long startTime = System.nanoTime();

        // addBargain();

        updBargain();

        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1000000;

        System.out.println("That took " + duration + " milliseconds");

    }

    public void updBargain() throws ServiceDaoException, ServiceException {

        List<Object_dest> obj_list = osi.getAll(Object_dest.class);

        List<Long> obj_id_list = new ArrayList<Long>();

        for (Object_dest obj :obj_list) {

            obj_id_list.add(obj.getObj_id());

        }

        Long[] obj_ids = obj_id_list.toArray(new Long[obj_id_list.size()] ) ;

        List<RightOwner> lor = null;//rsi.getRighOwnbyObjectPerson(obj_ids, null);

        long obj_id = lor.get(0).getRight().getBindedObj().getObj_id();

        Long[] lobj = new Long[]{obj_id};

        List<BargainContent> bar_cont_list = bsi.getBargainbyObjectPerson(lobj, null);

       // Operation opr = getOperField(61, 1, 62, 3, 63, 3100);

        BargainContent bar_cont = new BargainContent();

        if (bar_cont_list.size() > 0) {

            bar_cont = bar_cont_list.get(0);

            bar_cont.setRight_entity_id(Long.valueOf(600));

            Bargain bar = bar_cont.getBargain();

            bar.setVat_complex(80);

            bar.getOoper().setOperSubtype(catalogService.getCatalogItem(62,3).getCode_id());


            bsi.updateBargain(bar_cont);

        }


    }

    public void addBargain() throws ServiceDaoException, ServiceException {

        Operation opr = getOperField(61, 1, 62, 1, 63, 3100);

        BargainContent brg_cont = new BargainContent();

        ///*************************************Bargain initialization******************************************************************///
        Bargain brg = new Bargain();

        CatalogItem rightcounttype = CollectionUtils.find(bargainType, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("залог");
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
        brg.setOoper(opr);

        brg.setCount_object(0);


        ///**************************************Bargain Content init*****************************************************************///


        List<CatalogItem> rightEntytyTypeList = catalogService.getCatalogItemsByTyp(21);

        CatalogItem  directType = CollectionUtils.find(rightEntytyTypeList, new Predicate() {
            public boolean evaluate(Object o) {
                CatalogItem c = (CatalogItem) o;
                return c.getCode_name().toLowerCase().contains("долевое право");
            }
        });


        Set<RightOwner> r_lrt = new HashSet<RightOwner>(rsi.findbyrightCountType(directType.getCode_id()));

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

        brg_cont.setOoper(opr);

        brg_cont.setBargain(brg);

        bsi.addBargain(brg_cont);

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

        oper.setOperType(ci.getCode_id());

        baseServiceImp.getBaseDao().getSession().evict(ci);


        cp.setAnalytic_type(operSubtype);

        cp.setCode_id(operSubcode);

        ci = catalogService.getCatalogItem(cp);

        oper.setOperSubtype(ci.getCode_id());

        baseServiceImp.getBaseDao().getSession().evict(ci);


        // cp.setAnalytic_type(resonType);

        // cp.setCode_id(ReasonCode);

        // ci = catalogService.getCatalogItem(cp);

        ci = catalogService.getCatalogItem(63,3100);

        oper.setReason(ci.getCode_id());

        baseServiceImp.getBaseDao().getSession().evict(ci);



        oper.setRegDate(new Date ());

        List<OPerson> ops = oServices.findOffUser("", "", "", null, "", null);

        Person prs = ops.get(0);

        oper.setExecutor(prs.getSubjectId());

        oper.setStatus(1);


        return oper ;
    }



}
