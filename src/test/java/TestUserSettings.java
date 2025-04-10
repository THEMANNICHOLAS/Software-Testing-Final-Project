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
        emailField.sendKeys("SoftwareTestingDK@mail.com"); // Replace with your actual email
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

        // Verify the settings page opens
        WebElement settingsHeader = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//h2[contains(text(),'User Settings')]"))
        );
        assert settingsHeader.isDisplayed();
    }

    // Test update display name
    @Test
    public void testUpdateDisplayName() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open settings
        WebElement settingsButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='User Settings']"))
        );
        settingsButton.click();

        // Update display name
        WebElement displayNameField = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[aria-label='Edit display name']"))
        );
        displayNameField.clear();
        displayNameField.sendKeys("New Display Name");

        // Save changes
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Save Changes')]"))
        );
        saveButton.click();

        // Verify the new name is saved
        WebElement successMessage = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Changes saved')]"))
        );
        assert successMessage.isDisplayed();
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

        // Open appearance settings
        WebElement appearanceTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Appearance')]"))
        );
        appearanceTab.click();

        // Toggle dark mode
        WebElement darkModeToggle = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@name='darkMode']"))
        );
        darkModeToggle.click();
        assert darkModeToggle.isSelected();
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

        // Open language settings
        WebElement languageTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Language')]"))
        );
        languageTab.click();

        // Change language to English
        WebElement selectLanguage = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//select[@name='language']"))
        );
        selectLanguage.sendKeys("English (US)");
        selectLanguage.click();

        // Verify the language change
        WebElement selectedLanguage = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//option[@selected='selected']"))
        );
        assert selectedLanguage.getText().equals("English (US)");
    }

    // Test enable notifications
    @Test
    public void testEnableNotifications() throws InterruptedException {


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open settings
        WebElement settingsButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='User Settings']"))
        );
        settingsButton.click();

        // Open notifications settings
        WebElement notificationsTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[contains(text(),'Notifications')]"))
        );
        notificationsTab.click();

        // Enable notifications
        WebElement enableNotifications = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@name='enableNotifications']"))
        );
        enableNotifications.click();
        assert enableNotifications.isSelected();
    }
}
