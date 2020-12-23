# Java Web Project

## 20201211

同学们，今天我们简单的把一个Java动态Web项目给运行在Tomcat里面了，并使用Servlet创建了一个什么都没干的images接口，编写了项目的主页index.html，粗浅的学习了一点Bootstrap前端UI库的使用。请大家自己动手写一点，周末愉快！

## 20201222

修改返回数据，统一为ResponseMessage类型。

## 20201225 

该Web应用不少代码我已经给大家写了，大家作为参考。

启动该项目后，可以请求app-init接口，对应用和数据库进行初始化。注意：目前请求该接口会重建IMAGE表。

数据库配置文件是src/db/util/db-config.properties，注意修改数据库文件的存储URL，请使用绝对路径！Windows系统示例：jdbc:h2:D:/db/h2db；类UNIX系统示例：jdbc:h2:~/db/h2db。

实验课要求：

完成源代码中有补充标记和要求的部分，需要补充和修改的代码Tag在以下文件中：

- src/servlet/AppInitServlet.java
- WebContent/index.html
- WebContent/detail.html

## Reference

### H2 DB

- https://www.cnblogs.com/hanhuibing/articles/5527458.html
- https://www.cnblogs.com/tianwyam/articles/h2db.html
- 数据类型 https://qiang.blog.csdn.net/article/details/43269771?utm_medium=distribute.pc_relevant_t0.none-task-blog-OPENSEARCH-1.control&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-OPENSEARCH-1.control
