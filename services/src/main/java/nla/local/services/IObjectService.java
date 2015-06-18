package nla.local.services;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.object.Object;
import nla.local.pojos.object.Object_dest;
import nla.local.pojos.object.Object_src;


import java.util.List;

/**
 * Created by beresnev on 03.06.2015.
 */

public interface IObjectService extends IService{

   // public <T extends Object> List<T>  findObjectbyInventoryNum(Class<T> cobj, Integer inventory_number, Integer object_type, Integer org_id) throws ServiceDaoException;

    public List<? extends Object> findObjectbyAddress(Class<? extends Object> cobj, List<Long> address_id) throws ServiceDaoException;

    public List<? extends Object>  findObjectbyCadastreNum(Class<? extends Object> cobj, String cadastre_number) throws ServiceDaoException;

    public List<? extends Object>   findObjectbyInventoryNum(Class<? extends Object> cobj, Integer inventory_number, Integer object_type, Integer org_id) throws ServiceDaoException;

    public Object_dest bindObject( Object_dest dos) throws ServiceDaoException, ServiceException;

    public Object_dest convertSrctoDest(Object_src adr_src) throws ServiceDaoException;

    public List<Object_dest> findbyobjDestid(Long obj_dest_id) throws ServiceDaoException;

    public List<Object_dest> findObjectbyAddressCommon(List<Long> address_id) throws ServiceDaoException;

    public List<Object_dest> findObjectbyInventoryNumCommon(Integer inventory_number, Integer object_type, Integer org_id) throws ServiceDaoException ;

    public List<Object_dest> findObjectbyCadastreNumCommon(String cadastre_number) throws ServiceDaoException ;

}
