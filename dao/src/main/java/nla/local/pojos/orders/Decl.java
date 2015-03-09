package nla.local.pojos.orders;


import nla.local.pojos.subjects.Person;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by beresnev on 09.03.2015.
 */
@Entity
@Table(name = "Decl")
public class Decl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer decl_id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "DECLARANTS", joinColumns = {
            @JoinColumn(name = "DECL_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "SUBJECT_ID", nullable = false, updatable = false) })
    Set<Person> declarants;

    @ManyToMany
    @JoinColumn(name = "DECL_ID")
    Set<DeclUser> oUsers;

    @Column(name = "DECLDATE")
    Date decldate;

    @Column(name = "REGDECLDATE")
    Date regdecldate;

    @Column(name = "INFO")
    String info;

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
