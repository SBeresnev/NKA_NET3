package nla.local.services;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.rights.Right;
import nla.local.pojos.rights.RightOwner;


import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by beresnev on 23.06.2015.
 */
public interface IRightService extends IService {


    /*************************Close right need for update***********************************************************************/
    public Right CloseRight(Right rgt)  throws ServiceDaoException, ServiceException;

    /*************************Operation block***********************************************************************************/
    public Right getRight(Long rght) throws ServiceDaoException, ServiceException;

    public RightOwner getRightOwner(Long rght_own) throws ServiceDaoException;

    public Right addRight(Right rght) throws ServiceDaoException, ServiceException;

    public RightOwner addRightOwner(RightOwner rght_own) throws ServiceDaoException, ServiceException ;


    /*************************Main operations***********************************************************************************/
    public void passSingleRight(RightOwner rght_own) throws ServiceDaoException, ServiceException ;

    public void passSharedRight(HashMap<RightOwner,RightOwner> right_own) throws ServiceDaoException, ServiceException ;

    public void splitSharedRight(List<RightOwner> child_owners, RightOwner parent_owner) throws ServiceDaoException, ServiceException ;

    /*************************Serch function block***********************************************************************************/

    public List<Right> getRightbySubject(Integer person_id) throws ServiceDaoException;

    public List<Right> getRightbyObject(Long obj_id) throws ServiceDaoException;

    public List<RightOwner> getRightbyObjectOwn(String Adr, String soato ) throws ServiceDaoException;

    public List<Right> getRightbyObject(String Adr, String soato ) throws ServiceDaoException;

    public List<RightOwner> getRightbyObjectPersonOwn(Long[] obj_ids, Integer person_id) throws ServiceDaoException;



    public List<Right> getlimitationsRight (Long[] right_ids) throws ServiceDaoException, ServiceException;

    public List<RightOwner> getRightOwnersbyRight (Long[] right_ids) throws ServiceDaoException, ServiceException; ;

    /************************* update operations *****************************************************************************************/

    public void updateRight(Right right) throws ServiceDaoException ;

    public void updateRightOwner(RightOwner rightowner) throws ServiceDaoException ;

    /************************* Only for Test *****************************************************************************************/
    @Deprecated
    public List<RightOwner> findbyrightCountTypeOwn( CatalogItem countType) throws ServiceDaoException ;
}
