package nla.local.exception;


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

    public ServiceException(Throwable t, String message) {
        super(t);

    }
}
