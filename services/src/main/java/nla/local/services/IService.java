package nla.local.services;

import org.hibernate.criterion.DetachedCriteria;
import java.io.Serializable;
import java.util.List;


public interface IService<T> {

    void add(T t);

    Class<T> getType();

    void update(T t);

    T get(Class<T> clazz, Serializable id);

    List<T> getCriterion(DetachedCriteria dc);

    void delete(T t);

    void refresh(T t);
}
