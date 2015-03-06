package nla.local.exception;

import nla.local.dao.exceptions.DaoErrorCode;
import nla.local.dao.exceptions.DaoException;

/**
 * Created by beresnev on 05.03.2015.
 */

public class ServiceDaoException extends DaoException {


    public ServiceDaoException(Throwable t, DaoErrorCode de, Object... params) {

        super(t, de, params);

    }


}

