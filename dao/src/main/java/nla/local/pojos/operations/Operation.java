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
    private Long ooperId;

    @Column(name = "DECL_ID")
    private Long declId;

    @Column (name ="ENTITY_TYPE")
    private Integer entytyType;

    @Column(name = "OPER_TYPE")
    private Integer operType;       //CatalogConstants.OPERATION_TYP

    @Column(name = "OPER_SUBTYPE")
    private Integer operSubtype;    //CatalogConstants.OPERATION_SUB_TYP

    @Column(name = "REASON")
    private Integer reason;         //CatalogConstants.OPERATION_BASE

    @Column(name = "EXECUTOR", nullable = false)
    private Integer executor;

    @Column( name = "REG_DATE", nullable = false)
    private Date regDate;

    @Column( name = "OPER_DATE", nullable = false)
    private Date operDate;

    @Column(name = "PARENT_ID_ORDER")
    private Long parent_id_order;

    @Column(name = "PARENT_ID_HIST")
    private Long parent_id_hist;

    @Column( name = "STATUS", nullable = false)
    private Integer status;


    public Long getParent_id_order() {
        return parent_id_order;
    }

    public void setParent_id_order(Long parent_id_order) {
        this.parent_id_order = parent_id_order;
    }

    public Long getParent_id_hist() {
        return parent_id_hist;
    }

    public void setParent_id_hist(Long parent_id_hist) {
        this.parent_id_hist = parent_id_hist;
    }

    public Integer getReason() { return reason; }

    public void setReason(Integer reason) { this.reason = reason; }

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

    public Long getOoperId() {
        return ooperId;
    }

    public void setOoperId(Long ooperId) {
        this.ooperId = ooperId;
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public Integer getOperSubtype() {
        return operSubtype;
    }

    public void setOperSubtype(Integer operSubtype) {
        this.operSubtype = operSubtype;
    }

    public Integer getExecutor() {
        return executor;
    }

    public void setExecutor(Integer executor) {
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
