package nla.local.util;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by beresnev on 27.01.2015.
 */

@Component
public class CodeGenerator  {


    private SessionFactory sessionFactory;

    private static Logger log = Logger.getLogger(CodeGenerator.class);


    @Autowired
    public CodeGenerator(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    public  Serializable generate(String hql) throws HibernateException {

        String conn_str ="SELECT "+hql + ".nextval from dual";

        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(conn_str);

        try {

            Integer ret_val =  ((BigDecimal)query.uniqueResult()).intValue();

                return ret_val;

        } catch (HibernateException e) {

            log.error(e);

            throw new HibernateException("Unable to generate Code Sequence");
        }

    }

    public List<Object> generate_obj(String sql) throws HibernateException {

        String conn_str =sql;

        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(conn_str);

        try {

            List<Object> ret_val = query.list();

            return ret_val;

        } catch (HibernateException e) {

            log.error(e);

            throw new HibernateException("Unable to object generate");
        }

    }
}
