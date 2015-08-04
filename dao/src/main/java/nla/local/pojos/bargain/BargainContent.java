package nla.local.pojos.bargain;

import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.object.Object_dest;
import nla.local.pojos.rights.RightOwner;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;


/**
 * Created by beresnev on 28.07.2015.
 */
@Entity
@Table( name = "BARGAINCONTENT")
public class BargainContent {

     @Id
    @Column(name = "BARGAIN_CONTENT_ID",unique=true, nullable=false)
    @SequenceGenerator(name="bar_cont_seq", sequenceName="SEQ_BARGAIN_CONTENT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO ,generator="bar_cont_seq")
    private Long bargain_content_id;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column=@JoinColumn(name = "BARGAIN_ENTITY_TYPE", nullable = false, referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula( formula=@JoinFormula(value= CatalogConstants.BARGAIN_ENTITY_TYPE,referencedColumnName="ANALYTIC_TYPE")
            )
    })
    private CatalogItem bargain_entity_type;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BARGAIN_ID")
    private Bargain bargain;

    @Column(name = "RIGHT_ENTITY_ID")
    private Long right_entity_id;             //  right refrence

    @Column(name = "OBJECT_ENTITY_ID")
    private Long object_entity_id;            //  object refrence

    @Transient
    private Object_dest bindedObj;

    @Transient
    private RightOwner bindedRight;

    @Column(name = "STATUS")
    private Integer Status;

    @Column( name ="OOPER_ID")
    private Integer ooper_id;



    public Integer getOoper_id() {
        return ooper_id;
    }

    public void setOoper_id(Integer ooper_id) {
        this.ooper_id = ooper_id;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public Object_dest getBindedObj() {
        return bindedObj;
    }

    public void setBindedObj(Object_dest bindedObj) {
        this.bindedObj = bindedObj;
    }

    public RightOwner getBindedRight() {
        return bindedRight;
    }

    public void setBindedRight(RightOwner bindedRight) {
        this.bindedRight = bindedRight;
    }

    public Long getBargain_content_id() {
        return bargain_content_id;
    }

    public void setBargain_content_id(Long bargain_content_id) {
        this.bargain_content_id = bargain_content_id;
    }

    public CatalogItem getBargain_entity_type() {
        return bargain_entity_type;
    }

    public void setBargain_entity_type(CatalogItem bargain_entity_type) {
        this.bargain_entity_type = bargain_entity_type;
    }

    public Long getRight_entity_id() {
        return right_entity_id;
    }

    public void setRight_entity_id(Long right_entity_id) {
        this.right_entity_id = right_entity_id;
    }

    public Long getObject_entity_id() {
        return object_entity_id;
    }

    public void setObject_entity_id(Long object_entity_id) {
        this.object_entity_id = object_entity_id;
    }

    public Bargain getBargain() {
        return bargain;
    }

    public void setBargain(Bargain bargain) {
        this.bargain = bargain;
    }

}
