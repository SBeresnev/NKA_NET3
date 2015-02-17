package nla.local.pojos.dict;

import org.hibernate.annotations.BatchSize;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by beresnev on 10.02.2015.
 *
 * State List (список Государств)
 */

@Entity
@BatchSize(size = 20)
@DiscriminatorValue("200")
public class StateDict extends Dict implements Serializable{

}
