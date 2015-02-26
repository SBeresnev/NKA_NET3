package nla.local;

import nla.local.pojos.subjects.PassportNCA;
import nla.local.pojos.subjects.RespNCA;
import nla.local.services.impl.subjects.PassportServiceImp;
import nla.local.util.CodeGenerator;
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
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class MVDTest {

    //private static String mvdsubjectsinputcleaner = "delete from MVD_SUBJECTS_INPUT";

    @Autowired
    PassportServiceImp psi;

    @Autowired
    public CodeGenerator scg;

    private List<PassportNCA> pl;

    @Before
    public  void setList()
    {
        pl = psi.getSession().createCriteria(PassportNCA.class).add(Restrictions.between("rownum", 101, 250)).list();//le("rownum", 100)).list();
    }

    @Test
    @Transactional
    public void MVDServiceTest()
    {

       //scg.update(mvdsubjectsinputcleaner);

        Integer i = 0;

        for (PassportNCA pass : pl) {

            PassportNCA ps  =  pass;

            ps.setSer(Converter.convertText(ps.getSer(), Converter.ConvertType.CYR2LAT));

            if (ps.getIdentif() != null) {

                ps.setIdentif(Converter.convertText(ps.getIdentif(), Converter.ConvertType.CYR2LAT));
                ps.setName(null);
                ps.setLastname(null);
                ps.setSurname(null);

            }
            RespNCA resp = psi.findSubject(ps);

            resp.setOrg_id(ps.getOrg_id());
            resp.setOwner_id(ps.getOwner_id());

            System.out.println("Number of Objects --- " + i);
            i++;
            psi.getSession().save(resp);
        }


        /*List<Object> ty = scg.generate_obj(getsubjectsinput);
          PassportNCA PassNCA = new ObjectFactory().createPassportNCA();
          PassNCA.setSer("MP");
          PassNCA.setNum("2415801");
          PassNCA.setIdentif("3170583m002pb9");
          RespNCA rNCA = psi.findSubject(PassNCA); */

    }

}
