package nla.local.pojos.dict;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

/**
 * Created by beresnev on 16.02.2015.
 */

@Entity
@Immutable
@Table(name="ANALYTICCODES")
@IdClass(DictPk.class)
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Dict implements IDict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANALYTIC_CODE",  nullable = false)
    private  Integer code_id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name ="ANALYTIC_TYPE", nullable = false, insertable = false , updatable = false )
    private Integer analytic_type;

    @Column( name = "ANALYTIC_CODE_NAME")
    private  String code_name;

    @Column( name = "ANALYTIC_CODE_SHORTNAME")
    private  String code_short_name;


    public Integer getAnalytic_type() {
        return analytic_type;
    }

    public void setAnalytic_type(Integer analytic_type) {
        this.analytic_type = analytic_type;
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


