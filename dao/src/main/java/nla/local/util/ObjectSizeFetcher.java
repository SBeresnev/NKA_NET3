package nla.local.util;

/**
 * Created by beresnev on 29.05.2015.
 */
import org.springframework.stereotype.Component;

import java.lang.instrument.Instrumentation;

@Component
public class ObjectSizeFetcher {
    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }
}