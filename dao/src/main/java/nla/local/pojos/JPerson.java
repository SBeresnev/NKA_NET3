package nla.local.pojos;

import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;



@Entity
@Table(name="SUBJECTSDATA", schema = "NKA_NET3_DEV")
@PrimaryKeyJoinColumn(name="SUBJECT_ID")
public class JPerson extends Person{
    private static final long serialVersionUID = 3L;

    @Column(name = "FULLNAME")
    public String fullname;

    @Column(name = "SHORTNAME")
    public String shortname;

    @Column(name = "REG_NUMBER")
    public String regNumber;

    @Column(name = "UNP")
    public String unp;

    @Column(name = "ORG_RIGHT_FORM" )
    public Integer orgRightForm;

    @Column(name = "BOTH_REG_DATE")
    public Date bothRegDate;

    @Column(name = "REMARK")
    public String remark;

    @Column(name = "ACTUAL")
    public Integer actual;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ADDRESS_ID")
    public Address address;

    public void setSubjectType(Integer subjecttype)
    {
        super.subjectType=subjecttype;
    }


}