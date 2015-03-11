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
    OrderServiceImp osi;

    @Autowired
    OSubjectServiceImp ossi;

    @Qualifier("PSubjectServiceImp")
    @Autowired
    PSubjectServiceImp pssi;

    Integer GLOBAL_INDEX = 12;

    @Before
    public  void setOrder()
    { }

    @Test
    public void AddOrder() throws ServiceDaoException, ServiceException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        Calendar cal = Calendar.getInstance();

        List<OPerson> op = ossi.getAll();

        List<PPerson> pp = pssi.getAll();

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

                    du_one.date_in = cal.getTime() ;

                    du_one.oPerson = op.get(j);

                    sdu.add(du_one);

                }

            int Rbp = r.nextInt(pp.size()-2);

            dcl.setDeclarants(new HashSet<Person>(pp.subList(Rbp,Rbp+1)));

            dcl.setoUsers(sdu);

            cal.add(Calendar.DATE, -Rbo);

            dcl.setDecldate(cal.getTime());

            osi.add(dcl);


        }


        /*******************************************************************************/


    }
}
