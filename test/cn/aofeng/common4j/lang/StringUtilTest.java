package cn.aofeng.common4j.lang;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * {@linkplain StringUtil}的单元测试用例。
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public class StringUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testIsNull() {
        assertTrue(StringUtil.isNull(null));
        assertFalse(StringUtil.isNull(""));
        assertFalse(StringUtil.isNull(" "));
        assertFalse(StringUtil.isNull("a"));
    }

    @Test
    public void testIsEmpty() {
        assertTrue(StringUtil.isEmpty(null));
        assertTrue(StringUtil.isEmpty(""));
        assertFalse(StringUtil.isEmpty(" "));
        assertFalse(StringUtil.isEmpty("a"));
    }

    @Test
    public void testIsBlank() {
        assertTrue(StringUtil.isBlank(null));
        assertTrue(StringUtil.isBlank(""));
        assertTrue(StringUtil.isBlank(" "));
        assertFalse(StringUtil.isBlank("a"));
        assertFalse(StringUtil.isBlank(" a "));
    }

}
