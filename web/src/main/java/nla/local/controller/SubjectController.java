package nla.local.controller;

/**
 * Created by beresnev on 16.01.2015.
 */

import forms.SearchSubjectForm;
import forms.SubjectForm;
import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.dict.Dict;
import nla.local.pojos.dict.EnumDict;
import nla.local.pojos.subjects.JPerson;
import nla.local.pojos.subjects.PPerson;
import nla.local.services.impl.DictionaryServiceImp;
import nla.local.services.impl.subjects.JSubjectServiceImp;
import nla.local.services.impl.subjects.OSubjectServiceImp;
import nla.local.services.impl.subjects.PSubjectServiceImp;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    public OSubjectServiceImp oService;

    @Autowired
    public PSubjectServiceImp pService;

    @Autowired
    public JSubjectServiceImp jService;

    @Autowired
    public DictionaryServiceImp commonDict;

    private List<Dict> subjectServDictList;

    @RequestMapping(value = "/private", method = RequestMethod.GET )
    public List getPerson(SearchSubjectForm searchSubjectForm) throws DaoException {
        List result_p  = new ArrayList();
        if(100 < searchSubjectForm.getType() && searchSubjectForm.getType() < 200){
           result_p.addAll(pService.findByFIOType(
                   searchSubjectForm.surname,
                   searchSubjectForm.firstname,
                   searchSubjectForm.surname,
                   searchSubjectForm.getNumber(),
                   searchSubjectForm.getType()
           ));
        }
        if(200 < searchSubjectForm.getType() && searchSubjectForm.getType() < 500){
            result_p.addAll(jService.findByNameType(searchSubjectForm.getName(), searchSubjectForm.getNumber(), searchSubjectForm.getType()));
        }
        return result_p;
    }

    @RequestMapping(value = "/subjectTypes", method = RequestMethod.GET )
    public List<Dict> getSubjectType() throws DaoException {

        List<Dict> ld = commonDict.getDict(EnumDict.SubjectType);

        return ld;
    }

    @RequestMapping(value = "/states", method = RequestMethod.GET )
    public List<Dict> getStates() throws DaoException {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Dict.class);


        return commonDict.getCriterion(detachedCriteria);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT )
    public void updatePerson(SubjectForm subjectForm) throws DaoException
    {
         if(subjectForm.getSubjectId() != null) {
           PPerson pPerson = (PPerson) pService.getSubject(subjectForm.getSubjectId());
            pService.refreshSubject(subjectForm.updatePPerson(pPerson));
       }
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT )
    public void addPerson(SubjectForm subjectForm) throws DaoException
    {
        if(subjectForm.getSubjectId() != null) {
            PPerson pPerson = (PPerson) pService.getSubject( subjectForm.getSubjectId());
            pService.refreshSubject(subjectForm.updatePPerson(pPerson));
        }
    }

    @RequestMapping(value = "/juridical", method = RequestMethod.GET)
    public List<JPerson> getJuridicalPerson(SubjectForm subjectForm)
    {

        List<JPerson> result_j = (List<JPerson>) jService.getAll();

        JPerson jp = jService.getSubject(result_j.get(0).getSubjectId());

        DetachedCriteria.forClass(Dict.class)
                .add(Restrictions.eq("analytic_type", EnumDict.SubjectType.toInt()))
                .add(Restrictions.eq("parent_code", 600));

        result_j.clear();

        result_j= jService.findByNameType("Upd", null, subjectForm.getSubjectId());

        return result_j;
    }

}
