package nla.local.services.util;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beresnev on 28.01.2015.
 */

public class CriteriaServiceStorage<T> extends DetachedCriteria {

    private static Logger log = Logger.getLogger(CriteriaServiceStorage.class);

    private Class<T> type;

    private String typeName;

    private DetachedCriteria localdc;

    public CriteriaServiceStorage(Class<T> type) {

        super(null);

        localdc=DetachedCriteria.forClass(type);

        this.type = type;

        this.typeName = type.getSimpleName();

        log.debug(String.format("Created Dao for %s.", typeName));

    }

    public DetachedCriteria getByNameType(String fullName, String regNumber, Integer subjectType ){

        log.info("Get " + " by name. Invoked SubjectService.getByNameType()" );

        List retval = new ArrayList();

        if(fullName != null || regNumber != null) {

            regNumber = regNumber == null ? "":regNumber;
            fullName = fullName==null? "":fullName;

            localdc = localdc.add(Restrictions.eq("subjectType", subjectType))
                    .add(Restrictions.eq("actual", 1))
                    .add(Restrictions.or(Restrictions.like("regNumber", "%" + regNumber +"%"), Restrictions.isNull("regNumber")))
                    .add(Restrictions.or(Restrictions.like("fullname", "%" + fullName + "%"), Restrictions.isNull("fullname")));

        }

        return localdc;

    }

    public DetachedCriteria getByFIOType(String surname, String firstname  ,String fathername, String personalNumber, Integer subjectType ) {

        log.info("Get " + " by name. Invoked SubjectService.getByFIOType()" );

        List retval =  new ArrayList();

        if(surname != null || personalNumber != null) {

            surname = surname == null ? "":surname;
            firstname = firstname==null? "":firstname;
            fathername = fathername == null ? "":fathername;

            localdc = localdc
                    .add(Restrictions.eq("subjectType", subjectType))
                    .add(Restrictions.eq("actual", 1))
                    .add(Restrictions.or(Restrictions.like("surname", "%" + surname + "%"), Restrictions.isNull("surname")))
                    .add(Restrictions.or(Restrictions.like("firstname","%" + firstname + "%"), Restrictions.isNull("firstname")))
                    .add(Restrictions.or(Restrictions.like("fathername","%" + fathername + "%"), Restrictions.isNull("fathername")));

        }

        return localdc;

    }


}
