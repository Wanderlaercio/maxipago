package main.java.br.com.max.resource;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import main.java.br.com.max.dao.CitiesDAOImpl;
import main.java.br.com.max.model.CitiesModel;
import main.java.br.com.max.model.ResponseCitiesModel;
import main.java.br.com.max.model.ResponseCitiesModels;



@RestController
public class CitiesResource {

	@RequestMapping(value = "/cities/json/{type}", method = RequestMethod.GET)
	public List<ResponseCitiesModel> calculateBetweenJSON(@PathVariable String type) {
		List<ResponseCitiesModel> response = new ArrayList<ResponseCitiesModel>();
		response = calculateDistance(type);
		return response;
	}

	@RequestMapping(value = "/cities/xml/{type}", produces = MediaType.APPLICATION_XML, method = RequestMethod.GET)
	public @ResponseBody String calculateBetweenXML(@PathVariable String type) {
		try {
			ResponseCitiesModels cities = new ResponseCitiesModels();
			List<ResponseCitiesModel> response = new ArrayList<ResponseCitiesModel>();
			response = calculateDistance(type);
			cities.setCities(response);

			JAXBContext jaxbContext = JAXBContext.newInstance(ResponseCitiesModels.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Marshal the employees list in console
			StringWriter w = new StringWriter();
			jaxbMarshaller.marshal(cities, w);
			return w.toString();
		} catch (JAXBException e) {
			System.out.println("Cannot unmarshaller the OBJECT to STRING, please check erros\n" + e);
		}
		return null;
	}

	public List<ResponseCitiesModel> calculateDistance(String type) {
		List<ResponseCitiesModel> response = new ArrayList<ResponseCitiesModel>();
		List<CitiesModel> cities = new CitiesDAOImpl().listCities();
		for (CitiesModel c : cities) {
			List<CitiesModel> cities2 = new CitiesDAOImpl().listCities();
			for (CitiesModel c2 : cities2) {
				if (c.getId() != c2.getId()) {
					response.add(geoCalculate(c, c2, type));
				}
			}
		}

		return response;
	}

	public ResponseCitiesModel geoCalculate(CitiesModel firstCity, CitiesModel secondCity, String type) {
		GeodeticCalculator geoCalc = new GeodeticCalculator();
		ResponseCitiesModel responseCitiesModel = new ResponseCitiesModel();
		Ellipsoid reference = Ellipsoid.WGS84;

		GlobalPosition pointA = new GlobalPosition(firstCity.getLatitude(), firstCity.getLongitude(), 0.0);

		GlobalPosition userPos = new GlobalPosition(secondCity.getLatitude(), secondCity.getLongitude(), 0.0);

		responseCitiesModel.setFirstCity(firstCity.getName());
		responseCitiesModel.setSecondCity(secondCity.getName());
		if (type.equals("km")) {
			responseCitiesModel.setDistance(
					(geoCalc.calculateGeodeticCurve(reference, userPos, pointA).getEllipsoidalDistance() / 100) * 100);

		}
		if (type.equals("mi")) {
			responseCitiesModel.setDistance(
					geoCalc.calculateGeodeticCurve(reference, userPos, pointA).getEllipsoidalDistance() * 0.62137);

		}

		return responseCitiesModel;
	}

}