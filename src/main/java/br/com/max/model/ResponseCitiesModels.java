package br.com.max.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cities")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseCitiesModels {

	@XmlElement(name = "cities")
	private List<ResponseCitiesModel> cities = null;

	public List<ResponseCitiesModel> getCities() {
		return cities;
	}

	public void setCities(List<ResponseCitiesModel> cities) {
		this.cities = cities;
	}

}
