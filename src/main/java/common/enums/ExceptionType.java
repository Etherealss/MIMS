package common.enums;

/**
 * @author wtk
 * @description
 * @date 2021-05-26
 */
public class ExceptionType {

    public static final String USER_NOT_FOUND = "UserNotFound";

    public static final String WRONG_PASSWORD = "WrongPassword";

    public static final String MUSIC_EXISTED = "MusicExisted";

    public static final String USER_EXISTED = "UserExisted";

    /** 余额不足 */
    public static final String INSUFFICIENT_BALANCE = "InsufficientBalance";

    public static final String NOT_SUCH_RECORD = "NoySuchRecord";

    /** 错误的类型，可以是多种类型，视具体场景而定 */
    public static final String ERROR_TYPE = "ErrorType";

    /** 错误的参数，可以是多种参数的错误，如数值小于0等 */
    public static final String ERROR_PARAM = "ErrorParam";

    public static final String IS_ADMIN = "IsAdmin";
    public static final String NOT_ADMIN = "NotAdmin";
    public static final String NO_SUCH_ADMIN_TYPE = "NoSuchAdminType";

}
