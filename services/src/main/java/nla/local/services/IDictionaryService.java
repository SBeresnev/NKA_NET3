package nla.local.services;

import nla.local.exception.ServiceException;
import nla.local.pojos.dict.Dict;
import nla.local.pojos.dict.DictPk;
import nla.local.pojos.dict.EnumDict;

import java.util.List;

/**
 * Created by belonovich on 12.02.2015.
 */

public interface  IDictionaryService extends IService<Dict> {

    public List<Dict> getDict(EnumDict a_type) throws ServiceException;;

    public Dict getDict(DictPk dPk) throws ServiceException;

    public List<Dict> getAll() throws ServiceException;

}
