package forms;

/**
 * Created by belonovich on 06.02.2015.
 */
public class SearchSubjectForm {
    private String name;
    private Integer type;
    private String number;

    public String surname = "";
    public String firstname = "";
    public String lastname = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
            String[] names = name.split(" ");
            try{
                this.firstname = names[0];
                this.surname = names[1];
                this.lastname = names[2];
            } catch (Exception e){}
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
