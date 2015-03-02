package nla.local.pojos.subjects;
/**
 * содержит справочную инфлормацию о типах субъектов. Числа соответствуют полю PARENT_CODE
 * из таблицы ANALYTICCODES, ANALYTIC_TYPE = 110
 * Created by Serega on 22.02.2015.
 */

public enum SubjectClass {
    PRV("private"),
    JUR("juridical"),
    OFC("official"),
    UKW("unknown");
    private String value;
    SubjectClass(String value) {
        this.value = value;
    }
    public String toString() {
        return value;
    }
    public static int toInt(SubjectClass value) {
        switch(value) {
            case JUR: return 200;
            case PRV: return 100;
            case OFC: return 600;
            default:
                return -1;
        }
    }
    public static SubjectClass fromInt(int value) {
        switch(value) {
            case 200: return JUR;
            case 100: return PRV;
            case 600: return OFC;
            default:
                return UKW;
        }
    }
}