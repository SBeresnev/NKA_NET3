package nla.local.pojos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
juridical person
*/

@Entity
@Table(name="SUBJECTSDATA")
//@PrimaryKeyJoinColumn(name="SUBJECT_ID" )
public class JPerson extends Person implements Serializable{
    private static final long serialVersionUID = 3L;

    @Id
    @Column(name="SUBJECT_DATA_ID", unique=true, nullable=false )
    @SequenceGenerator(name="j_seq", sequenceName="SEQ_SUBJECTSDATA_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="j_seq")
    public Integer subjectdataid;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="SUBJECT_ID", nullable=false)
    public Person person;

    @Column(name = "FULLNAME", nullable = false)
    public String fullname;

    @Column(name = "SHORTNAME")
    public String shortname;

    @Column(name = "REG_NUMBER", nullable = false)
    public String regNumber;

    @Column(name = "UNP")
    public String unp;

    @Column(name = "ORG_RIGHT_FORM" )
    public Integer orgRightForm;

    @JsonSerialize(using=DateSerializer.class)
    @Column(name = "BOTH_REG_DATE")
    //@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    public Date bothRegDate;

    @Column(name = "REMARK")
    public String remark;

    @Column(name = "ACTUAL",  columnDefinition = "number default 1")
    public Integer actual;

    @Column(name = "PREV_ADDRESS")
     public String address;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ADDRESS_ID")
    public Address address;*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JPerson)) return false;

        JPerson person = (JPerson) o;

        if (regNumber != null ? !regNumber.equals(person.regNumber) : person.regNumber != null) return false;
        if (unp != null ? !unp.equals(person.unp) : person.unp != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = regNumber != null ? regNumber.hashCode() : 0;
        result = 31 * result + (unp != null ? unp.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {

        return "Fullname" + fullname + "RegNumber: " + regNumber + "UNP: " + unp;
    }

}