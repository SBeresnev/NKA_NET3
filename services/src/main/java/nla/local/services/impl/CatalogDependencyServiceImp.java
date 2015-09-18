package nla.local.services.impl;

import nla.local.dao.ICatalogDependencyDAO;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.pojos.dict.CatalogDependency;
import nla.local.pojos.dict.DependencyData;
import nla.local.services.ICatalogDependencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by belonovich on 23.04.2015.
 */

@Service
@Transactional
public class CatalogDependencyServiceImp implements ICatalogDependencyService {

    @Autowired
    private ICatalogDependencyDAO catalogDependencyDAO;

    //region CatalogDependency
    public List<CatalogDependency> getAllCatalogDependencies() throws ServiceDaoException {
        try {
            return catalogDependencyDAO.getAllCatalogDependencies();
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(), e.getCode(), e.getParams());
        }
    }

    public Serializable add(CatalogDependency catalogDependency) throws ServiceDaoException {
        try {
            return catalogDependencyDAO.add(catalogDependency);
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(), e.getCode(), e.getParams());
        }
    }

    public List<CatalogDependency> getDependencyByChildId(Integer id) throws ServiceDaoException
    {
        try{

            return catalogDependencyDAO.getDependencyByChildId(id);

        }catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(), e.getCode(), e.getParams());
        }
    }


    public CatalogDependency getCatalogDependency(Serializable id) throws ServiceDaoException {
        try {
            return catalogDependencyDAO.getCatalogDependency(id);
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(), e.getCode(), e.getParams());
        }
    }

    public void update(CatalogDependency catalogDependency) throws ServiceDaoException {
        try {
            catalogDependencyDAO.update(catalogDependency);
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(), e.getCode(), e.getParams());
        }
    }

    public List<CatalogDependency> getDependencyByParentId(Integer id) throws ServiceDaoException {
        try {
            return catalogDependencyDAO.getDependencyByParentId(id);
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(), e.getCode(), e.getParams());
        }
    }

    public void delete(CatalogDependency catalogDependency) throws ServiceDaoException {
        try {
            catalogDependencyDAO.delete(catalogDependency);
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(), e.getCode(), e.getParams());
        }
    }
    //endregion

    //region DependencyData
    public List<DependencyData> findByParentCodeAndTypes(Integer id, Integer type, Integer parentType) throws ServiceDaoException {
        try {
            return catalogDependencyDAO.findByParentCodeAndTypes(id, type, parentType);
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(), e.getCode(), e.getParams());
        }
    }

    public List<DependencyData> findByChildCodeAndTypes(Integer id, Integer childType, Integer type) throws ServiceDaoException {
        try {
            return catalogDependencyDAO.findByChildCodeAndTypes(id, childType, type);
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(), e.getCode(), e.getParams());
        }
    }

    public List<DependencyData> findByParentCodeAndDependencyId(Integer id, Integer parentId) throws ServiceDaoException {
        try {
            return catalogDependencyDAO.findByParentCodeAndDependencyId(id, parentId);
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(), e.getCode(), e.getParams());
        }
    }

    public DependencyData get(Serializable id) throws ServiceDaoException {
        try {
            return catalogDependencyDAO.get(id);
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(), e.getCode(), e.getParams());
        }
    }

    public void delete(DependencyData dependencyData) throws ServiceDaoException {
        try {
            catalogDependencyDAO.delete(dependencyData);
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(), e.getCode(), e.getParams());
        }
    }

    public Serializable add(DependencyData dependencyData) throws ServiceDaoException {
        try {
            return catalogDependencyDAO.add(dependencyData);
        } catch (DaoException e) {
            throw new ServiceDaoException(e.fillInStackTrace(), e.getCode(), e.getParams());
        }
    }
    //endregion
}
