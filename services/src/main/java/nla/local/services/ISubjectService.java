package nla.local.services;

import nla.local.dao.exceptions.DaoException;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;


public interface ISubjectService<T> extends IService<T> {

    public void addSubject(T t) throws DaoException;

    public T getSubject(Serializable id) throws DaoException;

    public void refreshSubject(T t) throws DaoException;

    public List<T> findSubjects(DetachedCriteria dc) throws DaoException;

}
