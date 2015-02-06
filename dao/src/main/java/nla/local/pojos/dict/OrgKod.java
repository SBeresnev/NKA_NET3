package nla.local.pojos.dict;

import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by beresnev on 05.02.2015.
 */

@Entity
@Table(name="ANALYTICCODES")
@Where(clause = "ANALYTIC_TYPE = 220")
public class OrgKod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANALYTIC_CODE", unique = true, nullable = false)
    Integer code_id;

    @Column( name = "ANALYTIC_CODE_NAME")
    String code_name;

}
