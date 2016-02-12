package nla.local.pojos.rights;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import nla.local.pojos.object.Object_dest;
import nla.local.pojos.operations.Operation;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * Created by beresnev on 22.06.2015.
 */

@Entity
@Table( name = "V_RIGHT")
public class Right implements Serializable {


    @Id
    @GeneratedValue(generator="seq_id")
    @GenericGenerator(
            name="seq_id",
            strategy = "nla.local.util.CodeGenerator",
            parameters = @org.hibernate.annotations.Parameter(name = "seq_name", value = "SEQ_RIGHT_ID"))
    @Column(name="RIGHT_ID", unique=true, nullable=false )
    private Long right_id;

    @Column(name = "RIGHT_TYPE")
    private Integer right_type;

    @Column(name = "RIGHT_ENTITY_TYPE")
    private Integer right_entity_type;

    @Column(name = "RIGHT_COUNT_TYPE")
    private Integer right_count_type;

    @Column(name = "LIMIT_RIGHT_ID",nullable = true)
    private Long limit_right;                    //  right refrence указатель на ограниченное право

    @Column(name = "BOUND_ID")
    private Integer bound_id;

    @Column(name = "COMMENTS")
    private String comments;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn( name = "OOPER_ID")
    private Operation ooper;

    @Column(name = "IS_NEEDED")
    private Integer is_needed;

    @Column(name = "STATUS")
    private Integer status;

    @Column(name = "BEGIN_DATE")
    @JsonSerialize(using=DateSerializer.class)
    private Date begin_date;

    @Column(name = "END_DATE")
    @JsonSerialize(using=DateSerializer.class)
    private Date end_date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name = "OBJECT_ENTITY_ID",updatable = false, nullable = false)
    private Object_dest bindedObj;

    @OneToMany( mappedBy = "right_id", fetch = FetchType.EAGER , cascade = CascadeType.ALL )
    private List<RightOwner> rightOwners;

    /*
    @Transient
    @JsonProperty("limit_rights")
    private List<Right> limit_rights;


    public List<Right> getLimit_rights() {
        return limit_rights;
    }

    public void setLimit_rights(List<Right> limit_rights) {
        this.limit_rights = limit_rights;
    }
    */

    public Long getLimit_right() {
        return limit_right;
    }

    public void setLimit_right(Long limit_right) {
        this.limit_right = limit_right;
    }

    public List<RightOwner> getRightOwners() {
        return rightOwners;
    }

    public void setRightOwners(List<RightOwner> rightOwners) {
        this.rightOwners = rightOwners;
    }

    public Object_dest getBindedObj() {
        return bindedObj;
    }

    public void setBindedObj(Object_dest bindedObj) {

        this.bindedObj = bindedObj;

    }

    public Integer getRight_count_type() {
        return right_count_type;
    }

    public void setRight_count_type(Integer right_count_type) {
        this.right_count_type = right_count_type;
    }

    public Long getRight_id() {
        return right_id;
    }

    public void setRight_id(Long right_id) {
        this.right_id = right_id;
    }

    public Integer getRight_type() {
        return right_type;
    }

    public void setRight_type(Integer right_type) {
        this.right_type = right_type;
    }

    public Integer getRight_entity_type() {
        return right_entity_type;
    }

    public void setRight_entity_type(Integer right_entyty_type) {
        this.right_entity_type = right_entyty_type;
    }

    public Integer getBound_id() {
        return bound_id;
    }

    public void setBound_id(Integer bound_id) {
        this.bound_id = bound_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Operation getOoper() {
        return ooper;
    }

    public void setOoper(Operation ooper) {
        this.ooper = ooper;
    }

    public Integer getIs_needed() {
        return is_needed;
    }

    public void setIs_needed(Integer is_needed) {
        this.is_needed = is_needed;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(Date begin_date) {
        this.begin_date = begin_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

}
