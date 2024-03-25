package utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverUtils {

    private static AppiumDriver driver;
    public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;

	@SuppressWarnings({ "deprecation", "rawtypes" })
	public static void initializeDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "C:\\Users\\hp\\Downloads\\v8.7.1_release.apk"); //change apk file location as per local device
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "8.1.0");
        capabilities.setCapability("deviceName", "Pixel 6 TrustWallet"); //change as per emulator or real connected device
        capabilities.setCapability("automationName", "UiAutomator2");
        
        //"appPackage": "com.wallet.crypto.trustapp",
        //"appActivity": "com.wallet.crypto.trustapp.ui.app.AppActivity"
        

        try {
        	URL url = new URL("http://127.0.0.1:4723/wd/hub");
        	driver = new AppiumDriver(url, capabilities);
        	System.out.println("Trust Wallet Application Started...");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Appium server URL is invalid", e);
        }
    }

    public static AppiumDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized. Please call initializeDriver first.");
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}