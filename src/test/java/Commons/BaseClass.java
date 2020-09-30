package Commons;

import Utilities.Config;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseClass
{
    public static WebDriver driver=null;
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test=null;
    public static Logger log = Logger.getLogger("Logger");
    public static Config config;

    static {
        try {
            config = Config.ConfigReturn();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeSuite //creates a template for the report
    public void beginReport() throws UnknownHostException {
        htmlReporter = new ExtentHtmlReporter("Reports//report.html");
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("Functional Report");
        htmlReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("Hostname", InetAddress.getLocalHost().getHostName());
        extent.setSystemInfo("OS", "Windows10");
        extent.setSystemInfo("Tester", "Jithin");
        extent.setSystemInfo("Browser", "Chrome");
    }

    @Parameters("browser")
    @BeforeMethod
    public static WebDriver openBrowser(String browser) throws Exception {
        String url = config.get("URL");

        if (browser.equalsIgnoreCase("firefox"))
        {
            System.setProperty("webdriver.gecko.driver", "Drivers//geckodriver.exe");
            driver = new FirefoxDriver();
            test.log(Status.INFO, "Chrome browser opened");
            log.debug("Chrome browser opened");
        }
        else if (browser.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", "Drivers//chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("IE"))
        {
            System.setProperty("wedriver.ie.driver", "Drivers//IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        else if (browser.equalsIgnoreCase("edge"))
        {
            System.setProperty("webdriver.edge.driver", "Drivers//msedgedriver.exe");
            driver = new EdgeDriver();
        }
        else
        {
            throw new Exception("Browser is not correct");
        }

        driver.manage().window().maximize();
        driver.navigate().to(url);
        log.debug("Opening the Website");
        driver.manage().timeouts().implicitlyWait(20000, TimeUnit.MILLISECONDS);
        return driver;

    }

    // screenshot method

    public static String takeScreenshot(WebDriver driver, String name) throws IOException
    {
        TakesScreenshot screenshot = (TakesScreenshot)driver;
        File image = screenshot.getScreenshotAs(OutputType.FILE);
        Random random = new Random();
        String file = "image"+random.nextInt(1000)+".png";
        String filename = System.getProperty("user.dir")+"\\Reports\\" +file;
        FileUtils.copyFile(image, new File(filename));
        return file;
    }

    // scroll method

    public static void scroll(String value)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(value);// value = window.scrollBy(0,xx)
    }


    @AfterMethod
    public void closeBrowser()
    {
        driver.quit();
    }

    @AfterSuite
    public void endReport()
    {
        extent.flush();
    }
}
