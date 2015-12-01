package nla.local.exception;

import java.nio.charset.Charset;

/**
 * Created by beresnev on 24.11.2015.
 */

public class SubjectControllerException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SubjectControllerException(String villageName) {

        super(villageName);

        String value = new String(villageName.getBytes(Charset.forName("UTF-8")));


    }
}