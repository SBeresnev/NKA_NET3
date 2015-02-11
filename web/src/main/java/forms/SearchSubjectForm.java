package forms;

/**
 * Created by belonovich on 06.02.2015.
 */
public class SearchSubjectForm {
private String name;
    private Integer type;
    private String number;

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
            this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
