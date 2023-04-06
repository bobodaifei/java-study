必装Community Server Connectors、Extension Pack for Java、Deploy

setting.json

```json
//maven、gradle的配置文件变更后自动更新
"java.configuration.updateBuildConfiguration": "automatic",
//java源文件路径
"java.project.sourcePaths": [
    "src/main/java",
    "src/test/java",
],
//在项目根目录下生成.classpath等配置文件
"java.import.generatesMetadataFilesAtProjectRoot": true,
//使用Deploy插件来进行热部署时所需要的配置信息
    "deploy": {
        "packages": [
            {
                "name": "前端",
                "description": "webapp里面的所有文件",
                "files": [
                    "src/main/webapp/*",
                    "src/main/webapp/*/*",
                    "src/main/webapp/*.*",
                    "src/main/webapp/*/*.*",
                    "src/main/webapp/*/*/*.*",
                    "src/main/webapp/*/*/*/*.*",
                    "src/main/webapp/*/*/*/*/*.*",
                    "src/main/webapp/*/*/*/*/*",
                    "src/main/webapp/*/*/*/*/*/*.*",
                ],
                "exclude": [
                    "src/main/webapp/test/*"
                ],
                "deployOnSave": true,
                "useTargetList": true,
                "button": {
                    "text": "热部署",
                    "tooltip": "点击这里将前端部署到hotsite",
                    "targets": [ "HOTSITE" ]
                },
            }
        ],
        "targets": [
            {
                "type": "local",
                "name": "HOTSITE",
                "description": "A local folder",
                "dir": "target/SDDZYY/",
                "mappings": [
                    {
                        "source": "src/main/webapp",
                        "isRegEx": false,
                        "target": "/"
                    }
                ]
            }
        ]
    }
```

pom.xml

```xml
<build>
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>
        <!-- clean lifecycle, see https://maven.apache.org/ref/current/maven-core/lifecycles.html#clean_Lifecycle -->
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
        <!-- default lifecycle, jar packaging: see https://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_jar_packaging -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.8.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-jar-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>2.5.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
        </plugin>
        <!-- site lifecycle, see https://maven.apache.org/ref/current/maven-core/lifecycles.html#site_Lifecycle -->
        <plugin>
          <artifactId>maven-site-plugin</artifactId>
          <version>3.7.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-project-info-reports-plugin</artifactId>
          <version>3.0.0</version>
        </plugin>
      </plugins>
    </pluginManagement>
    <sourceDirectory>src/main/java</sourceDirectory>
    <resources>
      <resource>
        <directory>src/main/resources</directory>
      </resource>
    </resources>
    <outputDirectory>${basedir}/target/SDDZYY/WEB-INF/classes</outputDirectory>
  </build>
```

点击Maven选项卡中的compile来将后端文件编译成class文件，保存到target下的指定文件夹中去了。

在工具的最底端，有一个热部署的按钮，只需要第一次的时候点击就可以了，以后更改项目文件的时候直接ctrl+s保存就会进行热部署，就不再需要按了。点击之后，会扫描之前settings.json中配置的文件路径，将其中的文件进行拷贝到target下的项目文件中，每次ctrl+s都会触发更新操作的。

选择File或者是Exploded，File就是文件，一般是用来选择war包的，还有一个是选择文件夹的，这边我们用到了热部署，会实时更新target，所以选择Exploded，指定到上面编译后的项目文件夹，即我的工程下的target/SDDZYY文件夹。

接下来先启动Tomcat，然后点击Publish Server(Full)进行项目发布
