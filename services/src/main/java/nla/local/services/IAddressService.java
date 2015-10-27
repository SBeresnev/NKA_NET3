package nla.local.services;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.address.Address_src;
import nla.local.pojos.address.Ate;
import nla.local.pojos.address.Address_dest;
import nla.local.pojos.object.Object_dest;

import java.util.List;

/**
 * Created by beresnev on 29.01.2015.
 */

public interface IAddressService extends IService {


    public List<Ate> findATEbyName(String ate_name) throws  ServiceDaoException;

    public Ate findATEbyId(Integer ATEId) throws  ServiceDaoException ;

    public List<Ate> expandATE(String Tree_ids) throws ServiceDaoException;

    public List<Ate> findATEbyParentId(Integer parentate_id) throws  ServiceDaoException;

    public List<Ate> findATEbyParentLike(Integer parentate_id, String ate_name) throws  ServiceDaoException;

    public List<Ate> getAllATE() throws  ServiceDaoException ;

    public String fillParentAte(Ate in_ate) throws ServiceDaoException;

    /************************************************************************/

    public List<Address_src> findHomeAddress(Integer ate_id, String street_name, Integer house_num, Integer corp_num, Integer room_num, Integer elem_type_dep  ) throws ServiceDaoException;

    public List<Address_src> findAddress(Integer ate_id, String elementName, Integer objectType, Integer objectPlace, Integer house_num, Integer room_num )  throws ServiceDaoException;

    public List<Address_src> findParcelAddress(Integer ate_id, String street_name, Integer house_num , Integer elem_type_dep ) throws ServiceDaoException;

    public Address_dest bindAddress( Address_dest new_adr) throws ServiceDaoException;

    public List<Address_src> getsrcbyID(Long adr_id, Long adr_num, Integer prop_type) throws ServiceDaoException;



    /************************************************************************/

    public Address_dest getdestbyIDs(Long addressID, Long adr_num ) throws ServiceDaoException;

    public List<Address_dest> findAddressDest(String Adr, String soato) throws ServiceDaoException;

    public Address_dest convertSrctoDest(Address_src adr_src) throws ServiceDaoException;


}
