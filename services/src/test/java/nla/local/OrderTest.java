package nla.local;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.orders.Decl;
import nla.local.pojos.orders.DeclUser;
import nla.local.pojos.subjects.OPerson;
import nla.local.pojos.subjects.PPerson;
import nla.local.pojos.subjects.Person;
import nla.local.services.impl.OrderServiceImp;
import nla.local.services.impl.subjects.OSubjectServiceImp;
import nla.local.services.impl.subjects.PSubjectServiceImp;
import org.hibernate.criterion.DetachedCriteria;
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
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by beresnev on 09.03.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-services.xml","classpath:beans-dao.xml"})
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class OrderTest {

    @Autowired
    OrderServiceImp osi;

    @Autowired
    OSubjectServiceImp ossi;

    @Qualifier("PSubjectServiceImp")
    @Autowired
    PSubjectServiceImp pssi;

    @Before
    public  void setOrder()
    { }

    @Test
    public void AddOrder() throws ServiceDaoException, ServiceException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();


        Decl dcl = new Decl();

        List<OPerson> op = ossi.getAll();

        List<PPerson> pp = pssi.getAll();

        /*******************************************************************************/
        Set<DeclUser> sdu = new HashSet<DeclUser>();

        DeclUser du_one = new DeclUser();

        cal.add(Calendar.DATE, -1);

        du_one.date_in = cal.getTime() ;

        du_one.oPerson = op.get(0);

        DeclUser du_two = new DeclUser();

        cal.add(Calendar.DATE, -2);

        du_two.date_in = cal.getTime();

        du_two.oPerson = op.get(1);

        sdu.add(du_one);

        sdu.add(du_two);

        /*******************************************************************************/


        DetachedCriteria cd =DetachedCriteria.forClass(PSubjectServiceImp.class);

        dcl.setDeclarants(new HashSet<Person>(pp.subList(0,1)));

        dcl.setoUsers(sdu);

        cal.add(Calendar.DATE, -5);

        dcl.setDecldate(cal.getTime());

        osi.add(dcl);
    }
}
