package nla.local.pojos.subjects;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="V_SUBJECTSDATA_F")
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

    @Column(name = "SITIZENS")
    public Integer sitizens;

    @Column(name = "BOTH_REG_DATE")
    @JsonSerialize(using= DateSerializer.class)
    public Date bothRegDate;

    @Column(name = "PERSONAL_NUMBER")
    public String personalNumber;

    @Column(name = "PREV_ADDRESS")
    public String address;

    @Column(name = "REMARK")
    public String remark;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PPerson)) return false;
        PPerson person = (PPerson) o;

        if (subjectId != null ? !subjectId.equals(person.personalNumber) : person.subjectId != null) return false;
        if (personalNumber != null ? !personalNumber.equals(person.personalNumber) : person.personalNumber != null) return false;
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
        return " Subject Id: " + subjectId +" PersonalNumber: " + personalNumber;
    }

}