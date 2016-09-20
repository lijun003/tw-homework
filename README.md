# tw-homework project

### travis-ci

[![travis-ci](https://travis-ci.org/lijun003/tw-homework.svg)](https://travis-ci.org/lijun003/tw-homework)
[![JDK](http://img.shields.io/badge/JDK-v8.0-yellow.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)

### BUILD

#### Generate IntelliJ IDEA project
`./gradlew cleanIdea idea`

Open the `tw-homework.ipr` using IntelliJ

#### Run clean and build tasks
`./gradlew clean build`

This will run checkstyle, unit test and code coverage check

#### start application
`./gradlew bootRun` or `java -jar build/libs/tw-homework-0.1.0.jar`

#### swagger-ui links
`http://localhost:8081/tw-homework/swagger-ui.html`


### 测试运行结果
#### 正常结果：
![normal result](http://bmob-cdn-1019.b0.upaiyun.com/2016/09/20/4162e56b40ed5954801827aef984bd7f.png)

#### invalid format结果：
![invalid format result](http://bmob-cdn-1019.b0.upaiyun.com/2016/09/20/76e2c74040c7380780d1fbd6f09c5bea.png)

#### conflict_found结果：
![invalid format result](http://bmob-cdn-1019.b0.upaiyun.com/2016/09/20/d98488044054a3a580c5a97b5e763145.png)

### Git提交规范：

[卡号][提交作者&Pair作者] - comment here

Note: [提交作者]和comment之间需要有 空格+minus+空格，comment内容不要出现minus。

**Example:** `[M001][lijun&jason] - add the order domain object`


### Java代码规范：

- Java代码必须删去没有引用到的包，Import语句为灰色即是可删去。
- 类文件开始处不要出现 "/* xxx create xxx */" 这样自动生成的注释。
- 还有其他规范Checkstyle也会覆盖到

