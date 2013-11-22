package is.ru.TicTacToe;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@RunWith(BlockJUnit4ClassRunner.class)
public class ChromeTestTitleIT {
    private static ChromeDriverService service;
    private WebDriver driver;
    private String baseUrl;


    @BeforeClass
    public static void createAndStartService() throws IOException {
     service = new ChromeDriverService.Builder()
         .usingDriverExecutable(new File("chromedriver"))
         .usingAnyFreePort()
         .build();
     service.start();
    }

     @AfterClass
     public static void createAndStopService() {
         service.stop();
     }

     @Before
     public void createDriver() {
        baseUrl = "http://tictacwhoop.cloudcontrolapp.com/";
        driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
     }

     @After
     public void quitDriver() {
         driver.quit();
     }

     @Test
     public void testTitle() throws Exception {
      driver.get(baseUrl);
      assertEquals("TicTacToe", driver.getTitle());
    }
}