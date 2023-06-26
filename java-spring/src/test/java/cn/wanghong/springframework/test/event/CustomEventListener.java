package cn.wanghong.springframework.test.event;

import cn.wanghong.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @Author: wanghong
 * @date: 2023/6/15  17:40
 * @Version: 1.0
 * @Description:
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
