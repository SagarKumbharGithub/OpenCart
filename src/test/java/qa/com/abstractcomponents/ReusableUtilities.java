package qa.com.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import qa.com.reports.ExtentListener;
import qa.com.reports.ExtentLogger;

public class ReusableUtilities {

	WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	
	

	

	

	

	
	
	/**
	 * 
	 * @param driver ReuableUtilities constructor created
	 */
	public ReusableUtilities(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	



	

	/**
	 * 
	 * @param fieldName : Enter the fieldName/element name where to click
	 * @param element   : WebElement where to click action required
	 */
	public void click_custom(String fieldName, WebElement element) {
		try {
			waitForElementToBeClickable(element);
			element.click();
			ExtentLogger.pass("Clicked on " + fieldName);

		} catch (Exception e) {
			e.printStackTrace();
			ExtentLogger.fail("Not able to click on the "+fieldName);
			Assert.fail("Not able to click on the "+fieldName);
		}
	}
  /**
   * 
   * @param fieldName :Enter the field name where value to be sent
   * @param element  :WebElement where to sendKey action required
   * @param valueToBeSent :Enter the value which is requied to sent in field
   */
	public void sendkey_custom(String fieldName, WebElement element,String valueToBeSent) {
		try {
			waitForElementToBeClickable(element);
			element.sendKeys(valueToBeSent);
			ExtentLogger.pass(valueToBeSent+" : value entered in the "+fieldName +" field");

		} catch (Exception e) {
			e.printStackTrace();
			ExtentLogger.fail(valueToBeSent+" : not able to enter value in the "+fieldName +" field");
			Assert.fail(valueToBeSent+" : not able to enter value in the "+fieldName +" field");
		}
	}
	
	public String getText_custom(WebElement element)
	{
	
			waitForElementToBeVisible(element);
		    String text= element.getText().trim();			
			return text;
			
	}
	public String getFieldPlaceholderValue(String fieldName, WebElement element)
	{
		waitForElementToBeVisible(element);
		String placeholderValue=element.getAttribute("placeholder").trim();
		return placeholderValue;
	}
	public boolean verifyPlacehoderValue(String fieldName, WebElement element,String expectedPlacehoderValue )
	{
		boolean placeholderValue=false;
		
		String value=getFieldPlaceholderValue(fieldName, element);
		if(value.equals(expectedPlacehoderValue))
		{
			ExtentLogger.pass("Placehoder value displyed for the "+fieldName+ " is : "+value);
			placeholderValue=true;
		}
		else
		{
			ExtentLogger.fail("Incorrect placehoder value displyed for the "+fieldName+ " is : "+value);
		    placeholderValue=false;
		}
		return placeholderValue;
		
	}
	public void scrollIntoViewElement(WebElement element ) 
	{
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	/**
	 * 
	 * @param element : Wait until the element to be in clickable mode
	 */
	public void waitForElementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * 
	 * @param element : Wait until the element to be in visible mode
	 */
	public void waitForElementToBeVisible(WebElement element) {
	
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * 
	 * @param element : Wait until the element to be present on the screen
	 */
	public void waitForPresenceOfElement(By by) {
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
}
