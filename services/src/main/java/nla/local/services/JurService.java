package nla.local.services;


import nla.local.dao.JurSubjectsDao;
import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.JPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by beresnev on 23.01.2015.
 */
@Service
public class JurService extends BaseService<JPerson> {

    private JurSubjectsDao JurDao;

    @Autowired
    public JurService(JurSubjectsDao JurDao) {
        this.JurDao = JurDao;
    }

    public JurService() {}


    public List<JPerson> getByNameType(String fullname, String regNumber, String subjectType ) throws DaoException {

        List<JPerson> jprs = JurDao.getByNameType(fullname, regNumber, subjectType);

        return jprs;
    }


}
