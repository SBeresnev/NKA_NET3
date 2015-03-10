package nla.local.pojos.orders;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import nla.local.pojos.subjects.Person;
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

   /*  @Id   @Parameter(name="key", value = "SEQ_DECL_ID")
    @Column(name="DECL_ID", unique=true, nullable=false )
    @SequenceGenerator(name="decl_seq", sequenceName="SEQ_SUBJECTS_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="decl_seq")*/

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

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "DECL_ID")
    private Set<DeclUser> oUsers;

    @Column(name = "DECLDATE")
    @JsonSerialize(using=DateSerializer.class)
    private Date decldate;

    @Column(name = "REGDECLDATE")
    @JsonSerialize(using=DateSerializer.class)
    private Date regdecldate;

    @Column(name = "INFO")
    private String info;

    public void fillDeclId(Integer d_id)
    {
        for(DeclUser du : oUsers)
        {
            du.decl_id = d_id;
        }

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
    /*
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "DECLDOCUMENTS", joinColumns = {
            @JoinColumn(name = "DECL_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "DOC_ID", nullable = false, updatable = false) })
    Set<Doc> decldocuments;

    "DECL_ID" NUMBER NOT NULL ENABLE,
    "DECLNUMBER" NUMBER NOT NULL ENABLE,
    "DECLDATE" DATE DEFAULT SYSDATE NOT NULL ENABLE,
    "REGDECLDATE" DATE DEFAULT NULL ,
    "DECLTYPE" NUMBER DEFAULT 1 NOT NULL ENABLE,
    "URGENCY" NUMBER DEFAULT 0 NOT NULL ENABLE,
    "INFO" VARCHAR2(1000 BYTE) DEFAULT NULL,
    "CORRECT_COUNT" NUMBER DEFAULT 0 NOT NULL ENABLE,*/

}
