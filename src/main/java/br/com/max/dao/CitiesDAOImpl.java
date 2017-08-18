package main.java.br.com.max.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.br.com.max.jdbc.ConnectionFactory;
import main.java.br.com.max.model.CitiesModel;



public class CitiesDAOImpl implements CitiesDAO {

	Connection conn = null;
	Statement stmt = null;

	@Override
	public CitiesModel findById(Integer id) {

		CitiesModel model = new CitiesModel();

		try {
			String sql = "SELECT * FROM cities WHERE id =" + id;
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				model.setId(rs.getInt("id"));
				model.setName(rs.getString("name"));
				model.setLatitude(rs.getDouble("latitude"));
				model.setLongitude(rs.getDouble("longitude"));
			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return model;
	}

	@Override
	public List<CitiesModel> listCities() {

		List<CitiesModel> cities = new ArrayList<CitiesModel>();

		try {
			String sql = "SELECT * FROM cities";
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			stmt.executeQuery(sql);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				CitiesModel citiesModel = new CitiesModel();
				citiesModel.setId(rs.getInt("id"));
				citiesModel.setName(rs.getString("name"));
				citiesModel.setLatitude(rs.getDouble("latitude"));
				citiesModel.setLongitude(rs.getDouble("longitude"));
				cities.add(citiesModel);

			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cities;
	}
	
	public static void main(String[] args) {
		CitiesDAOImpl dao = new CitiesDAOImpl();
		List<CitiesModel> cities = new ArrayList<CitiesModel>();
		cities = dao.listCities();
		cities.forEach(item->System.out.println(item.getName()));
	}

}
