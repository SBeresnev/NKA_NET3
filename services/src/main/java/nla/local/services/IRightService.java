package nla.local.services;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.rights.Right;
import nla.local.pojos.rights.RightOwner;

import java.util.HashMap;
import java.util.List;


/**
 * Created by beresnev on 23.06.2015.
 */
public interface IRightService extends IService {


    /*************************Close right need for update***********************************************************************/
    //public Right CloseRight(Right rgt)  throws ServiceDaoException, ServiceException;

    /*************************Operation block***********************************************************************************/
    public Right getRight(Long rght_id) throws ServiceDaoException, ServiceException;

    public RightOwner getRightOwner(Long rght_own_id) throws ServiceDaoException;

    public Right addRight(Right rght) throws ServiceDaoException, ServiceException;

    public RightOwner addRightOwner(RightOwner rght_own) throws ServiceDaoException, ServiceException ;

    public void updateRight(Right right) throws ServiceDaoException ;

    public void updateRightOwner(RightOwner rightowner) throws ServiceDaoException ;

    /*************************Main operations***********************************************************************************/
    public void passSingleRight(RightOwner rght_own) throws ServiceDaoException, ServiceException ;

    public void passSharedRight(HashMap<RightOwner,RightOwner> right_own) throws ServiceDaoException, ServiceException ;

    public void splitSharedRight(List<RightOwner> child_owners, RightOwner parent_owner) throws ServiceDaoException, ServiceException ;

    /*************************Serch function block***********************************************************************************/

    public List<RightOwner> getRighOwnbyObjectPerson(Long[] obj_ids, Integer person_id) throws ServiceDaoException;

    public List<RightOwner> getRightbyObjectAddr(String Adr, String soato ) throws ServiceDaoException;

    public List<Right> getRightbyObjectPerson(Long[] obj_ids, Integer person_id) throws ServiceDaoException;

    public List<Right> getRightbyRightOwner(Long[] right_own_ids) throws ServiceDaoException;

    /************************* Only for Test *****************************************************************************************/
    @Deprecated
    public List<RightOwner> findbyrightCountType( Integer countType) throws ServiceDaoException ;

    @Deprecated
    public List<RightOwner> getRightOwnersbyRight (Long[] right_ids) throws ServiceDaoException, ServiceException;

}
