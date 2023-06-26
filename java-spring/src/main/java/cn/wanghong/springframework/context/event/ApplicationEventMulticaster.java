package cn.wanghong.springframework.context.event;

import cn.wanghong.springframework.context.ApplicationEvent;
import cn.wanghong.springframework.context.ApplicationListener;

/**
 * @Author: wanghong
 * @date: 2023/6/12  11:21
 * @Version: 1.0
 * @Description: 事件广播器
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> lister);

    void multicastEvent(ApplicationEvent event);
}
