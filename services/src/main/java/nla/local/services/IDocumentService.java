package nla.local.services;

import nla.local.exception.ServiceDaoException;
import nla.local.pojos.docs.Docdata;
import nla.local.pojos.docs.Document;

import java.util.List;

/**
 * Created by beresnev on 25.03.2016.
 */
public interface IDocumentService<T extends Document> extends IService<T>  {

    public List<Document> getDescDocuments(Long entyti_id, Long doc_id) throws ServiceDaoException;

    public List<Docdata> getDocuments(Long doc_id) throws ServiceDaoException;


}
