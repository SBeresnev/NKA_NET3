package nla.local.controller;

import forms.SearchMvdForm;
import forms.SearchSubjectForm;
import forms.SubjectForm;
import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.dict.Dict;
import nla.local.pojos.dict.EnumDict;
import nla.local.pojos.subjects.JPerson;
import nla.local.pojos.subjects.PPerson;
import nla.local.pojos.subjects.RespNCA;
import nla.local.pojos.subjects.SubjectClass;
import nla.local.services.impl.subjects.JSubjectServiceImp;
import nla.local.services.impl.subjects.OSubjectServiceImp;
import nla.local.services.impl.subjects.PSubjectServiceImp;
import nla.local.services.impl.subjects.PassportServiceImp;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/subject"})
public class SubjectController
{

    @Autowired
    public OSubjectServiceImp oService;

    @Qualifier("PSubjectServiceImp")
    @Autowired
    public PSubjectServiceImp pService;

    @Autowired
    public PassportServiceImp passService;

    @Autowired
    public JSubjectServiceImp jService;

    @RequestMapping(value={"/private"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public List getPerson(SearchSubjectForm searchSubjectForm)
            throws DaoException
    {
        List result_p = new ArrayList();
        if (searchSubjectForm.getSubjectClass() == SubjectClass.PRV) {
            result_p.addAll(this.pService.findByFIOType(searchSubjectForm.surname, searchSubjectForm.firstname, searchSubjectForm.surname, searchSubjectForm.getNumber(), searchSubjectForm.getType()));
        }
        if (searchSubjectForm.getSubjectClass() == SubjectClass.JUR) {
            result_p.addAll(this.jService.findByNameType(searchSubjectForm.getName(), searchSubjectForm.getNumber(), searchSubjectForm.getType()));
        }
        return result_p;
    }

    @RequestMapping(value={"/update"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT})
    public void updatePerson(SubjectForm subjectForm)
            throws DaoException
    {
        if (subjectForm.getSubjectId() != null)
        {
            PPerson pPerson = this.pService.getSubject(subjectForm.getSubjectId());
            this.pService.refreshSubject(subjectForm.updatePPerson(pPerson));
        }
    }

    @RequestMapping(value={"/add"}, method={org.springframework.web.bind.annotation.RequestMethod.PUT})
    public void addPerson(SubjectForm subjectForm)
            throws DaoException
    {
        if (subjectForm.getSubjectId() != null)
        {
            PPerson pPerson = this.pService.getSubject(subjectForm.getSubjectId());
            this.pService.refreshSubject(subjectForm.updatePPerson(pPerson));
        }
    }

    @RequestMapping(value={"/mvd"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<PPerson> getMVDPerson(SearchMvdForm searchMvdForm)
            throws Exception
    {
        RespNCA resp = this.passService.findSubject(searchMvdForm.createOrGetPassportNCAObj());
        PPerson pp = this.passService.casttoPerson(resp);
        List<PPerson> list = new ArrayList();
        list.add(pp);
        return list;
    }

    @RequestMapping(value={"/juridical"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
    public List<JPerson> getJuridicalPerson(SubjectForm subjectForm)
    {
        List<JPerson> result_j = this.jService.getAll();
        JPerson jp = this.jService.getSubject(((JPerson)result_j.get(0)).getSubjectId());
        DetachedCriteria.forClass(Dict.class).add(Restrictions.eq("analytic_type", Integer.valueOf(EnumDict.SubjectType.toInt()))).add(Restrictions.eq("parent_code", Integer.valueOf(SubjectClass.toInt(SubjectClass.OFC))));


        result_j.clear();
        result_j = this.jService.findByNameType("Upd", null, subjectForm.getSubjectId());
        return result_j;
    }
}
