package cn.wanghong.springframework.test.bean;

import cn.wanghong.springframework.beans.factory.DisposableBean;
import cn.wanghong.springframework.beans.factory.InitializingBean;

public class UserService implements InitializingBean, DisposableBean {

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
}
