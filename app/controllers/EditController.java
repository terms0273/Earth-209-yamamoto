package controllers;

import models.User;
import play.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class EditController extends Controller {

    /**
     * ユーザー編集画面へ遷移
     * @return 
     */
    public static Result edit() {
        Form<User> form = new Form(User.class);
        return ok(edit.render(form));
    }

}
