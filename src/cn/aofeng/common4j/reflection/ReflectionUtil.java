package cn.aofeng.common4j.reflection;

import org.apache.log4j.Logger;

/**
 * 反射操作常用方法。
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public class ReflectionUtil {

    private final static Logger logger = Logger.getLogger(ReflectionUtil.class);
    
    private ReflectionUtil() {
        
    }
    
    /**
     * 生成指定完整类名的实例。
     * 
     * @param className 完整类名
     * @return 类实例。如果找不到指定的类名，将返回false。
     */
    @SuppressWarnings("unchecked")
    public static <T> T createInstance(String className) {
        if (null == className ) {
            return null;
        }
        
        T listener = null;
        try {
            Class<T> listenerClass = (Class<T>) Class.forName(className);
            listener = listenerClass.newInstance();
        } catch (ClassNotFoundException e) {
            logger.error( String.format("could not found class:%s", className), e);
        } catch (InstantiationException e) {
            logger.error( String.format("create instance for class:%s fail", className), e);
        } catch (IllegalAccessException e) {
            logger.error( String.format("create instance for class:%s fail", className), e);
        }
        
        return listener;
    }

}
