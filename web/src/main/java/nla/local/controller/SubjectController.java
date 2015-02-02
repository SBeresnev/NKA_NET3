package nla.local.controller;

/**
 * Created by beresnev on 16.01.2015.
 */

import nla.local.pojos.Person;
import nla.local.services.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {


    @Autowired
    ISubjectService<Person> sService;


    @RequestMapping(value = "/private/", method = RequestMethod.GET )
    public List<Person> getPerson(@RequestParam(value = "name", defaultValue = "") String name,
                                  @RequestParam(value = "number", defaultValue = "") String personalnumber,
                                  @RequestParam(value = "type", defaultValue = "") String type)
    {

        //List<PPerson> result= pService.findByFIOType("Б", "С", null,null,110);
        List<Person> result_p= sService.findByFIOType("Ив", "И", null, null, 100);
        return result_p;
    }

    @RequestMapping(value = "/juridical", method = RequestMethod.GET)
    public List<Person> getJuridicalPerson(@RequestParam(value="name", defaultValue="") String name,
                                            @RequestParam(value="number",defaultValue="") String regnumber,
                                            @RequestParam(value="type",defaultValue="") String type)
    {

        List<Person> result_p= sService.findByFIOType("Ив", "И", null, null, 100);
        return result_p;
    }

}
