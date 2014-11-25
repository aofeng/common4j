package cn.aofeng.common4j.reflection;

import static org.junit.Assert.*;

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

}
