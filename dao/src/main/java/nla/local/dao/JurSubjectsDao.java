package nla.local.dao;

import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.JPerson;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by beresnev on 23.01.2015.
 */
@Repository
public class JurSubjectsDao extends BaseDao<JPerson> {

    private static Logger log = Logger.getLogger(JurSubjectsDao.class);

    public JurSubjectsDao(){}

    public List<JPerson> getByNameType(String fullname, String regNumber, String subjectType ) throws DaoException {
        log.info("JurSubjectsDao.getByNameType");

        DetachedCriteria query = DetachedCriteria.forClass(JPerson.class)
                .add(Restrictions.eq("subjectType", subjectType))
                .add(Restrictions.or(Restrictions.like("fullname", fullname), Restrictions.or(Restrictions.isNull("fullname"), Restrictions.isEmpty("fullname"))))
                .add(Restrictions.or(Restrictions.like("regNumber", regNumber), Restrictions.or(Restrictions.isNull("regNumber"), Restrictions.isEmpty("regNumber"))));

         List<JPerson> jpersons = super.getCriterion(query);

        return jpersons;
    }

}
