## 项目简介

本项目是一个报文解析代码生成器，使用的编程语言为**Java**和**freemarker**。若您有着解析大量不同类型报文的需求，并且想要获取到报文解析后对应的实体，该项目可能对您有所帮助。



## 使用方法

1.在application.yml中定义报文的数据段，根据提供的模板修改fieldName，bit，fieldType三个属性的值

| 属性名    | 属性类型                             |
| --------- | ------------------------------------ |
| fieldName | String                               |
| bit       | int                                  |
| fieldType | String，可选值为UInt，IntS，Bit，Int |

2.修改config.properties中的entityName值，该值为生成文件的类名，可以定义为报文的名字



2.定义完成后，启动springboot项目，生成的代码会在result包中出现