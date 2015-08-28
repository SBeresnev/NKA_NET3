package nla.local.pojos.bargain;

import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.rights.RightOwner;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;

import javax.persistence.*;

/**
 * Created by beresnev on 25.06.2015.
 */
@Entity
@Table( name = "V_BARGAIN")
public class Bargain {

    @Id
    @GeneratedValue(generator="seq_id")
    @GenericGenerator(
            name="seq_id",
            strategy = "nla.local.util.CodeGenerator",
            parameters = @org.hibernate.annotations.Parameter(name = "seq_name", value = "SEQ_BARGAINS_ID"))
    @Column(name="BARGAIN_ID", unique=true, nullable=false )
    private Long bargain_id;

    @ManyToOne
    @JoinColumnsOrFormulas({
            @JoinColumnOrFormula(column=@JoinColumn(name = "BARGAIN_TYPE", nullable = false, referencedColumnName = "ANALYTIC_CODE")),
            @JoinColumnOrFormula( formula=@JoinFormula(value= CatalogConstants.BARGAIN_TYPE,referencedColumnName="ANALYTIC_TYPE")
            )
    })
    private CatalogItem bargain_type;

    @Column(name="BARGAIN_CONDITION")
    private String bargain_condition;

    @Column( name ="REGISTRED")
    private Integer registred;

    @Column( name ="RELATIVES")
    private Integer relatives;

    @Column( name ="AFFILIATES")
    private Integer affiliates;

    @Column( name ="DONE")
    private Integer done;

    @Column( name ="STATUS")
    private Integer status;

    @Column( name ="DOC_ID")
    private Integer doc_id;

    @Column( name ="OOPER_ID")
    private Integer ooper_id;

    @Column( name ="CURRENCY_COMPLEX")
    private Integer currency_complex;

    @Column( name ="PRICE_VALUE_COMPLEX")
    private Integer price_value_complex;

    @Column( name ="VAT_COMPLEX")
    private Integer vat_complex;

    @Column( name ="COUNT_OBJECT")
    private Integer count_object;


    public Integer getCount_object() {
        return count_object;
    }

    public void setCount_object(Integer count_object) {
        this.count_object = count_object;
    }

    public Integer getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(Integer doc_id) {
        this.doc_id = doc_id;
    }

    public Long getBargain_id() {
        return bargain_id;
    }

    public void setBargain_id(Long bargain_id) {
        this.bargain_id = bargain_id;
    }

    public CatalogItem getBargain_type() {
        return bargain_type;
    }

    public void setBargain_type(CatalogItem bargain_type) {
        this.bargain_type = bargain_type;
    }

    public String getBargain_condition() {
        return bargain_condition;
    }

    public void setBargain_condition(String bargain_condition) {
        this.bargain_condition = bargain_condition;
    }

    public Integer getRegistred() {
        return registred;
    }

    public void setRegistred(Integer registred) {
        this.registred = registred;
    }

    public Integer getRelatives() {
        return relatives;
    }

    public void setRelatives(Integer relatives) {
        this.relatives = relatives;
    }

    public Integer getAffiliates() {
        return affiliates;
    }

    public void setAffiliates(Integer affiliates) {
        this.affiliates = affiliates;
    }

    public Integer getDone() {
        return done;
    }

    public void setDone(Integer done) {
        this.done = done;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOoper_id() {
        return ooper_id;
    }

    public void setOoper_id(Integer ooper_id) {
        this.ooper_id = ooper_id;
    }

    public Integer getCurrency_complex() {
        return currency_complex;
    }

    public void setCurrency_complex(Integer currency_complex) {
        this.currency_complex = currency_complex;
    }

    public Integer getPrice_value_complex() {
        return price_value_complex;
    }

    public void setPrice_value_complex(Integer price_value_complex) {
        this.price_value_complex = price_value_complex;
    }

    public Integer getVat_complex() {
        return vat_complex;
    }

    public void setVat_complex(Integer vat_complex) {
        this.vat_complex = vat_complex;
    }

}
