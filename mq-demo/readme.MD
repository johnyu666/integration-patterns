## 启动ActiveMQ的方法：
```shell
docker pull rmohr/activemq
docker run -p 61616:61616 -p 8161:8161 --name mq --rm rmohr/activemq
```