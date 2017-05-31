package models;

import apps.FakeApp;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;
import java.util.List;
import org.junit.*;
import org.mindrot.jbcrypt.BCrypt;

import static org.fest.assertions.Assertions.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.start;
import views.html.user;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class UserTest extends FakeApp {
    
    @Before
    public void setUpTest() {
        start(fakeApplication(inMemoryDatabase()));
    }
        /**
         * Userメソッドに値を渡す
         */
    @Test
    public void findByIdTest() {
        User user = new User();
        user.userid = "209";
        user.username = "a-yamamoto";        
        user.password = BCrypt.hashpw("aaaaa", BCrypt.gensalt());

         /**
         * DBに保存
         */
        user.save();
        
        String sql = "SELECT * FROM user WHERE id = :id";

        List<SqlRow> result = Ebean.createSqlQuery(sql).setParameter("id", 1L).findList();
        assertThat(result.get(0).getString("userid")).isEqualTo("209");
        assertThat(result.get(0).getString("username")).isEqualTo("a-yamamoto");
        assertThat(BCrypt.checkpw("aaaaa", result.get(0).getString("password"))).isEqualTo(true);
    }  
      
    
    @Test
    public void UserTest() {
        //Userメソッドを呼ぶ
        //User user = new User();
        //Userメソッド内のidを見る
        User requestuser = User.find.where().eq("userid","209").findUnique();
        //idとDBに保存されている値が等しいか確認
        assertThat(requestuser.userid).isEqualTo("user");
        assertThat(requestuser.username).isEqualTo("user");
    }
       
}