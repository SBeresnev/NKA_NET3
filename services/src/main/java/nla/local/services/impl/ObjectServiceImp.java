package nla.local.services.impl;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.address.Address_dest;
import nla.local.pojos.address.Address_src;
import nla.local.pojos.object.Object;
import nla.local.pojos.object.Object_dest;
import nla.local.pojos.object.Object_src;
import nla.local.services.IAddressService;
import nla.local.services.IObjectService;
import nla.local.util.CodeGenerator;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by beresnev on 03.06.2015.
 */
@Service
@Transactional
public class ObjectServiceImp extends BaseServiceImp implements IObjectService{

    private static Logger log = Logger.getLogger(ObjectServiceImp.class);

    @Autowired
    private IAddressService ias;

    @Autowired
    private CodeGenerator scg;

    @Transactional( rollbackFor=Exception.class)
    public Object_dest bindObject( Object_dest object_dest) throws ServiceDaoException, ServiceException  {

        object_dest.setAddress_dest(ias.bindAddress(object_dest.getAddress_dest()));

        object_dest.setAddress_id( object_dest.getAddress_dest() == null ? null : object_dest.getAddress_dest().getAddress_id() );


        if( object_dest.getObj_id() != null )
        {
            this.update(object_dest);

        } else {

            Long objid = Long.valueOf(scg.generate("SEQ_OBJECTS_ID.NEXTVAL").toString());

            object_dest.setObj_id(objid);

            this.add(object_dest);
        }

        return object_dest;

    }

    public List<Object_dest> findbyobjDestid(Long obj_dest_id) throws ServiceDaoException    {

        List<Object_dest> ret_val = null;

        DetachedCriteria query_ = DetachedCriteria.forClass(Object_dest.class);

        if( obj_dest_id != null) {

            query_ = query_.add(Restrictions.eq("obj_dest_id", obj_dest_id)) ;

            ret_val = super.getCriterion(query_);
        }

        return ret_val;

    }

    public Object_dest convertSrctoDest(Object_src obj_src) throws ServiceDaoException    {

        Object_dest ret_val = new Object_dest();

        ret_val.setObj_id_inv(obj_src.getObj_id());

        ret_val.setCadaster_number(obj_src.getCadaster_number());

        ret_val.setOrg_id(obj_src.getOrg_id());

        ret_val.setObjectType(obj_src.getObjectType());

        ret_val.setInventory_number(obj_src.getInventory_number());

        ret_val.setSquare(obj_src.getSquare());

        ret_val.setRoomscount(obj_src.getRoomscount());

        ret_val.setReadiness(obj_src.getReadiness() );

        ret_val.setObject_name(obj_src.getObject_name());

        ret_val.setUse_purpose(obj_src.getUse_purpose());

        ret_val.setLand_category(obj_src.getLand_category());

        ret_val.setAdr_num(obj_src.getAddress_id());



        return ret_val;
    }

    public List<Object_dest> findObjectbyInventoryNumCommon(Integer inventory_number, Integer object_type, Integer org_id) throws ServiceDaoException {

        List<Object_dest> ret_val_dest = null;

        List<Object_src> ret_val_src = null;

        ret_val_dest = (List<Object_dest>) findObjectbyInventoryNum(Object_dest.class, inventory_number, object_type, org_id);

        if(ret_val_dest.size() == 0) {

            ret_val_src = (List<Object_src>) findObjectbyInventoryNum(Object_src.class, inventory_number, object_type, org_id);

            for (Object_src src : ret_val_src)
            {

                Object_dest dest = convertSrctoDest(src);

                Address_dest ret_dest_adr = findbyAdrnum(null,src.getAddress_id(), src.getObjectType().getCode_id());

                dest.setAddress_dest(ret_dest_adr);

                ret_val_dest.add(dest);

            }

        } else {

            int i =0;

            for (Object_dest dst : ret_val_dest)
            {

                Address_dest ret_dest_adr = findbyAdrnum(dst.getAddress_id(),dst.getAdr_num(), dst.getObjectType().getCode_id());

                dst.setAddress_dest(ret_dest_adr);

                ret_val_dest.set(i, dst);

                i++;

            }

        }

        return ret_val_dest;

    }

    public List<Object_dest> findObjectbyCadastreNumCommon(String cadastre_number) throws ServiceDaoException {

        List<Object_dest> ret_val_dest = null;

        List<Object_src> ret_val_src = null;

        ret_val_dest = (List<Object_dest>) findObjectbyCadastreNum(Object_dest.class, cadastre_number);

        if(ret_val_dest.size() == 0) {

            ret_val_src = (List<Object_src>) findObjectbyCadastreNum(Object_src.class, cadastre_number);

            for (Object_src src : ret_val_src)
            {

                Object_dest dest = convertSrctoDest(src);

                Address_dest ret_dest_adr = findbyAdrnum(null, src.getAddress_id(),  src.getObjectType().getCode_id());

                dest.setAddress_dest(ret_dest_adr);

                ret_val_dest.add(dest);

            }
        }
        {

            int i = 0;

            for (Object_dest dst : ret_val_dest) {

                Address_dest ret_dest_adr = findbyAdrnum(dst.getAddress_id(), dst.getAdr_num(), dst.getObjectType().getCode_id());

                dst.setAddress_dest(ret_dest_adr);

                ret_val_dest.set(i, dst);

                i++;

            }
        }

        return ret_val_dest;

    }

    public List<Object_dest> findObjectbyAddressCommon(List<Long> address_id) throws ServiceDaoException {

        List<Object_dest> ret_val_dest = null;

        List<Object_src> ret_val_src = null;

        ret_val_dest = (List<Object_dest>) findObjectbyAddress(Object_dest.class, address_id);

        if(ret_val_dest.size() == 0) {

            ret_val_src = (List<Object_src>) findObjectbyAddress(Object_src.class, address_id);

            for (Object_src src : ret_val_src)
            {

                Object_dest dest = convertSrctoDest(src);

                ret_val_dest.add(dest);

            }
        }

        return ret_val_dest;
    }

    public List<? extends Object> findObjectbyAddress(Class<? extends Object> cobj, List<Long> address_id) throws ServiceDaoException {
        List<? extends Object> ret_val = null;

        DetachedCriteria query_ = DetachedCriteria.forClass(cobj);

        query_ = query_.add(Restrictions.in("address_id", address_id));

        ret_val = super.getCriterion(query_);

        return ret_val;

    }

    public List<? extends Object>  findObjectbyCadastreNum(Class<? extends Object> cobj, String cadastre_number) throws ServiceDaoException  {
        List<? extends Object> ret_val = null;

        DetachedCriteria query_ = DetachedCriteria.forClass(cobj);

        if( cadastre_number != null) {

            query_ = query_.add(Restrictions.eq("cadastre_number", cadastre_number)) ;

            ret_val = super.getCriterion(query_);
        }

        return  ret_val;
    }

    public List<? extends Object>  findObjectbyInventoryNum(Class<? extends Object> cobj, Integer inventory_number, Integer object_type, Integer org_id) throws ServiceDaoException {

        List<? extends Object> ret_val = null;

        DetachedCriteria query_ = DetachedCriteria.forClass(cobj);

        if( inventory_number != null) {

            query_ = query_.add(Restrictions.eq("inventory_number", inventory_number));

            query_ = query_.add(Restrictions.eq("org_id", org_id));

            query_ =  query_.createCriteria("objectType").add(Restrictions.eq("code_id", object_type));

            ret_val = super.getCriterion(query_);
        }

        return  ret_val;

    }

    private Address_dest findbyAdrnum(Long address_id,Long adr_num, Integer obj_type) throws ServiceDaoException  {

        Address_dest ade = ias.getdestbyIDs(address_id,adr_num);

        if(ade == null) {

            List<Address_src> asr = ias.getsrcbyID(null, adr_num, obj_type);

            if(asr != null) { ade = ias.convertSrctoDest(asr.get(0)); }

        }

        return ade;
    }


}
