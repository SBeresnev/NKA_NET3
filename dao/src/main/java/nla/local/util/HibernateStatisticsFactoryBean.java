package nla.local.util;

/**
 * Created by beresnev on 27.10.2015.
 */
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("hibernateStatisticsFactoryBean")
public class HibernateStatisticsFactoryBean implements FactoryBean<Statistics> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Statistics getObject() throws Exception {
        return sessionFactory.getStatistics();
    }

    @Override
    public Class<?> getObjectType() {
        return Statistics.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}