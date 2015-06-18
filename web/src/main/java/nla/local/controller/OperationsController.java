package nla.local.controller;

import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.operations.Operation;
import nla.local.services.impl.OperationsServiceImp;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by belonovich on 08.04.2015.
 */
@RestController
@RequestMapping({"/operations"})
public class OperationsController {

    private static final Logger logger = Logger.getLogger(OperationsController.class);

    @Autowired
    private OperationsServiceImp operationsService;


    @RequestMapping(value={"/get_all"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<Operation> getAll() throws DaoException {
        logger.info("root - /operations/get_all");
        return operationsService.getAll();
    }

    @RequestMapping(value={"/add"}, method={RequestMethod.POST})
    public List<Operation> add(@RequestBody Operation operation) throws DaoException {
        logger.info("root - /operations/add");
       /*
        operation.setExecutor(fullSubjectService.getSubject(30200));
        operation.setStatus(1);
        operation.setRegDate(new Date());
        */
        operationsService.add(operation);
        return operationsService.getAll();
    }

    @RequestMapping(value={"/get_from_decl"}, method={RequestMethod.GET})
    public List<Operation> getFromDecl(Integer declId) throws DaoException {
        logger.info("root - /operations/get_from_decl");
        return operationsService.getFromDecl(declId);
    }

    @RequestMapping(value={"/delete"}, method={RequestMethod.DELETE})
    public void deleteOperation(Integer ooperId) throws DaoException {
        logger.info("root - /operations/get_from_decl");
        operationsService.delete(operationsService.get(ooperId));
    }
}
