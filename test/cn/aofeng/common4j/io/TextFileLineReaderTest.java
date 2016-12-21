package cn.aofeng.common4j.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * {@link TextFileLineReader}的单元测试用例。 
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public class TextFileLineReaderTest {

    private TextFileLineReader _lineRead = new TextFileLineReader();
    
    @Rule
    public ExpectedException _expectedEx = ExpectedException.none();
    
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * 前提：行处理器不为null <br>
     * 用例：文件对象为null
     */
    @Test
    public void testReadFileTextFileLineProcessor4FileIsNull() throws IOException {
        _expectedEx.expect(IllegalArgumentException.class);
        _expectedEx.expectMessage("file null invalid, may be not exists");
        
        File file = null;
        _lineRead.read(file, new TextFileLineProcessor() {
            
            @Override
            public void process(String line, int lineNum) {
                // nothing
            }
        });
    }
    
    /**
     * 前提：行处理器不为null <br>
     * 用例：文件对象不为null，但文件不存在
     */
    @Test
    public void testReadFileTextFileLineProcessor4FileNotExists() throws IOException {
        _expectedEx.expect(IllegalArgumentException.class);
        _expectedEx.expectMessage("file FileNotExists_999.txt invalid, may be not exists");
        
        File file = new File("FileNotExists_999.txt");
        _lineRead.read(file, new TextFileLineProcessor() {
            
            @Override
            public void process(String line, int lineNum) {
                // nothing
            }
        });
    }
    
    /**
     * 前提：文件存在 <br>
     * 用例：行处理器为null
     */
    @Test
    public void testReadFileTextFileLineProcessor4LineProcessorIsNull() throws IOException {
        _expectedEx.expect(IllegalArgumentException.class);
        _expectedEx.expectMessage("line processor is null");
        
        File file = new File(getClass().getResource("/cn/aofeng/common4j/io/LineSample.txt").getFile());
        _lineRead.read(file, null);
    }

    /**
     * 前提：文件存在，行处理器不为null <br>
     * 用例：正确读取文件内容
     */
    @Test
    public void testReadFileTextFileLineProcessor() throws IOException {
        File file = new File(getClass().getResource("/cn/aofeng/common4j/io/LineSample.txt").getFile());
        SimpleLineProcessor lineProcessor = new SimpleLineProcessor();
        _lineRead.read(file, lineProcessor);
        assertEquals(3, lineProcessor._num);
    }

}
