package cn.wanghong.springframework.beans.factory.support;

import cn.wanghong.springframework.beans.BeansException;
import cn.wanghong.springframework.beans.factory.DisposableBean;
import cn.wanghong.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object bean) {
        singletonObjects.put(beanName, bean);
    }

    public void registerDisposeableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposeableBeanNames = keySet.toArray();

        for (int i = disposeableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposeableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);

            }
        }

    }

}
