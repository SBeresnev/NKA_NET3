package nla.local.pojos;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
private person
get from SUBJECTSDATA
*/

@Entity
@Table(name="SUBJECTSDATA")
//@PrimaryKeyJoinColumn(name="SUBJECT_ID" )
public class PPerson extends Person implements Serializable{

    private static final long serialVersionUID = 2L;

    @Id
    @Column(name="SUBJECT_DATA_ID", unique=true, nullable=false )
    @SequenceGenerator(name="p_seq", sequenceName="SEQ_SUBJECTSDATA_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="p_seq")
    public Integer subjectdataid;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name="SUBJECT_ID", nullable=false)
    public Person person;

    @Column(name = "FIRSTNAME")
    public String firstname;

    @Column(name = "SURNAME")
    public String surname;

    @Column(name = "FATHERNAME")
    public String fathername;

    @Column(name = "BOTH_REG_DATE")
    @JsonSerialize(using= DateSerializer.class)
    public Date bothRegDate;

    @Column(name = "PERSONAL_NUMBER")
    public String personalNumber;

    @JsonSerialize(using=DateSerializer.class)
    @Column(name = "DATESTART")
    public Date datestart;

    @Column(name = "ACTUAL")
    public Integer actual;

    @Column(name = "PREV_ADDRESS")
    public String address;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ADDRESS_ID")
    public Address address;
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PPerson)) return false;

        PPerson person = (PPerson) o;

        if (personalNumber != null ? !personalNumber.equals(person.personalNumber) : person.personalNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = personalNumber != null ? personalNumber.hashCode() : 0;

        return result;
    }

    @Override
    public String toString() {
        return " PersonalNumber: " + personalNumber;
    }
}
