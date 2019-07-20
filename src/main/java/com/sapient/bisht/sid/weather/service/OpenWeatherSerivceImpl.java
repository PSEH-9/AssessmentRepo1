package com.sapient.bisht.sid.weather.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.bisht.sid.weather.dto.DataPoint;
import com.sapient.bisht.sid.weather.dto.OpenWeatherResponseData;
import com.sapient.bisht.sid.weather.dto.Rain;
import com.sapient.bisht.sid.weather.dto.Temp;
import com.sapient.bisht.sid.weather.dto.Weather;
import com.sapient.bisht.sid.weather.dto.Wind;

@Component
@Slf4j
public class OpenWeatherSerivceImpl implements OpenWeatherSerivce {

	@Autowired
	RestTemplate restTemplate;

	@Value("${weather.api.url}")
	String apiURL;

	@Value("${weather.api.appid}")
	String appId;

	private Logger log = Logger.getLogger(this.getClass());

	private String buildURL(String city, String country) {
		StringBuilder sb = new StringBuilder();
		String tempURL = new String(apiURL);

		if (StringUtils.isEmpty(city)) {
			city = "Bangalore";
			if (StringUtils.isEmpty(country)) {
				country = "in";
			}
		}
		if (StringUtils.isEmpty(country))
			tempURL = tempURL.replace("@@PARAM@@", city);
		else
			tempURL = tempURL.replace("@@PARAM@@", city + "," + country);
		sb.append(tempURL).append("&appid=").append(appId);

		return sb.toString();
	}

	public OpenWeatherResponseData getForcast(String city, String country) {
		OpenWeatherResponseData responseObj = null;
		HttpEntity<String> response = restTemplate.exchange(
				buildURL(city, country), HttpMethod.GET, new HttpEntity<>(
						new HttpHeaders()), String.class);

		ObjectMapper om = new ObjectMapper();

		try {
			log.debug("response from service: " + response.getBody());
			responseObj = om.readValue(response.getBody(),
					OpenWeatherResponseData.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		responseObj = processThreeDays(responseObj);
		return responseObj;
	}

	private OpenWeatherResponseData processThreeDays(
			OpenWeatherResponseData responseObj) {

		List<DataPoint> threeDayData = new ArrayList<>();

		Map<LocalDate, List<DataPoint>> dailyData = responseObj
				.getList()
				.stream()
				.collect(
						Collectors.groupingBy(data -> formatDate(data
								.getDateLong())));
		System.out.println(dailyData);

		LocalDate ld = formatDate(Calendar.getInstance().getTime().getTime()/1000);

		for (int i = 0; i < 3; i++) {
			ld.plus(i, ChronoUnit.DAYS);
			DataPoint dp = new DataPoint();
			dp.setDateText(ld.get(ChronoField.YEAR) + "-" + ld.getMonthValue() + "-"
					+ ld.getDayOfMonth());
			dp.setRain(processRain(dailyData.get(ld)));
			dp.setTempData(processTemp(dailyData.get(ld)));
			dp.setWind(processWind(dailyData.get(ld)));
			dp.setWeather(processWeather((dailyData.get(ld))));
			dp.setMessage(processForMessage(dp.getWeather(), dp.getTempData()));
			threeDayData.add(dp);
		}
		responseObj.setCnt(3);
		responseObj.setList(threeDayData);
		return responseObj;
	}

	private String processForMessage(List<Weather> weatherList, Temp temp) {
		if(temp.getTemp() > 40){
			return "Use sunscreen lotion!!";
		}

		int finalCode = 1;
		for (Weather weather : weatherList){
			switch (""+weather.getId()) {
			case "200":
			case "201":
			case "210":
			case "211":
			case "212":
			case "230":
			case "231":
			case "232":
			case "300":
			case "301":
			case "302":
			case "310":
			case "311":
			case "312":
			case "313":
			case "500":
			case "501":
			case "520":
			case "521":
			case "600":
			case "601":
			case "611":
			case "612":
			case "615":
			case "620":
			case "621":
				if (finalCode < 2)
					finalCode = 2;
				break;
			case "202":
			case "221":
			case "314":
			case "321":
			case "502":
			case "503":
			case "504":
			case "511":
			case "522":
			case "531":
			case "602":
			case "613":
			case "616":
			case "622":
			case "803":
			case "804":
				if (finalCode < 3)
					finalCode = 3;
				break;
			case "800":
			case "801":
			case "802":
			default:
				break;
			}
			
		}
		if (finalCode == 1)
			return "its a nice weather...";
		else if (finalCode == 2)
			return "there is a possibility of rain/shower.., its advisible to carry a jacket.";
		else
			return "rough weather conditions.. its advisible to stay indoors";
	}

	private List<Weather> processWeather(List<DataPoint> list) {
		Set<Weather> set = new HashSet<>();
		for(DataPoint dp : list){
			if(dp.getWeather() != null){
				set.addAll(dp.getWeather());
			}
		}
		List<Weather> temp = new ArrayList<Weather>();
		temp.addAll(set);
		return temp;
	}

	private Wind processWind(List<DataPoint> list) {
		Wind wind = new Wind();

		wind.setSpeed(
				list.stream()
					.filter(dp -> dp.getWind()!=null && dp.getWind().getSpeed()!=null)
					.collect(Collectors.toList())
				.stream().mapToDouble(dp -> dp.getWind().getSpeed())
					.average()
					.orElse(Double.NaN));
		wind.setDeg(
				list.stream()
					.filter(dp -> dp.getWind()!=null && dp.getWind().getDeg()!=null)
					.collect(Collectors.toList())
				.stream().mapToDouble(dp -> dp.getWind().getDeg())
					.average()
					.orElse(Double.NaN));
		
		return wind;
	}

	private Temp processTemp(List<DataPoint> list) {
		Temp temp = new Temp();
		temp.setGrnd_level(list.get(0).getTempData().getGrnd_level());
		temp.setSea_level(list.get(0).getTempData().getSea_level());
		temp.setTemp_kf(list.get(0).getTempData().getTemp_kf());
		temp.setHumidity(
				list.stream()
					.filter(dp -> dp.getTempData()!=null && dp.getTempData().getHumidity()!=null)
					.collect(Collectors.toList())
				.stream().mapToDouble(dp -> dp.getTempData().getHumidity())
					.average()
					.orElse(Double.NaN));
		temp.setPressure(
				list.stream()
					.filter(dp -> dp.getTempData()!=null && dp.getTempData().getPressure()!=null)
					.collect(Collectors.toList())
				.stream().mapToDouble(dp -> dp.getTempData().getPressure())
					.average()
					.orElse(Double.NaN));
		temp.setTemp(
				list.stream()
					.filter(dp -> dp.getTempData()!=null && dp.getTempData().getTemp()!=null)
					.collect(Collectors.toList())
				.stream().mapToDouble(dp -> dp.getTempData().getTemp())
					.average()
					.orElse(Double.NaN));
		temp.setTemp_min(
				list.stream()
					.filter(dp -> dp.getTempData()!=null && dp.getTempData().getTemp_min()!=null)
					.collect(Collectors.toList())
				.stream().mapToDouble(dp -> dp.getTempData().getTemp_min())
					.min()
					.orElse(Double.NaN));
		temp.setTemp_max(
				list.stream()
					.filter(dp -> dp.getTempData()!=null && dp.getTempData().getTemp_max()!=null)
					.collect(Collectors.toList())
				.stream().mapToDouble(dp -> dp.getTempData().getTemp_max())
					.max()
					.orElse(Double.NaN));

		return temp;
	}

	private Rain processRain(List<DataPoint> list) {
		Rain rain = new Rain();
		rain.setThreeH(
				list.stream()
					.filter(dp -> dp.getRain()!=null && dp.getRain().getThreeH()!=null)
					.collect(Collectors.toList())
				.stream().mapToDouble(dp -> dp.getRain().getThreeH())
					.average()
					.orElse(Double.NaN));
		return rain;
	}

	private LocalDate formatDate(Long dateLong) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(dateLong * 1000);

		ZoneId gmtZoneId = ZoneId.of("GMT");
		return cal.getTime().toInstant().atZone(gmtZoneId).toLocalDate();
	}
}
