package cn.aofeng.common4j.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
     * @param <T> 创建的实例类型
     */
    public static <T> T createInstance(String className) {
        return createInstance(className, null);
    }
    
    /**
     * 生成指定完整类名的实例。
     * 
     * @param className 完整类名
     * @param loader 加载类的{@link ClassLoader}
     * @return 类实例。如果找不到指定的类名，将返回false。
     * @param <T> 创建的实例类型
     */
    @SuppressWarnings("unchecked")
    public static <T> T createInstance(String className, ClassLoader loader) {
        if (null == className ) {
            return null;
        }
        
        T obj = null;
        try {
            Class<T> claz = null;
            if (null == loader) {
                claz = (Class<T>) Class.forName(className);
            } else {
                claz = (Class<T>) Class.forName(className, true, loader);
            }
            Constructor<T> constructor = claz.getDeclaredConstructor();
            constructor.setAccessible(true);
            obj = constructor.newInstance();
        } catch (ClassNotFoundException e) {
            _logger.error( String.format("could not found class:%s", className), e);
        } catch (Exception e) {
            _logger.error( String.format("create instance for class:%s fail", className), e);
        }
        
        return obj;
    }
    
    /**
     * 生成指定完整类名的实例。
     * 
     * @param className 完整类名
     * @param parameterTypes 构造参数类型数组
     * @param initargs 调用构造方法生成实例时作为参数传给构造方法的对象数组
     * @return 类实例。如果找不到指定的类名，将返回false。
     * @param <T> 创建的实例类型
     */
    public static <T> T createInstance(String className, 
            Class<?>[] parameterTypes, Object[] initargs) {
        return createInstance(className, parameterTypes, initargs, null);
    }
    
    /**
     * 生成指定完整类名的实例。
     * 
     * @param className 完整类名
     * @param parameterTypes 构造参数类型数组
     * @param initargs 调用构造方法生成实例时作为参数传给构造方法的对象数组
     * @param loader 加载类的{@link ClassLoader}
     * @return 类实例。如果找不到指定的类名，将返回false。
     * @param <T> 创建的实例类型
     */
    @SuppressWarnings("unchecked")
    public static <T> T createInstance(String className, 
            Class<?>[] parameterTypes, Object[] initargs, ClassLoader loader) {
        if (null == className ) {
            return null;
        }
        
        T obj = null;
        try {
            Class<T> claz = null;
            if (null == loader) {
                claz = (Class<T>) Class.forName(className);
            } else {
                claz = (Class<T>) Class.forName(className, true, loader);
            }
            Constructor<T> constructor = claz.getDeclaredConstructor(parameterTypes);
            constructor.setAccessible(true);
            obj = constructor.newInstance(initargs);
        } catch (ClassNotFoundException e) {
            _logger.error( String.format("could not found class:%s", className), e);
        } catch (Exception e) {
            _logger.error( String.format("create instance for class:%s fail", className), e);
        }
        
        return obj;
    }

    /**
     * 查找实例中的方法。不受修饰符的限制，private和protected都可以查找。
     * 
     * @param instance 在此实例中查找方法
     * @param methodName 方法名称
     * @param parameterTypes 方法参数类型列表
     * @return 如果有符合条件的方法，返回{@link Method}对象。否则：
     * <ul>
     *     <li>如果<code>instance</code>或<code>methodName</code>为null，返回null。</li>
     *     <li>如果没有符合条件的方法，返回null。</li>
     * </ul>
     */
    public static Method findMethod(Object instance, String methodName, Class<?>... parameterTypes) {
        if (null == instance || null == methodName) {
            return null;
        }
        
        Method method = null;
        try {
            method = instance.getClass().getDeclaredMethod(methodName, parameterTypes);
        } catch (SecurityException e) {
            _logger.error( String.format("find method[%s] of class[%s] occurs error", methodName, getClassName(instance)), e);
        } catch (NoSuchMethodException e) {
            try {
                method = instance.getClass().getMethod(methodName, parameterTypes);
            } catch (SecurityException e1) {
                // ignore
            } catch (NoSuchMethodException e1) {
                _logger.error( String.format("find method[%s] of class[%s] occurs error", methodName, getClassName(instance)), e);
            }
        }
        
        return method;
    }
    
    /**
     * 调用方法。不受修饰符的限制，private和protected都可以调用。
     * 
     * @param instance 调用此实例中的地
     * @param method 方法对象。可通过 {@link #findMethod(Object, String, Class...)}查找方法。
     * @param args 调用方法时传入的参数列表
     * @return 返回方法的执行结果。否则：
     * <ul>
     *     <li>传入的参数<code>instance</code>或<code>method</code>为null，返回null。</li>
     *     <li>方法执行出错，返回null。</li>
     * </ul>
     */
    public static Object invokeMethod(Object instance, Method method, Object... args) {
        if (null == instance || null == method) {
            return null;
        }
        
        Object result = null;
        try {
            method.setAccessible(true);
            result = method.invoke(instance, args);
        } catch (IllegalArgumentException e) {
            _logger.error( String.format("invoke method[%s] of class[%s] occurs error", method.getName(), getClassName(instance)), e);
        } catch (IllegalAccessException e) {
            _logger.error( String.format("invoke method[%s] of class[%s] occurs error", method.getName(), getClassName(instance)), e);
        } catch (InvocationTargetException e) {
            _logger.error( String.format("invoke method[%s] of class[%s] occurs error", method.getName(), getClassName(instance)), e);
        }
        
        return result;
    }

    /**
     * 调用类的静态方法。
     *
     * @param className 类名
     * @param methodName 静态方法名
     * @param argsType 静态方法的参数类型数组
     * @param args 静态方法的参数数组
     * @return 方法调用产生的返回值。如果出现以下情况，将返回null：
     * <ul>
     *     <li>找不到类</li>
     *     <li>类中找不到指定的方法</li>
     *     <li>参数无效</li>
     * </ul>
     */
    public static Object invokeStaticMethod(String className, String methodName, 
            Class<?>[] argsType, Object[] args) {
        Class<?> claz = null;
        try {
            claz = Class.forName(className);
            Method method = claz.getMethod(methodName, argsType);
            return method.invoke(null, args);
        } catch (Exception e) {
            _logger.error( String.format("invoke method[%s] of class[%s] occurs error", methodName, className), e);
        }
        
        return null;
    }

    private static String getClassName(Object obj) {
        if (null == obj) {
            return null;
        }
        
        return obj.getClass().getName();
    }

}
