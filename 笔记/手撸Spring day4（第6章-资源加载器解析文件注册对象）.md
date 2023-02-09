#### 手撸Spring day4（第6章-资源加载器解析文件注册对象）



目标：

* 通过配置文件的方式简化创建过程，把注册、定义、初始化等步骤放到配置文件中进行处理
* 在现有Spring框架中，添加Spring配置的读取、解析、注册bean的操作

设计：

* 添加一个资源加载器，能够读取classpath、本地文件和云文件的配置内容
* 资源加载完成后，然后就是解析和注册Bean到Spring容器中的操作，所有解析后的注册动作，都会把Bean定义信息放到 DefaultListableBeanFactory 核心类 中
* 读取Bean定义的读取接口 `BeanDefinitionReader`  和对应的实现类，在实现类中对Bean对象进行解析和注册

实现：

* 两大部分：资源加载器、xml资源处理器，主要是对接口Resource、ResourceLoader的实现， 然后BeanDefinitionReader  接口对资源进行的具体调用，将配置信息注册到Spring容器中

* 资源加载器：接口Resource提供getInputStream()获取字节流的方法，三个实现类包括

  * ClassPathResource（classLoader.getResourceAsStream(path)）、

  * FileSystemResource（new FileInputStream(this.file)）

  * UrlResource（url.openConnection().getInputStream()）

    实现具体的获取字节流的方法

* 资源获取接口：ResourceLoader接口提供getResource方法，然后在DefaultResourceLoader中根据 location 判断，获取不同类型的Resouce类

*  接口：BeanDefinitionReader、抽象类：AbstractBeanDefinitionReader、实现类：XmlBeanDefinitionReader

  * 接口BeanDefinitionReader：提供获取BeanDefinition注册器和ResourceLoader资源加载器方法，以及三个解析资源的方法
  * 抽象类AbstractBeanDefinitionReader：获取BeanDefinition注册器和ResourceLoader资源加载器方法的实现
  * 实现类XmlBeanDefinitionReader：解析资源方法的具体实现，循环解析xml文件中bean标签和property标签的内容，填充属性并注册到BeanDefinition中

测试：

* 创建一个XmlBeanDefinitionReader资源处理器，然后传入DefaultListableBeanFactory注册器，然后解析方法读取xml文件，获取bean

