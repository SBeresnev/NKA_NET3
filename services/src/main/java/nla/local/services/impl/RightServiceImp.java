package nla.local.services.impl;


import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.rights.Right;
import nla.local.services.IObjectService;
import nla.local.services.IRightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by beresnev on 23.06.2015.
 */
@Service
public class RightServiceImp extends BaseServiceImp implements IRightService {

    @Autowired
    IObjectService ios;

    @Override
    public Right addRight(Right rght) throws ServiceDaoException, ServiceException {


        rght.setBindedObj(ios.bindObject(rght.getBindedObj()));

        super.add(rght);

        return rght;

    }



}
