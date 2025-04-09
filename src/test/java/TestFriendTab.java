/***********************************************
 * file: TestFriendTab.java
 * dev: Nicholas Perez
 * desc: this test class will test the friend
 * functionality of the online discord app
 ***********************************************/
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
/*

 */

public class TestFriendTab extends BaseClass {

    @BeforeMethod
    //Help function to log in first onto Discord
    public void login() throws InterruptedException {
        driver.get("https://discord.com/login");
        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        emailField.sendKeys("softwareTestingdk@mail.com");
        passwordField.sendKeys("PaulBlart123!");
        passwordField.submit(); // Instead of using Login button
    }

    @Test
    public void testViewPending() throws InterruptedException {
        //CSS selector for the "Pending" tab after login
        driver.get("https://discord.com/channels/@me"); //Account already logged in to prevent discord from banning us
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement pendingTab = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
                "div[role='tab'][aria-controls='pending-tab']")));

        //Must click on button
        Actions actions = new Actions(driver);
        actions.moveToElement(pendingTab).click().perform();
        Thread.sleep(2000);

    }

    @Test
    public void testAddFriend() throws InterruptedException {
        driver.get("https://discord.com/channels/@me"); //Account already logged in to prevent discord from banning us
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //CSS selector for the "Add Friend" Tab
        WebElement addFriendTab = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
                "div[aria-label='Add Friend']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(addFriendTab).click().perform();
        Thread.sleep(2000);

        WebElement friendSearchBar = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[aria-label='You can add friends with their Discord username.']")
                )
        );
        friendSearchBar.click();
        friendSearchBar.sendKeys("Hello Test!");
        Thread.sleep(2000);
    }

    @Test
    public void testAcceptFriend() throws InterruptedException {
        driver.get("https://discord.com/channels/@me"); //Account already logged in to prevent discord from banning us
        //A friend request was already sent from my personal account so we can test the feature.
        //A friend request is required to confirm this test.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement acceptFriendButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
                "div[aria-label='Accept'][role='button']")));
        Actions actions = new Actions(driver);
        actions.moveToElement(acceptFriendButton).click().perform();
        Thread.sleep(2000);


    }

    @Test
    public void testSearchFriend() throws InterruptedException {
        driver.get("https://discord.com/channels/@me"); //Account already logged in to prevent discord from banning us
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement friendSearchBar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
                "input[aria-label='Search']")));
        Thread.sleep(2000);
        friendSearchBar.click();
        friendSearchBar.sendKeys("Hello Test!"); //Obviously will shop up blank because not a friend
        Thread.sleep(2000);
    }

    @Test
    public void testRemoveFriend() throws InterruptedException {
        driver.get("https://discord.com/channels/@me"); //Account already logged in to prevent discord from banning us
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement allButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
                "div.item__133bf.item_b3f026.selected_b3f026.themed_b3f026")));
        Actions action  = new Actions(driver);
        action.moveToElement(allButton).click().perform();
        WebElement moreButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
                "div.actionButton_f8fa06.highlight_f8fa06")));
        Thread.sleep(2000);
        action.moveToElement(moreButton).click().perform();
        Thread.sleep(2000);
    }
}
