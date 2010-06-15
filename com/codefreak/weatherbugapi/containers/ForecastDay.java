package com.codefreak.weatherbugapi.containers;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Node;

public class ForecastDay {	
	private String title;
	private String altTitle;
	private String description;
	private String shortPrediction;
	private String imageURL;
	private String iconName;
	private boolean isNight;
	private String longPrediction;
	private String highTemp;
	private String highTempUnits;
	private String lowTemp;
	private String lowTempUnits;

	public ForecastDay() { }
	
	public ForecastDay(XPath xpath, Node node) {
		try {
			this.title = (String)xpath.evaluate("./aws:title", node, XPathConstants.STRING);
			this.altTitle = (((Node)xpath.evaluate("./aws:title", node, XPathConstants.NODE)).getAttributes().getNamedItem("alttitle").getNodeValue());
			this.description = (String)xpath.evaluate("./aws:description", node, XPathConstants.STRING);
			this.shortPrediction = (String)xpath.evaluate("./aws:short-prediction", node, XPathConstants.STRING);
			this.imageURL = (String)xpath.evaluate("./aws:image", node, XPathConstants.STRING);
			this.iconName = (((Node)xpath.evaluate("./aws:image", node, XPathConstants.NODE)).getAttributes().getNamedItem("icon").getNodeValue());
			this.isNight = ((Node)xpath.evaluate("./aws:title", node, XPathConstants.NODE)).getAttributes().getNamedItem("alttitle").getNodeValue().equals("1");
			this.longPrediction = (String)xpath.evaluate("./aws:prediction", node, XPathConstants.STRING);
			this.highTemp = (String)xpath.evaluate("./aws:high", node, XPathConstants.STRING);
			this.highTempUnits = (String)(((Node)xpath.evaluate("./aws:high", node, XPathConstants.NODE)).getAttributes().getNamedItem("units").getNodeValue());
			this.lowTemp = (String)xpath.evaluate("./aws:low", node, XPathConstants.STRING);
			this.lowTempUnits = (String)(((Node)xpath.evaluate("./aws:low", node, XPathConstants.NODE)).getAttributes().getNamedItem("units").getNodeValue());

		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAltTitle() {
		return altTitle;
	}

	public void setAltTitle(String altTitle) {
		this.altTitle = altTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortPrediction() {
		return shortPrediction;
	}

	public void setShortPrediction(String shortPrediction) {
		this.shortPrediction = shortPrediction;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public boolean isNight() {
		return isNight;
	}

	public void setNight(boolean isNight) {
		this.isNight = isNight;
	}

	public String getLongPrediction() {
		return longPrediction;
	}

	public void setLongPrediction(String longPrediction) {
		this.longPrediction = longPrediction;
	}

	public String getHighTemp() {
		return highTemp;
	}

	public void setHighTemp(String highTemp) {
		this.highTemp = highTemp;
	}

	public String getHighTempUnits() {
		return highTempUnits;
	}

	public void setHighTempUnits(String highTempUnits) {
		this.highTempUnits = highTempUnits;
	}

	public String getLowTemp() {
		return lowTemp;
	}

	public void setLowTemp(String lowTemp) {
		this.lowTemp = lowTemp;
	}

	public String getLowTempUnits() {
		return lowTempUnits;
	}

	public void setLowTempUnits(String lowTempUnits) {
		this.lowTempUnits = lowTempUnits;
	}

	

}
