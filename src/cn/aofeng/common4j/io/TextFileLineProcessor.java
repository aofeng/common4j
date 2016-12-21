package cn.aofeng.common4j.io;

/**
 * 文本文件行处理器。
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public interface TextFileLineProcessor {

    /**
     * 处理一行文本。
     * 
     * @param line 不带回车换行符的一行文本字符串。
     * @param lineNum 行号。第一行为1、第二行为2，依次类推。
     */
    public void process(String line, int lineNum);

}
