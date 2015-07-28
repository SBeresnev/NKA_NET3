package nla.local.services.impl;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.rights.Right;
import nla.local.pojos.rights.RightOwner;
import nla.local.services.IObjectService;
import nla.local.services.IRightService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by beresnev on 23.06.2015.
 */
@Service
@Transactional
public class RightServiceImp extends BaseServiceImp implements IRightService {

    @Autowired
    IObjectService ios;

    private static Logger log = Logger.getLogger(AddressServiceImp.class);

    private DetachedCriteria query_Right = DetachedCriteria.forClass(Right.class);

    private DetachedCriteria query_RightOwn = DetachedCriteria.forClass(RightOwner.class);


    @Override
    public Right addRight(Right rght) throws ServiceDaoException, ServiceException {

        if(rght.getObject_entity_id() == null) rght.setBindedObj(ios.bindObject(rght.getBindedObj()));

        super.add(rght);

        return rght;

    }

    @Override
    public RightOwner addRightOwner(RightOwner rght_own) throws ServiceDaoException, ServiceException {


        if(rght_own.getRight() != null && rght_own.getRight().getRight_id() == null)
        {
            this.addRight(rght_own.getRight());
        }

        super.add(rght_own);

        return rght_own;

    }


    public List<Right> findbyObjectSubject(Long obj_id,  Integer person_id)throws ServiceDaoException
    {

        final Integer f_person_id = person_id;

        List<Right> ret_val = new ArrayList<Right>();

        Set<Right> obj_list =  new HashSet<Right>(findbyObject(obj_id));

        for(Right obj : obj_list)
        {

            Set<RightOwner> ro_list = obj.getRight_owner_lst();

            RightOwner row = CollectionUtils.find(ro_list, new Predicate() {
            public boolean evaluate(Object o) {
                RightOwner c = (RightOwner) o;
                return (c.getOwner().getSubjectId() == f_person_id.intValue() && c.getDate_out() == null);
            }
        });

            if (row != null) ret_val.add(obj);

        }

        return ret_val;

    }

    public List<Right> findbyObject(Long obj_id) throws ServiceDaoException
    {

        List<Right> ret_val = null;

        DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query_Right);

        if( obj_id != null )
        {
            query_ = query_.add(Restrictions.eq("object_entity_id", obj_id));

            query_ = query_.add(Restrictions.eq("status", 1));

            ret_val = super.getCriterion(query_);

        }

        return ret_val;

    }


    @Deprecated
    public List<Right> findbyrightCountType( CatalogItem countType) throws ServiceDaoException {

        List<Right> ret_val = null;

        DetachedCriteria query = (DetachedCriteria) SerializationUtils.clone(query_Right);

        if( countType != null )
        {
            query = query.add(Restrictions.eq("right_count_type",countType));

            ret_val =  super.getCriterion(query);

        }

        return ret_val;
    }

    public List<Right> findbySubject(Integer person_id) throws ServiceDaoException
    {
        RightOwner ro =new RightOwner();

        List<Right> ret_val = new ArrayList<Right>();

        DetachedCriteria query = (DetachedCriteria) SerializationUtils.clone(query_Right);

        DetachedCriteria query_own = (DetachedCriteria) SerializationUtils.clone(query_RightOwn);

        if( person_id != null )
        {

            query_own = query_own.add(Restrictions.eq("status",1));

            query_own = query_own.createCriteria("owner").add(Restrictions.eq("subjectId", person_id));

            List<RightOwner> ret_val_own = super.getCriterion(query_own);

            for (RightOwner row_l : ret_val_own) {

                ret_val.add(row_l.getRight());

            }


            /*Integer[] right_ids = new Integer[ret_val_own.size()];

            for (int i =0 ; i < ret_val_own.size() ; i++) {

                right_ids[i] = ret_val_own.get(i).getRight_id();
            }

            query = query.add(Restrictions.in("right_id",right_ids));

            ret_val =  super.getCriterion(query); */

        }

        return ret_val;

    }



    public RightOwner updateRightOwner(RightOwner rightowner) throws ServiceDaoException {

        super.update(rightowner);

        return rightowner;
    }

}
