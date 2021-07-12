

**I.安装和启动arthas**
https://arthas.aliyun.com/doc/quick-start.html


curl -O https://arthas.aliyun.com/arthas-boot.jar
java -jar arthas-boot.jar

可用:
java -jar arthas-boot.jar --repo-mirror aliyun --use-http


执行该程序的用户需要和目标进程具有相同的权限。
比如以admin用户来执行：sudo su admin && java -jar arthas-boot.jar 或 sudo -u admin -EH java -jar arthas-boot.jar。


**II.选择应用(启动后)**
选择应用对应下标

[dingsai88@localhost arthas]$ java -jar arthas-boot.jar --repo-mirror aliyun --use-http
[INFO] arthas-boot version: 3.5.2
[INFO] Found existing java process, please choose one and input the serial number of the process, eg : 1. Then hit ENTER.
* [1]: 2413 math-game.jar
  1
  [INFO] arthas home: /home/dingsai88/.arthas/lib/3.5.2/arthas
  [INFO] Try to attach process 2413
  [INFO] Attach process 2413 success.
  [INFO] arthas-client connect 127.0.0.1 3658
  ,---.  ,------. ,--------.,--.  ,--.  ,---.   ,---.                           
  /  O  \ |  .--. ''--.  .--'|  '--'  | /  O  \ '   .-'                          
  |  .-.  ||  '--'.'   |  |   |  .--.  ||  .-.  |`.  `-.                          
  |  | |  ||  |\  \    |  |   |  |  |  ||  | |  |.-'    |                         
  `--' `--'`--' '--'   `--'   `--'  `--'`--' `--'`-----'


wiki       https://arthas.aliyun.com/doc                                        
tutorials  https://arthas.aliyun.com/doc/arthas-tutorials.html                  
version    3.5.2                                                                
main_class                                                                      
pid        2413                                                                 
time       2021-07-12 00:16:19





测试应用
curl -O https://arthas.aliyun.com/math-game.jar
java -jar math-game.jar

https://github.com/alibaba/arthas/blob/master/math-game/src/main/java/demo/MathGame.java


**III.查看基础信息 dashboard（仪表盘）**

[arthas@2413]$
[arthas@2413]$
[arthas@2413]$ dashboard
ID  NAME                       GROUP        PRIORITY STATE    %CPU     DELTA_TI TIME    INTERRUP DAEMON   
-1  VM Periodic Task Thread    -            -1       -        0.0      0.000    0:12.78 false    true     
1   main                       main         5        TIMED_WA 0.0      0.000    0:1.523 false    false    
-1  VM Thread                  -            -1       -        0.0      0.000    0:1.337 false    true     
-1  C1 CompilerThread0         -            -1       -        0.0      0.000    0:1.269 false    true     
38  arthas-NettyHttpTelnetBoot system       5        RUNNABLE 0.0      0.000    0:0.410 false    true     
7   Attach Listener            system       9        RUNNABLE 0.0      0.000    0:0.170 false    true     
31  arthas-NettyHttpTelnetBoot system       5        RUNNABLE 0.0      0.000    0:0.098 false    true     
3   Finalizer                  system       8        WAITING  0.0      0.000    0:0.021 false    true     
39  arthas-command-execute     system       5        TIMED_WA 0.0      0.000    0:0.019 false    true     
2   Reference Handler          system       10       WAITING  0.0      0.000    0:0.010 false    true     
40  Timer-for-arthas-dashboard system       5        RUNNABLE 0.0      0.000    0:0.009 false    true     
32  arthas-NettyWebsocketTtyBo system       5        RUNNABLE 0.0      0.000    0:0.005 false    true     
Memory                 used   total   max    usage   GC                                                   
heap                   34M    77M     121M   28.19%  gc.copy.count              35                        
eden_space             2M     21M     33M    6.58%   gc.copy.time(ms)           395                       
survivor_space         0K     2688K   4288K  0.00%   gc.marksweepcompact.count  5                         
tenured_gen            32M    53M     84M    38.24%  gc.marksweepcompact.time(m 303                       
nonheap                29M    40M     118M   25.06%  s)                                                   
code_cache             1M     2M      32M    5.62%                                                        
perm_gen               16M    16M     64M    25.13%                                                       
perm_gen_[shared-ro]   4M     10M     10M    48.28%                                                       
perm_gen_[shared-rw]   6M     12M     12M    57.17%                                                       
direct                 0K     0K      -      0.00%                                                        
mapped                 0K     0K      -      0.00%                                                        
Runtime                                                                                                   
os.name                                              Linux                                                
os.version                                           2.6.32-431.el6.i686                                  
java.version                                         1.7.0_71                                             
java.home                                            /usr/java/jdk1.7.0_71/jre                            
systemload.average                                   0.02                                                 
processors                                           1                                                    
timestamp/uptime                                     Mon Jul 12 00:17:36 PDT 2021/998s    



**IV.其它信息查看**

**1.查看线程**
[arthas@2413]$ thread 1 | grep 'main('

"main" Id=1 TIMED_WAITING
at java.lang.Thread.sleep(Native Method)
at java.lang.Thread.sleep(Thread.java:340)
at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:360)
at demo.MathGame.main(MathGame.java:17)



**2.反编译：jad demo.MathGame**

[arthas@2413]$ jad demo.MathGame

ClassLoader:                                                                                              
+-sun.misc.Launcher$AppClassLoader@1bd0dd4                                                                
+-sun.misc.Launcher$ExtClassLoader@d70d7a

Location:                                                                                                 
/home/dingsai88/arthas/math-game.jar

       /*
        * Decompiled with CFR.
        */
       package demo;
       
       import java.util.ArrayList;
       import java.util.List;
       import java.util.Random;
       import java.util.concurrent.TimeUnit;
       

**3.查看方法返回值:**
watch demo.MathGame primeFactors returnObj

watch 包.类 方法 returnObj

public List<Integer> primeFactors(int number) {
    }


**4.退出 arthas**

如果只是退出当前的连接，
可以用quit或者exit命令
Attach到目标进程上的arthas还会继续运行，端口会保持开放，下次连接时可以直接连接上。

如果想完全退出arthas，可以执行stop命令。



**相关命令:**
https://arthas.aliyun.com/doc/advanced-use.html




