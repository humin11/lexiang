package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
@Table(name = "city")
public class City extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	public String code;

	@JsonProperty("text")
	public String name;

	public String provincecode;

	public static Finder<Long, City> find = new Finder<Long, City>(
			Long.class, City.class);
}
