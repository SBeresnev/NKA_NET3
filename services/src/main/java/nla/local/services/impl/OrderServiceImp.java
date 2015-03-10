package nla.local.services.impl;


import nla.local.dao.BaseDao;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.orders.Decl;
import nla.local.pojos.subjects.Person;
import nla.local.services.IOrderService;
import nla.local.util.CodeGenerator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


/**
 * Created by beresnev on 09.03.2015.
 */

@Service
@Transactional
public class OrderServiceImp  extends BaseDao<Decl> implements IOrderService {


    @Autowired
    private CodeGenerator scg;

    private BaseDao BD;


    @Autowired
    public OrderServiceImp(SessionFactory sessionFactory)
    {
        super(sessionFactory);

        BD = new BaseDao(sessionFactory);

    }


    @Override
    public void add(Decl decl) throws ServiceDaoException {

        try {

            super.add(decl);

        } catch (DaoException e) { e.printStackTrace(); }

    }


    @Override
    public void postOrder(Decl decl) throws ServiceException {

        this.getSession().persist(decl);

        try {

            super.add(decl);

        } catch (DaoException e) {e.printStackTrace();}

        Set<Person> sp = decl.getDeclarants();

    }
}
