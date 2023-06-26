package cn.wanghong.springframework.test.event;

import cn.wanghong.springframework.context.ApplicationListener;
import cn.wanghong.springframework.context.event.ContextRefreshedEvent;

/**
 * @Author: wanghong
 * @date: 2023/6/15  17:41
 * @Version: 1.0
 * @Description:
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("开启事件：" + this.getClass().getName());
    }
}
