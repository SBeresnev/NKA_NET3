package nla.local.services.impl;

import nla.local.dao.BaseDao;
import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.dict.Dict;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * Created by beresnev on 06.02.2015.
 */
@Service
public class DictionaryServiceImp<T extends Dict> extends BaseDao<T> {

    Class<T> type;

    public T get( Serializable id)
    {
        try {

            return super.get(type,id);

        } catch (DaoException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    @Deprecated
    public void add(T t)
    {}

    @Override
    @Deprecated
    public void update(T t)
    {}

    @Override
    @Deprecated
    public void delete(T t)  {
    }

    @Override
    @Deprecated
    public void refresh(T t) {
    }
}
