package nla.local.controller;

/**
 * Created by beresnev on 16.01.2015.
 */
import java.util.concurrent.atomic.AtomicLong;

import nla.local.pojos.JPerson;
import nla.local.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    IService<JPerson> jIService;

    @RequestMapping("/subject")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {

        String result="Hello "+name;

        return result;
    }
}
