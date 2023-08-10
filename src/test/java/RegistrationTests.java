import confi.AppiumConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfiguration {

    int i = new Random().nextInt(1000) + 1000;

    @Test
    public void LoginPositive() {
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
    public void LoginNegativeWrongEmail() {
        Assert.assertTrue(
                new SplashScreen(driver)
                        .goToAuthenticationScreen()
                        .fillEmail("adel" + i + "gmail.com")
                        .fillPassword("Zxcvb$4500")
                        .submitRegistrationNegative()
                        .isAlertPresent()

        );

    }
}
