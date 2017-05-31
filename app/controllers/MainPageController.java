/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Authenticator.Secured;
import org.hibernate.validator.internal.util.ConcurrentReferenceHashMap.Option;
import play.mvc.*;
import static play.mvc.Results.ok;
import views.html.*;

/**
 *メインページへ反映させる
 * @author a-yamamoto
 */
public class MainPageController extends Controller {
    @Security.Authenticated(Secured.class)
    public static Result mainpage() {
//        String mySession = session("userid");
//        if(mySession == null){
//            return redirect(routes.LoginController.login());
//        }else{
            return ok(mainpage.render());
//        }
    }
    
}