package br.com.hbsis.bean;

import br.com.hbsis.entity.City;
import junit.framework.TestCase;

public class CityMBTest extends TestCase {
	private CityMB cityMB;

	protected void setUp() throws Exception {
		super.setUp();
		cityMB = new CityMB();
	}

	public void testInit() {
		cityMB.init();
	}

	public void testSave() {
		cityMB.save();
	}

	public void testDelete() {
		City city = new City();
		city.setId(1L);
		city.setDsCity("Blumenau");
		cityMB.delete(city);
	}

}
