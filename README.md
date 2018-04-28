common4j
========

一些通用的类集合。在threadpool4j，event4j等项目中使用。

# 一、I/O操作

* [cn.aofeng.common4j.io.IOUtil](src/cn/aofeng/common4j/io/IOUtil.java)：复制文件、复制字节流、复制字符流、读取文件内容返回字符串。
* [cn.aofeng.common4j.io.TextFileLineReader](src/cn/aofeng/common4j/io/TextFileLineReader.java)：按行读取文本文件，将行内容和行编号交给实现了[TextFileLineProcessor](src/cn/aofeng/common4j/io/TextFileLineProcessor.java)接口的处理器处理。

# 二、反射操作

* [cn.aofeng.common4j.reflection.ReflectionUtil](src/cn/aofeng/common4j/reflection/ReflectionUtil.java)：使用默认构造方法或带参数的构造方法创建实例、查找方法、调用实例方法、调用静态方法。

# 三、线程

* [cn.aofeng.common4j.thread.DefaultThreadFactory](src/cn/aofeng/common4j/thread/DefaultThreadFactory.java)：线程工厂，创建线程池时使用，可自定义线程名称前缀。

# 四、XML

* [cn.aofeng.common4j.xml.DomUtil](src/cn/aofeng/common4j/xml/DomUtil.java)：读取XML文件解析成DOM树。
* [cn.aofeng.common4j.xml.NodeParser](src/cn/aofeng/common4j/xml/NodeParser.java)：解析DOM节点，获取其子节点和属性。

# 五、其他

* [cn.aofeng.common4j.lang.StringUtil](src/cn/aofeng/common4j/lang/StringUtil.java)：字符串操作：是否为null、是否为空字符、是否为空白字符。
* [cn.aofeng.common4j.lang.SystemUtil](src/cn/aofeng/common4j/lang/SystemUtil.java)：获取系统换行符、处理器个数。
