package main;

import operations.TestReport;
import testcase.CreateNewAccount;
import testcase.CustomerSearch;
import testcase.LoginLogout;
import testcase.MaintainAccountDeposit;
import testcase.Payments;
import testcase.ProvideService;
import testcase.Routing;

public class TestCase 
{
	public static String ReportLocation =  "Reports\\";// = "Reports\\"+TestData.buildNumber+"\\";
	
	public static void PELExistingCustomer()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PELExistingCustomer", ReportLocation);

		ProvideService provideService	= new ProvideService(report, "240004430000");
		provideService.ServiceType		= "PEL";
		provideService.ServicePackage	= "ETFTESTING";
		provideService.execute();
		report.createScreenshotDocument();
	}
	
	public static void PDLExistingCustomer()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PDLExistingCustomer", ReportLocation);

		ProvideService provideService	= new ProvideService(report, "240004430000");
		provideService.ServiceType		= "PDL";
		provideService.ServicePackage	= "ADSL";
		provideService.execute();
		report.createScreenshotDocument();
	}
	
	public static void PELPDLNewCustomer()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PELPDLNewCustomer", ReportLocation);
		
		CreateNewAccount createNewAccount	= new CreateNewAccount(report, "fixedResidential","ETFTESTING");
		createNewAccount.ServiceType		= "PEL";
		createNewAccount.ServicePackage		= "ETFTESTING";
		createNewAccount.execute();

		ProvideService provideService		= new ProvideService(report, createNewAccount.AccountNumber);
		provideService.NewCustomer			= true;
		provideService.PELPDLProvisioning	= true;
		provideService.ServiceType			= createNewAccount.ServiceType;
		provideService.ServicePackage		= createNewAccount.ServicePackage;
		provideService.execute();
		report.createScreenshotDocument();
	}
	
	public static void PCLExistingCustomer()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PCLExistingCustomer", ReportLocation);
		
		ProvideService provideService	= new ProvideService(report, "240004430000");
		provideService.NewCustomer		= false;
		provideService.ServiceType		= "PCL";
		provideService.ServicePackage	= "PCL_Res";
		provideService.execute();
		
		report.createScreenshotDocument();
	}
	
	public static void PCLNewCustomer()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PCLNewCustomer", ReportLocation);
		
		CreateNewAccount createNewAccount	= new CreateNewAccount(report, "fixedResidential","PCL_Res");
		createNewAccount.ServiceType		= "PCL";
		createNewAccount.ServicePackage		= "PCL_Res";
		createNewAccount.execute();

		ProvideService provideService		= new ProvideService(report, createNewAccount.AccountNumber);
		provideService.NewCustomer			= true;
		provideService.ServiceType			= createNewAccount.ServiceType;
		provideService.ServicePackage		= createNewAccount.ServicePackage;
		provideService.execute();
		report.createScreenshotDocument();
	}
	
	public static void PCLNewCustomerwithCreditLimit()
	{
		MainClass.AUTURL		= "http://172.21.73.80:8081/liberate-LONI01-S10/";
		MainClass.Environment	= "S10";
				
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PCLNewCustomerwithCreditLimit", ReportLocation);
		
		CreateNewAccount createNewAccount	= new CreateNewAccount(report, "fixedResidential","PCL_Res");
		createNewAccount.ServiceType		= "PCL";
		createNewAccount.ServicePackage		= "PCL_Res";
		createNewAccount.execute();

		ProvideService provideService		= new ProvideService(report, createNewAccount.AccountNumber);
		provideService.NewCustomer			= true;
		provideService.ServiceType			= createNewAccount.ServiceType;
		provideService.ServicePackage		= createNewAccount.ServicePackage;
		provideService.createCreditLimit	= true;
		provideService.execute();
		report.createScreenshotDocument();
	}

	
	public static void PTVExisting()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PTVExisting", ReportLocation);

		ProvideService provideService		= new ProvideService(report, "240004430000");
		provideService.ServiceType			= "PTV";
		provideService.ServicePackage		= "PTV_RES";
		provideService.execute();
		report.createScreenshotDocument();
	}
	
	public static void PTIExisting()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PTIExisting", ReportLocation);

		ProvideService provideService	= new ProvideService(report, "260000550000");
		provideService.ServiceType		= "PTI";
		provideService.ServicePackage	= "IPTVBasicS";
		provideService.execute();
		report.createScreenshotDocument();
	}
	
	public static void PELAddMorePDL()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PELAddMorePDL", ReportLocation);

		ProvideService providePELService	= new ProvideService(report, "240004430000");
		providePELService.ServiceType		= "PEL";
		providePELService.ServicePackage	= "ETFTESTING";
		providePELService.AddMore			= "PDL";
		providePELService.execute();
		
		ProvideService providePDLService	= new ProvideService(report, "240004430000");
		providePDLService.ServiceType		= "PDL";
		providePDLService.ServicePackage	= "ADSL";
		providePDLService.AddMoreFlow		= true;
		providePDLService.runningPDL		= true;
		providePDLService.execute();
		report.createScreenshotDocument();
	}
	
	public static void PELAddMorePCL()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PELAddMorePCL", ReportLocation);

		ProvideService providePELService	= new ProvideService(report, "240004430000");
		providePELService.ServiceType		= "PEL";
		providePELService.ServicePackage	= "ETFTESTING";
		providePELService.AddMore			= "PCL";
		providePELService.execute();
		
		ProvideService providePDLService	= new ProvideService(report, "240004430000");
		providePDLService.ServiceType		= "PCL";
		providePDLService.ServicePackage	= "PCL_Res";
		providePDLService.AddMoreFlow		= true;
		providePDLService.execute();
		report.createScreenshotDocument();
	}
	
	public static void PCLComverseExistingCustomer()
	{
		String BackUpEnvironment = MainClass.Environment;
		String BackupAUTURL = MainClass.AUTURL;

		
		if(!MainClass.Environment.equals("S06"))
		{						
			MainClass.Environment	= "S06";
			MainClass.AUTURL		= "http://172.21.73.80:8083/liberate-LONI02-S06/"; 
		}
		
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("PCLComverseExistingCustomer", ReportLocation);
		
		ProvideService provideService	= new ProvideService(report, "240004430000");
		provideService.NewCustomer		= false;
		provideService.ServiceType		= "PCL";
		provideService.ServicePackage	= "LIME_PCL_Y";
		provideService.comverseOne		= true;
		provideService.comOneOffer		= "51";
		provideService.execute();
		
		MainClass.Environment	= BackUpEnvironment;
		MainClass.AUTURL		= BackupAUTURL;
		
		report.createScreenshotDocument();
	}
	
	public static void BatchPayment()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report	= new TestReport("BatchPayment", ReportLocation);
		
		Payments payment	= new Payments(report, "Batch");
		payment.execute();
		
		report.createScreenshotDocument();
	}
	
	public static void SinglePayment()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report			= new TestReport("SinglePayment", ReportLocation);
		
		Payments payment			= new Payments(report, "SinglePayment");
		payment.AccountNumber		= "210010230000";
		payment.SinglePaymentType	= "Standard";
		payment.execute();
		
		report.createScreenshotDocument();
	}
	
	public static void VerifyCashdrawer()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report	= new TestReport("VerifyCashdrawer", ReportLocation);
		
		Payments payment	= new Payments(report, "CashDrawer");
		payment.execute();
		
		report.createScreenshotDocument();
	}
	
	public static void CreateDepositReason()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report 				= new TestReport("CreateDepositReason", ReportLocation);
		
		CustomerSearch customerSearch	= new CustomerSearch(report);
		customerSearch.AccountNumber 	= "240004430000";
		customerSearch.execute();
		
		MaintainAccountDeposit depositreason = new MaintainAccountDeposit(report);
		depositreason.execute();
		
		Payments depositpayment			= new Payments(report, "SinglePayment");
		depositpayment.AccountNumber	= customerSearch.AccountNumber;
		depositpayment.SinglePaymentType= "Deposit";
		depositpayment.execute();
		
		report.createScreenshotDocument();
	}
	
	public static void FibreRouting()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report		= new TestReport("FibreRouting", ReportLocation);
		
		Routing routing			= new Routing(report, "Auto");
		routing.ServiceOrder	= "XD00203";
		routing.Department		= "BILDM";
		routing.PlantItemType	= "FDP";
		routing.PlantItemNum	= "FDP20";
		routing.ExchnageID		= "BOT";
		routing.execute();
		
		report.createScreenshotDocument();
	}
	
	public static void testCase()
	{
		LoginLogout.login(MainClass.AUTURL);

		TestReport report = new TestReport("testCase", ReportLocation);
		CreateNewAccount createNewAccount = new CreateNewAccount(report, "fixedResidential","ETFTESTING");
		createNewAccount.ServiceType = "PEL";
		createNewAccount.ServicePackage = "ETFTESTING";
		createNewAccount.execute();
		
		report.createScreenshotDocument();
	}
}
