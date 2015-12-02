package nla.local.pojos.operations;

import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.subjects.Person;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by belonovich on 08.04.2015.
 */
@Entity
@Table(name="V_OPERATIONS", schema = "NKA_NET3_DEV")
public class Operation implements Serializable {


    @Id
    @Column(name="OOPER_ID", unique=true, nullable=false )
    @SequenceGenerator(name="operation_seq", sequenceName="SEQ_OPERATIONS_ID")
    @GeneratedValue(strategy = GenerationType.AUTO ,generator="operation_seq")
    private Integer ooperId;


    @Column(name = "DECL_ID")
    private Long declId;


    @Column (name ="ENTITY_TYPE")
    private Integer entytyType;


    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column= @JoinColumn(name = "OPER_TYPE", nullable = false, referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula(formula=@JoinFormula(value= CatalogConstants.OPERATION_TYP, referencedColumnName="ANALYTIC_TYPE"))})
    private CatalogItem operType;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column= @JoinColumn(name = "OPER_SUBTYPE", nullable = false, referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula(formula=@JoinFormula(value=CatalogConstants.OPERATION_SUB_TYP, referencedColumnName="ANALYTIC_TYPE"))})
    private CatalogItem operSubtype;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column= @JoinColumn(name = "REASON", nullable = false, referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula(formula=@JoinFormula(value=CatalogConstants.OPERATION_BASE, referencedColumnName="ANALYTIC_TYPE"))})
    private CatalogItem reason;

    @OneToOne
    @JoinColumn(name = "EXECUTOR", nullable = false)
    private Person executor;

    @Column( name = "REG_DATE", nullable = false)
    private Date regDate;

    @Column( name = "OPER_DATE", nullable = false)
    private Date operDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_ID_ORDER")
    private Operation parent_id_order;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PARENT_ID_HIST")
    private Operation parent_id_hist;

    @Column( name = "STATUS", nullable = false)
    private Integer status;


    public Operation getParent_id_order() {
        return parent_id_order;
    }

    public void setParent_id_order(Operation parent_id_order) {
        this.parent_id_order = parent_id_order;
    }

    public Operation getParent_id_hist() {
        return parent_id_hist;
    }


    public void setParent_id_hist(Operation parent_id_hist) {
        this.parent_id_hist = parent_id_hist;
    }

    public CatalogItem getReason() { return reason; }

    public void setReason(CatalogItem reason) { this.reason = reason; }

    public Date getOperDate() {
        return operDate;
    }

    public void setOperDate(Date operDate) {
        this.operDate = operDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Integer getOoperId() {
        return ooperId;
    }

    public void setOoperId(Integer ooperId) {
        this.ooperId = ooperId;
    }

    public CatalogItem getOperType() {
        return operType;
    }

    public void setOperType(CatalogItem operType) {
        this.operType = operType;
    }

    public CatalogItem getOperSubtype() {
        return operSubtype;
    }

    public void setOperSubtype(CatalogItem operSubtype) {
        this.operSubtype = operSubtype;
    }

    public Person getExecutor() {
        return executor;
    }

    public void setExecutor(Person executor) {
        this.executor = executor;
    }

    public Long getDeclId() {
        return declId;
    }

    public void setDeclId(Long declId) {
        this.declId = declId;
    }

    public Integer getEntytyType() {
        return entytyType;
    }

    public void setEntytyType(Integer entytyType) {
        this.entytyType = entytyType;
    }


}
