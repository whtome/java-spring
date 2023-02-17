package cn.wanghong.springframework.context.support;

import cn.wanghong.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.wanghong.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @Author: wanghong
 * @date: 2023/2/16  14:59
 * @Version: 1.0
 * @Description:
 */
public  abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{


    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if (null != configLocations) {
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
