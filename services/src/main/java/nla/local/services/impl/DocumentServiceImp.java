package nla.local.services.impl;

import nla.local.exception.ServiceDaoException;
import nla.local.pojos.docs.Docdata;
import nla.local.pojos.docs.Document;

import nla.local.services.IDocumentService;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by beresnev on 25.03.2016.
 */
@Service
@Transactional
public class DocumentServiceImp<T extends Document> extends BaseServiceImp<T> implements IDocumentService {

    private static Logger log = Logger.getLogger(DocumentServiceImp.class);

    private DetachedCriteria query_Docdata = DetachedCriteria.forClass(Document.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

    private DetachedCriteria query_Doc = DetachedCriteria.forClass(Docdata.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

    @Override
    public List<Document> getDescDocuments(Long entyti_id, Long doc_id) throws ServiceDaoException{

        List<Document> ret_val = new ArrayList<Document>();

        DetachedCriteria query_doc = (DetachedCriteria) SerializationUtils.clone(query_Doc);

        query_doc = query_doc.add(Restrictions.or(Restrictions.eq("entity_id", entyti_id),Restrictions.eq("doc_id", doc_id)) );

        query_doc.setProjection(Property.forName("doc_id"));

        ret_val = (List<Document>) this.getCriterion(query_doc);

        return ret_val;
    }

    @Override
    public List<Docdata> getDocuments(Long doc_id) throws ServiceDaoException{

        List<Docdata> ret_val = new ArrayList<Docdata>();

        DetachedCriteria query_doc = (DetachedCriteria) SerializationUtils.clone(query_Docdata);

        query_doc = query_doc.add(Restrictions.eq("doc_id", doc_id));

        ret_val = (List<Docdata>) this.getCriterion(query_doc);

        return ret_val;
    }



}
