package nla.local.services.impl;


import nla.local.dao.BaseDao;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.pojos.orders.Decl;
import nla.local.services.IOrderService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by beresnev on 09.03.2015.
 */

@Service
@Transactional
public class OrderServiceImp  extends BaseDao<Decl> implements IOrderService {


    @Autowired
    public OrderServiceImp(SessionFactory sessionFactory)
    {
        super(sessionFactory);

    }


    @Override
    public void add(Decl decl) throws ServiceDaoException {

        try {

            super.add(decl);

        } catch (DaoException e) { e.printStackTrace(); }

    }


}
