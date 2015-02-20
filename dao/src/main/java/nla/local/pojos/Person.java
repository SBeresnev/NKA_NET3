package nla.local.pojos;
import nla.local.pojos.dict.SubjectType;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Serega on 25.09.2014.
 */

@Entity
@Table(name="SUBJECTS")
@BatchSize(size = 20)
@SecondaryTable(name = "ANALYTICTYPES")
@Inheritance(strategy= InheritanceType.JOINED)
@Where(clause = "ANALYTIC_TYPE=110")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="SUBJECT_ID", unique=true, nullable=false )
    @SequenceGenerator(name="person_seq", sequenceName="SEQ_SUBJECTS_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="person_seq")
    public Integer subjectId;

    @Column(name = "REESTRDATAID", updatable = false, insertable = false)
    public Integer reestrdataID;

    @Column(name = "IS_OWNER")
    public Integer isOwner;

    @Column(name = "ANALYTIC_TYPE", insertable = false , updatable = false)
    public Integer analytic_type;

    @ManyToOne
    @JoinColumns( {
            @JoinColumn(name = "SUBJECT_TYPE", nullable = false, referencedColumnName = "ANALYTIC_CODE"),
            @JoinColumn(name = "ANALYTIC_TYPE", nullable = false, referencedColumnName = "ANALYTIC_TYPE")})
    public SubjectType subjectType;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        if (subjectId != null ? !subjectId.equals(person.reestrdataID) : person.subjectId != null) return false;
        if (reestrdataID != null ? !reestrdataID.equals(person.reestrdataID) : person.reestrdataID != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = subjectId != null ? subjectId.hashCode() : 0;
        result = 31 * result + (reestrdataID != null ? reestrdataID.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person : id: " + subjectId + " Reestrdate: " + reestrdataID;
    }


    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getReestrdataID() {
        return reestrdataID;
    }

    public void setReestrdataID(Integer reestrdataID) {
        this.reestrdataID = reestrdataID;
    }

    public Integer getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(Integer isOwner) {
        this.isOwner = isOwner;
    }

    public SubjectType getSubjectType() {

        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {

        this.subjectType = subjectType;
    }
}