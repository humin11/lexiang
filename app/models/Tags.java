package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Humin on 12/4/13.
 */

@Entity
@Table(name = "tags")
public class Tags extends Model{

    @Id
    public Long id;

    @Constraints.Required
    public String name;

    /**
     * type: Community, Blog, User
     */
    public String type;

    @ManyToMany(mappedBy = "tags")
    public List<Communities> communities;

    public static Finder<Long, Tags> find = new Finder<Long, Tags>(
            Long.class, Tags.class);
}
