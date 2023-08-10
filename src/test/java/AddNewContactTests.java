import confi.AppiumConfiguration;
import io.appium.java_client.MobileElement;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

import java.util.List;

public class AddNewContactTests extends AppiumConfiguration {
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
    public void addNewContactPositive(){

        Contact contact = Contact.builder()
                .name("John_" + i)
                .lastName("Snow")
                .phone("01234578" + i)
                .email("john_" + i + "@mail.com")
                .address("Rehovot")
                .description("Best friend")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContact();

       String phone = new ContactListScreen(driver).getLastPhone();
       Assert.assertEquals(contact.getPhone(),phone);



    }
}
