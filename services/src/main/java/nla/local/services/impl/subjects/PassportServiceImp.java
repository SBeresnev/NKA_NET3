package nla.local.services.impl.subjects;

import nla.local.pojos.subjects.PassportNCA;
import nla.local.pojos.subjects.RespNCA;
import nla.local.services.IPassportService;
import nla.local.services.MVDutil.PassportNCAResponse;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.bind.JAXBElement;

/**
 * Created by beresnev on 25.02.2015.
 */
@Service
public class PassportServiceImp extends PSubjectServiceImp implements IPassportService{

    @Autowired
    WebServiceTemplate webServiceTemplate;

    public PassportServiceImp(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public RespNCA findSubject(PassportNCA PassNCA) {

        JAXBElement<PassportNCAResponse> jaxrespNCA = (JAXBElement<PassportNCAResponse>) webServiceTemplate.marshalSendAndReceive(PassNCA);

        RespNCA ret_val = jaxrespNCA.getValue().getReturn();

        return ret_val;
    }

}
