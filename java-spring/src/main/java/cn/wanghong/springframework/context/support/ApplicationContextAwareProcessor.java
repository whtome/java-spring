package cn.wanghong.springframework.context.support;

import cn.hutool.core.bean.BeanException;
import cn.wanghong.springframework.beans.BeansException;
import cn.wanghong.springframework.beans.factory.config.BeanPostProcessor;
import cn.wanghong.springframework.context.ApplicationContext;
import cn.wanghong.springframework.context.ApplicationContextAware;

/**
 * @Author: wanghong
 * @date: 2023/3/3  16:39
 * @Version: 1.0
 * @Description: ApplicationContextAware包装处理器
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
