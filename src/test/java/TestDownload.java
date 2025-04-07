/***********************************************
 * file: TestDownload.java
 * dev: Nicholas Perez
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
        File debFile = new File(this.DOWNLOAD_DIR + "/" + debExpectedFile);
        targzDownload.click();
        String targzExpectedFile = "discord-0.0.90.tar";
        File tarFile = new File(this.DOWNLOAD_DIR + "/" + targzExpectedFile);

        for (int waitTime = 0; waitTime <60 && !debFile.exists() && !tarFile.exists(); ++waitTime) {
            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        Assert.assertTrue(debFile.exists());
        Assert.assertTrue(tarFile.exists());
    }

    @Test
    public void downloadTestBuilds() throws InterruptedException {
        this.driver.get("https://discord.com/download");
        WebElement dropdown = this.driver.findElement(By.id("w-dropdown-toggle-11"));
        dropdown.click();
        Thread.sleep(1000L);
        new File(this.DOWNLOAD_DIR);
        WebElement macTestBuild = this.driver.findElement(By.linkText("Mac"));
        String macExpectedFile = "DiscordPTB.dmg";
        File macDownloadFile = new File(this.DOWNLOAD_DIR + "/" + macExpectedFile);
        macTestBuild.click();

        WebElement linuxDebTestBuild = this.driver.findElement(By.linkText("Linux deb"));
        String linuxDebExpectedFile = "discord-ptb-0.0.136.deb";
        File linuxDebDownloadFile = new File(this.DOWNLOAD_DIR + "/" + linuxDebExpectedFile);
        linuxDebTestBuild.click();

        WebElement windowTestBuild = this.driver.findElement(By.linkText("Windows 64-bit"));
        String windowTestBuildExpectedFile = "DiscordPTBSetup.exe";
        File windowTestBuildDownloadFile = new File(this.DOWNLOAD_DIR + "/" + windowTestBuildExpectedFile);
        windowTestBuild.click();

        WebElement linuxTarTestBuild = this.driver.findElement(By.linkText("Linux tar.gz"));
        String linuxTarTestBuildExpectedFile = "discord-ptb-0.0.136.tar";
        File linuxTarTestBuildDownloadFile = new File(this.DOWNLOAD_DIR + "/" + linuxTarTestBuildExpectedFile);
        linuxTarTestBuild.click();

        //TODO: make sure time for downloads is proper and that Discord is not kicking us from download
        for(int waitTime = 0;
            waitTime < 60 && !macDownloadFile.exists() && !linuxDebDownloadFile.exists() &&
                    !windowTestBuildDownloadFile.exists() && !linuxTarTestBuildDownloadFile.exists(); ++waitTime) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(1000);
        Assert.assertTrue(macDownloadFile.exists());
        Assert.assertTrue(linuxDebDownloadFile.exists());
        Assert.assertTrue(windowTestBuildDownloadFile.exists());
        Assert.assertTrue(linuxTarTestBuildDownloadFile.exists());
    }

    @Test
    public void downloadMobile() throws InterruptedException {
        this.driver.get("https://discord.com/download");
        String downloadPage = "https://discord.com/download";
        WebElement iosDownloadButton = this.driver.findElement(By.cssSelector(
                "a[data-platform='iOS'][data-track-download='Download Page']"));
        iosDownloadButton.click();
        new WebDriverWait(this.driver, Duration.ofSeconds(5L));
        String actualURL = this.driver.getCurrentUrl();
        Assert.assertNotEquals(actualURL, downloadPage);

        Thread.sleep(1000);
        driver.get(downloadPage); //revert back to download page
        Thread.sleep(1000);
        WebElement androidDownloadButton = this.driver.findElement(By.cssSelector(
                "a[data-platform='Android'][data-track-download='Download Page']"));
        androidDownloadButton.click();
        new WebDriverWait(this.driver, Duration.ofSeconds(5L));
        String actualAndroidURL = this.driver.getCurrentUrl();
        Assert.assertNotEquals(actualAndroidURL, downloadPage);
        Thread.sleep(1000);
    }
}
