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
import java.util.HashMap;
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

    /******************************************Operation block**********************************************************************/

    public void updateRight(Right right) throws ServiceDaoException {

        super.update(right);

    }

    public void updateRightOwner(RightOwner right_own) throws ServiceDaoException {

        super.update(right_own);

    }


    @Override
    public Right addRight(Right rght) throws ServiceDaoException, ServiceException {

        if(rght.getObject_entity_id() == null) rght.setBindedObj(ios.bindObject(rght.getBindedObj()));

        //////////////////////////// set parent operations by binding ////////////////////////////////////////////////////////

        if( rght.getOoper() != null ) {
            if (rght.getBindedObj() != null) {
                if (rght.getBindedObj().getOoper() != null)
                    rght.getOoper().setParent_id_order(rght.getBindedObj().getOoper());
            }
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        if(rght.getRight_id() == null)  super.add(rght);


        return rght;

    }

    @Override
    public RightOwner addRightOwner(RightOwner rght_own) throws ServiceDaoException, ServiceException {


        if(rght_own.getRight() != null && rght_own.getRight().getRight_id() == null)
        {
            this.addRight(rght_own.getRight());
        }

             rght_own.getOoper().setParent_id_order(rght_own.getRight().getOoper());

        super.add(rght_own);

        return rght_own;

    }

    /*************************************finds***************************************************************************/
    @Override
    public Right getRight(Long id) throws ServiceDaoException  {
       return (Right) super.get(Right.class,id);
    }

    @Override
    public RightOwner getRightOwner(Long id) throws ServiceDaoException {
        return (RightOwner) super.get(RightOwner.class,id);
    }

    @Deprecated
    public List<RightOwner> findbyrightCountType( Integer countType) throws ServiceDaoException {

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

    @Override
    public List<RightOwner> getRightbyObjectAddr(String Adr, String soato ) throws ServiceDaoException {

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

            ret_val.addAll(getRightbyObjectPerson(obj_ids, null));

        }

        return ret_val;

    }

    @Override
    public List<RightOwner> getRightbyObjectPerson(Long[] obj_ids, Integer person_id) throws ServiceDaoException {

        boolean lakmus = false;

        List<RightOwner> ret_val = new ArrayList<RightOwner>();

        DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query_RightOwn);

        query_ = query_.add(Restrictions.eq("status", 1));

        query_ = query_.add(Restrictions.isNull("date_out"));

        if(person_id != null) {

          lakmus = true;

          query_.add(Restrictions.eq("owner.subjectId", person_id)) ;
        }

        if( obj_ids != null && obj_ids.length > 0 )
        {
            lakmus = true;

            query_ = query_.createCriteria("right").add(Restrictions.in("object_entity_id",obj_ids));

        }

        ret_val = lakmus ? super.getCriterion(query_): null;

        return ret_val;

    }

    @Override
    public List<RightOwner> getRightOwnersbyRight(Long[] right_ids) throws ServiceDaoException, ServiceException {

        List<RightOwner> ret_val_own = null;

        DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query_RightOwn);

        query_ = query_.add(Restrictions.eq("status", 1));

        query_ = query_.add(Restrictions.isNull("date_out"));

        query_ = query_.createCriteria("right").add(Restrictions.in("right_id", right_ids));

        ret_val_own =  super.getCriterion(query_);

        return ret_val_own;
    }

    public List<Right> getlimitationsObject (Long[] obj_ids)  throws ServiceDaoException, ServiceException {

        List<Right> ret_val = null;

        DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query_Right);

        query_ = query_.add(Restrictions.in("object_entity_id", obj_ids));

        query_ = query_.add(Restrictions.between("right_type", 200, 499));

        ret_val = super.getCriterion(query_);

        return ret_val;

    }




    @Override
    public void passSingleRight(RightOwner rght_own) throws ServiceDaoException, ServiceException {

        this.addRightOwner(rght_own);

    }

    @Override
    public void passSharedRight(HashMap<RightOwner,RightOwner> right_own) throws ServiceDaoException, ServiceException {

        /***************************** update parent owner **********************************************************/

        Right main_right =  right_own.get(right_own.keySet().iterator().next()).getRight();

        this.addRight(main_right);

        for (RightOwner parent_owner : right_own.keySet() )  {

            RightOwner child_own = right_own.get(parent_owner);

            parent_owner.setStatus(0);

            this.updateRightOwner(parent_owner);

            child_own.setParent_owner(parent_owner.getRight_owner_id());

            child_own.setRight(main_right);

            this.addRightOwner(child_own);

        }

    }

    @Override
    public void splitSharedRight(List<RightOwner> child_owners, RightOwner parent_owner) throws ServiceDaoException, ServiceException {

        /***************************** update parent owner **********************************************************/

        this.updateRightOwner(parent_owner);

        /**************************************************************************************************************/

        for (RightOwner child_owner : child_owners )
        {
            child_owner.setParent_owner(parent_owner.getRight_owner_id());

            this.addRightOwner(child_owner);
        }
    }


}
