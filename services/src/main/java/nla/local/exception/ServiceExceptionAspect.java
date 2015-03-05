package nla.local.exception;

/**
 * Created by beresnev on 04.03.2015.
 */

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ServiceExceptionAspect {

    //within(nla.local.services..*) && execution(public * nla.local.dao.BaseDao.*(..)) execution(* nla.local.services.impl.*.*(..))
    //@AfterThrowing(pointcut = "within(nla.local.services..*) && execution(public * nla.local.dao.BaseDao.*(..))", throwing = "ex" )

    @Around(" within(nla.local.services..*) && execution(* nla.local.dao.BaseDao.*(..))")
    public Object  doServiceException (ProceedingJoinPoint pjp) throws Throwable
    {
        try {

            System.out.println("LA-LA-LA.....LA-LA-LA..... LA-LA-LA");

            return pjp.proceed();

        } catch (HibernateException e) {

            throw new Exception(e);

        }

    }

}
