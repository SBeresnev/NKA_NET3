package nla.local.services.impl;

import nla.local.exception.ServiceDaoException;
import nla.local.pojos.address.*;
import nla.local.services.IAddressService;

import nla.local.util.CodeGenerator;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;


/**
 * Created by beresnev on 29.01.2015.
 */
@Service
@Transactional
public class AddressServiceImp extends BaseServiceImp implements IAddressService {

    @Autowired
    private CodeGenerator scg;

    private static Logger log = Logger.getLogger(AddressServiceImp.class);

    private DetachedCriteria query_Address = DetachedCriteria.forClass(Address_src.class);

    private DetachedCriteria query_Address_dest = DetachedCriteria.forClass(Address_dest.class);

    private DetachedCriteria query_ate = DetachedCriteria.forClass(Ate.class);


    @Override
    public Address_dest getdestbyIDs(Long addressID, Long adr_num ) throws ServiceDaoException {

        Address_dest ret_val = null;

        if(addressID != null) {

            ret_val = (Address_dest) super.get(Address_dest.class, addressID);

        }else {

            if (adr_num != null) {

                DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query_Address_dest);

                query_ = query_.add(Restrictions.eq("adr_num", adr_num));

                List<Address_dest> ret_val_list = super.getCriterion(query_);

                ret_val = ret_val_list.size()>0 ? ret_val_list.get(0): null;

            }
        }

        return ret_val;
    }


    @Override
    public List<Address_src> getsrcbyID(Long id_adr, Long adr_num, Integer prop_type) throws ServiceDaoException {

            List<Address_src> ret_val = null ;

            DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query_Address);

           if(id_adr != null || adr_num != null) {

               query_ = id_adr != null ? query_.add(Restrictions.eq("id_adr", id_adr)) : query_;

               query_ = adr_num != null ? query_.add(Restrictions.eq("adr_num", adr_num)) : query_;

               query_ = query_.add(Restrictions.eq("propType", prop_type));

               ret_val = (List<Address_src>) super.getCriterion(query_);

           }

        return ret_val;
    }

    @Override
    public List<Address_src> findAddress(Integer ate_id, String elementName, Integer objectType, Integer objectPlace, Integer house_num, Integer room_num) throws ServiceDaoException
    {

        List<Address_src> ret_val = null;

        DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query_Address);

        if( ate_id != null) {

            query_ = query_.add(Restrictions.eq("ateId", ate_id));

            query_ = elementName != null ? query_.add(Restrictions.like("elementName", elementName, MatchMode.ANYWHERE).ignoreCase()) : query_;

            query_ = objectType != null ? query_.add(Restrictions.eq("propType", objectType )) : query_;

            query_ = objectPlace != null ? query_.add(Restrictions.eq("elementtypeDepend", objectPlace )): query_;

            query_ = house_num != null ? query_.add(Restrictions.eq("houseNum", house_num)) : query_;

            query_ = room_num != null ? query_.add(Restrictions.eq("roomNum", room_num)) : query_;

            ret_val = super.getCriterion(query_);
        }

        return ret_val;
    }

    @Override
    public List<Address_dest> findAddressDest(String Adr, String soato ) throws ServiceDaoException
    {

        List<Address_dest> ret_val = null;

        DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query_Address_dest);

        if( Adr != null) {

            query_Address_dest =  query_Address_dest.add(Restrictions.like("Adr", Adr, MatchMode.ANYWHERE).ignoreCase());

            query_Address_dest =  soato != null ? query_Address_dest.add(Restrictions.eq("soato", soato)) : query_Address_dest;

            ret_val = super.getCriterion(query_Address_dest);

        }

        return ret_val;
    }

    @Override
    public List<Address_src> findHomeAddress(Integer ate_id, String street_name, Integer house_num, Integer room_num ) throws ServiceDaoException
    {
        // в населенном пункте (1) , (Строение, Часть строения ) (2,3)
        List<Address_src> ret_val = null;

        DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query_Address);

        if( ate_id != null && street_name != null) {

            query_ = query_.add(Restrictions.eq("ateId", ate_id));

            query_ = query_.add(Restrictions.like("elementName", street_name, MatchMode.ANYWHERE).ignoreCase());

            query_ = house_num != null ?  query_.add(Restrictions.eq("houseNum", house_num)) : query_;

            query_ = room_num != null ?  query_.add(Restrictions.eq("roomNum", room_num)) : query_;

            query_ = query_.add(Restrictions.in("propType", new Integer[]{2, 3})); //(Строение, Часть строения ) (2,3)

            query_ = query_.add(Restrictions.eq("elementtypeDepend", 1)); // (в населенном пункте) (1)

            ret_val = super.getCriterion(query_);
        }

        return ret_val;

    }

    @Override
    public List<Ate> findATEbyName(String ate_name) throws ServiceDaoException
    {

        List<Ate> ret_ate = null;

        if (ate_name != null || ate_name != "") {

            DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query_ate);

            query_ = query_.add(Restrictions.eq("ate_name", ate_name));

            ret_ate = super.getCriterion(query_);

        }

        return ret_ate;

    }

    @Override
    public Ate findATEbyId(Integer ATEId) throws ServiceDaoException
    {

        Ate ret_ate = ATEId != null ? (Ate) super.get(Ate.class,ATEId) : null;

        return  ret_ate;

    }

    @Override
    public List<Ate> findATEbyParentId(Integer parentate_id) throws ServiceDaoException
    {

        DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query_ate);

        List<Ate> ret_ate = null;

        query_ = parentate_id != null ? query_.add(Restrictions.eq("parentate_id", parentate_id)) : query_.add(Restrictions.isNull("parentate_id") ) ;

        ret_ate = super.getCriterion(query_);

        return  ret_ate;

    }

    @Override
    public List<Ate> findATEbyParentLike(Integer parentate_id, String ate_name) throws ServiceDaoException
    {

        List<Ate> ret_ate = null;

        DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query_ate);

        query_ = ate_name != null? query_.add(Restrictions.like("ate_name", ate_name, MatchMode.ANYWHERE).ignoreCase()):query_;

        if(parentate_id != null) {

            query_ =  query_.add(Restrictions.ne("ate_id", parentate_id));

            query_ =  query_.add(Restrictions.like("tree_ids", String.valueOf(parentate_id) , MatchMode.ANYWHERE).ignoreCase());

        }

        ret_ate = super.getCriterion(query_);

        return ret_ate;

    }

    @Override
    public List<Ate> expandATE(String Tree_ids) throws ServiceDaoException
    {

        List<Ate> ret_ate = null;

        DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query_ate);

        List<Integer> paret_int = new ArrayList<Integer>();

        for (String par_str : Tree_ids.substring(1).split(";"))
        {
            paret_int.add(Integer.parseInt(par_str));
        }

        query_ = query_.add(Restrictions.in("ate_id",paret_int.toArray()));

        ret_ate = super.getCriterion(query_.addOrder(Order.desc("tree_ids")));

        return ret_ate;

    }

    @Override
    public List<Ate> getAllATE() throws  ServiceDaoException  {

        return super.getAll(Ate.class);

    }

    @Override
    public Address_dest convertSrctoDest(Address_src adr_src) throws ServiceDaoException {

        Address_dest ret_val = new Address_dest();

        String adr = "";

        Ate ate_n = this.findATEbyId(adr_src.getAteId());

        List<Ate> lst_ate = this.expandATE(ate_n.getTree_ids());

        for(Ate ate : lst_ate)
        {
            adr = adr + ate.getAte_name() + ",";

        }

        if(adr_src.getElementName() != null && adr_src.getElementName() != "")

        adr = adr + " " + adr_src.getElementName();

        if(adr_src.getHouseNum() != null)

        adr = adr + " " + adr_src.getHouseNum();

        if(adr_src.getHouseId() != null && adr_src.getHouseId() != "")

        adr = adr + " " + adr_src.getHouseId();

        if(adr_src.getCorpNum() != null)

        adr = adr + " , корп. " + adr_src.getCorpNum();

        if(adr_src.getRoomNum() != null)

        adr = adr + " , кв. " + adr_src.getRoomNum();

        if(adr_src.getRoomId() != null && adr_src.getRoomId() != "")

        adr = adr + " " + adr_src.getRoomId();

        ret_val.setAdr(adr);

        ret_val.setAdr_num(adr_src.getAdr_num());

        ret_val.setSoato(ate_n.getSoato());

        return ret_val;

    }

    @Override
    public Address_dest bindAddress( Address_dest new_adr) throws  ServiceDaoException  {


        if (new_adr.getAddress_id() != null)
        {


            this.update(new_adr);

        } else {



            this.add(new_adr);
        }

        return new_adr;

    }

    @Override
    public void addLinear(String elementName, String comments) throws ServiceDaoException {

        Address_src asr  = new Address_src();

        asr.setId_adr(Long.valueOf(scg.generate("SEQ_LINEAR_ID.NEXTVAL").toString()));

        asr.setElementName(elementName);

        asr.setComments(comments);

        asr.setPropType(5);

        super.add(asr);

    }

}
