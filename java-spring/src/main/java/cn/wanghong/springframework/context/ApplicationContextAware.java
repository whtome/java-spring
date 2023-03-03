package cn.wanghong.springframework.context;

import cn.wanghong.springframework.beans.BeansException;
import cn.wanghong.springframework.beans.factory.Aware;
import cn.wanghong.springframework.context.ApplicationContext;

/**
 * @Author: wanghong
 * @date: 2023/3/3  16:25
 * @Version: 1.0
 * @Description:
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
