package nla.local;

import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.pojos.dict.Dict;
import nla.local.pojos.dict.DictPk;
import nla.local.pojos.dict.EnumDict;
import nla.local.pojos.subjects.JPerson;
import nla.local.pojos.subjects.OPerson;
import nla.local.pojos.subjects.PPerson;
import nla.local.pojos.subjects.Person;
import nla.local.services.impl.DictionaryServiceImp;
import nla.local.services.impl.subjects.JSubjectServiceImp;
import nla.local.services.impl.subjects.OSubjectServiceImp;
import nla.local.services.impl.subjects.PSubjectServiceImp;
import nla.local.util.CodeGenerator;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Transactional
public class SubjectTest
{

    private static Logger log = Logger.getLogger(SubjectTest.class);


    @Autowired
    public OSubjectServiceImp oService;

    @Autowired
    @Qualifier("PSubjectServiceImp")
    public PSubjectServiceImp pService;

    @Autowired
    public JSubjectServiceImp jService;

    @Autowired
    public DictionaryServiceImp CommonDict;

    @Autowired
    public CodeGenerator scg;

    private List<Dict> subjectServDictList;
    private List<Dict> orgStructDictList;
    private List<Dict> stateDictList;
    private List<Dict> torStructDictList;

    private List<Dict> allDictList;

    private Integer GLOBAL_INDEX = 500;

    private static String cleanSubject = "delete from SUBJECTS";
    private static String cleanSubjectData = "delete from SUBJECTSDATA";
    private static String cleanOfficialuser = "delete from OFFICIALUSERS";

    @Before
    public void setUp() throws Exception {

        /*small dictionary test */

        subjectServDictList = CommonDict.getDict(EnumDict.SubjectType);

        orgStructDictList = CommonDict.getDict(EnumDict.OrgStruct);

        stateDictList = CommonDict.getDict(EnumDict.State);

        torStructDictList = CommonDict.getDict(EnumDict.TorStruct);

        allDictList = CommonDict.getAll();

        DictPk pk = new DictPk(112,200); /*Республика Беларусь*/

        Dict pm = CommonDict.getDict(pk);

        if (pm == null || subjectServDictList.size() == 0 || orgStructDictList.size() == 0 || stateDictList.size() == 0 || torStructDictList.size() == 0 || allDictList.size() == 0 )
            assert (false);

    }


    @Test
    public void SubjectsTestController() {


        scg.update(cleanSubjectData);
        scg.update(cleanOfficialuser);
        scg.update(cleanSubject);

        AddJurSubject();
        GetJurSubject();
        UpdateJurSubject();


        AddOffSubject();
        GetOffSubject();
        UpdateOffSubject();

        AddPhysSubject();
        GetPhysSubject();
        UpdatePhysSubject();


    }


    public void AddOffSubject() {

        log.info("Invoked SubjectTest.AddOffSubject" );

        boolean retval = true;


        try {

            for (int i = 0; i < GLOBAL_INDEX; i++) {


                OPerson op = new OPerson();
                op.subjectType = subjectServDictList.get(24);
                op.isOwner = 0;

                op.firstname = "Дударык" + String.valueOf(i);
                op.surname = "Труба" + String.valueOf(i);
                op.fathername = "Флейтович" + String.valueOf(i);

                op.org_kod =  torStructDictList.get(0);
                op.orgname = "РУП \"Брестское агентство по государственной регистрации и земельному кадастру\"";
                oService.add(op);

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

        List<OPerson> result_o= null;
        try {

            result_o = oService.findOffUser("Тру", "", "", null, "Брест",null);

        } catch (ServiceDaoException e) {

            e.printStackTrace();
        }

        assertTrue(!result_o.isEmpty());
    }

    public void UpdateOffSubject()  {

        log.info("Invoked SubjectTest.UpdateJurSubject" );

        boolean retval = true;



        try {

            for(int i=0; i<GLOBAL_INDEX; i++) {


                OPerson op = new OPerson();
                op.subjectType = subjectServDictList.get(24);
                op.isOwner = 0;

                op.surname = "Иванов" + String.valueOf(20+i);
                op.firstname = "Петр" + String.valueOf(20+i);
                op.fathername = "Артемьевич" + String.valueOf(20+i);

                op.org_kod =  torStructDictList.get(0);
                op.orgname = "РУП \"Брестское агентство по государственной регистрации и земельному кадастру\"";
                oService.add(op);

            }

            List<OPerson> result_o= oService.findOffUser("Иванов22", null , "Артем", null, "БРЕСТ",null);


            int i = 0;

            for(Person o : result_o)
            {
                OPerson op = (OPerson) o;
                op.surname = "Иванов" + String.valueOf(40+i);
                op.firstname  = "Петр" + String.valueOf(40+i) ;
                op.fathername = "Артемьевич" + String.valueOf(40+i);

                oService.refreshSubject(op);
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

            for(int i=0; i<GLOBAL_INDEX; i++) {

                JPerson jp = new JPerson();
                jp.subjectdataid = Integer.valueOf(scg.generate("SEQ_SUBJECTSDATA_ID.nextval").toString());
                jp.isOwner = 1;

                jp.subjectType = subjectServDictList.get(9);
                jp.orgRightForm =  orgStructDictList.get(6);

                jp.fullname = "Валенки"+"_"+i;
                jp.regNumber = String.valueOf(123456000+i);
                jp.unp = String.valueOf(159357258+i);
                jp.bothRegDate = new Date();
                jService.add(jp);

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

        try {

            List<JPerson> result_j_0 = jService.getAll();

            List<JPerson> result_j_1 = jService.findByNameType("", "",null);

            List<JPerson> result_j_2 = jService.findByNameType("Вал", "", subjectServDictList.get(9).getCode_id());

            JPerson dc = null;

            if(result_j_2 != null) dc = jService.getSubject(result_j_2.get(0).getSubjectId());

            if(dc == null || (result_j_0.size() != result_j_1.size()) || result_j_2.size()<GLOBAL_INDEX) { retval= false;}

        } catch (ServiceDaoException e) {
            e.printStackTrace();
        }

        assertTrue(retval);


    }

    public void UpdateJurSubject()  {

        log.info("Invoked SubjectTest.UpdateJurSubject" );

        boolean retval = true;

        try {

            for(int i=0; i<GLOBAL_INDEX; i++) {
                JPerson jp = new JPerson();
                jp.subjectdataid = Integer.valueOf(scg.generate("SEQ_SUBJECTSDATA_ID.nextval").toString());
                jp.fullname = "ОАО Update_" + String.valueOf(i) ;
                jp.isOwner = 1;
                jp.orgRightForm =  orgStructDictList.get(6);
                jp.subjectType = subjectServDictList.get(14);
                jp.regNumber = String.valueOf(124566000+i) ;
                jp.unp = String.valueOf(159777758+i);
                jp.bothRegDate = new Date();
                jService.add(jp);
            }

            List<JPerson> result_j= jService.findByNameType("Upd", null, subjectServDictList.get(14).getCode_id());

            int i = GLOBAL_INDEX;

            for(Person p : result_j)
            {
                JPerson jp = (JPerson) p;
                jp.fullname  = "ОАО Update_" + String.valueOf(i) ;
                jp.unp =  String.valueOf(159777758+i);
                jp.regNumber =  String.valueOf(124566000+i);

                jp.orgRightForm =  orgStructDictList.get(7);
                jp.subjectType = subjectServDictList.get(12);

                jService.refreshSubject(jp);
                i++;
            }

        } catch (DaoException e) {

            retval = false;

            e.printStackTrace();
        }

        assertTrue(retval);
    }


    public void AddPhysSubject() {

        log.info("Invoked SubjectTest.AddPhysSubject" );

        boolean retval = true;


        try {


            for(int i=0; i<GLOBAL_INDEX; i++) {

                PPerson pp = new PPerson();
                pp.subjectdataid = Integer.valueOf(scg.generate("SEQ_SUBJECTSDATA_ID.nextval").toString());
                pp.surname = "Иванов"+"_"+i;
                pp.firstname = "Иван"+"_"+i;
                pp.fathername = "Иванович"+"_"+i;
                pp.subjectType = subjectServDictList.get(2);
                pp.isOwner = 1;
                pp.bothRegDate = new Date();
                pp.personalNumber = "78"+String.valueOf(71000+i)+"F408AE" ;
                pp.personalNumber += (String)scg.generate("SUBJECTS_PKG.GET_PN_CHECKDIGIT('"+pp.personalNumber+"0')");
                pp.sitizens = stateDictList.get(73);

                pService.add(pp);

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
        try {

            List<PPerson> result_p_0 = pService.getAll();

            List<PPerson> result_p_1 = pService.findByFIOType("", "", null, null, 120);

            List<PPerson> result_p_2 = pService.findByFIOType("Ив", "И", null, null, subjectServDictList.get(2).getCode_id());

            PPerson dc = null;

            if(result_p_2 != null)  dc = pService.getSubject(result_p_2.get(0).getSubjectId());

            if(dc == null || (result_p_0.size() != result_p_1.size()) || result_p_2.size()<GLOBAL_INDEX) { retval= false;}

        } catch (ServiceDaoException e) {

            e.printStackTrace();

        }

        assertTrue(retval);

    }

    public void UpdatePhysSubject() {

        log.info("Invoked SubjectTest.UpdatePhysSubject" );

        boolean retval = true;

        try {

            for(int i=0; i<GLOBAL_INDEX; i++) {

                PPerson pp = new PPerson();
                pp.subjectdataid = Integer.valueOf(scg.generate("SEQ_SUBJECTSDATA_ID.nextval").toString());

                pp.surname = "Дженкинс"+"_"+i;
                pp.firstname = "Владимир"+"_"+i;
                pp.fathername = "Обамович"+"_"+i;
                pp.subjectType = subjectServDictList.get(3);
                pp.isOwner = 1;
                pp.sitizens = stateDictList.get(2);
                pp.bothRegDate = new Date();
                pp.datestart = new Date();

                pp.personalNumber = "78"+String.valueOf(31158+i)+"F408AE" ;
                pp.personalNumber += (String)scg.generate("SUBJECTS_PKG.GET_PN_CHECKDIGIT('"+pp.personalNumber+"0')");

                pService.add(pp);

            }

            List<PPerson> result_p= pService.findByFIOType("Дж", "", "Об", "311", subjectServDictList.get(3).getCode_id());

            int i = 0;

            for(Person p : result_p)
            {
                PPerson pp = (PPerson) p;

                pp.surname  += String.valueOf(i);

                pp.personalNumber = "78"+String.valueOf(31158+i)+"F408AE" ;
                pp.personalNumber += (String)scg.generate("SUBJECTS_PKG.GET_PN_CHECKDIGIT('"+pp.personalNumber+"0')");

                pp.subjectType = subjectServDictList.get(2);
                pp.sitizens = stateDictList.get(3);

                pService.refreshSubject(pp);
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
