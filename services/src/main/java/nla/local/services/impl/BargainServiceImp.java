package nla.local.services.impl;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.bargain.Bargain;
import nla.local.pojos.bargain.BargainContent;
import nla.local.services.IBargainService;
import nla.local.services.IObjectService;
import nla.local.services.IRightService;
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

    @Autowired
    private IRightService irs;

    private static Logger log = Logger.getLogger(BargainServiceImp.class);

    private DetachedCriteria query_Bargain = DetachedCriteria.forClass(Bargain.class);

    private DetachedCriteria query_BargainContent = DetachedCriteria.forClass(BargainContent.class);


    @Override
    public BargainContent addBargain(BargainContent rght) throws ServiceDaoException, ServiceException {

        if (rght.getRight_entity_id() == null && rght.getBindedRight() != null)
        {
            if(rght.getBindedRight().getRight_owner_id() == null )
            {

                irs.add(rght.getBindedRight());

            }

            rght.setRight_entity_id(rght.getBindedRight().getRight_owner_id());

        }

        if (rght.getObject_entity_id() == null && rght.getBindedObj() != null)
        {

            if(rght.getBindedObj().getObj_id() == null )
            {

                ios.bindObject(rght.getBindedObj());

            }

            rght.setObject_entity_id(rght.getBindedObj().getObj_id());

        }

        if( rght.getRight_entity_id() != null || rght.getObject_entity_id() != null)
        {
            super.add(rght);
        }

        return rght;
    }


    @Override
    public List<BargainContent> findbyObject(Long obj_id) throws ServiceDaoException {

        return null;
    }

    @Override
    public List<BargainContent> findbySubject(Integer person_id) throws ServiceDaoException {

        return null;
    }

    @Override
    public List<BargainContent> findbyObjectSubject(Long obj_id, Integer person_id) throws ServiceDaoException {

        return null;
    }

    @Override
    public BargainContent updateBargain(BargainContent brg_cont) throws ServiceDaoException, ServiceException {

        return null;
    }


}
