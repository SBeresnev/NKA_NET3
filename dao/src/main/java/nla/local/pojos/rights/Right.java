package nla.local.pojos.rights;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by beresnev on 22.06.2015.
 */

@Entity
@Table( name = "V_RIGHT")
public class Right {

    @Id
    @Column(name="RIGHT_ID", unique=true, nullable=false )
    @SequenceGenerator(name="right_seq", sequenceName="SEQ_RIGHT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO ,generator="right_seq")
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
            @JoinColumnOrFormula(column=@JoinColumn(name = "RIGHT_CONUNT_TYPE", nullable = false, referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula( formula=@JoinFormula(value= CatalogConstants.RIGHT_COUNT_TYPE,referencedColumnName="ANALYTIC_TYPE")
            )
    })
    private CatalogItem right_count_type;


    @Column(name = "OBJECT_ENTITY_ID")
    private Integer object_entity_id;               //  object refrence


    @OneToOne
    @JoinColumn( name = "RIGHT_ID")
    private Right right_entity_id;                   //  right refrence


    @OneToMany
    @JoinColumn(name = "RIGHT_OWNER_ID")
    private Set<RightOwners> right_owner_lst;


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

    public Integer getObject_entity_id() {
        return object_entity_id;
    }

    public void setObject_entity_id(Integer object_entity_id) {
        this.object_entity_id = object_entity_id;
    }

    public Right getRight_entity_id() {
        return right_entity_id;
    }

    public void setRight_entity_id(Right right_entity_id) {
        this.right_entity_id = right_entity_id;
    }

    public Set<RightOwners> getRight_owner_lst() {
        return right_owner_lst;
    }

    public void setRight_owner_lst(Set<RightOwners> right_owner_lst) {
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
