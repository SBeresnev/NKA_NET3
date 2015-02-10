package nla.local.pojos.dict;

/**
 * Created by beresnev on 06.02.2015.
 * Abstract Dictionary
 */
public abstract class Dict {

    public abstract Integer getCode_id();

    public abstract String getCode_name();

    public abstract String getCode_short_name();

    public abstract void setCode_id(Integer code_id);

    public abstract void setCode_name(String code_name);

    public abstract void setCode_short_name(String code_short_name);

}
