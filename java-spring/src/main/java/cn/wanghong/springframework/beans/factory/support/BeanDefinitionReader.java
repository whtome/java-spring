package cn.wanghong.springframework.beans.factory.support;

import cn.wanghong.springframework.beans.BeansException;
import cn.wanghong.springframework.core.io.Resource;
import cn.wanghong.springframework.core.io.ResourceLoader;

/**
 * BeanDefinition 读取接口
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;

}
