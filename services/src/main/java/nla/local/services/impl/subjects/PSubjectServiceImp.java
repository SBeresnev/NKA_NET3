package nla.local.services.impl.subjects;

import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.PPerson;
import nla.local.pojos.dict.SubjectTypeDict;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beresnev on 12.02.2015.
 */
@Service
@Transactional
public class PSubjectServiceImp extends SubjectServiceImp<PPerson> {

    private static Logger log = Logger.getLogger(PSubjectServiceImp.class);

    private DetachedCriteria query;

    @Autowired
    public PSubjectServiceImp(SessionFactory sessionFactory) {

        super(sessionFactory);

    }

    public DetachedCriteria getQuery()
    {
        return query;
    }

    public void setQuery(DetachedCriteria query)
    {
        super.setQuery(query);

        this.query = query;
    }

    public List<PPerson> getAll()
    {
        try {

            return super.getAll(PPerson.class);

        } catch (DaoException e) {

            e.printStackTrace();
        }

        return null;
    }

    public List<PPerson> findByFIOType(String surname, String firstname, String fathername, String personalNumber, Integer subjectType )
    {

        log.info("Get " + " by name. Invoked SubjectService.getByFIOType" );


        List<PPerson> retval =  new ArrayList<PPerson>();

        SubjectTypeDict std = new SubjectTypeDict();
        std.setCode_id(subjectType);
        std.setAnalytic_type(110);

        if(surname != null || personalNumber != null) {

            surname = surname == null ? "":surname;
            firstname = firstname==null? "":firstname;
            fathername = fathername == null ? "":fathername;
            personalNumber = personalNumber == null ? "":personalNumber;


            query = DetachedCriteria.forClass(PPerson.class)
                    .add(Restrictions.or(Restrictions.like("surname", surname, MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("surname")))
                    .add(Restrictions.or(Restrictions.like("firstname", firstname, MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("firstname")))
                    .add(Restrictions.or(Restrictions.like("fathername", fathername, MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("fathername")))
                    .add(Restrictions.or(Restrictions.like("personalNumber",  personalNumber , MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("personalNumber")))
                    ;

            query = subjectType != null ? query.createCriteria("subjectType").add(Restrictions.eq("code_id", subjectType)):query;


            retval = (List<PPerson>) this.findSubject(query);
        }

        return retval;

    }


}
