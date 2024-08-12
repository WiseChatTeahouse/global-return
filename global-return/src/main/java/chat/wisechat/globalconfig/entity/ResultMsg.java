package chat.wisechat.globalconfig.entity;

import chat.wisechat.globalconfig.constants.ReturnCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @Author huqizhi
 * @Date 2024/8/11 12:30
 */
@Getter
@Setter
public class ResultMsg<T> {
    private int code;
    private String message;
    private T data;
    private long timestamp;
    private String traceId;

    public ResultMsg() {
        timestamp = System.currentTimeMillis();
        // TODO:还需要验证 写入链路的traceId
        traceId = UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static <T> ResultMsg<T> SUCCESS() {
        return SUCCESS(null);
    }

    public static <T> ResultMsg<T> SUCCESS(T data) {
        ResultMsg<T> resultMsg = new ResultMsg<>();
        resultMsg.setCode(ReturnCode.RC_OK.getCode());
        resultMsg.setMessage(ReturnCode.RC_OK.getMessage());
        resultMsg.setData(data);
        return resultMsg;
    }

    public static <T> ResultMsg<T> ERROR(String message) {
        return ERROR(ReturnCode.RC_INTERNAL_SERVER_ERROR.getCode(), message);
    }

    public static <T> ResultMsg<T> ERROR(ReturnCode returnCode) {
        return ERROR(returnCode.getCode(), returnCode.getMessage());
    }

    public static <T> ResultMsg<T> ERROR(int code, String message) {
        ResultMsg<T> resultMsg = new ResultMsg<>();
        resultMsg.setCode(code);
        resultMsg.setMessage(message);
        return resultMsg;
    }
}
