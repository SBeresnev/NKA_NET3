package nla.local.services;


import nla.local.exception.ServiceDaoException;
import nla.local.pojos.operations.EntityType;
import nla.local.pojos.operations.Operation;

import java.util.List;

/**
 * Created by belonovich on 08.04.2015.
 */
public interface IOperationsService extends IService<Operation>{

    public List<Operation> getAll() throws ServiceDaoException;

    public Operation get( Integer id) throws ServiceDaoException;

    public List<Operation> getEntytyOper (Integer declId, EntityType et) throws ServiceDaoException;


}
