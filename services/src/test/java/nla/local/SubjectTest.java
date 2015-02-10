package nla.local;

import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.JPerson;
import nla.local.pojos.PPerson;
import nla.local.pojos.Person;
import nla.local.pojos.dict.OrgKod;
import nla.local.pojos.dict.SubjectType;
import nla.local.services.ISubjectService;
import nla.local.services.impl.DictionaryServiceImp;
import nla.local.util.CodeGenerator;
import org.apache.log4j.Logger;
import org.junit.Before;
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

    private static Logger log = Logger.getLogger(SubjectTest.class);

    @Autowired
    public ISubjectService<Person> sService;

    @Autowired
    public DictionaryServiceImp<OrgKod> OrgServ;

    @Autowired
    public DictionaryServiceImp<SubjectType> SubType;

    @Autowired
    public CodeGenerator scg;

    private List<SubjectType> subjectServ;
    private List<OrgKod> orgKods ;

    @Before
    public void setUp() throws Exception {

        subjectServ = SubType.getAll(SubjectType.class);

        orgKods = OrgServ.getAll(OrgKod.class);

    }

    //@org.junit.Test
    public void SubjectJurTestController() throws DaoException {

        AddJurSubject();
        GetJurSubject();
        UpdateJurSubject();
    }

   @org.junit.Test
    public void SubjectPhyTestController() throws DaoException {

        AddPhysSubject();
        GetPhysSubject();
        //UpdatePhysSubject();

    }


    public void AddJurSubject() throws DaoException {

        log.info("Invoked SubjectTest.AddJurSubject()" );

        boolean retval = true;

       try {

            for(int i=0; i<10; i++) {

                JPerson jp = new JPerson();
                jp.subjectdataid = (Integer)scg.generate("SEQ_SUBJECTSDATA_ID");
                jp.isOwner = 1;

                jp.subjectType = subjectServ.get(9);
                jp.orgRightForm = orgKods.get(6);

                jp.fullname = "Валенки"+"_"+i;
                jp.regNumber = String.valueOf(123456000+i);
                jp.unp = String.valueOf(159357258+i);
                jp.bothRegDate = new Date();
                sService.addSubject(jp);

            }

        } catch (DaoException e) {

            e.printStackTrace();

            retval = false;
        }

        assertTrue(retval);

    }

    public void GetJurSubject()  {

        log.info("Invoked SubjectTest.GetJurSubject()" );

        boolean retval = true;

        List<Person> result_j= sService.findByNameType("Вал", "", subjectServ.get(9).getCode_id());

        assertTrue(!result_j.isEmpty());


    }

    public void UpdateJurSubject() throws DaoException {

        log.info("Invoked SubjectTest.UpdateJurSubject()" );

        boolean retval = true;

        for(int i=0; i<10; i++) {
            JPerson jp = new JPerson();
            jp.subjectdataid = (Integer)scg.generate("SEQ_SUBJECTSDATA_ID");
            jp.fullname = "ОАО Update_" + String.valueOf(i) ;
            jp.orgRightForm = orgKods.get(6);
            jp.subjectType = subjectServ.get(14);
            jp.regNumber = String.valueOf(124566000+i) ;
            jp.unp = String.valueOf(159777758+i);
            jp.bothRegDate = new Date();
            sService.addSubject(jp);
        }


        List<Person> result_p= sService.findByNameType("Upd",null,subjectServ.get(9).getCode_id());

        int i = 10;

        for(Person p : result_p)
        {
            ((JPerson) p).fullname  = "ОАО Update_" + String.valueOf(i) ;
            ((JPerson) p).unp =  String.valueOf(159777758+i);
            ((JPerson) p).regNumber =  String.valueOf(124566000+i);

            ((JPerson) p).orgRightForm = orgKods.get(7);
            ((JPerson) p).subjectType = subjectServ.get(12);

            sService.refreshSubject(p);
            i++;
        }


    }



    public void AddPhysSubject() throws DaoException {

       log.info("Invoked SubjectTest.AddPhysSubject()" );

        boolean retval = true;

        try {

            for(int i=0; i<10; i++) {

                PPerson pp = new PPerson();
                pp.subjectdataid = (Integer)scg.generate("SEQ_SUBJECTSDATA_ID");
                pp.surname = "Иван"+"_"+i;
                pp.firstname = "Иванов"+"_"+i;
                pp.fathername = "Иванович"+"_"+i;
                pp.subjectType = subjectServ.get(2);
                pp.isOwner = 1;
                pp.bothRegDate = new Date();
                pp.personalNumber = "78"+String.valueOf(71000+i)+"F408AE8";
                sService.addSubject(pp);

            }

        } catch (DaoException e) {

            e.printStackTrace();

            retval = false;
        }

        assertTrue(retval);

    }

    public void GetPhysSubject() {

        log.info("Invoked SubjectTest.GetPhysSubject()" );

        boolean retval = true;

        List<Person> result_p = sService.findByFIOType("Ив", "И", null, null,subjectServ.get(2).getCode_id());

        assertTrue(!result_p.isEmpty());

    }

    public void UpdatePhysSubject()throws DaoException {

        log.info("Invoked SubjectTest.UpdatePhysSubject()" );

        boolean retval = true;

        for(int i=0; i<10; i++) {

            PPerson pp = new PPerson();
            pp.subjectdataid = (Integer)scg.generate("SEQ_SUBJECTSDATA_ID");

            pp.surname = "Дженкинс"+"_"+i;
            pp.firstname = "Владимир"+"_"+i;
            pp.fathername = "Обамович"+"_"+i;
            pp.subjectType = subjectServ.get(3);
            pp.isOwner = 1;
            pp.bothRegDate = new Date();
            pp.datestart = new Date();
            pp.personalNumber = "78"+String.valueOf(31158+i)+"F408AE3";
            sService.addSubject(pp);
            //7[0-9]{6}[A-Z]{1}[0-9]{3}[A-Z]{2}[0-9]{1}
        }

        List<Person> result_p= sService.findByFIOType("Дж", "", "Об", "1597", 110);

        int i = 0;

        for(Person p : result_p)
        {
            ((PPerson) p).surname  += String.valueOf(i);
            ((PPerson) p).personalNumber = "78"+String.valueOf(6367+i)+"F408AE2";
            ((PPerson) p).subjectType = subjectServ.get(2);

            sService.refreshSubject(p);
            i++;
        }


    }



}
