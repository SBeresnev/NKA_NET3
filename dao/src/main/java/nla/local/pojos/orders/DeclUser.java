package nla.local.pojos.orders;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer decluser_id;

    @ManyToMany
    @JoinColumn(name = "DECL_ID")
    Set<Decl> decls ;

    @OneToOne
    @JoinColumn(name = "SUBJECT_ID")
    OPerson oPerson;

    @Column(name = "DATE_IN")
    Date date_in;

    @Column(name = "DATE_OUT")
    Date date_out;

}
