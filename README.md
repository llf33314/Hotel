# SpringBoot Hotel Maven 多模块 

项目基于[SpringBootDemo 项目](http://git.duofee.com/zhangmz/SpringBootDemo), 升级至多模块项目。

### 构建方式
使用Intellij IDEA方式构建项目模块

Eclipse/Myeclipse 请逐个Import maven 项目方式加入

### 代码风格：

IDEA 请使用**doc/java-code-style-IDEA.xml**

eclipse 请使用**doc/java-code-style-eclipse.xml**

导入以上Java代码风格，提交代码请先格式化后再提交。

### 代码编写风格：

DAO 层，

- SQL代码一律不许使用 * 方式查询结果。
- 返回结果集，不允许使用Map or List<Map> 返回。

Service 层

- 所有业务逻辑不通过的代码一律使用 throw new XXXException(); 抛出并声明消息
- 接口方法必须声明所抛出的异常类型，不允许直接使用Exception

Controller 层

- 参数校验
- 统一返回值处理

### 提交代码方式：

先Fork ，编写代码，再发起PullRequest合并

注：不熟悉SpringBoot项目构建，请先去使用SpringBoot单模块方式构建项目

### 运行
运行项目有两种方式：

1. 使用HotelApplication.java
2. 使用tomcat

默认打包成war

> 注:打包上（测试 or 正式）环境前，请先确定当前环境。

多模块细分各模块职责。

### 酒店ERP 系统
模块分析
- hotel                         基础 提供jar包依赖管理并管理各子模块
- hotel-common                  提供基础设施
- hotel-dao                     数据持久层 依赖 entity 和 common
- hotel-entity                  实体层
- hotel-generator               项目代码生成，一键生成DAO Xml Service
- hotel-service                 业务层 依赖dao
- hotel-cxf-client              客户端调用(仅生成CXF服务端代码，供客户端调用)
- hotel-cxf-server              服务端提供(提供cxf的服务端)
- hotel-web                     程序入口，并依赖service 统一集成单元测试，所有测试代码写入
- hotel-web-front-management    前台管理
- hotel-web-back-management     后台管理
- hotel-web-mobile              移动端(包含微信页面)
- hotel-web-api                 接口层 依赖service

多模块 多web 端构建项目基建。

[Redis Session 共享配置 -- 子域名共享Session](http://git.duofee.com/zhangmz/SpringBootDemo/wiki/Redis+Session+%E5%85%B1%E4%BA%AB%E9%85%8D%E7%BD%AE+--+%E5%AD%90%E5%9F%9F%E5%90%8D%E5%85%B1%E4%BA%ABSession)

[使用 Lombok 自动生成 Getter and Setter](http://www.qtdebug.com/java-lombok/)

[Spring Boot干货系列：（七）默认日志logback配置解析 | 掘金技术征文](https://juejin.im/post/58f86981b123db0062363203)