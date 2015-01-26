package forms;

import javax.validation.Valid;

/**
 * Created with IntelliJ IDEA.
 * User: User
 * Date: 31.07.14
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */
public class UserForm {
    @Valid
    private Integer personId;

    private String name;
    private Integer age;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
