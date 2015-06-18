package nla.local.services.impl.subjects;

import nla.local.dao.exceptions.DaoErrorCode;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
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

    private DetachedCriteria query = DetachedCriteria.forClass(PPerson.class);


    public DetachedCriteria getQuery()
    {
        return query;
    }

    public void setQuery(DetachedCriteria query)
    {

        this.query = (DetachedCriteria) SerializationUtils.clone(query);
    }

    public List<PPerson> getAll() throws ServiceDaoException {

        return super.findSubjects(query);

    }

    @Override
    public PPerson getSubject( Serializable id) throws ServiceDaoException {

        try {
            return super.get(PPerson.class, id);

        } catch (DaoException e) {

            throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_001, id);
        }
    }


    public List<PPerson> findByFIOType(String surname, String firstname, String fathername, String personalNumber, Integer subjectType ) throws ServiceDaoException {

        DetachedCriteria query_ = (DetachedCriteria) SerializationUtils.clone(query);

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
                    .add(Restrictions.or(Restrictions.like("personalNumber",  personalNumber , MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("personalNumber"))) ;

            query_ = subjectType != null ? query_.createCriteria("subjectType").add(Restrictions.eq("code_id", subjectType)):query_.add(Restrictions.eq("dtype", SubjectClass.PRV.toString()));


            retval = (List<PPerson>) this.findSubjects(query_);
        }

        return retval;

    }


}
