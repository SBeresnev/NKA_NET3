package nla.local;

import nla.local.pojos.orders.Decl;
import nla.local.services.impl.OrderServiceImp;
import nla.local.services.impl.subjects.OSubjectServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by beresnev on 09.03.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-services.xml","classpath:beans-dao.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class OrderTest {

    @Autowired
    OrderServiceImp osi;

    @Autowired
    OSubjectServiceImp ossi;

    @Before
    public  void setOrder()
    {}

    @Test
    public void AddOrder()
    {
        Decl dcl = new Decl();

        ossi.
        OPerson oPerson

        dcl.setDeclarants();
        dcl.setoUsers();

        osi.add(dcl);
    }
}
