package nla.local.controller;

import nla.local.controller.forms.DeclarantForm;
import nla.local.controller.forms.StatusPutForm;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogPk;
import nla.local.pojos.orders.Decl;
import nla.local.pojos.orders.Journal;
import nla.local.pojos.subjects.OPerson;
import nla.local.pojos.subjects.Person;
import nla.local.pojos.subjects.SubjectClass;
import nla.local.services.impl.CatalogServiceImp;
import nla.local.services.impl.DeclServiceImp;
import nla.local.services.impl.subjects.JSubjectServiceImp;
import nla.local.services.impl.subjects.OSubjectServiceImp;
import nla.local.services.impl.subjects.PSubjectServiceImp;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * Created by belonovich on 16.03.2015.
 */

@RestController
@RequestMapping({"/decl"})
public class DeclController {

    private static final Logger logger = Logger.getLogger(DeclController.class);

    @Autowired
    private CatalogServiceImp catalogService;

    @Autowired
    private DeclServiceImp orderServiceImp;

    @Autowired
    private OSubjectServiceImp oServices;



    @Qualifier("PSubjectServiceImp")
    @Autowired
    private PSubjectServiceImp pServices;

    @Autowired
    private JSubjectServiceImp jServices;

    @RequestMapping(value = "/get_journal", method = {RequestMethod.GET})
    public List<Journal> getJournal() throws ServiceDaoException, ServiceException {
        logger.info("root - decl/get_journal");
        List<OPerson> oPersons = oServices.findOffUser("", "", "", null, "", null);
        return orderServiceImp.getJournal(oPersons.get(0));
    }

    @RequestMapping(value = "/new_decl", method = {RequestMethod.GET})
    public Serializable getNewDecl() throws DaoException, ServiceException {
        logger.info("root - decl/new_decl");
        List<OPerson> oPersons = oServices.findOffUser("", "", "", null, "", null);
        return orderServiceImp.addNewEmptyDecl(oPersons.get(0), catalogService.getCatalogItem(new CatalogPk(1, Integer.decode(CatalogConstants.RESOLUTION_TYP))));
    }

    @RequestMapping(value = "/get_decl", method = {RequestMethod.GET})
    public Decl getDecl(Long id) throws DaoException, ServiceException {
        logger.info("root - decl/get_decl");
        return orderServiceImp.get(id);
    }

    @RequestMapping(value = "/delete_subject_in_decl", method = {RequestMethod.DELETE})
    public Decl deleteSubject(Long idDecl,Long... declarantIds) throws DaoException, ServiceException {

        logger.info("root - decl/delete_subject_in_decl");

        Decl dcl =orderServiceImp.deleteDeclarantInDecl(declarantIds, idDecl);

        return dcl;
    }

    @RequestMapping(value = "/add_subject_in_decl", method = {RequestMethod.POST})
    public Decl addSubject(@RequestBody DeclarantForm declarantForm) throws DaoException, ServiceException {
        logger.info("root - decl/add_subject_in_decl");
        Person person = pServices.getSubject(declarantForm.getIdSubject());
        if(person == null) person = jServices.getSubject(declarantForm.getIdSubject());
        return orderServiceImp.addNewDeclarantInDecl(person, declarantForm.getType(), declarantForm.getIdDecl(), declarantForm.getClients());
    }

    @RequestMapping(value = "/status", method = {RequestMethod.POST})
    public Decl setStatus(@RequestBody StatusPutForm statusPutForm) throws DaoException, ServiceException {
        logger.info("root - decl/status");
        List<OPerson> oPersons = oServices.findOffUser("", "", "", null, "", null);
        return orderServiceImp.setStatus(catalogService.getCatalogItem(new CatalogPk(statusPutForm.getStatus(), Integer.decode(CatalogConstants.RESOLUTION_TYP))), statusPutForm.getDeclId(), oPersons.get(0));
    }

    @RequestMapping(value = "/change_decltype", method = {RequestMethod.PUT})
    public Decl changeDecltype(@RequestBody Long declId) throws DaoException, ServiceException {
        logger.info("root - decl/change_decltype");
        return orderServiceImp.changeDeclType(declId);
    }
}
