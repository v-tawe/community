# 使用 Spring Boot + Thymeleaf 搭建 Community 提问社区

历时一个月（06/03/2020 - 07/06/2020）终于完结啦！！！

## 技术栈

- Spring Boot
- MyBatis
- Pagination - PageHelper
- MBG - MyBatis Gernerator
- Flyway
- Lombok
- GitHub Authorization
- Session & Cookies

- Bootstrap
- MD Editor
- Image Upload

- Developer Tools
- Azure Development

## 功能实现

主要实现一个可以论坛问答平台，功能包括：

- 提交问题
- 回复问题
- 通知提醒
- 最新问题
- 最热问题

## 实现流程

1. 创建 Spring Boot 项目
1. 前端引入 Bootstrap
1. 使用 GitHub 第三方登录
1. 使用 Session & Cookies 保存登录状态
1. 引入 H2 数据库
1. 集成 MyBatis
1. 使用 Flyway Migration 管理 MyBatis
1. 完成 问题发布页面
1. 添加 Lombok 支持 - @Data
1. 完成 首页列表功能
1. 引入 Developer Tools 实现热部署
1. 引入 PageHelper 实现分页功能
1. 前端页面拆解 - nav
1. 添加拦截器实现是否登录检测
1. 完成 问题详情 页面
1. 完成 更新问题 页面
1. 引入 MyBatis Generator
1. 异常管理 - ControllerAdvice & ExceptionHandler
1. 实现 阅读数 功能
1. 实现 评论回复 功能
    1. 实现 二级评论 功能 - 前端未实现
1. 添加 事务 处理
1. 自动跳转登录 - 登录后会自动关闭登录页面
1. 实现 回复列表 功能
1. 实现 回复通知 功能
1. 引入 MD Editor 实现 Markdown 编辑
1. 上传图片 - 基于 MD Editor 插件
1. 引入 Azure Storage 存储 图片
1. 实现 搜索 功能
1. 添加 页尾
1. 添加 日志记录 功能
1. 配置 Maven Profile 实现 PROD/DEV 环境快速切换