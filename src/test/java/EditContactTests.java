import confi.AppiumConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class EditContactTests extends AppiumConfiguration {

    int i = (int) (System.currentTimeMillis() / 1000) % 3600;

    @BeforeMethod
    public void precondition(){
        new SplashScreen(driver)
                .goToAuthenticationScreen()
                .fillEmail("adel249@gmail.com")
                .fillPassword("Zxcvb$249")
                .submitLogin();
    }

    @Test
    public void editOneContactPositive(){
        String text = "updated" + i + "@gmail.com";
       new ContactListScreen(driver)
               .editOneContact()
               .editEmail(text)
               .submitEditContact()
               .isContactContains(text);


    }

}
