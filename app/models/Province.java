package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import play.db.ebean.Model;

@Entity
@Table(name = "province")
public class Province extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	public String code;

	@JsonProperty("text")
	public String name;

	public static Finder<Long, Province> find = new Finder<Long, Province>(
			Long.class, Province.class);

}
