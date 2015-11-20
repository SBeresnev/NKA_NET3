package nla.local.services.impl.subjects;

import nla.local.dao.exceptions.DaoErrorCode;
import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.subjects.PPerson;
import nla.local.pojos.subjects.PassportNCA;
import nla.local.pojos.subjects.RespNCA;
import nla.local.services.IPassportService;
import nla.local.services.MVDutil.PassportNCAResponse;
import nla.local.services.impl.CatalogServiceImp;
import nla.local.util.CodeGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.log4j.Logger;
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

    private static Logger log = Logger.getLogger(PassportServiceImp.class);

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Autowired
    public CodeGenerator scg;

    @Autowired
    public CatalogServiceImp commonDict;

    @Override
    public RespNCA findSubject(PassportNCA PassNCA) throws ServiceDaoException {

        RespNCA ret_val = null;

        try {

            JAXBElement<PassportNCAResponse> jaxrespNCA = (JAXBElement<PassportNCAResponse>) webServiceTemplate.marshalSendAndReceive(PassNCA);

            ret_val = jaxrespNCA.getValue().getReturn();

        } catch (Exception ex) {

            throw new ServiceDaoException(ex, DaoErrorCode.NKANET_DAO_007, PassNCA.toString());

        }

        return ret_val;

    }

    public PPerson casttoPerson(RespNCA resp) throws ServiceException
    {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

            PPerson p = new PPerson();

            p.subjectdataid = Integer.valueOf(scg.generate("SEQ_SUBJECTSDATA_ID.nextval").toString());

            p.firstname = resp.getNAME();
            p.fathername = resp.getSNAME();
            p.surname = resp.getSURNAME();

            if (resp.getDOCTYPE() == 54100001)
              p.sitizens= 112 ; /*new Dict(new DictPk(112,200));      РЕСПУБЛИКА БЕЛАРУСЬ*/


            p.subjectType =110; /* new Dict(new DictPk(110,110)); /*Граждане Республики Беларусь, имеющие паспорт нового образца*/

            p.address = "РЕСПУБЛИКА БЕЛАРУСЬ; " + resp.getAREALTXT() + "; ";
            p.address += resp.getREGIONLTXT() != null ? resp.getREGIONLTXT() + " р-н ; " : "";
            p.address += resp.getTYPECITYLTXT() + ". " + resp.getCITYLTXT() + "; ";
            p.address += resp.getTYPESTREETLTXT() + ". " + resp.getSTREETLTXT() + " ";
            p.address += resp.getHOUSE() != null ? " д. " + resp.getHOUSE() + " " : "";
            p.address += resp.getKORPS() != null ? ", корп. " + resp.getKORPS() + " " : "";
            p.address += resp.getAPP() != null ? ", кв. " + resp.getAPP() +  " " : "";

            try {
                p.bothRegDate = formatter.parse(resp.getBDATE());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            p.remark = resp.getSTATUS();

            p.personalNumber = resp.getIDENTIF();

            return p;
        } catch (Exception ex) {

            throw new ServiceException(ex, ex.getMessage() + ". Can not cast RespNCA " + resp.toString() +" to PPerson.");
        }


    }

}
