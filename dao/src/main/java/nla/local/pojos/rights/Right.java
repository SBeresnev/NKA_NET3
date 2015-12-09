package nla.local.pojos.rights;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.object.Object_dest;
import nla.local.pojos.operations.Operation;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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


    /*@ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column=@JoinColumn(name = "RIGHT_TYPE", nullable = false, referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula( formula=@JoinFormula(value= CatalogConstants.RIGHT_TYPE,referencedColumnName="ANALYTIC_TYPE")
            )
    })*/
    @Column(name = "RIGHT_TYPE")
    private Integer right_type;


    /*@ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column=@JoinColumn(name = "RIGHT_ENTITY_TYPE", nullable = false, referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula( formula=@JoinFormula(value= CatalogConstants.RIGHT_ENTYTY_TYPE,referencedColumnName="ANALYTIC_TYPE")
            )
    })*/
    @Column(name = "RIGHT_ENTITY_TYPE")
    private Integer right_entyty_type;


    /*@ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column=@JoinColumn(name = "RIGHT_COUNT_TYPE", nullable = false, referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula( formula=@JoinFormula(value= CatalogConstants.RIGHT_COUNT_TYPE,referencedColumnName="ANALYTIC_TYPE")
            )
    })*/
    @Column(name = "RIGHT_COUNT_TYPE")
    private Integer right_count_type;


    //@Column(name = "OBJECT_ENTITY_ID")
    //private Long object_entity_id;               //  object refrence


    @Column(name = "LIMIT_RIGHT_ID",nullable = true)
    private Long limit_righ;                    //  right refrence указатель на ограниченное право


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



    public Long getLimit_righ() {
        return limit_righ;
    }

    public void setLimit_righ(Long limit_righ) {
        this.limit_righ = limit_righ;
    }

    public Object_dest getBindedObj() {
        return bindedObj;
    }

    public void setBindedObj(Object_dest bindedObj) {

       // this.setObject_entity_id(bindedObj.getObj_id());

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

    public Integer getRight_entyty_type() {
        return right_entyty_type;
    }

    public void setRight_entyty_type(Integer right_entyty_type) {
        this.right_entyty_type = right_entyty_type;
    }

    /*public Long getObject_entity_id() {
        return object_entity_id;
    }

    public void setObject_entity_id(Long object_entity_id) {
        this.object_entity_id = object_entity_id;
    }*/

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
