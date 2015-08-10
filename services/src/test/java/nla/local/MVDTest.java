package nla.local;

import nla.local.dao.IMinjstDAO;
import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.subjects.*;
import nla.local.services.IJusticeService;
import nla.local.services.ISubjectService;
import nla.local.services.impl.subjects.PassportServiceImp;
import nla.local.util.Converter;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by beresnev on 25.02.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-services.xml","classpath:beans-dao.xml"})
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class MVDTest {

    //private static String mvdsubjectsinputcleaner = "delete from MVD_SUBJECTS_INPUT";

    @Autowired
    private  PassportServiceImp psi;

    @Autowired
    private IJusticeService jsi;

    @Qualifier("JSubjectServiceImp")
    @Autowired
    private ISubjectService<JPerson> ssi;


    private List<PassportNCA> pl;

    @Before
    public  void setList()
    {
        pl = psi.getBaseDao().getSession().createCriteria(PassportNCA.class).add(Restrictions.between("rownum", 256, 500)).list();

    }

    @Test
    public void MINUSTTest() throws ServiceDaoException, ServiceException {


       // List<JurMINUST> list_ust = jsi.findSubjectName("ленин");

        JurMINJST get_jur =jsi.findSubjectUnp(390383368);

        JPerson jp = jsi.casttoPerson(get_jur);

        ssi.add(jp);

        int o = 0;



    }

    //@Test
   // @Transactional
    public void MVDSingleTest()
    {
        PassportNCA ps = new PassportNCA();

        ps.setIdentif("4230256C014PB7");
        ps.setSer("AB");
        ps.setNum("1176453");

        try {

            RespNCA resp = null;

            resp = psi.findSubject(ps);

            PPerson pp =psi.casttoPerson(resp);

            try { psi.add(pp); }
            catch (Exception e) { e.printStackTrace(); assert (false); }

            if (pp.personalNumber != null) assert (true);

        } catch (ServiceDaoException e) {
            e.printStackTrace();
        }
        catch (ServiceException e1) {

            e1.printStackTrace();
        }
    }


    public void MVDServiceTest()
    {

        //scg.update(mvdsubjectsinputcleaner);

        for (int i = 0; i <pl.size() ; i++) {

            PassportNCA ps  =  pl.get(i);

            ps.setSer(Converter.convertText(ps.getSer(), Converter.ConvertType.CYR2LAT));

            if (ps.getIdentif() != null) {

                ps.setIdentif(Converter.convertText(ps.getIdentif(), Converter.ConvertType.CYR2LAT));
                ps.setName(null);
                ps.setLastname(null);
                ps.setSurname(null);

            }
            try {
                RespNCA resp = psi.findSubject(ps);

                resp.setOrg_id(ps.getOrg_id());
                resp.setOwner_id(ps.getOwner_id());

                System.out.println("Number of Objects --- " + i);
                i++;
                psi.getBaseDao().getSession().save(resp);

            } catch (Exception p)
            {
                System.out.print(p.getMessage());
                System.out.print(p.getCause().getMessage());
            }
        }

    }

}
