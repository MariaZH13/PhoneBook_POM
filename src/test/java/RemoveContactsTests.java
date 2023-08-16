import confi.AppiumConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class RemoveContactsTests extends AppiumConfiguration {
    @BeforeClass
    public void precondition(){
        new SplashScreen(driver)
                .goToAuthenticationScreen()
                .fillEmail("adel249@gmail.com")
                .fillPassword("Zxcvb$249")
                .submitLogin();
    }

    @BeforeMethod
    public void providerContacts(){
        new ContactListScreen(driver)
                .provideContacts();
    }

    @Test
    public void removeOneContactPositive(){
        Assert.assertTrue(
        new ContactListScreen(driver)
               .removeOneContact()
               .isContactRemove()
        );
    }

    @Test
    public void removeAllContactsPositive(){
        Assert.assertTrue(
        new ContactListScreen(driver)
                .removeAllContacts()
                .isNoContactMessage()
        );

    }


}
