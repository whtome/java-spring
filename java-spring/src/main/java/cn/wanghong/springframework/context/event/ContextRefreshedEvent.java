package cn.wanghong.springframework.context.event;

/**
 * @Author: wanghong
 * @date: 2023/6/12  11:22
 * @Version: 1.0
 * @Description:
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
