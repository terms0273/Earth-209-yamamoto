package controllers;

import Authenticator.Secured;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.User;
import play.data.Form;
import play.mvc.*;
import static play.mvc.Results.ok;
import static play.mvc.Results.redirect;
import views.html.*;
import org.mindrot.jbcrypt.BCrypt;

/**
 *ユーザー登録画面へ反映
 * @author a-yamamoto
 */
public class SigninController extends Controller{
    @Security.Authenticated(Secured.class)
    public static Result signin() {
//        String mySession = session("userid");
//        if(mySession == null){
//            return redirect(routes.LoginController.login());
//        }else{
            Form<User> form = new Form(User.class);
            return ok(signinpage.render(form));
//        }
    }
    
    //データベースに入力情報を登録
    @Security.Authenticated(Secured.class)
    public static Result create(){
        Form<User> form = new Form(User.class).bindFromRequest();
        User requestuser = form.get();
        
        if(form.hasErrors()){
            return redirect(routes.SigninController.signin());
        }
        //暗号化
        else if(User.find.where().eq("userid",requestuser.userid).findRowCount() == 0){
            String hashedPassword = BCrypt.hashpw(requestuser.password,BCrypt.gensalt());
            requestuser.password = hashedPassword;
            requestuser.save();
            return redirect(routes.UserController.index());
        }else{
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "既に存在するIDです。");
            return redirect(routes.SigninController.signin());
        }
    }
        

    
}
