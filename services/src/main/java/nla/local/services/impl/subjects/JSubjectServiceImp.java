package nla.local.services.impl.subjects;

import nla.local.dao.exceptions.DaoErrorCode;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.pojos.subjects.JPerson;
import nla.local.pojos.subjects.SubjectClass;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by beresnev on 12.02.2015.
 */
@Service
@Transactional
public class JSubjectServiceImp extends SubjectServiceImp<JPerson> {

    private static Logger log = Logger.getLogger(JSubjectServiceImp.class);

    private DetachedCriteria query = DetachedCriteria.forClass(JPerson.class).add(Restrictions.eq("dtype", SubjectClass.JUR.toString()));

    @Autowired
    public JSubjectServiceImp(SessionFactory sessionFactory)
    {

        super(sessionFactory);

    }

    @Override
    public List<JPerson> getAll(Class<JPerson> clazz) throws ServiceDaoException {

        return this.getAll();

    }

    @Override
    public JPerson getSubject(Serializable id) throws ServiceDaoException {

        try {
            return super.get(JPerson.class,id);

        } catch (DaoException e) {

            throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_001, id);

        }

    }

    public List<JPerson> getAll() throws ServiceDaoException {

         return super.findSubjects(query);

    }

    public DetachedCriteria getQuery()
    {
        return query;
    }

    public void setQuery(DetachedCriteria query)
    {
         this.query =  (DetachedCriteria) SerializationUtils.clone(query);
    }

    public List<JPerson> findByNameType(String fullName, String regNumber, Integer subjectType ) throws ServiceDaoException {


        DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query);

        List<JPerson> retval = new ArrayList<JPerson>();

        if(fullName != null || regNumber != null) {

            regNumber = regNumber == null ? "":regNumber;
            fullName = fullName==null? "":fullName;

            query_ = query_
                    .add(Restrictions.or(Restrictions.like("regNumber", regNumber , MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("regNumber")))
                    .add(Restrictions.or(Restrictions.like("fullname", fullName ,MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("fullname")));


            query_ = subjectType != null ? query_.createCriteria("subjectType").add(Restrictions.eq("code_id", subjectType)):query_;


            retval = (List<JPerson>) this.findSubjects(query_);

        }

        return retval;
    }


}
