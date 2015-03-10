package nla.local.pojos.orders;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import nla.local.pojos.subjects.OPerson;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by beresnev on 09.03.2015.
 */
@Entity
@Table(name = "DECLUSER")
public class DeclUser {


    @Id
    @Column(name="decluser_id", unique=true, nullable=false )
    @SequenceGenerator(name="decluser_seq", sequenceName="SEQ_DECLUSER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="decluser_seq")
    public Integer decluser_id;

    @Column(name = "DECL_ID")
    public Integer  decl_id;

    @OneToMany
    @JoinColumn(name="DECL_ID")
    public Set<Decl> decls ;

    @OneToOne
    @JoinColumn(name = "SUBJECT_ID")
    public OPerson oPerson;

    @Column(name = "DATE_IN")
    @JsonSerialize(using=DateSerializer.class)
    public Date date_in;

    @Column(name = "DATE_OUT")
    @JsonSerialize(using=DateSerializer.class)
    public Date date_out;

}
