#### 手撸Spring （8、初始化和销毁方法）



目标：

* 满足用户可以在xml中配置初始化和销毁方法，也可以通过实现类的方式处理，后续还可以通过注解的方式处理初始化操作

设计：

* 在xml配置中添加初始化和销毁init-method、destroy-method方法的注解，在xml文件加载的过程中把注解配置一并定义到BeanDefinition（在BeanDefinition的属性中添加初始化和销毁方法名）的属性中；在initializeBean初始化操作中，通过反射的方式来调用配置在Bean定义属性当中的方法信息。如果是接口实现的方法，直接通过Bean对象调用对应接口的定义的方法即可；
* 除了初始化操作外，destroy-method 和 DisposableBean 接口的定义也都是在Bean对象初始化阶段完成，执行注册销毁方法的信息会在 DefaultSingletonBeanRegistry 类中的 disposableBeans 缓存中，后续统一执行销毁操作。执行销毁方法时，因为由反射调用和接口直接调用，因此使用适配器模式进行包装；销毁方法需要在在虚拟机执行关闭之前进行操作，注册一个钩子函数在关闭虚拟机的时候进行调用，执行销毁方法和close方法；

实现：

* 初始化方法InitializingBean ：afterPropertiesSet() 在Bean处理了属性填充后调用
* 销毁方法接口 DisposableBean ：destroy() 
* Bean属性中新增初始化和销毁属性：读取xml中配置的初始化和销毁方法名
* 在 XmlBeanDefinitionReader 中增加对新增初始化和销毁属性的读取，填充到BeanDefinition中
* 在 AbstractAutowireCapableBeanFactory  中的初始化invokeInitMethods方法中，执行对象定义的初始化方法，包括实现了 InitializingBean  接口的，处理 afterPropertiesSet  方法；配置信息中存在 init-method的，判断方法是否存在，然后执行反射调用 initMethod.invoke(bean) 。
* 定义销毁方法适配器DisposableBeanAdapter ：实现DisposableBean 接口，目前有两个方式：实现接口 DisposableBean 和配置信息 destroy-method ，都是由 AbstractApplicationContext  在注册虚拟机钩子后，在虚拟机关闭前执行动作
* 创建Bean时注册销毁方法对象：在创建Bean时，把销毁方法的具体信息注册到 DefaultSingletonBeanRegistry  中新增的 disposableBeans缓存池中，最终被 AbstractApplicationContext  的close方法通过 getBeanFactory().destroySingletons() 调用
* 虚拟机关闭钩子注册调用销毁方法：在 ConfigurableApplicationContext  接口中定义注册虚拟机钩子方法 registerShutdownHook () 和手动关闭的方法close()；在 registerShutdownHook ()方法实现中，通过 Runtime.getRuntime().addShutdownHook（new Thread(this::close)）调用close方法调用销毁方法

