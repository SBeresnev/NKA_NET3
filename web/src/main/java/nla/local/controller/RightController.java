package nla.local.controller;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.rights.RightOwner;
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

    @RequestMapping(value = {"/getRightObjectPerson"}, method = {RequestMethod.GET})
    public List<RightOwner> getRightbyObjectPerson(Long[] obj_ids, Integer person_id) throws ServiceDaoException{

        logger.info("root - /right/getRightObjectPerson");

        List<RightOwner> ret_val = irs.getRightbyObjectPerson(obj_ids,person_id);

        return ret_val;

    }

    @RequestMapping(value = {"/getRightbyObjectAddr"}, method = {RequestMethod.GET})
    public List<RightOwner> getRightbyObjectAddr(String adr, String soato ) throws ServiceDaoException
    {
        logger.info("root - /right/getRightbyObjectAddr");

        List<RightOwner> ret_val = irs.getRightbyObjectAddr(adr, soato);

        return ret_val;
    }

    @RequestMapping(value = {"/getRight"}, method = {RequestMethod.GET})
    public RightOwner getRightOwn(Long right_own_id ) throws ServiceDaoException, ServiceException {

        logger.info("root - /right/getRight");

        RightOwner ret_val = irs.getRightOwner(right_own_id);

        return ret_val;
    }

    @RequestMapping(value = {"/updRight"}, method = {RequestMethod.PUT})
    public void updateRightOwn(RightOwner right_own ) throws ServiceDaoException, ServiceException {

          logger.info("root - /right/updRight");

          irs.updateRightOwner(right_own);

    }


}
