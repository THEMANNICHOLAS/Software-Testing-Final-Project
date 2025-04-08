/***********************************************
 * file: TestUserAudio.java
 * dev: George Alenchery
 * desc: This test class will test the audio features
 * for joining a voice channel, muting and unmuting,
 * and speaker and microphone control.
 ***********************************************/

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import java.time.Duration;

public class TestUserAudio extends BaseClass {

    public void loginToDiscordAudio() throws InterruptedException {
        driver.get("https://discord.com/login");
        Thread.sleep(3000);  // Wait for the page to load

        // Use valid credentials (replace with your own credentials for testing)
        WebElement emailField = driver.findElement(By.name("email"));
        WebElement passwordField = driver.findElement(By.name("password"));
        emailField.sendKeys("SoftwareTestingDK@mail.com"); //
        passwordField.sendKeys("PaulBlart123!"); //
        Thread.sleep(3000);

        // Log in by submitting the password form
        passwordField.submit();

        // Wait for the Discord dashboard to load
        Thread.sleep(8000);  // Increase the time if the page is slow to load
    }

    // Test joining a voice channel
    @Test
    public void testJoinVoiceChannel() throws InterruptedException {
        loginToDiscordAudio(); // Now using the renamed method

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate and click the voice channel
        WebElement voiceChannel = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(text(),'Voice Channel Name')]")
        ));
        voiceChannel.click();

        // Wait for audio controls to load
        WebElement muteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Mute']")
        ));
        WebElement unmuteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Unmute']")
        ));

        // Assert that mute and unmute buttons are visible
        assert muteButton.isDisplayed();
        assert unmuteButton.isDisplayed();
    }

    // Test mute audio
    @Test
    public void testMuteAudio() throws InterruptedException {
        loginToDiscordAudio(); // Using the renamed login method

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate mute button and click it
        WebElement muteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Mute']")
        ));
        muteButton.click();

        // Assert that the unmute button appears after mute
        WebElement unmuteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Unmute']")
        ));
        assert unmuteButton.isDisplayed();
    }

    // Test unmute audio
    @Test
    public void testUnmuteAudio() throws InterruptedException {
        loginToDiscordAudio(); // Using the renamed login method

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate unmute button and click it
        WebElement unmuteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Unmute']")
        ));
        unmuteButton.click();

        // Assert that mute button appears after unmute
        WebElement muteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Mute']")
        ));
        assert muteButton.isDisplayed();
    }

    // Test microphone control (mute/unmute)
    @Test
    public void testMicControl() throws InterruptedException {
        loginToDiscordAudio(); // Using the renamed login method

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate and click microphone button to mute/unmute
        WebElement micButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Microphone']"))
        );
        micButton.click();

        // Assert that the mic button toggles
        assert micButton.getAttribute("aria-pressed").equals("true");
    }

    // Test speaker control (mute/unmute)
    @Test
    public void testSpeakerControl() throws InterruptedException {
        loginToDiscordAudio(); // Using the renamed login method

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate and click speaker button to mute/unmute
        WebElement speakerButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@aria-label='Speaker']"))
        );
        speakerButton.click();

        // Assert that the speaker button toggles
        assert speakerButton.getAttribute("aria-pressed").equals("true");
    }
}
