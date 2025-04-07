/***********************************************
 * file: TestLogin.java
 * dev: Braxton Tillman
 * desc: This test class will test discord.com login
 *       with various username and password cases
 ***********************************************/
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;

public class TestLogin extends BaseClass {

    // This test the login process with valid credentials
    @Test
    public void testLoginWithValidCredentials() throws InterruptedException {
        driver.get("https://discord.com/login");
        Thread.sleep(3000);

        // Use valid credentials
        WebElement emailField = driver.findElement(By.name("email"));
        Thread.sleep(3000);
        WebElement passwordField = driver.findElement(By.name("password"));
        Thread.sleep(3000);
        emailField.sendKeys("SoftwareTestingDK@mail.com"); // CHANGE IF DISCORD ACCOUNT IS DISABLED
        passwordField.sendKeys("PaulBlart123!"); // CHANGE IF DISCORD ACCOUNT IS DISABLED
        Thread.sleep(3000);

        // Log in
        passwordField.submit(); // Instead of using Login button
        Thread.sleep(6000);

    }

    // This tests the login process with invalid credentials
    @Test
    public void testLoginWithInvalidCredentials() throws InterruptedException {
        driver.get("https://discord.com/login");
        Thread.sleep(3000);

        // Use invalid credentials
        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        Thread.sleep(3000);
        emailField.sendKeys("NotSoftwareTestingDK@gmail.com"); // Do not change
        passwordField.sendKeys("wrongpassword123!"); // Do not change
        Thread.sleep(3000);

        // Log in
        passwordField.submit(); // Instead of using login button
        Thread.sleep(3000);

    }

    // This tests the forgot password flow on the login page
    @Test
    public void testForgotPasswordFlow() throws InterruptedException {
        driver.get("https://discord.com/login");
        Thread.sleep(3000);

        // Enter email to enable the forgot password link
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("SoftwareTestingDK@gmail.com");
        Thread.sleep(3000);

        // Click the "Forgot your password?" link
        WebElement forgotPasswordLink = driver.findElement(By.className("contents__201d5"));
        forgotPasswordLink.click();
        Thread.sleep(3000);
    }

    // This checks if there is a QR code on the login page
    @Test
    public void testQRCodeIsDisplayed() throws InterruptedException {
        driver.get("https://discord.com/login");
        Thread.sleep(3000);

        // Check for QR Code
        WebElement qrImage = driver.findElement(By.xpath("//*[@id=\"app-mount\"]/div[2]/div[1]/div[1]/div/div/div/div/form/div[2]/div/div[3]/div/div/div/div[1]"));

        // Condition: QR is either displayed or not displayed
        if (qrImage.isDisplayed()) {
            System.out.println("QR Code is displayed successfully.");
        } else {
            System.out.println("QR Code is NOT displayed.");
        }

        Thread.sleep(3000);
    }

    // This checks the page title and elements on the page
    // NOTE: The way Discord is set up there should be no links on the login page
    @Test
    public void testPageTitleAndElements() {
        // Open the Discord login page
        driver.get("https://discord.com/login");

        // Get the page title and print it
        String pageTitle = driver.getTitle();
        System.out.println("Page Title: " + pageTitle);

        // Get the number of links on the page
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Number of links on the page: " + links.size());

        // Get number of inputs
        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        System.out.println("Number of input fields on the page: " + inputs.size());

        // Check for login button
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));
        System.out.println("Login button is displayed: " + loginButton.isDisplayed());

        // Make sure that the page title contains discord and there are no links on the login page
        assert pageTitle.contains("Discord");
        assert links.isEmpty();  // There should be no links on the page!
    }
}