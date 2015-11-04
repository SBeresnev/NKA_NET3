package nla.local.dao;


import nla.local.dao.exceptions.DaoException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface Dao<T> {

    public Serializable add(T t) throws DaoException;

    public void update(T t) throws DaoException;

    public T get(Class clazz, Serializable id) throws DaoException;

    public List<T> getCriterion(DetachedCriteria crio) throws DaoException;

    public List<T> getAll(Class<T> clazz) throws DaoException;

    public void delete(T t) throws DaoException;

    public void refresh(T t) throws DaoException;

    public Session getSession() ;

    public Class<T>  getType()

}
