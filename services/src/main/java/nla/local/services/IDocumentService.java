package nla.local.services;

import nla.local.exception.ServiceDaoException;
import nla.local.pojos.docs.Document;

import java.util.List;

/**
 * Created by beresnev on 25.03.2016.
 */
public interface IDocumentService extends IService<Document>  {

    public List<Document> getDescDocuments(Long entyti_id, Long doc_id) throws ServiceDaoException;

    public List<Document> getDocuments(Long entyti_id, Long doc_id) throws ServiceDaoException;


}
