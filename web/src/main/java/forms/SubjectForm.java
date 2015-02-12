package forms;

import nla.local.pojos.PPerson;

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
    private Long bothRegDate;
    private String personalNumber;
    private Long datestart;
    private Integer actual;
    private String address;
    private Integer subjectType;

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

    public Long getBothRegDate() {
        return bothRegDate;
    }

    public void setBothRegDate(Long bothRegDate) {
        this.bothRegDate = bothRegDate;
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

    public Long getDatestart() {
        return datestart;
    }

    public void setDatestart(Long datestart) {
        this.datestart = datestart;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(Integer subjectType) {
        this.subjectType = subjectType;
    }

    public PPerson updatePPerson(PPerson pPerson){
        pPerson.firstname = this.firstname;
        pPerson.fathername = this.fathername;
        pPerson.surname = this.surname;
        //pPerson.actual = this.actual;
        pPerson.address = this.address;
        pPerson.bothRegDate = this.bothRegDate == null ? null : new Date(this.bothRegDate);
        pPerson.personalNumber = this.personalNumber;
        //pPerson.subjectType = this.subjectType;
        pPerson.datestart = this.datestart == null ? null : new Date(this.datestart);
        return pPerson;
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
