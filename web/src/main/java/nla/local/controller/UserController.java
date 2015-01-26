
package nla.local.controller;
/*
import nla.local.pojos.Person;
import forms.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private static List<Person> personList = new ArrayList();

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage(ModelMap model) {
        model.put("persons", personList);
        Person person = new Person();
        //person.setName("Yuli");
        model.put("person", person);
        return "user/main";
    }

    //@PreAuthorize("APP_ROLE")
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public String userInfo() {

        return "user/info";
    }

    @RequestMapping(value = "/add-person", method = {RequestMethod.POST})
    public String addPerson(ModelMap model, @Valid Person person, BindingResult bindingResult) {
        personList.add(person);
        if (!bindingResult.hasErrors()) {
            model.put("persons", personList);
            model.put("person", person);
        }

        return "user/main";
    }

    @RequestMapping(value = "/person-info", method = {RequestMethod.GET})
    public String infoPerson(ModelMap model, @RequestParam(value = "personId", defaultValue = "") Integer id) {

        model.put("person", personList.get(id));

        return "user/info";
    }

    @RequestMapping(value = "/update-person", method = {RequestMethod.POST})
    public String updatePerson(ModelMap model, @Valid Person person, BindingResult bindingResult,
                               @RequestParam(value = "id", defaultValue = "0") Integer id) {
        personList.remove(id);
        personList.add(person);
        if (!bindingResult.hasErrors()) {
            model.put("persons", personList);
            model.put("person", person);
        }

        return "user/main";
    }

    @RequestMapping(value = "/delete-person", method = RequestMethod.GET)
    public String deletePerson(ModelMap modelMap,@Valid UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

        } else {
            personList.remove(userForm.getPersonId());
        }

        modelMap.put("persons", personList);
        modelMap.put("person", new Person());

        return "user/main";
    }
}
*/