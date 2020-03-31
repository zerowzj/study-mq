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

4. 目录

   - bin
   - conf
   - lib
   - log

## 1.2 启停

### 1.2.1 脚本启动

1. 进入zookeeper3.6.0/bin目录，执行以下脚本

   ```shell
   #服务端
   ./zkServer.sh ../zoo.cfg start/stop/restart
   
   #客户端
   ./zkCli.sh
   ```

### 1.2.2 自动启动

1. 12312

   ```
   
   ```

   



# 2. 配置