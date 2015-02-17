package nla.local.pojos.dict;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Created by beresnev on 17.02.2015.
 */
@Entity
@DiscriminatorValue("110")
public class SubjectTypeDict extends Dict {

    @Transient
    private  String parent_desc;

    public  void setAnalytic_type() {
         super.setAnalytic_type(110);
    }

    public String getParent_desc() {

        if(super.getParent_code() == 100 ) parent_desc = "private";

        if(super.getParent_code() == 200 ) parent_desc = "juridical";

        if(super.getParent_code() == 600 ) parent_desc = "official";

        return parent_desc;
    }
}
