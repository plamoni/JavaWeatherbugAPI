package com.codefreak.weatherbugapi.containers;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

public class Location {
	private String city;
	private String state;
	private String zip;
	private String code;
	
	public static final Logger logger = Logger.getLogger(Location.class);
	
	public Location() {}
	
	public Location(XPath xpath, Node node) {
		try {
			this.city = (String)xpath.evaluate("//aws:city", node, XPathConstants.STRING);
			this.state = (String)xpath.evaluate("//aws:state", node, XPathConstants.STRING);
			this.zip = (String)xpath.evaluate("//aws:zip", node, XPathConstants.STRING);
			this.code = (String)xpath.evaluate("//aws:zone", node, XPathConstants.STRING);
		} catch (XPathExpressionException e) {
			logger.error("Failed to parse location!");
		}
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
