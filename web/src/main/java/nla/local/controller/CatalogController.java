package nla.local.controller;


import nla.local.controller.forms.CatalogForm;
import nla.local.controller.forms.FormCatalogDependency;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.pojos.dict.*;
import nla.local.services.ICatalogDependencyService;
import nla.local.services.impl.CatalogServiceImp ;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by belonovich on 06.03.2015.
 */
@RestController
@RequestMapping({"/catalog"})
public class CatalogController {


    private static final Logger logger = Logger.getLogger(CatalogController.class);

    @Autowired
    private CatalogServiceImp catalogServiceImpl;

    @Autowired
    private ICatalogDependencyService catalogDependencyService;

    ////////////////////////////////////////rights//////////////////////////////////////////////////////////////////////

    @RequestMapping(value = {"/rightCountType"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<CatalogItem> getRightCountType() throws DaoException {
        logger.info("root - /catalog/rightCountType");
        return this.catalogServiceImpl.getCatalogItemsByTyp(Integer.decode(CatalogConstants.RIGHT_COUNT_TYPE));
    }

    @RequestMapping(value = {"/rightEntityType"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<CatalogItem> getRightEntytyType() throws DaoException {
        logger.info("root - /catalog/rightEntityType");
        return this.catalogServiceImpl.getCatalogItemsByTyp(Integer.decode(CatalogConstants.RIGHT_ENTITY_TYPE));
    }

    @RequestMapping(value = {"/rightType"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<CatalogItem> getRightType() throws DaoException {
        logger.info("root - /catalog/rightType");
        return this.catalogServiceImpl.getCatalogItemsByTyp(Integer.decode(CatalogConstants.RIGHT_TYPE));
    }

    ////////////////////////////////////////objects///////////////////////////////////////////////////////////////////

    @RequestMapping(value = {"/objecttype"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<CatalogItem> getObjectType() throws DaoException {
        logger.info("root - /catalog/objecttype");
        return this.catalogServiceImpl.getCatalogItemsByTyp(Integer.decode(CatalogConstants.OBJECT_TYP));
    }

    ////////////////////////////////////////subjects///////////////////////////////////////////////////////////////////

    @RequestMapping(value = {"/states"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<CatalogItem> getStates() throws DaoException {
        logger.info("root - /catalog/states");
        return this.catalogServiceImpl.getCatalogItemsByTyp(Integer.decode(CatalogConstants.STATE));
    }

    @RequestMapping(value = {"/subjectTypes"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<CatalogItem> getSubjectType() throws DaoException {
        logger.info("root - /catalog/subjectTypes");
        return this.catalogServiceImpl.getCatalogItemsByTyp(Integer.decode(CatalogConstants.SUBJECT_TYP));
    }

    @RequestMapping(value = {"/OrgStruct"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<CatalogItem> getOrgStruct() throws DaoException {
        logger.info("root - /catalog/OrgStruct");
        return this.catalogServiceImpl.getCatalogItemsByTyp(Integer.decode(CatalogConstants.ORG_STRUCTURE));
    }

    @RequestMapping(value = {"/TorStruct"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<CatalogItem> getTorStruct() throws DaoException {
        logger.info("root - /catalog/TorStruct");
        return this.catalogServiceImpl.getCatalogItemsByTyp(Integer.decode(CatalogConstants.TOR_STRUCTURE));
    }

    ////////////////////////////////////////oprations///////////////////////////////////////////////////////////////////

    @RequestMapping(value = {"/operationType"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<CatalogItem> getOperationType() throws DaoException {
        logger.info("root - /catalog/operationType");
        return this.catalogServiceImpl.getCatalogItemsByTyp(Integer.decode(CatalogConstants.OPERATION_TYP));
    }

    @RequestMapping(value = {"/operationSubType"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<CatalogItem> getOperationSubType() throws DaoException {
        logger.info("root - /catalog/operationSubType");
        return this.catalogServiceImpl.getCatalogItemsByTyp(Integer.decode(CatalogConstants.OPERATION_SUB_TYP));
    }

    @RequestMapping(value = {"/operationBase"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<CatalogItem> getOperationBase() throws DaoException {
        logger.info("root - /catalog/operationBase");
        return this.catalogServiceImpl.getCatalogItemsByTyp(Integer.decode(CatalogConstants.OPERATION_BASE));
    }


    @RequestMapping(value = {"/elementTypeDepend"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<CatalogItem> getElementTypeDepend() throws DaoException {
        logger.info("root - /catalog/elementTypeDepend");
        return this.catalogServiceImpl.getCatalogItemsByTyp(Integer.decode(CatalogConstants.ELEMENT_TYPE_DEPEND));
    }

    ////////////////////////////////////////catalogs///////////////////////////////////////////////////////////////////

    @RequestMapping(value = "get_catalogs_by_type", method = {RequestMethod.GET})
    public List<CatalogItem> getByType(int type) throws ServiceDaoException {
        logger.info("root - catalog/get_catalogs_by_type");
        return this.catalogServiceImpl.getCatalogItemsByTyp(type);
    }

    @RequestMapping(value = "get_catalog_by_id", method = {RequestMethod.GET})
    public CatalogItem getCatalogByPk(CatalogPk catalogPk) throws ServiceDaoException {
        logger.info("root - catalog/get_catalog_by_id");
        return this.catalogServiceImpl.getCatalogItem(catalogPk);
    }

    @RequestMapping(value = "delete_catalog_by_id", method = {RequestMethod.DELETE})
    public List<CatalogItem> deleteCatalogByPk(CatalogPk catalogPk) throws ServiceDaoException {
        logger.info("root - catalog/delete_catalog_by_id");
        catalogServiceImpl.deleteCatalogItemWithDependency(catalogPk);
        return catalogServiceImpl.getCatalogItemsByTyp(catalogPk.getAnalytic_type());
    }

    @RequestMapping(value = "update_catalog", method = {RequestMethod.PUT})
    public List<CatalogItem> updateCatalog(@RequestBody CatalogForm catalogForm) throws ServiceDaoException {
        logger.info("root - catalog/update_catalog");
        CatalogPk catalogPk = new CatalogPk(catalogForm.getCode_id(), catalogForm.getAnalytic_type());
        CatalogItem catalogItem = catalogServiceImpl.getCatalogItem(catalogPk);
        catalogForm.copyInCatalogAndGet(catalogItem);
        catalogServiceImpl.update(catalogItem);
        return catalogServiceImpl.getCatalogItemsByTyp(catalogForm.getAnalytic_type());
    }

    @RequestMapping(value = "add_catalog", method = {RequestMethod.POST})
    public List<CatalogItem> addCatalog(@RequestBody CatalogItem catalogItem) throws ServiceDaoException {
        logger.info("root - catalog/add_catalog");
        catalogServiceImpl.add(catalogItem);
        return catalogServiceImpl.getCatalogItemsByTyp(catalogItem.getAnalytic_type());
    }

    @RequestMapping(value = "add_catalog_type", method = {RequestMethod.POST})
    public void addCatalogType(@RequestBody CatalogType type) throws ServiceDaoException {
        logger.info("root - catalog/add_catalog_type");
        catalogServiceImpl.add(type);
    }

    @RequestMapping(value = "get_all_types", method = {RequestMethod.GET})
    public List<CatalogType> getAllTypes() throws DaoException {
        logger.info("root - catalog/get_all_types");
        return catalogServiceImpl.getAllCatalogTypes();
    }

    @RequestMapping(value = "get_type_by_id", method = {RequestMethod.GET})
    public CatalogType getByAnalyticType(Integer id) throws DaoException {
        logger.info("root - catalog/get_type_by_id");
        return catalogServiceImpl.getCatalogTypeByAnalyticType(id);
    }

    @RequestMapping(value = "deleted_type_by_id", method = {RequestMethod.DELETE})
    public void deleteById(Integer analytic_type) throws DaoException {
        logger.info("root - catalog/deleted_type_by_id");
        catalogServiceImpl.deleteCatalogTypeWithDependency(analytic_type);
    }

    @RequestMapping(value = "get_all_analytic_dependencies", method = {RequestMethod.GET})
    public List<CatalogDependency> getDependency() throws DaoException {
        return catalogDependencyService.getAllCatalogDependencies();
    }

    @RequestMapping(value = "add_analytic_dependency", method = {RequestMethod.POST})
    public List<CatalogDependency> addDependency(@RequestBody FormCatalogDependency formCatalogDependency) throws DaoException {
        CatalogDependency catalogDependency = new CatalogDependency();
        catalogDependency.setAnalyticTypeId(formCatalogDependency.getAnalyticTypeId());
        catalogDependency.setParentAnalyticTypeId(formCatalogDependency.getParentAnalyticTypeId());
        catalogDependencyService.add(catalogDependency);
        return catalogDependencyService.getDependencyByParentId(formCatalogDependency.getParentAnalyticTypeId());
    }

    @RequestMapping(value = "delete_analytic_dependency", method = {RequestMethod.DELETE})
    public List<CatalogDependency> deleteDependency(Integer id) throws DaoException {
        CatalogDependency catalogDependency = catalogDependencyService.getCatalogDependency(id);
        catalogDependencyService.delete(catalogDependency);
        return catalogDependencyService.getDependencyByParentId(catalogDependency.getParentAnalyticTypeId());
    }

    @RequestMapping(value = "update_analytic_dependency", method = {RequestMethod.GET})
    public void updateDependency(CatalogDependency catalogDependency) throws DaoException {
        catalogDependencyService.update(catalogDependency);
    }

    @RequestMapping(value = "get_catalog_dependency_by_parent_id", method = {RequestMethod.GET})
    public List<CatalogDependency> getDependencyParentById(Integer parentId) throws DaoException {
        return catalogDependencyService.getDependencyByParentId(parentId);
    }

    @RequestMapping(value = "get_analytic_depended_item", method = {RequestMethod.GET})
    public List getDependedItem(Integer id, Integer type, Integer parentType) throws DaoException {
        List<DependencyData> list = new ArrayList<DependencyData>(catalogDependencyService.findByParentCodeAndTypes(id, type, parentType));
        return list.size() != 0 ? catalogServiceImpl.getDependedCatalogItems(list) : Collections.EMPTY_LIST;
    }


    @RequestMapping(value = "delete_dependency_data", method = {RequestMethod.DELETE})
    public List deleteDependencyData(Integer idDependency, Integer idCode, Integer idParentCode) throws DaoException {
        DependencyDataPk dependencyDataPk = new DependencyDataPk(idDependency, idCode, idParentCode);
        catalogDependencyService.delete(catalogDependencyService.get(dependencyDataPk));
        List listDependencyData = catalogDependencyService.findByParentCodeAndDependencyId(idDependency, idParentCode);
        return listDependencyData.size() != 0 ? catalogServiceImpl.getDependedCatalogItems(listDependencyData) : Collections.EMPTY_LIST;
    }

    @RequestMapping(value = "add_dependency_data", method = {RequestMethod.POST})
    public List addDependedData(@RequestBody DependencyDataPk dependencyDataPk) throws DaoException {
        DependencyData dependencyData = new DependencyData(dependencyDataPk);
        dependencyData.setAnalyticValue(1);
        catalogDependencyService.add(dependencyData);
        return catalogServiceImpl.getDependedCatalogItems(catalogDependencyService.findByParentCodeAndDependencyId(dependencyDataPk.getDependencyId(), dependencyDataPk.getParentAnalyticCode()));
    }

    @RequestMapping(value = "get_catalog_dependency_by_child_id", method = {RequestMethod.POST})
    public List<CatalogDependency> getDependencyByChildId (Integer childId) throws ServiceDaoException {

        List<CatalogDependency> ret_val = catalogDependencyService.getDependencyByChildId(childId);

        return ret_val;

    }


    @RequestMapping(value = {"/childCodeAndType"}, method = {RequestMethod.GET})
    public List<DependencyData> operSubTypeDep(Integer id, Integer childType, Integer parentType ) throws ServiceDaoException {

        logger.info("root - /catalog/childCodeAndType");

        List<DependencyData> ret_val = catalogDependencyService.findByChildCodeAndTypes(id, childType, parentType);

        return ret_val;

    }

    //endregion

}
