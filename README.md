## 项目简介

本项目是一个报文解析代码生成器，使用的编程语言为**Java**和**freemarker**。若您有着解析大量不同类型报文的需求，并且想要获取到报文解析后对应的实体，该项目可能对您有所帮助。



## 使用方法

1.在application.yml中定义报文的数据段，根据提供的模板修改fieldName，bit，fieldType，notes三个属性的值

| 属性名    | 属性类型                             | 属性含义 |
| --------- | ------------------------------------ | -------- |
| fieldName | String                               | 字段名   |
| bit       | int                                  | 字段长度 |
| fieldType | String，可选值为UInt，IntS，Bit，Int | 字段类型 |
| notes     | String                               | 字段注释 |

2.修改config.properties中的entityName值，该值为生成文件的类名，可以定义为报文的名字



2.定义完成后，启动springboot项目，生成的代码会在result包中出现





## 词汇表

1.对于希腊字母，采取的命名用对应读法来表示

![](img/vocabulary.png)



2.对于某个希腊字母上面有 “  .  ” 等其他修饰符时。即$\dot{A}$ 这种，我们java采用的命名方式为 A\$dot\$ 。即采用$$来操作， ​ 

**在$$ 中间添加想要的内容,若为点的话为dot**



3.对于下标的情况，我们采用下划线来命名。即$t_0$  我们采用 t_0来命名



4.总体命名还是采取驼峰式命名法。



下面举几个命名例子

| 字段名            | java的命名      |
| ----------------- | --------------- |
| $\Omega_0$        | omega_0         |
| $SISAIocb~$       | sisai_ocb       |
| $\Delta \dot n_0$ | deltaN\$dot\$_0 |

