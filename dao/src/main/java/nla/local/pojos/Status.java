package nla.local.pojos;

import org.hibernate.annotations.Immutable;
import javax.persistence.*;

/**
 * Created by Serega on 13.10.2014.
 */
@Entity
@Immutable
@Table( name="SUBJECTSTATUS")
public class Status {
    @Id
    @Column(name="STATUS_ID")
    public Integer ID;
    @Column(name = "STAT_NAME")
    public String StatName;

}
