package cn.aofeng.common4j.io;

import java.io.Closeable;
import java.io.IOException;

/**
 * I/O实用工具方法集合。
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public class IOUtil {

    public static void closeQuietly(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                // nothing
            }
        }
    }

}
