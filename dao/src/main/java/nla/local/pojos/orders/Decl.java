package nla.local.pojos.orders;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import nla.local.pojos.operations.Operation;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by beresnev on 09.03.2015.
 */
@Entity
@Table(name = "DECL")
public class Decl implements Serializable {

    @Id
    @GeneratedValue(generator="seq_id")
    @GenericGenerator(
            name="seq_id",
            strategy = "nla.local.util.CodeGenerator",
            parameters = @org.hibernate.annotations.Parameter(name = "seq_name", value = "SEQ_DECL_ID"))
    @Column(name="DECL_ID", unique=true, nullable=false )
    private Integer decl_id;

    @OneToMany(mappedBy = "decl_id", fetch = FetchType.EAGER ,orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Declarant> declarants;

    @OneToMany(mappedBy = "decl_id", fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    private Set<DeclResolution> dclresolution;

    @OneToMany(fetch = FetchType.EAGER ,cascade=CascadeType.ALL)
    @JoinColumn(name = "DECL_ID", nullable = true)
    private Set<DeclUser> oUsers;

    @Column(name = "DECLNUMBER")
    private String declnumber;

    @OneToMany(fetch = FetchType.EAGER ,cascade=CascadeType.ALL)
    @JoinColumn(name = "DECL_ID", nullable = true)
    private Set<Operation> operations;

    @Column(name = "DECLDATE")
    @JsonSerialize(using=DateSerializer.class)
    private Date decldate;

    @Column(name="DECLTYPE")
    private Integer decltype;

    @Column(name="URGENCY")
    private Integer urgency;

    @Column(name = "REGDECLDATE")
    @JsonSerialize(using=DateSerializer.class)
    private Date regdecldate;

    @Column(name = "INFO")
    private String info;



    public void fillDeclId(Integer d_id){

        if( oUsers != null ) for(DeclUser du : oUsers){
            du.setDecl_id(d_id);
        }

        if( dclresolution != null ) for(DeclResolution dr : dclresolution){
            dr.setDecl_id(d_id);
        }

       /* for(Operation op : operations){
            op.setDeclId(d_id);
        }*/

        if( declarants != null ) for(Declarant dcr : declarants){

            dcr.setDecl_id(d_id);

            if(dcr.getParentPerson() != null)
            {
                dcr.getParentPerson().setDecl_id(d_id);
            }

        }

    }

    public Set<DeclResolution> getDclresolution() {
        return dclresolution;
    }

    public void setDclresolution(Set<DeclResolution> dclresolution) {
        this.dclresolution = dclresolution;
    }

    public Integer getDecltype() {
        return decltype;
    }

    public void setDecltype(Integer decltype) {
        this.decltype = decltype;
    }

    public Integer getUrgency() {
        return urgency;
    }

    public void setUrgency(Integer urgency) {
        this.urgency = urgency;
    }

    public Integer getDecl_id() {
        return decl_id;
    }

    public void setDecl_id(Integer decl_id) {
        this.decl_id = decl_id;
    }

    public Date getDecldate() {
        return decldate;
    }

    public void setDecldate(Date decldate) {
        this.decldate = decldate;
    }

    public Date getRegdecldate() {
        return regdecldate;
    }

    public void setRegdecldate(Date regdecldate) {
        this.regdecldate = regdecldate;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Set<DeclUser> getoUsers() {
        return oUsers;
    }

    public void setoUsers(Set<DeclUser> oUsers) {
        this.oUsers = oUsers;
    }

    public String getDeclnumber() {
        return declnumber;
    }

    public void setDeclnumber(String declnumber) {
        this.declnumber = declnumber;
    }

    public Set<Declarant> getDeclarants() {
        return declarants;
    }

    public void setDeclarants(Set<Declarant> declarants) {
        this.declarants = declarants;
    }

    public Set<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Set<Operation> operations) {

        this.operations = operations;
    }

}