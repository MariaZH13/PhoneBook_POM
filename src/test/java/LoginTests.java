import confi.AppiumConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfiguration {

    @Test
    public void LoginPositive() {
        Assert.assertTrue(
                new SplashScreen(driver)
                        .goToAuthenticationScreen()
                        .fillEmail("adel249@gmail.com")
                        .fillPassword("Zxcvb$249")
                        .submitLogin()
                        .isContactListActivityPresent()
        );

    }

    @AfterMethod
    public void postcondition() {
        if (new ContactListScreen(driver)
                .isContactListActivityPresent()) {
            new ContactListScreen(driver).logout();
            new SplashScreen(driver);
        }
    }
}