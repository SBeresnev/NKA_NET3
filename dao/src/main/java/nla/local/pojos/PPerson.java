package nla.local.pojos;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="SUBJECTSDATA" , schema = "NKA_NET3_DEV")
@PrimaryKeyJoinColumn(name="SUBJECT_ID" )
public class PPerson extends Person{

    private static final long serialVersionUID = 2L;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ADDRESS_ID")
    public Address address;

}
