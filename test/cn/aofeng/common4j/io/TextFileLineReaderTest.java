package cn.aofeng.common4j.io;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * {@link TextFileLineReader}的单元测试用例。 
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public class TextFileLineReaderTest {

    private TextFileLineReader _lineRead = new TextFileLineReader();
    
    /**
     * 前提：行处理器不为null <br>
     * 用例：文件对象为null
     */
    @Test
    public void testReadFileTextFileLineProcessor4FileIsNull() throws IOException {
        File file = null;
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> _lineRead.read(file, new TextFileLineProcessor() {

            @Override
            public void process(String line, int lineNum) {
                // nothing
            }
        }));
        assertEquals("file null invalid, may be not exists", ex.getMessage());
    }
    
    /**
     * 前提：行处理器不为null <br>
     * 用例：文件对象不为null，但文件不存在
     */
    @Test
    public void testReadFileTextFileLineProcessor4FileNotExists() throws IOException {
        File file = new File("FileNotExists_999.txt");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> _lineRead.read(file, new TextFileLineProcessor() {
            @Override
            public void process(String line, int lineNum) {
                // nothing
            }
        }));
        assertEquals("file FileNotExists_999.txt invalid, may be not exists", ex.getMessage());
    }
    
    /**
     * 前提：文件存在 <br>
     * 用例：行处理器为null
     */
    @Test
    public void testReadFileTextFileLineProcessor4LineProcessorIsNull() throws IOException {
        File file = new File(getClass().getResource("/cn/aofeng/common4j/io/LineSample.txt").getFile());
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> _lineRead.read(file, null));
        assertEquals("line processor is null", ex.getMessage());
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
