package nla.local;

import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.JPerson;
import nla.local.pojos.PPerson;
import nla.local.pojos.Person;
import nla.local.services.ISubjectService;
import nla.local.util.CodeGenerator;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-services.xml","classpath:beans-dao.xml"})
@TransactionConfiguration(defaultRollback = false)
public class SubjectTest
{

    @Autowired
    public ISubjectService<Person> pService;

    @Autowired
    public ISubjectService<Person> jService;

    @Autowired
    public CodeGenerator scg;

    public void SubjectJurTestController() throws DaoException {
        AddJurSubject();
        GetJurSubject();
    }

    @org.junit.Test
    @Transactional
    public void SubjectPhyTestController() throws DaoException {
        AddPhysSubject();
        //GetPhySubject();
    }


    public void AddJurSubject() throws DaoException {

        boolean retval = true;

        JPerson jp = new JPerson();

        jp.fullname = "ОАО Валенки";
        jp.subjectType = 210;
        jp.actual = 1;
        jp.regNumber = String.valueOf(123456000);
        jp.unp = String.valueOf(159357258);
        jp.bothRegDate = new Date();
        jService.addSubject(jp);

        try {

            for(int i=0; i<=1000; i++) {

                jp.fullname += "_"+i;
                jp.regNumber = String.valueOf(123456000+i);
                jp.unp = String.valueOf(159357258+i);
                jService.addSubject(jp);

            }

        } catch (DaoException e) {

            e.printStackTrace();

            retval = false;
        }

        assertTrue(retval);

    }


    public void GetPhySubject() {

        boolean retval = true;

        List<Person> result_p= pService.findByFIOType("Б", "С", null, null,110);

        assertTrue(!result_p.isEmpty());

    }


    public void GetJurSubject()  {

        boolean retval = true;

        List<Person> result_j= jService.findByNameType("Петр", "", 210);

        assertTrue(!result_j.isEmpty());


    }


    public void AddPhysSubject() throws DaoException {

        boolean retval = true;


        PPerson pp = new PPerson();
        pp.firstname = "Иван";
        pp.surname = "Иванов";
        pp.fathername = "Иванович";
        pp.subjectType = 100;
        pp.personalNumber = "7159357DB4";
        pp.bothRegDate = new Date();
        pp.subjectdataid = 10;//scg.generate("SEQ_SUBJECTSDATA_ID");
        pService.addSubject(pp);

       /*
        try {

            for(int i=0; i<=1000; i++) {
                PPerson pop = new PPerson();

                pop.surname += "_"+i;
                pop.firstname += "_"+i;
                pop.personalNumber = pp.personalNumber +String.valueOf(1000+i);
                pService.addSubject(pp);

            }

        } catch (DaoException e) {

            e.printStackTrace();

            retval = false;
        }
            */
        assertTrue(retval);

    }


}
