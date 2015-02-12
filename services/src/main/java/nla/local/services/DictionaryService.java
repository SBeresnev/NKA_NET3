package nla.local.services;

/**
 * Created by belonovich on 12.02.2015.
 */

import java.util.List;

public interface  DictionaryService<T> extends IService<T> {
    public List<T> getAll(Class dict);
}
