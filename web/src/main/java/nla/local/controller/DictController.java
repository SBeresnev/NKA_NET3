package nla.local.controller;

import nla.local.dao.exceptions.DaoException;
import nla.local.pojos.dict.Dict;
import nla.local.pojos.dict.EnumDict;
import nla.local.services.impl.DictionaryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by belonovich on 25.02.2015.
 */

@RestController
@RequestMapping("/dict")
public class DictController {

    @Autowired
    public DictionaryServiceImp commonDict;


    @RequestMapping(value = "/states", method = RequestMethod.GET )
    public List<Dict> getStates() throws DaoException {
        return commonDict.getDict(EnumDict.State);
    }

    @RequestMapping(value = "/subjectTypes", method = RequestMethod.GET )
    public List<Dict> getSubjectType() throws DaoException {
        return commonDict.getDict(EnumDict.SubjectType);
    }

    @RequestMapping(value = "/OrgStruct", method = RequestMethod.GET )
    public List<Dict> getOrgStruct() throws DaoException {
        return commonDict.getDict(EnumDict.OrgStruct);
    }

    @RequestMapping(value = "/TorStruct", method = RequestMethod.GET )
    public List<Dict> getTorStruct() throws DaoException {
        return commonDict.getDict(EnumDict.TorStruct);
    }
}
