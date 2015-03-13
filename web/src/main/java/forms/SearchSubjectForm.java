package forms;

import nla.local.pojos.subjects.SubjectClass;

/**
 * Created by belonovich on 06.02.2015.
 */
public class SearchSubjectForm {
    private String name;
    private Integer type;
    private String number;
    private SubjectClass subjectClass;
    public String surname = "";
    public String firstname = "";
    public String lastname = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String[] names = name.split(" ");
        try {
            this.firstname = names[0];
            this.surname = names[1];
            this.lastname = names[2];
        } catch (Exception e) {
        }
        this.name = name;
    }

    public SubjectClass getSubjectClass() {
        return subjectClass;
    }

    public void setSubjectClass(SubjectClass subjectClass) {
        this.subjectClass = subjectClass;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        if (type < SubjectClass.toInt(SubjectClass.PRV) ) setSubjectClass(SubjectClass.PRV);
        else {
            if (type < SubjectClass.toInt(SubjectClass.JUR)) setSubjectClass(SubjectClass.JUR);
            else setSubjectClass(SubjectClass.OFC);
        }
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
