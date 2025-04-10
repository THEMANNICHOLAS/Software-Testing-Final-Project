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
import java.util.ArrayList;
import java.util.List;

public class TestSupportPage extends BaseClass {
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
    public void testHelpCenterOptions() throws InterruptedException {
        String mainURL = "https://support.discord.com/hc/en-us";
        driver.get(mainURL);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        List<WebElement> linkElements = driver.findElements(By.cssSelector("ul.flex-container > a"));

        // Extract the href attributes into a list of URLs
        List<String> urls = new ArrayList<>();
        for (WebElement link : linkElements) {
            String href = link.getAttribute("href");
            if (href != null) {
                if (href.startsWith("//")) {
                    href = "https:" + href;
                }
                urls.add(href);
            }
        }

        // Loop through the collected URLs
        for (String url : urls) {
            System.out.println("Navigating to: " + url);
            driver.get(url);

            try {
                Thread.sleep(2000); // Wait for 2 seconds; use WebDriverWait for more robust synchronization.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Navigate back to the main page by reloading the URL
            driver.get(mainURL);
        }


    }
}
