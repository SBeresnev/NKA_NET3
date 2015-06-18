package nla.local.pojos.dict;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by belonovich on 30.03.2015.
 */
@Entity
@Table(name = "ANALYTICSDEPENDENCY")
@SequenceGenerator(name="dependency_seq", sequenceName="SEQ_ANALYTICSDEPEND_ID")
public class CatalogDependency implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO ,generator="dependency_seq")
    @Column(name = "DEPENDENCY_ID")
    private Integer dependencyId;

    @Column(name = "ANALYTIC_TYPE")
    private Integer analyticTypeId;

    @Column(name = "PARENT_ANALYTIC_TYPE")
    private Integer parentAnalyticTypeId;

    @OneToOne
    @JoinColumn(name = "ANALYTIC_TYPE", insertable = false, updatable = false)
    private CatalogType analyticType;

    @OneToOne
    @JoinColumn(name = "PARENT_ANALYTIC_TYPE", insertable = false, updatable = false)
    private CatalogType parentAnalyticType;

    @JsonIgnore
    @OneToMany(mappedBy = "dependencyId", cascade = CascadeType.REMOVE)
    private List<DependencyData> dependencyDatas;

    public List<DependencyData> getDependencyDatas() {
        return dependencyDatas;
    }

    public void setDependencyDatas(List<DependencyData> dependencyDatas) {
        this.dependencyDatas = dependencyDatas;
    }

    public Integer getDependencyId() {
        return dependencyId;
    }

    public void setDependencyId(Integer dependencyId) {
        this.dependencyId = dependencyId;
    }

    public CatalogType getAnalyticType() {
        return analyticType;
    }

    public void setAnalyticType(CatalogType analyticType) {
        this.analyticType = analyticType;
    }

    public CatalogType getParentAnalyticType() {
        return parentAnalyticType;
    }

    public void setParentAnalyticType(CatalogType parentAnalyticType) {
        this.parentAnalyticType = parentAnalyticType;
    }

    public Integer getAnalyticTypeId() {
        return analyticTypeId;
    }

    public void setAnalyticTypeId(Integer analyticTypeId) {
        this.analyticTypeId = analyticTypeId;
    }

    public Integer getParentAnalyticTypeId() {
        return parentAnalyticTypeId;
    }

    public void setParentAnalyticTypeId(Integer parentAnalyticTypeId) {
        this.parentAnalyticTypeId = parentAnalyticTypeId;
    }

}
