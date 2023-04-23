# ChangeLog

### Release_1.0.5_20230422_build_A

#### 功能构建

- Dubbo 微服务增加分组配置。

- 依赖升级。
  - 升级 `spring` 依赖版本为 `5.3.27` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.4.11.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.3.3.a` 以规避漏洞。
  - 升级 `dwarfeng-ftp` 依赖版本为 `1.1.5.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.11.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.6.a` 以规避漏洞。

#### Bug修复

- (无)

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
