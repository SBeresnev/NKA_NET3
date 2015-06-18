package nla.local.pojos.orders;


import nla.local.pojos.subjects.Person;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * Created by belonovich on 23.03.2015.
 */
@Entity
@Table( name = "DECLARANTS", schema = "NKA_NET3_DEV")
public class Declarant {

    @Id
    @SequenceGenerator(name="declarant_seq", sequenceName="SEQ_DECLARANT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO ,generator="declarant_seq")
    @Column(name="DECLARANT_ID", unique=true, nullable=false )
    private  Integer declarantId;

    @Column(name = "DECL_ID")
    private Integer decl_id;

    @OneToOne
    @JoinColumn(name = "SUBJECT_ID")
    private Person person;

    @OneToOne
    @JoinColumn( name = "PARENT_ID")
    //@Cascade(CascadeType.SAVE_UPDATE)
    private Declarant parentPerson;

    @Column(name = "DECLREPR_TYPE")
    private Integer declrepr_type = 1;


    public Integer getDeclarantId() {
        return declarantId;
    }

    public void setDeclarantId(Integer declarantId) {
        this.declarantId = declarantId;
    }

    public Integer getDecl_id() {
        return decl_id;
    }

    public void setDecl_id(Integer decl_id) {
        this.decl_id = decl_id;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    public Declarant getParentPerson() {
        return parentPerson;
    }

    public void setParentPerson(Declarant parentPerson) {
        this.parentPerson = parentPerson;
    }


    public Integer getDeclrepr_type() {
        return declrepr_type;
    }

    public void setDeclrepr_type(Integer declrepr_type) {
        this.declrepr_type = declrepr_type;
    }


}
