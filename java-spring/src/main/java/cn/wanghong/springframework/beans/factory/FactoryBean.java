package cn.wanghong.springframework.beans.factory;

/**
 * @Author: wanghong
 * @date: 2023/5/31  15:23
 * @Version: 1.0
 * @Description:
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<T> getObjectType();

    boolean isSingleton();
}
