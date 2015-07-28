package nla.local.pojos.bargain;

import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.rights.RightOwner;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by beresnev on 28.07.2015.
 */
@Entity
@Table( name = "BARGAINCONTENT")
public class BargainContent {

    @Id
    @GeneratedValue(generator="seq_id")
    @GenericGenerator(
            name="seq_id",
            strategy = "nla.local.util.CodeGenerator",
            parameters = @org.hibernate.annotations.Parameter(name = "seq_name", value = "SEQ_BARGAINS_ID"))
    @Column(name="BARGAIN_CONTENT_ID", unique=true, nullable=false )
    private Integer bargain_content_id;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column=@JoinColumn(name = "BARGAIN_ENTITY_TYPE", nullable = false, referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula( formula=@JoinFormula(value= CatalogConstants.BARGAIN_ENTITY_TYPE,referencedColumnName="ANALYTIC_TYPE")
            )
    })
    private CatalogItem bargain_entity_type;



}
