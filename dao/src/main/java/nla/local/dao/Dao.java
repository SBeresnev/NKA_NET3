package nla.local.dao;


import nla.local.dao.exceptions.DaoException;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

public interface Dao<T> {

    void add(T t) throws DaoException;

    void update(T t) throws DaoException;

    T get(Class<T> clazz, Serializable id) throws DaoException;

    public List<T> getCriterion(DetachedCriteria crio) throws DaoException;

    List<T> getAll(Class<T> clazz) throws DaoException;

    void delete(T t) throws DaoException;

    void refresh(T t) throws DaoException;

    public Class<T>  getType();
}
