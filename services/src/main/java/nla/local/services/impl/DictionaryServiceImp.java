package nla.local.services.impl;

import nla.local.dao.BaseDao;
import nla.local.pojos.dict.Dict;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by beresnev on 06.02.2015.
 */
@Service
public class DictionaryServiceImp<T extends Dict> extends BaseDao<T> {

    @Autowired
    public DictionaryServiceImp(SessionFactory sessionFactory)
    {
        super(sessionFactory);

    }

   /* @Override
    public List<T> getAll(Class<T> dict)
    {
        Class<Dict> p = dict. //dict.getClass();
        try {
               super.getAll(p);
            } catch (DaoException e) {
            e.printStackTrace();
        }

    }*/

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
}
