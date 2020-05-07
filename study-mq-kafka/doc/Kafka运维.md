# 一. 安装

## 1.1 安装

1. 安装jdk

2. 安装zookeeper

3. 解压

   ```shell
   tar -zxvf 
   ```

## 1.2 目录

- /bin
- /config
- /lib
- /log

## 1.3 启停

```shell
#前台启动
./kafka-server-start.sh ../config/server.properties
#后台启动
./kafka-server-start.sh -daemon ../config/server.properties

#停止
./kafka-server-stop.sh
```



# 二. 配置

```shell


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
```



## 2.1 基本配置

- broker.id

  broker的全局唯一编号，不能重复

## 2.2 网络配置

- host.name

- advertised.listeners

- num.network.threads

  处理网络请求的线程数量，也就是接收消息的线程数。接收线程会将接收到的消息放到内存中，然后再从内存中写入磁盘。

- num.io.threads

  消息从内存中写入磁盘是时候使用的线程数量。用来处理磁盘IO的线程数量。

- socket.send.buffer.bytes

  发送套接字的缓冲区大小。

- socket.receive.buffer.bytes

  接受套接字的缓冲区大小。

- socket.request.max.bytes

  请求套接字的缓冲区大小。

## 2.3 zk配置

- zookeeper.connect

  zk quorum设置。如果有多个使用逗号分割

- zookeeper.connection.timeout.ms

  连接zk的超时时间

- zookeeper.sync.time.ms

  zk集群中leader和follower之间的同步时间

## 2.3 主题配置

- auto.create.topics.enable

  是否允许自动创建topic，若是false，就需要通过命令创建topic

- default.replication.factor

  一个 topic，默认分区的 replication 个数 ，不能大于集群中 broker 的个数

- message.max.bytes

  消息体的最大大小，单位是字节

## 2.4 日志配置

### 2.4.1 基本

- log.dirs

  日志存放目录，多个目录使用逗号分割。

- num.partitions

  每个主题的默认日志分区数

- num.recovery.threads.per.data.dir

  我们知道segment文件默认会被保留7天的时间，超时的话就会被清理，那么清理这件事情就需要有一些线程来做。这里就是用来设置恢复和清理data下数据的线程数量在启动时用于日志恢复和在关闭时刷新的每个数据目录的线程数。对于数据目录位于RAID阵列中的安装，建议增加此值。

### 2.4.2 刷新

- log.flush.interval.messages

  当达到消息数量时，会将数据flush到日志文件中。默认10000

- log.flush.interval.ms

  当达到上面的时间时，执行一次强制的flush操作。interval.ms和interval.messages无论哪个达到，都会flush。默认3000ms

- log.flush.scheduler.interval.ms

  检查是否需要将日志flush的时间间隔

### 2.4.3 保留

- log.retention.hours

  日志保存时间 (hours|minutes)，默认为7天（168小时）。超过这个时间会根据policy处理数据。

- log.retention.bytes

  日志数据存储的最大字节数。超过这个时间会根据policy处理数据。bytes和minutes无论哪个先达到都会触发。

- log.cleanup.policy 

  日志清理策略（delete|compact）

- log.retention.check.interval.ms

  日志片段文件的检查周期，查看它们是否达到了删除策略的设置（log.retention.hours或log.retention.bytes）

- log.segment.bytes

  控制日志segment文件的大小，超出该大小则追加到一个新的日志segment文件中（-1表示没有限制）

### 2.4.4 压缩

- log.cleaner.enable

  是否开启压缩

- log.cleaner.delete.retention.ms

  对于压缩的日志保留的最长时间

1. 日志

   - 文件段

     - log.roll.hours

     - log.index.size.max.bytes

     - log.index.interval.bytes

       对于segment日志的索引文件大小限制



# 三. 管理

## 2.1 主题管理

1. 查询主题

   ```shell
   #查询集群描述
   ./kafka-topics.sh --zookeeper 172.17.0.5:2181 --describe
   
   #查询topic列表
   ./kafka-topics.sh --zookeeper 172.17.0.5:2181 --list 
   ./kafka-topics.sh --bootstrap-server 172.17.0.5:9092 --list 
   #查询topic详情
   ./kafka-topics.sh --zookeeper 172.17.0.5:2181 --describe \
              --topic TOPICNAME 
   ```

2. 创建主题

   ```shell
   #创建topic
   ./kafka-topics.sh --zookeeper 172.17.0.5:2181 \
              --create \
              --partitions NUM \
              --replication-factor NUM \
              --topic TOPICNAME
   ```

3. 变更主题

   ```shell
   #删除topic
   ./kafka-topics.sh --zookeeper 172.17.0.5:2181 --delete \
             --topic TOPICNAME
   #修改topic分区
   ./kafka-topics.sh --zookeeper 172.17.0.5:2181 \
             --alter \
             --partitions 2 \
             --topic topic
   ```

## 2.2 消费者组管理

1. 查询

   ```shell
   #查询消费者组列表
   ./kafka-consumer-groups.sh --bootstrap-server 172.17.0.5:9092 --list
   
   #查询消费者组详情
   ./kafka-consumer-groups.sh --bootstrap-server 172.17.0.5:9092 --describe \
                --group GROUBNAME
   ```

2. 删除

   ```shell
   #删除消费者组
   ./kafka-consumer-groups.sh --zookeeper 172.17.0.5:2181 --delete \
                --group GROUBNAME
   ```

3. 重设消费者组位移

   ```shell
   #最早处
   ./kafka-consumer-groups.sh --bootstrap-server 172.17.0.5:9092 \
                --group GROUPNAME \
                --reset-offsets \
                --all-topics \
                --to-earliest \
                --execute
   #最新处
   ./kafka-consumer-groups.sh --bootstrap-server 172.17.0.5:9092 \
                --group GROUPNAME \
                --reset-offsets \
                --all-topics \
                --to-latest \
                --execute
   #某个位置
   ./kafka-consumer-groups.sh --bootstrap-server 172.17.0.5:9092 \
                --group GROUPNAME \
                --reset-offsets \
                --all-topics \
                --to-offset 3 \
                --execute
   #某个时间之后得最早位移
   ./kafka-consumer-groups.sh --bootstrap-server 172.17.0.5:9092 \
                --group GROUPNAME \
                --reset-offsets \
                --all-topics \
                --to-datetime 2019-09-15T00:00:00.000
   ```



# 常见问题及解决

## 4.1 消息堆积



