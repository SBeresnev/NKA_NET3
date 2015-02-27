package nla.local.pojos.subjects;

/**
 * Created by Serega on 22.02.2015.
 */


public enum SubjectClass {
    PRV("private"),
    JUR("juridical"),
    OFC("official");

    private String value;

    SubjectClass(String value) {

        this.value = value;
    }

    public String toString() {
        return value;
    }
};
