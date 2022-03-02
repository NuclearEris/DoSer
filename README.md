# DoSer

鸡肋DoS水洞半自动检测工具

参考：https://xz.aliyun.com/t/10925

基本使用：`java -jar xxx.jar 目标JAR -m 模块名`

示例：`java -jar xxx.jar target.jar -m dos`

结果显示在对应的`txt`中

## 成果

- Apache Dubbo 拒绝服务漏洞（能复现有危害，官方不修复因为影响性能）
- Alibaba Druid 拒绝服务漏洞（触发条件较高，能复现有危害，官方不认）
- Apache Shiro 日志注入漏洞（官方认可，但认为该漏洞应该报告给Log4j）
- Apache Hadoop 日志注入漏洞（官方认可，但由于危害过低不给CVE）
- Apache Log4j2 日志注入漏洞（官方认为这不是漏洞，而是一种功能的改进）
- Apache Tomcat 日志注入漏洞（官方认为这不是漏洞，而是一种功能的改进）
- Apache ActiveMQ/Apache Kafka/Apache Commons/...（不回复）
