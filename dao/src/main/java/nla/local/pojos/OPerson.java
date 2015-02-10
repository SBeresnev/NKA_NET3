package nla.local.pojos;

import nla.local.pojos.dict.TorStructDict;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * Created by Serega on 26.09.2014.
 * officialuser (представители)
 */

@Entity
@Table(name="OFFICIALUSERS" )
@PrimaryKeyJoinColumn(name="SUBJECT_ID")
public class OPerson extends Person implements Serializable{

    private static final long serialVersionUID = 4L;

    @Column(name = "SURNAME")
    public  String surname;

    @Column(name = "NAME")
    public String firstname;

    @Column(name = "FATHERNAME")
    public String fathername;

    @Column(name = "POST")
    public String post;

    @Column(name = "ORGNAME")
    public String orgname;

    @Column(name = "USER_NUM")
    public Integer user_num;

    @Column(name = "DATE_OUT")
    public Date date_out;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORG_KOD", referencedColumnName = "ANALYTIC_CODE")
    public TorStructDict org_kod;

    @Column(name = "PREV_ADDRESS")
    public String prev_address;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof OPerson)) return false;
        OPerson person = (OPerson) o;
        if (user_num != null ? !user_num.equals(person.user_num) : person.user_num != null) return false;
        if (org_kod != null ? !org_kod.equals(person.org_kod) : person.org_kod != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = user_num != null ? user_num.hashCode() : 0;
        result = 31 * result + (org_kod != null ? org_kod.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {

        return "user_num: " + user_num +  " org_kod:" + org_kod ;

    }

}


