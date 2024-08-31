package cn.aofeng.common4j.lang;

/**
 * 字符串操作常用方法。
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public class StringUtil {

    /**
     * 判断字符串是否为null。例：
     * &lt;ul&gt;
     * &lt;li&gt;StringUtil.isNull(null)，结果为true。&lt;/li&gt;
     * &lt;li&gt;StringUtil.isNull("")，结果为false。&lt;/li&gt;
     * &lt;li&gt;StringUtil.isNull(" ")，结果为false。&lt;/li&gt;
     * &lt;li&gt;StringUtil.isNull("a")，结果为false。&lt;/li&gt;
     * &lt;/ul&gt;
     * 
     * @param str 字符串对象
     * @return 如果字符串为null，返回true。否则，返回false。
     */
    public static boolean isNull(String str) {
        return null == str;
    }
    
    /**
     * 判断字符串是否为null或空字符串。例：
     * &lt;ul&gt;
     * &lt;li&gt;StringUtil.isEmpty(null)，结果为true。&lt;/li&gt;
     * &lt;li&gt;StringUtil.isEmpty("")，结果为true。&lt;/li&gt;
     * &lt;li&gt;StringUtil.isEmpty(" ")，结果为false。&lt;/li&gt;
     * &lt;li&gt;StringUtil.isEmpty("a")，结果为false。&lt;/li&gt;
     * &lt;/ul&gt;
     * 
     * @param str 字符串对象
     * @return 如果字符串为null或空字符串，返回true。否则，返回false。
     */
    public static boolean isEmpty(String str) {
        return (null == str || 0 == str.length());
    }

    /**
     * 判断字符串是否为null或全部由空白字符组成。例：
     * &lt;ul&gt;
     * &lt;li&gt;StringUtil.isNull(null)，结果为true。&lt;/li&gt;
     * &lt;li&gt;StringUtil.isNull("")，结果为true。&lt;/li&gt;
     * &lt;li&gt;StringUtil.isNull(" ")，结果为true。&lt;/li&gt;
     * &lt;li&gt;StringUtil.isNull("a")，结果为false。&lt;/li&gt;
     * &lt;li&gt;StringUtil.isNull(" a ")，结果为false。&lt;/li&gt;
     * &lt;/ul&gt;
     * 
     * @param str 字符串对象
     * @return 如果字符串为null或全部由空白字符组成，返回true。否则，返回false。
     */
    public static boolean isBlank(String str) {
        if (isEmpty(str)) {
            return true;
        }
        
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        
        return true;
    }

}
