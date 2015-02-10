package nla.local;

import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.JPerson;
import nla.local.pojos.OPerson;
import nla.local.pojos.PPerson;
import nla.local.pojos.Person;
import nla.local.pojos.dict.OrgStructDict;
import nla.local.pojos.dict.StateDict;
import nla.local.pojos.dict.SubjectTypeDict;
import nla.local.pojos.dict.TorStructDict;
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
    public DictionaryServiceImp CommonDict;

    @Autowired
    public CodeGenerator scg;

    private List<SubjectTypeDict> subjectServDictList;
    private List<OrgStructDict> orgStructDictList;
    private List<StateDict> stateDictList;
    private List<TorStructDict> torStructDictList;

    @Before
    public void setUp() throws Exception {

        subjectServDictList = CommonDict.getAll(SubjectTypeDict.class);

        orgStructDictList = CommonDict.getAll(OrgStructDict.class);

        stateDictList = CommonDict.getAll(StateDict.class);

        torStructDictList = CommonDict.getAll(TorStructDict.class);


    }

    @org.junit.Test
    public void SubjectOffTestController()  {

        AddOffSubject();
        GetOffSubject();
        UpdateOffSubject();
    }

    //@org.junit.Test
    public void SubjectJurTestController() {

        AddJurSubject();
        GetJurSubject();
        UpdateJurSubject();
    }

   // @org.junit.Test
    public void SubjectPhyTestController(){

        AddPhysSubject();
        GetPhysSubject();
        UpdatePhysSubject();

    }

    public void AddOffSubject() {
        log.info("Invoked SubjectTest.AddOffSubject()" );

        boolean retval = true;

        try {

            for (int i = 0; i < 10; i++) {

                OPerson op = new OPerson();
                op.subjectType = subjectServDictList.get(24);
                op.isOwner = 0;

                op.firstname = "Дударык" + String.valueOf(i);
                op.surname = "Труба" + String.valueOf(i);
                op.fathername = "Флейтович" + String.valueOf(i);

                op.org_kod = torStructDictList.get(0);
                op.orgname = "РУП \"Брестское агентство по государственной регистрации и земельному кадастру\"";
                sService.addSubject(op);

            }
        }
            catch (DaoException e) {
                retval = false;
                e.printStackTrace();
            }

        assertTrue(retval);

    }

    public void GetOffSubject() {
        log.info("Invoked SubjectTest.GetOffSubject" );

        List<Person> result_o= sService.findOffUser("Тру", "", "", null, "Брест");

        assertTrue(!result_o.isEmpty());
    }

    public void UpdateOffSubject()  {

        log.info("Invoked SubjectTest.UpdateJurSubject" );

        boolean retval = true;

        try {

            for(int i=0; i<10; i++) {

                OPerson op = new OPerson();
                op.subjectType = subjectServDictList.get(24);
                op.isOwner = 0;

                op.firstname = "Петров" + String.valueOf(20+i);
                op.surname = "Иван" + String.valueOf(20+i);
                op.fathername = "Артемьевич" + String.valueOf(20+i);

                op.org_kod = torStructDictList.get(0);
                op.orgname = "РУП \"Брестское агентство по государственной регистрации и земельному кадастру\"";
                sService.addSubject(op);

            }

            List<Person> result_o= sService.findOffUser(null, "", "Артем", null, "БРЕСТ");


            int i = 0;

            for(Person o : result_o)
            {
                OPerson op = (OPerson) o;
                op.firstname  = "Петров" + String.valueOf(30+i) ;
                op.surname = "Иван" + String.valueOf(30+i);
                op.fathername = "Артемьевич" + String.valueOf(30+i);

                sService.refreshSubject(op);
                i++;
            }

        } catch (DaoException e) {

            retval = false;

            e.printStackTrace();
        }

        assertTrue(retval);
    }


    public void AddJurSubject(){

        log.info("Invoked SubjectTest.AddJurSubject" );

        boolean retval = true;

        try {

            for(int i=0; i<10; i++) {

                JPerson jp = new JPerson();
                jp.subjectdataid = (Integer)scg.generate("SEQ_SUBJECTSDATA_ID");
                jp.isOwner = 1;

                jp.subjectType = subjectServDictList.get(9);
                jp.orgRightForm = orgStructDictList.get(6);

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

        log.info("Invoked SubjectTest.GetJurSubject" );

        boolean retval = true;

        List<Person> result_j= sService.findJurByNameType("Вал", "", subjectServDictList.get(9).getCode_id());

        assertTrue(!result_j.isEmpty());


    }

    public void UpdateJurSubject()  {

        log.info("Invoked SubjectTest.UpdateJurSubject" );

        boolean retval = true;

        try {

        for(int i=0; i<10; i++) {
            JPerson jp = new JPerson();
            jp.subjectdataid = (Integer)scg.generate("SEQ_SUBJECTSDATA_ID");
            jp.fullname = "ОАО Update_" + String.valueOf(i) ;
            jp.orgRightForm = orgStructDictList.get(6);
            jp.subjectType = subjectServDictList.get(14);
            jp.regNumber = String.valueOf(124566000+i) ;
            jp.unp = String.valueOf(159777758+i);
            jp.bothRegDate = new Date();
            sService.addSubject(jp);
        }


        List<Person> result_p= sService.findJurByNameType("Upd",null,subjectServDictList.get(9).getCode_id());

        int i = 10;

        for(Person p : result_p)
        {
            JPerson jp = (JPerson) p;
            jp.fullname  = "ОАО Update_" + String.valueOf(i) ;
            jp.unp =  String.valueOf(159777758+i);
            jp.regNumber =  String.valueOf(124566000+i);

            jp.orgRightForm = orgStructDictList.get(7);
            jp.subjectType = subjectServDictList.get(12);

            sService.refreshSubject(p);
            i++;
        }

        } catch (DaoException e) {

            retval = false;

            e.printStackTrace();
        }

        assertTrue(retval);
    }


    public void AddPhysSubject() {

        log.info("Invoked SubjectTest.AddPhysSubject()" );

        boolean retval = true;

        try {

            for(int i=0; i<10; i++) {

                PPerson pp = new PPerson();
                pp.subjectdataid = (Integer)scg.generate("SEQ_SUBJECTSDATA_ID");
                pp.surname = "Иван"+"_"+i;
                pp.firstname = "Иванов"+"_"+i;
                pp.fathername = "Иванович"+"_"+i;
                pp.subjectType = subjectServDictList.get(2);
                pp.isOwner = 1;
                pp.bothRegDate = new Date();

                pp.personalNumber = "78"+String.valueOf(71000+i)+"F408AE" ;
                pp.personalNumber += String.valueOf(CheckLastNumber(pp.personalNumber));

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

        List<Person> result_p = sService.findPhyzByFIOType("Ив", "И", null, null, subjectServDictList.get(2).getCode_id());

        assertTrue(!result_p.isEmpty());

    }

    public void UpdatePhysSubject() {

        log.info("Invoked SubjectTest.UpdatePhysSubject" );

        boolean retval = true;

        try {

        for(int i=0; i<10; i++) {

            PPerson pp = new PPerson();
            pp.subjectdataid = (Integer)scg.generate("SEQ_SUBJECTSDATA_ID");

            pp.surname = "Дженкинс"+"_"+i;
            pp.firstname = "Владимир"+"_"+i;
            pp.fathername = "Обамович"+"_"+i;
            pp.subjectType = subjectServDictList.get(3);
            pp.isOwner = 1;
            pp.sitizens = stateDictList.get(2);
            pp.bothRegDate = new Date();
            pp.datestart = new Date();

            pp.personalNumber = "78"+String.valueOf(31158+i)+"F408AE" ;
            pp.personalNumber += String.valueOf(CheckLastNumber(pp.personalNumber));

            sService.addSubject(pp);

            //7[0-9]{6}[A-Z]{1}[0-9]{3}[A-Z]{2}[0-9]{1}

        }

        List<Person> result_p= sService.findPhyzByFIOType("Дж", "", "Об", "1597", 110);

        int i = 0;

        for(Person p : result_p)
        {
            PPerson pp = (PPerson) p;

            pp.surname  += String.valueOf(i);

            pp.personalNumber = "78"+String.valueOf(31158+i)+"F408AE" ;
            pp.personalNumber += String.valueOf(CheckLastNumber(((PPerson) p).personalNumber));

            pp.subjectType = subjectServDictList.get(2);
            pp.sitizens = stateDictList.get(3);

            sService.refreshSubject(p);
            i++;
        }

        } catch (DaoException e) {
            e.printStackTrace();
        }


    }


    public int CheckLastNumber(String str) {
        int v_1 = Integer.valueOf(str.substring(1, 2));
        int v_2 = Integer.valueOf(str.substring(2, 3));
        int v_3 = Integer.valueOf(str.substring(3, 4));

        int v_4 = Integer.valueOf(str.substring(4, 5));
        int v_5 = Integer.valueOf(str.substring(5, 6));
        int v_6 = Integer.valueOf(str.substring(6, 7));
        int v_7 = Integer.valueOf(str.substring(7, 8));

        int v_8 = Integer.valueOf(str.substring(9, 10));
        int v_9 = Integer.valueOf(str.substring(10, 11));
        int v_10 = Integer.valueOf(str.substring(11, 12));

        int p_ret = ((v_1*7+ v_2*3+ v_3*1+ v_4*7+ v_5*3+ v_6*1+ v_7*7+ v_8*3+ v_9*1+v_10*7)% 10);

        return p_ret;
    }

}
