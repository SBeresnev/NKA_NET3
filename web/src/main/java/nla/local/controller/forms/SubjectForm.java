package nla.local.controller.forms;

import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.subjects.JPerson;
import nla.local.pojos.subjects.PPerson;
import nla.local.pojos.subjects.SubjectClass;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by belonovich on 05.02.2015.
 */
public class SubjectForm {
    private Integer subjectId;
    private String firstname;
    private String surname;
    private String fathername;
    private Date bothRegDate;
    private String personalNumber;
    private Date datestart;
    private String reestrdataID;
    private Integer actual;
    private String address;
    private CatalogItem subjectType;
    private SubjectClass subjectClass;
    private CatalogItem sitizens;
    private String remark;
    private String fullname;
    private String shortname;
    private String regNumber;
    private String unp;
    private CatalogItem orgRightForm;
    private String isOwner;
    private String dtype;
    private  String subjectdataid;

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public Date getBothRegDate() {
        return bothRegDate;
    }

    public void setBothRegDate(Date bothRegDate) {
        this.bothRegDate = bothRegDate;
    }

    public String getDtype() {
        return dtype;
    }

    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    public String getSubjectdataid() {
        return subjectdataid;
    }

    public void setSubjectdataid(String subjectdataid) {
        this.subjectdataid = subjectdataid;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public Integer getActual() {
        return actual;
    }

    public void setActual(Integer actual) {
        this.actual = actual;
    }

    public Date getDatestart() {
        return datestart;
    }

    public void setDatestart(Date datestart) {
        this.datestart = datestart;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CatalogItem getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(CatalogItem type) {
        if (type.getCode_id() < 200)
            setSubjectClass(SubjectClass.PRV);
        else {
            if (type.getCode_id() < 500)
                setSubjectClass(SubjectClass.JUR);
            else
                setSubjectClass(SubjectClass.OFC);
        }
        this.subjectType = type;
    }

    public SubjectClass getSubjectClass() {
        return subjectClass;
    }

    public void setSubjectClass(SubjectClass subjectClass) {
        this.subjectClass = subjectClass;
    }

    public CatalogItem getSitizens() {
        return sitizens;
    }

    public void setSitizens(CatalogItem sitizens) {
        this.sitizens = sitizens;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getUnp() {
        return unp;
    }

    public void setUnp(String unp) {
        this.unp = unp;
    }

    public CatalogItem getOrgRightForm() {
        return orgRightForm;
    }

    public void setOrgRightForm(CatalogItem orgRightForm) {
        this.orgRightForm = orgRightForm;
    }

    public String getReestrdataID() {
        return reestrdataID;
    }

    public void setReestrdataID(String reestrdataID) {
        this.reestrdataID = reestrdataID;
    }

    public String getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(String isOwner) {
        this.isOwner = isOwner;
    }

    public PPerson updatePPerson(PPerson pPerson){
        pPerson.subjectdataid = this.subjectdataid != null ?  Integer.decode(this.subjectdataid) : null;
        pPerson.firstname = this.firstname;
        pPerson.fathername = this.fathername;
        pPerson.surname = this.surname;
        pPerson.address = this.address;
        pPerson.bothRegDate = this.bothRegDate ;
        pPerson.personalNumber = this.personalNumber;
        pPerson.subjectType = this.subjectType;
        pPerson.isOwner =  this.isOwner != null ? Integer.decode(this.isOwner) : null;
        pPerson.reestrdataID = this.reestrdataID != null ? Integer.decode(this.reestrdataID) : null;
        pPerson.remark = this.remark;
        pPerson.sitizens = this.sitizens;
        pPerson.dtype = this.dtype;
        return pPerson;
    }

    public JPerson updateJPerson(JPerson jPerson){
        jPerson.subjectdataid = this.subjectdataid != null ? Integer.decode(this.subjectdataid) : null;
        jPerson.address = this.address;
        jPerson.bothRegDate = this.bothRegDate;
        jPerson.fullname = this.fullname;
        jPerson.orgRightForm = this.orgRightForm;
        jPerson.regNumber = this.regNumber;
        jPerson.remark = this.remark;
        jPerson.shortname = this.shortname;
        jPerson.unp = this.unp;
        jPerson.isOwner = this.isOwner != null ? Integer.decode(this.isOwner) : null;
        jPerson.dtype = this.dtype;
        jPerson.reestrdataID = this.reestrdataID != null ? Integer.decode(this.reestrdataID) : null;
        jPerson.subjectType = this.subjectType;
        return jPerson;
    }

    public SubjectForm() {
    }

    public SubjectForm(HttpServletRequest request) {
        for(Field field : this.getClass().getDeclaredFields()){
            try {
                field.set(this ,request.getParameter(field.getName()));
            } catch (IllegalAccessException e) {
                    e.toString();
            }

        }
    }
}
