# 1. 安装

## 1.1 安装

1. 配置JDK

2. 解压apache-zookeeper-3.6.0-bin.tar.gz到/usr/server/zookeeper3.6.0

   ```shell
   tar -zxvf apache-zookeeper-3.6.0-bin.tar.gz -C /usr/server/zookeeper3.6.0
   ```

3. 复制文件配置文件： zookeeper3.6.0/conf/zoo_sample.cfg

   ```shell
   cp zoo_sample.cfg zoo.cfg
   ```

## 1.2 目录

1. bin
2. conf
3. lib
4. logs

## 1.3 启停

### 1.3.1 脚本启动

1. 进入 zookeeper3.6.0/bin 目录，执行以下脚本

   ```shell
   #服务端
   ./zkServer.sh ../zoo.cfg start/stop/restart
   ./zkServer.sh stop
   ./zkServer.sh restart
   
   #客户端
   ./zkCli.sh
   ```

### 1.3.2 自动启动

1. 进入目录 /etc/init.d，新建zookeeper文件，添加以下内容

   ```shell
   #!/bin/sh
   #chkconfig:2345 20 90
#description:zookeeper
   #processname:zookeeper
   
   export JAVA_HOME=/usr/local/jdk1.8.0_162
   ZK_HOME=/usr/server/zookeeper3.6.0
   case $1 in
   start)
     #注意：前面加 sudo 会报JAVA_HOME未设置错误
     $ZK_HOME/bin/zkServer.sh start
     ;;
   stop)
     $ZK_HOME/bin/zkServer.sh stop
     ;;
   status)
     $ZK_HOME/bin/zkServer.sh status
     ;;
   restart)
     $ZK_HOME/bin/zkServer.sh restart
     ;;
   *)
     echo "Usage: nexus {start|stop|status|restart}"
     ;;
   esac
   ```
   
2. 文件赋权

   ```
   
   ```

3. 注册服务并开机启动

   ```shell
   chkconfig --add zookeeper
   ```



# 2. 配置

# 3. 集群





