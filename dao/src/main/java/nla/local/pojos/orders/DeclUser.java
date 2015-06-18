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
@Table(name = "V_DECLUSER")
public class DeclUser {
    @Id
    @Column(name = "decluser_id", unique = true, nullable = false)
    @SequenceGenerator(name = "decluser_seq", sequenceName = "SEQ_DECLUSER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "decluser_seq")
    private Integer decluser_id;

    @Column(name = "DECL_ID")
    private Integer decl_id;

     @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
     @JoinColumn(name = "DECL_ID")
     private Set<Decl> decls;

    @OneToOne
    @JoinColumn(name = "SUBJECT_ID")
    private OPerson oPerson;



    @Column(name = "DATE_IN")
    @JsonSerialize(using = DateSerializer.class)
    private Date date_in;

    @Column(name = "DATE_OUT")
    @JsonSerialize(using = DateSerializer.class)
    private Date date_out;


    public Integer getDecluser_id() {
        return decluser_id;
    }

    public void setDecluser_id(Integer decluser_id) {
        this.decluser_id = decluser_id;
    }

    public Integer getDecl_id() {
        return decl_id;
    }

    public void setDecl_id(Integer decl_id) {
        this.decl_id = decl_id;
    }


    public Set<Decl> getDecls() {
        return decls;
    }

    public void setDecls(Set<Decl> decls) {
        this.decls = decls;
    }


    public OPerson getoPerson() {
        return oPerson;
    }

    public void setoPerson(OPerson oPerson) {
        this.oPerson = oPerson;
    }

    public Date getDate_in() {
        return date_in;
    }

    public void setDate_in(Date date_in) {
        this.date_in = date_in;
    }

    public Date getDate_out() {
        return date_out;
    }

    public void setDate_out(Date date_out) {
        this.date_out = date_out;
    }

}
