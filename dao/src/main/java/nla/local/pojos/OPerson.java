package nla.local.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Serega on 26.09.2014.
 * officialuser
 */
@Entity
@Table(name="OFFICIALUSERS" )
@PrimaryKeyJoinColumn(name="SUBJECT_ID")
public class OPerson extends Person implements Serializable{

    private static final long serialVersionUID = 4L;

    @Column(name = "SURNAME")
    public  String surname;

    @Column(name = "NAME")
    public String name;

    @Column(name = "FATHERNAME")
    public String fathername;

    @Column(name = "POST")
    public String post;

    @Column(name = "ORGNAME")
    public String orgname;

    @Column(name = "USER_NUM")
    public String user_num;

    @Column(name = "DATE_OUT")
    public Date date_out;

    @Column(name = "ORG_KOD")
    public Integer org_kod;

    @Column(name = "BASE_USER_UID")
    public Integer base_user_uid;

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof OPerson)) return false;
        OPerson person = (OPerson) o;
        if (user_num != null ? !user_num.equals(person.user_num) : person.user_num != null) return false;
        if (base_user_uid != null ? !base_user_uid.equals(person.base_user_uid) : person.base_user_uid != null) return false;
        if (org_kod != null ? !org_kod.equals(person.org_kod) : person.org_kod != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = user_num != null ? user_num.hashCode() : 0;
        result = 31 * result + (base_user_uid != null ? base_user_uid.hashCode() : 0);
        result = 31 * result + (org_kod != null ? org_kod.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {

        return "user_num: " + user_num + " base_user_uid:" + base_user_uid + " org_kod:" ;

    }

}


