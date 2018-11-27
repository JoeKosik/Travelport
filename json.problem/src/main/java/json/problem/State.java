package json.problem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class State {

	private Integer id;	
	private String country;
	private String name;

	private String abbreviation;
	
	private String area;
	
	private String largestCity;
	
	private String capital;
	
	public State() {
		super();
	}
	
	public State(Integer id, String country, String name, String abbreviation, String area, String largestCity,
			String capital) {
		this();
		this.id = id;
		this.country = country;
		this.name = name;
		this.abbreviation = abbreviation;
		this.area = area;
		this.largestCity = largestCity;
		this.capital = capital;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbr() {
		return abbreviation;
	}

	public void setAbbr(String abbreviaion) {
		this.abbreviation = abbreviaion;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLargest_city() {
		return largestCity;
	}

	public void setLargest_city(String largestCity) {
		this.largestCity = largestCity;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", country=" + country + ", name=" + name + ", abbreviaion=" + abbreviation
				+ ", area=" + area + ", largestCity=" + largestCity + ", capital=" + capital + "]";
	}
	
}
