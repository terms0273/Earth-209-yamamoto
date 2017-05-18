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
public class ApplicationTest {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }
    
    @Test
    public void renderTemplate() {
        Content html = views.html.index.render("Your new application is ready.");
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Your new application is ready.");
    }
    
    
    /**
     * ログイン画面表示
     * 　ログイン成功したらメイン画面へ
     * 　失敗したらエラー表示
     */
    @Test
    public void testLoginRender() {
        Content html = views.html.loginrender.render();
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("LoginPage");
        assertThat(contentAsString(html)).contains("ID");
        assertThat(contentAsString(html)).contains("UserName");
        assertThat(contentAsString(html)).contains("Password");
        /**
         * 新規登録画面へ遷移するコメント表示
         */
        assertThat(contentAsString(html)).contains("SignUp");
    }
    
    /**
     * ログアウト画面表示
     * 　確認画面表示
     */
    @Test
    public void testLogoutPage() {
        Content html = views.html.logoutpage.render();
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("LogoutPage");
    }
    
    /**
     * ユーザー登録画面表示
     *  ID,UserName,Passwordの入力フォーム表示
     */
    @Test
    public void testSignUpPage() {
        Content html = views.html.signuppage.render();
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("SignUpPage");
        assertThat(contentAsString(html)).contains("ID");
        assertThat(contentAsString(html)).contains("UserName");
        assertThat(contentAsString(html)).contains("Password");
    }
    
    /**
     * ユーザー編集画面表示(ログインID、ユーザ名)
     */
    @Test
    public void testEditPage() {
        Content html = views.html.editpage.render();
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("EditPage");
        assertThat(contentAsString(html)).contains("ID");
        assertThat(contentAsString(html)).contains("UserName");
    }
    
    /**
     * ユーザー編集画面表示(パスワード)
     * 　現在のパスワード
     * 　新規のパスワード２回
     * 　入力するフォームを表示
     */
    @Test
    public void testEditPassPage() {
        Content html = views.html.editpasspage.render();
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("EditpassPage");
        assertThat(contentAsString(html)).contains("OldPassword");
        assertThat(contentAsString(html)).contains("NewPassword");
        assertThat(contentAsString(html)).contains("again");
    }

}
