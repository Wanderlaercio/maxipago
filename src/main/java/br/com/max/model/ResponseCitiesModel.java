package main.java.br.com.max.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "city")
@XmlAccessorType (XmlAccessType.FIELD)
public class ResponseCitiesModel {

	private String firstCity;
	private String secondCity;
	private Double distance;

	@XmlElement(name = "cities")
	private List<ResponseCitiesModel> cities = null;

	public String getFirstCity() {
		return firstCity;
	}

	public void setFirstCity(String firstCity) {
		this.firstCity = firstCity;
	}

	public String getSecondCity() {
		return secondCity;
	}

	public void setSecondCity(String secondCity) {
		this.secondCity = secondCity;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public List<ResponseCitiesModel> getCities() {
		return cities;
	}

	public void setCities(List<ResponseCitiesModel> cities) {
		this.cities = cities;
	}

}
