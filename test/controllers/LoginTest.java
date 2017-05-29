package controllers;

//import .*;
import apps.FakeApp;
import java.util.HashMap;
import java.util.Map;
import models.User;

import org.junit.*;

import play.mvc.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;
import play.data.Form;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class LoginTest extends FakeApp{
    
  @Before
  public void setUpTest() {
    start(fakeApplication(inMemoryDatabase()));
  }
  
    /**
     * ログイン成功時、ログイン画面へ遷移
     * かつ、sessionに値が入るか
     */
    @Test
    public void LoginSuccessTest() {
        Map<String,String> params = new HashMap<String,String>();
        params.put("userid","209");
        params.put("password","aaaaa");
        
        Result result = route(fakeRequest(POST,"/login").withFormUrlEncodedBody(params));
        assertThat(status(result)).isEqualTo(SEE_OTHER);
        assertThat(redirectLocation(result)).isEqualTo("/login");
        
        assertThat(session(result)).isNotNull();
    } 
    
//    @Test
//    public void testLoginError() {
//        
//        Map<String,String> params = new HashMap<String,String>();
//        params.put("userId","209");
//        params.put("password","aaaaa");
//        
//        Result result = route(fakeRequest(POST,"/doLogin").withFormUrlEncodedBody(params));
//        Form<User> form = new Form(User.class).bindFromRequest();
//        User getUser = form.get();
//        assertThat(getUser.userid()).isEmpty();
//        assertThat(getUser.password()).isEmpty();
//        
//        assertThat(status(result)).isEqualTo(BAD_REQUEST);
//        assertThat(redirectLocation(result)).isEqualTo("/login");
//        assertThat(contentAsString(result)).contains("IDかPasswordまたはその両方が間違っています");
//        assertThat(session(result)).isNull();
//    }
        
   }
    
    
    
    
    
    
    
  