package cn.wanghong.springframework.test.event;

import cn.wanghong.springframework.context.ApplicationListener;
import cn.wanghong.springframework.context.event.ContextClosedEvent;

/**
 * @Author: wanghong
 * @date: 2023/6/15  17:42
 * @Version: 1.0
 * @Description:
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }
}
