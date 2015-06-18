package nla.local.pojos.dict;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by belonovich on 06.03.2015.
 */

@Entity
@Table(name="ANALYTICTYPES")
public class CatalogType implements Serializable{

    @Id
    @Column( name ="ANALYTIC_TYPE", nullable = false)
    private Integer analytic_type;

    @Column(name = "ANALYTIC_TYPE_NAME")
    private String analyticTypeName;

    @Column(name = "STATUS")
    private Integer status;

    @JsonIgnore
    @OneToMany(mappedBy = "analytic_type")
    private List<CatalogItem> catalogItems;

    public Integer getAnalytic_type() {
        return analytic_type;
    }

    public void setAnalytic_type(Integer analytic_type) {
        this.analytic_type = analytic_type;
    }

    public String getAnalyticTypeName() {
        return analyticTypeName;
    }

    public void setAnalyticTypeName(String analyticTypeName) {
        this.analyticTypeName = analyticTypeName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<CatalogItem> getCatalogItems() {
        return catalogItems;
    }

    public void setCatalogItems(List<CatalogItem> catalogItems) {
        this.catalogItems = catalogItems;
    }
}
