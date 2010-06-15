package com.codefreak.weatherbugapi.containers;

import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Forecast {
	private Location location;
	private ArrayList<ForecastDay> forecastDays = new ArrayList<ForecastDay>();
	
	public static final Logger logger = Logger.getLogger(Forecast.class);
	
	public Forecast() { }
	
	public Forecast(XPath xpath, Node node) {
		try {
			this.location = new Location(xpath, node);
			NodeList forecastNodes = (NodeList)xpath.evaluate("//aws:forecast", node, XPathConstants.NODESET);
			
			for(int i=0; i<forecastNodes.getLength(); i++) {
				forecastDays.add(new ForecastDay(xpath, forecastNodes.item(i)));
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			logger.error("Failed to parse forecast");
		}
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public ArrayList<ForecastDay> getForecastDays() {
		return forecastDays;
	}

	public void setForecastDays(ArrayList<ForecastDay> forecastDays) {
		this.forecastDays = forecastDays;
	}
	
	
}
