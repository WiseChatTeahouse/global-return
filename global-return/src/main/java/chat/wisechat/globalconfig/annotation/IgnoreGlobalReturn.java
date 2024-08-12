package chat.wisechat.globalconfig.annotation;

import java.lang.annotation.*;

/**
 * 注解用于在不需要包装时可不包响应结果（例如在暴露给第三方调用的接口上，有特别约定的格式可不包装响应处理）
 * <p>
 * 用法：直接在相关方法上打上该注解即可
 *
 * @Author siberia.hu
 * @Package chat.wisechat.annotation
 * @Date 2024/8/11 13:53
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreGlobalReturn {
}
