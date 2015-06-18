package nla.local.services;

import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.pojos.dict.CatalogDependency;
import nla.local.pojos.dict.DependencyData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by belonovich on 23.04.2015.
 */
public interface ICatalogDependencyService {

  public List<CatalogDependency> getAllCatalogDependencies() throws DaoException;

    public Serializable add(CatalogDependency catalogDependency) throws DaoException;

    public CatalogDependency getCatalogDependency(Serializable id) throws DaoException;

    public void update(CatalogDependency catalogDependency) throws DaoException;

    public List<CatalogDependency> getDependencyByParentId(Integer id) throws DaoException;

    public void delete(CatalogDependency catalogDependency) throws DaoException;

    public List<DependencyData> findByParentCodeAndTypes(Integer id, Integer type, Integer parentType) throws ServiceDaoException;

    public List<DependencyData> findByParentCodeAndDependencyId(Integer id, Integer parentId) throws ServiceDaoException;

    public DependencyData get(Serializable id) throws DaoException;

    public void delete(DependencyData dependencyData) throws DaoException;

    public Serializable add(DependencyData dependencyData) throws DaoException;
}
