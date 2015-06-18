package nla.local.dao.impl;

import nla.local.dao.Dao;
import nla.local.dao.exceptions.DaoErrorCode;
import nla.local.dao.exceptions.DaoException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;


/**
 * Created by Serega on 15.09.2014.
 */

@Repository
public class BaseDao<T> implements Dao<T> {

    private static Logger log = Logger.getLogger(BaseDao.class);

    private SessionFactory sessionFactory;

    private Class<T> type;

    private String typeName;

    public BaseDao(Class<T> type) {

        this.type = type;

        this.typeName = type.getSimpleName();

        log.debug(String.format("Created Dao for %s.", typeName));

    }

    public Class<T>  getType() { return type;}

    public BaseDao() { }

    @Autowired
    public BaseDao(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;

  }

    public Session getSession() {

        return sessionFactory.getCurrentSession();

    }

    @Override
    public Serializable add(T t) throws DaoException {

        Serializable id = null;

        try{

            log.info("Save:" + t);

            id = getSession().save(t);


        } catch (HibernateException e ) {

            DaoException dEx = new DaoException(e, DaoErrorCode.NKANET_DAO_002, t);

            log.error(dEx.getMessage());

            throw dEx;

        }

        return  id;
    }

    @Override
    public void update(T t) throws DaoException{
        try{
            getSession().update(t);
            log.info("Update:" + t);
        } catch (HibernateException e) {
            DaoException dEx = new DaoException(e, DaoErrorCode.NKANET_DAO_003, t);
            log.error(dEx.getMessage());
            throw dEx;
        }
    }

    @Override
    public List<T> getAll(Class<T> clazz) throws DaoException {
        try {
            List<T> list = getSession().createCriteria(clazz).list();
            log.debug(String.format("Got %d products", list == null ? 0 : list.size()));
            return list;
        } catch (HibernateException e) {
            DaoException dEx = new DaoException(e, DaoErrorCode.NKANET_DAO_001, typeName);
            log.error(dEx.getMessage());
            throw dEx;
        }
    }

    @Override
    public T get(Class clazz, Serializable id) throws DaoException{
        log.info("Get:" + id.toString());
        try {
            log.debug(String.format("Get %s with id=%s.", clazz.getCanonicalName() , id));
            T obj = (T) getSession().get(clazz, id);
            return obj;
        } catch (HibernateException e) {
            DaoException dEx = new DaoException(e, DaoErrorCode.NKANET_DAO_000, clazz.getCanonicalName() , id);
            log.error(dEx.getMessage());
            throw dEx;
        }

    }

    @Override
    public void delete(T t) throws DaoException {
        try {
            log.debug(String.format("Delete %s: %s.", typeName, t));
            if (t != null) {
                log.info("Delete:" + t);
                getSession().delete(t);
            }
        } catch (HibernateException e) {
            DaoException dEx = new DaoException(e, DaoErrorCode.NKANET_DAO_004, t);
            log.error(dEx.getMessage());
            throw dEx;
        }
    }

    @Override
    public void refresh(T t) throws  DaoException{
        try {
            log.info("Refresh:" + t);
            getSession().refresh(t);
        }
        catch (HibernateException e)
        {
            DaoException dEx = new DaoException(e, DaoErrorCode.NKANET_DAO_006, typeName);
            log.error(dEx.getMessage());
            throw dEx;
        }
    }

    @Override
    public List<T> getCriterion(DetachedCriteria crio) throws DaoException {
        try {

            Criteria cria = crio.getExecutableCriteria(getSession());
            return cria.list();
        } catch (HibernateException e) {
            DaoException dEx = new DaoException(e, DaoErrorCode.NKANET_DAO_005, typeName);
            log.error(dEx.getMessage());
            throw dEx;}

    }

}
