/************************************************
 file: BaseClass.java
 dev: Nicholas Perez
 desc: Serves as base setup for test classes that
 will extend this one. Initializes driver with
 protected variables.
 ***********************************************/

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;


/*
Hello teammates! Please set your download directory and the path to your
chrome driver in system environment variables, just like you did with maven.
This makes it easy to upload to GitHub without showing the private stuff
 */

public class BaseClass {
    public WebDriver driver;
    protected String DOWNLOAD_DIR = System.getenv("DOWNLOAD_DIR");
    protected String CHROME_DRIVER_DIR = System.getenv("CHROME_DRIVER");
    protected ChromeOptions options;

    @BeforeSuite
    public void setup(){
        System.out.println("CHROME_DRIVER_DIR: " + CHROME_DRIVER_DIR);
        if (CHROME_DRIVER_DIR == null || CHROME_DRIVER_DIR.trim().isEmpty()) {
            throw new IllegalStateException("CHROME_DRIVER is not valid");
        }
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_DIR);

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", DOWNLOAD_DIR);
        prefs.put("download.prompt_for_download", false);
        prefs.put("safebrowsing.enabled", true);

        options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--disable-blink-features=AutomationControlled");
    }

    @BeforeMethod
    public void beforeMethod(){
        // Use the ChromeOptions when instantiating the ChromeDriver
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod(){
        if(driver != null){
            driver.close();
        }
    }
}

