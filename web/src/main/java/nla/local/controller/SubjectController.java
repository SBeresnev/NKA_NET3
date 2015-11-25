package nla.local.controller;

import nla.local.controller.forms.SearchMvdForm;
import nla.local.controller.forms.SearchSubjectForm;
import nla.local.controller.forms.SubjectForm;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.exception.SubjectAlreadyExistsException;
import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.subjects.*;
import nla.local.services.impl.subjects.*;
import nla.local.util.CodeGenerator;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping({"/subject"})
public class SubjectController {

    private static final Logger logger = Logger.getLogger(SubjectController.class);

    @Qualifier("PSubjectServiceImp")
    @Autowired
    public PSubjectServiceImp pService;

    @Qualifier("JSubjectServiceImp")
    @Autowired
    public JSubjectServiceImp jService;


    @Autowired
    public PassportServiceImp passService;


    @Autowired
    public OSubjectServiceImp oService;

    @Autowired
    public JusticeServiceImp justiceService;

    @Autowired
    public CodeGenerator scg;

    @RequestMapping(value = {"/search"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<Person> getPerson(SearchSubjectForm searchSubjectForm) throws DaoException {
        logger.info("root - /subject/search");

        ArrayList<Person> result_p = new ArrayList();
        if (searchSubjectForm.getSubjectClass() == SubjectClass.PRV) {
             result_p.addAll(this.pService.findByFIOType(searchSubjectForm.surname, searchSubjectForm.firstname, searchSubjectForm.lastname, searchSubjectForm.getNumber(), searchSubjectForm.getType()));
        }
        if (searchSubjectForm.getSubjectClass() == SubjectClass.JUR) {
            result_p.addAll(this.jService.findByNameType(searchSubjectForm.getName(), searchSubjectForm.getNumber(), searchSubjectForm.getType()));
        }
        return result_p;
    }

    @RequestMapping(value = {"/update"}, method = {org.springframework.web.bind.annotation.RequestMethod.PUT})
    public Person updatePerson(@RequestBody SubjectForm subjectForm) throws ServiceDaoException {
        logger.info("root - /subject/update");
        PPerson pPerson = this.pService.getSubject(subjectForm.getSubjectId());
            this.pService.update(subjectForm.updatePPerson(pPerson));
        return pPerson;
    }

    @ExceptionHandler(value = SubjectAlreadyExistsException.class)
    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    public Person addPerson(@RequestBody SubjectForm subjectForm) throws Exception {

        logger.info("root - /subject/add");

        if (subjectForm.getSubjectClass() == SubjectClass.PRV) {
            if (pService.findByFIOType("", "", "", subjectForm.getPersonalNumber(), subjectForm.getSubjectType()).size() != 0)
                throw new SubjectAlreadyExistsException("Субъект уже существует");

            PPerson pPerson = new PPerson();
            subjectForm.updatePPerson(pPerson);
            pPerson.subjectdataid = Integer.valueOf(scg.generate("SEQ_SUBJECTSDATA_F_ID.nextval").toString());
            pService.add(pPerson);

            return pPerson;
        }
       if(subjectForm.getSubjectClass() == SubjectClass.JUR ){
           if ( jService.findByNameType("",subjectForm.getUnp(),null).size() != 0)
               throw new SubjectAlreadyExistsException("Субъект уже существует");

            JPerson jPerson = new JPerson();
            subjectForm.updateJPerson(jPerson);
            jPerson.subjectdataid = Integer.valueOf(scg.generate("SEQ_SUBJECTSDATA_J_ID.nextval").toString());
            jService.add(jPerson);

           return jPerson;

        }

        return null;
    }

    @RequestMapping(value = {"/mvd"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<PPerson> getMVDPerson(SearchMvdForm searchMvdForm) throws Exception {

        logger.info("root - /subject/mvd");
        RespNCA resp = this.passService.findSubject(searchMvdForm.createOrGetPassportNCAObj());
        PPerson pp = this.passService.casttoPerson(resp);
        List<PPerson> list = new ArrayList();
        list.add(pp);

        return list;
    }

    @RequestMapping(value = {"/minjust"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<JPerson> getJusticePerson(Integer unp, String name) throws Exception {

        logger.info("root - /subject/minjust");

        List<JPerson> ret_val = new ArrayList<JPerson>() ;

         if(unp != null) {

             JurMINJST jum = justiceService.findSubjectUnp(unp);

             JPerson jp = justiceService.casttoPerson(jum);

             ret_val.add(jp);

         } else {

             List<JurMINJST> jum_list = justiceService.findSubjectName(name);

             for (JurMINJST jum : jum_list)
             {
                 JPerson jp  = justiceService.casttoPerson(jum);

                 ret_val.add(jp);
             }
         }

        return ret_val;
    }

    @RequestMapping(value = {"/minjust_serv"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<JurMINJST> getJusticeService(Integer unp, String name) throws Exception {

        logger.info("root - /subject/minjust_serv");

        List<JurMINJST> ret_val = new ArrayList<JurMINJST>() ;

        if(unp != null) {

            JurMINJST jum = justiceService.findSubjectUnp(unp);

            ret_val.add(jum);

        } else {

            ret_val = justiceService.findSubjectName(name);

            //for (JurMINJST jum : jum_list) { ret_val.add(jum); }
        }

        return ret_val;
    }

    @RequestMapping(value = {"/juridical"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<JPerson> getJuridicalPerson(SubjectForm subjectForm) {
        logger.info("root - /subject/juridical");
        List<JPerson> result_j = null;

      try {
            result_j = this.jService.getAll();

            JPerson jp = this.jService.getSubject(((JPerson) result_j.get(0)).getSubjectId());
            DetachedCriteria.forClass(CatalogItem.class).add(Restrictions.eq("analytic_type", Integer.valueOf(CatalogConstants.SUBJECT_TYP))).add(Restrictions.eq("parent_code", Integer.valueOf(SubjectClass.toInt(SubjectClass.OFC))));

            result_j.clear();
            result_j = this.jService.findByNameType("Upd", null, subjectForm.getSubjectId());

        } catch (ServiceDaoException e) {  e.printStackTrace();  }

        return result_j;
    }

}
