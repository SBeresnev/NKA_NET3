package nla.local.pojos.docs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.context.annotation.Lazy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.sql.Blob;
import java.util.Date;

/**
 * Created by beresnev on 25.03.2016.
 */
@Entity
@Table(name = "V_DOCS", schema = "NKA_NET3_DEV")
public class Document {

    @Id
    private Long doc_id;

    private Long entity_id;

    private Integer doc_type;

    private Integer document_number;

    private Date document_date;

    private String discription_org;

    private String organisation;

    private String description_doc;

    private Integer num;


    public Long getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(Long doc_id) {
        this.doc_id = doc_id;
    }

    public Long getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(Long entity_id) {
        this.entity_id = entity_id;
    }

    public Integer getDoc_type() {
        return doc_type;
    }

    public void setDoc_type(Integer doc_type) {
        this.doc_type = doc_type;
    }

    public Integer getDocument_number() {
        return document_number;
    }

    public void setDocument_number(Integer document_number) {
        this.document_number = document_number;
    }

    public Date getDocument_date() {
        return document_date;
    }

    public void setDocument_date(Date document_date) {
        this.document_date = document_date;
    }

    public String getDiscription_org() {
        return discription_org;
    }

    public void setDiscription_org(String discription_org) {
        this.discription_org = discription_org;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getDescription_doc() {
        return description_doc;
    }

    public void setDescription_doc(String description_doc) {
        this.description_doc = description_doc;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


}
