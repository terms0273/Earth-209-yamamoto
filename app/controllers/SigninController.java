package controllers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.User;
import play.data.Form;
import play.mvc.*;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;
import views.html.*;

/**
 *ユーザー登録画面へ反映
 * @author a-yamamoto
 */
public class SigninController extends Controller{
    public static Result signin() {
        String mySession = session("userid");
        if(mySession == null){
            return redirect(routes.LoginController.login());
        }else{
            Form<User> form = new Form(User.class);
            return ok(signinpage.render(form));
        }
    }
    
    //データベースに入力情報を登録
    public static Result create(){
        Form<User> form = new Form(User.class).bindFromRequest();
        User requestuser = form.get();
        User user = User.find.where().eq("userid",requestuser.userid).findUnique();
        
        if(form.hasErrors()){
            return redirect(routes.SigninController.signin());
        }else if(user == null){
                requestuser.save();
                return redirect(routes.UserController.index());
        }else{
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "既に存在するIDです。");
            return redirect(routes.SigninController.signin());
        }
    }
        

    
}
