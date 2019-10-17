package com.ding.study.concurrent.jkjuc.juc16Semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 *
 16 Semaphore :如何快速实现一个限流器
 PV 信号量 semaphore

 Semaphore 现在普遍翻译为信号量，以前也曾被翻译成信号灯，因为类似现实生活里
 的红绿灯，车辆能不能通行，要看是不是绿灯。同样在编程世界里，线程能不能执行，也要看
 信号量是不是允许。

 信号量是由大名鼎鼎的计算机科学家迪杰斯特拉DIJKSTRA于1965年提出，在这之后的15年
 ，信号量一直都是并发编程领域的终结者，知道1980年管程被提出来，我们才有了第二选
 则。目前几乎所有支持并发编程的语言都支持信号量机制，所以学号信号量还是很有必要的。

 下面我们首先介绍信号量模型，之后介绍如何使用信号量，最后我们再用信号量来实现一个限流器。

 信号量模型还是很简单的，可以简单概况为：一个计数器、一个等待队列，三个方法（init、up、down）。在
 信号量模型里，计数器和等待队列对外是透明的，所以只能通过信号量模型提供的三个方法来访问它
 们，这三个方法分别是：init、down和up。你可以结

 这三个方法详细的语义具体如下所示
 init:设置计数器的初始值。
 down:计数器的值减一；如果此时计数器的值小于0，则当前线程被阻塞，否则当前线程可以继续执行。
 up：计数器的值加一；如果此时计数器的值小于或者等于0，则唤醒等待队列中的一个线程，并将其从等待队列中移除。

 这里提到的init、down、up三个方法都是原子性的，并且这个原子性是由信号量模型的实现方保证的。
 在javasdk里面，信号量是由模型javauitl.concurrent.Symaphore实现的
 ，Semaphore这个类能够保证这三个方法都是原子操作。

 semaphore.acquire 减一
 semphore. release 加一

 这里再插一句，信号量模型里面，down up这两个操作历史上最早称为P操作和V操作，
 所以信号量模型也被称为PV原语。另外，还有些人喜欢用semWait和semSignal来称呼他们
 ，虽然叫法不同，但是语义都是相同的。在javaSDK并发包里，down和up对应的则是
 acquire和release()


 如何使用信号量
 通过上文，你应该发现信号量的模型还是很简单的，那具体该如何使用呢？其实你想想红绿灯
 就可以了。十字路口的红绿灯可以控制交通，得益于他的一个关键规则：车辆在通过路口前必须
 先检查是否是绿灯，只有绿灯才能通过。这个规则和我前面提到的锁规则是不是很类似。

 其实，信号量使用的也是类似的。这里我们还是用累加器的例子来说明信号量的使用吧。在累加器
 的利器里。count+1的操作是个临界区，值运行一个线程执行，也即是说保证互斥。那这种
 情况用信号量怎么控制呢

 其实很简单，就像我们用互斥锁一样，只需要在进入临界区之前执行一个down操作，推出临界
 区之前执行一下up操作就可以了。下面是java代码的示例，acquire就是信号量里的down
 操作，release就是信号量里的up操作o

 下面我们再来分析一下，信号量是如何保证互斥的。假设两个线程T1和T2同事访问addone方法
 ，当它们同事调用acquire的时候，由于acquire是一个原子操作，所以只能由一个
 线程把信号量里的计数器减为0，另外一个线程T2则是将计数器减为-1。对于线程
 T1，信号量里面的计数器的值是0，大于等于0，所以线程T1会继续执行；对于线程T2，信号量
 里面的计数器的值是-1，晓宇0，按照信号量模型里对down操作的描述，线程T2将被阻塞。
 所以此时只有线程T1会进入临界区执行count+1
 当线程T1执行release操作，也就是up操作的时候，信号量里计数器的值是-1，加1之后的值是0，
 小于等于0，按照信号量模型里对up操作的描述，此时等待队列中的T2将会被唤醒。
 于是T2在T1执行完临界区代码之后才获得了进入临界区执行的机会，从而保证了互斥性。


 快速实现一个限流器
 上面的例子，我们用信号量实现了一个最简单的互斥锁功能。估计你会觉得奇怪，既然有java
 SDK里面提供的lcok，为啥还要提供一个Semaphore其实实现一个互斥锁，仅仅是
 semaphore的部分功能，semaphore还有一个功能是lcok不容易实现的，那就是
 semaphore可以允许多个线程访问一个临界区。

 现实中还有这种需求？有的。比较常见的需求就是我们工作中遇到的各种池化资源，例如
 连接池、对象池、线程池等等。其中，你可可能最熟悉数据库连接池，在同一时刻，一定是允许多个线程
 同时使用连接池的，当然，每个连接在被释放前，是不允许其他线程使用的。

 其实前不久，我在工作中也遇到了一个对象池的需求。所谓对象池呢，值得是一次性创建出N个对象
 ，之后所有线程重复利用这N个对象，当然对象在被释放前，也是不允许其他线程使用的。
 对象池，可以用list保存示例对象，这个很简单。但管家你是限流器的设计，这里的限流，指
 的是不允许多余N个线程同时进入临界区。那如何快读实现一个这样的限流器呢？这种场景，
 我立刻就想到了信号量的解决方案。


 信号量的计数器，在上面的例子中，我们设置成了1，这个1表示只允许一个线程进入临界区，
 但如果我们把计数器的值设置成对象池里对象的个数N，就能完美解决对象池的限流问题了。


 我们用一个list来保持对象实例，用semaphore 实现限流器。关键的代码是objpool里面的
 exec方法，这个方法里面实现了限流的功能。在这个方法里面，我们首先调用acquire方法
 与之匹配的是在finally里面调用了release方法，假设对象池的大小是10，信号量的计数器
 初始化为10，那么前10个线程调用aquire方法，都能继续支持，相当于通过了信号灯，
 而其他线程则会阻塞在acquire方法上。对于通过信号灯的线程，我们为每个线程分配了一个
 对象T这个分配工作时通过pool.remove实现的，分配完之后会执行一个回调函数hunc，
 而函数的参数正是前面分配的对象t；执行完回调函数之后，他们就会释放对象 这个释放工作时通过pooladd实现的
 同时调用release方法来更新信号量的计数器。如果此时
 信号量里计数器的值小于等于0，那么说明有线程在等待，此时会自动唤醒等待的线程。

 简言之，使用信号量，我们可以轻松实现一个限流器，使用起来还是非常简单的。

 总结:

 信号量在java语言里面名气并不算大，但是在其他语言里却是很有知名度的。java在并发编程
 领域走的很快，重点支持的还是管程模型。管程模型理论上解决了信号量模型的一些不足，主要
 提现在易用性和工程方面，例如用信号量解决我们曾经提到过的阻塞队列问题，就比管程模型
 麻烦很多，你如果感兴趣，可以课下了解尝试一下。


 * @author daniel 2019-10-12 0012.
 */
public class MySemaphore<T> {
    /**
     * 信号量 PV
     */
    public Semaphore semaphore = new Semaphore(10);

    /**
     * 最多只能放入10个，再多就等待
     */
    public List<T> list = new ArrayList<>();

    /**
     * 放入数据并且减一  最多放入10个
     *
     * @param t
     * @return
     */
    public boolean up(T t) {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t + "--up--" + semaphore.availablePermits());
        list.add(t);
        return true;
    }

    public T down() {

        semaphore.release();
        if (list.size() == 0) {
            System.out.println("acquire--" + list.size());
            return null;
        }

        T t = list.get(list.size() - 1);
        list.remove(t);
        System.out.println(t + "--down--" + semaphore.availablePermits());

        return t;
    }


    public static void main(String[] args) throws Exception {
        MySemaphore semaphore = new MySemaphore();
        //只能放入10个
        for (int i = 0; i < 20; i++) {
            //  System.out.println(i + "--" + semaphore.semaphore.availablePermits());
            String t = i + "";
            new Thread(() -> semaphore.up(t)).start();
        }

        //拿出后再多拿出
        for (int i = 0; i < 10; i++) {
            //   System.out.println(i + "--" + semaphore.semaphore.availablePermits());
            new Thread(() -> semaphore.down()).start();
        }

        Thread.sleep(111);
        System.out.println("-555-" + semaphore.semaphore.availablePermits() + ":" + semaphore.list);


        System.out.println("TT开始:" + tt);
        System.out.println("TT RUN:" + testFinally(5));
        System.out.println("TT end:" + tt);

    }

    private static Integer tt = 10;

    public static Integer testFinally(Integer i) {
        try {
            tt -= i;
            return tt;
        } finally {
            tt += i;
            System.out.println(tt);
        }


    }
}
