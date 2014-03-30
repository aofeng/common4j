package cn.aofeng.common4j.xml;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * {@link NodeParser}的单元测试用例。
 * 
 * @author <a href="mailto:nieyong@ucweb.com">聂勇</a>
 */
public class NodeParserTest {

    private Document _document = DomUtil.createDocument("/cn/aofeng/common4j/xml/NodeParser.xml");
    private Node _root = _document.getDocumentElement();
    private NodeParser _nodeParser = new NodeParser(_root);
    
    @Before
    public void setUp() throws Exception {
    }
    
    @After
    public void tearDown() throws Exception {
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
        // root节点有两个子节点
        Map<String, Node> nodeMap = _nodeParser.getChildNodes();
        assertNotNull(nodeMap);
        assertEquals(2, nodeMap.size());
        
        // EmptyNode节点没有子节点
        Node emptyNode = _nodeParser.getChildNode("EmptyNode");
        NodeParser emptyNodeParser = new NodeParser(emptyNode);
        assertEquals(0, emptyNodeParser.getChildNodes().size());
    }
    
    @Test
    public void testGetChildNodeCount() {
        // root节点有两个子节点
        assertEquals(2, _nodeParser.getChildNodeCount());
        
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
