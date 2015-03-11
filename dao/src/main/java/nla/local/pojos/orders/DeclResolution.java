package nla.local.pojos.orders;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import nla.local.pojos.dict.Dict;
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
@Table(name = "DECLRESOLUTIONS")
public class DeclResolution {

    @Id
    @Column(name="DECL_RESOLUTION_ID", unique=true, nullable=false )
    @SequenceGenerator(name="decl_resolution_seq", sequenceName="SEQ_DECLRESOLUTION_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE ,generator="decl_resolution_seq")
    private Integer decl_resolution_id;

    @OneToOne
    @JoinColumn(name = "SUBJECT_ID")
    private OPerson oPerson;

    @Column(name = "DECL_ID")
    private Integer decl_id;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column= @JoinColumn(name = "RESOLUTION_TYPE", referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula( formula=@JoinFormula(value="55", referencedColumnName="ANALYTIC_TYPE"))})
    private Dict ResolutionType;

    @Column(name = "RESOLUTION_DATE")
    @JsonSerialize(using=DateSerializer.class)
    private Date ResolutionDate;

    @Column(name = "RESOLUTION_DATE_OUT")
    @JsonSerialize(using=DateSerializer.class)
    private Date ResolutionDateOut;


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

    public Dict getResolutionType() {
        return ResolutionType;
    }

    public void setResolutionType(Dict resolutionType) {
        ResolutionType = resolutionType;
    }

    public Date getResolutionDate() {
        return ResolutionDate;
    }

    public void setResolutionDate(Date resolutionDate) {
        ResolutionDate = resolutionDate;
    }

    public Date getResolutionDateOut() {
        return ResolutionDateOut;
    }

    public void setResolutionDateOut(Date resolutionDateOut) {
        ResolutionDateOut = resolutionDateOut;
    }

    public Integer getDecl_id() {
        return decl_id;
    }

    public void setDecl_id(Integer decl_id) {
        this.decl_id = decl_id;
    }


}
