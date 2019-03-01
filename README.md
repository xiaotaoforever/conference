# Problem Two: Conference Track Management
## 代码运行说明
1. resources目录下的talks文件为对应的Talk文本文件。
2. 在IDE中Main.java中的main函数为运行主类。
3. 也可通过mvn package 编译为jar包后，通过 java -jar xxx.jar 运行。

## 代码设计说明
1. 将会议的各个部分映射为实体
2. 根据配置动态计算track的数量
4. track的session后续可以拓展为不同的组装方式
5. session中的talk可以通过新增实现SessionType实现类的方式进行拓展。