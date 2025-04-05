/************************************************
 file: BaseClass.java
 dev: Nicholas Perez
 desc: Serves as base setup for test classes that
 will extend this one. Initializes driver with
 protected variables.
 ***********************************************/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/*
Hello teammates! Please set your download directory and the path to your
chrome driver in system environment variables, just like you did with maven.
This makes it easy to upload to GitHub without showing the private stuff
 */

public class BaseClass {
    public WebDriver driver;

    //Private path to user download dir for the sake of this project.
    protected String DOWNLOAD_DIR = System.getenv("DOWNLOAD_DIR");

    //Private chrome driver path for the sake of the project. this varies and requires
    //the user to set a system environment variable for this assignment
    protected String CHROME_DRIVER_DIR = System.getenv("CHROME_DRIVER");

    @BeforeSuite
    public void setup(){
        System.out.println("CHROME_DRIVER_DIR: " + CHROME_DRIVER_DIR);
        if (CHROME_DRIVER_DIR == null || CHROME_DRIVER_DIR.trim().isEmpty()) {
            throw new IllegalStateException("CHROME_DRIVER is not valid");
        }
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_DIR);
    }

    @BeforeMethod
    public void beforeMethod(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }
    @AfterMethod
    public void afterMethod(){
        if(driver != null){
            driver.close();
        }
    }
}
