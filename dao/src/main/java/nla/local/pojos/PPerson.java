package nla.local.pojos;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="SUBJECTSDATA")
@PrimaryKeyJoinColumn(name="SUBJECT_ID" )
public class PPerson extends Person implements Serializable{

    private static final long serialVersionUID = 2L;

    @Column(name = "SUBJECT_DATA_ID")
    public Integer subjectdataid;

    @Column(name = "FIRSTNAME")
    public String firstname;

    @Column(name = "SURNAME")
    public String surname;

    @Column(name = "FATHERNAME")
    public String fathername;

    @Column(name = "BOTH_REG_DATE")
    public Date bothRegDate;

    @Column(name = "PERSONAL_NUMBER")
    public String personalNumber;

    @Column(name = "DATESTART")
    public Date datestart;

    @Column(name = "ACTUAL")
    public Integer actual;

    @Column(name = "PREV_ADDRESS")
    public String address;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ADDRESS_ID")
    public Address address;*/


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
