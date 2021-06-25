package pojo.vo;

import common.enums.ResultCode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wtk
 * @description 接口结果
 * @date 2021-05-26
 */
public class Msg {
    /** 结果码 */
    private int code;

    /** 结果描述，对结果进行描述，如“用户密码错误” */
    private String message;

    /** 接口返回的结果 */
    private Map<String, Object> data = new HashMap<>(2);

    public static Msg success() {
        return new Msg(200, "success");
    }

    public static Msg success(String key, Object metadata) {
        Msg msg = new Msg(200, "success");
        msg.addData(key, metadata);
        return msg;
    }

    public static Msg userUnlogged() {
        return new Msg(ResultCode.UNAUTHORIZED, "用户未登录");
    }

    public static Msg exception() {
        return new Msg(500, "exception");
    }

    public static Msg exception(String message) {
        return new Msg(500, message);
    }

    public static Msg exception(Throwable e) {
        return new Msg(500, e.getMessage());
    }

    public static Msg exception(String key, Object metadata) {
        Msg msg = new Msg(500, "exception");
        msg.addData(key, metadata);
        return msg;
    }

    public static Msg userNotFound() {
        return new Msg(404, "账号不存在");
    }

    public static Msg musicNotFound() {
        return new Msg(404, "音乐不存在");
    }

    public static Msg notFound(String message) {
        return new Msg(404, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData(String key) {
        return data.get(key);
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void addData(String key, Object metadata) {
        data.put(key, metadata);
    }

    public Msg(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Msg() {
    }

    @Override
    public String toString() {
        return "ResultMsg{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
