package nla.local.services;

/**
 * Created by beresnev on 29.01.2015.
 */

import nla.local.dao.Dao;
import nla.local.exception.ServiceException;


public interface IService<T> extends Dao<T> {

   @Override
   public  void add(T t) throws ServiceException;
}