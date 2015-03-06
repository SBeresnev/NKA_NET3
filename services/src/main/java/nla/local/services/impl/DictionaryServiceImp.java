package nla.local.services.impl;

import nla.local.dao.BaseDao;
import nla.local.dao.exceptions.DaoErrorCode;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.pojos.dict.Dict;
import nla.local.pojos.dict.DictPk;
import nla.local.pojos.dict.EnumDict;
import nla.local.services.IDictionaryService;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * Created by beresnev on 06.02.2015.
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class DictionaryServiceImp extends BaseDao<Dict> implements IDictionaryService {

    /* hash for most common dicts */

    private HashMap<EnumDict,List<Dict>> common ;

    @Autowired
    public DictionaryServiceImp(SessionFactory sessionFactory)
    {
        super(sessionFactory);

        common = new HashMap<EnumDict, List<Dict>>();

    }

    @Override
    public List<Dict> getCriterion(DetachedCriteria dc) throws ServiceDaoException
    {

        List ret_val = null;

        try {

            ret_val = super.getCriterion(dc);

        } catch (DaoException e) {

            throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_005, null);

        }

        return ret_val;

    }

    @Override
    public Dict getDict(DictPk dPk) throws ServiceDaoException
    {
        Dict ret_val = null;

        try {

            ret_val = super.get(Dict.class,dPk);

        } catch (DaoException e) {

            throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_000, dPk);

        }

        return ret_val;

    }

    @Override
    public List<Dict> getAll() throws ServiceDaoException {

        List ret_val = null;

        try {


            ret_val =  super.getAll(Dict.class);

        } catch (DaoException e) {

            throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_005, null);

        }

        return ret_val;
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
    public void delete(Dict t)
    {}

    @Override
    @Deprecated
    public void refresh(Dict t)
    {}

    @Override
    public List<Dict> getDict(EnumDict code_type) throws ServiceDaoException {

        List<Dict> ret_val = null;

        DetachedCriteria dc = DetachedCriteria.forClass(Dict.class).add(Restrictions.eq("analytic_type", code_type.toInt()));

        if(code_type == EnumDict.SubjectType || code_type == EnumDict.OrgStruct || code_type == EnumDict.TorStruct || code_type == EnumDict.State ){

          if(common.containsKey(code_type) == false)

              common.put(code_type,this.getCriterion(dc));

            return common.get(code_type);
        }

        ret_val = this.getCriterion(dc);

        return ret_val;

    }
}
