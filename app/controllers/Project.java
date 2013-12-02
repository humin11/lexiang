package controllers;

import java.text.SimpleDateFormat;
import java.util.Date;

import models.User;
import play.Routes;
import play.data.Form;
import play.mvc.*;
import play.mvc.Http.Response;
import play.mvc.Http.Session;
import play.mvc.Result;

import views.html.*;

public class Project extends Controller {

	public static Result index() {
		return ok(index.render());
	}

}