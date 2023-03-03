package cn.wanghong.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import cn.wanghong.springframework.beans.factory.Aware;

/**
 * @Author: wanghong
 * @date: 2023/3/3  16:24
 * @Version: 1.0
 * @Description: 感知classLoader接口
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader) throws BeanException;
}
