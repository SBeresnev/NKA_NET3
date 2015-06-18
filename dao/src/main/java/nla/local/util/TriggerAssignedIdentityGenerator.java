package nla.local.util;

import org.hibernate.HibernateException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.AbstractPostInsertGenerator;
import org.hibernate.id.IdentifierGeneratorHelper;
import org.hibernate.id.PostInsertIdentityPersister;
import org.hibernate.id.SequenceIdentityGenerator;
import org.hibernate.id.insert.AbstractReturningDelegate;
import org.hibernate.id.insert.IdentifierGeneratingInsert;
import org.hibernate.id.insert.InsertGeneratedIdentifierDelegate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by beresnev on 18.06.2015.
 */
public class TriggerAssignedIdentityGenerator extends AbstractPostInsertGenerator {

    @Override
    public InsertGeneratedIdentifierDelegate getInsertGeneratedIdentifierDelegate(PostInsertIdentityPersister persister, Dialect dialect, boolean isGetGeneratedKeysEnabled) throws HibernateException {
        return new Delegate(persister, dialect);
    }

    public static class Delegate extends AbstractReturningDelegate {

        private Dialect dialect;
        private String[] keyColumns;

        public Delegate(PostInsertIdentityPersister persister, Dialect dialect) {
            super(persister);
            this.dialect = dialect;
            this.keyColumns = getPersister().getRootTableKeyColumnNames();
            if (keyColumns.length > 1) {
                throw new HibernateException("trigger assigned identity generator cannot be used with multi-column keys");
            }
        }

        @Override
        public IdentifierGeneratingInsert prepareIdentifierGeneratingInsert() {
            return new SequenceIdentityGenerator.NoCommentsInsert(dialect);
        }

        @Override
        protected PreparedStatement prepare(String insertSQL, SessionImplementor session) throws SQLException {
            return session.connection().prepareStatement(insertSQL, keyColumns);
        }

        @Override
        protected Serializable executeAndExtract(PreparedStatement insert, SessionImplementor session) throws SQLException {
            insert.executeUpdate();
            ResultSet generatedKeys = insert.getGeneratedKeys();
            return IdentifierGeneratorHelper.getGeneratedIdentity(generatedKeys, keyColumns[0], getPersister().getIdentifierType());
          }

    }
}
