package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
@Table(name = "area")
public class Area extends Model{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;
	
	public String code;
	
	public String name;
	
	public String citycode;
	
	public static Finder<Long, Area> find = new Finder<Long, Area>(
			Long.class, Area.class);
}
