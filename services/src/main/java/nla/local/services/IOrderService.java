package nla.local.services;

import nla.local.dao.Dao;
import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.orders.Decl;
import nla.local.pojos.orders.DeclUser;
import nla.local.pojos.orders.Journal;
import nla.local.pojos.subjects.OPerson;
import nla.local.pojos.subjects.Person;

import java.util.List;
import java.util.Map;

/**
 * Created by beresnev on 09.03.2015.
 */
public interface IOrderService extends Dao<Decl> {

    public void postOrder(Decl decl) throws ServiceException;

    public List<Decl> getAllOrder() throws ServiceException;

    public Map<Journal,Decl> getJournal(OPerson declUser) throws ServiceDaoException, ServiceException;

    public Journal castToJournal(Decl decl) throws ServiceException;

}
