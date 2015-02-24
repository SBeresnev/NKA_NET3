package nla.local.services;

/**
 * Created by beresnev on 24.02.2015.
 */

import nla.local.services.util.ObjectFactory;
import nla.local.services.util.RespNCA;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;



@WebService(name = "Passport", targetNamespace = "http://MVDWebServices/")
@XmlSeeAlso({ ObjectFactory.class})
public interface IPassportService {

    /**
     *
     * @param num
     * @param ser
     * @param name
     * @param identif
     * @param lastname
     * @param surname
     * @param bdate
     * @return
     *     returns by.nca.RespNCA
     */
    @WebMethod(operationName = "PassportNCA")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "PassportNCA", targetNamespace = "http://MVDWebServices/", className = "by.nca.PassportNCA")
    @ResponseWrapper(localName = "PassportNCAResponse", targetNamespace = "http://MVDWebServices/", className = "by.nca.PassportNCAResponse")
    @Action(input = "http://MVDWebServices/Passport/PassportNCARequest", output = "http://MVDWebServices/Passport/PassportNCAResponse")
    public RespNCA passportNCA(
            @WebParam(name = "ser", targetNamespace = "")
            String ser,
            @WebParam(name = "num", targetNamespace = "")
            String num,
            @WebParam(name = "identif", targetNamespace = "")
            String identif,
            @WebParam(name = "surname", targetNamespace = "")
            String surname,
            @WebParam(name = "name", targetNamespace = "")
            String name,
            @WebParam(name = "lastname", targetNamespace = "")
            String lastname,
            @WebParam(name = "bdate", targetNamespace = "")
            String bdate);

}

