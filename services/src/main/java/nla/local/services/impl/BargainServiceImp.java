package nla.local.services.impl;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.bargain.BargainContent;
import nla.local.pojos.rights.RightOwner;
import nla.local.services.IBargainService;
import nla.local.services.IObjectService;
import nla.local.services.IRightService;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    private DetachedCriteria query_BargainContent = DetachedCriteria.forClass(BargainContent.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

    public BargainContent getBargain(Long bar_cont_id) throws ServiceDaoException {

        BargainContent ret_val = (BargainContent) super.get(BargainContent.class, bar_cont_id);

        return ret_val;
    }

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

       /* if (rght.getObject_entity_id() == null && rght.getBindedObj() != null)
        {

            if(rght.getBindedObj().getObj_id() == null )
            {

                ios.bindObject(rght.getBindedObj());

            }

            rght.setObject_entity_id(rght.getBindedObj().getObj_id());

        }*/

        if( rght.getRight_entity_id() != null)
        {
            super.add(rght);
        }

        return rght;
    }


    public List<BargainContent> getbyRightOwner(Long[] right_owner_id) throws ServiceDaoException {

        //Object bar =   super.get(Bargain.class,Long.valueOf(82));

        List<BargainContent> ret_val = null;

        DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query_BargainContent);

        query_.add(Restrictions.in("right_entity_id",right_owner_id));

        ret_val = super.getCriterion(query_);

       return ret_val;
    }


    @Override
    public List<BargainContent> getRightbyObjectAddr(String Adr, String soato ) throws ServiceDaoException {

        List<BargainContent> ret_val = new ArrayList<BargainContent>();

        List<RightOwner> rown_list = irs.getRightbyObjectAddr(Adr, soato);

        Long[] obj_ids = new Long[rown_list.size()];

        for (int i =0 ; i < rown_list.size() ; i++) {

            obj_ids[i] = rown_list.get(i).getRight_owner_id();
        }

        ret_val = this.getbyRightOwner(obj_ids);

        return ret_val;

    }


    @Override
    public List<BargainContent> getBargainbyObjectPerson(Long[] obj_id, Integer person_id) throws ServiceDaoException {

         List<BargainContent> ret_val = new ArrayList<BargainContent>();

         List<RightOwner> rown_list = irs.getRighOwnbyObjectPerson(obj_id, person_id);

         Long[] obj_ids = new Long[rown_list.size()];

            for (int i =0 ; i < rown_list.size() ; i++) {

                obj_ids[i] = rown_list.get(i).getRight_owner_id();
            }

            ret_val = this.getbyRightOwner(obj_ids);


        return ret_val;
    }


    @Override
    public BargainContent updateBargain(BargainContent brg_cont) throws ServiceDaoException, ServiceException {

        super.update(brg_cont);

        return brg_cont;
    }


}
