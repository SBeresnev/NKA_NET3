package nla.local.dao;

import nla.local.dao.exceptions.DaoErrorCode;
import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.JPerson;
import nla.local.pojos.PPerson;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by beresnev on 23.01.2015.
 */
@Repository
public class PhysSubjectsDao extends BaseDao<PPerson> {
    private static Logger log = Logger.getLogger(JurSubjectsDao.class);

    public PhysSubjectsDao(){}


    public List<PPerson> getByFIOType(String firstname, String surname ,String fathername, String personalNumber, String subjectType ) throws DaoException {

        log.info("PhysSubjectsDao.getByFIOType");

        DetachedCriteria query = DetachedCriteria.forClass(JPerson.class)
                .add(Restrictions.eq("subjectType", subjectType))
                .add(Restrictions.or(Restrictions.like("firstname", firstname), Restrictions.or(Restrictions.isNull("firstname"), Restrictions.isEmpty("firstname"))))
                .add(Restrictions.or(Restrictions.like("surname", surname), Restrictions.or(Restrictions.isNull("surname"), Restrictions.isEmpty("surname"))))
                .add(Restrictions.or(Restrictions.like("fathername", surname), Restrictions.or(Restrictions.isNull("fathername"), Restrictions.isEmpty("fathername"))))
                .add(Restrictions.or(Restrictions.like("personalNumber", surname), Restrictions.or(Restrictions.isNull("personalNumber"), Restrictions.isEmpty("personalNumber"))));

                  List<PPerson> jpersons = super.getCriterion(query);

                 return jpersons;


    }

}
