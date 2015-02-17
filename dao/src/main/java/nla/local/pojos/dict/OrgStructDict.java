package nla.local.pojos.dict;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;


/**
 * Created by beresnev on 05.02.2015.
 * Organizational structure dictionary (ОАО ЗАО ....)
 */

@Entity
@DiscriminatorValue("200")
public class OrgStructDict extends Dict implements Serializable {

}
