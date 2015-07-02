package nla.local.services;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.rights.Right;
import nla.local.pojos.rights.RightOwner;


import java.util.List;
import java.util.Set;

/**
 * Created by beresnev on 23.06.2015.
 */
public interface IRightService extends IService {

    public Right addRight(Right rght) throws ServiceDaoException, ServiceException;

    public List<Right> findbyObject(Long obj_id) throws ServiceDaoException;

    public List<Right> findbySubject(Integer person_id) throws ServiceDaoException;

    public List<Right> findbyObjectSubject(Long obj_id, Integer person_id)throws ServiceDaoException;

    public RightOwner updateRightOwner(RightOwner rightowner) throws ServiceDaoException ;
}
