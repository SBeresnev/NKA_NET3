package nla.local;

import nla.local.pojos.subjects.PPerson;
import nla.local.pojos.subjects.PassportNCA;
import nla.local.pojos.subjects.RespNCA;
import nla.local.services.impl.subjects.PassportServiceImp;
import nla.local.util.Converter;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class MVDTest {

    //private static String mvdsubjectsinputcleaner = "delete from MVD_SUBJECTS_INPUT";

    @Autowired
    PassportServiceImp psi;

    private List<PassportNCA> pl;

    @Before
    public  void setList()
    {
        pl = psi.getSession().createCriteria(PassportNCA.class).add(Restrictions.between("rownum", 256, 500)).list();

    }

    @Test
    @Transactional
    public void MVDSingleTest()
    {
        PassportNCA ps = new PassportNCA();

        ps.setIdentif("4230256C014PB7");
        ps.setSer("AB");
        ps.setNum("1176453");

        RespNCA resp = psi.findSubject(ps);
        PPerson pp =psi.casttoPerson(resp);

        try { psi.add(pp); }
        catch (Exception e) { e.printStackTrace(); assert (false); }

        if (pp.personalNumber != null) assert (true);

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
                psi.getSession().save(resp);

            } catch (Exception p)
            {
                System.out.print(p.getMessage());
                System.out.print(p.getCause().getMessage());
            }
        }

    }

}
