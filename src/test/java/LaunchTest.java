import confi.AppiumConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

public class LaunchTest extends AppiumConfiguration {

    @Test
    public void launch(){
        String version = new SplashScreen(driver).getCurrentVersion();
        System.out.println(version.toString());
        Assert.assertTrue(version.contains("1.0.0"));

    }
}
