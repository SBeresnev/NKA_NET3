package nla.local.pojos.operations;

import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.subjects.Person;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
/**
 * Created by belonovich on 08.04.2015.
 */
@Entity
@Table(name="OPERATIONS", schema = "NKA_NET3_DEV")
public class Operation {

    @Id
    @Column(name="OOPER_ID", unique=true, nullable=false )
    @SequenceGenerator(name="operation_seq", sequenceName="SEQ_OPERATIONS_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="operation_seq")
    private Integer ooperId;

    @Column(name = "DECL_ID")
    private Integer declId;

    /*@ManyToOne
    @JoinColumn(name = "OBJECT_ID")
    private Objects objectId;*/

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column= @JoinColumn(name = "OPER_TYPE", nullable = false, referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula(formula=@JoinFormula(value= CatalogConstants.OPERATION__TYP, referencedColumnName="ANALYTIC_TYPE"))})
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

    @Column( name = "STATUS", nullable = false)
    private Integer status;


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

    public Integer getDeclId() {
        return declId;
    }

    public void setDeclId(Integer declId) {
        this.declId = declId;
    }


    /* public nla.local.pojos.object.Object getObjectId() {
        return objectId;
    }

    public void setObjectId(nla.local.pojos.object.Object objectId) {
        this.objectId = objectId;
    } */
}
