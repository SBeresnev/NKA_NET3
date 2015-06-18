package nla.local.services.MVDutil;

/**
 * Created by beresnev on 24.02.2015.
 */

import nla.local.pojos.subjects.PassportNCA;
import nla.local.pojos.subjects.RespNCA;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the  package.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PassportNCA_QNAME = new QName("http://MVDWebServices/", "PassportNCA");
    private final static QName _PassportNCAResponse_QNAME = new QName("http://MVDWebServices/", "PassportNCAResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: 
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PassportNCAResponse }
     *
     */
    public PassportNCAResponse createPassportNCAResponse() {
        return new PassportNCAResponse();
    }

    /**
     * Create an instance of {@link nla.local.pojos.subjects.PassportNCA }
     *
     */
    public PassportNCA createPassportNCA() {
        return new PassportNCA();
    }

    /**
     * Create an instance of {@link nla.local.pojos.subjects.RespNCA }
     *
     */
    public RespNCA createRespNCA() {
        return new RespNCA();
    }

    /**
     * Create an instance of {@link javax.xml.bind.JAXBElement }{@code <}{@link PassportNCA }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://MVDWebServices/", name = "PassportNCA")
    public JAXBElement<PassportNCA> createPassportNCA(PassportNCA value) {
        return new JAXBElement<PassportNCA>(_PassportNCA_QNAME, PassportNCA.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PassportNCAResponse }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://MVDWebServices/", name = "PassportNCAResponse")
    public JAXBElement<PassportNCAResponse> createPassportNCAResponse(PassportNCAResponse value) {
        return new JAXBElement<PassportNCAResponse>(_PassportNCAResponse_QNAME, PassportNCAResponse.class, null, value);
    }

}

