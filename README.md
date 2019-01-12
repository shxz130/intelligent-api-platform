# intelligent-api-platform

#### 项目介绍
智能的api平台，提供根据项目自动生成接口文档(只支持java项目)，自动生成json格式数据，便于做自测/测试，可以对接口直接进行测试，模拟http请求，自定义cookie，header信息。

####PPT
详细见doc

#### 软件架构
![文档架构简要图](https://upload-images.jianshu.io/upload_images/3397380-dcc8444bc76042c5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
当我们开发过程中，通过给代码加注解，然后通过 **`mvn clean install -P generate-doc`**,在编译期通过解析class生成doc树，并将doc树通过http请求，发送到itapm系统后台，通过itapm-web展示。

#### 安装教程

1. 拉代码到本地。
2. 初始化config/init.sql到mysql数据库。
3. 配置mysql信息到env.properties,也可以自定义。
4. 将itapm-annotations,itapm-maven-plugins,itapm-parser三个jar推送到私服（本地或者公司）
5. 生成mvn install 该项目，将itapm-runner.jar(spring-boot的应用)部署在公司服务器，或者本地启动。


#### 使用说明

1. 引入itapm-annotations.jar到自己的java工程
2. 配置itapm-maven-plugins到项目
3. 在自己的java项目中使用注解 @Catagory @DubboApi  @RestApi  @ApiParam
4. 通过maven命令打包即可。

itapm-maven-plugins配置：
```xml
   <profiles>
        <profile>
            <id>generate-doc</id>
            <build>
                <plugins>
                    <plugin>
                        <groupId>com.gitee.shxz130.itapm.maven</groupId>
                        <artifactId>itapm-maven-plugin</artifactId>
                        <version>1.0-SNAPSHOT</version>
                        <executions>
                            <execution>
                                <id>generate-doc</id>
                                <phase>install</phase>
                                <goals>
                                    <goal>generate-doc</goal>
                                </goals>
                            </execution>
                        </executions>
                        <configuration>
                            <systemEnName>系统英文名</systemEnName>
                            <systemChName>系统中文名</systemChName>
                            <docBaseUrl>{http://localhost:8080}/api/push.json</docBaseUrl><!-- 指定itapm应用地址-->
                            <docVersion>1.0.0</docVersion><!-- 文档版本号，不建议修改-->
                        </configuration>
                    </plugin>
                </plugins>
            </build>
        </profile>
    </profiles>
```

####项目截图
![主页](https://upload-images.jianshu.io/upload_images/3397380-0cbfba1291055bb0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![接口明细页](https://upload-images.jianshu.io/upload_images/3397380-c3c1383184220ad5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![json数据](https://upload-images.jianshu.io/upload_images/3397380-b92f296d28a1572d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![测试功能显示](https://upload-images.jianshu.io/upload_images/3397380-b9792a5d96a19579.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


最后，欢迎加好友交流，一起学习共同进步
![微信号](https://upload-images.jianshu.io/upload_images/3397380-2e430bd8654def22.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)