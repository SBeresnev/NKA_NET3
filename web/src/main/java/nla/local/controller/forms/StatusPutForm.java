package nla.local.controller.forms;

/**
 * Created by belonovich on 24.03.2015.
 */
public class StatusPutForm {

    private Integer status;

    private Long declId;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getDeclId() {
        return declId;
    }

    public void setDeclId(Long declId) {
        this.declId = declId;
    }
}
