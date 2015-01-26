package nla.local.pojos;

import javax.persistence.*;

/**
 * Created by Serega on 26.09.2014.
 */
@Entity
@Table(name="OFFICIALUSERS" )
@PrimaryKeyJoinColumn(name="SUBJECT_ID")
public class User extends Person{

    private static final long serialVersionUID = 4L;

    private Integer ID;

    private String Username;

    private String Password;

    @Column(name = "DATESTART")
    public String orgname;

    @Column(name = "ORG_KOD")
    public int orgKod;

    @Column(name = "POST")
    public int post;

}
