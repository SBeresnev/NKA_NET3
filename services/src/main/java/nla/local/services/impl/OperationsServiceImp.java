package nla.local.services.impl;

import nla.local.exception.ServiceDaoException;
import nla.local.pojos.operations.EntityType;
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
    public List<Operation> getEntytyOper (Long declId, EntityType et) throws ServiceDaoException {

        DetachedCriteria dc = DetachedCriteria.forClass(Operation.class);

        if (declId != null) {

            dc = dc.add(Restrictions.eq("declId", declId));

            dc = dc.add(Restrictions.eq("status",1));

            dc = dc.add(Restrictions.eq("entytyType",EntityType.toInt(et)));

            List<Operation> ret_val = super.getCriterion(dc);

            return ret_val;

        } else {

            return Collections.EMPTY_LIST;

        }

    }

}
