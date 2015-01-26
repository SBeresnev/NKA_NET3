package nla.local.services;

import org.hibernate.criterion.DetachedCriteria;
import java.io.Serializable;
import java.util.List;


public interface IService<T> {

    void add(T t);

    void update(T t);

    T get(Class<T> clazz, Serializable id);

    List<T> getCriterion(DetachedCriteria crio);

    void delete(T t);

    void refresh(T t);
}
