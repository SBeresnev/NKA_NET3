package nla.local.pojos.orders;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import nla.local.pojos.subjects.Person;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
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
    public Integer decl_id;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "DECLARANTS", joinColumns = {
            @JoinColumn(name = "DECL_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "SUBJECT_ID", nullable = false, updatable = false) })
    private Set<Person> declarants;

    @Column(name = "DECLNUMBER")
    private String declnumber ;

    @OneToMany(cascade=CascadeType.MERGE)
    @JoinColumn(name = "DECL_ID")
    //@Where( clause = "RESOLUTION_DATE_OUT is NULL")
    private Set<DeclResolution> dclresolution;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "DECL_ID")
    private Set<DeclUser> oUsers;

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

    public void fillDeclId(Integer d_id)
    {
        for(DeclUser du : oUsers)
        {
            du.setDecl_id(d_id);
        }
        for(DeclResolution dr : dclresolution)
        {
            dr.setDecl_id(d_id);
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
    public Set<Person> getDeclarants() {
        return declarants;
    }
    public void setDeclarants(Set<Person> declarants) {
        this.declarants = declarants;
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
}