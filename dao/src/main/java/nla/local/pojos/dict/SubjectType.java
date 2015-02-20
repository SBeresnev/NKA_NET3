package nla.local.pojos.dict;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by beresnev on 20.02.2015.
 */

@Entity
@Immutable
@Table(name="ANALYTICCODES")
@Where(clause = "ANALYTIC_TYPE=110")
public class SubjectType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANALYTIC_CODE",  nullable = false)
    private  Integer code_id;

    @Column( name ="ANALYTIC_TYPE", nullable = false, insertable = false , updatable = false )
    private Integer analytic_type;

    @Column( name = "ANALYTIC_CODE_NAME")
    private  String code_name;

    @Column( name = "ANALYTIC_CODE_SHORTNAME")
    private  String code_short_name;

    @Column( name = "PARENT_CODE")
    private  Integer parent_code;

    @Transient
    private  String parent_desc;


    public Integer getAnalytic_type() {
        return analytic_type;
    }

    public void setAnalytic_type(Integer analytic_type) {
        this.analytic_type = analytic_type;
    }

    public Integer getParent_code() {

        return parent_code;

    }

    public void setParent_code(Integer parent_code) {
        this.parent_code = parent_code;
    }

    public String getParent_desc() {

        if( parent_code == 100 && analytic_type == 110 ) parent_desc = "private";

        if( parent_code == 200 && analytic_type == 110 ) parent_desc = "juridical";

        if( parent_code == 600 && analytic_type == 110 ) parent_desc = "official";

        return parent_desc;}

    public void setParent_desc(String parent_desc) {
        this.parent_desc = parent_desc;
    }

    public Integer getCode_id() {
        return code_id;
    }

    public String getCode_name() {
        return code_name;
    }

    public String getCode_short_name() {
        return code_short_name;
    }

    public void setCode_id(Integer code_id) {
        this.code_id = code_id;
    }

    public void setCode_name(String code_name) {
        this.code_name = code_name;
    }

    public void setCode_short_name(String code_short_name) {
        this.code_short_name = code_short_name;
    }


}
