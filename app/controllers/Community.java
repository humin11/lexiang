package controllers;

import java.io.File;
import java.util.Map;

import com.feth.play.module.pa.PlayAuthenticate;
import com.feth.play.module.pa.user.AuthUser;

import models.Communities;
import models.User;
import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import views.html.community.*;

public class Community extends Controller {

	public static Result index() {
		return ok(index.render());
	}
	
	public static Result uniqueName(String name){
		Communities comm = Communities.find.where().eq("name", name).findUnique();
		if(comm!=null){
			return ok("existed");
		}
		return ok("");
	}
	
	@Restrict(@Group(Application.USER_ROLE))
	public static Result create(){
		final AuthUser currentAuthUser = PlayAuthenticate.getUser(session());
		final User localUser = User.findByAuthUserIdentity(currentAuthUser);
		MultipartFormData form = request().body().asMultipartFormData();
		
		Communities comm = null;
		if(form!=null){
			FilePart logoFile = form.getFile("logo");
			Map<String, String[]> formData = form.asFormUrlEncoded();
			String logo="";
			if(logoFile!=null){
				String fileName = logoFile.getFilename();
			    String contentType = logoFile.getContentType(); 
			    File file = logoFile.getFile();
			    file.renameTo(new File("public/images/"+localUser.id, fileName));
			    logo="images/"+localUser.id+"/"+fileName;
			}else{
				logo="images/community_default.png";
			}
			
			String name="";
			String bio="";
			if(formData!=null){
				if(formData.containsKey("name")){
					name = formData.get("name")[0];
				}
				
				if(formData.containsKey("bio")){
					bio = formData.get("bio")[0];
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
				comm.save();
			}
		}
		return ok("Okay");
	}

	private static void saveFile(String fileName, String contentType, File file){
		
	}
}