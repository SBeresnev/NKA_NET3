package nla.local;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import nla.local.pojos.JPerson;
import nla.local.pojos.PPerson;
import nla.local.pojos.Person;
import nla.local.services.BaseService;
import nla.local.services.IService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {

        ApplicationContext context = new ClassPathXmlApplicationContext("beans-services.xml");


        IService<PPerson> pIService = (BaseService<PPerson>)context.getBean(BaseService.class);

        DetachedCriteria query = DetachedCriteria.forClass(PPerson.class).add(Restrictions.eq("subjectType", 2));

        List<PPerson> pperson = pIService.getCriterion(query);

        IService<JPerson> jIService = (BaseService<JPerson>)context.getBean(BaseService.class);

         List<JPerson> jperson = jIService.getCriterion(DetachedCriteria.forClass(JPerson.class).add(Restrictions.eq("subjectType", 1)));

        System.out.println(pperson.size());

        for (JPerson j : jperson) {
            System.out.println("Subject type === " + j.shortname);
        }

        assertTrue(true);
    }
}
