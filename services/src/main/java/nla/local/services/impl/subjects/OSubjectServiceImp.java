package nla.local.services.impl.subjects;

import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.OPerson;
import nla.local.pojos.dict.EnumDict;
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
public class OSubjectServiceImp extends SubjectServiceImp<OPerson> {

    private static Logger log = Logger.getLogger(PSubjectServiceImp.class);

    private DetachedCriteria query;

    @Autowired
    public OSubjectServiceImp(SessionFactory sessionFactory) {

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

    public List<OPerson> getAll()
    {
        try {

            return super.getAll(OPerson.class);

        } catch (DaoException e) {

            e.printStackTrace();
        }

        return null;
    }

    public List<OPerson> findOffUser(String surname, String firstname, String fathername, Integer user_num, String orgname, Integer subjectType )
    {

        log.info("Get " + " by name. Invoked SubjectService.findOffUser" );

        List<OPerson> retval =  new ArrayList<OPerson>();

        if(surname != null || orgname != null) {

            surname = surname == null ? "":surname;
            firstname = firstname == null? "":firstname;
            fathername = fathername == null ? "":fathername;
            orgname = orgname == null ? "":orgname;

            query = DetachedCriteria.forClass(OPerson.class)
                    .add(Restrictions.or(Restrictions.like("surname", surname, MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("surname")))
                    .add(Restrictions.or(Restrictions.like("firstname", firstname, MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("firstname")))
                    .add(Restrictions.or(Restrictions.like("fathername", fathername, MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("fathername")))
                    .add(Restrictions.or(Restrictions.like("orgname", orgname, MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("orgname")));

            query = user_num != null ? query.add(Restrictions.eq("user_num", user_num)): query;
            query = subjectType != null ? query.createCriteria("subjectType").add(Restrictions.eq("code_id", subjectType)).add(Restrictions.eq("analytic_type", EnumDict.SubjectType.toInt())):query;


            retval = (List<OPerson>) this.findSubject(query);

        }

        return retval;

    }



}
