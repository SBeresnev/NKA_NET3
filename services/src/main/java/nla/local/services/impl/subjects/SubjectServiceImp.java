package nla.local.services.impl.subjects;

import nla.local.dao.BaseDao;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.subjects.Person;
import nla.local.services.ISubjectService;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * Created by beresnev on 23.01.2015.
 */

public abstract class SubjectServiceImp<T extends Person> extends BaseDao<T> implements ISubjectService<T> {

   private static Logger log = Logger.getLogger(SubjectServiceImp.class);

   private DetachedCriteria query;

   public DetachedCriteria getQuery()
    {
        return query;
    }


   public SubjectServiceImp(SessionFactory sessionFactory)
    {
        super(sessionFactory);

    }

    @Override
    public void add(T t)  {

        try {

            super.add(t);

        } catch (DaoException e) {

            e.printStackTrace();
        }

    }


    @Override
    public void refreshSubject(T t) throws ServiceException
    {
        try {

            super.update(t);

        } catch (DaoException e) {

            e.printStackTrace();
        }

    };

    @Override
    public List<T> findSubjects(DetachedCriteria dc)
    {
        List<T> out = null;

        if (dc == null)
        {
            dc = getQuery();
        }

        try {

            out = super.getCriterion(dc);

        } catch (DaoException e) {
            e.printStackTrace();
        }

        return (List<T>) out;
    };


}
