package cn.wanghong.springframework.test.common;

import cn.wanghong.springframework.beans.BeansException;
import cn.wanghong.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.wanghong.springframework.beans.factory.PropertyValue;
import cn.wanghong.springframework.beans.factory.PropertyValues;
import cn.wanghong.springframework.beans.factory.config.BeanDefinition;
import cn.wanghong.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @Author: wanghong
 * @date: 2023/2/16  15:01
 * @Version: 1.0
 * @Description:
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }
}
