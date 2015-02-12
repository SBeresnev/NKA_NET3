package nla.local.services.impl.subjects;

import nla.local.pojos.JPerson;
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
public class JSubjectServiceImp extends SubjectServiceImp<JPerson> {

    private static Logger log = Logger.getLogger(JSubjectServiceImp.class);

    private DetachedCriteria query;

    @Autowired
    public JSubjectServiceImp(SessionFactory sessionFactory) {

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


    public List<JPerson> findByNameType(String fullName, String regNumber, Integer subjectType )
    {

        log.info("Get " + " by name. Invoked JSubjectServiceImp.getByNameType" );

        List<JPerson> retval = new ArrayList<JPerson>();

        if(fullName != null || regNumber != null) {

            regNumber = regNumber == null ? "":regNumber;
            fullName = fullName==null? "":fullName;

            query = DetachedCriteria.forClass(JPerson.class)
                    .add(Restrictions.or(Restrictions.like("regNumber", "%" + regNumber + "%", MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("regNumber")))
                    .add(Restrictions.or(Restrictions.like("fullname", "%" + fullName + "%",MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("fullname")));

            query = subjectType != null ? query.createCriteria("subjectType").add(Restrictions.eq("code_id", subjectType)):query;


            retval = (List<JPerson>) this.findSubject(query);

        }

        return retval;
    }


}
