package qa.com.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.com.abstractcomponents.ReusableUtilities;
import qa.com.reports.ExtentLogger;

public class NewsLetterPage extends ReusableUtilities {
	
	WebDriver driver;	
	
	@FindBy (css="#input-newsletter")
	private WebElement newsletterCheckbox;
	
	
	
	public NewsLetterPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}


	public boolean verifyNewsLetterCheckboxSelectedBydefault()
	{
		waitForElementToBeVisible(newsletterCheckbox);		
		boolean flag=newsletterCheckbox.isSelected();
		if(flag==true)
		{
			ExtentLogger.info("Newsletter checkbox is selected by default");
		}
		else
		{
			ExtentLogger.info("Newsletter checkbox is not selected even Yes options selected at the time of registration");
		}
		return flag;

		
	}
}
