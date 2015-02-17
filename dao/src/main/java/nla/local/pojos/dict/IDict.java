package nla.local.pojos.dict;

import java.io.Serializable;

/**
 * Created by beresnev on 17.02.2015.
 */
public interface IDict extends Serializable {

    public Integer getAnalytic_type();

    public void setAnalytic_type(Integer analytic_type);

    public Integer getParent_code();

    public void setParent_code(Integer parent_code);

    public String getParent_desc();

    public void setParent_desc(String parent_desc);

    public Integer getCode_id();

    public String getCode_name();

    public String getCode_short_name();

    public void setCode_id(Integer code_id);

    public void setCode_name(String code_name);

    public void setCode_short_name(String code_short_name);

}
