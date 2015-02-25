package nla.local.services.impl.subjects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

/**
 * Created by beresnev on 25.02.2015.
 */
@Service
public class IPassportServiceImp {

    @Autowired
    WebServiceTemplate webServiceTemplate;

}
