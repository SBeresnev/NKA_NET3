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
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class SubjectTest
{

    @Autowired
    public ISubjectService<Person> sService;

    @Autowired
    public CodeGenerator scg;

    @org.junit.Test
    public void SubjectJurTestController() throws DaoException {
        AddJurSubject();
        GetJurSubject();
    }

    @org.junit.Test
    public void SubjectPhyTestController() throws DaoException {
        AddPhysSubject();
        GetPhysSubject();
    }


    @org.junit.Test
    public void UpdateJurSubject() throws DaoException {
        boolean retval = true;

        JPerson jp = new JPerson();
        jp.fullname = "ОАО Update";
        jp.subjectType = 210;
        jp.actual = 1;
        jp.regNumber = String.valueOf(124566000);
        jp.unp = String.valueOf(159777758);
        jp.subjectdataid = (Integer)scg.generate("SEQ_SUBJECTSDATA_ID");
        jp.bothRegDate = new Date();

        sService.addSubject(jp);

        jp.fullname += String.valueOf("12");
        jp.regNumber = String.valueOf("1245660100");
        sService.refreshSubject(jp);


    }

    public void AddJurSubject() throws DaoException {

        boolean retval = true;

       try {

            for(int i=0; i<=10; i++) {

                JPerson jp = new JPerson();
                jp.fullname = "ОАО Валенки"+"_"+i;
                jp.subjectType = 210;
                jp.actual = 1;
                jp.regNumber = String.valueOf(123456000+i);
                jp.unp = String.valueOf(159357258+i);
                jp.subjectdataid = (Integer)scg.generate("SEQ_SUBJECTSDATA_ID");
                jp.bothRegDate = new Date();
                sService.addSubject(jp);

            }

        } catch (DaoException e) {

            e.printStackTrace();

            retval = false;
        }

        assertTrue(retval);

    }

    public void AddPhysSubject() throws DaoException {

        boolean retval = true;

        try {

            for(int i=0; i<=10; i++) {

                PPerson pp = new PPerson();

                pp.surname = "Иван"+"_"+i;
                pp.firstname = "Иванов"+"_"+i;
                pp.fathername = "Иванович"+"_"+i;
                pp.subjectType = 100;
                pp.subjectdataid = (Integer)scg.generate("SEQ_SUBJECTSDATA_ID");
                pp.bothRegDate = new Date();
                pp.actual = 1;
                pp.personalNumber = "7159357DB4" +String.valueOf(1000+i);
                sService.addSubject(pp);

            }

        } catch (DaoException e) {

            e.printStackTrace();

            retval = false;
        }

        assertTrue(retval);

    }

    public void GetPhysSubject() {

        boolean retval = true;

        List<Person> result_p= sService.findByFIOType("Ив", "И", null, null,100);

        assertTrue(!result_p.isEmpty());

    }

    public void GetJurSubject()  {

        boolean retval = true;

        List<Person> result_j= sService.findByNameType("Вал", "", 210);

        assertTrue(!result_j.isEmpty());


    }




}
