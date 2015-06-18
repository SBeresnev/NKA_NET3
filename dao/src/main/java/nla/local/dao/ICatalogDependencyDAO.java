package nla.local.dao;

import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.dict.CatalogDependency;
import nla.local.pojos.dict.DependencyData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by belonovich on 05.05.2015.
 */
public interface ICatalogDependencyDAO extends Dao<CatalogDependency> {

    //region CatalogDependency
    public List<CatalogDependency> getAllCatalogDependencies() throws DaoException;

    public CatalogDependency getCatalogDependency(Serializable id) throws DaoException;

    public List<CatalogDependency> getDependencyByParentId(Integer id) throws DaoException;
    //endregion

    //region DependencyData
    public List<DependencyData> findByParentCodeAndTypes(Integer id, Integer type, Integer parentType) throws DaoException;

    public List<DependencyData> findByParentCodeAndDependencyId(Integer id, Integer parentId) throws DaoException;

    public DependencyData get(Serializable id) throws DaoException;

    public void delete(DependencyData dependencyData) throws DaoException;

    public Serializable add(DependencyData dependencyData) throws DaoException;
    //endregion
}
