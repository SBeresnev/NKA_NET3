package nla.local.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by beresnev on 24.11.2015.
 */
@ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR, reason="Объект уже существует")
public class SubjectAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public SubjectAlreadyExistsException(String villageName) {
        super(villageName+"");
    }
}