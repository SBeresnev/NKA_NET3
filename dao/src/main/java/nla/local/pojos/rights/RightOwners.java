package nla.local.pojos.rights;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import nla.local.pojos.subjects.Person;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by beresnev on 23.06.2015.
 */

@Entity
@Table( name = "RIGHTOWNERS")
public class RightOwners {

    @Column(name = "RIGHT_OWNER_ID")
    private Integer right_owner_id;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private Person owner_id;

    @Column(name = "RIGHT_ID")
    private Integer right_id;

    @Column(name="NUMERATOR_PART")
    private Integer numerator_part;

    @Column(name="DENOMINATOR_PART")
    private Integer denominator_part;

    @Column(name = "DATE_IN")
    @JsonSerialize(using=DateSerializer.class)
    private Date date_in;

    @Column(name = "DATE_OUT")
    @JsonSerialize(using=DateSerializer.class)
    private Date date_out;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "OOPER_ID")
    private Integer ooper_id;



    public Integer getRight_owner_id() {
        return right_owner_id;
    }

    public void setRight_owner_id(Integer right_owner_id) {
        this.right_owner_id = right_owner_id;
    }

    public Person getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Person owner_id) {
        this.owner_id = owner_id;
    }

    public Integer getRight_id() {
        return right_id;
    }

    public void setRight_id(Integer right_id) {
        this.right_id = right_id;
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

    public Integer getOoper_id() {
        return ooper_id;
    }

    public void setOoper_id(Integer ooper_id) {
        this.ooper_id = ooper_id;
    }


}