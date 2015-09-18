package nla.local.services.impl;

import nla.local.dao.ICatalogDAO;
import nla.local.dao.exceptions.DaoErrorCode;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.pojos.dict.*;
import nla.local.services.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by belonovich on 15.04.2015.
 */
@Service
@Transactional
public class CatalogServiceImp implements ICatalogService {

    @Autowired
    private ICatalogDAO catalogDAO;

    @Override
    public List<CatalogType> getAllCatalogTypes() throws ServiceDaoException {
        try {
            return catalogDAO.getAllCatalogTypes();
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(),e.getCode(),e.getParams());
        }
    }

    @Override
    public CatalogType getCatalogTypeByAnalyticType( Integer id) throws ServiceDaoException {
        try {
            return catalogDAO.getCatalogTypeByAnalyticType(id);
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(),e.getCode(),e.getParams());
        }
    }

    @Override
    public List<CatalogItem> getCatalogItemsByTyp( Integer a_type) throws ServiceDaoException {
        try {
            return  catalogDAO.getCatalogItemsByTyp(a_type);
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(),e.getCode(),e.getParams());
        }
    }

    @Override
    public List<CatalogItem> getDependedCatalogItems( List<DependencyData> list) throws ServiceDaoException {
        try {
            return catalogDAO.getDependedCatalogItems(list);
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(),e.getCode(),e.getParams());
        }
    }

    @Override
    public CatalogItem getCatalogItem( CatalogPk dPk) throws ServiceDaoException {
        try {
            return catalogDAO.getCatalogItemByPk(dPk);
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(),e.getCode(),e.getParams());
        }
    }

    @Override
    public CatalogItem getCatalogItem( Integer catType, Integer catCode) throws ServiceDaoException {
        try {
            return catalogDAO.getCatalogItem(catType, catCode);
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(),e.getCode(),e.getParams());
        }
    }




    @Override
    public List<CatalogItem> getAllCatalogItems() throws DaoException {
        try {
            return catalogDAO.getAllCatalogItems();
        }catch (Exception e){
            throw new ServiceDaoException(e.fillInStackTrace(),DaoErrorCode.NKANET_DAO_000, e.getSuppressed());
        }
    }


    public void deleteCatalogItemWithDependency( CatalogPk catalogPk) throws ServiceDaoException {
        try {
            catalogDAO.deleteCatalogItemWithDependency(catalogPk);
        } catch (Exception e) {
            throw new ServiceDaoException(e.fillInStackTrace(),DaoErrorCode.NKANET_DAO_000, e.getSuppressed());
        }
    }


    public void deleteCatalogTypeWithDependency( Integer catalogType) throws ServiceDaoException {
        try {
            catalogDAO.deleteCatalogTypeWithDependency(catalogType);
        } catch (Exception e) {
            throw new ServiceDaoException(e.fillInStackTrace(),DaoErrorCode.NKANET_DAO_000, e.getSuppressed());
        }
    }

    
    public void update( CatalogType catalogType) throws ServiceDaoException {
        try {
            catalogDAO.update(catalogType);
        } catch (Exception e) {
            throw new ServiceDaoException(e.fillInStackTrace(), DaoErrorCode.NKANET_DAO_003,e.getSuppressed());
        }
    }

    
    public void update( CatalogItem catalogItem) throws ServiceDaoException {
        try {
            catalogDAO.update(catalogItem);
        } catch (Exception e) {
            throw new ServiceDaoException(e.fillInStackTrace(), DaoErrorCode.NKANET_DAO_003,e.getSuppressed());
        }
    }

    
    public void delete( CatalogType catalogType) throws ServiceDaoException {
        try {
            catalogDAO.delete(catalogType);
        } catch (Exception e) {
            throw new ServiceDaoException(e.fillInStackTrace(), DaoErrorCode.NKANET_DAO_004,e.getSuppressed());
        }
    }

    
    public void delete( CatalogItem catalogItem) throws ServiceDaoException {
        try {
            catalogDAO.delete(catalogItem);
        } catch (Exception e) {
            throw new ServiceDaoException(e.fillInStackTrace(), DaoErrorCode.NKANET_DAO_004,e.getSuppressed());
        }
    }

    
    public void add( CatalogType catalogType) throws ServiceDaoException {
        try {
            catalogDAO.add(catalogType);
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(), DaoErrorCode.NKANET_DAO_004,e.getSuppressed());
        }
    }

    
    public void add( CatalogItem catalogItem) throws ServiceDaoException {
        try {
            catalogDAO.add(catalogItem);
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(), DaoErrorCode.NKANET_DAO_004,e.getSuppressed());
        }
    }
}
