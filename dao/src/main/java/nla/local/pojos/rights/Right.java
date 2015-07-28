package nla.local.pojos.rights;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.object.Object_dest;
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
    private Integer right_id;


    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column=@JoinColumn(name = "RIGHT_TYPE", nullable = false, referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula( formula=@JoinFormula(value= CatalogConstants.RIGHT_TYPE,referencedColumnName="ANALYTIC_TYPE")
            )
    })
    private CatalogItem right_type;


    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column=@JoinColumn(name = "RIGHT_ENTITY_TYPE", nullable = false, referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula( formula=@JoinFormula(value= CatalogConstants.RIGHT_ENTYTY_TYPE,referencedColumnName="ANALYTIC_TYPE")
            )
    })
    private CatalogItem right_entyty_type;


    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column=@JoinColumn(name = "RIGHT_COUNT_TYPE", nullable = false, referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula( formula=@JoinFormula(value= CatalogConstants.RIGHT_COUNT_TYPE,referencedColumnName="ANALYTIC_TYPE")
            )
    })
    private CatalogItem right_count_type;


    @Column(name = "OBJECT_ENTITY_ID")
    private Long object_entity_id;               //  object refrence


    @OneToMany(mappedBy = "right", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<RightOwner> right_owner_lst;


    @Column(name = "BOUND_ID")
    private Integer bound_id;


    @Column(name = "COMMENTS")
    private String comments;


    @Column(name = "OOPER_ID")
    private Integer ooper_id;


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

    @Transient
    private Object_dest bindedObj;


   /*
    public void fillRightId(Integer r_id) {

        if( right_owner_lst != null ) {

            for (RightOwner row : right_owner_lst) {

                row.setRight_id(r_id);

            }

        }

    } */

    public Object_dest getBindedObj() {
        return bindedObj;
    }

    public void setBindedObj(Object_dest bindedObj) {

        this.setObject_entity_id(bindedObj.getObj_id());

        this.bindedObj = bindedObj;

    }

    public CatalogItem getRight_count_type() {
        return right_count_type;
    }

    public void setRight_count_type(CatalogItem right_count_type) {
        this.right_count_type = right_count_type;
    }

    public Integer getRight_id() {
        return right_id;
    }

    public void setRight_id(Integer right_id) {
        this.right_id = right_id;
    }

    public CatalogItem getRight_type() {
        return right_type;
    }

    public void setRight_type(CatalogItem right_type) {
        this.right_type = right_type;
    }

    public CatalogItem getRight_entyty_type() {
        return right_entyty_type;
    }

    public void setRight_entyty_type(CatalogItem right_entyty_type) {
        this.right_entyty_type = right_entyty_type;
    }

    public Long getObject_entity_id() {
        return object_entity_id;
    }

    public void setObject_entity_id(Long object_entity_id) {
        this.object_entity_id = object_entity_id;
    }

    public Set<RightOwner> getRight_owner_lst() {
        return right_owner_lst;
    }

    public void setRight_owner_lst(Set<RightOwner> right_owner_lst) {
        this.right_owner_lst = right_owner_lst;
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

    public Integer getOoper_id() {
        return ooper_id;
    }

    public void setOoper_id(Integer ooper_id) {
        this.ooper_id = ooper_id;
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
