#### 手撸Spring day1（1~3）

* 目标

  简单实现一个Spring Bean容器，实现Bean容器关于bean对象的注册和获取，将Bean的创建交给容器

* 设计：完善Spring容器
  *  BeanDefinition 注册时，只注册类信息，而不是实例化对象注册
  * 定义Bean工厂，提供Bean的获取方法
  * 单例Bean的接口定义实现

* 实现
  * 接口**BeanFactory**：定义Bean工厂，提供Bean的获取方法getBean，这个Bean工厂接口有抽象类 AbstractBeanFactory 实现
  * 接口**SingletonBeanRegistry**：单例注册接口，提供getSingleton获取单例对象方法，由抽象类DefaultSingletonBeanRegistry实现
  * 接口**BeanDefinitionRegistry**：向注册表中注册BeanDefinition
  * 抽象类**DefaultSingletonBeanRegistry**：实现单例注册接口的 getSingleton 方法，同时自己实现一个受保护的 addSingleton 方法 ，可以被继承此类的其他类调用
  * 抽象类定义模板方法**AbstractBeanFactory**：继承 DefaultSingletonBeanRegistry 方法，具备单例注册类的能力；对接口BeanFactory的实现，对单例Bean对象的获取及获取不到时拿到Bean的定义做Bean相应的实例化操作；定义了获取Bean定义的getBeanDefinition和Bean实例化的createBean的抽象方法，由抽象类的实现类进行实现
  * 实例化Bean抽象类**AbstractAutowireCapableBeanFactory**：继承AbstractBeanFactory，实现了Bean的实例化操作 newInstance ，实例化后调用 addSingleton 方法放到单例对象的缓存中
  * 核心实现类**DefaultListableBeanFactory**：继承AbstractAutowireCapableBeanFactory类，具备接口 BeanFactory 和 AbstractBeanFactory 的功能实现；还实现了 BeanDefinitionRegistry 接口中的 registerBeanDefinition 方法；

