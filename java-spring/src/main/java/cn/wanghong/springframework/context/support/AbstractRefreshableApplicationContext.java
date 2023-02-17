package cn.wanghong.springframework.context.support;

import cn.wanghong.springframework.beans.BeansException;
import cn.wanghong.springframework.beans.factory.ConfigurableListableBeanFactory;
import cn.wanghong.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @Author: wanghong
 * @date: 2023/2/16  14:59
 * @Version: 1.0
 * @Description:
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);
}
