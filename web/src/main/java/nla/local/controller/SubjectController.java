package nla.local.controller;

import nla.local.controller.forms.SearchMvdForm;
import nla.local.controller.forms.SearchSubjectForm;
import nla.local.controller.forms.SubjectForm;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.pojos.dict.CatalogConstants;
import nla.local.pojos.dict.CatalogItem;
import nla.local.pojos.subjects.*;
import nla.local.services.IJusticeService;
import nla.local.services.impl.subjects.*;
import nla.local.util.CodeGenerator;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

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
    public void updatePerson(@RequestBody SubjectForm subjectForm) throws ServiceDaoException {
        logger.info("root - /subject/update");
        PPerson pPerson = this.pService.getSubject(subjectForm.getSubjectId());
            this.pService.update(subjectForm.updatePPerson(pPerson));
    }

    @RequestMapping(value = {"/add"}, method = {org.springframework.web.bind.annotation.RequestMethod.POST})
    public void addPerson(@RequestBody SubjectForm subjectForm) throws Exception {
        logger.info("root - /subject/add");
        if (subjectForm.getSubjectClass() == SubjectClass.PRV) {
            if (pService.findByFIOType("", "", "", subjectForm.getPersonalNumber(), subjectForm.getSubjectType().getAnalytic_type()).size() != 0)
                throw new Exception("Субъект уже существует");
            PPerson pPerson = new PPerson();
            subjectForm.updatePPerson(pPerson);
            pPerson.subjectdataid = Integer.valueOf(scg.generate("SEQ_SUBJECTSDATA_ID.nextval").toString());
            pService.add(pPerson);
        }
       if(subjectForm.getSubjectClass() == SubjectClass.JUR ){
            JPerson jPerson = new JPerson();
            subjectForm.updateJPerson(jPerson);
            jPerson.subjectdataid = Integer.valueOf(scg.generate("SEQ_SUBJECTSDATA_ID.nextval").toString());
            jService.add(jPerson);
        }
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

        } catch (ServiceDaoException e) {
            e.printStackTrace();
        }
        return result_j;
    }

}
