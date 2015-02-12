package nla.local.pojos.dict;


import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Where;

import javax.persistence.*;


/**
 * Created by beresnev on 05.02.2015.
 * Subject structure dictionary
 */

@Entity
@Immutable
@Table(name="ANALYTICCODES")
@Where(clause = "ANALYTIC_TYPE = 110")
public class SubjectTypeDict extends Dict {


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

    private  String parent_desc;

    public String getParent_desc(){

        if(parent_code == 100) parent_desc = "PRIVATE";

        if(parent_code == 200) parent_desc = "PUBLIC";

        if(parent_code == 600) parent_desc = "OFFICIAL";

        return parent_desc;

    }

    @Override
    public Class getType(){ return SubjectTypeDict.class; }

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
