package nla.local.services.impl;

import nla.local.dao.Dao;
import nla.local.dao.exceptions.DaoErrorCode;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.services.IService;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;

import java.util.List;

/**
 * Created by beresnev on 22.04.2015.
 */

@Service
@Transactional
public class BaseServiceImp<T> implements IService<T> {

    private Dao<T> baseDao;

    private SessionFactory sessionFactory;

    public Dao<T> getBaseDao() {
        return baseDao;
    }

    public SessionFactory getSessionFactory() {

        return sessionFactory;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Autowired
    public void setBaseDao(Dao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void add(T t)  throws ServiceDaoException {
        try {

            baseDao.add(t);

        } catch (DaoException e) {

            throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_002, t);

        }
    }

    @Override
    public void update(T t) throws ServiceDaoException {

        try {

            baseDao.update(t);

        } catch (DaoException e) {

            throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_003, t);

        }


    }

    @Override
    public T get(Class<T> clazz, Serializable id) throws ServiceDaoException {

        try{

        return baseDao.get(clazz, id);

        } catch (DaoException e) {

        throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_001, clazz.getCanonicalName() , id);
        }
    }

    @Override
    public void delete(T t) throws ServiceDaoException {
        try{

          baseDao.delete(t);

        } catch (DaoException e) {

        throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_004, t);


    }
    }

    @Override
    public void refresh(T t) throws ServiceDaoException {
      try
        {
        baseDao.refresh(t);

         } catch (DaoException e) {

        throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_003, t);

      }
    }

    @Override
    public List<T> getCriterion(DetachedCriteria crio) throws ServiceDaoException {

        List<T> ret_val = null;

        try {

            ret_val = baseDao.getCriterion(crio);


        } catch (DaoException e) {

            throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_005,null);

        }

        return  ret_val;
    }

    @Override
    public List<T> getAll(Class<T> clazz) throws ServiceDaoException {

        List<T> ret_val = null;

        try {

            ret_val = baseDao.getAll(clazz);

            } catch (DaoException e) {

            throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_005,null);

        }

        return  ret_val;

        }

}
