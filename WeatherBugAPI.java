package com.codefreak.iphonestats.modules.weatherbug;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.codefreak.iphonestats.modules.weatherbug.containers.Forecast;
import com.codefreak.iphonestats.modules.weatherbug.containers.ForecastDay;
import com.codefreak.iphonestats.modules.weatherbug.containers.LiveWeather;
import com.codefreak.iphonestats.modules.weatherbug.containers.Location;
import com.codefreak.iphonestats.modules.weatherbug.containers.Station;

public class WeatherBugAPI {
	public static final Logger logger = Logger.getLogger(WeatherBugAPI.class);
	public static final Properties props = new Properties();
	static {
		try {
			props.load(WeatherBugAPI.class.getResourceAsStream("weatherbugapi.properties"));
		} catch (Exception e) {
			logger.fatal("Could not load weatherbugapi.properties! Please create a 'weatherbugapi.properties' file" +
					"in com.codefreak.iphonestats.modules.weatherbug and include 'apikey=YOUR_API_KEY'.");
		}
	}
	
	public static final String API_KEY = props.getProperty("apikey");
	private XPath xpath = XPathFactory.newInstance().newXPath();
	
	public WeatherBugAPI() {
		
		xpath.setNamespaceContext(new AWSNameSpaceContext());
	}
	
	public ArrayList<Station> getStationsByZipCode(String zipCode, float maxDistance) {
		ArrayList<Station> results = new ArrayList<Station>();
		InputSource inputSource = new InputSource("http://" + API_KEY + ".api.wxbug.net/getStationsXML.aspx?ACode=" + API_KEY + "&zipCode=" + zipCode + "&unitType=0");
		
		NodeList list;
		try {
			list = (NodeList)xpath.evaluate("//aws:station[@distance< " + maxDistance + "]", inputSource, XPathConstants.NODESET);
	
			for(int i=0; i<list.getLength(); i++) {
				results.add(new Station(list.item(i)));
			}
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return results;
	}
	
	public Forecast getForecastByZipCode(String zipCode) {
		try {
			String url = "http://" + API_KEY + ".api.wxbug.net/getForecastRSS.aspx?ACode=" + API_KEY + "&zipCode=" + zipCode + "&unitType=0&outputType=1";
			logger.debug("Grabbing URL: " + url);
			InputSource inputSource = new InputSource(url);
			
			Node node = (Node)xpath.evaluate("//aws:weather", inputSource, XPathConstants.NODE);
			return new Forecast(xpath, node);
		
		} catch (XPathExpressionException e) {
			logger.error("Failed to parse out Forecast!");
		}
		
		return null;
	}
	
	public LiveWeather getLiveWeatherByZipCode(String zipCode) {
		try {
			String url = "http://" + API_KEY + ".api.wxbug.net/getLiveWeatherRSS.aspx?ACode=" + API_KEY + "&zipCode=" + zipCode + "&unitType=0&outputType=1";
			logger.debug("Grabbing URL: " + url);
			InputSource inputSource = new InputSource(url);
			
			Node node = (Node)xpath.evaluate("//aws:weather", inputSource, XPathConstants.NODE);
			logger.debug(node.getNodeName());
			LiveWeather weather = new LiveWeather(xpath, node);
			
			return weather;
		} catch (XPathExpressionException e) {
			logger.error("Failed to parse out Forecast!");
		}
		return null;
	}
	
	private static final Pattern conditionPattern = Pattern.compile("(cond[0-9][0-9][0-9])[\\.]");
	public static String getIconURL(String iconName, String size, boolean transparent) {
		Matcher m = conditionPattern.matcher(iconName);
		if(m.find()) {
			String condition = m.group(1);
			logger.debug(condition);
			return String.format("http://img.weather.weatherbug.com/forecast/icons/localized/%s/en/%s/%s.png", 
								 size,
								 (transparent) ? "trans" : "opaq",
								 condition);
		}
		return iconName; // whoops!
	}
	
	//sizes
	public static final String SIZE_15x13 = "15x13";
	public static final String SIZE_20x17 = "20x17";
	public static final String SIZE_25x21 = "25x21";
	public static final String SIZE_30x25 = "30x25";
	public static final String SIZE_35x29 = "35x29";
	public static final String SIZE_40x34 = "40x34";
	public static final String SIZE_45x38 = "45x38";
	public static final String SIZE_50x42 = "50x42";
	public static final String SIZE_55x46 = "55x46";
	public static final String SIZE_60x50 = "60x50";
	public static final String SIZE_65x55 = "65x55";
	public static final String SIZE_70x59 = "70x59";
	public static final String SIZE_80x67 = "80x67";
	public static final String SIZE_85x71 = "85x71";
	public static final String SIZE_90x76 = "90x76";
	public static final String SIZE_95x80 = "95x80";
	public static final String SIZE_100x84 = "100x84";
	public static final String SIZE_105x88 = "105x88";
	public static final String SIZE_110x92 = "110x92";
	public static final String SIZE_115x97 = "115x97";
	public static final String SIZE_120x101 = "120x101";
	public static final String SIZE_125x105 = "125x105";
	public static final String SIZE_130x109 = "130x109";
	public static final String SIZE_135x113 = "135x113";
	public static final String SIZE_140x118 = "140x118";
	public static final String SIZE_145x122 = "145x122";
	public static final String SIZE_150x126 = "150x126";
	public static final String SIZE_155x130 = "155x130";
	public static final String SIZE_160x134 = "160x134";
	public static final String SIZE_165x139 = "165x139";
	public static final String SIZE_170x143 = "170x143";
	public static final String SIZE_175x147 = "175x147";
	public static final String SIZE_180x151 = "180x151";
	public static final String SIZE_185x155 = "185x155";
	public static final String SIZE_190x160 = "190x160";
	public static final String SIZE_195x164 = "195x164";
	public static final String SIZE_200x168 = "200x168";
	public static final String SIZE_205x172 = "205x172";
	public static final String SIZE_210x176 = "210x176";
	public static final String SIZE_215x181 = "215x181";
	public static final String SIZE_220x185 = "220x185";
	public static final String SIZE_225x189 = "225x189";
	public static final String SIZE_230x193 = "230x193";
	public static final String SIZE_235x197 = "235x197";
	public static final String SIZE_240x202 = "240x202";
	public static final String SIZE_245x206 = "245x206";
	public static final String SIZE_250x210 = "250x210";
	public static final String SIZE_255x214 = "255x214";
	public static final String SIZE_260x218 = "260x218";
	public static final String SIZE_265x223 = "265x223";
	public static final String SIZE_270x227 = "270x227";
	public static final String SIZE_275x231 = "275x231";
	public static final String SIZE_280x235 = "280x235";
	public static final String SIZE_285x239 = "285x239";
	public static final String SIZE_290x244 = "290x244";
	public static final String SIZE_295x248 = "295x248";
	public static final String SIZE_300x252 = "300x252";
	public static final String SIZE_305x256 = "305x256";
	public static final String SIZE_310x260 = "310x260";
	public static final String SIZE_315x265 = "315x265";
	public static final String SIZE_320x269 = "320x269";
	public static final String SIZE_325x273 = "325x273";
	public static final String SIZE_330x277 = "330x277";
	public static final String SIZE_335x281 = "335x281";
	public static final String SIZE_340x286 = "340x286";
	public static final String SIZE_345x290 = "345x290";
	public static final String SIZE_350x294 = "350x294";
	public static final String SIZE_355x298 = "355x298";
	public static final String SIZE_360x302 = "360x302";
	public static final String SIZE_365x307 = "365x307";
	public static final String SIZE_370x311 = "370x311";
	public static final String SIZE_375x315 = "375x315";
	public static final String SIZE_380x319 = "380x319";
	public static final String SIZE_385x323 = "385x323";
	public static final String SIZE_390x328 = "390x328";
	public static final String SIZE_395x332 = "395x332";
	public static final String SIZE_400x336 = "400x336";
	public static final String SIZE_405x340 = "405x340";
	public static final String SIZE_410x344 = "410x344";
	public static final String SIZE_415x349 = "415x349";
	public static final String SIZE_420x353 = "420x353";
	public static final String SIZE_425x357 = "425x357";
	public static final String SIZE_430x361 = "430x361";
	public static final String SIZE_435x365 = "435x365";
	public static final String SIZE_440x370 = "440x370";
	public static final String SIZE_445x374 = "445x374";
	public static final String SIZE_450x378 = "450x378";
	public static final String SIZE_455x382 = "455x382";
	public static final String SIZE_460x386 = "460x386";
	public static final String SIZE_465x391 = "465x391";
	public static final String SIZE_470x395 = "470x395";
	public static final String SIZE_475x399 = "475x399";
	public static final String SIZE_480x403 = "480x403";
	public static final String SIZE_485x407 = "485x407";
	public static final String SIZE_490x412 = "490x412";
	public static final String SIZE_495x416 = "495x416";
	public static final String SIZE_500x420 = "500x420";
}
