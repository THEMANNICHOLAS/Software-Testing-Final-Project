/***********************************************
 * file: TestUserInbox.java
 * dev: Nicholas Perez
 * desc: This test class will test the inbox features
 * for opening the inbox, sending a message, visibility of the messages,
 * indication of unread message notifications, and opening a DM thread
 * and verifying that it is displayed.
 ***********************************************/
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;

public class TestUserInbox extends BaseClass {

    // Renamed the login method to avoid conflicts with other test classes
    @BeforeMethod
    public void loginToDiscordInbox() throws InterruptedException {
        driver.get("https://discord.com/login/");
        Thread.sleep(3000);  // Wait for the page to load

        // Use valid credentials (replace with your own credentials for testing)
        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        emailField.sendKeys("SoftwareTestingDK2@mail.com");
        passwordField.sendKeys("PaulBlart123!");
        Thread.sleep(6000);

        // Log in by submitting the password form
        passwordField.submit();

        // Wait for the Discord dashboard to load
        Thread.sleep(8000);  // Increase the time if the page is slow to load
    }

    // Test opening the inbox
    @Test
    public void testOpenInbox() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to Direct Messages tab
        WebElement inboxMessagesTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div[aria-label='Inbox']"))
        );
        inboxMessagesTab.click();
        Thread.sleep(2000);

        // Verify that the inbox opens
        WebElement inboxForYou = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div[aria-label='For You']"))
        );
        inboxForYou.click();
        Thread.sleep(2000);
        assert inboxForYou.isDisplayed();
    }

    // Test sending a message
    @Test
    public void testSendMessage() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open Direct Messages
        WebElement directMessagesTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a.link__972a0[aria-label*='direct message']"))
        );
        directMessagesTab.click();
        Thread.sleep(2000);

        // Send a test message
        WebElement messageBox = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div[data-slate-node='element']"))
        );
        messageBox.sendKeys("Test message");
        messageBox.sendKeys(Keys.ENTER);

        // Verify message appears in the DM thread
        WebElement lastMessage = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@id='message-content-1364069448046084096']/span[text()='Test message']"))
        );
        assert lastMessage.getText().contains("Test message");
    }

    // Test message input box visibility
    @Test
    public void testMessageInputBox() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Check if message input box is visible in Direct Messages
        WebElement messageBox = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("textarea[placeholder='Message @username']"))
        );
        assert messageBox.isDisplayed();
    }

    // Test unread message indicator
    @Test
    public void testInboxFunctionality() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Opens inbox icon
        WebElement inbox = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div[aria-label='Inbox']")));
        inbox.click();
        Thread.sleep(2000);
        assert inbox.isDisplayed();
        //Tab list with "For You"/"Unreads"/"Mentions"/"Bookmarks"
        WebElement tabList = driver.findElement(By.cssSelector("div[role='tablist']"));

        //List of tab elements in inbox
        List<WebElement> tabs = tabList.findElements(By.cssSelector("div[role='tab']"));
        By trapLocator = By.cssSelector("div.clickTrapContainer_da8173.trapClicks_da8173");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (WebElement tab : tabs) {
            String name = tab.getText().trim();
            System.out.println("Clicking tab: " + name);
            wait.until(ExpectedConditions.invisibilityOfElementLocated(trapLocator));
            wait.until(ExpectedConditions.elementToBeClickable(tab));

            try{
                tab.click();
            } catch(ElementClickInterceptedException e){
                wait.until(ExpectedConditions.invisibilityOfElementLocated(trapLocator));
                js.executeScript("arguments[0].click()", tab);

            }
            // (Optional) wait until this tab has aria-selected="true"
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(d -> "true".equals(tab.getAttribute("aria-selected")));
            Thread.sleep(2000);
        }

    }

    // Test open a DM thread
    @Test
    public void testOpenDMThread() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open a DM thread
        WebElement createDmThread = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("svg.privateChannelRecipientsInviteButtonIcon__99e7c.icon__9293f")));
        createDmThread.click();
        // Verify the DM thread is opened
        assert createDmThread.isDisplayed();
        Thread.sleep(2000);

        //Create group DM test by counteracting click trap.
        By trap = By.cssSelector("div.clickTrapContainer_da8173.trapClicks_da8173");
        By newGroupDm = By.cssSelector("div[aria-label='New Group DM']");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait.until(ExpectedConditions.invisibilityOfElementLocated(trap));

        WebElement btn = driver.findElement(newGroupDm);
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", btn);

        wait.until(ExpectedConditions.elementToBeClickable(newGroupDm));

        //Last attempt, try normal click to circumvent click trap
        try {
            btn.click();
        } catch (ElementClickInterceptedException ex) {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(trap));
            js.executeScript("arguments[0].click();", btn);
        }

    }
}
