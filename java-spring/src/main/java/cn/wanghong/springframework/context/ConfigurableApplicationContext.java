package cn.wanghong.springframework.context;

/**
 * @Author: wanghong
 * @date: 2023/2/16  14:58
 * @Version: 1.0
 * @Description:
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    void refresh();

}
