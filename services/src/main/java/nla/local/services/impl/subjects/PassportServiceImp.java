package nla.local.services.impl.subjects;

/**
 * Created by beresnev on 24.02.2015.
 */

import nla.local.services.IPassportService;

import javax.xml.namespace.QName;
import javax.xml.ws.*;
import java.net.MalformedURLException;
import java.net.URL;

@WebServiceClient(name = "Passport", targetNamespace = "http://MVDWebServices/", wsdlLocation = "http://172.31.11.242:8080/MVDWS/Passport?wsdl")
public class PassportServiceImp extends Service
{

    private final static URL PASSPORT_WSDL_LOCATION;
    private final static WebServiceException PASSPORT_EXCEPTION;
    private final static QName PASSPORT_QNAME = new QName("http://MVDWebServices/", "Passport");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://172.31.11.242:8080/MVDWS/Passport?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PASSPORT_WSDL_LOCATION = url;
        PASSPORT_EXCEPTION = e;
    }

    public PassportServiceImp() {
        super(__getWsdlLocation(), PASSPORT_QNAME);
    }

    public PassportServiceImp(WebServiceFeature... features) {
        super(__getWsdlLocation(), PASSPORT_QNAME, features);
    }

    public PassportServiceImp(URL wsdlLocation) {
        super(wsdlLocation, PASSPORT_QNAME);
    }

    public PassportServiceImp(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PASSPORT_QNAME, features);
    }

    public PassportServiceImp(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PassportServiceImp(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns Passport
     */
    @WebEndpoint(name = "PassportPort")
    public IPassportService getPassportPort() {
        return super.getPort(new QName("http://MVDWebServices/", "PassportPort"), IPassportService.class);
    }

    /**
     *
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Passport
     */
    @WebEndpoint(name = "PassportPort")
    public IPassportService getPassportPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://MVDWebServices/", "PassportPort"), IPassportService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PASSPORT_EXCEPTION!= null) {
            throw PASSPORT_EXCEPTION;
        }
        return PASSPORT_WSDL_LOCATION;
    }

}

