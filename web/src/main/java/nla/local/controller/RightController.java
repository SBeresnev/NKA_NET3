package nla.local.controller;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.DependencyData;
import nla.local.pojos.rights.Right;
import nla.local.pojos.rights.RightOwner;
import nla.local.services.ICatalogDependencyService;
import nla.local.services.IRightService;
import nla.local.util.Converter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by beresnev on 12.08.2015.
 */

@RestController
@RequestMapping({"/right"})
public class RightController {

    private static final Logger logger = Logger.getLogger(RightController.class);

    @Autowired
    private IRightService irs;

    @RequestMapping(value = {"/addSingleRight"}, method = {RequestMethod.GET})
    public RightOwner passSingleRight(@RequestBody RightOwner rght_own) throws Exception {

        logger.info("root - /right/addSingleRight");

        irs.passSingleRight(rght_own);

        return rght_own ;

    }

    @RequestMapping(value = {"/addSharedRight"}, method = {RequestMethod.GET})
    public  ArrayList<RightOwner> passSharedRight(@RequestBody ArrayList<RightOwner> rght_key, ArrayList<RightOwner> rght_val ) throws Exception {

            logger.info("root - /right/addSharedRight");

            HashMap<RightOwner, RightOwner> right_own = new HashMap<RightOwner, RightOwner>();

            right_own = Converter.getCastHash(rght_key,rght_val);

            irs.passSharedRight(right_own);

        return rght_val;

    }

    @RequestMapping(value = {"/splitSharedRight"}, method = {RequestMethod.GET})
    public RightOwner splitSharedRight(List<RightOwner> child_owners, RightOwner parent_owner) throws ServiceDaoException, ServiceException {

        logger.info("root - /right/splitSharedRight");

        irs.splitSharedRight(child_owners, parent_owner);

        return parent_owner;

    }

    @RequestMapping(value = {"/getRightOwnObjectPerson"}, method = {RequestMethod.GET})
    public List<RightOwner> getRightOwnbyObjectPerson(Long[] obj_ids, Integer person_id) throws ServiceDaoException{

        logger.info("root - /right/getRightObjectPerson");

        List<RightOwner> ret_val = irs.getRighOwnbyObjectPerson(obj_ids, person_id);

        return ret_val;

    }

    @RequestMapping(value = {"/getRightObjectPerson"}, method = {RequestMethod.GET})
    public List<Right> getRightbyObjectPerson(Long[] obj_ids, Integer person_id) throws ServiceDaoException{

        logger.info("root - /right/getRightObjectPerson");

        List<Right> ret_val = irs.getRightbyObjectPerson(obj_ids, person_id);

        return ret_val;

    }

    @RequestMapping(value = {"/getRightbyObjectAddr"}, method = {RequestMethod.GET})
    public List<RightOwner> getRightbyObjectAddr(String adr, String soato ) throws ServiceDaoException {
        logger.info("root - /right/getRightbyObjectAddr");

        List<RightOwner> ret_val = irs.getRightbyObjectAddr(adr, soato);

        return ret_val;
    }

    @RequestMapping(value = {"/getRightOwn"}, method = {RequestMethod.GET})
    public RightOwner getRightOwn(Long right_own_id ) throws ServiceDaoException, ServiceException {

        logger.info("root - /right/getRightOwn");

        RightOwner ret_val = irs.getRightOwner(right_own_id);

        return ret_val;
    }

    @RequestMapping(value = {"/updRightOwn"}, method = {RequestMethod.PUT})
    public void updateRightOwn(RightOwner right_own ) throws ServiceDaoException, ServiceException {

          logger.info("root - /right/updRight");

          irs.updateRightOwner(right_own);

    }

    @RequestMapping(value = {"/updRight"}, method = {RequestMethod.PUT})
    public void updateRight(Right right ) throws ServiceDaoException, ServiceException {

        logger.info("root - /right/updRight");

        irs.updateRight(right);

    }

    @RequestMapping(value = {"/addRight"}, method = {RequestMethod.PUT})
    public void addRight(Right right ) throws ServiceDaoException, ServiceException {

        logger.info("root - /right/addRight");

        irs.addRight(right);

    }

    @RequestMapping(value = {"/getRightbyRightOwner"}, method = {RequestMethod.GET})
    public List<Right> getRightbyRightOwner(Long[] right_own_ids) throws ServiceDaoException, ServiceException {

        List<Right> ret_val = new ArrayList<Right>();

        logger.info("root - /right/getRightbyRightOwner");

        ret_val = irs.getRightbyRightOwner(right_own_ids);

        return ret_val;

    }


}
