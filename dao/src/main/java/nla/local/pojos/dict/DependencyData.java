package nla.local.pojos.dict;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by belonovich on 31.03.2015.
 */
@Entity
@Table(name = "DEPENDENCYDATA")
@IdClass(DependencyDataPk.class)
public class DependencyData implements Serializable{

    @Id
    @Column(name = "DEPENDENCY_ID")
    private Integer dependencyId;

    @Id
    @Column(name = "ANALYTIC_CODE")
    private Integer analyticCode;

    @Id
    @Column(name = "PARENT_ANALYTIC_CODE")
    private Integer parentAnalyticCode;

    @Column(name = "ANALYTIC_VALUE")
    private Integer analyticValue;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "DEPENDENCY_ID", insertable=false, updatable=false)
    private CatalogDependency catalogDependency;

    public Integer getAnalyticValue() {
        return analyticValue;
    }

    public void setAnalyticValue(Integer analyticValue) {
        this.analyticValue = analyticValue;
    }

    public Integer getDependencyId() {
        return dependencyId;
    }

    public void setDependencyId(Integer dependencyId) {
        this.dependencyId = dependencyId;
    }

    public Integer getAnalyticCode() {
        return analyticCode;
    }

    public void setAnalyticCode(Integer analyticCode) {
        this.analyticCode = analyticCode;
    }

    public Integer getParentAnalyticCode() {
        return parentAnalyticCode;
    }

    public void setParentAnalyticCode(Integer parentAnalyticCode) {
        this.parentAnalyticCode = parentAnalyticCode;
    }

    public CatalogDependency getCatalogDependency() {
        return catalogDependency;
    }

    public void setCatalogDependency(CatalogDependency catalogDependency) {
        this.catalogDependency = catalogDependency;
    }

    public DependencyData() {}

    public DependencyData(DependencyDataPk dependencyDataPk) {
        this.dependencyId = dependencyDataPk.getDependencyId();
        this.analyticCode = dependencyDataPk.getAnalyticCode();
        this.parentAnalyticCode = dependencyDataPk.getParentAnalyticCode();
    }
}

