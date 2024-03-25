package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverUtils;

public class BasePage extends BaseTest{

    protected AppiumDriver driver;
    
  //Page Factory - Object Repositories;
  	@FindBy(xpath = "//android.widget.TextView[@text=\"Get Started\"]")
  	MobileElement getStartedButton;
  	
  	@FindBy(xpath = "//android.widget.TextView[@text=\"Create new wallet\"]")
  	MobileElement createNewWalletButton;
  	
  	@FindBy(xpath = "//android.widget.TextView[@text=\"Add existing wallet\"]")
  	MobileElement addExistingWalletButton;
  	
  	@FindBy(id = "logo")
  	MobileElement logo;
  	
  	@FindBy(xpath = "(//android.widget.TextView[@text=\"Create\"])[1]")
  	MobileElement secretPhraseCreateButton;
  	
  	@FindBy(xpath = "//android.widget.TextView[@text=\"SKIP\"]")
  	MobileElement skipBackupButton;
  	
  	@FindBy(id = "passcodeTextBox")
  	MobileElement passcodeTextBox;
  	
  	@FindBy(xpath = "1Button")
  	MobileElement inputOneButton;
  	
  	@FindBy(xpath = "2Button")
  	MobileElement inputTwoButton;
  	
  	@FindBy(xpath = "//android.widget.TextView[@text=\"Deny\"]")
  	MobileElement denyButton;
  	
  	@FindBy(xpath = "//android.view.ViewGroup/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.widget.Button")
  	MobileElement startUsingTrustWalletButton;

    public BasePage() {
        this.driver = DriverUtils.getDriver();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    
  //Actions available for Create Wallet
    public boolean validateLogo() {
        return logo.isDisplayed();
    }

    public BasePage clickGetStartedButton() {
        getStartedButton.click();
        return this; // Allows for method chaining
    }

    public BasePage clickCreateNewWalletButton() {
        createNewWalletButton.click();
        return this;
    }

    public BasePage clickAddExistingButton() {
        addExistingWalletButton.click();
        return this;
    }

    public BasePage clickSecretPhraseCreateButton() {
        secretPhraseCreateButton.click();
        return this;
    }

    public BasePage clickSkipBackupButton() {
        skipBackupButton.click();
        return this;
    }

    public BasePage enterPasscode(String passcode) {
        // Assuming passcode is always 6 digits and only contains 1s and 2s for this example
        for (char digit : passcode.toCharArray()) {
            if (digit == '1') {
                inputOneButton.click();
            } else if (digit == '2') {
                inputTwoButton.click();
            }
        }
        return this; // Allow chaining after entering passcode
    }

    public BasePage confirmPasscode(String passcode) {
        // Re-use enterPasscode for simplicity
        enterPasscode(passcode);
        return this;
    }

    public BasePage denyPermissions() {
        denyButton.click();
        return this;
    }

    public SamplePage startUsingTrustWallet() {
        startUsingTrustWalletButton.click();
        return new SamplePage();
    }

    // Simplified wallet creation method combining all actions
    public SamplePage createWallet(String passcode) {
        clickGetStartedButton()
            .clickCreateNewWalletButton()
            .clickSecretPhraseCreateButton()
            .clickSkipBackupButton()
            .enterPasscode(passcode)
            .confirmPasscode(passcode)
            .denyPermissions()
            .startUsingTrustWallet();

        return new SamplePage();
    }
}