package nla.local.services;

import nla.local.dao.exceptions.DaoException;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;


public interface ISubjectService<T> extends IService<T> {

    public void addSubject(T t) throws DaoException;

    public void refreshSubject(T t) throws DaoException;

    public List<T> findSubject(DetachedCriteria dc);

    public List<T> findByNameType(String fullName, String regNumber, Integer subjectType );

    public List<T> findByFIOType(String surname, String firstname  ,String fathername, String personalNumber, Integer subjectType );



}
