package nla.local.services;

import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.pojos.operations.Operation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by belonovich on 08.04.2015.
 */
public interface IOperationsService extends IService<Operation>{

    public List<Operation> getAll() throws ServiceDaoException;

    public Operation get( Integer id) throws ServiceDaoException;

    public List<Operation> getFromDecl(Integer declId) throws ServiceDaoException;
}
