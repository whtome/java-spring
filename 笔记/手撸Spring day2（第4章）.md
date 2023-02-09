#### 手撸Spring day2（第4章）



* 目标
  * 基于上一章节把实例化对象交给容器统一处理，增加Bean对象在含有构造函数时的实例化
* 设计
  * 传递参数：参考Spring Bean容器的实现方式，在BeanFactory中添加重载Object getBean(String name, Object... args) 接口，传递入参
  * 根据入参实例化对象：基于Java本身自带的 DeclaredConstructor ，和Cglib来动态创建Bean对象两种方式
* 实现
  *  **新增getBean接口**：BeanFactory 中我们重载了一个含有入参信息 args 的 getBean 方法 ：Object getBean(String name, Object... args)，方便传参给构造函数实例化
  * **实例化策略接口 InstantiationStrategy **：实例化接口 instantiate  接口中添加入参信息： beanDefinition、 beanName、ctor（ Constructor 类 ，包含必要的类信息，进行匹配符合入参信息的构造函数）、args（入参信息） 
  * 具体的实例化实现类：SimpleInstantiationStrategy （JDK 实例化） 和 CglibSubclassingInstantiationStrategy (Cglib 实例化) 实现InstantiationStrategy 实例化接口的instantiate  方法，进行具体的实例化操作
  * **创建策略调用**：在 AbstractAutowireCapableBeanFactory  抽象类中定义创建对象的实例化策略属性类 InstantiationStrategy ，选择具体的实例化实现类；抽取 createBeanInstance 方法，通过 beanClass.getDeclaredConstructors()  方法获取类所有的构造函数集合，然后循环对比构造函数集合与入参信息 args 的匹配，通过实例化策略创建Bean对象

