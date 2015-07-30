package nla.local.services;


import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.orders.Decl;
import nla.local.pojos.orders.Journal;
import nla.local.pojos.subjects.OPerson;
import nla.local.pojos.subjects.Person;

import java.io.Serializable;
import java.util.List;


/**
 * Created by beresnev on 09.03.2015.
 */
public interface IDeclService extends IService<Decl> {


    public List<Decl> getAllOrders() throws ServiceDaoException;

    public List<Journal> getJournal(OPerson declUser) throws ServiceDaoException, ServiceException;

    public Journal castToJournal(Decl decl) throws ServiceException;

    public List<Decl> getDeclsByUser(OPerson oPerson) throws ServiceDaoException;

    public Decl get(Serializable id) throws ServiceDaoException;

    public Decl addNewEmptyDecl(OPerson person, CatalogItem declResolutionType) throws ServiceDaoException ;

    public Decl addNewDeclarantInDecl(Person person, Integer type, Long idDecl , Long[] clients) throws ServiceDaoException;

    public Decl deleteDeclarantInDecl(Serializable declarantId, Serializable idDecl) throws ServiceDaoException;

    public List<Decl> getOrderByUser(OPerson oPerson)  throws ServiceException;

    public Decl changeDeclType(Long declId) throws  ServiceDaoException;

    public Decl changeDeclUser(Long declId, Long decluser_id, OPerson user) throws  ServiceDaoException;

    public Decl setStatus(CatalogItem dict, Long declId, OPerson user) throws ServiceDaoException ;

}
