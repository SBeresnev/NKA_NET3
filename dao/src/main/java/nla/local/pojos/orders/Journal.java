package nla.local.pojos.orders;

import nla.local.pojos.subjects.Person;

import java.util.Date;
import java.util.Set;

/**
 * Created by beresnev on 13.03.2015.
 */
public class Journal {

    private Integer decl_id;

    private String declnumber;

    private Date decldate;

    private Set<Person> declarants;

    private Integer DeclObjects;

    private Integer Documents;

    private Integer Operations;

    private DeclResolution declResolution;


    public Integer getOperations() {
        return Operations;
    }

    public void setOperations(Integer operations) {
        Operations = operations;
    }


    public DeclResolution getDeclResolution() {
        return declResolution;
    }

    public void setDeclResolution(DeclResolution declResolution) {
        this.declResolution = declResolution;
    }


    public Integer getDecl_id() {
        return decl_id;
    }

    public void setDecl_id(Integer decl_id) {
        this.decl_id = decl_id;
    }

    public String getDeclnumber() {
        return declnumber;
    }

    public void setDeclnumber(String declnumber) {
        this.declnumber = declnumber;
    }

    public Date getDecldate() {
        return decldate;
    }

    public void setDecldate(Date decldate) {
        this.decldate = decldate;
    }

    public Set<Person> getDeclarants() {
        return declarants;
    }

    public void setDeclarants(Set<Person> declarants) {
        this.declarants = declarants;
    }

    public Integer getDeclObjects() {
        return DeclObjects;
    }

    public void setDeclObjects(Integer declObjects) {
        DeclObjects = declObjects;
    }

    public Integer getDocuments() {
        return Documents;
    }

    public void setDocuments(Integer documents) {
        Documents = documents;
    }


}
