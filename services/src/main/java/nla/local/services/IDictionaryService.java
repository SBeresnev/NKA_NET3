package nla.local.services;

import nla.local.pojos.dict.Dict;

import java.util.List;

/**
 * Created by belonovich on 12.02.2015.
 */

public interface  IDictionaryService extends IService<Dict> {

    public List<Dict> getDict(Integer a_type);

}
