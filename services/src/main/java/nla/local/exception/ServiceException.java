package nla.local.exception;


import nla.local.pojos.object.*;

import java.lang.Object;

/**
 * Created by beresnev on 06.03.2015.
 */
public class ServiceException extends Exception {

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public ServiceException( Throwable e, String message) {

        super(e);

        setMessage(message );



    }
}
