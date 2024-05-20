package qa.com.testComponents;

import java.io.FileInputStream;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;

import io.qameta.allure.Step;
import qa.com.reports.ExtentLogger;

public class BaseClass {

	public WebDriver driver;
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

	public void initiate_driver(String browserName) throws IOException {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");
		Properties prop = new Properties();
		prop.load(fis);
		//String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
		String url = prop.getProperty("url");

		if (browserName.contains("chrome")) {
			setUpChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("firefox")) {
			setUpFirefoxxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge")) {
			setUpEdgeDriver();
		}

		tdriver=open_url(url);
		ExtentLogger.pass("Navigate to the url: " +url);
	}
	
	public synchronized static WebDriver getDriver()
	{
		return tdriver.get();
	}
	
	public ThreadLocal<WebDriver> open_url(String url)
	{
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		tdriver.set(driver);
		tdriver.get();
		return tdriver;
	}

	@Step("Chrome Browser Opened")
	public void setUpChromeDriver() {
		driver = new ChromeDriver();
		ExtentLogger.pass("Chrome browser launched");
	}

	@Step("FireFox Browser Opened")
	public void setUpFirefoxxDriver() {
		driver = new FirefoxDriver();
		ExtentLogger.pass("FireFox browser launched");

	}

	@Step("Edge Browser Opened")
	public void setUpEdgeDriver() {
		driver = new EdgeDriver();
		ExtentLogger.pass("Edge browser launched");

	}

	public void setUpRemoteDriver(String browserName) throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setBrowserName(browserName);
		driver = new RemoteWebDriver(new URL("http://localhost:4444/"), caps);
	}
	
	@AfterMethod
	public void  tearDown()
	{
		getDriver().quit();
		ExtentLogger.info("Browser closed");

	}

}
