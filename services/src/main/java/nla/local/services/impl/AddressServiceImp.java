package nla.local.services.impl;

import nla.local.dao.BaseDao;
import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.Address;
import nla.local.services.IAdressService;
import nla.local.services.impl.subjects.SubjectServiceImp;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by beresnev on 29.01.2015.
 */
@Service
public class AddressServiceImp extends BaseDao<Address> implements IAdressService {

    private static Logger log = Logger.getLogger(SubjectServiceImp.class);

    private DetachedCriteria query;

    public AddressServiceImp() {

    }

    @Override
    public List<Address> findAddress(DetachedCriteria dc) {

        List<Address> out = null;

        if (dc == null) { dc = getQuery(); }
        else { setQuery(dc);}

        try {

            out = super.getCriterion(query);

        } catch (DaoException e) {

            e.printStackTrace();
        }

        return out;

    }

    public DetachedCriteria getQuery() {
        return query;
    }

    public void setQuery(DetachedCriteria query) {
        this.query = query;
    }

}
