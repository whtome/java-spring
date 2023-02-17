package cn.wanghong.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.wanghong.springframework.beans.BeansException;
import cn.wanghong.springframework.beans.factory.PropertyValue;
import cn.wanghong.springframework.beans.factory.PropertyValues;
import cn.wanghong.springframework.beans.factory.config.AutowireCapableBeanFactory;
import cn.wanghong.springframework.beans.factory.config.BeanDefinition;
import cn.wanghong.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition beanDefinition, Object[] args) throws BeansException{
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, name, args);
            //属性填充
            applyPropertyValues(name, bean, beanDefinition);
            //执行Bean的初始化方法 和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(name, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        addSingleton(name, bean);
        return bean;
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition){
        //1. 执行BeanPostProcessor before处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        //待完成内容
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        //2. 执行 BeanPostProcessor After处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);

        return wrappedBean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor ctor : declaredConstructors) {
            if (null != args && args.length == ctor.getParameterTypes().length) {
                constructorUse = ctor;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructorUse, args);
    }

    private void invokeInitMethods(String beanName, Object wrappedBean,BeanDefinition beanDefinition) {

    }

    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }

                BeanUtil.setFieldValue(bean, name, value);
            }
        }catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        return null;
    }
}
