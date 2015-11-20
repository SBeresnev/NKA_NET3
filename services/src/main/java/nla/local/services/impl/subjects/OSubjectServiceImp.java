package nla.local.services.impl.subjects;

import nla.local.dao.exceptions.DaoErrorCode;
import nla.local.dao.exceptions.DaoException;
import nla.local.exception.ServiceDaoException;
import nla.local.pojos.subjects.OPerson;
import nla.local.pojos.subjects.SubjectClass;
import org.apache.commons.lang3.SerializationUtils;
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
public class OSubjectServiceImp extends SubjectServiceImp<OPerson> {

    private static Logger log = Logger.getLogger(OSubjectServiceImp.class);

    private DetachedCriteria query = DetachedCriteria.forClass(OPerson.class);


      public List<OPerson> getAll() throws ServiceDaoException
    {
        try {

            return super.getAll(OPerson.class);

        } catch (DaoException e) {

            throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_005, null);

        }
    }

    public List<OPerson> findOffUser(String surname, String firstname, String fathername, Integer user_num, String orgname, Integer subjectType ) throws ServiceDaoException {

        DetachedCriteria query_  = (DetachedCriteria) SerializationUtils.clone(query);

        //log.info("Get " + " by name. Invoked SubjectService.findOffUser" );

        List<OPerson> retval =  new ArrayList<OPerson>();

        if(surname != null || orgname != null) {

            surname = surname == null ? "":surname;
            firstname = firstname == null? "":firstname;
            fathername = fathername == null ? "":fathername;
            orgname = orgname == null ? "":orgname;

            query_ = query_
                    .add(Restrictions.or(Restrictions.like("surname", surname, MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("surname")))
                    .add(Restrictions.or(Restrictions.like("firstname", firstname, MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("firstname")))
                    .add(Restrictions.or(Restrictions.like("fathername", fathername, MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("fathername")))
                    .add(Restrictions.or(Restrictions.like("orgname", orgname, MatchMode.ANYWHERE).ignoreCase(), Restrictions.isNull("orgname")));

            query_ = user_num != null ? query_.add(Restrictions.eq("user_num", user_num)): query_;
            query_ = subjectType != null ? query_.add(Restrictions.eq("subjectType", subjectType)):query_;


            retval = (List<OPerson>) this.findSubjects(query_);

        }

        return retval;

    }

    @Override
    public OPerson getSubject( Serializable id) throws ServiceDaoException {

        try {

            return super.get(OPerson.class,id);

        } catch (DaoException e) {

            throw new ServiceDaoException(e, DaoErrorCode.NKANET_DAO_001, id);

        }

    }


}
