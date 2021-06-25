package common.exception;

import common.enums.ExceptionType;

/**
 * @author wtk
 * @description
 * @date 2021-05-28
 */
public class NotFoundException extends BadRequestException {
    public NotFoundException() {
        super(ExceptionType.USER_NOT_FOUND, "目标不存在");
    }

    public NotFoundException(String message) {
        super(ExceptionType.USER_NOT_FOUND, message);
    }
}
