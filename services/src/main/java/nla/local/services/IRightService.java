package nla.local.services;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.object.Object_dest;
import nla.local.pojos.rights.Right;
import nla.local.pojos.rights.RightOwners;

import java.util.List;

/**
 * Created by beresnev on 23.06.2015.
 */
public interface IRightService extends IService {

    public Right addRight(Right rght) throws ServiceDaoException, ServiceException;

    public List<Right> findbyObjctSubject(Object_dest obj, RightOwners rown) throws ServiceDaoException, ServiceException;

    public List<Right> findbyObjct(Object_dest obj) throws ServiceDaoException, ServiceException;

    public List<Right> findbySubject(RightOwners rown) throws ServiceDaoException, ServiceException;


}
