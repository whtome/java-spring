package cn.wanghong.springframework.context;

import cn.wanghong.springframework.beans.factory.HierarchicalBeanFactory;
import cn.wanghong.springframework.beans.factory.ListableBeanFactory;
import cn.wanghong.springframework.core.io.ResourceLoader;

/**
 * @Author: wanghong
 * @date: 2023/2/16  14:58
 * @Version: 1.0
 * @Description: 应用上下文接口
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
