/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
        Form<User> form = new Form(User.class);
        return ok(loginpage.render(form));
    }
    
    /**
     * ログインボタンを押すと呼ばれる
     * メインページへ遷移する
     * @return 
     */
    public static Result doLogin() {
        Form<User> form = new Form(User.class).bindFromRequest();
        return redirect(routes.MainPageController.mainpage());
    }
}

