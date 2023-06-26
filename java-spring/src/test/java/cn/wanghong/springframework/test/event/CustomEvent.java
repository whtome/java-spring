package cn.wanghong.springframework.test.event;

import cn.wanghong.springframework.context.event.ApplicationContextEvent;
import com.sun.org.apache.xerces.internal.impl.XMLEntityScanner;

/**
 * @Author: wanghong
 * @date: 2023/6/15  17:36
 * @Version: 1.0
 * @Description:
 */
public class CustomEvent extends ApplicationContextEvent {

    private Long id;
    private String message;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CustomEvent(Object source, Long id, String message) {
        super(source);
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
