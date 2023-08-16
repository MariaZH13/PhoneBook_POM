import confi.AppiumConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfiguration {

    int i = new Random().nextInt(1000) + 1000;

    @Test
    public void RegistrationPositive() {
        Assert.assertTrue(
                new SplashScreen(driver)
                        .goToAuthenticationScreen()
                        .fillEmail("adel" + i + "@gmail.com")
                        .fillPassword("Zxcvb$4500")
                        .submitRegistration()
                        .isContactListActivityPresent()
        );

    }
    @Test
    public void RegistrationNegativeWrongEmail() {
        Assert.assertTrue(
                new SplashScreen(driver)
                        .goToAuthenticationScreen()
                        .fillEmail("adel" + i + "gmail.com")
                        .fillPassword("Zxcvb$4500")
                        .submitRegistrationNegative()
                        .isErrorMessageHasText("{username=must be a well-formed email address}")
        );

    }
}
