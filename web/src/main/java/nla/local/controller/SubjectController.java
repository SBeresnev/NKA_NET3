package nla.local.controller;

/**
 * Created by beresnev on 16.01.2015.
 */

import forms.SearchSubjectForm;
import forms.SubjectForm;
import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.JPerson;
import nla.local.pojos.PPerson;
import nla.local.pojos.dict.StateDict;
import nla.local.pojos.dict.SubjectTypeDict;
import nla.local.services.impl.DictionaryServiceImp;
import nla.local.services.impl.subjects.JSubjectServiceImp;
import nla.local.services.impl.subjects.OSubjectServiceImp;
import nla.local.services.impl.subjects.PSubjectServiceImp;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    private List<SubjectTypeDict> subjectServDictList;

    @RequestMapping(value = "/private", method = RequestMethod.GET )
    public List<PPerson> getPerson(SearchSubjectForm searchSubjectForm) throws DaoException {

        //subjectServDictList = commonDict.getAll(SubjectTypeDict.class);

        DetachedCriteria dc = DetachedCriteria.forClass(StateDict.class).add(Restrictions.or(Restrictions.like("code_short_name", "Беларусь", MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("code_short_name")));

        subjectServDictList = commonDict.getCriterion(dc);

        List<PPerson> result_p= pService.findByFIOType("", searchSubjectForm.getName(), null, searchSubjectForm.getNumber(), subjectServDictList.get(2).getCode_id());

        return result_p;

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT )
    public void updatePerson(SubjectForm subjectForm) throws DaoException
    {
         if(subjectForm.getSubjectId() != null) {
           PPerson pPerson = (PPerson) pService.getSubject(PPerson.class, subjectForm.getSubjectId());
             pService.refreshSubject(subjectForm.updatePPerson(pPerson));
       }
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT )
    public void addPerson(SubjectForm subjectForm) throws DaoException
    {
        if(subjectForm.getSubjectId() != null) {
            PPerson pPerson = (PPerson) pService.getSubject(PPerson.class, subjectForm.getSubjectId());
            pService.refreshSubject(subjectForm.updatePPerson(pPerson));
        }
    }

    @RequestMapping(value = "/juridical", method = RequestMethod.GET)
    public List<JPerson> getJuridicalPerson(SubjectForm subjectForm)
    {
        List<JPerson> result_j= jService.findByNameType("Upd", null, subjectForm.getSubjectId());

        return result_j;
    }
}
