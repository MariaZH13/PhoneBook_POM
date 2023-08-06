import confi.AppiumConfiguration;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.BaseScreen;
import screens.LoginRegistrationScreen;
import screens.SplashScreen;

public class LaunchTest extends AppiumConfiguration {


    @Test
    public void launch(){
        String version = new SplashScreen(driver).getCurrentVersion();
        System.out.println(version);
        Assert.assertTrue(version.contains("1.0.0"));

    }
    @Test
    public void tests(){
        getHelperBase().pause(5000);
       String text = new LoginRegistrationScreen(driver).getText();
        System.out.println(text);


    }
}
