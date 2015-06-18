package nla.local.dao.exceptions;

/**
 * Code for dao exceptions
 *
 */
public enum DaoErrorCode {

    NKANET_DAO_000("Cannot get %s by id=%s"),
    NKANET_DAO_001("Cannot get list of <%s>"),
    NKANET_DAO_002("Cannot create object %s"),
    NKANET_DAO_003("Cannot update object %s"),
    NKANET_DAO_004("Cannot delete object %s"),
    NKANET_DAO_005("Cannot create criteria for type %s"),
    NKANET_DAO_006("Cannot refresh object: %s"),
    NKANET_DAO_007("Cannot get Passport data %s by id=%s");

    private final String value;

    private DaoErrorCode(String s) {
	value = s;
    }

    public boolean equalsValue(String value2) {
	return (value2 != null) && value.equals(value2);
    }

    public String toString() {
	return value;
    }
}
