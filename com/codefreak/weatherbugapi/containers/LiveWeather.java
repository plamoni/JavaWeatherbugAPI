package com.codefreak.weatherbugapi.containers;

import java.util.Calendar;
import java.util.TimeZone;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;



public class LiveWeather {
	private Calendar observationTime;
	private Calendar gustTime;
	private Calendar sunrise;
	private Calendar sunset;
	private String stationZipcode;
	private String stationCityState;
	private String stationCountry;
	private double stationLatitude;
	private double stationLongitude;
	
	private String currentConditions;
	private String currentImageURL;
	private double dewPoint;
	private String dewPointUnits;
	private double elevation;
	private String elevationUnits;
	private double feelsLikeTemp;
	private String feelsLikeTempUnits;
	private String gustDirectionStr;
	private double gustDirectionDegs;
	private double gustSpeed;
	private String gustSpeedUnits;
	
	private double humidity;
	private String humidityUnits;
	private double humidityLow;
	private String humidityLowUnits;
	private double humidityHigh;
	private String humidityHighUnits;
	private double humidityRate; //not sure what this is.
	
	private double light;
	private double lightRate;
	private double moonPhase;
	private String moonPhaseImageURL;
	
	private double pressure;
	private String pressureUnits;
	private double pressureHigh;
	private String pressureHighUnits;
	private double pressureLow;
	private String pressureLowUnits;
	private double pressureRate;
	private String pressureRateUnits;

	private double rainMonth;
	private String rainMonthUnits;
	private double rainRate;
	private String rainRateUnits;
	private double rainRateMax;
	private String rainRateMaxUnits;
	private double rainToday;
	private String rainTodayUnits;
	private double rainYear;
	private String rainYearUnits;
	
	private double temp;
	private String tempUnits;
	private double tempHigh;
	private String tempHighUnits;
	private double tempLow;
	private String tempLowUnits;
	private double tempRate;
	private String tempRateUnits;
	
	private double indoorTemp;
	private String indoorTempUnits;
	private double indoorTempRate;
	private String indoorTempRateUnits;
	
	private double wetBulb;
	private String wetBulbUnits;
	private double windSpeed;
	private String windSpeedUnits;
	private double windSpeedAverage;
	private String windSpeedAverageUnits;
	private String windDirection;
	private String windDegrees;
	private String windDirectionAverage;
	
	
	public static final Logger logger = Logger.getLogger(LiveWeather.class);
	
	public LiveWeather() { }
	
	public LiveWeather(XPath xpath, Node node) {
		try {
			this.observationTime = parseDate((Node)xpath.evaluate("//aws:ob-date", node, XPathConstants.NODE), xpath);
			this.gustTime = parseDate((Node)xpath.evaluate("//aws:gust-time", node, XPathConstants.NODE), xpath);
			this.sunrise = parseDate((Node)xpath.evaluate("//aws:sunrise", node, XPathConstants.NODE), xpath);
			this.sunset = parseDate((Node)xpath.evaluate("//aws:sunset", node, XPathConstants.NODE), xpath);
			this.stationZipcode = (String)xpath.evaluate("//aws:city-state/@zipcode", node, XPathConstants.STRING);
			this.stationCityState = (String)xpath.evaluate("//aws:city-state", node, XPathConstants.STRING);
			this.stationCountry = (String)xpath.evaluate("//aws:country", node, XPathConstants.STRING);
			this.stationLatitude = (Double)xpath.evaluate("//aws:latitude", node, XPathConstants.NUMBER);
			this.stationLongitude = (Double)xpath.evaluate("//aws:longitude", node, XPathConstants.NUMBER);
			
			this.currentConditions = (String)xpath.evaluate("//aws:current-condition", node, XPathConstants.STRING);
			this.currentImageURL = (String)xpath.evaluate("//aws:current-condition/@icon", node, XPathConstants.STRING);
			this.dewPoint = (Double)xpath.evaluate("//aws:dew-point", node, XPathConstants.NUMBER);
			this.dewPointUnits = (String)xpath.evaluate("//aws:dew-point/@units", node, XPathConstants.STRING);
			this.elevation = (Double)xpath.evaluate("//aws:elevation", node, XPathConstants.NUMBER);
			this.elevationUnits = (String)xpath.evaluate("//aws:elevation/@units", node, XPathConstants.STRING);
			this.feelsLikeTemp = (Double)xpath.evaluate("//aws:feels-like", node, XPathConstants.NUMBER);
			this.feelsLikeTempUnits = (String)xpath.evaluate("//aws:feels-like/@units", node, XPathConstants.STRING);
			this.gustDirectionStr = (String)xpath.evaluate("//aws:gust-direction", node, XPathConstants.STRING);
			this.gustDirectionDegs = (Double)xpath.evaluate("//aws:gust-direction-degrees", node, XPathConstants.NUMBER);
			this.gustSpeed = (Double)xpath.evaluate("//aws:gust-speed", node, XPathConstants.NUMBER);
			this.gustSpeedUnits = (String)xpath.evaluate("//aws:gust-speed/@units", node, XPathConstants.STRING);
			
			this.humidity = (Double)xpath.evaluate("//aws:humidity", node, XPathConstants.NUMBER);
			this.humidityUnits = (String)xpath.evaluate("//aws:humidity/@units", node, XPathConstants.STRING);
			this.humidityLow = (Double)xpath.evaluate("//aws:humidity-low", node, XPathConstants.NUMBER);
			this.humidityLowUnits = (String)xpath.evaluate("//aws:humidity-low/@units", node, XPathConstants.STRING);
			this.humidityHigh = (Double)xpath.evaluate("//aws:humidity-high", node, XPathConstants.NUMBER);
			this.humidityHighUnits = (String)xpath.evaluate("//aws:humidity-high/@units", node, XPathConstants.STRING);
			this.humidityRate = (Double)xpath.evaluate("//aws:humidity-rate", node, XPathConstants.NUMBER);
			
			this.light = (Double)xpath.evaluate("//aws:light", node, XPathConstants.NUMBER);
			this.lightRate = (Double)xpath.evaluate("//aws:light-rate", node, XPathConstants.NUMBER);
			this.moonPhase = (Double)xpath.evaluate("//aws:moon-phase", node, XPathConstants.NUMBER);
			this.moonPhaseImageURL = (String)xpath.evaluate("//aws:moon-phase/@moon-phase-img", node, XPathConstants.STRING);

			this.pressure = (Double)xpath.evaluate("//aws:pressure", node, XPathConstants.NUMBER);
			this.pressureUnits = (String)xpath.evaluate("//aws:pressure/@units", node, XPathConstants.STRING);
			this.pressureHigh = (Double)xpath.evaluate("//aws:pressure-high", node, XPathConstants.NUMBER);
			this.pressureHighUnits = (String)xpath.evaluate("//aws:pressure-high/@units", node, XPathConstants.STRING);
			this.pressureLow = (Double)xpath.evaluate("//aws:pressure-low", node, XPathConstants.NUMBER);
			this.pressureLowUnits = (String)xpath.evaluate("//aws:pressure-low/@units", node, XPathConstants.STRING);
			this.pressureRate = (Double)xpath.evaluate("//aws:pressure-rate", node, XPathConstants.NUMBER);
			this.pressureRateUnits = (String)xpath.evaluate("//aws:pressure-rate/@units", node, XPathConstants.STRING);
			
			this.rainMonth = (Double)xpath.evaluate("//aws:rain-month", node, XPathConstants.NUMBER);
			this.rainMonthUnits = (String)xpath.evaluate("//aws:rain-month/@units", node, XPathConstants.STRING);
			this.rainRate = (Double)xpath.evaluate("//aws:rain-rate", node, XPathConstants.NUMBER);
			this.rainRateUnits = (String)xpath.evaluate("//aws:rain-rate/@units", node, XPathConstants.STRING);
			this.rainRateMax = (Double)xpath.evaluate("//aws:rain-rate-max", node, XPathConstants.NUMBER);
			this.rainRateMaxUnits = (String)xpath.evaluate("//aws:rain-rate-max/@units", node, XPathConstants.STRING);
			this.rainToday = (Double)xpath.evaluate("//aws:rain-today", node, XPathConstants.NUMBER);
			this.rainTodayUnits = (String)xpath.evaluate("//aws:rain-today/@units", node, XPathConstants.STRING);
			this.rainYear = (Double)xpath.evaluate("//aws:rain-year", node, XPathConstants.NUMBER);
			this.rainYearUnits = (String)xpath.evaluate("//aws:rain-year/@units", node, XPathConstants.STRING);
			
			this.temp = (Double)xpath.evaluate("//aws:temp", node, XPathConstants.NUMBER);
			this.tempUnits = (String)xpath.evaluate("//aws:temp/@units", node, XPathConstants.STRING);
			this.tempHigh = (Double)xpath.evaluate("//aws:temp-high", node, XPathConstants.NUMBER);
			this.tempHighUnits = (String)xpath.evaluate("//aws:temp-high/@units", node, XPathConstants.STRING);
			this.tempLow = (Double)xpath.evaluate("//aws:temp-low", node, XPathConstants.NUMBER);
			this.tempLowUnits = (String)xpath.evaluate("//aws:temp-low/@units", node, XPathConstants.STRING);
			this.tempRate = (Double)xpath.evaluate("//aws:temp-rate", node, XPathConstants.NUMBER);
			this.tempRateUnits = (String)xpath.evaluate("//aws:temp-rate/@units", node, XPathConstants.STRING);
			
			this.indoorTemp = (Double)xpath.evaluate("//aws:indoor-temp", node, XPathConstants.NUMBER);
			this.indoorTempUnits = (String)xpath.evaluate("//aws:indoor-temp/@units", node, XPathConstants.STRING);
			this.indoorTempRate = (Double)xpath.evaluate("//aws:indoor-temp-rate", node, XPathConstants.NUMBER);
			this.indoorTempRateUnits = (String)xpath.evaluate("//aws:indoor-temp-rate/@units", node, XPathConstants.STRING);
						
			this.wetBulb = (Double)xpath.evaluate("//aws:wet-bulb", node, XPathConstants.NUMBER);
			this.wetBulbUnits = (String)xpath.evaluate("//aws:wet-bulb/@units", node, XPathConstants.STRING);
			this.windSpeed = (Double)xpath.evaluate("//aws:wind-speed", node, XPathConstants.NUMBER);
			this.windSpeedUnits = (String)xpath.evaluate("//aws:wind-speed/@units", node, XPathConstants.STRING);
			this.windSpeedAverage = (Double)xpath.evaluate("//aws:wind-speed-avg", node, XPathConstants.NUMBER);
			this.windSpeedAverageUnits = (String)xpath.evaluate("//aws:wind-speed-avg/@units", node, XPathConstants.STRING);
			this.windDirection = (String)xpath.evaluate("//aws:wind-direction", node, XPathConstants.STRING);
			this.windDegrees = (String)xpath.evaluate("//aws:wind-direction-degrees", node, XPathConstants.STRING);
			this.windDirectionAverage = (String)xpath.evaluate("//aws:wind-direction-avg", node, XPathConstants.STRING);
			
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}
	
	public static Calendar parseDate(Node dateNode, XPath xpath) {
		Calendar result = null;
		try {
			result = Calendar.getInstance(TimeZone.getTimeZone((String)(xpath.evaluate("./aws:time-zone/@abbrv", dateNode, XPathConstants.STRING))));
			result.set(Calendar.YEAR, ((Double)(xpath.evaluate("./aws:year/@number", dateNode, XPathConstants.NUMBER))).intValue());
			result.set(Calendar.MONTH, ((Double)(xpath.evaluate("./aws:month/@number", dateNode, XPathConstants.NUMBER))).intValue() - 1); //-1 because Java considers January to be 0... stupid Java.
			result.set(Calendar.DAY_OF_MONTH, ((Double)(xpath.evaluate("./aws:day/@number", dateNode, XPathConstants.NUMBER))).intValue() + 1); //Not sure why this is screwed up... Adding 1 to compensate??
			result.set(Calendar.HOUR_OF_DAY, ((Double)(xpath.evaluate("./aws:hour-24/@number", dateNode, XPathConstants.NUMBER))).intValue());
			result.set(Calendar.MINUTE, ((Double)(xpath.evaluate("./aws:minute/@number", dateNode, XPathConstants.NUMBER))).intValue());
			result.set(Calendar.SECOND, ((Double)(xpath.evaluate("./aws:second/@number", dateNode, XPathConstants.NUMBER))).intValue());
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public Calendar getObservationTime() {
		return observationTime;
	}

	public void setObservationTime(Calendar observationTime) {
		this.observationTime = observationTime;
	}

	public Calendar getGustTime() {
		return gustTime;
	}

	public void setGustTime(Calendar gustTime) {
		this.gustTime = gustTime;
	}

	public Calendar getSunrise() {
		return sunrise;
	}

	public void setSunrise(Calendar sunrise) {
		this.sunrise = sunrise;
	}

	public Calendar getSunset() {
		return sunset;
	}

	public void setSunset(Calendar sunset) {
		this.sunset = sunset;
	}

	public String getStationZipcode() {
		return stationZipcode;
	}

	public void setStationZipcode(String stationZipcode) {
		this.stationZipcode = stationZipcode;
	}

	public String getStationCityState() {
		return stationCityState;
	}

	public void setStationCityState(String stationCityState) {
		this.stationCityState = stationCityState;
	}

	public String getStationCountry() {
		return stationCountry;
	}

	public void setStationCountry(String stationCountry) {
		this.stationCountry = stationCountry;
	}

	public double getStationLatitude() {
		return stationLatitude;
	}

	public void setStationLatitude(double stationLatitude) {
		this.stationLatitude = stationLatitude;
	}

	public double getStationLongitude() {
		return stationLongitude;
	}

	public void setStationLongitude(double stationLongitude) {
		this.stationLongitude = stationLongitude;
	}

	public String getCurrentConditions() {
		return currentConditions;
	}

	public void setCurrentConditions(String currentConditions) {
		this.currentConditions = currentConditions;
	}

	public String getCurrentImageURL() {
		return currentImageURL;
	}

	public void setCurrentImageURL(String currentImageURL) {
		this.currentImageURL = currentImageURL;
	}

	public double getDewPoint() {
		return dewPoint;
	}

	public void setDewPoint(double dewPoint) {
		this.dewPoint = dewPoint;
	}

	public String getDewPointUnits() {
		return dewPointUnits;
	}

	public void setDewPointUnits(String dewPointUnits) {
		this.dewPointUnits = dewPointUnits;
	}

	public double getElevation() {
		return elevation;
	}

	public void setElevation(double elevation) {
		this.elevation = elevation;
	}

	public String getElevationUnits() {
		return elevationUnits;
	}

	public void setElevationUnits(String elevationUnits) {
		this.elevationUnits = elevationUnits;
	}

	public double getFeelsLikeTemp() {
		return feelsLikeTemp;
	}

	public void setFeelsLikeTemp(double feelsLikeTemp) {
		this.feelsLikeTemp = feelsLikeTemp;
	}

	public String getFeelsLikeTempUnits() {
		return feelsLikeTempUnits;
	}

	public void setFeelsLikeTempUnits(String feelsLikeTempUnits) {
		this.feelsLikeTempUnits = feelsLikeTempUnits;
	}

	public String getGustDirectionStr() {
		return gustDirectionStr;
	}

	public void setGustDirectionStr(String gustDirectionStr) {
		this.gustDirectionStr = gustDirectionStr;
	}

	public double getGustDirectionDegs() {
		return gustDirectionDegs;
	}

	public void setGustDirectionDegs(double gustDirectionDegs) {
		this.gustDirectionDegs = gustDirectionDegs;
	}

	public double getGustSpeed() {
		return gustSpeed;
	}

	public void setGustSpeed(double gustSpeed) {
		this.gustSpeed = gustSpeed;
	}

	public String getGustSpeedUnits() {
		return gustSpeedUnits;
	}

	public void setGustSpeedUnits(String gustSpeedUnits) {
		this.gustSpeedUnits = gustSpeedUnits;
	}

	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public String getHumidityUnits() {
		return humidityUnits;
	}

	public void setHumidityUnits(String humidityUnits) {
		this.humidityUnits = humidityUnits;
	}

	public double getHumidityLow() {
		return humidityLow;
	}

	public void setHumidityLow(double humidityLow) {
		this.humidityLow = humidityLow;
	}

	public String getHumidityLowUnits() {
		return humidityLowUnits;
	}

	public void setHumidityLowUnits(String humidityLowUnits) {
		this.humidityLowUnits = humidityLowUnits;
	}

	public double getHumidityHigh() {
		return humidityHigh;
	}

	public void setHumidityHigh(double humidityHigh) {
		this.humidityHigh = humidityHigh;
	}

	public String getHumidityHighUnits() {
		return humidityHighUnits;
	}

	public void setHumidityHighUnits(String humidityHighUnits) {
		this.humidityHighUnits = humidityHighUnits;
	}

	public double getHumidityRate() {
		return humidityRate;
	}

	public void setHumidityRate(double humidityRate) {
		this.humidityRate = humidityRate;
	}

	public double getLight() {
		return light;
	}

	public void setLight(double light) {
		this.light = light;
	}

	public double getLightRate() {
		return lightRate;
	}

	public void setLightRate(double lightRate) {
		this.lightRate = lightRate;
	}

	public double getMoonPhase() {
		return moonPhase;
	}

	public void setMoonPhase(double moonPhase) {
		this.moonPhase = moonPhase;
	}

	public String getMoonPhaseImageURL() {
		return moonPhaseImageURL;
	}

	public void setMoonPhaseImageURL(String moonPhaseImageURL) {
		this.moonPhaseImageURL = moonPhaseImageURL;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public String getPressureUnits() {
		return pressureUnits;
	}

	public void setPressureUnits(String pressureUnits) {
		this.pressureUnits = pressureUnits;
	}

	public double getPressureHigh() {
		return pressureHigh;
	}

	public void setPressureHigh(double pressureHigh) {
		this.pressureHigh = pressureHigh;
	}

	public String getPressureHighUnits() {
		return pressureHighUnits;
	}

	public void setPressureHighUnits(String pressureHighUnits) {
		this.pressureHighUnits = pressureHighUnits;
	}

	public double getPressureLow() {
		return pressureLow;
	}

	public void setPressureLow(double pressureLow) {
		this.pressureLow = pressureLow;
	}

	public String getPressureLowUnits() {
		return pressureLowUnits;
	}

	public void setPressureLowUnits(String pressureLowUnits) {
		this.pressureLowUnits = pressureLowUnits;
	}

	public double getPressureRate() {
		return pressureRate;
	}

	public void setPressureRate(double pressureRate) {
		this.pressureRate = pressureRate;
	}

	public String getPressureRateUnits() {
		return pressureRateUnits;
	}

	public void setPressureRateUnits(String pressureRateUnits) {
		this.pressureRateUnits = pressureRateUnits;
	}

	public double getRainMonth() {
		return rainMonth;
	}

	public void setRainMonth(double rainMonth) {
		this.rainMonth = rainMonth;
	}

	public String getRainMonthUnits() {
		return rainMonthUnits;
	}

	public void setRainMonthUnits(String rainMonthUnits) {
		this.rainMonthUnits = rainMonthUnits;
	}

	public double getRainRate() {
		return rainRate;
	}

	public void setRainRate(double rainRate) {
		this.rainRate = rainRate;
	}

	public String getRainRateUnits() {
		return rainRateUnits;
	}

	public void setRainRateUnits(String rainRateUnits) {
		this.rainRateUnits = rainRateUnits;
	}

	public double getRainRateMax() {
		return rainRateMax;
	}

	public void setRainRateMax(double rainRateMax) {
		this.rainRateMax = rainRateMax;
	}

	public String getRainRateMaxUnits() {
		return rainRateMaxUnits;
	}

	public void setRainRateMaxUnits(String rainRateMaxUnits) {
		this.rainRateMaxUnits = rainRateMaxUnits;
	}

	public double getRainToday() {
		return rainToday;
	}

	public void setRainToday(double rainToday) {
		this.rainToday = rainToday;
	}

	public String getRainTodayUnits() {
		return rainTodayUnits;
	}

	public void setRainTodayUnits(String rainTodayUnits) {
		this.rainTodayUnits = rainTodayUnits;
	}

	public double getRainYear() {
		return rainYear;
	}

	public void setRainYear(double rainYear) {
		this.rainYear = rainYear;
	}

	public String getRainYearUnits() {
		return rainYearUnits;
	}

	public void setRainYearUnits(String rainYearUnits) {
		this.rainYearUnits = rainYearUnits;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public String getTempUnits() {
		return tempUnits;
	}

	public void setTempUnits(String tempUnits) {
		this.tempUnits = tempUnits;
	}

	public double getTempHigh() {
		return tempHigh;
	}

	public void setTempHigh(double tempHigh) {
		this.tempHigh = tempHigh;
	}

	public String getTempHighUnits() {
		return tempHighUnits;
	}

	public void setTempHighUnits(String tempHighUnits) {
		this.tempHighUnits = tempHighUnits;
	}

	public double getTempLow() {
		return tempLow;
	}

	public void setTempLow(double tempLow) {
		this.tempLow = tempLow;
	}

	public String getTempLowUnits() {
		return tempLowUnits;
	}

	public void setTempLowUnits(String tempLowUnits) {
		this.tempLowUnits = tempLowUnits;
	}

	public double getTempRate() {
		return tempRate;
	}

	public void setTempRate(double tempRate) {
		this.tempRate = tempRate;
	}

	public String getTempRateUnits() {
		return tempRateUnits;
	}

	public void setTempRateUnits(String tempRateUnits) {
		this.tempRateUnits = tempRateUnits;
	}

	public double getIndoorTemp() {
		return indoorTemp;
	}

	public void setIndoorTemp(double indoorTemp) {
		this.indoorTemp = indoorTemp;
	}

	public String getIndoorTempUnits() {
		return indoorTempUnits;
	}

	public void setIndoorTempUnits(String indoorTempUnits) {
		this.indoorTempUnits = indoorTempUnits;
	}

	public double getIndoorTempRate() {
		return indoorTempRate;
	}

	public void setIndoorTempRate(double indoorTempRate) {
		this.indoorTempRate = indoorTempRate;
	}

	public String getIndoorTempRateUnits() {
		return indoorTempRateUnits;
	}

	public void setIndoorTempRateUnits(String indoorTempRateUnits) {
		this.indoorTempRateUnits = indoorTempRateUnits;
	}

	public double getWetBulb() {
		return wetBulb;
	}

	public void setWetBulb(double wetBulb) {
		this.wetBulb = wetBulb;
	}

	public String getWetBulbUnits() {
		return wetBulbUnits;
	}

	public void setWetBulbUnits(String wetBulbUnits) {
		this.wetBulbUnits = wetBulbUnits;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public String getWindSpeedUnits() {
		return windSpeedUnits;
	}

	public void setWindSpeedUnits(String windSpeedUnits) {
		this.windSpeedUnits = windSpeedUnits;
	}

	public double getWindSpeedAverage() {
		return windSpeedAverage;
	}

	public void setWindSpeedAverage(double windSpeedAverage) {
		this.windSpeedAverage = windSpeedAverage;
	}

	public String getWindSpeedAverageUnits() {
		return windSpeedAverageUnits;
	}

	public void setWindSpeedAverageUnits(String windSpeedAverageUnits) {
		this.windSpeedAverageUnits = windSpeedAverageUnits;
	}

	public String getWindDirection() {
		return windDirection;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getWindDegrees() {
		return windDegrees;
	}

	public void setWindDegrees(String windDegrees) {
		this.windDegrees = windDegrees;
	}

	public String getWindDirectionAverage() {
		return windDirectionAverage;
	}

	public void setWindDirectionAverage(String windDirectionAverage) {
		this.windDirectionAverage = windDirectionAverage;
	}

	public static Logger getLogger() {
		return logger;
	}
	
	
}
