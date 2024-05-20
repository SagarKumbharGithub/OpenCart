 package qa.com.reports;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {
	
	public ExtentManager() {}
	
	public static ThreadLocal<ExtentTest> extTest= new ThreadLocal<ExtentTest>();
	
	 static ExtentTest getExtentTest()
	{
		return extTest.get();
	}

	 static void setExtentTest(ExtentTest test)
	{
		extTest.set(test);
	}
	
	 static void unload()
	{
		extTest.remove();
	}
}
