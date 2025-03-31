# ChangeLog

### Release_1.2.0_20250331_build_A

#### 功能构建

- (无)

#### Bug修复

- 修正部分 WebInput DTO 中不正确的 JSON 字段名称。
  - com.dwarfeng.familyhelper.note.sdk.bean.dto.WebInputNoteNodeUpdateInfo。

#### 功能移除

- (无)

---

### Release_1.1.1_20241211_build_A

#### 功能构建

- 增加预设查询。
  - PonbMaintainService.CHILD_FOR_NOTE_BOOK_SET。

#### Bug修复

- 修正配置文件中的错误。
  - logging/settings-linux.xml。

#### 功能移除

- (无)

---

### Release_1.1.0_20241208_build_A

#### 功能构建

- 依赖优化。
  - 优化部分依赖的排除项，以避免潜在的 `netty` 版本冲突问题。

- 部分 dubbo 消费者服务注册配置添加 `check="false"` 属性。
  - generateService。

- 优化配置文件。
  - 优化 `application-context-database.xml`，使得更多属性可以在配置文件中配置。

- 增加操作服务方法。
  - com.dwarfeng.familyhelper.note.stack.service.NoteBookOperateService。

- 增加预设查询。
  - NoteBookMaintainService.USER_OWNED。
  - NoteBookMaintainService.USER_PERMITTED_WITH_CONDITION_DISPLAY。
  - NoteBookMaintainService.USER_OWNED_WITH_CONDITION_DISPLAY。

- 新增实体。
  - com.dwarfeng.familyhelper.note.stack.bean.entity.Favorite。

- dwarfeng-ftp 优化。
  - 优化 FtpHandler 的扫描方式，使其符合最新版本标准。
  - 优化 FtpConstants 中的常量类型，使其更加符合 dwarfeng-ftp 的接口标准。

- 升级 spring-telqos 并应用其新功能。
  - 使用包扫描的方式注册指令。
  - 优化 `telqos/connection.properties` 中配置的键名。

- 优化 `node` 模块部分服务启停脚本的注释。
  - binres/familyhelper-start.bat。
  - binres/familyhelper-start.sh。

- 优化项目启停脚本设置程序的根目录的方式。

- 日志功能优化。
  - 优化默认日志配置，默认配置仅向控制台输出 `INFO` 级别的日志。
  - 优化日志配置结构，提供 `conf/logging/settings.xml` 配置文件及其不同平台的参考配置文件，以供用户自定义日志配置。
  - 优化日志配置结构，提供 `confext/logging-settings.xml` 配置文件，以供外部功能自定义日志配置。
  - 优化启动脚本，使服务支持新的日志配置结构。
  - 优化 `assembly.xml`，使项目打包时输出新的日志配置结构。
  - 优化 `confext/README.md`，添加新的日志配置结构的相关说明。

- 优化启停脚本的目录结构。

- 优化 `application-context-*.xml` 中的格式。
  - application-context-dubbo.xml。

- 依赖升级。
  - 升级 `subgrade` 依赖版本为 `1.5.7.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `dwarfeng-ftp` 依赖版本为 `1.2.2.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `spring` 依赖版本为 `5.3.39` 以规避漏洞。
  - 升级 `protobuf` 依赖版本为 `3.25.5` 以规避漏洞。
  - 升级 `mysql` 依赖版本为 `8.2.0` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.22` 以规避漏洞。
  - 升级 `jetty` 依赖版本为 `9.4.55.v20240627` 以规避漏洞。
  - 升级 `netty` 依赖版本为 `4.1.115.Final` 以规避漏洞。
  - 升级 `zookeeper` 依赖版本为 `3.9.3` 以规避漏洞。
  - 升级 `guava` 依赖版本为 `32.0.1-jre` 以规避漏洞。
  - 升级 `slf4j` 依赖版本为 `1.7.36` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.5.3.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.14.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.11.b` 以规避漏洞。

- dubbo 优化。
  - 优化 `dubbo/connection.properties` 中配置的键名。

#### Bug修复

- 修复 `assembly.xml` 中缺失的配置项。

- 修正配置类中的错误。
  - ExceptionCodeOffsetConfiguration。

- 修正 Hibernate 实体中的注解错误。
  - com.dwarfeng.familyhelper.note.impl.bean.entity.HibernatePonb。

#### 功能移除

- (无)

---

### Release_1.0.5_20230426_build_A

#### 功能构建

- 优化 Mapper 接口的文件路径。

- 增加预设查询。
  - NoteItemMaintainService.CHILD_FOR_BOOK_NAME_LIKE。
  - NoteItemMaintainService.CHILD_FOR_BOOK_ROOT_INDEX_ASC。
  - NoteItemMaintainService.CHILD_FOR_BOOK_ROOT_INDEX_DESC。
  - NoteNodeMaintainService.CHILD_FOR_BOOK_NAME_LIKE。

- Dubbo 微服务增加分组配置。

- 依赖升级。
  - 升级 `spring` 依赖版本为 `5.3.27` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.4.11.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.3.3.a` 以规避漏洞。
  - 升级 `dwarfeng-ftp` 依赖版本为 `1.1.5.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.11.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.6.a` 以规避漏洞。

#### Bug修复

- 补全 `application-context-placeholder.xml` 中的本地文件覆盖路径。

#### 功能移除

- (无)

---

### Release_1.0.4_20230402_build_A

#### 功能构建

- (无)

#### Bug修复

- 修复启动脚本 `familyhelper-start.bat` 默认程序根目录不正确的问题。

#### 功能移除

- (无)

---

### Release_1.0.3_20230329_build_A

#### 功能构建

- 优化项目结构，增加项目目录。
  - `./confext/`。
  - `./libext/`。
  - `./opt/`。
  - `./optext/`。

#### Bug修复

- 修复启动脚本 `familyhelper-start.bat` 启动程序时控制台标题不正确的问题。

#### 功能移除

- (无)

---

### Release_1.0.2_20230327_build_A

#### 功能构建

- 启停脚本优化。
  - 优化 Windows 系统的启动脚本。
  - 优化 Linux 系统的启停脚本。

- 依赖升级。
  - 升级 `snakeyaml` 依赖版本为 `2.0` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.21` 以规避漏洞。
  - 升级 `netty` 依赖版本为 `4.1.86.Final` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.3.2.a` 以规避漏洞。

#### Bug修复

- 修复 `NoteNodeCrudOperation` 删除实体方法的逻辑错误。

#### 功能移除

- (无)

---

### Release_1.0.1_20230112_build_A

#### 功能构建

- 使用 `MapStruct` 重构 `BeanTransformer`。

- 增加依赖。
  - 增加依赖 `protobuf` 以规避漏洞，版本为 `3.19.6`。
  - 增加依赖 `guava` 以规避漏洞，版本为 `31.1-jre`。
  - 增加依赖 `gson` 以规避漏洞，版本为 `2.8.9`。
  - 增加依赖 `snakeyaml` 以规避漏洞，版本为 `1.33`。
  - 增加依赖 `jackson` 以规避漏洞，版本为 `2.14.0`。
  - 增加依赖 `javax.servlet-api` 以规避漏洞，版本为 `4.0.1`。
  - 增加依赖 `jboss-logging` 以规避漏洞，版本为 `3.4.3.Final`。

- 插件升级。
  - 升级 `maven-deploy-plugin` 插件版本为 `2.8.2`。

- 依赖升级。
  - 升级 `mysql` 依赖版本为 `8.0.31` 以规避漏洞。
  - 升级 `jedis` 依赖版本为 `3.8.0` 以规避漏洞。
  - 升级 `spring-data-redis` 依赖版本为 `2.7.5` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.18` 以规避漏洞。
  - 升级 `zookeeper` 依赖版本为 `3.5.7` 以规避漏洞。
  - 升级 `curator` 依赖版本为 `4.3.0` 以规避漏洞。
  - 升级 `hibernate-validator` 依赖版本为 `6.2.5.Final` 以规避漏洞。
  - 升级 `dutil` 依赖版本为 `beta-0.3.2.a` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.4.10.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.3.0.a` 以规避漏洞。
  - 升级 `dwarfeng-ftp` 依赖版本为 `1.1.2.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.10.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.5.a` 以规避漏洞。

- 优化 pom.xml 文件格式。

#### Bug修复

- 修正附件文件删除时报错的 bug。

#### 功能移除

- (无)

---

### Release_1.0.0_20221001_build_A

#### 功能构建

- 项目结构建立，程序清理测试通过。

- 建立实体以及维护服务，并通过单元测试。
  - com.dwarfeng.familyhelper.note.stack.bean.entity.AttachmentFileInfo。
  - com.dwarfeng.familyhelper.note.stack.bean.entity.NoteBook。
  - com.dwarfeng.familyhelper.note.stack.bean.entity.NoteItem。
  - com.dwarfeng.familyhelper.note.stack.bean.entity.NoteNode。
  - com.dwarfeng.familyhelper.note.stack.bean.entity.Ponb。
  - com.dwarfeng.familyhelper.note.stack.bean.entity.User。

- 完成 node 模块，打包测试及启动测试通过。

#### Bug修复

- (无)

#### 功能移除

- (无)
