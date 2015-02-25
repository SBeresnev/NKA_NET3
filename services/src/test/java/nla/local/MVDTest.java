package nla.local;

import nla.local.pojos.subjects.RespNCA;
import nla.local.services.IPassportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by beresnev on 25.02.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:beans-services.xml","classpath:beans-dao.xml"})
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class MVDTest {

    @Test
    public void MVDServiceTest()
    {
        IPassportService service = new PassportServiceImp().getPassportPort();
        //invoke business method
        RespNCA resp = service.passportNCA("MP", "2415801", "3170583m002pb9", "", "", "", "");
        if (resp.getError() == null) {
            System.out.println(resp.getNAME()+resp.getSNAME()/*+etc+*/);
        } else {
            System.out.println(resp.getError());
        }

    }

}
