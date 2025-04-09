/***********************************************
 * file: TestHomeTab.java
 * dev: Braxton Tillman
 * desc: this test class will test the home page
 * and its various tabs and links
 ***********************************************/

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class TestHomePage extends BaseClass {
    // This test the home icon. The home icon can only be checked if you navigate to a different URL
    // NOTE: The home button is not traditional and it's technically the Direct Messages button
    @Test
    public void verifyHomeIconIsClickable() throws InterruptedException {
        // Log in to Discord
        loginToDiscord();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to test server
        WebElement firstServer = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div[aria-label][data-list-item-id^='guildsnav_']:not([data-list-item-id$='__home'])")));
        firstServer.click();

        Thread.sleep(3000);

        // Navigate back to home using the home button
        WebElement homeIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div[data-list-item-id='guildsnav___home']")));
        homeIcon.click();
        Thread.sleep(3000);

        // Verify we're back on the home page (URL should contain /channels/@me)
        wait.until(ExpectedConditions.urlContains("/channels/@me"));
        String currentUrl = driver.getCurrentUrl();
        String pageTitle = driver.getTitle();

        System.out.println("URL: " + currentUrl);
        System.out.println("Title: " + pageTitle);

        Assert.assertTrue(driver.getCurrentUrl().contains("/channels/@me"));
    }

    // This tests the discover button and the tabs associated with it
    @Test
    public void testDiscoverButton() throws InterruptedException {
        // Log in to Discord
        loginToDiscord();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Click the "Discover" button (compass icon)
        WebElement discoverBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div[aria-label='Discover']")));

        discoverBtn.click();
        Thread.sleep(3000);

        // Wait for content to load. Verify tabs are present. Then navigate to tabs
        WebElement appsTab = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'Apps')]")));
        appsTab.click();
        Thread.sleep(3000);

        WebElement serversTab = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'Servers')]")));
        serversTab.click();
        Thread.sleep(3000);

        WebElement questsTab = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(),'Quests')]")));
        questsTab.click();
        Thread.sleep(3000);

        // Verify the display
        Assert.assertTrue(appsTab.isDisplayed());
        Assert.assertTrue(serversTab.isDisplayed());
        Assert.assertTrue(questsTab.isDisplayed());
    }

    // This tests the inbox button and navigates between mentions tab and unread tab
    @Test
    public void testInboxButton() throws InterruptedException {
        // Log in to Discord
        loginToDiscord();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Locate inbox button
        WebElement inboxBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div[aria-label='Inbox']")));

        // Click inbox
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", inboxBtn);
        Thread.sleep(3000);

        // Wait for the inbox modal
        WebElement inboxHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(text(), 'Inbox')]")));

        Assert.assertTrue(inboxHeader.isDisplayed());

        // UNREAD AND MENTIONS TABS
        // Click the Mentions tab inside the Inbox
        WebElement mentionsTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div[aria-label='Mentions']")));
        mentionsTab.click();
        Thread.sleep(3000);

        // Assert the Mentions tab is now active
        WebElement activeMentionsTab = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[aria-label='Mentions'][aria-selected='true']")));
        Assert.assertTrue(activeMentionsTab.isDisplayed());

        // Click the Unreads tab
        WebElement unreadsTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='tab' and text()='Unreads']")));
        unreadsTab.click();
        Thread.sleep(3000);

        // Assert the Unreads tab is now selected
        WebElement activeUnreadsTab = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='tab' and text()='Unreads' and @aria-selected='true']")));
        Assert.assertTrue(activeUnreadsTab.isDisplayed());

    }

    // This tests the user modal in the homepage
    @Test
    public void testUserHomePagePresence() throws InterruptedException {
        // Log in to Discord
        loginToDiscord();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Mute button
        WebElement muteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("button[aria-label='Mute']")));
        muteButton.click();
        Thread.sleep(3000);

        // Deafen button
        WebElement deafenButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("button[aria-label='Deafen']")));
        deafenButton.click();
        Thread.sleep(3000);

        // Wait for user control panel (bottom-left of page)
        WebElement userPanel = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("div[class*='container__']")));  // Broad match for user panel
        userPanel.click();
        Thread.sleep(3000);

        // Assert visibility of user panel, mute button, and deafen button
        Assert.assertTrue(userPanel.isDisplayed());
        Assert.assertTrue(muteButton.isDisplayed());
        Assert.assertTrue(deafenButton.isDisplayed());

    }


    // This tests the shop tab on the home page and "attempts" to buy an item
    @Test
    public void verifyShopTab() throws InterruptedException {
        // Log in to Discord
        loginToDiscord();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait for the Shop tab to be clickable
        WebElement shopTab = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#app-mount > div.appAsidePanelWrapper_a3002d > div.notAppAsidePanel_a3002d > div.app_a3002d > div > div.layers__960e4.layers__160d8 > div > div > div > div.content_c48ade > div.sidebar_c48ade > div.sidebarList_c48ade.sidebarListRounded_c48ade > nav > div.scroller__99e7c.thin__99f8c.scrollerBase__99f8c.fade__99f8c > ul > li > div")));
        // Click the Shop tab
        shopTab.click();

        // Wait for the Shop page to load by checking for a known element on the page
        WebElement shopHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(text(),'Shop')]")));
        Assert.assertTrue(shopHeader.isDisplayed());
        Thread.sleep(3000);

        // Wait for the Buy button to be clickable
        WebElement buyButton = driver.findElement(By.xpath("//div[contains(@class, 'shopCardDark_c3d04b')]//button//div[contains(text(), 'Buy for $10.99')]"));
        buyButton.click();

        // Wait for modal details to appear
        Thread.sleep(4000); // or use wait if it has a known header or modal class

        // Close the popup (usually an "X")
        WebElement closeButton = driver.findElement(By.cssSelector("button[aria-label='Close'], div[aria-label='Close']"));
        closeButton.click();
        Thread.sleep(3000);

    }

    // Login method
    private void loginToDiscord() throws InterruptedException {
        driver.get("https://discord.com/login");
        Thread.sleep(3000);

        // Use valid credentials
        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        emailField.sendKeys("SoftwareTestingDK@mail.com"); // CHANGE IF DISCORD DISABLES ACCOUNT
        passwordField.sendKeys("PaulBlart123!"); // CHANGE IF DISCORD DISABLES ACCOUNT
        Thread.sleep(3000);

        // Log in
        passwordField.submit();

        Thread.sleep(8000); // wait for dashboard to load
    }
}
