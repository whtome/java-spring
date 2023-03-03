package cn.wanghong.springframework.beans.factory;

/**
 * @Author: wanghong
 * @date: 2023/2/28  11:25
 * @Version: 1.0
 * @Description: bean销毁方法接口
 */
public interface DisposableBean {

    void destroy() throws Exception;

}
