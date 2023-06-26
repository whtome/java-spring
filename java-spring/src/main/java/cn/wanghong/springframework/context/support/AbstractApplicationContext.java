package cn.wanghong.springframework.context.support;

import cn.wanghong.springframework.beans.BeansException;
import cn.wanghong.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.wanghong.springframework.beans.factory.config.BeanFactoryPostProcessor;
import cn.wanghong.springframework.beans.factory.config.BeanPostProcessor;
import cn.wanghong.springframework.context.ApplicationEvent;
import cn.wanghong.springframework.context.ApplicationListener;
import cn.wanghong.springframework.context.ConfigurableApplicationContext;
import cn.wanghong.springframework.context.event.ApplicationEventMulticaster;
import cn.wanghong.springframework.context.event.ContextClosedEvent;
import cn.wanghong.springframework.context.event.ContextRefreshedEvent;
import cn.wanghong.springframework.context.event.SimpleApplicationEventMulticaster;
import cn.wanghong.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * @Author: wanghong
 * @date: 2023/2/16  14:59
 * @Version: 1.0
 * @Description: 抽象应用上下文
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticatster;

    @Override
    public void refresh() {

        //1. 创建BeanFactory，并记载BeanFactory
        refreshBeanFactory();

        //2.获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //3. 添加ApplicationContextAwareProcessor，让继承ApplicationContextAware的Bean对象都能感知所属的 ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //4.在Bean实例化之前，执行BeanFactoryPostProcessor()
        invokeBeanFactoryPostProcessors(beanFactory);

        //5.BeanPostProcessor提前于其他Bean对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        //6. 初始化事件发布者
        initApplicationEventMulticaster();

        //7. 注册事件监听器
        registerListeners();

        //8.提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

        //9.发布容器刷新完成事件
        finishRefresh();
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        //发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));
        // 执行销毁单例bean的销毁方法
        getBeanFactory().destroySingletons();
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

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticatster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticatster);

    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticatster.addApplicationListener(listener);
        }
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticatster.multicastEvent(event);
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
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }
}
