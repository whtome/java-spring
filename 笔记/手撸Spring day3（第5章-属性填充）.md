### 手撸Spring day3（第5章-属性）



1. 目标

   对实例化对象进行属性填充，包括还没有实例化的对象属性

2. 设计

   * 属性填充要在类实例化创建之后，在 `AbstractAutowireCapableBeanFactory`  的  createBean  方法中添加填充属性操作 `applyPropertyValues`  
   * 填充属性之前，需要在bean定义 BeanDefinition类中，添加 PropertyValues  信息
   * 属性填充还包括Bean的对象属性，所以需要再定义一个 BeanReference ，在具体的实例化操作时进行递归创建和填充

3. 实现

   * 定义属性PropertyValue 和PropertyValues ：PropertyValue 包含属性name和属性值value，PropertyValues 是对象的所有属性集合
   * Bean定义补全：在BeanDefinition 中扩充PropertyValues 属性
   * Bean属性填充：在实例化抽象类AbstractAutowireCapableBeanFactory 中的createBean方法中添加填充属性操作 applyPropertyValues  ，通过 beanDefinition.getPropertyValues() 循环进行属性填充，如果遇到 BeanReference ，则递归获取Bean实例，调用getBean方法， 最后通过 *BeanUtil.setFieldValue(bean, name, value)* 填充属性
   * 调试注意：需要先将对象属性userDao注入到容器中

