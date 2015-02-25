package nla.local.pojos.subjects;

/**
 * Created by beresnev on 24.02.2015.
 */

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for respNCA complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="respNCA">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="APP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AREA_L_TXT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BDATE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CITIZENSHIP" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="CITIZENSHIP_TXT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CITY_L_TXT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="COUNTRY_L_TXT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="c_AREA_L" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="c_CITY_L" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="c_COUNTRY_L" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="c_REGION_L" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="c_STREET_L" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="c_TYPE_CITY_L" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="c_TYPE_STREET_L" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="DOCUMENT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DOC_TYPE" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="DOC_TYPE_TXT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HOUSE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IDENTIF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="KORPS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="REGION_L_TXT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SNAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="STATUS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="STATUS_CODE" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="STREET_L_TXT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SURNAME" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TYPE_CITY_L_TXT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TYPE_STREET_L_TXT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="t_AREA_L" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="t_CITY_L" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="t_COUNTRY_L" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="t_REGION_L" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="t_STREET_L" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="t_TYPE_CITY_L" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="t_TYPE_STREET_L" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "respNCA", propOrder = {
        "app",
        "arealtxt",
        "bdate",
        "citizenship",
        "citizenshiptxt",
        "cityltxt",
        "countryltxt",
        "careal",
        "ccityl",
        "ccountryl",
        "cregionl",
        "cstreetl",
        "ctypecityl",
        "ctypestreetl",
        "document",
        "doctype",
        "doctypetxt",
        "error",
        "house",
        "identif",
        "korps",
        "name",
        "regionltxt",
        "sname",
        "status",
        "statuscode",
        "streetltxt",
        "surname",
        "typecityltxt",
        "typestreetltxt",
        "tareal",
        "tcityl",
        "tcountryl",
        "tregionl",
        "tstreetl",
        "ttypecityl",
        "ttypestreetl"
})
public class RespNCA {

    @XmlElement(name = "APP")
    protected String app;
    @XmlElement(name = "AREA_L_TXT")
    protected String arealtxt;
    @XmlElement(name = "BDATE")
    protected String bdate;
    @XmlElement(name = "CITIZENSHIP")
    protected Long citizenship;
    @XmlElement(name = "CITIZENSHIP_TXT")
    protected String citizenshiptxt;
    @XmlElement(name = "CITY_L_TXT")
    protected String cityltxt;
    @XmlElement(name = "COUNTRY_L_TXT")
    protected String countryltxt;
    @XmlElement(name = "c_AREA_L")
    protected Long careal;
    @XmlElement(name = "c_CITY_L")
    protected Long ccityl;
    @XmlElement(name = "c_COUNTRY_L")
    protected Long ccountryl;
    @XmlElement(name = "c_REGION_L")
    protected Long cregionl;
    @XmlElement(name = "c_STREET_L")
    protected Long cstreetl;
    @XmlElement(name = "c_TYPE_CITY_L")
    protected Long ctypecityl;
    @XmlElement(name = "c_TYPE_STREET_L")
    protected Long ctypestreetl;
    @XmlElement(name = "DOCUMENT")
    protected String document;
    @XmlElement(name = "DOC_TYPE")
    protected Long doctype;
    @XmlElement(name = "DOC_TYPE_TXT")
    protected String doctypetxt;
    protected String error;
    @XmlElement(name = "HOUSE")
    protected String house;
    @XmlElement(name = "IDENTIF")
    protected String identif;
    @XmlElement(name = "KORPS")
    protected String korps;
    @XmlElement(name = "NAME")
    protected String name;
    @XmlElement(name = "REGION_L_TXT")
    protected String regionltxt;
    @XmlElement(name = "SNAME")
    protected String sname;
    @XmlElement(name = "STATUS")
    protected String status;
    @XmlElement(name = "STATUS_CODE")
    protected Long statuscode;
    @XmlElement(name = "STREET_L_TXT")
    protected String streetltxt;
    @XmlElement(name = "SURNAME")
    protected String surname;
    @XmlElement(name = "TYPE_CITY_L_TXT")
    protected String typecityltxt;
    @XmlElement(name = "TYPE_STREET_L_TXT")
    protected String typestreetltxt;
    @XmlElement(name = "t_AREA_L")
    protected Long tareal;
    @XmlElement(name = "t_CITY_L")
    protected Long tcityl;
    @XmlElement(name = "t_COUNTRY_L")
    protected Long tcountryl;
    @XmlElement(name = "t_REGION_L")
    protected Long tregionl;
    @XmlElement(name = "t_STREET_L")
    protected Long tstreetl;
    @XmlElement(name = "t_TYPE_CITY_L")
    protected Long ttypecityl;
    @XmlElement(name = "t_TYPE_STREET_L")
    protected Long ttypestreetl;

    /**
     * Gets the value of the app property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAPP() {
        return app;
    }

    /**
     * Sets the value of the app property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAPP(String value) {
        this.app = value;
    }

    /**
     * Gets the value of the arealtxt property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAREALTXT() {
        return arealtxt;
    }

    /**
     * Sets the value of the arealtxt property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAREALTXT(String value) {
        this.arealtxt = value;
    }

    /**
     * Gets the value of the bdate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBDATE() {
        return bdate;
    }

    /**
     * Sets the value of the bdate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBDATE(String value) {
        this.bdate = value;
    }

    /**
     * Gets the value of the citizenship property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getCITIZENSHIP() {
        return citizenship;
    }

    /**
     * Sets the value of the citizenship property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setCITIZENSHIP(Long value) {
        this.citizenship = value;
    }

    /**
     * Gets the value of the citizenshiptxt property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCITIZENSHIPTXT() {
        return citizenshiptxt;
    }

    /**
     * Sets the value of the citizenshiptxt property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCITIZENSHIPTXT(String value) {
        this.citizenshiptxt = value;
    }

    /**
     * Gets the value of the cityltxt property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCITYLTXT() {
        return cityltxt;
    }

    /**
     * Sets the value of the cityltxt property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCITYLTXT(String value) {
        this.cityltxt = value;
    }

    /**
     * Gets the value of the countryltxt property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCOUNTRYLTXT() {
        return countryltxt;
    }

    /**
     * Sets the value of the countryltxt property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCOUNTRYLTXT(String value) {
        this.countryltxt = value;
    }

    /**
     * Gets the value of the careal property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getCAREAL() {
        return careal;
    }

    /**
     * Sets the value of the careal property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setCAREAL(Long value) {
        this.careal = value;
    }

    /**
     * Gets the value of the ccityl property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getCCITYL() {
        return ccityl;
    }

    /**
     * Sets the value of the ccityl property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setCCITYL(Long value) {
        this.ccityl = value;
    }

    /**
     * Gets the value of the ccountryl property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getCCOUNTRYL() {
        return ccountryl;
    }

    /**
     * Sets the value of the ccountryl property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setCCOUNTRYL(Long value) {
        this.ccountryl = value;
    }

    /**
     * Gets the value of the cregionl property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getCREGIONL() {
        return cregionl;
    }

    /**
     * Sets the value of the cregionl property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setCREGIONL(Long value) {
        this.cregionl = value;
    }

    /**
     * Gets the value of the cstreetl property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getCSTREETL() {
        return cstreetl;
    }

    /**
     * Sets the value of the cstreetl property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setCSTREETL(Long value) {
        this.cstreetl = value;
    }

    /**
     * Gets the value of the ctypecityl property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getCTYPECITYL() {
        return ctypecityl;
    }

    /**
     * Sets the value of the ctypecityl property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setCTYPECITYL(Long value) {
        this.ctypecityl = value;
    }

    /**
     * Gets the value of the ctypestreetl property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getCTYPESTREETL() {
        return ctypestreetl;
    }

    /**
     * Sets the value of the ctypestreetl property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setCTYPESTREETL(Long value) {
        this.ctypestreetl = value;
    }

    /**
     * Gets the value of the document property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDOCUMENT() {
        return document;
    }

    /**
     * Sets the value of the document property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDOCUMENT(String value) {
        this.document = value;
    }

    /**
     * Gets the value of the doctype property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getDOCTYPE() {
        return doctype;
    }

    /**
     * Sets the value of the doctype property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setDOCTYPE(Long value) {
        this.doctype = value;
    }

    /**
     * Gets the value of the doctypetxt property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDOCTYPETXT() {
        return doctypetxt;
    }

    /**
     * Sets the value of the doctypetxt property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDOCTYPETXT(String value) {
        this.doctypetxt = value;
    }

    /**
     * Gets the value of the error property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the value of the error property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setError(String value) {
        this.error = value;
    }

    /**
     * Gets the value of the house property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getHOUSE() {
        return house;
    }

    /**
     * Sets the value of the house property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setHOUSE(String value) {
        this.house = value;
    }

    /**
     * Gets the value of the identif property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIDENTIF() {
        return identif;
    }

    /**
     * Sets the value of the identif property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setIDENTIF(String value) {
        this.identif = value;
    }

    /**
     * Gets the value of the korps property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getKORPS() {
        return korps;
    }

    /**
     * Sets the value of the korps property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setKORPS(String value) {
        this.korps = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNAME() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNAME(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the regionltxt property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getREGIONLTXT() {
        return regionltxt;
    }

    /**
     * Sets the value of the regionltxt property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setREGIONLTXT(String value) {
        this.regionltxt = value;
    }

    /**
     * Gets the value of the sname property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSNAME() {
        return sname;
    }

    /**
     * Sets the value of the sname property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSNAME(String value) {
        this.sname = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSTATUS() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSTATUS(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the statuscode property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getSTATUSCODE() {
        return statuscode;
    }

    /**
     * Sets the value of the statuscode property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setSTATUSCODE(Long value) {
        this.statuscode = value;
    }

    /**
     * Gets the value of the streetltxt property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSTREETLTXT() {
        return streetltxt;
    }

    /**
     * Sets the value of the streetltxt property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSTREETLTXT(String value) {
        this.streetltxt = value;
    }

    /**
     * Gets the value of the surname property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSURNAME() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSURNAME(String value) {
        this.surname = value;
    }

    /**
     * Gets the value of the typecityltxt property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTYPECITYLTXT() {
        return typecityltxt;
    }

    /**
     * Sets the value of the typecityltxt property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTYPECITYLTXT(String value) {
        this.typecityltxt = value;
    }

    /**
     * Gets the value of the typestreetltxt property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getTYPESTREETLTXT() {
        return typestreetltxt;
    }

    /**
     * Sets the value of the typestreetltxt property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setTYPESTREETLTXT(String value) {
        this.typestreetltxt = value;
    }

    /**
     * Gets the value of the tareal property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getTAREAL() {
        return tareal;
    }

    /**
     * Sets the value of the tareal property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setTAREAL(Long value) {
        this.tareal = value;
    }

    /**
     * Gets the value of the tcityl property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getTCITYL() {
        return tcityl;
    }

    /**
     * Sets the value of the tcityl property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setTCITYL(Long value) {
        this.tcityl = value;
    }

    /**
     * Gets the value of the tcountryl property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getTCOUNTRYL() {
        return tcountryl;
    }

    /**
     * Sets the value of the tcountryl property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setTCOUNTRYL(Long value) {
        this.tcountryl = value;
    }

    /**
     * Gets the value of the tregionl property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getTREGIONL() {
        return tregionl;
    }

    /**
     * Sets the value of the tregionl property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setTREGIONL(Long value) {
        this.tregionl = value;
    }

    /**
     * Gets the value of the tstreetl property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getTSTREETL() {
        return tstreetl;
    }

    /**
     * Sets the value of the tstreetl property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setTSTREETL(Long value) {
        this.tstreetl = value;
    }

    /**
     * Gets the value of the ttypecityl property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getTTYPECITYL() {
        return ttypecityl;
    }

    /**
     * Sets the value of the ttypecityl property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setTTYPECITYL(Long value) {
        this.ttypecityl = value;
    }

    /**
     * Gets the value of the ttypestreetl property.
     *
     * @return
     *     possible object is
     *     {@link Long }
     *
     */
    public Long getTTYPESTREETL() {
        return ttypestreetl;
    }

    /**
     * Sets the value of the ttypestreetl property.
     *
     * @param value
     *     allowed object is
     *     {@link Long }
     *
     */
    public void setTTYPESTREETL(Long value) {
        this.ttypestreetl = value;
    }

}
