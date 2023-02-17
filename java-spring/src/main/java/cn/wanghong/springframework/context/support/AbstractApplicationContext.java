package cn.wanghong.springframework.context.support;

import cn.wanghong.springframework.beans.BeansException;
import cn.wanghong.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.wanghong.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.wanghong.springframework.beans.factory.config.BeanPostProcessor;
import cn.wanghong.springframework.context.ConfigurableApplicationContext;
import cn.wanghong.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @Author: wanghong
 * @date: 2023/2/16  14:59
 * @Version: 1.0
 * @Description:
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    @Override
    public void refresh() {

        //1. 创建BeanFactory，并记载BeanFactory
        refreshBeanFactory();

        //2.获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //3.在Bean实例化之前，执行BeanFactoryPostProcessor()
        invokeBeanFactoryPostProcessors(beanFactory);

        //4.BeanPostProcessor提前于其他Bean对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        //5.提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();
    }

    protected abstract  void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return null;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }
}
