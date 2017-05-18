/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.LogoutController.logout;
import models.User;
import play.data.Form;
import play.mvc.*;
import static play.mvc.Results.ok;
import views.html.*;

/**
 *
 * @author a-yamamoto
 */
public class LogoutController extends Controller{
    
    public static Result logout() {
        Form<User> form = new Form(User.class);   
        return ok(logoutpage.render(form));
    }
    
    public static Result doLogout() {
        Form<User> form = new Form(User.class).bindFromRequest();
        return redirect(routes.LoginController.login());
    }
}