package nla.local.dao;

import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.dict.CatalogPk;
import nla.local.pojos.dict.CatalogType;
import nla.local.pojos.dict.DependencyData;

import java.io.Serializable;
import java.util.List;

/**
 * Created by belonovich on 05.05.2015.
 */
public interface ICatalogDAO extends Dao<CatalogItem> {

    public List getAllCatalogTypes() throws DaoException;

    public List getAllCatalogItems() throws DaoException;

    public CatalogType getCatalogTypeByAnalyticType(Integer id) throws DaoException;

    public List<CatalogItem> getCatalogItemsByTyp(Integer a_type) throws DaoException;

    public List<CatalogItem> getDependedCatalogItems(List<DependencyData> list) throws DaoException;

    public void deleteCatalogItemWithDependency(CatalogPk catalogPk) throws DaoException;

    public void deleteCatalogTypeWithDependency(Integer catalogType) throws DaoException;

    public void update(CatalogType catalogType) throws DaoException;

    public void delete(CatalogType catalogType) throws DaoException;

    public Serializable add(CatalogType catalogType) throws DaoException;

    public CatalogItem getCatalogItemByPk(CatalogPk catalogPk) throws DaoException;

    public CatalogItem getCatalogItem(Integer catType, Integer catCode) throws DaoException;

}
