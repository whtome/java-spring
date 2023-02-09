package cn.wanghong.springframework.test.bean;

public class UserService {

    private String uid;

    private UserDao userDao;

//    public UserService(String uid, UserDao userDao) {
//        this.uid = uid;
//        this.userDao = userDao;
//    }

    public String queryUserInfo() {
        return userDao.queryUserName(uid);
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
}
