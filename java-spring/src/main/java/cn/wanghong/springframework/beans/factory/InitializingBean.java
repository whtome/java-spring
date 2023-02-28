package cn.wanghong.springframework.beans.factory;

/**
 * @Author: wanghong
 * @date: 2023/2/28  11:25
 * @Version: 1.0
 * @Description:
 */
public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}
