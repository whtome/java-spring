package cn.wanghong.springframework.context;

import java.util.EventListener;

/**
 * @Author: wanghong
 * @date: 2023/6/12  11:24
 * @Version: 1.0
 * @Description:
 */
public interface ApplicationListener <E extends ApplicationEvent> extends EventListener {

    /**
     * Handle an application event.
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);
}
