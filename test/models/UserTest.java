package models;

import org.junit.*;


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
       
        User user = new User();
        user.userid = "user1";
        user.username = "user1";
        user.password = "user1";
        /**
         * DBに保存
         */
        user.save();

    }
    
    @Test
    public void findByIdTest() {
        //Userメソッドを呼ぶ
        //User user = new User();
        //Userメソッド内のidを見る
        User requestuser = User.find.where().eq("userid","user").findUnique();
        //idとDBに保存されている値が等しいか確認
        assertThat(requestuser.userid).isEqualTo("user");
        assertThat(requestuser.username).isEqualTo("user");
    }
}