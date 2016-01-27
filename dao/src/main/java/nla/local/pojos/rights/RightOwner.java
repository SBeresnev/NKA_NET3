package nla.local.pojos.rights;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import nla.local.pojos.operations.Operation;
import nla.local.pojos.subjects.Person;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by beresnev on 23.06.2015.
 */

@Entity
@Table( name = "V_RIGHTOWNERS", schema = "NKA_NET3_DEV")
//@JsonIdentityInfo(scope=RightOwner.class, property="@id", generator=ObjectIdGenerators.PropertyGenerator.class)
public class RightOwner implements Serializable {

    @Id
    @Column(name = "RIGHT_OWNER_ID")
    @SequenceGenerator(name="right_seq", sequenceName="SEQ_RIGHT_OWNERS")
    @GeneratedValue(strategy = GenerationType.AUTO ,generator="right_seq")
    private Long right_owner_id;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID", nullable = false)
    private Person owner;

    //@ManyToOne(cascade = javax.persistence.CascadeType.ALL)
    //@JoinColumn(name = "RIGHT_ID", nullable = false)

    @JsonIgnore
    @Transient
     private Right right;

    @Column(name="NUMERATOR_PART")
    private Integer numerator_part;

    @Column(name="DENOMINATOR_PART")
    private Integer denominator_part;

    @Column(name="PARENT_OWNER")
    private Long parent_owner;

    @Column(name = "DATE_IN")
    @JsonSerialize(using=DateSerializer.class)
    private Date date_in;

    @Column(name = "DATE_OUT")
    @JsonSerialize(using=DateSerializer.class)
    private Date date_out;

    @Column(name = "STATUS")
    private Integer status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn( name = "OOPER_ID")
    private Operation ooper;

    @Column(name = "RIGHT_ID")
    private Long right_id;

    public Right getRight() {
        return right;
    }

    public void setRight(Right right) {
        this.right = right;
    }

    public Long getRight_id() {
        return right_id;
    }

    public void setRight_id(Long right_id) {
        this.right_id = right_id;
    }

    public Long getRight_owner_id() {
        return right_owner_id;
    }

    public void setRight_owner_id(Long right_owner_id) {
        this.right_owner_id = right_owner_id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Long getParent_owner() {
        return parent_owner;
    }

    public void setParent_owner(Long parent_owner) {
        this.parent_owner = parent_owner;
    }

    public Integer getNumerator_part() {
        return numerator_part;
    }

    public void setNumerator_part(Integer numerator_part) {
        this.numerator_part = numerator_part;
    }

    public Integer getDenominator_part() {
        return denominator_part;
    }

    public void setDenominator_part(Integer denominator_part) {
        this.denominator_part = denominator_part;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Operation getOoper() {
        return ooper;
    }

    public void setOoper(Operation ooper_id) {

        this.ooper = ooper_id;
    }


}
