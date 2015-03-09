package nla.local.pojos.dict;

/**
 * Created by beresnev on 17.02.2015.
 */


public enum EnumDict {

    OrgStruct(220),

    State(200),

    SubjectType(110),

    TorStruct(300),

    ResolutionType(55),

    UNKNOW(-1);


    private int value;

    EnumDict(int value) {

        this.value = value;
    }


    public  int toInt() {
        return value;
    }

    public static EnumDict fromInt(int value) {

        switch(value) {

            case 220: return OrgStruct;

            case 200: return State;

            case 110: return SubjectType;

            case 300: return TorStruct;

            case 55: return ResolutionType;

            default:
                return UNKNOW;
        }
    }

}
