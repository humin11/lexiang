package controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.JsonNode;
import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.user.AuthUser;

import models.Communities;
import models.Tags;
import models.User;
import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import utils.Constants;
import views.html.community.*;

public class Community extends Controller {

	public static Result index() {
        List<Communities> communityList = Communities.find.all();
		return ok(index.render(communityList));
	}
	
	public static Result uniqueName(String name){
		Communities comm = Communities.find.where().eq("name", name).findUnique();
		if(comm!=null){
			return ok("existed");
		}
		return ok("");
	}
	
	@Restrict(@Group(Application.USER_ROLE))
	public static Result create() throws IOException {
		final AuthUser currentAuthUser = PlayAuthenticate.getUser(session());
		final User localUser = User.findByAuthUserIdentity(currentAuthUser);
		MultipartFormData form = request().body().asMultipartFormData();
		
		Communities comm = null;
        Tags tag = null;
        JsonNode result = null;
		if(form!=null){
			FilePart logoFile = form.getFile("logo");
			Map<String, String[]> formData = form.asFormUrlEncoded();
			String logo="";
			if(logoFile!=null){
				String fileName = logoFile.getFilename();
			    String contentType = logoFile.getContentType(); 
			    File file = logoFile.getFile();
                if(!new File("public/images/"+localUser.id).exists()){
                    new File("public/images/"+localUser.id).mkdirs();
                }
			    file.renameTo(new File("public/images/"+localUser.id, fileName));
                logo="images/"+localUser.id+"/"+fileName;
			}else{
				logo="images/community_default.png";
			}
			
			String name="";
			String bio="";
            String tags="";
			if(formData!=null){
				if(formData.containsKey("name")){
					name = formData.get("name")[0];
				}
				
				if(formData.containsKey("bio")){
					bio = formData.get("bio")[0];
				}

                if(formData.containsKey("tags")){
                    tags = formData.get("tags")[0];
                }
			}

			if(name!=null && !"".equals(name)){
				comm = Communities.find.where().eq("name", name).findUnique();
				if(comm==null){
					comm = new Communities();
				}
				comm.name = name;
				comm.bio = bio;
				comm.user = localUser;
				comm.logo = logo;
                comm.users.add(localUser);
                comm.save();
                result = Json.toJson(comm);
            }

            for (String tagName: tags.split(",")){
                tag = Tags.find.where().eq("name",tagName.trim()).findUnique();
                if(tag==null){
                    tag = new Tags();
                    tag.name = tagName;
                    tag.type = Constants.TAGS_COMMUNITY;
                }
                if(comm!=null){
                    tag.communities.add(comm);
                }
                tag.save();
            }
		}
		return ok(result.toString());
	}

	private static void saveFile(String fileName, String contentType, File file){
		
	}
}