package nla.local.services.impl.subjects;

import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.JPerson;
import nla.local.pojos.dict.EnumDict;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by beresnev on 12.02.2015.
 */
@Service
@Transactional
public class JSubjectServiceImp extends SubjectServiceImp<JPerson> {

    private static Logger log = Logger.getLogger(JSubjectServiceImp.class);

    private DetachedCriteria query = DetachedCriteria.forClass(JPerson.class).add(Restrictions.eq("parent_desc","juridical"));

    @Autowired
    public JSubjectServiceImp(SessionFactory sessionFactory) {

        super(sessionFactory);



    }

    public List<JPerson> getAll()
    {

        try {

            return super.findSubjects(query);
            //super.getAll(JPerson.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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

        DetachedCriteria query_ = query;

        log.info("Get " + " by name. Invoked JSubjectServiceImp.getByNameType" );

        List<JPerson> retval = new ArrayList<JPerson>();

        if(fullName != null || regNumber != null) {

            regNumber = regNumber == null ? "":regNumber;
            fullName = fullName==null? "":fullName;

             query_ = query_
                    .add(Restrictions.or(Restrictions.like("regNumber", "%" + regNumber + "%", MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("regNumber")))
                    .add(Restrictions.or(Restrictions.like("fullname", "%" + fullName + "%",MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("fullname")));


            query_ = subjectType != null ? query_.createCriteria("subjectType").add(Restrictions.eq("code_id", subjectType)).add(Restrictions.eq("analytic_type", EnumDict.SubjectType.toInt())):query_;


            retval = (List<JPerson>) this.findSubjects(query_);

        }

        return retval;
    }


    @Override
    public JPerson getSubject(Serializable id) {

        try {
            return super.get(JPerson.class,id);

        } catch (DaoException e) {

            e.printStackTrace();
        }
        return null;
    }
}
