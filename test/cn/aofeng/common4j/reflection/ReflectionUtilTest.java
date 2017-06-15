package cn.aofeng.common4j.reflection;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link ReflectionUtil}的单元测试用例。
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public class ReflectionUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateInstance() {
        ReflectionMock mock = ReflectionUtil.createInstance("cn.aofeng.common4j.reflection.ReflectionMock");
        assertNotNull(mock);
        assertEquals("default", mock.getValue());
    }

    @Test
    public void testCreateInstanceByArgs() {
        ReflectionMock mock = ReflectionUtil.createInstance(
                "cn.aofeng.common4j.reflection.ReflectionMock",
                new Class[]{String.class},
                new String[]{"NewValue"});
        assertNotNull(mock);
        assertEquals("NewValue", mock.getValue());
    }

    @Test
    public void testFindMethod() {
        // 方法存在
        ReflectionMock instance = new ReflectionMock();        
        Method method = ReflectionUtil.findMethod(instance, "join", String.class, int.class);
        assertNotNull(method);
        assertEquals("join", method.getName());
        
        // 方法不存在
        method = ReflectionUtil.findMethod(instance, "NotExists", String.class);
        assertNull(method);
    }

    @Test
    public void testInvokeMethod() {
        ReflectionMock instance = new ReflectionMock();        
        Method method = ReflectionUtil.findMethod(instance, "join", String.class, int.class);
        String result = (String) ReflectionUtil.invokeMethod(instance, method, "0123456789___", 696969);
        assertNotNull(result);
        assertEquals("0123456789___696969", result);
    }

    @Test
    public void testInvokeStaticMethod() {
        String hello = (String) ReflectionUtil.invokeStaticMethod("cn.aofeng.common4j.reflection.ReflectionMock", 
                "sayHello", null, null);
        assertEquals("Hello", hello);
    }

}
