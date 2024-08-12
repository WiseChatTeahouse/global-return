package chat.wisechat.globalconfig.constants;

import lombok.Getter;

/**
 * @Author siberia.hu
 * @Package chat.wisechat.globalconfig.constants
 * @Date 2024/8/11 14:39
 */
@Getter
public enum ReturnCode {

    /**
     * 操作成功
     **/
    RC_OK(200, "请求成功"),

    /**
     * 无权访问
     **/
    RC_FORBIDDEN(403, "无访问权限,请联系管理员授予权限"),

    /**
     * 服务异常
     **/
    RC_INTERNAL_SERVER_ERROR(500, "系统异常，请稍后重试"),

    /**
     * 服务异常-网关超时
     **/
    RC_GATEWAY_TIMEOUT(504, "系统异常，网关超时");

    /**
     * 自定义状态码
     **/
    private final int code;

    /**
     * 自定义描述
     **/
    private final String message;

    ReturnCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
