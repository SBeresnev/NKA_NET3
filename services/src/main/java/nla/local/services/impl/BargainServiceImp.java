package nla.local.services.impl;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.bargain.Bargain;
import nla.local.pojos.bargain.BargainContent;
import nla.local.services.IBargainService;
import nla.local.services.IObjectService;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by beresnev on 29.07.2015.
 */

@Service
@Transactional
public class BargainServiceImp extends BaseServiceImp implements IBargainService {

    @Autowired
    private IObjectService ios;

    private static Logger log = Logger.getLogger(BargainServiceImp.class);

    private DetachedCriteria query_Bargain = DetachedCriteria.forClass(Bargain.class);


    @Override
    public Bargain addBargain(Bargain rght) throws ServiceDaoException, ServiceException {
        return null;
    }

    @Override
    public BargainContent addBargainRight(BargainContent brg_cont) throws ServiceDaoException, ServiceException {
        return null;
    }

    @Override
    public List<Bargain> findbyObject(Long obj_id) throws ServiceDaoException {
        return null;
    }

    @Override
    public List<Bargain> findbySubject(Integer person_id) throws ServiceDaoException {
        return null;
    }

    @Override
    public List<Bargain> findbyObjectSubject(Long obj_id, Integer person_id) throws ServiceDaoException {
        return null;
    }


}
