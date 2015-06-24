package nla.local.services;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.rights.Right;

/**
 * Created by beresnev on 23.06.2015.
 */
public interface IRightService extends IService {

    public Right addRight(Right rght) throws ServiceDaoException, ServiceException;

}
