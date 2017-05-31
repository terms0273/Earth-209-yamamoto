/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authenticator;

import play.mvc.*;
import controllers.routes;

/**
 *
 * @author a-yamamoto
 */
public class Secured extends Security.Authenticator{
    @Override
    public String getUsername(Http.Context ctx){
        return ctx.session().get("userid");
    }
    
    @Override
    public Result onUnauthorized(Http.Context ctx){
        return redirect(routes.LoginController.login());
    }
}
