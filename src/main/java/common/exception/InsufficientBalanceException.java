package common.exception;

import common.enums.ExceptionType;

/**
 * @author wtk
 * @description
 * @date 2021-06-01
 */
public class InsufficientBalanceException extends BadRequestException {
    public InsufficientBalanceException() {
        super(ExceptionType.INSUFFICIENT_BALANCE, "用户余额不足");
    }

    public InsufficientBalanceException(String message) {
        super(ExceptionType.USER_NOT_FOUND, message);
    }
}
