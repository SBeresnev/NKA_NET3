package nla.local.pojos.dict;



import javax.persistence.*;

/**
 * Created by belonovich on 06.03.2015.
 */

@Entity
@Table(name = "ANALYTICCODES")
@IdClass(CatalogPk.class)
public class CatalogItem {

    @Id
    @Column(name = "ANALYTIC_CODE", nullable = false)
    private Integer code_id;

    @Id
    @Column(name = "ANALYTIC_TYPE", nullable = false)
    private Integer analytic_type;

    @Column(name = "ANALYTIC_CODE_NAME")
    private String code_name;

    @Column(name = "ANALYTIC_CODE_SHORTNAME")
    private String code_short_name;

    @Column(name = "PARENT_CODE")
    private Integer parent_code;

    @Column(name = "N_PRM1")
    private Integer n_prm1;

    @Column(name = "V_PRM1")
    private String v_prm1;

    @Column(name = "REF_TYPE")
    private Integer unitmeasure;

    @Column(name = "STATUS")
    private Integer status;

    /*
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ANALYTIC_TYPE", nullable = false, insertable = false, updatable = false)
    private CatalogType catalogType;*/


    public Integer getParent_code() {
        return parent_code;
    }

    public void setParent_code(Integer parent_code) {
        this.parent_code = parent_code;
    }

    public Integer getAnalytic_type() {
        return analytic_type;
    }

    public void setAnalytic_type(Integer analytic_type) {
        this.analytic_type = analytic_type;
    }

    public Integer getCode_id() {
        return code_id;
    }

    public String getCode_name() {
        return code_name;
    }

    public String getCode_short_name() {
        return code_short_name;
    }

    public void setCode_id(Integer code_id) {
        this.code_id = code_id;
    }

    public void setCode_name(String code_name) {
        this.code_name = code_name;
    }

    public void setCode_short_name(String code_short_name) {
        this.code_short_name = code_short_name;
    }

    public Integer getUnitmeasure() {
        return unitmeasure;
    }

    public void setUnitmeasure(Integer unitmeasure) {
        this.unitmeasure = unitmeasure;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getN_prm1() {
        return n_prm1;
    }

    public void setN_prm1(Integer n_prm1) {
        this.n_prm1 = n_prm1;
    }

    public String getV_prm1() {
        return v_prm1;
    }

    public void setV_prm1(String v_prm1) {
        this.v_prm1 = v_prm1;
    }
 /*
    public CatalogType getCatalogType() {
        return catalogType;
    }

    public void setCatalogType(CatalogType catalogType) {
        this.catalogType = catalogType;
    }
*/
    public CatalogPk getCatalogPk(){
        return new CatalogPk(code_id, analytic_type);
    }

    public void setCatalogPk(CatalogPk catalogPk){
        this.analytic_type = catalogPk.getAnalytic_type();
        this.code_id = catalogPk.getCode_id();
    }

}

