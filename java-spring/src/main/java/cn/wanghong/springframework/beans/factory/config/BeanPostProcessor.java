package cn.wanghong.springframework.beans.factory.config;

import cn.hutool.core.bean.BeanException;
import cn.wanghong.springframework.beans.BeansException;

/**
 * @Author: wanghong
 * @date: 2023/2/16  14:56
 * @Version: 1.0
 * @Description:
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException;

    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}
