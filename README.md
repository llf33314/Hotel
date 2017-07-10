# SpringBoot Hotel Maven 多模块

项目基于[SpringBootDemo 项目](http://git.duofee.com/zhangmz/SpringBootDemo), 升级至多模块项目。

使用Intellij IDEA方式构建项目模块

Eclipse/Myeclipse 请参考此 [Maven 多模块搭建SpringBoot MyEclipse](http://note.youdao.com/share/?id=589999e5dbc54b99e2f2214f7863fde8&type=note#/)


注：不熟悉SpringBoot项目构建，请先去使用SpringBoot单方式模块构建项目

多模块细分各模块职责。

### 酒店ERP 系统
模块分析
- hotel  基础 提供jar包依赖管理并管理各子模块
- hotel-common 提供基础设施
- hotel-dao  数据持久层 依赖 entity 和 common
- hotel-entity 实体层 
- hotel-generator 项目代码生成，一键生成DAO Xml Service
- hotel-service 业务层 依赖dao
- hotel-web  程序入口，并依赖service 统一集成单元测试，所有测试代码写入
- hotel-web-manager 后台管理
- hotel-web-api 接口层 依赖service

多模块 多web 端构建项目基建。


