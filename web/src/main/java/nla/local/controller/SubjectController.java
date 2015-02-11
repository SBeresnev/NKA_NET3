package nla.local.controller;

/**
 * Created by beresnev on 16.01.2015.
 */

import forms.SearchSubjectForm;
import forms.SubjectForm;
import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.PPerson;
import nla.local.pojos.Person;
import nla.local.services.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    public ISubjectService<Person> sService;

    @RequestMapping(value = "/private", method = RequestMethod.GET )
    public List<Person> getPerson(SearchSubjectForm searchSubjectForm)
    {
        List<Person> result_p= sService.findByFIOType("", searchSubjectForm.getName(), null, searchSubjectForm.getNumber(), searchSubjectForm.getType());
        return result_p;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT )
    public void updatePerson(SubjectForm subjectForm) throws DaoException
    {
         if(subjectForm.getSubjectId() != null) {
           PPerson pPerson = (PPerson) sService.getSubject(PPerson.class, subjectForm.getSubjectId());
           sService.refreshSubject(subjectForm.updatePPerson(pPerson));
       }
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT )
    public void addPerson(SubjectForm subjectForm) throws DaoException
    {
        if(subjectForm.getSubjectId() != null) {
            PPerson pPerson = (PPerson) sService.getSubject(PPerson.class, subjectForm.getSubjectId());
            sService.refreshSubject(subjectForm.updatePPerson(pPerson));
        }
    }

    @RequestMapping(value = "/juridical", method = RequestMethod.GET)
    public List<Person> getJuridicalPerson(SearchSubjectForm searchSubjectForm)
    {
        List<Person> result_p= sService.findByFIOType("Ив", "И", null, null, 210);
        return result_p;
    }
}
