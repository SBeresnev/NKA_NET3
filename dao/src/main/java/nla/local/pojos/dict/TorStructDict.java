package nla.local.pojos.dict;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by beresnev on 10.02.2015.
 * Area list Organization (справочник территориальных единиц)
 */
@Entity
@Immutable
@Table(name="ANALYTICCODES")
@Where(clause = "ANALYTIC_TYPE = 300")
public class TorStructDict extends Dict{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANALYTIC_CODE", unique = true, nullable = false)
    private  Integer code_id;

    @Column( name = "ANALYTIC_CODE_NAME")
    private  String code_name;

    @Column( name = "ANALYTIC_CODE_SHORTNAME")
    private  String code_short_name;

    @Column( name = "PARENT_CODE")
    private  Integer parent_code;

    /*
    @Override
    public Integer getParent_code() { return parent_code; }

    @Override
    public void setParent_code(Integer parent_code) {
        this.parent_code = parent_code;
    }
    */

    @Override
    public Class getType()
    {
        return TorStructDict.class;

    }

    @Override
    public Integer getCode_id() {
        return code_id;
    }

    @Override
    public String getCode_name() {
        return code_name;
    }

    @Override
    public String getCode_short_name() {
        return code_short_name;
    }

    @Override
    public void setCode_id(Integer code_id) {
        this.code_id = code_id;
    }

    @Override
    public void setCode_name(String code_name) {
        this.code_name = code_name;
    }

    @Override
    public void setCode_short_name(String code_short_name) {
        this.code_short_name = code_short_name;
    }


}