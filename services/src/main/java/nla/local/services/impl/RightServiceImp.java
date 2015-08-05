package nla.local.services.impl;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.address.Address_dest;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.object.Object_dest;
import nla.local.pojos.rights.Right;
import nla.local.pojos.rights.RightOwner;
import nla.local.services.IAddressService;
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
 * Created by beresnev on 23.06.2015.
 */
@Service
@Transactional
public class RightServiceImp extends BaseServiceImp implements IRightService {

    @Autowired
    private IObjectService ios;

    @Autowired
    private IAddressService ias;

    private static Logger log = Logger.getLogger(RightServiceImp.class);

    private DetachedCriteria query_Right = DetachedCriteria.forClass(Right.class,"rght").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

    private DetachedCriteria query_RightOwn = DetachedCriteria.forClass(RightOwner.class,"rght_own").setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);


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



    public List<Right> getRightbyObject(String Adr, String soato ) throws ServiceDaoException
    {
        List<Right> ret_val = new ArrayList<Right>();

        List<Address_dest> addr_list  = ias.findAddressDest(Adr, soato);

        if(!addr_list.isEmpty())
        {
            List<Long> adr_ids = new ArrayList<Long>();

            for (Address_dest addr : addr_list ) {

                adr_ids.add(addr.getAddress_id());

            }

            List<Object_dest> object_list = (List<Object_dest>) ios.findObjectbyAddress(Object_dest.class,adr_ids);

            for (Object_dest obj :object_list)
            {
                ret_val.addAll(findbyObject(obj.getObj_id()));
            }

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
    public List<RightOwner> findbyrightCountTypeOwn( CatalogItem countType) throws ServiceDaoException {

        List<RightOwner> ret_val = null;

        DetachedCriteria query_own_ = (DetachedCriteria) SerializationUtils.clone(query_RightOwn);



        if( countType != null )
        {

            query_own_ = query_own_.add(Restrictions.eq("status", 1));

            query_own_ = query_own_.add(Restrictions.isNull("date_out"));

            query_own_ = query_own_.createCriteria("right").add(Restrictions.eq("right_count_type", countType));

            ret_val =  super.getCriterion(query_own_);

        }

        return ret_val;
    }


    public RightOwner updateRightOwner(RightOwner rightowner) throws ServiceDaoException {

        super.update(rightowner);

        return rightowner;
    }


    public List<RightOwner> getRightbyObjectOwn(String Adr, String soato ) throws ServiceDaoException {

        List<RightOwner> ret_val = new ArrayList<RightOwner>();

        List<Address_dest> addr_list  = ias.findAddressDest(Adr, soato);

            if(!addr_list.isEmpty())
            {
                List<Long> adr_ids = new ArrayList<Long>();

                for (Address_dest addr : addr_list ) {

                    adr_ids.add(addr.getAddress_id());

                }

                List<Object_dest> object_list = (List<Object_dest>) ios.findObjectbyAddress(Object_dest.class,adr_ids);

                 Long[] obj_ids = new Long[object_list.size()];

                 for (int i =0 ; i < object_list.size() ; i++) {

                     obj_ids[i] = object_list.get(i).getObj_id();
                 }


                ret_val.addAll(findbyObjectPersonOwn(obj_ids, null));

            }

        return ret_val;

    }


    public List<RightOwner> findbyObjectPersonOwn(Long[] obj_ids, Integer person_id) throws ServiceDaoException
    {

        List<RightOwner> ret_val = null;

        DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query_RightOwn);

        query_ = person_id != null ?  query_.add(Restrictions.eq("owner.subjectId",person_id)) : query_ ;

        if( obj_ids != null && obj_ids.length > 0 )
        {
            query_ = query_.add(Restrictions.eq("status", 1));

            query_ = query_.add(Restrictions.isNull("date_out"));

            query_ = query_.createCriteria("right").add(Restrictions.in("object_entity_id",obj_ids));

            ret_val = super.getCriterion(query_);

        }

        return ret_val;

    }

    public List<Right> getlimitationsObject (Long[] obj_ids)  throws ServiceDaoException, ServiceException
    {

        List<Right> ret_val = null;

        DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query_Right);

        query_ = query_.add(Restrictions.in("object_entity_id", obj_ids));

        query_ = query_.add(Restrictions.between("right_type", 200 , 499));

        ret_val = super.getCriterion(query_);

        return ret_val;


   }

    public List<Right> getlimitationsRight (Long[] right_ids)  throws ServiceDaoException, ServiceException
    {
        List<Right> ret_val = null;

        List<RightOwner> ret_val_own = null;

        DetachedCriteria query_r = (DetachedCriteria) SerializationUtils.clone(query_Right);

        ret_val_own = getRightOwnersbyRight(right_ids);


        Right[] right_array = new Right[ret_val_own.size()];

        ret_val_own.toArray(right_array);

        query_r = query_r.add(Restrictions.in("limit_righ",right_array));

        query_r = query_r.add(Restrictions.eq("status", 1));

        query_r = query_r.add(Restrictions.isNull("date_out"));

        ret_val = super.getCriterion(query_r);

        return ret_val;

    }

    @Override
    public List<RightOwner> getRightOwnersbyRight(Long[] right_ids) throws ServiceDaoException, ServiceException {

        List<RightOwner> ret_val_own = null;

        DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query_RightOwn);

        query_ = query_.add(Restrictions.eq("status", 1));

        query_ = query_.add(Restrictions.isNull("date_out"));

        query_ = query_.createCriteria("right").add(Restrictions.in("right_id", right_ids ));

        ret_val_own =  super.getCriterion(query_);

        return ret_val_own;
    }


    public void passSingleRight(RightOwner rght_own) throws ServiceDaoException, ServiceException {

        this.addRightOwner(rght_own);

    }

}
