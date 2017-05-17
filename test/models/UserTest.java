package models;

import controllers.*;
import .*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class UserTest {
    
    @Before
    public void createDb() {
        start(fakeApplication(inMemoryDatabase()));
        /**
         * Userメソッドに値を渡す
         */
        User user = new User("209", "yamamoto" , "aaa");
        /**
         * DBに保存
         */
        user.save();
    }
    
    @Test
    public void findById() {
        /**
         * Userメソッドを呼ぶ
         */
        User user = new User();
        /**
         * Userメソッド内のidを見る
         */
        User actual = User.findById(id);
        /**
         * idとDBに保存されている値が等しいか確認
         */
        assertThat(actual.id).isEqualTo(209));
    }
    
    @Test
    public void findByName() {
        User user = new User();
        
        User actual = User.findById(name);
        assertThat(actual.name).isEqualTo(yamamoto));
    }
    
    @Test
    public void findByPass() {
        User user = new User();
        
        User actual = User.findById(pass);
        assertThat(actual.pass).isEqualTo(aaa));
    }
    
//    @Test
//    public void RightParamTest() {
//        Map<String,String> map = new HashMap<String,String>();
//        map.put("209", "yamamoto");
//        Form<User> form = form(User.class).bind(map);
//        assertThat(form.hasErrors()).isFalse();
//    }
}
