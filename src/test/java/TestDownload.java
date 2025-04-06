/***********************************************
 * file: TestDownload.java
 * dev:
 * desc:this test class will test discord.com to
 * see if the Discord app is downloaded from
 * the website.
 ***********************************************/

import java.io.File;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDownload extends BaseClass {
    public TestDownload() {
    }

    @Test
    public void downloadWindows() {
        this.driver.get("https://discord.com/download");
        WebElement downloadButton = this.driver.findElement(By.className("btn"));
        new File(this.DOWNLOAD_DIR);
        String expectedFileName = "DiscordSetup.exe";
        File downloadFile = new File(this.DOWNLOAD_DIR + "/" + expectedFileName);
        downloadButton.click();

        for(int waitTime = 0; waitTime < 60 && !downloadFile.exists(); ++waitTime) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Assert.assertTrue(downloadFile.exists());
    }

    @Test
    public void downloadMacOS() {
        this.driver.get("https://discord.com/download");
        WebElement downloadButton = this.driver.findElement(By.className("btn-download"));
        new File(this.DOWNLOAD_DIR);
        String expectedFileName = "Discord.dmg";
        File downloadFile = new File(this.DOWNLOAD_DIR + "/" + expectedFileName);
        downloadButton.click();

        for(int waitTime = 0; waitTime < 60 && !downloadFile.exists(); ++waitTime) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Assert.assertTrue(downloadFile.exists());
    }

    @Test
    public void downloadLinux() throws InterruptedException {
        this.driver.get("https://discord.com/download");
        WebElement dropdown = this.driver.findElement(By.className("dropdown-linux"));
        dropdown.click();
        WebElement debDownload = this.driver.findElement(By.xpath("//*[@id=\"w-dropdown-list-10\"]/a[1]"));
        WebElement targzDownload = this.driver.findElement(By.xpath("//*[@id=\"w-dropdown-list-10\"]/a[2]"));
        new File(this.DOWNLOAD_DIR);
        Thread.sleep(1000L);
        debDownload.click();
        String debExpectedFile = "discord-0.0.90.deb";
        new File(this.DOWNLOAD_DIR + "/" + debExpectedFile);
        targzDownload.click();
        String targzExpectedFile = "discord-0.0.90.tar";
        new File(this.DOWNLOAD_DIR + "/" + targzExpectedFile);
    }

    @Test
    public void downloadTestBuilds() throws InterruptedException {
        this.driver.get("https://discord.com/download");
        WebElement dropdown = this.driver.findElement(By.className("dropdown-list-6"));
        dropdown.click();
        Thread.sleep(1000L);
        new File(this.DOWNLOAD_DIR);
        WebElement macTestBuild = this.driver.findElement(By.linkText("Mac"));
        String macExpectedFile = "DiscordPTB.dmg";
        File macDownloadFile = new File(this.DOWNLOAD_DIR + "/" + macExpectedFile);
        macTestBuild.click();
        WebElement linuxDebTestBuild = this.driver.findElement(By.linkText("Linux deb"));
        String linuxDebExpectedFile = "DiscordPTB.deb";
        File linuxDebDownloadFile = new File(this.DOWNLOAD_DIR + "/" + linuxDebExpectedFile);
        linuxDebTestBuild.click();
        WebElement windowTestBuild = this.driver.findElement(By.linkText("Windows 64-bit"));
        String windowTestBuildExpectedFile = "DiscordPTB.exe";
        File windowTestBuildDownloadFile = new File(this.DOWNLOAD_DIR + "/" + windowTestBuildExpectedFile);
        windowTestBuild.click();
        WebElement linuxTarTestBuild = this.driver.findElement(By.linkText("Linux tar.gz"));
        String linuxTarTestBuildExpectedFile = "";
        File linuxTarTestBuildDownloadFile = new File(this.DOWNLOAD_DIR + "/" + linuxTarTestBuildExpectedFile);
        linuxTarTestBuild.click();

        for(int waitTime = 0; waitTime < 60; ++waitTime) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Assert.assertTrue(macDownloadFile.exists());
        Assert.assertTrue(linuxDebDownloadFile.exists());
        Assert.assertTrue(windowTestBuildDownloadFile.exists());
        Assert.assertTrue(linuxTarTestBuildDownloadFile.exists());
    }

    @Test
    public void downloadMobile() throws InterruptedException {
        this.driver.get("https://discord.com/download");
        WebElement iosDownloadButton = this.driver.findElement(By.linkText("App store"));
        new WebDriverWait(this.driver, Duration.ofSeconds(5L));
        String expectedIOSURL = "https://apps.apple.com/us/app/discord-talk-play-hang-out/id985746746?attemptId=fddff72c-82f8-4d90-ba47-54f0660b30d8";
        String actualURL = this.driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedIOSURL);
        Thread.sleep(1000L);
        WebElement androidDownloadButton = this.driver.findElement(By.linkText("Google Play"));
        androidDownloadButton.click();
        new WebDriverWait(this.driver, Duration.ofSeconds(5L));
        String expectedAndroidURL = "https://play.google.com/store/apps/details?id=com.discord&attemptId=ff6ba3d5-411f-4aec-8d2f-8573bb28dfe8";
        String actualAndroidURL = this.driver.getCurrentUrl();
        Assert.assertEquals(actualAndroidURL, expectedAndroidURL);
    }
}
