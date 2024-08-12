package chat.wisechat.globalconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author siberia.hu
 * @Package chat.wisechat
 * @Date 2024/8/11 14:16
 */
@ComponentScan("chat.wisechat.globalconfig")
@Configuration(proxyBeanMethods = false) // 防止代理 bean 方法，提高性能
@ConditionalOnClass({RestControllerAdvice.class}) // 确保 RestControllerAdvice 类存在
public class GlobalReturnAutoConfiguration {

}
