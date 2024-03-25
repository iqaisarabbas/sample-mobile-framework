package tests;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import pages.BasePage;
import utils.DriverUtils;

public class BaseTest {
	
	protected static AppiumDriver<MobileElement> driver;
	BasePage basePage;
	
	public BaseTest() {
		super();
	}

    @BeforeMethod
    public void setUp() {
        DriverUtils.initializeDriver();
    }
    
    
    @Test(description = "Verify that a user can create wallet with valid credentials", priority = 1)
    public void testCreateWallet() {
        // Assuming entering "111222" as the passcode for simplicity
        String passcode = "111222";

        basePage.clickGetStartedButton();
        boolean logoDisplayed = basePage.validateLogo(); // assertion after clicking Get Started
        Assert.assertTrue(logoDisplayed, "Logo is not displayed");

        basePage.clickCreateNewWalletButton()
                .clickSecretPhraseCreateButton()
                .clickSkipBackupButton()
                .enterPasscode(passcode)
                .confirmPasscode(passcode)
                .denyPermissions();

        //`startUsingTrustWallet` method navigates to a new page where we can verify creation.
        SamplePage samplePage = basePage.startUsingTrustWallet();
        boolean isOnSamplePage = samplePage.isAt(); // `isAt` method will check for a specific element on the page.
        Assert.assertTrue(isOnSamplePage, "User is not on the Sample page after wallet creation");

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        DriverUtils.quitDriver();
    }

}
