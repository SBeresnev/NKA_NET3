package nla.local.services;

import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.subjects.JPerson;
import nla.local.pojos.subjects.JurMINJST;


import java.util.List;

/**
 * Created by beresnev on 10.08.2015.
 */
public interface IJusticeService {

    public JurMINJST findSubjectUnp(Integer unp) throws ServiceDaoException;

    public List<JurMINJST> findSubjectName(String name) throws ServiceDaoException;

    public JPerson casttoPerson(JurMINJST resp) throws ServiceException, ServiceDaoException;



}
