package nla.local.services.impl;

import nla.local.exception.ServiceDaoException;
import nla.local.pojos.operations.Operation;
import nla.local.services.IOperationsService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * Created by belonovich on 08.04.2015.
 */
@Service
@Transactional
public class OperationsServiceImp extends BaseServiceImp<Operation> implements IOperationsService {


    @Override
    public List<Operation> getAll() throws ServiceDaoException {
        return super.getAll(Operation.class);
    }


    @Override
    public Operation get(Integer id) throws ServiceDaoException {

        return super.get(Operation.class, id);
    }

    @Override
    public List<Operation> getFromDecl(Integer declId) throws ServiceDaoException {

        DetachedCriteria dc = DetachedCriteria.forClass(Operation.class);

        if (declId != null) {

            dc.add(Restrictions.eq("DECL_ID", declId));

            return super.getCriterion(dc);

        } else {
            return Collections.EMPTY_LIST;
        }
    }

}
