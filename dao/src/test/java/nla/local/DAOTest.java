package nla.local;

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
class DAOTest
{

    public DAOTest(  )
    {

    }


    public void testApp()
    {
       // ApplicationContext context = new ClassPathXmlApplicationContext("beans-services.xml");
       // Person person = (Person)context.getBean("person");
        //System.out.println(person.getName());
        assertTrue( true );
    }
}
