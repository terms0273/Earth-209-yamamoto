/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
    
  public static void setSession(User user){
    session("userid",user.userid);
    session("username",user.username);
}
    
    /**
     * ログインボタンを押すと呼ばれる
     * メインページへ遷移する
     * @return 
     */
    public static Result doLogin() {
        Form<LoginUser> form = new Form(LoginUser.class).bindFromRequest();
        LoginUser requestuser = form.get();
        //DBに保存されている値の中に入力されたuseridと一致するものがあるか調べる
        User user = User.find.where().eq("userid",requestuser.userid).findUnique();
        if(user != null){
            if(user.password.equals(requestuser.password)){
                //ログイン可能時useridの値をsessionに保存したい
                setSession(user);
                return redirect(routes.MainPageController.mainpage());
            }
            flash("error","IDかPasswordもしくはその両方が間違っています。");
            return redirect(routes.LoginController.login());
        }
        flash("error","IDかPasswordもしくはその両方が間違っています。");
        return redirect(routes.LoginController.login());
    }
    
    
    
    
}

