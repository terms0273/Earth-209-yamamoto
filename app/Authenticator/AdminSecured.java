/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Authenticator;

import play.*;
import play.mvc.*;
import views.html.*;
import controllers.*;
import play.Logger;

/**
 *
 * @author a-yamamoto
 */
public class AdminSecured extends Security.Authenticator {
    @Override
    public String getUsername(Http.Context ctx){
        //通常のログインチェック
        String userid = ctx.session().get("userid");
        if(userid == null){
            return null;
        }
        
        //さらに権限チェック
        String type = ctx.session().get("type");
        if(type == null){
            return null;
        }
        //if文を一行でかけるやつ,参考演算子(else ifがあるときは使わない)
        return (type.equals("0")) ? type : null;
    }
    
    @Override
    public Result onUnauthorized(Http.Context ctx){
        Logger.info("unauthorized access : " + ctx.request().remoteAddress());
        return redirect(routes.MainPageController.mainpage());
    }
    
}
