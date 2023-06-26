package cn.wanghong.springframework.context;

/**
 * @Author: wanghong
 * @date: 2023/6/12  11:24
 * @Version: 1.0
 * @Description:
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);
}
