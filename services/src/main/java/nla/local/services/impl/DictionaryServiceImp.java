package nla.local.services.impl;

import nla.local.dao.BaseDao;
import nla.local.pojos.dict.Dict;
import nla.local.services.IService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by beresnev on 06.02.2015.
 */
@Service
@Transactional
public class DictionaryServiceImp<T extends Dict> extends BaseDao<T> implements IService<T> {

    @Autowired
    public DictionaryServiceImp(SessionFactory sessionFactory)
    {
        super(sessionFactory);

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
