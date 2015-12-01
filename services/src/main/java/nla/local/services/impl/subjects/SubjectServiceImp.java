package nla.local.services.impl.subjects;

import nla.local.dao.exceptions.DaoErrorCode;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.pojos.subjects.Person;
import nla.local.services.ISubjectService;
import nla.local.services.impl.BaseServiceImp;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by beresnev on 23.01.2015.
 */

@Service
@Transactional
public abstract class SubjectServiceImp<T extends Person> extends BaseServiceImp<T> implements ISubjectService<T> {

   private static Logger log = Logger.getLogger(SubjectServiceImp.class);

   private DetachedCriteria query;

   public DetachedCriteria getQuery()
   {
        return query;
   }

    @Override
    public T getSubject(Serializable id) throws ServiceDaoException {

        try {

            return super.get((Class<T>) Person.class, id);

        } catch (DaoException e) {
            throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_001, id);
        }
    }

    @Override
    public void refreshSubject(T t) throws ServiceDaoException
    {
        try {

            super.refresh(t);


        } catch (DaoException e) {

            throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_003, null);

        }

    };

    @Override
    public List<T> findSubjects(DetachedCriteria dc) throws ServiceDaoException
    {
        List<T> out = null;

        if (dc == null)
        {
            dc = getQuery();
        }

        try {

            out = super.getCriterion(dc);

        } catch (DaoException e) {

            throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_005, null);

        }

        return (List<T>) out;
    };


}
