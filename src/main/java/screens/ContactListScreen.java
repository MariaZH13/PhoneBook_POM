package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Contact;
import org.openqa.selenium.support.FindBy;

import java.util.Iterator;
import java.util.List;

public class ContactListScreen extends BaseScreen{

    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/add_contact_btn']")
    MobileElement addBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement activityTextView;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<MobileElement> rowName;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowPhone']")
    List<MobileElement> rowPhone;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<MobileElement> rowContainer;
    @FindBy(xpath = "//*[@content-desc='More options']")
    MobileElement moreOptions;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    MobileElement logOutBtn;

    public boolean isContactListActivityPresent(){
       return shouldHave(activityTextView,"Contact list",5);

    }
    public AuthenticationScreen logout(){
        moreOptions.click();
        logOutBtn.click();
        return new AuthenticationScreen(driver);
    }
    public AddNewContactScreen openContactForm(){
        waitElement(addBtn,5);
        addBtn.click();
        return new AddNewContactScreen(driver);
    }

   public String getLastPhone(){
        if(!rowPhone.isEmpty()){
            return rowPhone.get(rowPhone.size()-1).getText();
        } else{
            return null;
        }
   }
}
