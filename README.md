# SpringBoot Hotel Maven 多模块

项目基于[SpringBootDemo 项目](http://sss.com), 升级至多模块项目。

注：不熟悉SpringBoot项目构建，请先去使用SpringBoot单模块构建

多模块细分各模块职责。

模块分析

- hotel  基础 提供jar包依赖管理并管理各子模块
- hotel-common 提供基础设施
- hotel-com.gt.hotel.dao  数据持久层 依赖 entity 和 common
- hotel-entity 实体层 
- hotel-service 业务层 依赖dao
- hotel-web  程序入口，并依赖service 统一集成单元测试，所有测试代码写入
- hotel-web-manager 后台管理
- hotel-web-api 接口层 依赖service
- hotel-web-wechat 微信接口层
- hotel-web-mobile 移动端接口 h5端

多模块 多web 端构建项目基建。

划分各层次之间的业务

