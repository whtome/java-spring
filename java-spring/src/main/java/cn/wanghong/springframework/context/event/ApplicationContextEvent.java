package cn.wanghong.springframework.context.event;

import cn.wanghong.springframework.context.ApplicationContext;
import cn.wanghong.springframework.context.ApplicationEvent;

/**
 * @Author: wanghong
 * @date: 2023/6/12  11:20
 * @Version: 1.0
 * @Description:
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
