package nla.local.controller;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.bargain.BargainContent;
import nla.local.services.IBargainService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by beresnev on 31.08.2015.
 */

@RestController
@RequestMapping({"/bargain"})
public class BargainController {


    private static final Logger logger = Logger.getLogger(BargainController.class);

    @Autowired
    private IBargainService bargainServiceImp;

    @RequestMapping(value = {"/getBargainbyObjectPerson"}, method = {RequestMethod.GET})
    public List<BargainContent> getBargainbyObjectPerson(Long[] obj_ids, Integer person_id) throws ServiceDaoException {

        List<BargainContent> ret_val =  null;

        ret_val = bargainServiceImp.getBargainbyObjectPerson(obj_ids, person_id);

        return ret_val;

    }

    @RequestMapping(value = {"/addBargain"}, method = {RequestMethod.PUT})
    public BargainContent addBargain(BargainContent b_content) throws ServiceDaoException, ServiceException {

       BargainContent ret_val = bargainServiceImp.addBargain(b_content);

        return  ret_val;

    }

    @RequestMapping(value = {"/updBargain"}, method = {RequestMethod.GET})
    public BargainContent updBargain(BargainContent b_content) throws ServiceDaoException, ServiceException {

        BargainContent ret_val = bargainServiceImp.updateBargain(b_content);

        return  ret_val;

    }



}
