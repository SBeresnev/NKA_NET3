package nla.local.pojos.dict;

import java.io.Serializable;

/**
 * Created by belonovich on 31.03.2015.
 */
public class DependencyDataPk implements Serializable {

    private Integer dependencyId;

    private Integer analyticCode;

    private Integer parentAnalyticCode;

    public DependencyDataPk(){};

    public DependencyDataPk(Integer dependencyId, Integer analyticCode, Integer parentAnalyticCode) {
        this.dependencyId = dependencyId;
        this.analyticCode = analyticCode;
        this.parentAnalyticCode = parentAnalyticCode;
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

    public Integer getDependencyId() {
        return dependencyId;
    }

    public void setDependencyId(Integer dependencyId) {
        this.dependencyId = dependencyId;
    }
}
