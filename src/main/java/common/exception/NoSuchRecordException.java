package common.exception;

import common.enums.ExceptionType;

/**
 * @author wtk
 * @description
 * @date 2021-06-01
 */
public class NoSuchRecordException extends BadRequestException {
    public NoSuchRecordException() {
        super(ExceptionType.NOT_SUCH_RECORD, "没有记录");
    }

    public NoSuchRecordException(String message) {
        super(ExceptionType.NOT_SUCH_RECORD, message);
    }
}
