package cn.wanghong.springframework.beans.factory.support;

import cn.wanghong.springframework.beans.BeansException;
import cn.wanghong.springframework.beans.factory.Aware;
import cn.wanghong.springframework.beans.factory.BeanFactory;

/**
 * @Author: wanghong
 * @date: 2023/3/3  16:23
 * @Version: 1.0
 * @Description:
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
