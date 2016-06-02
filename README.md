# Tomcat
tomcat源码阅读

1.导入tomcat源码包（重命名code）

2.导入tomcat发布包（重命名launch）

3.项目结构如下：
      tomcat7
            
            >code
          
            >launch
          
            >pom.xml(参见项目中的pom)
          
4.导入eclipse（import-existing maven projects）

    导入后的目录：
      tomcat7
            >code/java
                      
            >code/test
                      
            >JRE System Library
                      
            >Maven Dependencies
                      
            >code
                      
            >launch
                      
            >target
                      
            >pom.xml
                      
            >start-tomcat7.launch（文件中有注释）

5.运行-右键start-tomcat7.launch-run as start-tomcat7

6.访问http://localhost:8080

7.用户配置参见项目中（tomcat-users.xml）

参考文档：

          1.kyfxbl.iteye.com/blog/1894942

          2.How tomcat works
