/************************************************
 file: BaseClass.java
 dev: Braxton Tillman
 desc: Serves as the testing class for creating
 different types of servers. It will test
 creating a server, using templates, canceling
 server creation, etc.
 ***********************************************/
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;



public class TestServer extends BaseClass {

    @Test
    public void createServerFromScratch() throws InterruptedException {
        // Login to Discord
        loginToDiscord();

        // Wait for page to load and button to be present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click Add a Server button
        WebElement addServerBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("#app-mount > div.appAsidePanelWrapper_a3002d > div.notAppAsidePanel_a3002d > div.app_a3002d > div > div.layers__960e4.layers__160d8 > div > div > div > div.content_c48ade > div.sidebar_c48ade > nav > ul > div.itemsContainer_ef3116 > div > div.tutorialContainer__650eb")
        ));
        addServerBtn.click();
        Thread.sleep(3000);


        // Click "Create My Own"
        WebElement createOwn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='Create My Own']")
        ));
        createOwn.click();
        Thread.sleep(3000);

        // Click "For me and my friends"
        WebElement forFriends = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='For me and my friends']"))
        );
        forFriends.click();
        Thread.sleep(3000);

        // Click create button ( Have to use JS to click. Will not work because of a backdrop issue)
        WebElement createBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.='Create']"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createBtn);

        Thread.sleep(5000);
    }

    // 2. Create a server using a gaming template
    @Test
    public void createGamingTemplateServer() throws InterruptedException {
        // Login to Discord
        loginToDiscord();

        // Click Add a Server
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#app-mount > div.appAsidePanelWrapper_a3002d > div.notAppAsidePanel_a3002d > div.app_a3002d > div > div.layers__960e4.layers__160d8 > div > div > div > div.content_c48ade > div.sidebar_c48ade > nav > ul > div.itemsContainer_ef3116 > div > div.tutorialContainer__650eb"))).click();

        // Click "Gaming" Template
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Gaming']"))).click();

        // Click for me and my friends
        WebElement forFriends = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='For me and my friends']"))
        );
        forFriends.click();
        Thread.sleep(3000);

        // Click create button ( Have to use JS to click. Will not work because of a backdrop issue)
        WebElement createBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.='Create']"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createBtn);

        Thread.sleep(5000);

    }

    // 3. Create a server for a club or community
    @Test
    public void createServerForClub() throws InterruptedException {
        // Login to Discord
        loginToDiscord();

        // Add a server button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#app-mount > div.appAsidePanelWrapper_a3002d > div.notAppAsidePanel_a3002d > div.app_a3002d > div > div.layers__960e4.layers__160d8 > div > div > div > div.content_c48ade > div.sidebar_c48ade > nav > ul > div.itemsContainer_ef3116 > div > div.tutorialContainer__650eb"))).click();

        //Navigate to create my own
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Create My Own']"))).click();
        Thread.sleep(3000);

        // Navigate to Club or Community
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='For a club or community']"))).click();
        Thread.sleep(3000);

        // Click create button ( Have to use JS to click. Will not work because of a backdrop issue)
        WebElement createBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.='Create']"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createBtn);

        Thread.sleep(5000);
    }

    // 4. Create a server using a study group template
    @Test
    public void createStudyGroupServer() throws InterruptedException {
        // Login to Discord
        loginToDiscord();

        // CLick on Add a Server
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#app-mount > div.appAsidePanelWrapper_a3002d > div.notAppAsidePanel_a3002d > div.app_a3002d > div > div.layers__960e4.layers__160d8 > div > div > div > div.content_c48ade > div.sidebar_c48ade > nav > ul > div.itemsContainer_ef3116 > div > div.tutorialContainer__650eb"))).click();

        // Click "Gaming" Template
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Study Group']"))).click();

        // Click for me and my friends
        WebElement forFriends = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='For me and my friends']"))
        );
        forFriends.click();
        Thread.sleep(3000);

        // Click create button ( Have to use JS to click. Will not work because of a backdrop issue)
        WebElement createBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.='Create']"))
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createBtn);

        Thread.sleep(5000);
    }

    // 5. Cancel server creation from the modal
    @Test
    public void cancelServerCreation() throws InterruptedException {
        // Login to Discord
        loginToDiscord();

        // Click on Add a Server
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(
                "#app-mount > div.appAsidePanelWrapper_a3002d > div.notAppAsidePanel_a3002d > div.app_a3002d > div > div.layers__960e4.layers__160d8 > div > div > div > div.content_c48ade > div.sidebar_c48ade > nav > ul > div.itemsContainer_ef3116 > div > div.tutorialContainer__650eb"))
        ).click();

        // Click on "Create My Own"
        WebElement createOwn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='Create My Own']")
        ));
        createOwn.click();
        Thread.sleep(3000);

        // Click on "For me and my friends"
        WebElement forFriends = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[text()='For me and my friends']"))
        );
        forFriends.click();
        Thread.sleep(3000);
        
        // Wait for the modal to appear
        WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Close' or contains(@class, 'closeButton_c04f35')]")
        ));

        // Click the corner x button to close the modal
        closeBtn.click();

        // Verify the modal is closed
        boolean modalClosed = wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".backdrop__78332")
        ));
        assert modalClosed;

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