package nla.local.exception;

import org.springframework.orm.hibernate4.HibernateJdbcException;

import java.nio.charset.Charset;

/**
 * Created by beresnev on 24.11.2015.
 */

public class SubjectControllerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SubjectControllerException(String villageName) {

        super(villageName);


    }

}