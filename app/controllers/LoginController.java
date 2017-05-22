/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.LoginController.login;
import dto.LoginUser;
import models.User;
import play.data.Form;
import play.mvc.*;
import static play.mvc.Results.ok;
import views.html.*;

/**
 * @author a-yamamoto
 */
public class LoginController extends Controller{
    /**
     * ログインページへ反映させる
     * @return 
     */
    public static Result login() {
        Form<LoginUser> form = new Form(LoginUser.class);
        return ok(loginpage.render(form));
    }
    /**
     * ログインボタンを押すと呼ばれる
     * メインページへ遷移する
     * @return 
     */
    public static Result doLogin() {
        Form<LoginUser> form = new Form(LoginUser.class).bindFromRequest();
        LoginUser requestuser = form.get();
        User user = User.find.where().eq("userid",requestuser.userid).findUnique();
        if(user != null){
            User userPass = User.find.where().eq("password", requestuser.password).findUnique();
            if(userPass != null){
                return redirect(routes.MainPageController.mainpage());
            }
            return redirect(routes.LoginController.login());
        }
        return redirect(routes.LoginController.login());
    }
    
    
    
    
}

