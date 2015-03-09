package nla.local.pojos.orders;


import nla.local.pojos.subjects.Person;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by beresnev on 09.03.2015.
 */
@Entity
@Table(name = "Decl")
public class Decl implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer decl_id;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "DECLARANTS", joinColumns = {
            @JoinColumn(name = "DECL_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "SUBJECT_ID", nullable = false, updatable = false) })
    private Set<Person> declarants;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "DECL_ID")
    private Set<DeclUser> oUsers;

    @Column(name = "DECLDATE")
    private Date decldate;

    @Column(name = "REGDECLDATE")
    private Date regdecldate;

    @Column(name = "INFO")
    private String info;


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

    public Set<DeclUser> getoUsers() {
        return oUsers;
    }

    public void setoUsers(Set<DeclUser> oUsers) {
        this.oUsers = oUsers;
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
