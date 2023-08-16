package screens;

import confi.HelperBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import models.Contact;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

public class ContactListScreen extends BaseScreen {

    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    HelperBase base = new HelperBase();

    String phoneNumber;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/add_contact_btn']")
    MobileElement addBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement activityTextView;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<MobileElement> names;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowPhone']")
    List<MobileElement> phones;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<MobileElement> contacts;
    @FindBy(xpath = "//*[@content-desc='More options']")
    MobileElement moreOptions;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    MobileElement logOutBtn;

    @FindBy(id = "android:id/button1")
    MobileElement yesBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/emptyTxt")
    MobileElement emptyTxt;

    public boolean isContactListActivityPresent() {
        return shouldHave(activityTextView, "Contact list", 5);

    }

    public AuthenticationScreen logout() {
        moreOptions.click();
        logOutBtn.click();
        return new AuthenticationScreen(driver);
    }

    public AddNewContactScreen openContactForm() {
        waitElement(addBtn, 5);
        addBtn.click();
        return new AddNewContactScreen(driver);
    }

    public String getLastPhone() {
        if (!phones.isEmpty()) {
            return phones.get(phones.size() - 1).getText();
        } else {
            return null;
        }
    }

    public boolean isContactAdded(Contact contact) {

        boolean checkName = checkContainsText(names, contact.getName() + " " + contact.getLastName());
        boolean checkPhone = checkContainsText(phones, contact.getPhone());

        return checkName && checkPhone;

    }

    public boolean checkContainsText(List<MobileElement> list, String text) {
        for (MobileElement element : list) {
            if (element.getText().contains(text)) return true;
        }
        return false;

    }

    public ContactListScreen removeOneContact() {
        waitElement(addBtn, 5);
        MobileElement contact = contacts.get(0);
        phoneNumber = phones.get(0).getText();
        Rectangle rect = contact.getRect();

        int xStart = rect.getX() + rect.getWidth() / 8;
        int xEnd = xStart + rect.getWidth() * 6 / 8;
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xStart, y))
                .moveTo(PointOption.point(xEnd, y))
                .release()
                .perform();
        yesBtn.click();
        base.pause(3000);

        return this;

    }

    public boolean isContactContains(String text) {
        contacts.get(0).click();
        Contact contact = new ViewContactScreen(driver)
                .viewContactObject();
        driver.navigate().back();
        return contact.toString().contains(text);
    }

    public boolean isContactRemove() {
        boolean res = phones.contains(phoneNumber);
        return !res;
    }

    public ContactListScreen removeAllContacts() {
        waitElement(addBtn, 5);
        while (phones.size() > 0) {
            removeOneContact();
        }
        return this;
    }

    public boolean isNoContactMessage() {
        return shouldHave(emptyTxt, "No Contacts. Add One more!", 5);

    }

    public ContactListScreen provideContacts() {
        while (phones.size() < 3) {
            addNewContact();
        }
        return this;
    }

    public ContactListScreen addNewContact() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        Contact contact = Contact.builder()
                .name("Rita_" + i)
                .lastName("Snow")
                .phone("01234578" + i)
                .email("rita_" + i + "@mail.com")
                .address("Tel-Aviv")
                .description("friend")
                .build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContact();

        return this;
    }

    public EditContactScreen editOneContact() {
        waitElement(addBtn, 5);
        MobileElement contact = contacts.get(0);
        Rectangle rect = contact.getRect();

        int xStart = rect.getX() + rect.getWidth() / 8;
        int xEnd = xStart + rect.getWidth() * 6 / 8;
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xEnd, y))
                .moveTo(PointOption.point(xStart, y))
                .release()
                .perform();

        base.pause(3000);
        return new EditContactScreen(driver);
    }

    public ContactListScreen scrollList() {
        waitElement(addBtn, 5);
        MobileElement contact = contacts.get(contacts.size() - 1);
        Rectangle rect = contact.getRect();

        int x = rect.getX() + rect.getWidth() / 2;
        int y = rect.getY() + rect.getHeight() / 2;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(x, y))
                .moveTo(PointOption.point(x, 0))
                .release()
                .perform();
        return this;
    }

    public boolean isEndOflist() {
        String beforeScroll = names.get(names.size() - 1).getText()
                + " " + phones.get(phones.size() - 1).getText();
        scrollList();
        String afterScroll = names.get(names.size() - 1).getText()
                + " " + phones.get(phones.size() - 1).getText();
        if (beforeScroll.equals(afterScroll)) return true;
        return false;
    }

    public boolean isContactAddedScroll(Contact contact) {
        boolean res = false;
        while (!res) {
            boolean checkName = checkContainsText(names, contact.getName() + " " + contact.getLastName());
            boolean checkPhone = checkContainsText(phones, contact.getPhone());

            res = checkName && checkPhone;
            if(res == false) isEndOflist();
        }

        return res;
    }
}



