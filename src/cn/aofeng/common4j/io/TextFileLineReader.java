package cn.aofeng.common4j.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 对文件文件进行逐行处理。
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public class TextFileLineReader {

    /**
     * 读取文本文件，并逐行交给行处理器<code>lineProcessor</code>处理，读取完成后自动关闭文件。
     * 如果在读取文件的过程中报错，将抛出IO异常。
     * 
     * @param filePath 待读取的文本文件的路径
     * @param lineProcessor 实现了{@link TextFileLineProcessor}接口的对象实例
     * @throws IOException 如果文本文件对象为null或文件不存在，或者行处理器为null，抛出此异常
     * @see #read(File, TextFileLineProcessor)
     */
    public void read(String filePath, TextFileLineProcessor lineProcessor) throws IOException {
        File file = new File(filePath);
        read(file, lineProcessor);
    }
    
    /**
     * 读取文本文件，并逐行交给行处理器<code>lineProcessor</code>处理，读取完成后自动关闭文件。
     * 如果在读取文件的过程中报错，将抛出IO异常。
     * 
     * @param file 待读取的文本文件对象
     * @param lineProcessor 实现了{@link TextFileLineProcessor}接口的对象实例
     * @throws IOException 如果文本文件对象为null或文件不存在，或者行处理器为null，抛出此异常
     */
    public void read(File file, TextFileLineProcessor lineProcessor) throws IOException {
        if (null == file || !file.exists()) {
            throw new IllegalArgumentException("file "+file+" invalid, may be not exists");
        }
        if (null == lineProcessor) {
            throw new IllegalArgumentException("line processor is null");
        }
        
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            int lineNum = 0;
            while (null != (line = reader.readLine())) {
                lineNum++;
                lineProcessor.process(line, lineNum);
            }
        } finally {
            IOUtil.closeQuietly(reader);
        }
        
    }

}
