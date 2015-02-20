package nla.local.services.impl;

import nla.local.dao.BaseDao;
import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.dict.Dict;
import nla.local.services.IDictionaryService;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by beresnev on 06.02.2015.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class DictionaryServiceImp extends BaseDao<Dict> implements IDictionaryService {

    @Autowired
    public DictionaryServiceImp(SessionFactory sessionFactory)
    {
        super(sessionFactory);

    }

    @Override
    public List getCriterion(DetachedCriteria dc)
    {
        try {

            return super.getCriterion(dc);

        } catch (DaoException e) {

            e.printStackTrace();

        }
        return null;
    }


    public List<Dict> getAll()  {

        try {


            return super.getAll(Dict.class);

        } catch (DaoException e) {

            e.printStackTrace();

        }
        return null;
    }

    @Override
    @Deprecated
    public void add(Dict t)
    {}

    @Override
    @Deprecated
    public void update(Dict t)
    {}

    @Override
    @Deprecated
    public void delete(Dict t)  {
    }

    @Override
    @Deprecated
    public void refresh(Dict t) {
    }

    @Override
    public List<Dict> getDict(Integer a_type) {

        DetachedCriteria dc = DetachedCriteria.forClass(Dict.class).add(Restrictions.eq("analytic_type",a_type));

        List<Dict> ret_val = this.getCriterion(dc);

        return ret_val;


    }
}
