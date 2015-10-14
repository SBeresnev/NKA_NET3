package nla.local.pojos.address;

import javax.persistence.*;

/**
 * Created by beresnev on 30.04.2015.
 */

@Entity
@Table(name="ATE")
public class Ate {

    @Id
    @Column(name="ATE_ID", unique=true, nullable=false )
    private Integer ate_id;

    @Column(name = "ATE_NAME")
    private String ate_name;

    @Column(name = "PARENTATE_ID", insertable = false, updatable = false)
    private Integer parentate_id;

    @Column(name = "TREE_IDS")
    private String tree_ids;

    @Column(name = "SOATO")
    private String soato;

    @Transient
    private String full_ate;


    public String getSoato() {
        return soato;
    }

    public void setSoato(String soato) {
        this.soato = soato;
    }

    public Integer getParentate_id() {
        return parentate_id;
    }

    public void setParentate_id(Integer parentate_id) {
        this.parentate_id = parentate_id;
    }

    public String getTree_ids() {
        return tree_ids;
    }

    public void setTree_ids(String tree_ids) {
        this.tree_ids = tree_ids;
    }

    public Integer getAte_id() {
        return ate_id;
    }

    public void setAte_id(Integer ate_id) {
        this.ate_id = ate_id;
    }

    public String getAte_name() {
        return ate_name;
    }

    public void setAte_name(String ate_name) {
        this.ate_name = ate_name;
    }

    public String getParent_ate() {
        return full_ate;
    }

    public void setParent_ate(String full_ate) {
        this.full_ate = full_ate;
    }

}
