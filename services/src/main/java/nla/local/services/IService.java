package nla.local.services;

/**
 * Created by beresnev on 29.01.2015.
 */

import nla.local.exception.ServiceDaoException;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public interface IService<T>  {

    void add(T t) throws ServiceDaoException;

    void update(T t) throws ServiceDaoException;

    T get(Class<T> clazz, Serializable id) throws ServiceDaoException;

    void delete(T t) throws ServiceDaoException;

    void refresh(T t) throws ServiceDaoException;

    List<T> getCriterion(DetachedCriteria crio) throws ServiceDaoException;

    List<T> getAll(Class<T> clazz) throws ServiceDaoException;

}