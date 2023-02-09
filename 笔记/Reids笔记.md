```
redis-server.exe redis.windows.conf
```

```
redis-cli.exe -h 127.0.0.1 -p 6379
```

## Redis简介

> Redis是什么？

Reids（Remote Dictionary Server），远程字典服务

 是一个开源的使用ANSI [C语言](https://baike.baidu.com/item/C语言)编写、支持网络、可基于内存亦可持久化的日志型、Key-Value[数据库](https://baike.baidu.com/item/数据库/103728)，并提供多种语言的API。 

 redis会周期性的把更新的数据写入磁盘或者把修改操作写入追加的记录文件，并且在此基础上实现了master-slave(主从)同步。 

免费和开源！当下最热门的NoSQL技术之一！也被称之为结构化数据库！

> Redis能干嘛？

1. 内存存储、持久化，内存中是断电即失、所以持久化很重要（rdb、aof）
2. 效率高，可用于高速缓存
3. 发布订阅系统
4. 地图信息分析
5. 计时器、计数器（浏览量）
6. .......

> 特性

1. 多样的数据类型
2. 持久化
3. 集群
4. 事务



## Redis五大数据类型

### String（字符串）

```
##########################
基本操作命令
127.0.0.1:6379> set key1 v1		#设置值
OK
127.0.0.1:6379> get key1		#获取值
"v1"
127.0.0.1:6379> keys *			#获取所有的key
1) "key1"
127.0.0.1:6379> exists key1		#判断某一个key是否存在
(integer) 1
127.0.0.1:6379> append key1 "hello"		#追加字符串，如果当前key不存在，就相当于set key
(integer) 7
127.0.0.1:6379> get key1
"v1hello"
127.0.0.1:6379> strlen key1		#获取字符串的长度
(integer) 7
127.0.0.1:6379> append key1 ",wanghong"
(integer) 16
127.0.0.1:6379> strlen key1
(integer) 16
127.0.0.1:6379> get key1
"v1hello,wanghong"

###############################
#	i++
#	步长 i+=
127.0.0.1:6379> set views 0		#初始值为0
OK
127.0.0.1:6379> get views
"0"
127.0.0.1:6379> incr views		#自增1
(integer) 1
127.0.0.1:6379> get views
"1"
127.0.0.1:6379> decr views		#自减1
(integer) 0
127.0.0.1:6379> decr views		#自减1
(integer) -1
127.0.0.1:6379> get views
"-1"
127.0.0.1:6379> incrby views 10		#设置步长，指定增量10
(integer) 9
127.0.0.1:6379> incrby views 10
(integer) 19
127.0.0.1:6379> decrby views 5		#设置步长，指定自减量5
(integer) 14
127.0.0.1:6379> decrby views 5
(integer) 9

############################
#	字符串范围 range
127.0.0.1:6379> set key1 "hellowanghong"	#设置key
OK
127.0.0.1:6379> get key1
"hellowanghong"
127.0.0.1:6379> getrange key1 0 3			#截取字符串[0，3]
"hell"
127.0.0.1:6379> getrange key1 0 -1			#获取全部的字符串 和 getkey 一样
"hellowanghong"
#	替换
127.0.0.1:6379> set key2 abcdefg
OK
127.0.0.1:6379> get key2
"abcdefg"
127.0.0.1:6379> setrange key2 2 xxx			#替换指定位置开始的字符串
(integer) 7
127.0.0.1:6379> get key2
"abxxxfg"

#####################################
#setex(set with expire)		#设置过期时间
#setnx(set if not exist)	#不存在才设置值
127.0.0.1:6379> setex key3 30 hello		#设置key3的值为hello，30秒后过期
OK
127.0.0.1:6379> ttl key3
(integer) 24
127.0.0.1:6379> get key3
"hello"
127.0.0.1:6379> setnx mykey "redis"		#如果mykey不存在，创建mykey
(integer) 1
127.0.0.1:6379> keys *
1) "key2"
2) "mykey"
3) "key1"
127.0.0.1:6379> ttl key3
(integer) -2
127.0.0.1:6379> setnx mykey "mongodb"	#如果mykey存在，则创建失败
(integer) 0
127.0.0.1:6379> get mykey
"redis"
#####################################
#mset
#mget
127.0.0.1:6379> mset k1 v1 k2 v2 k3 v3		#同时设置多个值
OK
127.0.0.1:6379> keys *
1) "k3"
2) "k1"
3) "k2"
127.0.0.1:6379> mget k1 k2 k3				#同时获取多个值
1) "v1"
2) "v2"
3) "v3"
127.0.0.1:6379> msetnx k1 v1 k4 v4			#msetnx是一个原子性的操作，要么一起成功，要么一起失败
(integer) 0
127.0.0.1:6379> get k4
(nil)

####################################
#	对象
set user:1 {name:zhangsan,age:3}		#	设置一个user:1 对象，值为json字符来保存一个对象
127.0.0.1:6379> set user:1 {name:zhangsan,age:3}
OK
127.0.0.1:6379> get user:1
"{name:zhangsan,age:3}"

#	key的巧妙设计：	user:{id}:{filed},在redis中完全可以

127.0.0.1:6379> mset user:1:name zhangsan user:1:age 2
OK
127.0.0.1:6379> mget user:1:name user:1:age
1) "zhangsan"
2) "2"

##################
getset    #先get然后再set
127.0.0.1:6379> getset db redis		# 如果不存在值，则返回nil
(nil)
127.0.0.1:6379> get db		
"redis"
127.0.0.1:6379> getset db mongo		#	如果存在值，获取原来的值，并设置新的值
"redis"
127.0.0.1:6379> get db
"mongo"

```

String类型的使用场景：value除了是字符串还可以是数字

* 计数器
* 统计多单位的数量      uid:userid:follow  0  increate
* 粉丝数
* 对象缓存存储



### List（列表）

基本的数据类型，列表

在redis中，我们可以吧list玩成	栈，队列，阻塞队列

所有的list命令都是L开头的

```
############################
127.0.0.1:6379> lpush list one		#将一个值或者多个值，插入到列表头部（左）
(integer) 1
127.0.0.1:6379> lpush list two
(integer) 2
127.0.0.1:6379> lpush list three
(integer) 3
127.0.0.1:6379> lrange list 0 -1	#获取list中的值
1) "three"
2) "two"
3) "one"
127.0.0.1:6379> lrange list 0 1		#通过区间获取具体的值
1) "three"
2) "two"
127.0.0.1:6379> rpush list right	#将一个值或者多个值，插入到列表尾部（右）
(integer) 4
127.0.0.1:6379> lrange list 0 -1
1) "three"
2) "two"
3) "one"
4) "right"

###################################
#lpop
#rpop
127.0.0.1:6379> lrange list 0 -1
1) "three"
2) "two"
3) "one"
4) "right"
127.0.0.1:6379> lpop list		#移除list的第一个元素（左）
"three"	
127.0.0.1:6379> rpop list		#移除list的最后一个元素（右）
"right"
127.0.0.1:6379> lrange list 0 -1
1) "two"
2) "one"

#####################################
#lindex
127.0.0.1:6379> lrange list 0 -1
1) "two"
2) "one"
127.0.0.1:6379> lindex list 0		#通过下标获取list中的某一个值
"two"
127.0.0.1:6379> lindex list 1
"one"

#######################################
#llen
127.0.0.1:6379> lpush list one
(integer) 1
127.0.0.1:6379> lpush list two
(integer) 2
127.0.0.1:6379> lpush list there
(integer) 3
127.0.0.1:6379> llen list			#返回列表长度
(integer) 3

#######################################
#移除指定的值
#lrem
127.0.0.1:6379> lrange list 0 -1
1) "three"
2) "four"
3) "three"
4) "two"
127.0.0.1:6379> lrem list 1 three		#移除list集合中指定个数的value，精确匹配
(integer) 1
127.0.0.1:6379> lrange list 0 -1
1) "four"
2) "three"
3) "two"
127.0.0.1:6379> lpush list three
(integer) 4
127.0.0.1:6379> lrem list 2 three		#移除两个three
(integer) 2
127.0.0.1:6379> lrange list 0 -1
1) "four"
2) "two"

#######################################
#trim修剪： list截断
127.0.0.1:6379> rpush mylist "hello"
(integer) 1
127.0.0.1:6379> rpush mylist "hello2"
(integer) 2
127.0.0.1:6379> rpush mylist "hello3"
(integer) 3
127.0.0.1:6379> rpush mylist "hello4"
(integer) 4
127.0.0.1:6379> ltrim mylist 1 2		#通过下标截取指定的长度，这个list已经被改变，只剩下截取的元素
OK
127.0.0.1:6379> lrange mylist 0 -1
1) "hello2"
2) "hello3"

#######################################
#rpoplpush	移除列表的最后一个元素，并将它移动到新的列表中
127.0.0.1:6379> rpush mylist "hello"
(integer) 1
127.0.0.1:6379> rpush mylist "hello1"
(integer) 2
127.0.0.1:6379> rpush mylist "hello2"
(integer) 3
127.0.0.1:6379> rpoplpush mylist myotherlist	#移除最后一个元素到myotherlist
"hello2"
127.0.0.1:6379> lrange mylist 0 -1
1) "hello"
2) "hello1"
127.0.0.1:6379> lrange myotherlist 0 -1
1) "hello2"

#######################################
#lset	将列表中指定下标的值替换为另一个值，更新操作
127.0.0.1:6379> exists list				#判断列表是否存在
(integer) 0
127.0.0.1:6379> lset list 0 item		#如果不存在列表去更新就会报错
(error) ERR no such key
127.0.0.1:6379> lpush list value1
(integer) 1
127.0.0.1:6379> lrange list 0 -1			
1) "value1"
127.0.0.1:6379> lset list 0 item		#如果存在，更新当前下标的值
OK
127.0.0.1:6379> lrange list 0 0
1) "item"
127.0.0.1:6379> lset list 1 other		#如果下标不存在，也会报错
(error) ERR index out of range

###############################################
#linsert		将某个具体的value插入到列表中某个元素的前面或者后面
127.0.0.1:6379> rpush mylist hello
(integer) 1
127.0.0.1:6379> rpush mylist world
(integer) 2
127.0.0.1:6379> linsert mylist before world other
(integer) 3
127.0.0.1:6379> lrange mylist 0 -1
1) "hello"
2) "other"
3) "world"
127.0.0.1:6379> linsert mylist after world other
(integer) 4
127.0.0.1:6379> lrange mylist 0 -1
1) "hello"
2) "other"
3) "world"
4) "other"
```

>小结

* 它实际上是一个链表，before Node after，left，right都可以插入值
* 如果key不存在，创建新的链表
* 如果key存在，新增内容
* 如果移除了所有值，空链表，也代表不存在
* 在两边插入或者改动值，效率最高！中间元素，相对来说效率会低一点

消息排队！消息队列（Lpush   Rpop），栈（Lpush  Lpop）



### Set（集合）

set中的值是不能重复的

```
###############################################
127.0.0.1:6379> sadd myset hello		#set集合中添加元素
(integer) 1
127.0.0.1:6379> sadd myset wanghong
(integer) 1
127.0.0.1:6379> sadd myset home
(integer) 1
127.0.0.1:6379> smembers myset			#查看指定set的所有值
1) "wanghong"
2) "home"
3) "hello"
127.0.0.1:6379> sismember myset hello	#判断某一个值是不是在set集合中，存在返回1
(integer) 1
127.0.0.1:6379> sismember myset nihao	#不存在返回0
(integer) 0
127.0.0.1:6379> scard myset				#获取set集合中的内容元素个数
(integer) 3
127.0.0.1:6379> srem myset hello		#移除set集合中的指定元素
(integer) 1

###############################################
#set无需不重复集合		抽随机元素
127.0.0.1:6379> srandmember myset		#随机抽取一个元素
"wanghong"
127.0.0.1:6379> srandmember myset 2		#随机抽取指定个数的元素
1) "class"
2) "home"

###############################################
#spop	随机删除元素
127.0.0.1:6379> spop myset
"wanghong"
127.0.0.1:6379> spop myset
"class"

###############################################
将一个指定的key移动到另一个集合中
127.0.0.1:6379> sadd myset2 lisi
(integer) 0
127.0.0.1:6379> smove myset myset2 wanghong		#将一个指定的值，移动到另一个集合中
(integer) 1
127.0.0.1:6379> smembers myset
1) "class"
2) "home"
127.0.0.1:6379> smembers myset2
1) "wanghong"
2) "lisi"

###############################################
微博，B站，共同关注！（并集）
数字集合类：
- 差集
- 并集
- 交集
127.0.0.1:6379> smembers key1
1) "c"
2) "b"
3) "a"
127.0.0.1:6379> smembers key2
1) "c"
2) "e"
3) "d"
127.0.0.1:6379> sdiff key1 key2		#差集，第一个与第二个不同元素
1) "a"
2) "b"
127.0.0.1:6379> sinter key1 key2	#交集，共同好友
1) "c"
127.0.0.1:6379> sunion key1 key2	#并集
1) "c"
2) "e"
3) "a"
4) "b"
5) "d"
```



### Hash（哈希）

Map集合，key-map！这时候这个值是一个map集合！本质和String类型没有太大区别，还是一个简单的key-value！

```

###############################################
127.0.0.1:6379> hset myhash field1 wanghong			#set一个具体的key-value
(integer) 1
127.0.0.1:6379> hget myhash field1					#获取一个字段值
"wanghong"
127.0.0.1:6379> hmset myhash field1 hello field2 world		#set多个key-value
OK
127.0.0.1:6379> hmget myhash field1 field2			#获取多个字段值
1) "hello"
2) "world"
127.0.0.1:6379> hgetall myhash						#获取全部的数据
1) "field1"
2) "hello"
3) "field2"
4) "world"

###############################################
127.0.0.1:6379> hdel myhash field1			#删除hash指定key字段！对应的value值也消失了
(integer) 1
127.0.0.1:6379> hgetall myhash
1) "field2"
2) "world"
127.0.0.1:6379> hlen myhash					#获取hash表的字段数量
(integer) 1

###############################################
127.0.0.1:6379> hexists myhash field1		#判断hash中指定字段是否存在
(integer) 1
127.0.0.1:6379> hexists myhash field3
(integer) 0

###############################################
127.0.0.1:6379> hkeys myhash			#只获取所有的field
1) "field2"
2) "field1"
127.0.0.1:6379> hvals myhash			#只获取所有的值
1) "wanghong"
2) "hello"

###############################################
incr		decr
127.0.0.1:6379> hset myhash field3 5	
(integer) 1
127.0.0.1:6379> hincrby myhash field3 1		#指定增量+1
(integer) 6
127.0.0.1:6379> hincrby myhash field3 -1	#指定增量-1
(integer) 5
127.0.0.1:6379> hsetnx myhash field4 hello	#如果不存在则可以设置
(integer) 1
127.0.0.1:6379> hsetnx myhash field4 world	#如果存在则不能设置
(integer) 0
```

hash可以保存变更的数据 user name age，尤其是用户信息之类的，经常变动的信息！hash更适合于对象的存储，String更加适合字符串的存储！



### Zset（有序集合）

在set的基础上，增加了一个值，set k1 v1   zset k1 score1 v1

```
###############################################
127.0.0.1:6379> zadd myset 1 one			#插入一个值
(integer) 1
127.0.0.1:6379> zadd myset 2 two 3 three	#插入多个值
(integer) 2
127.0.0.1:6379> zrange myset 0 -1
1) "one"
2) "two"
3) "three"

###############################################
排序实现
127.0.0.1:6379> zadd money 2000 lisi		#添加三个用户和工资
(integer) 1
127.0.0.1:6379> zadd money 3000 zhangsan
(integer) 1
127.0.0.1:6379> zadd money 30000 wanghong
(integer) 1
#zrangebyscore key min max
127.0.0.1:6379> zrangebyscore money -inf +inf	#显示全部用户
1) "lisi"
2) "zhangsan"
3) "wanghong"
127.0.0.1:6379> zrangebyscore money -inf +inf withscores	#显示全部用户，并且附带工资
1) "lisi"
2) "2000"
3) "zhangsan"
4) "3000"
5) "wanghong"
6) "30000"
127.0.0.1:6379> zrangebyscore money -inf 5000 withscores	#显示工资小于5000的升序排列
1) "lisi"
2) "2000"
3) "zhangsan"
4) "3000"

###############################################
#zrem	移除指定元素
127.0.0.1:6379> zrange money 0 -1
1) "lisi"
2) "zhangsan"
3) "wanghong"
127.0.0.1:6379> zrem money lisi			#移除有序集合中指定元素
(integer) 1
127.0.0.1:6379> zrange money 0 -1
1) "zhangsan"
2) "wanghong"
127.0.0.1:6379> zcard money				#获取有序集合中的个数
(integer) 2

###############################################
127.0.0.1:6379> zrangebyscore money -inf +inf withscores
1) "lisi"
2) "3000"
3) "zhangsan"
4) "3000"
5) "wanghong"
6) "30000"

127.0.0.1:6379> zcount money 1 3000		#获取指定区间的成员数量
(integer) 2
127.0.0.1:6379> zcount money 1 2000
(integer) 0
```

重要：多看官方文档

案例思路：set 排序  存储班级成绩表，工资表排序

普通消息	1， 重要消息	2，带权重进行判断

排行榜应用实现，取Top N测试



## 三种特殊数据类型

### geospatial地理位置

朋友的定位，附近的人，打车距离计算？

Redis的Geo在Redis3.2版本就推出了！这个功能可以推算地理位置的信息，两地之间的距离，方圆之内的热门

六个命令：https://www.php.cn/manual/view/36388.html



```
###############################################
#geoadd		添加地理位置
#规则：两极无法添加，一般会下载城市地理数据，通过java程序一次性导入
127.0.0.1:6379> geoadd china:city 116.40 39.90 beijing
(integer) 1
127.0.0.1:6379> geoadd china:city 121.47 31.23 shanghai
(integer) 1
127.0.0.1:6379> geoadd china:city 106.50 29.53 chongqing 114.05 22.52 shenzhen
(integer) 2
127.0.0.1:6379> geoadd china:city 120.16 30.24 hangzhong 108.96 34.26 xian
(integer) 2

###############################################
#geopos		获取当前定位：一定是一个坐标值
127.0.0.1:6379> geopos china:city beijing
1) 1) "116.39999896287918091"
   2) "39.90000009167092543"
   
127.0.0.1:6379> geopos china:city beijing xian
1) 1) "116.39999896287918091"
   2) "39.90000009167092543"
2) 1) "108.96000176668167114"
   2) "34.25999964418929977"
127.0.0.1:6379>

###############################################
#geodist	获取两地之间的距离
#单位;	m：默认米	km：千米	mi：英里	ft：英尺
127.0.0.1:6379> geodist china:city beijing xian
"910056.5237"
127.0.0.1:6379> geodist china:city beijing xian km
"910.0565"

###############################################
#georadius	以给定的经度纬度为中心，找出某一半径内的元素
#附近的人，（获取所有附近的人的地址，定位！）通过半径来查询
#获取指定数量的人

127.0.0.1:6379> georadius china:city 110 30 1000 km		#以110，30这个经纬度为中心，寻找方圆1000km内的城市
1) "chongqing"
2) "xian"
3) "shenzhen"
4) "hangzhong"
127.0.0.1:6379> georadius china:city 110 30 500 km withdist		#显示到中心距离的位置
1) 1) "chongqing"
   2) "341.9374"
2) 1) "xian"
   2) "483.8340"
127.0.0.1:6379> georadius china:city 110 30 500 km withcoord	#显示他人的经纬度
1) 1) "chongqing"
   2) 1) "106.49999767541885376"
      2) "29.52999957900659211"
2) 1) "xian"
   2) 1) "108.96000176668167114"
      2) "34.25999964418929977"
127.0.0.1:6379> georadius china:city 110 30 500 km withcoord count 1	#筛选出指定数量的结果
1) 1) "chongqing"
   2) 1) "106.49999767541885376"
      2) "29.52999957900659211"
     
###############################################
#georadiusbymember	找出位于指定元素周围的其他元素
127.0.0.1:6379> georadiusbymember china:city beijing 1000 km
1) "beijing"
2) "xian"

###############################################
#geohash	将返回11位的geohash字符串
#将二维的经纬度转换为一维的字符串，字符串越接近，那么激励越近
127.0.0.1:6379> geohash china:city beijing chongqing
1) "wx4fbxxfke0"
2) "wm5xzrybty0"

###############################################
GEO底层的实现原理其实就是Zset！我们可以使用Zset命令来操作geo！

127.0.0.1:6379> zrange china:city 0 -1		#查看地图中全部元素
1) "chongqing"
2) "xian"
3) "shenzhen"
4) "hangzhong"
5) "shanghai"
6) "beijing"
127.0.0.1:6379> zrem china:city beijing		#移除地图中指定元素
(integer) 1
127.0.0.1:6379> zrange china:city 0 -1
1) "chongqing"
2) "xian"
3) "shenzhen"
4) "hangzhong"
5) "shanghai"

```



### Hyperloglog数据结构

```
什么是基数？（不重复的元素）
```

简介：Redis Hyperloglog 基数统计的算法

优点：占用的内存是固定的，2^64不同的元素的技术，只需要耗费12KB内存；

**网页的UV（一个人访问一个网站多次，但是还算做一个人！）**

传统的方式：set保存用户的id，然后就可以统计set中的元素数量作为标准判断！这种方式如果保存大量的用户id，就会比较麻烦！我们的目的是为了计数，而不是保存用户id；

0.81%容错率！统计UV任务，可以忽略不记!

```
127.0.0.1:6379> pfadd wh1 a b c d e f	#创建第一组元素
(integer) 1
127.0.0.1:6379> pfcount wh1
(integer) 6
127.0.0.1:6379> pfadd wh2 d e f g h j k		#创建第二组元素
(integer) 1
127.0.0.1:6379> pfcount wh2
(integer) 7
127.0.0.1:6379> pfmerge wh3 wh1 wh2		#合并两组元素，wh1 wh2 => wh3 并集
OK
127.0.0.1:6379> pfcount wh3		#查看并集的数量
(integer) 10
```

如果允许容错，那么一定可以使用Hyperloglog！

如果不允许容错，就用set或者自己的数据类型即可！



### bitmaps(位存储)

统计用户信息，活跃、不活跃！登录、未登录！打卡，365打卡  

Bitmaps位图，数据结构！都是操作二进制位来进行记录，就只有0和1两个状态！

365天 = 365 bit  1字节 = 8bit  46个字节左右

使用bitmap来记录 周一到周日的打卡 

```
127.0.0.1:6379> setbit sign 0 1
(integer) 0
127.0.0.1:6379> setbit sign 1 0
(integer) 0
127.0.0.1:6379> setbit sign 2 0
(integer) 0
127.0.0.1:6379> setbit sign 3 1
(integer) 0
127.0.0.1:6379> setbit sign 4 1
(integer) 0
127.0.0.1:6379> setbit sign 5 0
(integer) 0
127.0.0.1:6379> setbit sign 6 0
(integer) 0
127.0.0.1:6379>
```





## 事务

## Jedis

## SpringBoot

## Redis.conf详解

## Redis持久化

## Redis发布订阅

## Redis主从复制

## Redis缓存穿透和雪崩