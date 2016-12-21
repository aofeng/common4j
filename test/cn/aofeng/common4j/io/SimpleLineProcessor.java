package cn.aofeng.common4j.io;

import static org.junit.Assert.assertEquals;

/**
 * 
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public class SimpleLineProcessor implements TextFileLineProcessor {

    public int _num = 0;
    
    @Override
    public void process(String line, int lineNum) {
        if (1 == lineNum) {
            assertEquals("第1行 go", line);
        }
        if (2 == lineNum) {
            assertEquals("2----------", line);
        }
        if (3 == lineNum) {
            assertEquals("3==========", line);
        }
        
        _num++;
    }

}
