/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Authenticator.Secured;
import dto.LoginUser;
import models.User;
import play.data.Form;
import play.mvc.*;
import static play.mvc.Results.ok;
import views.html.*;
import org.mindrot.jbcrypt.BCrypt;

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
     * sessionに保存したい値
     * @param user 
     */
  private static void setSession(User user){
    session("userid",user.userid);
    session("username",user.username);
    session("type",user.type.toString());
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
        /**
         * 論理削除されているidじゃないか調べる(deleteFlagがtrueなら削除されてる)
         */
        if(user.deleteFlag == false){
            if(user != null){
                //パスワードの暗号化
                if(BCrypt.checkpw(requestuser.password, user.password)){
                //ログイン可能時useridの値をsessionに保存する
                setSession(user);
                return redirect(routes.MainPageController.mainpage());
                }
                flash("error","IDかPasswordもしくはその両方が間違っています。");
                return redirect(routes.LoginController.login());
            }
            flash("error","IDかPasswordもしくはその両方が間違っています。");
            return redirect(routes.LoginController.login());
        }
        flash("error","存在しないIDです。");
        return redirect(routes.LoginController.login());
    }  
}

