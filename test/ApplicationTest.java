import dto.LoginUser;
import org.junit.*;

import play.mvc.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;
import play.data.Form;
import views.html.edit;
import views.html.loginpage;
import views.html.logoutpage;
import views.html.signinpage;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {
    
    @Before 
    public void setUp() {
        start(fakeApplication(inMemoryDatabase()));
    }
    
    /**
     * ログイン画面表示
     * 　ログイン成功したらメイン画面へ
     * 　失敗したらエラー表示
     */
    @Test
    public void testLoginPage() {
        Content html = loginpage.render(new Form(LoginUser.class));
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("LoginPage");
        assertThat(contentAsString(html)).contains("USER NAME");
        assertThat(contentAsString(html)).contains("PASSWORD");

    }
    
    /**
     * ログアウト画面表示
     * 　確認画面表示
     */
//    @Test
//    public void testLogoutPage() {
//        Content html = logoutpage.render(new Form());
//        assertThat(contentType(html)).isEqualTo("text/html");
//        assertThat(contentAsString(html)).contains("Logout");
//    }
    
    /**
     * ユーザー登録画面表示
     *  ID,UserName,Passwordの入力フォーム表示
     */
    @Test
    public void testSigninPage() {
        Content html = signinpage.render(new Form(models.User.class));
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Signin");
        assertThat(contentAsString(html)).contains("userid");
        assertThat(contentAsString(html)).contains("username");
        assertThat(contentAsString(html)).contains("password");
        assertThat(contentAsString(html)).contains("type");
    }
    
    /**
     * ユーザー編集画面表示(ログインID、ユーザ名)
     */
    @Test
    public void testEditPage() {
        Content html = edit.render(new Form(models.User.class));
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("USER EDIT");
        assertThat(contentAsString(html)).contains("userid");
        assertThat(contentAsString(html)).contains("username");
        //パスワード変更
        assertThat(contentAsString(html)).contains("oldPass");
        assertThat(contentAsString(html)).contains("newPass");
        assertThat(contentAsString(html)).contains("confirmNewPass");
    }
}
    
    /**
     * ユーザー編集画面表示(パスワード)
     * 　現在のパスワード
     * 　新規のパスワード２回
     * 　入力するフォームを表示
     */
    
//    @Test
//    public void testEditPassPage() {
//        Form<User> form = new Form(User.class);
//        Content html = views.html.edit.render(form);
//        assertThat(contentType(html)).isEqualTo("text/html");
//        assertThat(contentAsString(html)).contains("oldPass");
//        assertThat(contentAsString(html)).contains("newPass");
//        assertThat(contentAsString(html)).contains("confirmNewPass");
//    }
//
//}
