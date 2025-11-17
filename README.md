项目名称
demo

🚀 快速开始
环境要求
JDK 17
Maven 3.6+
MySQL 5.7+
安装步骤
克隆项目
git clone https://github.com/FrankGong7362/demo.git
也可使用ssh下载 git@github.com:FrankGong7362/demo.git

数据库配置
datasource:
url: spring.datasource.url=jdbc:mysql://localhost:3306/test
username: root
password: 123456

📁 项目结构
src/
├── main/
│   ├── java/
│   │   └── com/yourpackage/
│   │       ├── controller/     # 控制层
│   │       ├── service/        # 业务层
│   │       ├── mapper/         # 数据持久层
│   │       ├── pojo/           # 实体类
│   │       ├── constants/      # 常量配置类
│   │       ├── exception/      # 异常处理类
│   │       └── response/       # 返回值封装类
│   └── resources/
│       ├── mapper/                    # MyBatis映射文件
│       ├── dbscripts/                 # 数据库脚本文件
│       └── application.properties     # 配置文件
└── test/           
