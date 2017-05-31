package controllers;

import Authenticator.Secured;
import dto.EditPass;
import dto.EditUser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.User;
import play.data.Form;
import play.mvc.*;

import views.html.*;

public class EditController extends Controller {

    /**
     * ユーザー編集画面へ遷移
     * @return 
     */
    @Security.Authenticated(Secured.class)
    public static Result edit() {
//        String mySession = session("userid");
//        if(mySession == null){
//            return redirect(routes.LoginController.login());
//        }else{
            Form<User> form = new Form(User.class);
            return ok(edit.render(form));
//        }
    }
        
    @Security.Authenticated(Secured.class)
    public static Result userUpdate(){
            Form<EditUser> form = new Form(EditUser.class).bindFromRequest();
            if(!form.hasErrors()){
                User requestuser = User.find.where().eq("userid",session("userid")).findUnique();
                requestuser.userid = form.get().userid;
                requestuser.username = form.get().username;
                requestuser.update();
                return redirect(routes.UserController.index());
            }else{
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "正しく入力してください。");
                return redirect(routes.UserController.index());           
            }
    }
    
    @Security.Authenticated(Secured.class)
    public static Result passUpdate(){
        Form<EditPass> form = new Form(EditPass.class).bindFromRequest();
        User requestpass = User.find.where().eq("userid",session("userid")).findUnique();
        if(form.hasErrors()){
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "正しく入力してください。");
            return redirect(routes.UserController.index()); 
        }else{
            if(!(requestpass.password.equals(form.get().oldPass))){
                flash("disagreement","Passwordが一致しません。");
                return redirect(routes.EditController.edit());
            }
            if(!(form.get().newPass.equals(form.get().confirmNewPass))){
                flash("disagreement","Passwordが一致しません。");
                return redirect(routes.EditController.edit());                    
            }
            requestpass.password = form.get().newPass;
            requestpass.update();
            return redirect(routes.UserController.index());
        }
    }
            
}