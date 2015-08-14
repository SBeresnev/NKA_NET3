package nla.local.controller;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.rights.Right;
import nla.local.pojos.rights.RightOwner;
import nla.local.services.IRightService;
import nla.local.util.Converter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping(value = {"/addSingleRight"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public RightOwner passSingleRight(@RequestBody RightOwner rght_own) throws Exception {

        irs.passSingleRight(rght_own);

        return rght_own ;

    }

    @RequestMapping(value = {"/addSharedRight"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public  ArrayList<RightOwner> passSharedRight(@RequestBody ArrayList<RightOwner> rght_key, ArrayList<RightOwner> rght_val ) throws Exception {

            HashMap<RightOwner, RightOwner> right_own = new HashMap<RightOwner, RightOwner>();

            right_own = Converter.getCastHash(rght_key,rght_val);

            irs.passSharedRight(right_own);

        return rght_val;

    }


    @RequestMapping(value = {"/splitSharedRight"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public RightOwner splitSharedRight(List<RightOwner> child_owners, RightOwner parent_owner) throws ServiceDaoException, ServiceException {

        irs.splitSharedRight(child_owners, parent_owner);

        return parent_owner;

    }

    @RequestMapping(value = {"/addLimitation"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public Right addLimitationRight( Right rght) throws ServiceDaoException, ServiceException {

        irs.addRight(rght);

        return rght;

    }


    @RequestMapping(value = {"/getRightObjectPerson"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<RightOwner> getRightbyObjectPerson(Long[] obj_ids, Integer person_id) throws ServiceDaoException{

        List<RightOwner> ret_val = irs.getRightbyObjectPerson(obj_ids,person_id);

        return ret_val;

    }

    @RequestMapping(value = {"/getRightbyObjectAddr"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<RightOwner> getRightbyObjectAddr(String adr, String soato ) throws ServiceDaoException
    {
        List<RightOwner> ret_val = irs.getRightbyObjectAddr(adr, soato);

        return ret_val;
    }


}
