package nla.local.dao.impl;

import nla.local.dao.ICatalogDAO;
import nla.local.dao.exceptions.DaoErrorCode;
import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.dict.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by belonovich on 04.05.2015.
 */
@Repository
public class CatalogDAO extends BaseDao<CatalogItem> implements ICatalogDAO {

    @Autowired
    public CatalogDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List getAllCatalogTypes() throws DaoException {
        try {
            return getSession().createCriteria(CatalogType.class).list();
        } catch (Exception e) {
            throw new DaoException(e, DaoErrorCode.NKANET_DAO_001);
        }
    }

    public List<CatalogItem> getAllCatalogItems() throws DaoException {
        return super.getAll(CatalogItem.class);
    }

    public CatalogType getCatalogTypeByAnalyticType(Integer id) throws DaoException {
        try {
            Criteria criteria = getSession().createCriteria(CatalogType.class);
            criteria.add(Restrictions.eq("analytic_type", id));
            return (CatalogType) criteria.uniqueResult();
        } catch (Exception e) {
            throw new DaoException(e, DaoErrorCode.NKANET_DAO_000);
        }
    }

    public List<CatalogItem> getCatalogItemsByTyp(Integer a_type) throws DaoException {
        try {
            return getSession().createCriteria(CatalogItem.class).add(Restrictions.eq("analytic_type", a_type)).list();
        } catch (Exception e) {
            throw new DaoException(e, DaoErrorCode.NKANET_DAO_001);
        }
    }

    public List<CatalogItem> getDependedCatalogItems(List<DependencyData> list) throws DaoException {
        try {
            Criteria criteria = getSession().createCriteria(CatalogItem.class);
            List<Integer> listFields = new LinkedList();
            for (DependencyData dependencyData : list) {
                listFields.add(dependencyData.getAnalyticCode());
            }
            criteria.add(Restrictions.in("code_id", listFields));
            if (list.size() > 0)
                criteria.add(Restrictions.eq("analytic_type", list.get(0).getCatalogDependency().getAnalyticType().getAnalytic_type()));
            return criteria.list();
        } catch (Exception e) {
            throw new DaoException(e, DaoErrorCode.NKANET_DAO_001);
        }
    }


    public void deleteCatalogItemWithDependency(CatalogPk catalogPk) throws DaoException {
        try {
            Session session = getSession();
            List list = session.createCriteria(DependencyData.class)
                    .add(Restrictions.eq("analyticCode", catalogPk.getCode_id()))
                    .createCriteria("catalogDependency")
                    .add(Restrictions.eq("analyticTypeId", catalogPk.getAnalytic_type())).list();
            list.addAll(session.createCriteria(DependencyData.class)
                    .add(Restrictions.eq("parentAnalyticCode", catalogPk.getCode_id()))
                    .createCriteria("catalogDependency")
                    .add(Restrictions.eq("parentAnalyticTypeId", catalogPk.getAnalytic_type())).list());
            for (Object catalog : list) {
                getSession().delete(catalog);
            }
            getSession().delete(getSession().get(CatalogItem.class, catalogPk));
        } catch (Exception e) {
            getSession().getTransaction().rollback();
            throw new DaoException(e, DaoErrorCode.NKANET_DAO_004);
        }
    }

    public void deleteCatalogTypeWithDependency(Integer catalogType) throws DaoException {
        Session session = getSession();

        try {
            List list = session.createCriteria(CatalogDependency.class).add(Restrictions.or(
                    Restrictions.eq("parentAnalyticTypeId", catalogType),
                    Restrictions.eq("analyticTypeId", catalogType)
            )).list();
            list.addAll(session.createCriteria(CatalogItem.class).add(Restrictions.eq("analytic_type", catalogType)).list());
            CatalogType type = (CatalogType) session.get(CatalogType.class, catalogType);
            for (Object catalog : list) {
                getSession().delete(catalog);
            }
            getSession().delete(type);

        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new DaoException(e, DaoErrorCode.NKANET_DAO_004);
        }
    }

    public void update(CatalogType catalogType) throws DaoException {
        try {
            getSession().update(catalogType);
        } catch (Exception e) {
            throw new DaoException(e, DaoErrorCode.NKANET_DAO_003);
        }
    }

    public void update(CatalogItem catalogItem) throws DaoException {
        super.update(catalogItem);
    }


    public void delete(CatalogType catalogType) throws DaoException {
        try {
            getSession().delete(catalogType);
        } catch (Exception e) {
            throw new DaoException(e, DaoErrorCode.NKANET_DAO_004);
        }
    }

    public void delete(CatalogItem catalogItem) throws DaoException {
        super.delete(catalogItem);
    }

    public Serializable add(CatalogType catalogType) throws DaoException {
        try {
            return getSession().save(catalogType);
        } catch (Exception e) {
            throw new DaoException(e, DaoErrorCode.NKANET_DAO_002);
        }
    }

    public Serializable add(CatalogItem catalogItem) throws DaoException {
        return super.add(catalogItem);
    }

    public CatalogItem getCatalogItemByPk(CatalogPk catalogPk) throws DaoException {
        try {
            return (CatalogItem) getSession().get(CatalogItem.class, catalogPk);
        } catch (Exception e) {
            throw new DaoException(e, DaoErrorCode.NKANET_DAO_000);
        }
    }

    public CatalogItem getCatalogItem(Integer catType, Integer catCode) throws DaoException {

        List<CatalogItem> ret_val_list = new ArrayList<CatalogItem>();

        try {
            if ( catType != null && catCode != null) {

                Criteria itemCri = getSession().createCriteria(CatalogItem.class).add(Restrictions.eq("analytic_type",catType)).add(Restrictions.eq("code_id", catCode));

                ret_val_list = itemCri.list();

            }

        } catch (Exception e) {
            throw new DaoException(e, DaoErrorCode.NKANET_DAO_000);
        }

        return ret_val_list.size() > 0 ? ret_val_list.get(0) : null ;
    }

}
