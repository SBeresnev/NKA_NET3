package nla.local.services;

import nla.local.dao.Dao;
import nla.local.exception.ServiceException;
import nla.local.pojos.orders.Decl;

/**
 * Created by beresnev on 09.03.2015.
 */
public interface IOrderService extends Dao<Decl> {

    public void postOrder(Decl decl) throws ServiceException;

}
