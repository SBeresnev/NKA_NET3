package nla.local.pojos.orders;

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
public class DeclResolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer decl_resolution_id;

    @OneToOne
    @JoinColumn(name = "SUBJECT_ID")
    OPerson oPerson;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column= @JoinColumn(name = "RESOLUTION_TYPE", referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula( formula=@JoinFormula(value="55", referencedColumnName="ANALYTIC_TYPE"))})
    Dict ResolutionType;

    @Column(name = "RESOLUTION_DATE")
    Date ResolutionDate;

    @Column(name = "RESOLUTION_DATE_OUT")
    Date ResolutionDateOut;

}
