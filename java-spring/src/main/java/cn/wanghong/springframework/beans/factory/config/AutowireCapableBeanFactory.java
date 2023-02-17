package cn.wanghong.springframework.beans.factory.config;

import cn.wanghong.springframework.beans.BeansException;
import cn.wanghong.springframework.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;

}
