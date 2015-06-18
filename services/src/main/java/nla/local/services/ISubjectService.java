package nla.local.services;

import nla.local.exception.ServiceDaoException;
import nla.local.pojos.subjects.Person;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;


public interface ISubjectService<T extends Person> extends IService<T> {

    public T getSubject(Serializable id) throws ServiceDaoException;

    public void refreshSubject(T t) throws ServiceDaoException;

    public List<T> findSubjects(DetachedCriteria dc) throws ServiceDaoException;

}
