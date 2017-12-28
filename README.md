# OCRTest

## 环境

IDEA 2017.2.4

Deepin GNU/Linux 15.5 \n \l

## 其他

试试百度的OCR API

[参考文档](http://ai.baidu.com/docs#/OCR-Java-SDK/top)

我这里直接使用JAR包

## 流程
引入的包 java-sdk Version 4.0.0
```
libs/
├── aip-java-sdk-4.0.0.jar
└── json-20160810.jar
```
## 测试
资源文件 resources/test.jpg

![测试](resources/result.jpg)

总共就新建了一个Sample类，其中关键是新建AipOcr。

AipOcr是Optical Character Recognition的Java客户端，为使用Optical Character Recognition的开发人员提供了一系列的交互方法。

为了安全，在提交前，我将API_KEY与SECRET_KEY删除了。

常量APP_ID在百度云控制台中创建，常量API_KEY与SECRET_KEY是在创建完毕应用后，系统分配给用户的，均为字符串，用于标识用户，为访问做签名验证，可在AI服务控制台中的应用列表中查看。

注意：如您以前是百度云的老用户，其中API_KEY对应百度云的“Access Key ID”，SECRET_KEY对应百度云的“Access Key Secret”。

## 结果

resources/result.jpg

![结果](resources/result.jpg)