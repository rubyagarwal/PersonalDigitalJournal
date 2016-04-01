package data;


public class Place {

	private String name;
	private String street;
	private String city;
	private String state;
	private String country;

	public String getName() {
		return name;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCountry(String country) {
		this.country = country;
	}

//	@Override
//	public String toString() {
//		return "Place [name=" + name + ", street=" + street + ", city=" + city
//				+ ", state=" + state + ", country=" + country + "]";
//	}

}
