package nla.local.services.impl;

import nla.local.dao.BaseDao;
import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.dict.Dict;
import nla.local.services.IDictionaryService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by beresnev on 06.02.2015.
 */
@Service
@Transactional
public class DictionaryServiceImp<T extends Dict> extends BaseDao<T> implements IDictionaryService<T> {

    @Autowired
    public DictionaryServiceImp(SessionFactory sessionFactory)
    {
        super(sessionFactory);

    }

    @Override
    public List<T> getAll(Class<T> clazz)  {

        try {
            return super.getAll(clazz);

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
}
