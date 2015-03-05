package nla.local;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import nla.local.aspect.ServiceAspect;
import nla.local.dao.exceptions.DaoException;
import nla.local.services.impl.subjects.PSubjectServiceImp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class OtherTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public OtherTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( OtherTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws DaoException {

        ApplicationContext appContext = new ClassPathXmlApplicationContext("beans-services.xml");

        PSubjectServiceImp pService = (PSubjectServiceImp) appContext.getBean("PSubjectServiceImp");

        pService.add(null);

        ServiceAspect aspect = (ServiceAspect) appContext.getBean("serviceAspect");


        assertTrue( true );
    }
}
