package cn.aofeng.common4j.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程工厂。
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public class DefaultThreadFactory implements ThreadFactory {

    private static AtomicLong _count = new AtomicLong(1);
    
    private final static String THREAD_NAME_PRIFIX = "aofeng-thread";
    
    private ThreadGroup _group;
    
    public DefaultThreadFactory() {
        this(THREAD_NAME_PRIFIX);
    }
    
    public DefaultThreadFactory(String threadNamePrefix) {
        ThreadGroup root = getRootThreadGroup();
        _group = new ThreadGroup(root, threadNamePrefix);
    }
    
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(_group, r);
        thread.setName(THREAD_NAME_PRIFIX+"-"+_count.getAndIncrement());
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        if (Thread.NORM_PRIORITY != thread.getPriority()) {
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        
        return thread;
    }
    
    /**
     * @return 返回根线程组实例
     */
    private ThreadGroup getRootThreadGroup() {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        while (null != threadGroup.getParent()) {
            threadGroup = threadGroup.getParent();
        }
        
        return threadGroup;
    }

}
