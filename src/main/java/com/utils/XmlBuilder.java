package com.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * @author Dmitry Sherstobitov
 *         QA LSE at intetics.tshop
 *         skype: dmitry_sherstobitov
 * 
 */
public class XmlBuilder {

	DocumentBuilderFactory docFactory;
	DocumentBuilder docBuilder;
	Document doc;

	public XmlBuilder() throws ParserConfigurationException {

		docFactory = DocumentBuilderFactory.newInstance();
		docBuilder = docFactory.newDocumentBuilder();
		doc = docBuilder.newDocument();
	}

	public XmlBuilder(Object input) throws ParserConfigurationException,
			SAXException, IOException, SOAPException, TransformerException {

		docFactory = DocumentBuilderFactory.newInstance();
		docBuilder = docFactory.newDocumentBuilder();

		if (input instanceof File)
			doc = docBuilder.parse((File) input);
		else {
			Source src = ((SOAPMessage) input).getSOAPPart().getContent();
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMResult result = new DOMResult();
			transformer.transform(src, result);
			doc = (Document) result.getNode();
		}
		doc.getDocumentElement().normalize();
	}

	public NodeList getElementsByTag(String tagName) {
		NodeList elements = doc.getElementsByTagName(tagName);
		return elements;
	}

	public String extractNodeAttr(Node node, String attr) {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			return element.getAttribute(attr);
		} else
			return null;
	}

	public Element create_element(String element) {
		return doc.createElement(element);
	}

	public void set_attribute(Element element, String attribute, String attrVal) {

		element.setAttribute(attribute, attrVal);

		// Attr attr = doc.createAttribute(attribute);
		// attr.setValue(attrVal);
		// element.setAttributeNode(attr);
	}

	public void append_element(Element element) {
		doc.appendChild(element);
	}

	public void append_element(Element relement, Element chelement) {
		relement.appendChild(chelement);
	}

	public void add_text(Element element, String text) {
		element.appendChild(doc.createTextNode(text));
	}

	public void create_xml(String file) throws TransformerException {

		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(file));
		// StreamResult result = new StreamResult(System.out);
		transformer.transform(source, result);
		System.out.println("File saved!");

	}
}
