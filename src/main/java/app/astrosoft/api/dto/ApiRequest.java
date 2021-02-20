package app.astrosoft.api.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import app.astrosoft.beans.Place.Direction;
import app.astrosoft.beans.Place.Location;
import app.astrosoft.consts.Nakshathra;
import app.astrosoft.consts.Rasi;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Request boyHoroscope;
	private Request girlHoroscope;
	private String boyName; 
	private String girlName;
	private Rasi boyRasi;
	private Rasi girlRasi;
	private Nakshathra boyNak;
	private Nakshathra girlNak;

	public Request getBoyHoroscope() {
		return boyHoroscope;
	}


	public void setBoyHoroscope(Request boyHoroscope) {
		this.boyHoroscope = boyHoroscope;
	}


	public Request getGirlHoroscope() {
		return girlHoroscope;
	}


	public void setGirlHoroscope(Request girlHoroscope) {
		this.girlHoroscope = girlHoroscope;
	}


	public String getBoyName() {
		return boyName;
	}


	public void setBoyName(String boyName) {
		this.boyName = boyName;
	}

	public String getGirlName() {
		return girlName;
	}


	public void setGirlName(String girlName) {
		this.girlName = girlName;
	}


	public Rasi getBoyRasi() {
		return boyRasi;
	}


	public void setBoyRasi(Rasi boyRasi) {
		this.boyRasi = boyRasi;
	}


	public Rasi getGirlRasi() {
		return girlRasi;
	}


	public void setGirlRasi(Rasi girlRasi) {
		this.girlRasi = girlRasi;
	}


	public Nakshathra getBoyNak() {
		return boyNak;
	}


	public void setBoyNak(Nakshathra boyNak) {
		this.boyNak = boyNak;
	}


	public Nakshathra getGirlNak() {
		return girlNak;
	}


	public void setGirlNak(Nakshathra girlNak) {
		this.girlNak = girlNak;
	}




	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Request implements Serializable {
		private static final long serialVersionUID = 1L;
		private String name;
		private String sex;
		@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	    private Date birthDate;
		private String city;
		private double timeZone;
		/*
		 * private double latitude; private double longitude;
		 */
		@JsonProperty("latitude")
		private Map<String,Object> latitude;
		@JsonProperty("longitude")
		private Map<String,Object> longitude;


		public Map<String, Object> getLatitude() {
			return latitude;
		}

		public void setLatitude(Map<String, Object> latitude) {
			this.latitude = latitude;
		}

		public Map<String, Object> getLongitude() {
			return longitude;
		}

		public void setLongitude(Map<String, Object> longitude) {
			this.longitude = longitude;
		}

		public Location getLatitudeValue() {
			return new Location(latitude.get("deg").toString(), latitude.get("min").toString(), Enum.valueOf(Direction.class, latitude.get("dir").toString()));	
		}
		
		public Location getLongitudeValue() {
			return new Location(longitude.get("deg").toString(), longitude.get("min").toString(), Enum.valueOf(Direction.class, longitude.get("dir").toString()));	
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public double getTimeZone() {
			return timeZone;
		}

		public void setTimeZone(double timeZone) {
			this.timeZone = timeZone;
		}

		public Date getBirthDate() {
			return birthDate;
		}

		public void setBirthDate(Date birthDate) {
			this.birthDate = birthDate;
		}
	}
}
