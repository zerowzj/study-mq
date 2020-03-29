# 1. 安装

## 1.1 安装

1. 安装jdk

2. 安装zookeeper

3. 解压

   ```shell
   tar -zxvf 
   ```

4. 目录

   - /bin
   - /config
   - /lib
   - /log

## 1.2 配置

1. server.properties

   ```shell
   ############################# Server Basics #############################
   #broker的全局唯一编号，不能重复
   broker.id=0
   
   ############################# Socket Server Settings #############################
   #处理网络请求的线程数量，也就是接收消息的线程数。
   #接收线程会将接收到的消息放到内存中，然后再从内存中写入磁盘。
   num.network.threads=3
   #消息从内存中写入磁盘是时候使用的线程数量。
   #用来处理磁盘IO的线程数量
   num.io.threads=8
   #发送套接字的缓冲区大小
   socket.send.buffer.bytes=102400
   #接受套接字的缓冲区大小
   socket.receive.buffer.bytes=102400
   #请求套接字的缓冲区大小
   socket.request.max.bytes=104857600
   
   ############################# Log Basics #############################
   #（分隔的目录列表，用于存储日志文件
   log.dirs=/tmp/kafka-logs
   #每个主题的默认日志分区数。更多分区允许更大
   num.partitions=1
   #我们知道segment文件默认会被保留7天的时间，超时的话就
   #会被清理，那么清理这件事情就需要有一些线程来做。这里就是
   #用来设置恢复和清理data下数据的线程数量
   #在启动时用于日志恢复和在关闭时刷新的每个数据目录的线程数。
   #对于数据目录位于RAID阵列中的安装，建议增加此值。
   num.recovery.threads.per.data.dir=1
   
   ############################# Internal Topic Settings  #############################
   offsets.topic.replication.factor=1
   transaction.state.log.replication.factor=1
   transaction.state.log.min.isr=1
   
   ############################# Log Flush Policy #############################
   log.flush.interval.messages=10000
   log.flush.interval.ms=1000
   log.retention.hours=168
   log.segment.bytes=1073741824
   log.retention.check.interval.ms=300000
   
   ############################# Log Retention Policy #############################
   
   ############################# Zookeeper #############################
   zookeeper.connect=150.158.122.249:2181
zookeeper.connection.timeout.ms=6000
   
   ############################# Group Coordinator Settings #############################
   ```
   
   

## 1.3 启停

```shell
#前台启动
./kafka-server-start.sh ../config/server.properties
#后台启动
./kafka-server-start.sh -daemon ../config/server.properties

#停止
./kafka-server-stop.sh
```

# 2. 管理

## 2.1 主题管理

1. 
2. 

```shell
#查询集群描述
./kafka-topics.sh --zookeeper 172.17.0.5:2181 --describe

#查询topic列表
./kafka-topics.sh --zookeeper 172.17.0.5:2181 --list 
./kafka-topics.sh --bootstrap-server 172.17.0.5:9092 --list 

#查询topic详情
./kafka-topics.sh --zookeeper 172.17.0.5:2181 --describe --topic TOPICNAME 

#删除topic
./kafka-topics.sh --zookeeper 172.17.0.5:2181 --delete --topic TOPICNAME

#修改topic
./kafka-topics.sh --zookeeper 172.17.0.5:2181 --alter --partitions NUM --topic TOPICNAME

#创建topic
./kafka-topics.sh --zookeeper 172.17.0.5:2181 --create -partitions NUM--replication-factor NUM --topic TOPICNAME
```

## 2.2 消费者组管理

```shell
#查询消费者组列表
./kafka-consumer-groups.sh --bootstrap-server 172.17.0.5:9092 --list

#查询消费者组详情
./kafka-consumer-groups.sh --bootstrap-server 172.17.0.5:9092 --describe --group GROUBNAME

#删除消费者组
./kafka-consumer-groups.sh --zookeeper 172.17.0.5:2181 --delete --group GROUBNAM

#重设消费者组位移
#最早处
./kafka-consumer-groups.sh --bootstrap-server 172.17.0.5:9092 --group GROUPNAME --reset-offsets --all-topics --to-earliest --execute
#最新处
./kafka-consumer-groups.sh --bootstrap-server 172.17.0.5:9092 --group GROUPNAME --reset-offsets --all-topics --to-latest --execute
#某个位置
./kafka-consumer-groups.sh --bootstrap-server 172.17.0.5:9092 --group GROUPNAME --reset-offsets --all-topics --to-offset 3 --execute
#某个时间之后得最早位移
./kafka-consumer-groups.sh --bootstrap-server 172.17.0.5:9092 --group GROUPNAME --reset-offsets --all-topics --to-datetime 2019-09-15T00:00:00.000
```

## 2.3 

# 3.



