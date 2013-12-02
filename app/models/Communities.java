package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "communities")
public class Communities extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	public Long id;
	
	@Required
	public String logo;
	
	@Required
	public String name;
	
	@Required
	@MaxLength(2048)
	public String bio;
	
	@OneToOne
	@JsonIgnore
	public User user;

	public static Finder<Long, Communities> find = new Finder<Long, Communities>(
			Long.class, Communities.class);
}
