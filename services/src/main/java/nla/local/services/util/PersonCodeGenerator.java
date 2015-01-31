package nla.local.services.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.apache.log4j.Logger;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.util.StringUtils;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by beresnev on 27.01.2015.
 */

public class PersonCodeGenerator implements IdentifierGenerator {

    private static Logger log = Logger.getLogger(PersonCodeGenerator.class);

    @Override
    public Serializable generate(SessionImplementor session, Object object)
            throws HibernateException {

        String prefix = "M";
        Connection connection = session.connection();
        try {

            PreparedStatement ps = connection.prepareStatement("SELECT nextval ('SEQ_SUBJECTSDATA_ID') as nextval");

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Integer id = rs.getInt("nextval");
                // String code = prefix + StringUtils.leftPad("" + id, 3, '0');
                //log.debug("Generated Stock Code: " + code);
                return id;
            }

        } catch (SQLException e) {
            log.error(e);
            throw new HibernateException(
                    "Unable to generate Stock Code Sequence");
        }
        return null;
    }
}

