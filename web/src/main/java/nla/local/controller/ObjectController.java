package nla.local.controller;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.object.Object_dest;
import nla.local.services.IObjectService;
import nla.local.services.impl.CatalogServiceImp;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Created by beresnev on 18.06.2015.
 */
@RestController
@RequestMapping({"/object"})
public class ObjectController {

    private static final Logger logger = Logger.getLogger(AddressController.class);

    @Autowired
    private IObjectService objectServiceImp;

    @Autowired
    private CatalogServiceImp catalogService;

    @RequestMapping(value = "/find_by_address", method = {RequestMethod.GET})
    public List<Object_dest> find_by_address(Long address_id) throws ServiceDaoException, ServiceException {

        logger.info("root - /object/find_by_address");

        return objectServiceImp.findObjectbyAddressCommon( Arrays.asList(address_id));

    }

    @RequestMapping(value = "/find_by_inv", method = {RequestMethod.GET})
    public List<Object_dest> findObjectbyInventoryNumCommon(Integer inventory_number, Integer object_type, Integer org_id) throws ServiceDaoException, ServiceException {

        logger.info("root - /object/find_by_inv");

        return objectServiceImp.findObjectbyInventoryNumCommon(inventory_number, object_type, org_id);

    }

    @RequestMapping(value = "/find_by_cadastr", method = {RequestMethod.GET})
    public List<Object_dest> findObjectbyCadastreNumCommon(String cadastre_number) throws ServiceDaoException, ServiceException {

        logger.info("root - /object/find_by_cadastr");


        return objectServiceImp.findObjectbyCadastreNumCommon(cadastre_number);

    }

    @RequestMapping(value = "/bindObject", method = {RequestMethod.GET})
    public Object_dest bindObject(Object_dest obj_dest) throws ServiceDaoException, ServiceException {

        logger.info("root - /object/bindObject");

        return objectServiceImp.bindObject(obj_dest);

    }



}
