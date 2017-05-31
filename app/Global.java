/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import models.User;
import play.*;
import play.api.mvc.EssentialFilter;
import play.filters.csrf.CSRFFilter;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author a-yamamoto
 */
//CSRF対策
public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app){
        List<User> userList = User.find.all();
        if(userList.size() == 0){
            User user = new User();
            user.userid = "user";
            user.username = "user";
            user.password = BCrypt.hashpw("user",BCrypt.gensalt());
            user.type = 0;
            user.save();
        }
    }
    
    @Override
    public <T extends EssentialFilter> Class<T>[] filters() {
        return new Class[]{CSRFFilter.class};
    }
    
}
