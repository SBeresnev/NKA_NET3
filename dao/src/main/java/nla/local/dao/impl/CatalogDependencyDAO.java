package nla.local.dao.impl;

import nla.local.dao.ICatalogDependencyDAO;
import nla.local.dao.exceptions.DaoErrorCode;
import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.dict.CatalogDependency;
import nla.local.pojos.dict.DependencyData;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by belonovich on 04.05.2015.
 */
@Repository
public class CatalogDependencyDAO extends BaseDao<CatalogDependency> implements ICatalogDependencyDAO {

    @Autowired
    public CatalogDependencyDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    //region CatalogDependency
    public List<CatalogDependency> getAllCatalogDependencies() throws DaoException {
        return super.getAll(CatalogDependency.class);
    }

    public Serializable add(CatalogDependency catalogDependency) throws DaoException {
        return super.add(catalogDependency);
    }

    public CatalogDependency getCatalogDependency(Serializable id) throws DaoException {
        return super.get(CatalogDependency.class, id);
    }

    public void update(CatalogDependency catalogDependency) throws DaoException {
        super.update(catalogDependency);
    }

    public List<CatalogDependency> getDependencyByParentId(Integer id) throws DaoException {
        try {
            Criteria criteria = getSession().createCriteria(CatalogDependency.class);
            return criteria.add(Restrictions.eq("parentAnalyticTypeId", id)).list();
        } catch (Exception e) {
            throw new DaoException(e, DaoErrorCode.NKANET_DAO_001);
        }
    }

    public void delete(CatalogDependency catalogDependency) throws DaoException {
        super.delete(catalogDependency);
    }
    //endregion

    //region DependencyData
    public List<DependencyData> findByParentCodeAndTypes(Integer id, Integer type, Integer parentType) throws DaoException {
        try {
            Criteria criteria = getSession().createCriteria(DependencyData.class);
            criteria.add(Restrictions.eq("parentAnalyticCode", id));
            criteria.createCriteria("catalogDependency").add(Restrictions.eq("analyticTypeId", type)).add(Restrictions.eq("parentAnalyticTypeId", parentType));
            return criteria.list();
        } catch (Exception e) {
            throw new DaoException(e, DaoErrorCode.NKANET_DAO_001);
        }
    }

    public List<DependencyData> findByParentCodeAndDependencyId(Integer id, Integer parentId) throws DaoException {
        try {
            Criteria criteria = getSession().createCriteria(DependencyData.class);
            criteria.add(Restrictions.eq("dependencyId", id));
            criteria.add(Restrictions.eq("parentAnalyticCode", parentId));
            return criteria.list();
        } catch (Exception e) {
            throw new DaoException(e, DaoErrorCode.NKANET_DAO_001);
        }
    }

    public DependencyData get(Serializable id) throws DaoException {
        try {
            return (DependencyData) getSession().get(DependencyData.class, id);
        } catch (Exception e) {
            throw new DaoException(e, DaoErrorCode.NKANET_DAO_000);
        }
    }

    public void delete(DependencyData dependencyData) throws DaoException {
        try {
            getSession().delete(dependencyData);
        } catch (Exception e) {
            throw new DaoException(e, DaoErrorCode.NKANET_DAO_004);
        }
    }

    public Serializable add(DependencyData dependencyData) throws DaoException {
        try {
            return getSession().save(dependencyData);
        } catch (Exception e) {
            throw new DaoException(e, DaoErrorCode.NKANET_DAO_002);
        }
    }
    //endregion
}
