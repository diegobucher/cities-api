package br.com.hbsis.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.hbsis.dao.CityDAO;
import br.com.hbsis.entity.City;

@ManagedBean
@ViewScoped
public class CityMB {

	private City city;
	private List<City> cities;
	private CityDAO dao;

	@PostConstruct
	public void init() {
		dao = new CityDAO();
		cities = dao.findAll();

		clean();
	}

	public void save() {
		dao = new CityDAO();
		dao.saveOrUpdate(city);

		init();
	}

	public void delete(City city) {
		if (dao == null)
			dao = new CityDAO();
		dao.delete(city);
		init();
	}

	public void load(City city) {
		this.city = city;
	}

	public void clean() {
		city = new City();

	}

	// ------------------------------

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	public CityDAO getDao() {
		return dao;
	}

	public void setDao(CityDAO dao) {
		this.dao = dao;
	}

}
