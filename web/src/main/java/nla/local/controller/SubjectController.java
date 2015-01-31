package nla.local.controller;

/**
 * Created by beresnev on 16.01.2015.
 */
import java.util.List;


import nla.local.pojos.JPerson;
import nla.local.pojos.PPerson;
import nla.local.services.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subject")
public class SubjectController {


    @Autowired
    ISubjectService<PPerson> pService;

    @Autowired
    ISubjectService<JPerson> jService;


    @RequestMapping(value = "/private", method = RequestMethod.GET )
    public List<PPerson> getPerson(@RequestParam(value="name", defaultValue="") String name,
                                   @RequestParam(value="number",defaultValue="") String personalnumber,
                                   @RequestParam(value="type",defaultValue="") String type )
    {

        List<PPerson> result= pService.findByFIOType("Б", "С", null,null,110);

        return result;
    }

    @RequestMapping(value = "/juridical", method = RequestMethod.GET)
    public List<JPerson> getJuridicalPerson(@RequestParam(value="name", defaultValue="") String name,
                                            @RequestParam(value="number",defaultValue="") String regnumber,
                                            @RequestParam(value="type",defaultValue="") String type)
    {

        List<JPerson> result= jService.findByNameType("Петр", "", 210);

        return result;
    }

}
