package testcase;

import operations.Operations;
import operations.TestAction;
import operations.TestReport;

public class SalesSignoff 
{
	static String classversion = "SalesSignoff Class 0.0.3 : Added - Screenshot";

	TestAction ta = new TestAction(Operations.getdriver());
	TestReport report;
	
	String xpath;
	boolean passed;
	
	String AccountNumber;
	String ServiceNumber;
	String ServiceOrder;
	
	public SalesSignoff(TestReport report)
	{
		this.report = report;
	}
	
	public boolean signoff()
	{		
		xpath = "//*[text()='Service Order Lines']";
		passed = ta.waitUntil(xpath, 10);
		
		report.takeScreenshot();
		ta.closeOKpopup();
		
		xpath = "(//*[text()[contains(.,'Account Number:')]]/./following::*)[1]";
		this.AccountNumber = ta.getDatafromPage(xpath);
		
		xpath = "(//*[text()[contains(.,'Service No:')]]/./following::*)[1]";
		this.ServiceNumber = ta.getDatafromPage(xpath);
		
		xpath = "(//*[text()[contains(.,'Service Order No:')]]/./following::*)[1]";
		this.ServiceOrder = ta.getDatafromPage(xpath);
		
		//Check for Appointment Detail
		xpath = "(//*[text()[contains(.,'Municipality Code:')]]/./following::select[@class='iceSelOneMnu MandatoryListBox'])[1]";
		if(ta.elementExist(xpath))
		{
			passed = ta.selectBy(xpath, 1);
			ta.waitFor(1000);
		}
		
		xpath = "//input[@value='Confirm Payment Amounts' and @disabled='disabled']";
		if(!ta.elementExist(xpath))
		{
			//TODO Confirm Payments
			xpath = "(//input[@type='checkbox' and @checked='checked'])";
			for(int i = 1; i<5; i++)
			{
				if(ta.elementExist(xpath+"["+i+"]"))
				{
					ta.clickOn(xpath+"["+i+"]");
				}
				else
				{
					break;
				}
			}
			xpath = "//input[@value='Confirm Payment Amounts']";
			passed = ta.clickOn(xpath);
			ta.waitFor(1000);
			ta.closeOKpopup();				
		}
		
		//TODO Handle if service charge needs to be added.
		
		xpath = "//input[@value='Submit Order']";
		passed = ta.clickOn(xpath);
		report.takeScreenshot();
		
		xpath = "//input[@value='OK']";
		passed = ta.waitUntil(xpath, 5);
		
		report.takeScreenshot();
		
		passed = ta.clickOn(xpath);		
		passed = ta.waitUntilElementnotExist(xpath, 5);

		return passed;
	}
	
	public boolean addMore(String serviceType)
	{
		xpath = "//*[text()='Service Order Lines']";
		passed = ta.waitUntil(xpath, 10);
		
		report.takeScreenshot();
		
		ta.closeOKpopup();
		
		xpath = "(//*[text()[contains(.,'Account Number:')]]/./following::*)[1]";
		this.AccountNumber = ta.getDatafromPage(xpath);
		
		xpath = "(//*[text()[contains(.,'Service No:')]]/./following::*)[1]";
		this.ServiceNumber = ta.getDatafromPage(xpath);
		
		xpath = "(//*[text()[contains(.,'Service Order No:')]]/./following::*)[1]";
		this.ServiceOrder = ta.getDatafromPage(xpath);
		
		//Check for Appointment Detail
		xpath = "(//*[text()[contains(.,'Municipality Code:')]]/./following::select[@class='iceSelOneMnu MandatoryListBox'])[1]";
		if(ta.elementExist(xpath))
		{
			passed = ta.selectBy(xpath, 1);
			ta.waitFor(1000);
		}
		
		//TODO Handle if service charge needs to be added.
		
		xpath = "//input[@value='Add More']";
		passed = ta.clickOn(xpath);
		report.takeScreenshot();
		
		xpath = "//*[text()='Change Service']";
		passed = ta.waitUntil(xpath, 5);
		
		if(serviceType.equals("PDL"))
		{
			xpath = "//*[text()='ADSL']";
			if(!ta.elementExist(xpath))
			{
				xpath = "(//*[text()[contains(.,'PEL')]])[1]";
				ta.clickOn(xpath);
			}
			xpath = "//*[text()='ADSL']";
			passed = ta.waitUntil(xpath, 2);
			passed = ta.clickOn(xpath);
		}
		else
		{
			xpath = "//*[text()='Service Package']";
			passed = ta.waitUntil(xpath, 2);
			passed = ta.clickOn(xpath);
		}
		return passed;
	}
}
