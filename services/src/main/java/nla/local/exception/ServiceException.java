package nla.local.exception;

import nla.local.dao.exceptions.DaoErrorCode;
import nla.local.dao.exceptions.DaoException;

/**
 * Created by beresnev on 05.03.2015.
 */

public class ServiceException extends DaoException {

    public ServiceException(Throwable t, DaoErrorCode code, Object... params) {
        super(t, code, params);
    }
}

