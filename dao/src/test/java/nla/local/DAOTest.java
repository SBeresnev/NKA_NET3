package nla.local;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-dao.xml"})
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class DAOTest
{

    public DAOTest(  )
    {

    }


    @Test
    public void testApp()
    {
        assertTrue( true );
    }
}
