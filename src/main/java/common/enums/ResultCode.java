package common.enums;

/**
 * @author wtk
 * @description
 * @date 2021-05-26
 */
public class ResultCode {

    public static final int SUCCESS = 200;
    public static final int BAD_REQUEST = 400;
    /**
     * 请求要求用户的身份认证
     */
    public static final int UNAUTHORIZED = 401;
    public static final int NOT_FOUND = 404;
    /**
     * 客户端请求中的方法被禁止
     */
    public static final int METHOD_NOT_ALLOWED = 405;

    /**
     * 服务器在验证在请求的头字段中给出先决条件时，没能满足其中的一个或多个
     */
    public static final int PRECONDITION_FAILED = 412;

    /** 用户余额不足*/
    public static final int INSUFFICIENT_BALANCE = 1001;
    /** 错误的类型 */
    public static final int ERROR_TYPE = 1002;

    public static final int IS_ADMIN = 1101;
    /** 非管理员 */
    public static final int NOT_ADMIN = 1102;
    public static final int NO_SUCH_ADMIN = 1103;



}
