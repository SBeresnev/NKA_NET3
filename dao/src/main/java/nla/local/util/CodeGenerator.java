package nla.local.util;

import nla.local.pojos.orders.Decl;
import nla.local.pojos.orders.Declarant;
import nla.local.pojos.rights.Right;
import nla.local.pojos.rights.RightOwner;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Created by beresnev on 27.01.2015.
 */

@Component
@Transactional
public class CodeGenerator implements IdentifierGenerator, Configurable {

    private static Logger log = Logger.getLogger(CodeGenerator.class);

    private SessionFactory sessionFactory;

    private String seqName;


    public CodeGenerator()  {}

    @Autowired
    public CodeGenerator(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    private void fillObject(Object object, Long id)
    {

        if (object instanceof Decl) {

            ((Decl)object).fillDeclId(id);

        }


        if(object instanceof Declarant) {

            ((Declarant)object).setDeclarantId(id);

        }


        if(object instanceof Right) {


            for (RightOwner rOwn : ((Right)object).getRightOwners())
            {
                rOwn.setRight_id(id);
            }

        }

    }

    public Serializable generate(SessionImplementor session, Object object) throws HibernateException
    {

        String q_str ="SELECT "+seqName + ".NEXTVAL as nextval " + " from dual";

        Connection connection = session.connection();

        try {

            PreparedStatement ps = connection.prepareStatement(q_str);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Long id = rs.getLong("nextval");

                fillObject(object,id);

                return id;
            }

        } catch (SQLException e) {
        log.error(e);
        throw new HibernateException( "Unable to generate " + seqName + " Code Sequence");
      }

        return null;
    }

    public  Object generate(String param) throws HibernateException {

        String conn_str ="SELECT "+param + " from dual";

        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(conn_str);

        try {

            Object ret_val =  query.uniqueResult();

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

            query.executeUpdate();

            List<Object> ret_val = query.list();

            return ret_val;

        } catch (HibernateException e) {

            log.error(e);

            throw new HibernateException("Unable to object generate");
        }

    }

    public int update(String sql) throws HibernateException {

        String conn_str =sql;

        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(conn_str);

        try {

            return query.executeUpdate();

        } catch (HibernateException e) {

            log.error(e);

            throw new HibernateException("Unable to update table");
        }

    }


    @Override
    public void configure(Type type, Properties properties, Dialect dialect) throws MappingException {
        seqName = properties.getProperty("seq_name");
    }
}
