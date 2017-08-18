package br.com.max.dao;

import java.util.List;

import br.com.max.model.CitiesModel;

public interface CitiesDAO {

	public CitiesModel findById(Integer id);
	public List<CitiesModel> listCities();
}
