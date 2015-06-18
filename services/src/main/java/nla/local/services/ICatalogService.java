package nla.local.services;

import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.dict.CatalogPk;
import nla.local.pojos.dict.CatalogType;
import nla.local.pojos.dict.DependencyData;

import java.util.List;

/**
 * Created by belonovich on 15.04.2015.
 */
public interface ICatalogService {

    public List<CatalogType> getAllCatalogTypes() throws ServiceDaoException;

    public CatalogType getCatalogTypeByAnalyticType(Integer id) throws ServiceDaoException;

    public List<CatalogItem> getCatalogItemsByTyp(Integer a_type) throws ServiceDaoException;

    public List<CatalogItem> getDependedCatalogItems(List<DependencyData> list) throws ServiceDaoException;

    public CatalogItem getCatalogItem(CatalogPk dPk) throws ServiceDaoException;

    public List<CatalogItem> getAllCatalogItems() throws DaoException;
}
