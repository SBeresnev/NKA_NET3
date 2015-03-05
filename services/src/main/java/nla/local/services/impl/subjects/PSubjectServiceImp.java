package nla.local.services.impl.subjects;

import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.subjects.PPerson;
import nla.local.pojos.subjects.SubjectClass;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class PSubjectServiceImp extends SubjectServiceImp<PPerson> {

    private static Logger log = Logger.getLogger(PSubjectServiceImp.class);

    private DetachedCriteria query = DetachedCriteria.forClass(PPerson.class).add(Restrictions.eq("dtype", SubjectClass.PRV.toString()));


    @Autowired
    public PSubjectServiceImp(@Qualifier("sessionFactory") SessionFactory sessionFactory) {

        super(sessionFactory);

    }

    public DetachedCriteria getQuery()
    {
        return query;
    }

    public void setQuery(DetachedCriteria query)
    {

        this.query = (DetachedCriteria) SerializationUtils.clone(query);
    }

    @Override
    public List<PPerson> getAll(Class<PPerson> clazz)
    {
        return this.getAll();

    }

    @Override
    public PPerson getSubject( Serializable id)  {

        try {
            return super.get(PPerson.class,id);

        } catch (DaoException e) {

            e.printStackTrace();
        }

        return null;
    }

    public void add() throws DaoException {

        throw new DaoException(null,null);//Exception("Generic Error");

    }

    @Override
    public void add(PPerson t) throws DaoException
    {

        try {
            super.add(t);
        } catch (DaoException e) {
            e.printStackTrace();
        }


    }

    public List<PPerson> getAll()
    {
        try {


            return super.findSubjects(query);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public List<PPerson> findByFIOType(String surname, String firstname, String fathername, String personalNumber, Integer subjectType )
    {

        DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query);

        log.info("Get " + " by name. Invoked SubjectService.getByFIOType" );


        List<PPerson> retval =  new ArrayList<PPerson>();


        if(surname != null || personalNumber != null) {

            surname = surname == null ? "":surname;
            firstname = firstname==null? "":firstname;
            fathername = fathername == null ? "":fathername;
            personalNumber = personalNumber == null ? "":personalNumber;


            query_ = DetachedCriteria.forClass(PPerson.class)
                    .add(Restrictions.or(Restrictions.like("surname", surname, MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("surname")))
                    .add(Restrictions.or(Restrictions.like("firstname", firstname, MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("firstname")))
                    .add(Restrictions.or(Restrictions.like("fathername", fathername, MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("fathername")))
                    .add(Restrictions.or(Restrictions.like("personalNumber",  personalNumber , MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("personalNumber")))

            ;

            query_ = subjectType != null ? query_.createCriteria("subjectType").add(Restrictions.eq("code_id", subjectType)):query_;


            retval = (List<PPerson>) this.findSubjects(query_);
        }

        return retval;

    }


}
