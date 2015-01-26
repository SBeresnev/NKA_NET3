/*
 * Copyright (C) 2014 GHX, Inc.
 *  Louisville, Colorado, USA.
 *  All rights reserved.
 *
 *  Warning: Unauthorized reproduction or distribution of this program, or
 *  any portion of it, may result in severe civil and criminal penalties,
 *  and will be prosecuted to the maximum extent possible under the law.
 *
 *  Created on 023 23.06.2014
 */
package nla.local.services;

import nla.local.dao.Dao;
import nla.local.dao.exceptions.DaoException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


@Service
@Transactional
public class BaseService<T> implements IService<T> {

    private Dao<T> baseDao;

    private  Class<T> type;

    private DetachedCriteria query;


    @Autowired
    public BaseService(Dao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void add(T t)  {

        try {

            baseDao.add(t);

        } catch (DaoException e) {

            e.printStackTrace();
        }


    }

    @Override
    public void update(T t) {

        try {
            baseDao.update(t);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<T> getCriterion(DetachedCriteria  dc) {
        List<T> out = null;

        if (dc == null) { dc = getQuery(); }
        else { setQuery(dc);}

        try {

          out = baseDao.getCriterion(query);

        } catch (DaoException e) {

            e.printStackTrace();
        }

        return out;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public T get(Class<T> clazz, Serializable id) {

        T oi = null;
        try {
            oi = baseDao.get(clazz, (Serializable) id);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return oi;
    }

    @Override
    public void delete(T t) {

        try {
            baseDao.delete(t);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh(T t) {

        try {
            baseDao.refresh(t);
        } catch (DaoException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Class<T> getType()
    { return  baseDao.getType();}

    public DetachedCriteria getQuery() {
        return query;
    }

    public void setQuery(DetachedCriteria query) {
        this.query = query;
    }
}
