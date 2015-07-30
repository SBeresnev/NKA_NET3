package nla.local.pojos.orders;

import nla.local.pojos.operations.Operation;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by beresnev on 13.03.2015.
 */
public class Journal {

    private Long decl_id;

    private String declnumber;

    private Date decldate;

    private Set<Declarant> declarants;

    private Integer DeclObjects;

    private Integer Documents;

    private List<Operation> operations;

    private DeclResolution declResolution;

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public DeclResolution getDeclResolution() {
        return declResolution;
    }

    public void setDeclResolution(DeclResolution declResolution) {
        this.declResolution = declResolution;
    }

    public Long getDecl_id() {
        return decl_id;
    }

    public void setDecl_id(Long decl_id) {
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

    public Set<Declarant> getDeclarants() {
        return declarants;
    }

    public void setDeclarants(Set<Declarant> declarants) {
        this.declarants = declarants;
    }
}
