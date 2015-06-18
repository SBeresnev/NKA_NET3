package nla.local.exception;

/**
 * Created by beresnev on 04.03.2015.
 */

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component("serviceExceptionAspect")
public class ServiceExceptionLogAspect {

    @AfterThrowing(pointcut = "within(nla.local.services..*)", throwing = "ex" )
    public void  doServiceException (JoinPoint pjp, Throwable ex)
    {
        String inp_str = pjp.getSourceLocation().getWithinType().getCanonicalName() + "." + pjp.getStaticPart().getSignature().getName() + ".";

        AspectUtils.getLogger(pjp).error("Exception in " + inp_str);

       String str_ex =  "Exception :" + ex.getMessage() + (ex.getCause() != null ? " . Cased by:" +  ex.getCause().getMessage():"");

        AspectUtils.getLogger(pjp).error(str_ex);

    }


    @Around("execution(!@nla.local.exception.NO_LOG * nla.local.services..* (..))")
    public Object doServiceLog(ProceedingJoinPoint pjp) throws Throwable {

        Logger logger = AspectUtils.getLogger(pjp);

        Object[] paramValues = pjp.getArgs();

        String[] paramNames = ((CodeSignature) pjp.getStaticPart().getSignature()).getParameterNames();

        String inp_str = pjp.getSourceLocation().getWithinType().getCanonicalName()+ "." + pjp.getStaticPart().getSignature().getName();

        StringBuilder logLine = new StringBuilder(inp_str).append("(");

        if (paramNames.length != 0) AspectUtils.logParamValues(logLine, paramNames, paramValues);

        logLine.append(") - started");

        logger.info(logLine.toString());

        Object ret = pjp.proceed();

        if(ret != null) logger.info("return " +  ret.toString());

        logger.info("finished");

        return ret;
    }

}
