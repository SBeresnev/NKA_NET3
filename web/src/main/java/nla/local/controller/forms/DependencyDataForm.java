package nla.local.controller.forms;

/**
 * Created by belonovich on 06.04.2015.
 */
public class DependencyDataForm {
    Integer idDependency, idCode,  idParentCode;

    public Integer getIdDependency() {
        return idDependency;
    }

    public void setIdDependency(Integer idDependency) {
        this.idDependency = idDependency;
    }

    public Integer getIdCode() {
        return idCode;
    }

    public void setIdCode(Integer idCode) {
        this.idCode = idCode;
    }

    public Integer getIdParentCode() {
        return idParentCode;
    }

    public void setIdParentCode(Integer idParentCode) {
        this.idParentCode = idParentCode;
    }
}
