package cn.aofeng.common4j.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * XML元素解析器。
 * 
 * @author <a href="mailto:aofengblog@163.com">聂勇</a>
 */
public class NodeParser {

    private Node _node;
    
    private Map<String, String> _attrMap;
    
    private List<Node> _childNodes;
    
    public NodeParser(Node node) {
        this._node = node;
    }
    
    /**
     * 获取指定属性的值。
     * 
     * @param attrName 属性名称
     * @return 属性的值。如果指定的属性不存在，返回null。
     */
    public String getAttributeValue(String attrName) {
        initAttrMap();
        return _attrMap.get(attrName);
    }
    
    /**
     * @return 元素的属性数量。如果没有属性，返回0。
     */
    public int getAttributeCount() {
        initAttrMap();
        return _attrMap.size();
    }
    
    private void initAttrMap() {
        if (null != _attrMap) {
            return;
        }
        
        _attrMap = new HashMap<String, String>();
        NamedNodeMap nodeMap =_node.getAttributes();
        if (null == nodeMap) {
            return;
        }
        for (int i = 0; i < nodeMap.getLength(); i++) {
            Node attr = nodeMap.item(i);
            _attrMap.put(attr.getNodeName(), attr.getNodeValue());
        }
    }
    
    /**
     * @return 下一级的所有子元素的列表。
     */
    public List<Node> getChildNodes() {
        initChildNodeList();
        return _childNodes;
    }
    
    /**
     * @return 下一级的子元素的数量。
     */
    public int getChildNodeCount() {
        initChildNodeList();
        return _childNodes.size();
    }
    
    /**
     * 返回指定子元素。<strong>注：</strong>如果有多个相同名称的子元素，只返回获取到的第一个元素。
     * 
     * @param nodeName 子元素名称
     * @return 子元素。如果指定的子元素不存在，返回null。
     */
    public Node getChildNode(String nodeName) {
        if (null == nodeName) {
            return null;
        }
        
        initChildNodeList();
        for (Node node : _childNodes) {
            if (nodeName.equals(node.getNodeName())) {
                return node;
            }
        }
        
        return null;
    }
    
    /**
     * 返回指定子元素的值。<strong>注：</strong>由于许多XML没有DTD或Schema，W3C DOM在识别节点的类型时会有一些问题。
     * 用getNodeValue会无法获取文本类型节点的值，因此用getTextContent方法代替，但如果该节点有子节点，会返回它所有的子节点的文本值。
     * 
     * @param nodeName 子元素名称
     * @return 子元素的值。如果为非文本元素，有可能返回不是期望的值。
     */
    public String getChildNodeValue(String nodeName) {
        Node node = getChildNode(nodeName);
        if (null == node) {
            return null;
        }
        
        return node.getTextContent();
    }
    
    private void initChildNodeList() {
        if (null != _childNodes) {
            return;
        }
        
        _childNodes = new ArrayList<Node>();
        NodeList nodeList = _node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Node.ELEMENT_NODE != node.getNodeType()) {
                continue;
            }
            _childNodes.add(node);
        }
    }

}
