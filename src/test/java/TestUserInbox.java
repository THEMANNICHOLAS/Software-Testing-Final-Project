/***********************************************
 * file: TestUserInbox.java
 * dev: George Alenchery
 * desc: This test class will test the inbox features
 * for opening the inbox, sending a message, visibility of the messages,
 * indication of unread message notifications, and opening a DM thread
 * and verifying that it is displayed.
 ***********************************************/
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import java.time.Duration;

public class TestUserInbox extends BaseClass {

    // Renamed the login method to avoid conflicts with other test classes
    public void loginToDiscordInbox() throws InterruptedException {
        driver.get("https://discord.com/login");
        Thread.sleep(3000);  // Wait for the page to load

        // Use valid credentials (replace with your own credentials for testing)
        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        emailField.sendKeys("SoftwareTestingDK@mail.com");
        passwordField.sendKeys("PaulBlart123!");
        Thread.sleep(3000);

        // Log in by submitting the password form
        passwordField.submit();

        // Wait for the Discord dashboard to load
        Thread.sleep(8000);  // Increase the time if the page is slow to load
    }

    // Test opening the inbox
    @Test
    public void testOpenInbox() throws InterruptedException {
        loginToDiscordInbox(); // Use the renamed method for this test

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to Direct Messages tab
        WebElement directMessagesTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Direct Messages')]"))
        );
        directMessagesTab.click();

        // Verify that the inbox opens
        WebElement inboxHeader = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//h2[contains(text(),'Direct Messages')]"))
        );
        assert inboxHeader.isDisplayed();
    }

    // Test sending a message
    @Test
    public void testSendMessage() throws InterruptedException {
        loginToDiscordInbox(); // Using the renamed login method

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open Direct Messages
        WebElement directMessagesTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Direct Messages')]"))
        );
        directMessagesTab.click();

        // Send a test message
        WebElement messageBox = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("textarea[placeholder='Message @username']"))
        );
        messageBox.sendKeys("Test message");

        WebElement sendButton = driver.findElement(By.xpath("//button[@type='submit']"));
        sendButton.click();

        // Verify message appears in the DM thread
        WebElement lastMessage = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[@class='messageContent'])[1]"))
        );
        assert lastMessage.getText().contains("Test message");
    }

    // Test message input box visibility
    @Test
    public void testMessageInputBox() throws InterruptedException {
        loginToDiscordInbox(); // Using the renamed login method

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Check if message input box is visible in Direct Messages
        WebElement messageBox = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("textarea[placeholder='Message @username']"))
        );
        assert messageBox.isDisplayed();
    }

    // Test unread message indicator
    @Test
    public void testUnreadMessageIndicator() throws InterruptedException {
        loginToDiscordInbox(); // Using the renamed login method

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Check if unread messages are indicated with a dot or notification
        WebElement unreadIndicator = driver.findElement(By.xpath("//div[@class='unreadIndicator']"));
        assert unreadIndicator.isDisplayed();
    }

    // Test open a DM thread
    @Test
    public void testOpenDMThread() throws InterruptedException {
        loginToDiscordInbox(); // Using the renamed login method

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open a DM thread
        WebElement dmThread = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='listItem-3Qhj8z']"))
        );
        dmThread.click();

        // Verify the DM thread is opened
        WebElement dmHeader = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class,'header-3JgF8H')]"))
        );
        assert dmHeader.isDisplayed();
    }
}
