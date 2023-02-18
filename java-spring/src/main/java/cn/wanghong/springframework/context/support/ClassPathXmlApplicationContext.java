package cn.wanghong.springframework.context.support;

import cn.hutool.core.bean.BeanException;

/**
 * @Author: wanghong
 * @date: 2023/2/16  15:00
 * @Version: 1.0
 * @Description:
 */
public class ClassPathXmlApplicationContext  extends AbstractXmlApplicationContext{

    private String[] configlocations;

    public ClassPathXmlApplicationContext() {
    }

    public ClassPathXmlApplicationContext(String configlocations) throws BeanException {
        this(new String[]{configlocations});
    }

    public ClassPathXmlApplicationContext(String[] configlocations) throws BeanException {
        this.configlocations = configlocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configlocations;
    }
}
