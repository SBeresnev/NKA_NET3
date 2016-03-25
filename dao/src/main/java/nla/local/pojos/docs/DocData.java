package nla.local.pojos.docs;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.sql.Blob;

/**
 * Created by beresnev on 25.03.2016.
 */
@Entity
@Table(name = "V_DOCS", schema = "NKA_NET3_DEV")
public class Docdata {

    @Id
    private Long doc_id;

    @Lob
    private Blob doc_content;

    public Long getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(Long doc_id) {
        this.doc_id = doc_id;
    }

    public Blob getDoc_content() {
        return doc_content;
    }

    public void setDoc_content(Blob doc_content) {
        this.doc_content = doc_content;
    }

}
