package nla.local.controller;

/**
 * Created by beresnev on 16.01.2015.
 */
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import nla.local.pojos.JPerson;
import nla.local.pojos.Person;
import nla.local.services.IService;
import nla.local.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {

    private static final String template = "Hello, %s!";

    @Qualifier("subjectService")
    @Autowired
    IService<Person> pService;


    @RequestMapping("/subject")
    public List<JPerson> greeting(@RequestParam(value="name", defaultValue="World") String name) {

        SubjectService<JPerson> ss = (SubjectService<JPerson>) pService;

        List<JPerson> result= ss.getByFIOType("Иванов", "Иван", "Иванович", null, 1 );

        return result;
    }
}
