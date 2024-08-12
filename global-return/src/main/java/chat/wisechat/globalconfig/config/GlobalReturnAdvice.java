package chat.wisechat.globalconfig.config;

import chat.wisechat.globalconfig.annotation.IgnoreGlobalReturn;
import chat.wisechat.globalconfig.entity.ResultMsg;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 全局返回结果包装器
 * <p>
 * 该类用于对控制器方法的返回结果进行统一包装，以便将所有的返回结果都转换为自定义的ResultMsg格式。
 *
 * @Author siberia.hu
 * @Package chat.wisechat.config
 * @Date 2024/8/11 13:03
 */
@RestControllerAdvice
public class GlobalReturnAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否支持当前方法的返回值包装。
     *
     * @param returnType    方法参数信息
     * @param converterType 消息转换器类型
     * @return 如果支持返回true，否则返回false
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        Type genericReturnType = returnType.getGenericParameterType();

        if (genericReturnType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericReturnType;
            Type rawType = parameterizedType.getRawType();
            // 判断返回类型是否为ResultMsg的子类
            if (rawType.equals(ResultMsg.class)) {
                // 已经是包装类型，不包装
                return false;
            }
        }
        // 如果是controller就都统一拦截加对象
        return returnType.getDeclaringClass().isAnnotationPresent(RestController.class);
    }

    /**
     * 在将返回值写入HTTP响应体之前进行处理。
     *
     * @param body                  返回值主体
     * @param returnType            方法参数信息
     * @param selectedContentType   选定的内容类型
     * @param selectedConverterType 选定的消息转换器类型
     * @param request               HTTP请求
     * @param response              HTTP响应
     * @return 处理后的返回值
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        Method method = returnType.getMethod();
        if (null == method) {
            // 方法为空的也不包装
            return body;
        }
        boolean annotationPresent = method.isAnnotationPresent(IgnoreGlobalReturn.class);
        if (annotationPresent) {
            // 排除的不包装
            return body;
        }
        // 来到这里就需要包装一下
        if (ObjectUtils.isEmpty(body)) {
            return ResultMsg.SUCCESS();
        }
        return ResultMsg.SUCCESS(body);
    }
}
