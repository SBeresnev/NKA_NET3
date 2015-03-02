package nla.local.services.impl.subjects;

import nla.local.pojos.dict.Dict;
import nla.local.pojos.dict.DictPk;
import nla.local.pojos.subjects.PPerson;
import nla.local.pojos.subjects.PassportNCA;
import nla.local.pojos.subjects.RespNCA;
import nla.local.services.IPassportService;
import nla.local.services.MVDutil.PassportNCAResponse;
import nla.local.util.CodeGenerator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.bind.JAXBElement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by beresnev on 25.02.2015.
 */
@Service
public class PassportServiceImp extends PSubjectServiceImp implements IPassportService{

    @Autowired
    WebServiceTemplate webServiceTemplate;

    @Autowired
    public CodeGenerator scg;

    @Autowired
    public PassportServiceImp(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public RespNCA findSubject(PassportNCA PassNCA) {

        JAXBElement<PassportNCAResponse> jaxrespNCA = (JAXBElement<PassportNCAResponse>) webServiceTemplate.marshalSendAndReceive(PassNCA);

        RespNCA ret_val = jaxrespNCA.getValue().getReturn();

        return ret_val;
    }

    public PPerson casttoPerson(RespNCA resp)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        PPerson p = new PPerson();

        p.subjectdataid = Integer.valueOf(scg.generate("SEQ_SUBJECTSDATA_ID.nextval").toString());

        p.firstname = resp.getNAME();
        p.fathername = resp.getSURNAME();
        p.surname = resp.getSURNAME();

        if (resp.getDOCTYPE() == 54100001)
        p.sitizens = new Dict(new DictPk(112,200));      /*РЕСПУБЛИКА БЕЛАРУСЬ*/
        p.subjectType = new Dict(new DictPk(110,110)); /*Граждане Республики Беларусь, имеющие паспорт нового образца*/

        p.address = "РЕСПУБЛИКА БЕЛАРУСЬ; " + resp.getAREALTXT() + "; ";
        p.address += resp.getREGIONLTXT() != null ? resp.getREGIONLTXT()+" р-н ; ": "";
        p.address +=  resp.getTYPECITYLTXT() + ". " + resp.getCITYLTXT()+ "; ";
        p.address +=  resp.getTYPESTREETLTXT() + ". " + resp.getSTREETLTXT() + " " ;
        p.address += resp.getHOUSE() != null ? "д. " + resp.getHOUSE()+" ": "";
        p.address += resp.getKORPS() != null ? "корп. " + resp.getKORPS()+" ": "";

        try { p.bothRegDate = formatter.parse(resp.getBDATE());}
        catch (ParseException e) { e.printStackTrace();}

        p.remark = resp.getSTATUS();

        p.personalNumber = resp.getIDENTIF();

        return p;

    }

}
