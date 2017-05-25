/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Authenticator.Secured;
import models.User;
import play.mvc.*;
import static play.mvc.Results.ok;
import views.html.*;

/**
 *
 * @author a-yamamoto
 */
/**
 * ログアウト画面へ反映
 * @author a-yamamoto
 */
public class LogoutController extends Controller{
    
    //session破棄のメソッド
    public static void clearSession(){
        session().clear();
    }
    
    @Security.Authenticated(Secured.class)
    public static Result doLogout() {
        String mySession = session("userid");
//        if(mySession == null){
//            return redirect(routes.LoginController.login());
//        }else{
//            clearSession();
            return redirect(routes.LoginController.login());
        }
//    }
    
  
    
}