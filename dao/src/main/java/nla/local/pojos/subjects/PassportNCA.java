
package nla.local.pojos.subjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PassportNCA complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="PassportNCA">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="num" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="identif" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PassportNCA", propOrder = {
        "ser",
        "num",
        "identif",
        "surname",
        "name",
        "lastname",
        "bdate"
})
public class PassportNCA {

    protected String ser;
    protected String num;
    protected String identif;
    protected String surname;
    protected String name;
    protected String lastname;
    protected String bdate;

    /**
     * Gets the value of the ser property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSer() {
        return ser;
    }

    /**
     * Sets the value of the ser property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setSer(String value) {
        this.ser = value;
    }

    /**
     * Gets the value of the num property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getNum() {
        return num;
    }

    /**
     * Sets the value of the num property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setNum(String value) {
        this.num = value;
    }

    /**
     * Gets the value of the identif property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getIdentif() {
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
    public void setIdentif(String value) {
        this.identif = value;
    }

    /**
     * Gets the value of the surname property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getSurname() {
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
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getName() {
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
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the lastname property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the value of the lastname property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLastname(String value) {
        this.lastname = value;
    }

    /**
     * Gets the value of the bdate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBdate() {
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
    public void setBdate(String value) {
        this.bdate = value;
    }

}
