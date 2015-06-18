package nla.local.util.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * Created by beresnev on 17.04.2015.
 */

public class CustomObjectMapper  extends ObjectMapper {

    public CustomObjectMapper() {

       /* this.enable(SerializationFeature.INDENT_OUTPUT);
        this.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);*/

        Hibernate4Module hibernate4Module = new Hibernate4Module();
        hibernate4Module.enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);

        this.registerModule(hibernate4Module);

    }


}

