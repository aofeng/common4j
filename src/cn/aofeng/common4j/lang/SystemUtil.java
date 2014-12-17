package cn.aofeng.common4j.lang;

/**
 * 与环境和系统相关的实用方法集合。
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public class SystemUtil {

    /**
     * 获取当前操作系统的换行符。
     * 
     * @return 当前操作系统的换行符。
     */
    public static String getEndLine() {
        return System.getProperty("line.separator");
    }

}
