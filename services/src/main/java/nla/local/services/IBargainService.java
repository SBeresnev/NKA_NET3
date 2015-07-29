package nla.local.services;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.bargain.Bargain;
import nla.local.pojos.bargain.BargainContent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by beresnev on 29.07.2015.
 */

public interface IBargainService {

    public Bargain addBargain(Bargain rght) throws ServiceDaoException, ServiceException;

    public BargainContent addBargainRight(BargainContent brg_cont) throws ServiceDaoException, ServiceException ;

    public List<Bargain> findbyObject(Long obj_id) throws ServiceDaoException;

    public List<Bargain> findbySubject(Integer person_id) throws ServiceDaoException;

    public List<Bargain> findbyObjectSubject(Long obj_id, Integer person_id)throws ServiceDaoException;

}
