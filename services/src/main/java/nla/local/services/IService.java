package nla.local.services;

/**
 * Created by beresnev on 29.01.2015.
 */
import nla.local.dao.Dao;
import org.hibernate.criterion.DetachedCriteria;
import java.io.Serializable;
import java.util.List;


public interface IService<T> extends Dao<T> {

}