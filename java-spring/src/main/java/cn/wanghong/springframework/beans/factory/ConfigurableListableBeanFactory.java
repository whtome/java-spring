package cn.wanghong.springframework.beans.factory;

import cn.wanghong.springframework.beans.BeansException;
import cn.wanghong.springframework.beans.factory.config.AutowireCapableBeanFactory;
import cn.wanghong.springframework.beans.factory.config.BeanDefinition;
import cn.wanghong.springframework.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
