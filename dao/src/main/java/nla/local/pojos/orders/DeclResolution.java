package nla.local.pojos.orders;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.subjects.OPerson;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by beresnev on 09.03.2015.
 */


@Entity
@Table(name = "V_DECLRESOLUTIONS")
public class  DeclResolution {

    @Id
    @Column(name="DECL_RESOLUTION_ID", unique=true, nullable=false )
    @SequenceGenerator(name="decl_resolution_seq", sequenceName="SEQ_DECLRESOLUTION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO ,generator="decl_resolution_seq")
    private Integer decl_resolution_id;

    @OneToOne
    @JoinColumn(name = "SUBJECT_ID")
    private OPerson oPerson;

    @Column(name = "DECL_ID")
    private Integer decl_id;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column= @JoinColumn(name = "RESOLUTION_TYPE", referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula(formula=@JoinFormula(value= CatalogConstants.RESOLUTION_TYP, referencedColumnName="ANALYTIC_TYPE"))})
    private CatalogItem resolutionType;

    @Column(name = "RESOLUTION_DATE")
    @JsonSerialize(using=DateSerializer.class)
    private Date resolutionDate;

    @Column(name = "RESOLUTION_DATE_OUT")
    @JsonSerialize(using=DateSerializer.class)
    private Date resolutionDateOut;


    public Integer getDecl_resolution_id() {
        return decl_resolution_id;
    }

    public void setDecl_resolution_id(Integer decl_resolution_id) {
        this.decl_resolution_id = decl_resolution_id;
    }

    public OPerson getoPerson() {
        return oPerson;
    }

    public void setoPerson(OPerson oPerson) {
        this.oPerson = oPerson;
    }

    public Date getResolutionDate() {
        return resolutionDate;
    }

    public void setResolutionDate(Date resolutionDate) {
        this.resolutionDate = resolutionDate;
    }

    public Date getResolutionDateOut() {
        return resolutionDateOut;
    }

    public void setResolutionDateOut(Date resolutionDateOut) {
        this.resolutionDateOut = resolutionDateOut;
    }

    public Integer getDecl_id() {
        return decl_id;
    }

    public void setDecl_id(Integer decl_id) {
        this.decl_id = decl_id;
    }

    public CatalogItem getResolutionType() {
        return resolutionType;
    }

    public void setResolutionType(CatalogItem resolutionType) {
        this.resolutionType = resolutionType;
    }
}
