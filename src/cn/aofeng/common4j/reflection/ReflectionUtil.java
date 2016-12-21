package cn.aofeng.common4j.reflection;

import java.lang.reflect.Constructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 反射操作常用方法。
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public class ReflectionUtil {

    private final static Logger _logger = LoggerFactory.getLogger(ReflectionUtil.class);
    
    private ReflectionUtil() {
        // nothing
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
            Constructor<T> constructor = listenerClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            listener = constructor.newInstance();
        } catch (ClassNotFoundException e) {
            _logger.error( String.format("could not found class:%s", className), e);
        } catch (Exception e) {
            _logger.error( String.format("create instance for class:%s fail", className), e);
        }
        
        return listener;
    }
    
    /**
     * 生成指定完整类名的实例。
     * 
     * @param className 完整类名
     * @param parameterTypes 构造参数类型数组
     * @param initargs 调用构造方法生成实例时作为参数传给构造方法的对象数组
     * @return 类实例。如果找不到指定的类名，将返回false。
     */
    @SuppressWarnings("unchecked")
    public static <T> T createInstance(String className, 
            Class<?>[] parameterTypes, Object[] initargs) {
        if (null == className ) {
            return null;
        }
        
        T listener = null;
        try {
            Class<T> listenerClass = (Class<T>) Class.forName(className);
            Constructor<T> constructor = listenerClass.getDeclaredConstructor(parameterTypes);
            constructor.setAccessible(true);
            listener = constructor.newInstance(initargs);
        } catch (ClassNotFoundException e) {
            _logger.error( String.format("could not found class:%s", className), e);
        } catch (Exception e) {
            _logger.error( String.format("create instance for class:%s fail", className), e);
        }
        
        return listener;
    }

}
