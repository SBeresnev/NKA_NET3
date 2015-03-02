package nla.local.pojos.dict;

import java.io.Serializable;

/**
 * Created by beresnev on 20.02.2015.
 */
public class DictPk implements Serializable{


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

    private Integer code_id;
    private Integer analytic_type;

    public DictPk() {}

    public DictPk(Integer code_id, Integer analytic_type) {
        this.code_id = code_id;
        this.analytic_type = analytic_type;
    }

    public boolean equals(Object object) {
        if (object instanceof DictPk) {
            DictPk pk = (DictPk)object;
            return code_id == pk.code_id && analytic_type == pk.analytic_type;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return code_id.hashCode() + analytic_type.hashCode();
    }

}
