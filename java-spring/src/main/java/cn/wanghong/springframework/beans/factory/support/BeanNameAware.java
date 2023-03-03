package cn.wanghong.springframework.beans.factory.support;

import cn.wanghong.springframework.beans.factory.Aware;

/**
 * @Author: wanghong
 * @date: 2023/3/3  16:24
 * @Version: 1.0
 * @Description:
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String name);
}
