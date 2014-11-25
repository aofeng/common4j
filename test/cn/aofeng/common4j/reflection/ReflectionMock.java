package cn.aofeng.common4j.reflection;

/**
 * 用于反射生成实例的测试类。
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public class ReflectionMock {

    private String _value;
    
    private ReflectionMock() {
        _value = "default";
    }
    
    private ReflectionMock(String value) {
        _value = value;
    }
    
    public String getValue() {
        return _value;
    }

}
