package common.exception;

/**
 * @author wtk
 * @description
 * @date 2021-05-28
 */
public class UserhasLoggedException extends BadRequestException{

    public UserhasLoggedException() {
        super("UserHasLogged", "用户已登录");
    }
}
