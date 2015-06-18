package nla.local.controller;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.object.*;
import nla.local.pojos.orders.Journal;
import nla.local.services.IAddressService;
import nla.local.services.IObjectService;
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

    @RequestMapping(value = "/find_by_address", method = {RequestMethod.GET})
    public List<? extends nla.local.pojos.object.Object> find_by_address(Long address_id) throws ServiceDaoException, ServiceException {

        logger.info("root - /object/find_by_address");

        return objectServiceImp.findObjectbyAddressCommon( Arrays.asList(address_id));

    }


}
