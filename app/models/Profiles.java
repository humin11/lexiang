package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import play.data.format.Formats;
import play.db.ebean.Model;

@Entity
@Table(name = "profiles")
public class Profiles extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	public Long id;
	
	@Formats.DateTime(pattern = "yyyy-MM-dd")
	public Date birthday;
	
	public int height;
	
	public int weight;
	
	public String skills;
	
	public String bio;
	
	@OneToOne
	@JsonIgnore
	public User user;

	public static Finder<Long, Profiles> find = new Finder<Long, Profiles>(
			Long.class, Profiles.class);
}
