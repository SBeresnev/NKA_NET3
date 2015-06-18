package nla.local.controller.forms;

import nla.local.pojos.dict.CatalogItem;

/**
 * Created by belonovich on 09.03.2015.
 */
public class CatalogForm {

    private Integer code_id;

    private Integer analytic_type;

    private String code_name;

    private String code_short_name;

    private Integer parent_code;

    private Integer n_prm1;

    private String v_prm1;

    private Integer unitmeasure;

    private Integer status;

    public Integer getCode_id() {
        return code_id;
    }

    public void setCode_id(Integer code_id) {
        this.code_id = code_id;
    }

    public Integer getAnalytic_type() {
        return analytic_type;
    }

    public void setAnalytic_type(Integer analytic_type) {
        this.analytic_type = analytic_type;
    }

    public String getCode_name() {
        return code_name;
    }

    public void setCode_name(String code_name) {
        this.code_name = code_name;
    }

    public String getCode_short_name() {
        return code_short_name;
    }

    public void setCode_short_name(String code_short_name) {
        this.code_short_name = code_short_name;
    }

    public Integer getParent_code() {
        return parent_code;
    }

    public void setParent_code(Integer parent_code) {
        this.parent_code = parent_code;
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

    public CatalogItem copyInCatalogAndGet(CatalogItem catalogItem){
        catalogItem.setCode_id(this.code_id);
        catalogItem.setAnalytic_type(this.analytic_type);
        catalogItem.setCode_name(this.code_name);
        catalogItem.setCode_short_name(this.code_short_name);
        catalogItem.setN_prm1(this.n_prm1);
        catalogItem.setV_prm1(this.v_prm1);
        catalogItem.setUnitmeasure(this.unitmeasure);
        catalogItem.setStatus(this.status);
        catalogItem.setParent_code(this.parent_code);
        return catalogItem;
    }
}
