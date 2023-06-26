package cn.wanghong.springframework.context.event;

import cn.wanghong.springframework.beans.factory.BeanFactory;
import cn.wanghong.springframework.context.ApplicationEvent;
import cn.wanghong.springframework.context.ApplicationListener;

/**
 * @Author: wanghong
 * @date: 2023/6/12  11:23
 * @Version: 1.0
 * @Description:
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
