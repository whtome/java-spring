package cn.wanghong.springframework.beans.factory.config;

/**
 * @Author: wanghong
 * @date: 2023/2/16  14:56
 * @Version: 1.0
 * @Description:
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName);

    Object postProcessAfterInitialization(Object bean, String beanName);

}
