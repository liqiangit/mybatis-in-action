## 功能描述

## 开发环境
### Maven

### JDK
JDK1.8

### 数据库
MySQL数据，版本5.5.39，如下：
```sql
mysql>select version(); 
5.5.39
```

## 项目结构
```sh
H:\workspace_opensource\mybatis-in-action\mybatis-in-action>tree .
H:\WORKSPACE_OPENSOURCE\MYBATIS-IN-ACTION\MYBATIS-IN-ACTION
├─.idea
│  ├─artifacts
│  ├─dictionaries
│  ├─inspectionProfiles
│  └─libraries
├─chapter2
│  └─src
│      ├─main
│      │  ├─java
│      │  │  └─com
│      │  │      └─learn
│      │  │          └─chapter2
│      │  │              ├─mapper
│      │  │              ├─po
│      │  │              └─util
│      │  └─resources
│      │      ├─com
│      │      │  └─learn
│      │      │      └─chapter2
│      │      │          └─mapper
│      │      └─db
│      ├─sql
│      └─test
│          └─java
│              └─com
│                  └─learn
│                      └─chapter2
└─chapter8
    └─src
        ├─main
        │  ├─java
        │  │  └─com
        │  │      └─learn
        │  │          └─chapter8
        │  │              ├─controller
        │  │              ├─dao
        │  │              ├─pojo
        │  │              └─service
        │  │                  └─impl
        │  ├─resources
        │  │  ├─com
        │  │  │  └─learn
        │  │  │      └─chapter8
        │  │  │          └─dao
        │  │  └─db
        │  └─webapp
        │      ├─META-INF
        │      └─WEB-INF
        └─sql
```
目前有两个模块
- chapter2
chapter2为jar包形式，数据库为mybatis_chapter2，初始化sql在src/sql文件夹内。
- chapter8
chapter8为war包形式，对应数据库为mybatis_chapter8，初始化sql在src/sql文件夹内。


## 相关连接
### 原著
《深入浅出MyBatis技术原理与实战》 杨开振，中国工信出版集团，电子工业出版社，2016.9。
原著参见[这里](http://www.imooc.com/article/12245).
       
### [kingzhe2011/mybatis-in-action](https://github.com/kingzhe2011/mybatis-in-action)
项目最初参考[kingzhe2011/mybatis-in-action](https://github.com/kingzhe2011/mybatis-in-action)，并在该项目上做了一些调整，包括：
- 优化项目结构
- 去掉redis缓存
- 增加sql初始化文件

### [bobshui/Mybatis_1](https://github.com/bobshui/Mybatis_1)
部分文件来源于[bobshui/Mybatis_1](https://github.com/bobshui/Mybatis_1)项目，包括t_role.sql文件等。