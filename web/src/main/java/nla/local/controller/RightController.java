package nla.local.controller;

import nla.local.controller.forms.SearchSubjectForm;
import nla.local.controller.forms.SubjectForm;
import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.rights.Right;
import nla.local.pojos.rights.RightOwner;
import nla.local.pojos.subjects.Person;
import nla.local.pojos.subjects.SubjectClass;
import nla.local.services.IRightService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beresnev on 12.08.2015.
 */

@RestController
@RequestMapping({"/right"})
public class RightController {

    private static final Logger logger = Logger.getLogger(RightController.class);

    @Autowired
    private IRightService irs;

    @RequestMapping(value = {"/add"}, method = {org.springframework.web.bind.annotation.RequestMethod.GET})
    public void passSingleRight(@RequestBody RightOwner rght_own) throws Exception {

        irs.passSingleRight(rght_own);

    }

    public void passSharedRight(@RequestBody RightOwner rght_own) throws Exception {

        irs.passSharedRight();

    }





}
