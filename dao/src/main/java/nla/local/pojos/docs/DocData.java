package nla.local.pojos.docs;

import javax.persistence.*;
import java.sql.Blob;

/**
 * Created by beresnev on 25.03.2016.
 */
@Entity
@MappedSuperclass
@Table(name = "V_DOCS", schema = "NKA_NET3_DEV")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Docdata extends Document{

    @Lob
    private Blob doc_content;

    public Blob getDoc_content() {
        return doc_content;
    }

    public void setDoc_content(Blob doc_content) {
        this.doc_content = doc_content;
    }

}
