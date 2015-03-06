
package nla.local.pojos.subjects;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PassportNCA")
@Entity
@Table(name="V_MVD_SUBJECTS_OUTPUT")
@Immutable
public class PassportNCA  {


    @XmlTransient
    private Integer owner_id;
    @XmlTransient
    private Integer org_id;
    @XmlTransient
    private Integer rownum;

    @XmlElement(name = "ser")
    protected String ser;
    @XmlElement(name = "num")
    protected String num;
    @XmlElement(name = "identif")
    protected String identif;
    @XmlElement(name = "surname")
    protected String surname;
    @XmlElement(name = "name")
    protected String name;
    @XmlElement(name = "lastname")
    protected String lastname;
    @XmlElement(name = "bdate")
    protected String bdate;


    public String toString()
    {
        return "PassportNCA : PassNum: " + ser + num + " Identif: " + identif;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OWNER_ID")
    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    @Column(name = "RWNUM")
    public Integer getRownum() {
        return rownum;
    }

    public void setRownum(Integer rownum) {
        this.rownum = rownum;
    }

    @Column(name = "ORG_ID")
    public Integer getOrg_id() {
        return org_id;
    }

    public void setOrg_id(Integer org_id) {
        this.org_id = org_id;
    }


    /**
     * Gets the value of the ser property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */

    @Column(name = "DOC_SERIAL")
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
    @Column(name = "DOC_NUMBER")
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
    @Column(name = "PERSONAL_NUMBER")
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
    @Column(name = "SURNAME")
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
    @Column(name = "FIRSTNAME")
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
    @Column(name = "FATHERNAME")
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
    @Column(name = "BIRTH_DATE")
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
