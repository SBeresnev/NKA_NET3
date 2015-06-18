package nla.local.services;


import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.subjects.PPerson;
import nla.local.pojos.subjects.PassportNCA;
import nla.local.pojos.subjects.RespNCA;

/**
 * Created by beresnev on 24.02.2015.
 */

public interface IPassportService {

   public RespNCA findSubject(PassportNCA PassNCA) throws ServiceDaoException;

   public PPerson casttoPerson(RespNCA resp) throws ServiceException;


}

