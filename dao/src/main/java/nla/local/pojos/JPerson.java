package nla.local.pojos;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import nla.local.pojos.dict.Dict;
import nla.local.pojos.dict.OrgKod;
import org.hibernate.annotations.Where;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*
juridical person
*/

@Entity
@Table(name="V_SUBJECTSDATA")
@SecondaryTable(name = "ANALYTICTYPES")
@PrimaryKeyJoinColumn(name="SUBJECT_ID")
@Where(clause = "ANALYTIC_TYPE=220")
public class JPerson extends Person implements Serializable{

    private static final long serialVersionUID = 3L;

    @Column(name= "SUBJECT_DATA_ID")
    public Integer subjectdataid;

    @Column(name = "FULLNAME", nullable = false)
    public String fullname;

    @Column(name = "SHORTNAME")
    public String shortname;

    @Column(name = "REG_NUMBER", nullable = false)
    public String regNumber;

    @Column(name = "UNP")
    public String unp;

    @Column(name = "ANALYTIC_TYPE", insertable = false , updatable = false)
    public Integer analytic_type;

    @ManyToOne
    @JoinColumns( {
            @JoinColumn(name = "ORG_RIGHT_FORM", nullable = false, referencedColumnName = "ANALYTIC_CODE"),
            @JoinColumn(name = "ANALYTIC_TYPE", nullable = false, referencedColumnName = "ANALYTIC_TYPE")})
    public OrgKod orgRightForm;

    @Column(name = "BOTH_REG_DATE")
    @JsonSerialize(using= DateSerializer.class)
    public Date bothRegDate;

    @Column(name = "REMARK")
    public String remark;

    @Column(name = "PREV_ADDRESS")
    public String address;

    /*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ADDRESS_ID")
    public Address address;*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JPerson)) return false;
        JPerson person = (JPerson) o;
        if (subjectId != null ? !subjectId.equals(person.subjectId) : person.subjectId != null) return false;
        if (regNumber != null ? !regNumber.equals(person.regNumber) : person.regNumber != null) return false;
        if (unp != null ? !unp.equals(person.unp) : person.unp != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = regNumber != null ? regNumber.hashCode() : 0;
        result = 31 * result + (unp != null ? unp.hashCode() : 0);
        result = 31 * result + (subjectId != null ? subjectId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SubjectId: " +subjectId+" Fullname: " + fullname + " RegNumber: " + regNumber + " UNP: " + unp;
    }
}