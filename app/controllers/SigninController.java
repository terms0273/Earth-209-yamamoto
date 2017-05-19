package controllers;

import models.User;
import play.data.Form;
import play.mvc.*;
import static play.mvc.Results.ok;
import views.html.*;

/**
 *ユーザー登録画面へ反映
 * @author a-yamamoto
 */
public class SigninController extends Controller{
    public static Result signin() {
        Form<User> form = new Form(User.class);
        return ok(signinpage.render(form));
    }
    
}
