package nla.local;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import nla.local.pojos.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTestU extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTestU( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTestU.class );
    }

    /**
     * Rigourous Test :-)
     */

    public void testApp()
    {
       // ApplicationContext context = new ClassPathXmlApplicationContext("beans-services.xml");
       // Person person = (Person)context.getBean("person");
        //System.out.println(person.getName());
        assertTrue( true );
    }
}
