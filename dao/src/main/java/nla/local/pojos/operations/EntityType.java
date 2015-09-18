package nla.local.pojos.operations;

/**
 * Created by beresnev on 15.09.2015.
 */
public enum  EntityType {

    RIGHT("RIGHT"),

    OBJECT("OBJECT"),

    BARGAIN("BARGAIN"),

    PERSON("PERSON"),

    ADDRESS("ADDRESS"),

    OPERATION("OPERATION"),

    UKW("unknown");

    private String value;

    EntityType(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

    public static int toInt(EntityType value) {

        switch(value) {

            case RIGHT     : return 1;
            case OBJECT    : return 2;
            case BARGAIN   : return 3;
            case PERSON    : return 4;
            case ADDRESS   : return 5;
            case OPERATION : return 6;

            default:
                return -1;
        }
    }
    public static EntityType fromInt(int value) {

        switch(value) {

            case 1 : return RIGHT;
            case 2 : return OBJECT ;
            case 3 : return BARGAIN;
            case 4 : return PERSON;
            case 5 : return ADDRESS;
            case 6 : return OPERATION;

            default:
                return UKW;
        }
    }

}
