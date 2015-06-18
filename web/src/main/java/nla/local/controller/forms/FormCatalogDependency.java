package nla.local.controller.forms;

/**
 * Created by belonovich on 02.04.2015.
 */
public class FormCatalogDependency {

    private Integer analyticTypeId;

    private Integer parentAnalyticTypeId;

    public Integer getParentAnalyticTypeId() {
        return parentAnalyticTypeId;
    }

    public void setParentAnalyticTypeId(Integer parentAnalyticTypeId) {
        this.parentAnalyticTypeId = parentAnalyticTypeId;
    }

    public Integer getAnalyticTypeId() {
        return analyticTypeId;
    }

    public void setAnalyticTypeId(Integer analyticTypeId) {
        this.analyticTypeId = analyticTypeId;
    }
}
