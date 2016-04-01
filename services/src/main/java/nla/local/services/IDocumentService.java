package nla.local.services;

import nla.local.exception.ServiceDaoException;
import nla.local.pojos.docs.Docdata;
import nla.local.pojos.docs.Document;

import java.util.List;

/**
 * Created by beresnev on 25.03.2016.
 */
public interface IDocumentService extends IService  {

    public List<Document> getDescDocuments(Long entyti_id, Long doc_id) throws ServiceDaoException;

    public Docdata getDocument(Long doc_id) throws ServiceDaoException;

    public void addDocuments(List<Document> doc_desc_list , List<Docdata> doc_data_list)  throws ServiceDaoException;

    public void addDocument(Document doc_desc , Docdata doc_data)  throws ServiceDaoException;

}
