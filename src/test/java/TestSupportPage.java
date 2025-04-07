/***********************************************
 * file: TestSupportPage.java
 * dev: Nicholas Perez
 * desc:This class will test the support page
 * located under discord.com main page dropdown
 ***********************************************/
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TestSupportPage extends BaseClass{
    @Test
    public void testHelpCenter() throws InterruptedException {
        driver.get("https://discord.com/");
        WebElement supportLink = driver.findElement(By.cssSelector("a[href='https://support.discord.com/hc/']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(supportLink).click().perform();
        Thread.sleep(2000);
        String helpCenterLink = "https://support.discord.com/hc/en-us";
        String actualLink = driver.getCurrentUrl();
        Assert.assertEquals(actualLink, helpCenterLink);
    }
    @Test
    public void testFeedback() throws InterruptedException {
        driver.get("https://discord.com/");
        WebElement supportTrigger = driver.findElement(By.id("w-dropdown-toggle-2"));
        Actions actions = new Actions(driver);
        actions.moveToElement(supportTrigger).perform();
        Thread.sleep(2000);
        WebElement feedbackLink = driver.findElement(By.linkText("Feedback"));
        feedbackLink.click();
        String expectedLink = "https://support.discord.com/hc/en-us/community/topics";
        Thread.sleep(2000);
        String actualLink = driver.getCurrentUrl();
        Assert.assertEquals(actualLink, expectedLink);

    }
    @Test
    public void testSubmitRequest() throws InterruptedException {
        driver.get("https://support.discord.com/hc/en-us/community/topics");
        WebElement requestLink = driver.findElement(By.linkText("Submit a request"));
        requestLink.click();
        Thread.sleep(2000);
        WebElement helpSupportDropdown = driver.findElement(By.cssSelector("div.request_ticket_form_id a.nesty-input"));
        Actions actions = new Actions(driver);
        actions.moveToElement(helpSupportDropdown).click().perform();
        Thread.sleep(2000);
        WebElement refundsOption = driver.findElement(By.cssSelector("div.nesty-panel"));
        refundsOption.click();
        Thread.sleep(2000);
    }

    @Test
    public void testSearch() throws InterruptedException {
        driver.get("https://support.discord.com/hc/en-us/community/topics");
        WebElement searchInput = driver.findElement(By.id("query"));
        searchInput.click();
        Thread.sleep(2000);
        searchInput.sendKeys("Refund");
        searchInput.sendKeys(Keys.ENTER);
        Thread.sleep(2000);
        WebElement refundPolicyLink = driver.findElement(By.linkText("Refund Policy"));
        refundPolicyLink.click();
        Thread.sleep(2000);
        String actualLink = driver.getCurrentUrl();
        String expectedLink = "https://support.discord.com/hc/en-us/articles/360012668071-Refund-Policy";
        Assert.assertEquals(actualLink, expectedLink);
    }

    @Test
    //TODO: Not working and selecting Espanol language, fix it
    public void testLanguageChange() throws InterruptedException {
        driver.get("https://support.discord.com/hc/en-us");
        // Locate the language button and click it
        WebElement languageButton = driver.findElement(By.cssSelector("div.dropdown.language-selector button.dropdown-toggle"));
        Actions actions = new Actions(driver);
        actions.moveToElement(languageButton).click().perform();

        // Wait until the dropdown menu becomes visible and the Spanish option is clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement spanishLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[normalize-space(text())='Español']")));

        // Click the Spanish option
        spanishLink.click();
        String expectedLink = "https://support.discord.com/hc/es";
        String actualLink = driver.getCurrentUrl();
        Assert.assertEquals(actualLink, expectedLink);

    }

}
