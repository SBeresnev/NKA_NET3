package nla.local.controller;

import nla.local.controller.forms.ErrorForm;
import nla.local.exception.ServiceDaoException;
import nla.local.exception.SubjectControllerException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate4.HibernateJdbcException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.servlet.http.HttpServletRequest;


/**
 * Created by beresnev on 25.11.2015.
 */

@ControllerAdvice
public class ExceptionController {

    private static final Logger logger = Logger.getLogger(ExceptionController.class);

    @ExceptionHandler(SubjectControllerException.class)
    public ResponseEntity<ErrorForm> subjectExeptionForm(HttpServletRequest req, SubjectControllerException sce)
    {

        ErrorForm ef = new ErrorForm();

        ef.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());

        ef.setMessage(sce.getMessage());

        return new ResponseEntity<ErrorForm>(ef, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @ExceptionHandler(ServiceDaoException.class)
    public ResponseEntity<ErrorForm> subjectUpdateForm(HttpServletRequest req)
    {

        ErrorForm ef = new ErrorForm();

        ef.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());

        ef.setMessage("Нельзя обновить объект");

        return new ResponseEntity<ErrorForm>(ef, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(HibernateJdbcException.class)
    public ResponseEntity<ErrorForm> subjectSQLHibenate(HttpServletRequest req, HibernateJdbcException exception)
    {

        String val = exception.getSQLException().getMessage();

        val = val.substring(val.indexOf("ORA"), val.indexOf("ORA",4));

        ErrorForm ef = new ErrorForm();

        ef.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());

        ef.setMessage(val);

        return new ResponseEntity<ErrorForm>(ef, HttpStatus.INTERNAL_SERVER_ERROR);

    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorForm> handleError(HttpServletRequest req, Exception exception) {

        ErrorForm ef = new ErrorForm();

        ef.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());

        ef.setMessage(exception.getMessage());

        return new ResponseEntity<ErrorForm>(ef, HttpStatus.INTERNAL_SERVER_ERROR);


    }

}
