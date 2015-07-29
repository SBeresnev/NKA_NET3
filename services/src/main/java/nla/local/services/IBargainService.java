package nla.local.services;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.bargain.Bargain;
import nla.local.pojos.bargain.BargainContent;


import java.util.List;

/**
 * Created by beresnev on 29.07.2015.
 */

public interface IBargainService {

    public BargainContent addBargain(BargainContent brg_cont) throws ServiceDaoException, ServiceException;

    public List<BargainContent> findbyObject(Long obj_id) throws ServiceDaoException;

    public List<BargainContent> findbySubject(Integer person_id) throws ServiceDaoException;

    public List<BargainContent> findbyObjectSubject(Long obj_id, Integer person_id) throws ServiceDaoException;

    public BargainContent updateBargain(BargainContent brg_cont) throws ServiceDaoException, ServiceException ;

}
