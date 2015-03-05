package nla.local.services;


import nla.local.exception.ServiceException;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;


public interface ISubjectService<T> extends IService<T> {


    public T getSubject(Serializable id) throws ServiceException;

    public void refreshSubject(T t) throws ServiceException;

    public List<T> findSubjects(DetachedCriteria dc) throws ServiceException;

}
