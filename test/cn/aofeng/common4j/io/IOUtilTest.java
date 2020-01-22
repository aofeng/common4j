/**
 * 
 */
package cn.aofeng.common4j.io;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link IOUtil}的单元测试用例。
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public class IOUtilTest {

    private File srcFile;
    private String rightStr;
    
    private File destFile;
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        URL fileUrl = IOUtilTest.class.getResource("/cn/aofeng/common4j/io/LineSample.txt");
        srcFile = new File(fileUrl.getPath());
        
        rightStr = "第1行 go\n"
                + "2----------\n"
                + "3==========";
        
        destFile = new File(System.getProperty("java.io.tmpdir"), "ioutiltest_destfile.tmp");
        if (destFile.exists()) {
            destFile.delete();
        }
        destFile.createNewFile();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        if (null != destFile && destFile.exists()) {
            destFile.delete();
        }
    }

    /**
     * Test method for {@link cn.aofeng.common4j.io.IOUtil#readAsString(java.io.File, java.lang.String)}.
     * @throws IOException 
     */
    @Test
    public void testReadAsStringFileString() throws IOException {
        String str = IOUtil.readAsString(srcFile, "UTF-8");
        assertEquals(rightStr, str);
    }

    /**
     * Test method for {@link cn.aofeng.common4j.io.IOUtil#readAsString(java.io.File, java.nio.charset.Charset)}.
     * @throws IOException 
     */
    @Test
    public void testReadAsStringFileCharset() throws IOException {
        String str = IOUtil.readAsString(srcFile, Charset.forName("UTF-8"));
        assertEquals(rightStr, str);
    }

    /**
     * Test method for {@link cn.aofeng.common4j.io.IOUtil#copy(java.io.File, java.io.File)}.
     * @throws IOException 
     */
    @Test
    public void testCopyFileFile() throws IOException {
        long count = IOUtil.copy(srcFile, destFile);
        assertEquals(34, count);
    }

    /**
     * Test method for {@link cn.aofeng.common4j.io.IOUtil#copy(java.io.Reader, java.io.Writer)}.
     * @throws IOException 
     */
    @Test
    public void testCopyReaderWriter() throws IOException {
        Reader reader = new FileReader(srcFile);
        Writer writer = new FileWriter(destFile);
        long count = IOUtil.copy(reader, writer);
        assertEquals(30, count);
    }

    /**
     * Test method for {@link cn.aofeng.common4j.io.IOUtil#copy(java.io.InputStream, java.io.OutputStream)}.
     * @throws IOException 
     */
    @Test
    public void testCopyInputStreamOutputStream() throws IOException {
        InputStream ins = new FileInputStream(srcFile);
        OutputStream outs = new FileOutputStream(destFile);
        long count = IOUtil.copy(ins, outs);
        assertEquals(34, count);
    }

}
