/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import play.mvc.*;
import static play.mvc.Results.ok;
import views.html.*;

/**
 * @author a-yamamoto
 */
public class LoginController extends Controller{
    public static Result login() {
        return ok(loginpage.render());

    }
}

