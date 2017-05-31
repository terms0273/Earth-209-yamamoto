/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Authenticator.AdminSecured;
import dto.LoginUser;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.User;
import play.data.*;
import play.mvc.*;

import views.html.*;

/**
 *
 * @author a-yamamoto
 */
public class UserController extends Controller{
    //権限のチェック
    @Security.Authenticated(AdminSecured.class)
    //ルートにアクセスした際のAction
    public static Result index() {
        //DBからデータをすべて取り出す
        List<User> users = User.find.all();
        //取り出したデータをviewに反映させる
        return ok(user.render(users));
    }
    
    //削除
    //権限のチェック
    @Security.Authenticated(AdminSecured.class)
        public static Result delete(Long id){
            JFrame frame = new JFrame();
            int option = JOptionPane.showConfirmDialog(frame,
                    "本当に削除しますか？", "削除の確認", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if(option == 0){
                User requestuser = User.find.byId(id);
                requestuser.deleteFlag = true;
                requestuser.update();
            }
            return redirect(routes.UserController.index());
        }
}

  


