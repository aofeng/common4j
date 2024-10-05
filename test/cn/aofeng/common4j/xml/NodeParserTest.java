package cn.aofeng.common4j.xml;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@link NodeParser}的单元测试用例。
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public class NodeParserTest {

    private Document _document = DomUtil.createDocument("/cn/aofeng/common4j/xml/NodeParser.xml");
    private Node _root = _document.getDocumentElement();
    private NodeParser _nodeParser = new NodeParser(_root);

    @Test
    public void testGetValue() {
        // root节点没有值，但有子节点，会返回所有子节点的文本值
        assertNotNull(_nodeParser.getValue());
        
        // EmptyNode的值为空字符串
        NodeParser emptyNode = new NodeParser(_nodeParser.getChildNode("EmptyNode"));
        assertEquals("", emptyNode.getValue());
        
        // TextNode的值为"TextNodeValue"
        NodeParser textNode = new NodeParser(_nodeParser.getChildNode("TextNode"));
        assertEquals("TextNodeValue", textNode.getValue());
    }
    
    @Test
    public void testGetAttributeValue() {
        // root节点没有属性
        assertNull(_nodeParser.getAttributeValue("NotExist"));
        
        // pool节点有default属性
        Node poolNode = _nodeParser.getChildNode("pool");
        NodeParser emptyNodeParser = new NodeParser(poolNode);
        assertEquals("default", emptyNodeParser.getAttributeValue("name"));
    }
    
    @Test
    public void testGetAttributeCount() {
        // root节点没有属性
        assertEquals(0, _nodeParser.getAttributeCount());
        
        // pool节点有一个属性
        Node poolNode = _nodeParser.getChildNode("pool");
        NodeParser emptyNodeParser = new NodeParser(poolNode);
        assertEquals(1, emptyNodeParser.getAttributeCount());
    }
    
    @Test
    public void testGetChildNodes() {
        // root节点有三个子节点
        List<Node> nodeMap = _nodeParser.getChildNodes();
        assertNotNull(nodeMap);
        assertEquals(4, nodeMap.size());
        
        // EmptyNode节点没有子节点
        Node emptyNode = _nodeParser.getChildNode("EmptyNode");
        NodeParser emptyNodeParser = new NodeParser(emptyNode);
        assertEquals(0, emptyNodeParser.getChildNodes().size());
    }
    
    @Test
    public void testGetChildNodeCount() {
        // root节点有四个子节点
        assertEquals(4, _nodeParser.getChildNodeCount());
        
        // EmptyNode节点没有子节点
        Node emptyNode = _nodeParser.getChildNode("EmptyNode");
        NodeParser emptyNodeParser = new NodeParser(emptyNode);
        assertEquals(0, emptyNodeParser.getChildNodeCount());
    }
    
    @Test
    public void testGetChildNode() {
        // 获取一个不存在的子节点
        assertNull(_nodeParser.getChildNode("NotExistChildNode"));
        
        // 获取一个EmptyNode节点
        assertNotNull(_nodeParser.getChildNode("EmptyNode"));
    }
    
    @Test
    public void testGetChildNodeValue() {
        // 获取一个不存在的子节点的值
        assertNull(_nodeParser.getChildNodeValue("NotExistChildNode"));
        
        // 获取子节点EmptyNode的值
        assertEquals("", _nodeParser.getChildNodeValue("EmptyNode"));
        
        // 获取子节点pool的值（注：会返回它下面所有子节点的文本值）
        assertNotNull(_nodeParser.getChildNodeValue("pool"));
        
        // 获取子节点coreSize的值
        NodeParser poolNodeParser = new NodeParser(_nodeParser.getChildNode("pool"));
        assertEquals("30", poolNodeParser.getChildNodeValue("coreSize"));
    }

}
