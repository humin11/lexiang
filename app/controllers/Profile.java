package controllers;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import be.objectify.deadbolt.java.actions.Group;
import be.objectify.deadbolt.java.actions.Restrict;

import com.fasterxml.jackson.databind.JsonNode;

import models.City;
import models.Profiles;
import models.Province;
import models.User;
import play.*;
import play.data.format.Formats;
import play.libs.Json;
import play.mvc.*;

public class Profile extends Controller {

	public static Result getProvince(){
		List<Province> provinceList = Province.find.all();
		JsonNode json = Json.toJson(provinceList);
		return ok(json);
	}
	
	public static Result getCity(Long provinceid){
		Province province = Province.find.byId(provinceid);
		List<City> cityList = City.find.where().eq("provincecode", province.code).findList();
		JsonNode json = Json.toJson(cityList);
		return ok(json);
	}
	
	@Restrict(@Group(Application.USER_ROLE))
	public static Result updateProfile(){
		JsonNode params = request().body().asJson();
		final User u = Application.getLocalUser(session());
		Profiles profile = Profiles.find.where().eq("user", u).findUnique();
		if(profile==null){
			profile = new Profiles();
			profile.user = u;
		}
		if(params.has("birthday")){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				profile.birthday = df.parse(params.findPath("birthday").asText());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		profile.save();
		return ok();
	}
	
	@Restrict(@Group(Application.USER_ROLE))
	public static Result getProfile(){
		final User u = Application.getLocalUser(session());
		Profiles profile = Profiles.find.where().eq("user", u).findUnique();
		JsonNode json = Json.toJson(profile);
		return ok(json);
	}
	
}
