package nla.local;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.exception.ServiceExceptionLogAspect;
import nla.local.services.impl.subjects.PSubjectServiceImp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 * AspectTest
 */
public class AspectExTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AspectExTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AspectExTest.class );
    }

    /**
     * Rigourous Test :-)
     */

    public void testApp()  {

        ApplicationContext appContext = new ClassPathXmlApplicationContext("beans-services.xml");

        PSubjectServiceImp pService = (PSubjectServiceImp) appContext.getBean("PSubjectServiceImp");

        ServiceExceptionLogAspect aspect = (ServiceExceptionLogAspect) appContext.getBean("serviceExceptionAspect");

        try {

                pService.add(null);

            } catch (ServiceDaoException e) {
                e.printStackTrace();
            }
             catch (IllegalArgumentException e){
                 e.printStackTrace();
             }


        assertTrue( true );
    }


}
