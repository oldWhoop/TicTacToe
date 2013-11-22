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

public class WebDriverTest {

    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        // Choose the browser, version, and platform to test
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("version", "17");
        capabilities.setCapability("platform", Platform.XP);
        // Create the connection to Sauce Labs to run the tests
        this.driver = new RemoteWebDriver(
                new URL("http://dextroc:49c6e8b6-acf6-4024-a3ff-f766fa7902e4@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
        baseUrl = "http://tictacwhoop.cloudcontrolapp.com/";
    }

    @Test
    public void checkTitle() throws Exception {
        // Make the browser get the page and check its title
        driver.get(baseUrl);
        assertEquals("TicTacToe", driver.getTitle());
    }

    @Test
    public void testPlayer1Win() throws Exception {
        driver.get(baseUrl);
        driver.findElement(By.xpath("//span[3]")).click();
        driver.findElement(By.xpath("//span[2]")).click();
        driver.findElement(By.xpath("//div[2]/span[2]")).click();
        driver.findElement(By.xpath("//div/span")).click();
        driver.findElement(By.xpath("//div[3]/span")).click();
        assertEquals("Spilari 1 vann!!!", driver.findElement(By.id("results")).getText());
    }
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}