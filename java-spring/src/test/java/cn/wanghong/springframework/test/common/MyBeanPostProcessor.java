package cn.wanghong.springframework.test.common;

import cn.hutool.core.bean.BeanException;
import cn.wanghong.springframework.beans.BeansException;
import cn.wanghong.springframework.beans.factory.config.BeanPostProcessor;
import cn.wanghong.springframework.test.bean.UserService;

/**
 * @Author: wanghong
 * @date: 2023/2/16  15:01
 * @Version: 1.0
 * @Description:
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
