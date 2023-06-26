package cn.wanghong.springframework.context;

import java.util.EventObject;

/**
 * @Author: wanghong
 * @date: 2023/6/12  11:23
 * @Version: 1.0
 * @Description:
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
