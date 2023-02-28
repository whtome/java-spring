package cn.wanghong.springframework.test;


import cn.hutool.core.io.IoUtil;
import cn.wanghong.springframework.beans.factory.PropertyValue;
import cn.wanghong.springframework.beans.factory.PropertyValues;
import cn.wanghong.springframework.beans.factory.config.BeanDefinition;
import cn.wanghong.springframework.beans.factory.config.BeanReference;
import cn.wanghong.springframework.beans.factory.support.DefaultListableBeanFactory;
import cn.wanghong.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import cn.wanghong.springframework.context.support.ClassPathXmlApplicationContext;
import cn.wanghong.springframework.core.io.DefaultResourceLoader;
import cn.wanghong.springframework.core.io.Resource;
import cn.wanghong.springframework.test.bean.UserDao;
import cn.wanghong.springframework.test.bean.UserService;
import cn.wanghong.springframework.test.common.MyBeanFactoryPostProcessor;
import cn.wanghong.springframework.test.common.MyBeanPostProcessor;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class ApiTest {

    @Test
    public void test_BeanFactory() {

        //1.初始化BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        //3. BeanDefinition 加载完成 并且 Bean实例化之前，修改BeanDefinition 的属性值
        MyBeanFactoryPostProcessor myBeanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        myBeanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        //4. Bean实例化之后，修改Bean属性信息
        MyBeanPostProcessor myBeanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(myBeanPostProcessor);

        //5. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        String result =  userService.queryUserInfo();
        System.out.println(result);
    }

    @Test
    public void test_xml() {
        //1. 初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        //2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println(result);
    }


    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_url() throws IOException {
        // 网络原因可能导致GitHub不能读取，可以放到自己的Gitee仓库。读取后可以从内容中搜索关键字；OLpj9823dZ
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/blob/main/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

}
