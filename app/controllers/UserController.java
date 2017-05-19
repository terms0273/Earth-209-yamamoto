/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
    //ルートにアクセスした際のAction
    public static Result index() {
        //DBからデータをすべて取り出す
        List<User> users = User.find.all();
        //取り出したデータをviewに反映させる
        return ok(user.render(users));
    }
    
    //データベースに入力情報を登録
    public static Result create(){
        Form<User> form = new Form(User.class).bindFromRequest();
        if(!form.hasErrors()){
            User requesttask = form.get();
            requesttask.save();
            return redirect(routes.UserController.index());
        }else{
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "正しく入力してください。");
            return redirect(routes.UserController.index());
        }
    }
    
    public static Result update(){
            Form<User> form = new Form(User.class).bindFromRequest();
            if(!form.hasErrors()){
                User requesttask = form.get();
                requesttask.update();
                return redirect(routes.UserController.index());
            }else{
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "正しく入力してください。");
                return redirect(routes.UserController.index());           
            }
        }
    
    //削除
        public static Result delete(Long id){
            JFrame frame = new JFrame();
            int option = JOptionPane.showConfirmDialog(frame,
                    "本当に削除しますか？", "削除の確認", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if(option == 0){
                User requesttask = User.find.byId(id);
                requesttask.delete();
            }
            return redirect(routes.UserController.index());
        }
  
}

