package is.ru.TicTacToe;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.net.URL;

import static org.junit.Assert.assertEquals;

public class ChromeTestsIT {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        // Choose the browser, version, and platform to test
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("platform", "Windows 8");
        caps.setCapability("version", "30");
        // Create the connection to Sauce Labs to run the tests
        this.driver = new RemoteWebDriver(
                new URL("http://dextroc:49c6e8b6-acf6-4024-a3ff-f766fa7902e4@ondemand.saucelabs.com:80/wd/hub"),
                caps);
        baseUrl = "http://tictacwhoop.cloudcontrolapp.com/";
    }

    @Test
    public void checkTitle() throws Exception {
        // Make the browser get the page and check its title
        driver.get(baseUrl);
        assertEquals("TicTacToe", driver.getTitle());
    }

    @Test
    public void testTieEndmsgButton() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.xpath("//div/span")).click();
        driver.findElement(By.xpath("//span[2]")).click();
        driver.findElement(By.xpath("//span[3]")).click();
        driver.findElement(By.xpath("//div[2]/span[2]")).click();
        driver.findElement(By.xpath("//div[3]/span[2]")).click();
        driver.findElement(By.xpath("//div[2]/span")).click();
        driver.findElement(By.xpath("//div[3]/span")).click();
        driver.findElement(By.xpath("//div[3]/span[3]")).click();
        driver.findElement(By.xpath("//div[2]/span[3]")).click();
        assertEquals("Jafntefli!", driver.findElement(By.id("results")).getText());
        assertEquals("Leik lokið", driver.findElement(By.id("next_player")).getText());
        assertEquals("Spila aftur", driver.findElement(By.id("new_game")).getText());
    }

    @Test
    public void testTurns() throws Exception {
        driver.get(baseUrl);
        assertEquals("Spilari X gerir fyrst", driver.findElement(By.id("next_player")).getText());
        driver.findElement(By.xpath("//div/span")).click();
        assertEquals("Spilari O gerir næst", driver.findElement(By.id("next_player")).getText());
        driver.findElement(By.xpath("//span[2]")).click();
        assertEquals("Spilari X gerir næst", driver.findElement(By.id("next_player")).getText());
    }

    @Test
    public void testWinsScoreXO() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.xpath("//div/span")).click();
        driver.findElement(By.xpath("//div[2]/span[2]")).click();
        driver.findElement(By.xpath("//span[2]")).click();
        driver.findElement(By.xpath("//div[2]/span")).click();
        driver.findElement(By.xpath("//span[3]")).click();
        assertEquals("Spilari 1 vann!!!", driver.findElement(By.id("results")).getText());
        assertEquals("1", driver.findElement(By.id("score1")).getText());
        driver.findElement(By.id("new_game")).click();
        assertEquals(" ", driver.findElement(By.xpath("//span[2]")).getText());
        assertEquals("Spilari X gerir fyrst", driver.findElement(By.id("next_player")).getText());
        driver.findElement(By.xpath("//span[2]")).click();
        driver.findElement(By.xpath("//span[3]")).click();
        driver.findElement(By.xpath("//div[2]/span[2]")).click();
        driver.findElement(By.xpath("//div[2]/span[3]")).click();
        driver.findElement(By.xpath("//div[2]/span")).click();
        driver.findElement(By.xpath("//div[3]/span[3]")).click();
        assertEquals("Spilari 2 vann!!!", driver.findElement(By.id("results")).getText());
        assertEquals("1", driver.findElement(By.id("score2")).getText());
        assertTrue(isElementPresent(By.id("new_game")));
    }
    
    @Test
    public void testRefresh() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.xpath("//div/span")).click();
        driver.findElement(By.xpath("//div[2]/span")).click();
        driver.findElement(By.xpath("//span[2]")).click();
        driver.findElement(By.xpath("//div[2]/span[2]")).click();
        driver.findElement(By.xpath("//span[3]")).click();
        assertEquals("1", driver.findElement(By.id("score1")).getText());
        driver.findElement(By.id("new_game")).click();
        driver.findElement(By.xpath("//span[2]")).click();
        driver.findElement(By.xpath("//div[2]/span")).click();
        driver.findElement(By.xpath("//div[2]/span[3]")).click();
        driver.get(baseUrl);
        assertEquals("0", driver.findElement(By.id("score1")).getText());
        assertEquals(" ", driver.findElement(By.xpath("//span[2]")).getText());
        assertEquals("Spilari X gerir fyrst", driver.findElement(By.id("next_player")).getText());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    private boolean isElementPresent(By by) {
        try {
              driver.findElement(by);
              return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
              driver.switchTo().alert();
              return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
  }

}