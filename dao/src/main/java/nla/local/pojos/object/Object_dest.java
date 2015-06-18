package nla.local.pojos.object;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import nla.local.pojos.address.Address_dest;
import nla.local.pojos.operations.Operation;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by belonovich on 09.04.2015.
 */
@Entity
@Table(name="V_OBJECT")
public class Object_dest extends Object  {

    @Id
    @Column(name="OBJECT_ID", unique=true, nullable=false )
    private Long obj_id;

    //@ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn( name = "OOPER_ID")
    @Column(name = "OOPER_ID")
    private Integer ooper;

    @Column( name = "CONSERV")
    private Integer conserv;        // 0  не законсерв, 1 законсерв

    @Column( name = "REG_TYPE")
    private Integer reg_type;       // 1 обычный,  0 предварительный

    @Column( name = "STATUS")
    private Integer status;         // 0 - не актуальн, 1 актуальн

    @Column( name = "BOUND_ID")
    private Integer bound_id;

    @Column (name = "OBJECT_ID_INV")
    private Long obj_dest_id;

    @Column( name = "ADDRESS_ID")
     private Long address_id;

    @Transient
    @JsonSerialize
    private Long adr_num;

    @Transient
    @JsonSerialize
    private Address_dest address_dest;


    public Long getAdr_num() {
        return adr_num;
    }

    public void setAdr_num(Long adr_num) {
        this.adr_num = adr_num;
    }

    public Long getObj_dest_id() {
        return obj_dest_id;
    }

    public void setObj_dest_id(Long obj_dest_id) {
        this.obj_dest_id = obj_dest_id;
    }

    public Long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Long address_id) {
        this.address_id = address_id;
    }

    public Address_dest getAddress_dest() {
        return address_dest;
    }

    public void setAddress_dest(Address_dest address_dest) {
        this.address_dest = address_dest;
    }

    public Long getObj_id_inv() {
        return obj_dest_id;
    }

    public void setObj_id_inv(Long obj_id_inv) {
        this.obj_dest_id = obj_id_inv;
    }

    public Long getObj_id() {
        return obj_id;
    }

    public void setObj_id(Long obj_id) {
        this.obj_id = obj_id;
    }

    public Integer getConserv() {
        return conserv;
    }

    public void setConserv(Integer conserv) {
        this.conserv = conserv;
    }

    public Integer getReg_type() {
        return reg_type;
    }

    public void setReg_type(Integer reg_type) {
        this.reg_type = reg_type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBound_id() {
        return bound_id;
    }

    public void setBound_id(Integer bound_id) {
        this.bound_id = bound_id;
    }

    public Integer getOoper() {
        return ooper;
    }

    public void setOoper(Integer ooper) {
        this.ooper = ooper;
    }

}
