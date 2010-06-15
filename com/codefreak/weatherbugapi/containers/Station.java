package com.codefreak.weatherbugapi.containers;

import org.apache.log4j.Logger;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Station {
	/****
 	<aws:station id="SLLDN" name="Suffolk County Community College"
		city="Selden" state="NY" zipcode="11784" distance="2.98" station-type="WeatherBug"
		latitude="40.85611" longitude="-73.06306"></aws:station>
	 *****/
	
	private String id;
	private String name;
	private String city;
	private String state;
	private String country;
	private String zipcode;
	private String distance;
	private String distanceUnits;
	private String latitude;
	private String longitude;
	
	public static final Logger logger = Logger.getLogger(Station.class);
	
	public Station() {}
	
	public Station(Node node) {
		NamedNodeMap attrs = node.getAttributes();
		this.id = attrs.getNamedItem("id").getNodeValue();
		this.name = attrs.getNamedItem("name").getNodeValue();
		this.city = attrs.getNamedItem("city").getNodeValue();
		this.state = attrs.getNamedItem("state").getNodeValue();
		this.country = attrs.getNamedItem("country").getNodeValue();
		this.zipcode = attrs.getNamedItem("zipcode").getNodeValue();
		this.distance = attrs.getNamedItem("distance").getNodeValue();
		this.distanceUnits = attrs.getNamedItem("Unit").getNodeValue();
		this.latitude = attrs.getNamedItem("latitude").getNodeValue();
		this.longitude = attrs.getNamedItem("longitude").getNodeValue();	
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getDistanceUnits() {
		return distanceUnits;
	}
	public void setDistanceUnits(String distanceUnits) {
		this.distanceUnits = distanceUnits;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	
}
