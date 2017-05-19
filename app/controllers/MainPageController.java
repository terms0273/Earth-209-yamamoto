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
 *メインページへ反映させる
 * @author a-yamamoto
 */
public class MainPageController extends Controller {
    public static Result mainpage() {
        return ok(mainpage.render());
    }
    
}