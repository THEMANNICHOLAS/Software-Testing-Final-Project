/***********************************************
 * file: TestUserSettings.java
 * dev: George Alenchery
 * desc: This test class will test user settings
 * when logged into discord.com
 * for: Opening user settings, updating display name and verifying saved changes,
 * toggling appearance theme, changing the language setting in user settings and
 * enabling notifications.
 ***********************************************/


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class TestUserSettings extends BaseClass {

    // Renaming the login method to avoid conflicts with other test classes
    @BeforeMethod
    public void loginToDiscordSettings() throws InterruptedException {
        driver.get("https://discord.com/login");
        Thread.sleep(3000);  // Wait for the page to load

        // Use valid credentials (replace with your own credentials for testing)
        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        emailField.sendKeys("SoftwareTestingDK4@mail.com"); // Replace with your actual email
        passwordField.sendKeys("PaulBlart123!"); // Replace with your actual password
        Thread.sleep(3000);

        // Log in by submitting the password form
        passwordField.submit();

        // Wait for the Discord dashboard to load
        Thread.sleep(8000);  // Increase the time if the page is slow to load
    }

    // Test open settings
    @Test
    public void testOpenSettings() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click on the settings button
        WebElement settingsButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='User Settings']"))
        );
        settingsButton.click();
        Thread.sleep(3000);

        // Click on the "Standing" tab
        WebElement standingTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@aria-label='Standing']"))
        );
        standingTab.click();
        Thread.sleep(3000); // Optional wait to ensure tab change

        // Click on the "Security" tab
        WebElement securityTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@aria-label='Security']"))
        );
        securityTab.click();
        Thread.sleep(3000); // Optional wait to ensure tab change
    }


    // Test search bar
    @Test
    public void testSearchBar() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open settings
        WebElement settingsButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='User Settings']"))
        );
        settingsButton.click();
        Thread.sleep(3000);

        WebElement searchbar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"app-mount\"]/div[2]/div[1]/div[1]/div/div[2]/div[2]/div/div[1]/div/nav/div/div[1]/div/div/input")
        ));
        searchbar.click();
        searchbar.sendKeys("Advanced");
        Thread.sleep(3000);

    }

    // Test change theme
    @Test
    public void testChangeTheme() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open settings
        WebElement settingsButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='User Settings']"))
        );
        settingsButton.click();
        Thread.sleep(2000);

        // Open appearance settings
        WebElement appearanceTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='tab' and @aria-label='Appearance']"))
        );
        appearanceTab.click();
        Thread.sleep(2000);

        // Toggle dark mode
        WebElement darkModeToggle = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#appearance-tab > div > div.children__7bffb > div:nth-child(2) > div:nth-child(2) > div > div > div:nth-child(1) > section > div:nth-child(7) > div"))
        );
        darkModeToggle.click();
        Thread.sleep(2000);

    }

    // Test change language
    @Test
    public void testChangeLanguage() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open settings
        WebElement settingsButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='User Settings']"))
        );
        settingsButton.click();
        Thread.sleep(3000);

        // Open language tab
        WebElement languageTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='tab' and text()='Language']"))

        );
        languageTab.click();
        Thread.sleep(2000);

        // Wait and click on the English, UK radio option
        WebElement englishUKRadio = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'English, UK')]/ancestor::div[@role='radio']"))
        );

        englishUKRadio.click();
        Thread.sleep(2000);

    }


    // Test notifications
    @Test(priority = 1)
    public void testToggleUnreadMessageBadge() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open settings
        WebElement settingsButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='User Settings']")));
        settingsButton.click();
        Thread.sleep(2000);

        // Go to Notifications tab
        WebElement notificationsTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='tab' and @aria-label='Notifications']")));
        notificationsTab.click();
        Thread.sleep(2000);

// Wait for the radio button to be clickable
        WebElement radioButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'item__001a7')]//div[contains(text(), 'All Messages')]")
        ));

// Click on the radio button
        radioButton.click();
        Thread.sleep(2000);

// Click the dropdown to expand it
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"notifications-tab\"]/div/div[2]/div[5]/div/div[1]"))
        );
        dropdown.click();
        Thread.sleep(2000);

/// Wait for the "1 minute" option to appear and be clickable
        WebElement oneMinuteOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='option' and contains(text(), '1 minute')]"))
        );

// Click the "1 minute" option
        oneMinuteOption.click();
        Thread.sleep(2000);


    }

}
