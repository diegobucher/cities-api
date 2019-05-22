package br.com.hbsis.bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
		if (isValidCity()) {
			dao = new CityDAO();
			dao.saveOrUpdate(city);

			init();
		}
	}

	private boolean isValidCity() {
		try {
			URL url = new URL("https://servicodados.ibge.gov.br/api/v1/localidades/municipios");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Error accessing the service: " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String retorno;
			while ((retorno = br.readLine()) != null) {
				if (!retorno.contains(city.getDsCity())) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "This city is invalid", null));
					conn.disconnect();
					return false;
				}
			}

			conn.disconnect();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
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
