package cn.wanghong.springframework.test.bean;

import cn.hutool.core.bean.BeanException;
import cn.wanghong.springframework.beans.BeansException;
import cn.wanghong.springframework.beans.factory.BeanFactory;
import cn.wanghong.springframework.beans.factory.DisposableBean;
import cn.wanghong.springframework.beans.factory.InitializingBean;
import cn.wanghong.springframework.beans.factory.support.BeanClassLoaderAware;
import cn.wanghong.springframework.beans.factory.support.BeanFactoryAware;
import cn.wanghong.springframework.beans.factory.support.BeanNameAware;
import cn.wanghong.springframework.context.ApplicationContext;
import cn.wanghong.springframework.context.ApplicationContextAware;

public class UserService implements InitializingBean, DisposableBean, BeanFactoryAware, BeanNameAware, BeanClassLoaderAware, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    private String uid;

    private String company;

    private String location;

    private UserDao userDao;

//    public UserService(String uid, UserDao userDao) {
//        this.uid = uid;
//        this.userDao = userDao;
//    }

    public String queryUserInfo() {
        return userDao.queryUserName(uid)+", 公司："+company+", 地点"+location;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiseSet");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) throws BeanException {
        System.out.println("ClassLoader: " + classLoader);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean name: " + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
