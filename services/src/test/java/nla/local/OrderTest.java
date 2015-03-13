package nla.local;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.dict.Dict;
import nla.local.pojos.dict.EnumDict;
import nla.local.pojos.orders.Decl;
import nla.local.pojos.orders.DeclResolution;
import nla.local.pojos.orders.DeclUser;
import nla.local.pojos.orders.Journal;
import nla.local.pojos.subjects.OPerson;
import nla.local.pojos.subjects.PPerson;
import nla.local.pojos.subjects.Person;
import nla.local.services.impl.DictionaryServiceImp;
import nla.local.services.impl.OrderServiceImp;
import nla.local.services.impl.subjects.OSubjectServiceImp;
import nla.local.services.impl.subjects.PSubjectServiceImp;
import nla.local.util.CodeGenerator;
import org.junit.Before;
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
 * Created by beresnev on 09.03.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-services.xml","classpath:beans-dao.xml"})
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class OrderTest {

    @Autowired
    public OrderServiceImp osi;

    @Autowired
    public OSubjectServiceImp oService;

    @Qualifier("PSubjectServiceImp")
    @Autowired
    public PSubjectServiceImp pService;

    @Autowired
    public CodeGenerator scg;

    @Autowired
    public DictionaryServiceImp CommonDict;

    private List<Dict> resolutionType;

    private static String cleanDecl = "delete from DECL";
    private static String cleanDeclarants = "delete from DECLARANTS";
    private static String cleanDecluser = "delete from DECLUSER";
    private static String cleanDeclresolution = "delete from DECLRESOLUTIONS";


    Integer GLOBAL_INDEX = 6;


    @Before
    public void setOrder() throws ServiceDaoException {

       /* scg.update(cleanDecluser);
        scg.update(cleanDeclresolution);
        scg.update(cleanDeclarants);
        scg.update(cleanDecl);*/

        resolutionType = CommonDict.getDict(EnumDict.ResolutionType);
    }
    @Test
    public void OrederTestController() throws DaoException, ServiceException {

      //  AddOrder();

      //  UpdateOrder();

      //  GetOrder();

        GetJournal();

    }

    public void UpdateOrder() throws DaoException {

        //ld.get(0).getDclresolution().iterator().next().setResolutionType(resolutionType.get(1));
        //DeclResolution dr = ld.get(0).getDclresolution().iterator().next();

        List<Decl> ld = osi.getAll(Decl.class);

        Random r = new Random();

        for (int i = 0; i < GLOBAL_INDEX/2 ; i++) {

            int iResol = r.nextInt(resolutionType.size()-1);

            int iDecl = r.nextInt(ld.size()-1);

            ld.get(iDecl).getDclresolution().iterator().next().setResolutionType(resolutionType.get(iResol));

            osi.update(ld.get(iDecl));
        }

    }

    public void AddOrder() throws ServiceDaoException, ServiceException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        List<OPerson> op = oService.getAll();
        List<PPerson> pp = pService.getAll();
        Random r = new Random();

/*******************************************************************************/

        for (int i = 0; i < GLOBAL_INDEX ; i++) {
            Set<DeclUser> sdu = new HashSet<DeclUser>();
            Decl dcl = new Decl();
            int Rbo = r.nextInt(op.size()-1);
            int Reo = r.nextInt(op.size()-1-Rbo) + Rbo;

            for (int j = Rbo; j <= Reo ; j++)
            {
                DeclUser du_one = new DeclUser();
                cal.add(Calendar.DATE, -Rbo);
                du_one.setDate_in(cal.getTime());
                du_one.setoPerson(op.get(j));
                sdu.add(du_one);
            }

            int Rbp = r.nextInt(pp.size()-2);
            dcl.setDeclarants(new HashSet<Person>(pp.subList(Rbp,Rbp+1)));
            dcl.setoUsers(sdu);
            cal.add(Calendar.DATE, -Rbo);
            dcl.setDecldate(cal.getTime());

/**************************DeclResolution***************************************/

            final DeclResolution dr = new DeclResolution();
            dr.setResolutionType(resolutionType.get(0));
            dr.setResolutionDate(cal.getTime());
            dr.setoPerson(null);

/********************************************************************************/

            dcl.setDclresolution(new HashSet<DeclResolution>() {{add(dr);}});
            dcl.setDecltype(1);
            dcl.setUrgency(0);
            osi.add(dcl);
            System.out.print("Sd");
        }

    }

    public void GetOrder() throws ServiceException {

        List<Decl> ld = osi.getAllOrder();

    }

    public void GetJournal() throws ServiceException, ServiceDaoException {

        List<OPerson> result_o = oService.findOffUser("Труба4", "Дударык4", "Флейтович4", null, "Брест",null);

        org.junit.Assert.assertNotNull(result_o);

        DeclUser du = new DeclUser();

        du.setoPerson(result_o.get(0));

        Map<Journal,Decl> mp = osi.getJournal(result_o.get(0));
    }
}