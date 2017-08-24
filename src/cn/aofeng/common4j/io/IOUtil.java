package cn.aofeng.common4j.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 * I/O实用工具方法集合。
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public class IOUtil {

    /** 默认的BUFFER大小：4K  */
    private final static int BUFFER_SIZE = 4096;
    
    /**
     * 关闭实现了{@link Closeable}接口的对象或资源。
     */
    public static void closeQuietly(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                // nothing
            }
        }
    }

    /**
     * 按指定编码读取文件内容并转换成字符串。
     * 
     * @param file 待读取的文件对象 
     * @param readEncoding 读取文件内容的编码格式。如：UTF-8
     * @return 字符串
     * @throws IOException 读取文件过程中出错抛出此异常
     */
    public static String readAsString(final File file, String readEncoding) throws IOException {
        StringWriter writer = new StringWriter(BUFFER_SIZE);
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(new FileInputStream(file), Charset.forName(readEncoding));
            copy(reader, writer);
        } finally {
            closeQuietly(reader);
        }
        
        return writer.toString();
    }

    /**
     * 复制文件。
     * 
     * @param src 源文件
     * @param dest 目标文件
     * @return 成功复制的字节数
     * @throws IOException 复制文件过程中出错抛出此异常
     */
    public static long copy(File src, File dest) throws IOException {
        long count = 0;
        InputStream ins = null;
        OutputStream outs = null;
        try {
            ins = new FileInputStream(src);
            outs = new FileOutputStream(dest);
            copy(ins, outs);
        } finally {
            closeQuietly(ins);
            closeQuietly(outs);
        }
        
        return count;
    }
    
    /**
     * 以字符方式复制数据。
     * 
     * @param reader 输入字符流
     * @param writer 输出字符流
     * @return 成功复制的字符数
     * @throws IOException 复制过程中出错抛出此异常
     */
    public static long copy(Reader reader, Writer writer) throws IOException {
        long count = 0;
        int num = 0;
        char[] buffer = new char[BUFFER_SIZE];
        while ( -1 != (num = reader.read(buffer)) ) {
            writer.write(buffer, 0, num);
            count += num;
        }
        
        return count;
    }
    
    /**
     * 以字节方式复制数据。
     * 
     * @param ins 输入流
     * @param outs 输出流
     * @return 成功复制的字节数
     * @throws IOException 复制过程中出错抛出此异常
     */
    public static long copy(InputStream ins, OutputStream outs) throws IOException {
        long count = 0;
        int num = 0;
        byte[] buffer = new byte[BUFFER_SIZE];
        while ( -1 != (num = ins.read(buffer)) ) {
            outs.write(buffer, 0, num);
            count += num;
        }
        
        return count;
    }

}
