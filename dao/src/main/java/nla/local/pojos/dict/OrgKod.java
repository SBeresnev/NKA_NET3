package nla.local.pojos.dict;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by beresnev on 05.02.2015.
 * Organizational structure dictionary
 */

@Entity
@Immutable
@Table(name="ANALYTICCODES")
@Where(clause = "ANALYTIC_TYPE = 220")
public class OrgKod extends Dict {

    private Integer code_id;

    private String code_name;

    private String code_short_name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANALYTIC_CODE", unique = true, nullable = false)
    @Override
    public Integer getCode_id() {
        return code_id;
    }

    @Column( name = "ANALYTIC_CODE_NAME")
    @Override
    public String getCode_name() {
        return code_name;
    }

    @Column( name = "ANALYTIC_CODE_SHORTNAME")
    @Override
    public String getCode_short_name() {
        return code_short_name;
    }

}
