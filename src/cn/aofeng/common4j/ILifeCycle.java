package cn.aofeng.common4j;

/**
 * 组件生命周期定义。提供初始化和销毁两个方法。
 * 
 * @author <a href="mailto:nieyong@ucweb.com">聂勇</a>
 */
public interface ILifeCycle {

    /**
     * 初始化资源。
     */
    public void init();
    
    /**
     * 释放资源。
     */
    public void destroy();

}
