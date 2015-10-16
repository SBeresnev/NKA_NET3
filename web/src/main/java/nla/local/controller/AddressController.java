package nla.local.controller;

import nla.local.exception.ServiceDaoException;
import nla.local.pojos.address.Address_dest;
import nla.local.pojos.address.Address_src;
import nla.local.pojos.address.Ate;
import nla.local.services.IAddressService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by beresnev on 29.01.2015.
 */

@RestController
@RequestMapping({"/address"})
public class AddressController {

    private static final Logger logger = Logger.getLogger(AddressController.class);

    @Autowired
    private IAddressService addressServiceImp;

    @RequestMapping(value = {"/getate"}, method = {RequestMethod.GET})
    public List<Ate> getAte(Integer parent_id) throws ServiceDaoException {

        logger.info("root - /address/getate");

        List<Ate> ret_val = null;

        ret_val = addressServiceImp.findATEbyParentId(parent_id);

        return ret_val;
    }


    @RequestMapping(value = {"/getAtebyName"}, method = {RequestMethod.GET})
    public List<Ate> getAtebyName(String ate_name) throws ServiceDaoException {

        logger.info("root - /address/getAtebyName");

        List<Ate> ret_val = null;

        ret_val = addressServiceImp.findATEbyName(ate_name);

        return ret_val;
    }

    @RequestMapping(value = {"/getAtebyName_apr"}, method = {RequestMethod.GET})
    public List<Ate> getAtebyName_apr(Integer parent_id , String ate_name) throws ServiceDaoException {

        logger.info("root - /address/getAtebyName_apr");

        List<Ate> ret_val = null;

        ret_val = addressServiceImp.findATEbyParentLike(parent_id, ate_name);

        if (ret_val.size() >0)  for (Ate val : ret_val ) addressServiceImp.fillParentAte(val);

        return ret_val;

    }

    @RequestMapping(value = {"/addLAddress"}, method = {RequestMethod.POST})
    public String addLAddress( String elementName, String comments) throws ServiceDaoException {

        logger.info("root - /address/addLAddress");

        addressServiceImp.addLinear(elementName,comments);

        return "OK";

    }

    @RequestMapping(value = {"/bindAddress"}, method = {RequestMethod.POST})
    public String bindAddress(Long id_adr, Long adr_num, Integer prop_type) throws ServiceDaoException {

        logger.info("root - /address/bindAddress");

        List<Address_src> ad_src = addressServiceImp.getsrcbyID(id_adr, adr_num, prop_type);

        Address_dest ade = addressServiceImp.convertSrctoDest(ad_src.get(0));

        if(ad_src.size() > 0) addressServiceImp.bindAddress(ade);

        return "OK";

    }

    @RequestMapping(value = {"/findHomeAddress"}, method = {RequestMethod.GET})
    public List<Address_src> findHomeAddress(Integer ate_id, String street_name, Integer house_num, Integer corp_num, Integer room_num) throws ServiceDaoException {

        logger.info("root - /address/findHomeAddress");

        List<Address_src> ret_val = addressServiceImp.findHomeAddress( ate_id,  street_name, house_num, corp_num, room_num);

        return ret_val;

    }


    @RequestMapping(value = {"/findAddress"}, method = {RequestMethod.GET})
    public List<Address_src> findAddress(Integer ate_id, String elementName, Integer objectType, Integer objectPlace, Integer house_num, Integer room_num) throws ServiceDaoException {

        logger.info("root - /address/findAddress");

        List<Address_src> ret_val = addressServiceImp.findAddress(ate_id, elementName, objectType, objectPlace, house_num, room_num);

        return ret_val;

      }



  }
