import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class LanguageListCollectionExecute {

	private static String ANDROID_PHOTO_PATH = "/mnt/sdcard/Android/data/com.sourcenext.pocketalks/files";
	private static File classpathRoot = new File(System.getProperty("user.dir"));
	private  ArrayList<String> backButtons = new ArrayList<String>();
	private static String errorImagePath = System.getProperty("user.dir")+"\\Screenshots\\";
	public ArrayList<String> header = new ArrayList<String>();
	public ArrayList<String> caseID = new ArrayList<String>();
	public ArrayList<String> fromLanguage = new ArrayList<String>();
	public ArrayList<String> toLanguage = new ArrayList<String>();
	public ArrayList<String> imageName = new ArrayList<String>();
	public ArrayList<String> ttsSupported = new ArrayList<String>();
	public ArrayList<String> trResults = new ArrayList<String>();
	public ArrayList<String> playButtonStatus = new ArrayList<String>();
	public static String packageName = "com.sourcenext.pocketalk";
	static AndroidDriver<MobileElement> driver;

	public ArrayList<String> LanguageListHeader = new ArrayList<String>();
	public ArrayList<String> expectedLanInTraditional_Chinese = new ArrayList<String>();
	public ArrayList<String> expectedLanIn_Russian = new ArrayList<String>();
	public ArrayList<String> expectedLanIn_Malay = new ArrayList<String>();
	public ArrayList<String> expectedLanIn_Thai = new ArrayList<String>();
	public ArrayList<String> expectedLanIn_Portuguese = new ArrayList<String>();
	public static ArrayList<String> ActualhomeTopLanguageChinese = new ArrayList<String>();
	public static ArrayList<String> ActualhomeTopLanguageRussian = new ArrayList<String>();
	public static ArrayList<String> ActualhomeTopLanguageMalay = new ArrayList<String>();
	public static ArrayList<String> ActualhomeTopLanguageThai = new ArrayList<String>();
	public static ArrayList<String> ActualhomeTopLanguagePortuguese = new ArrayList<String>();

	public ArrayList<String> LanguageListHeader_Camera = new ArrayList<String>();
	public ArrayList<String> expectedLanInTraditional_Chinese_Camera = new ArrayList<String>();
	public ArrayList<String> expectedLanIn_Russian_Camera = new ArrayList<String>();
	public ArrayList<String> expectedLanIn_Malay_Camera = new ArrayList<String>();
	public ArrayList<String> expectedLanIn_Thai_Camera = new ArrayList<String>();
	public ArrayList<String> expectedLanIn_Portuguese_Camera = new ArrayList<String>();
	public static ArrayList<String> ActualCamera_FromLanguage_Chinese = new ArrayList<String>();
	public static ArrayList<String> ActualCamera_FromLanguage_Russian = new ArrayList<String>();
	public static ArrayList<String> ActualCamera_FromLanguage_Malay = new ArrayList<String>();
	public static ArrayList<String> ActualCamera_FromLanguage_Thai = new ArrayList<String>();
	public static ArrayList<String> ActualCamera_FromLanguage_Portuguese = new ArrayList<String>();

	public static ArrayList<String> countryName_Chinese = new ArrayList<String>();
	public static ArrayList<String> countryName_Russian = new ArrayList<String>();
	public static ArrayList<String> countryName_Malay = new ArrayList<String>();
	public static ArrayList<String> countryName_Thai = new ArrayList<String>();
	public static ArrayList<String> countryName_Portuguese = new ArrayList<String>();
	public static ArrayList<ModelSubCountryList> countryLanguages_English = new ArrayList<ModelSubCountryList>();
	@BeforeTest

	public void setup() throws InterruptedException, IOException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android"); 
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"8.1");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"0123456789ABCDEF");
		capabilities.setCapability("noReset","true"); 
		capabilities.setCapability("fullReset","false");
		capabilities.setCapability("appPackage", packageName);
		capabilities.setCapability("appActivity",packageName+".activity.MainActivity"); 

		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Thread.sleep(5000);

		ArrayList<String> systemLanguageList = new ArrayList<String>();
		systemLanguageList.add("Use in English");
		systemLanguageList.add("日本語で操作する");
		systemLanguageList.add("使用简体中文操作");
		systemLanguageList.add("使用繁體中文操作");
		systemLanguageList.add("Uso en español");
		systemLanguageList.add("Operar em português");
		systemLanguageList.add("Работать на русском");
		systemLanguageList.add("Utilisation en français");
		systemLanguageList.add("Verwendung in deutsch");
		systemLanguageList.add("한국어로 사용하다");
		systemLanguageList.add("Utilizzare in italia");
		systemLanguageList.add("ใช้งานด้วยภาษาไทย");
		systemLanguageList.add("Operasi Dalam Bahasa Melayu");
		
		String filePath = System.getProperty("user.dir");
		//       this.readExcel(filePath, "languages_backup.xlsx","Sheet1");
//		       this.readHomeLanguageList(filePath, "conversation_languages.xlsx", "Sheet2");
		//       this.readCameraLanguageList(filePath, "Camera_conversation_languages.xlsx", "Sheet2");
		//       this.readCountryName(filePath, "countryName_withOrder_Implemented.xlsx", "Sheet1");

		backButtons.add("btn_navigation_menu_left");
		backButtons.add("btn_navigation_menu");
		backButtons.add("ib_back");
		backButtons.add("iv_top_back");
		backButtons.add("ib_top_back");
		backButtons.add("discard");
		backButtons.add("close_dialog");
		backButtons.add("imagebutton_top_back");
		backButtons.add("btn_ok");
		backButtons.add("//android.widget.LinearLayout/android.widget.Button");
		backButtons.add("android:id/button1");
	}

	public void readExcel(String filePath,String fileName,String sheetName) throws IOException{
		File file =    new File(filePath+"\\assets\\"+fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook guru99Workbook = null;
		XSSFSheet XLSX = null;
		HSSFSheet XLS = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			guru99Workbook = new XSSFWorkbook(inputStream);
			XLSX = (XSSFSheet) guru99Workbook.getSheet(sheetName);
			int rowCount = XLSX.getLastRowNum()-XLSX.getFirstRowNum();
			System.out.println(XLSX.getLastRowNum()+"========"+XLSX.getFirstRowNum());

			Iterator<Row> rowIterator = XLSX.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell=cellIterator.next();
					if (row.getRowNum()==0) {
						header.add(cell.getStringCellValue());
					}else {
						if (cell.getColumnIndex()==0) {
							caseID.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==1) {
							fromLanguage.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==2) {
							toLanguage.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==3) {
							imageName.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==4) {
							ttsSupported.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==5) {
							trResults.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==6) {
							playButtonStatus.add(cell.getStringCellValue());
						}
					}
				}
			}
		}
	}

	public void tearDown() {
		driver.quit();
	}
	// Camera Language List Start below

	@Test(priority=1)
	public void Chinese_Camera_Language_List() throws Exception{
		System.out.println("TestCase 1 :: Chine_Camera_Language_List Started !!");
		Thread.sleep(1000);
		driver.findElementById("btn_navigation_menu_left").click();
		Thread.sleep(2000);
		driver.findElementsById("layout_main_iv_tv").get(0).click();
		Thread.sleep(1000);
		selectSystemLanguageOption("System Language","使用繁體中文操作");
		Thread.sleep(6000);
		driver.findElementById("btn_navigation_menu_left").click();
		Thread.sleep(1000);
		driver.findElementsById("layout_main_iv_tv").get(1).click();
		Thread.sleep(2000);
//		driver.findElementById("tvLanguageTranslate").click(); // TO Language
		driver.findElementById("tvLanguageShooting").click(); //From Language
		Thread.sleep(2000);
//		String lastLanguageName ="祖魯文"; // TO Language
		String lastLanguageName ="白俄羅斯文"; // From Language
		ArrayList<String> LanguageList = new ArrayList<>();
		String languageName = "";
		String lastMenuFound = "";
		int tbsPosition= driver.findElementById("tabs").getLocation().getY();
		int tbsHeight=driver.findElementById("viewpager").getLocation().getY();
		int target=tbsPosition+tbsHeight;
		System.out.println(target);
		int firsttime=1;
		int checkValue=0;
		Point last = null;
		while (lastMenuFound.isEmpty()) {
			List<MobileElement> allChats = driver.findElementsById("parentLayout");
			int count=allChats.size();
			for (int i = 0; i < count; i++) {
				if (firsttime==1) {
					if (i==0) {
						checkValue=allChats.get(i).getSize().getHeight();
					}
				}else {
					//            	System.out.println("running second time");
				}
				int itemsY=allChats.get(i).getSize().getHeight();

				if (checkValue==itemsY) {
					languageName=allChats.get(i).findElementById("lan_name").getText();
					if (LanguageList.contains(languageName)) {
						//							System.out.println(languageName+" exists");
					}else {
						LanguageList.add(languageName);
						System.out.println(languageName);
					}
					last = allChats.get(i).getLocation();
					if (languageName.toLowerCase().contains(lastLanguageName.toLowerCase())) {
						lastMenuFound = "Yes";
						System.out.println("Last Menu Found");
						break;
					}else {
						//                    	System.out.println("Last Menu NOT Found");
					}
				}else {
					//						System.out.println("Size not matched");
				}
				Thread.sleep(1000);

			}

			new TouchAction(driver).press(PointOption.point(last)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(last.getX(),target+50)).release().perform();
			Thread.sleep(1000); 
			firsttime=0;
		}
		System.out.println("Total Language : "+LanguageList.size());

		for (int k = 0; k < LanguageList.size(); k++) {
			System.out.println(LanguageList.get(k));
			ActualCamera_FromLanguage_Chinese.add(LanguageList.get(k));
		}
		System.out.println("==============================");
		driver.findElementById("text_back_okay").click();
		Thread.sleep(1000);
		driver.findElementById("iv_top_back").click();
	}

//	@Test(priority=2)
//	public void Russian_Camera_Language_List() throws Exception{
//		System.out.println("TestCase 2 :: Russian_Camera_Language_List Started !!");
//		Thread.sleep(1000);
//		driver.findElementById("btn_navigation_menu_left").click();
//		Thread.sleep(2000);
//		driver.findElementsById("layout_main_iv_tv").get(0).click();
//		Thread.sleep(1000);
//		selectSystemLanguageOption("System Language","Работать на русском");
//		Thread.sleep(6000);
//		driver.findElementById("btn_navigation_menu_left").click();
//		Thread.sleep(1000);
//		driver.findElementsById("layout_main_iv_tv").get(1).click();
//		Thread.sleep(2000);
////		driver.findElementById("tvLanguageTranslate").click(); // TO Language
//		driver.findElementById("tvLanguageShooting").click(); //From Language
//		Thread.sleep(2000);
////		String lastLanguageName ="Зулу"; // TO Language
//		String lastLanguageName ="Белорусский"; // FROM Language
//		ArrayList<String> LanguageList = new ArrayList<>();
//		String languageName = "";
//		String lastMenuFound = "";
//		int tbsPosition= driver.findElementById("tabs").getLocation().getY();
//		int tbsHeight=driver.findElementById("viewpager").getLocation().getY();
//		int target=tbsPosition+tbsHeight;
//		System.out.println(target);
//
//		int firsttime=1;
//		int checkValue=0;
//		Point last = null;
//		while (lastMenuFound.isEmpty()) {
//			List<MobileElement> allChats = driver.findElementsById("parentLayout");
//			int count=allChats.size();
//			for (int i = 0; i < count; i++) {
//				if (firsttime==1) {
//					if (i==0) {
//						checkValue=allChats.get(i).getSize().getHeight();
//					}
//				}else {
//					//	            	System.out.println("running second time");
//				}
//				int itemsY=allChats.get(i).getSize().getHeight();
//
//				if (checkValue==itemsY) {
//					languageName=allChats.get(i).findElementById("lan_name").getText();
//					if (LanguageList.contains(languageName)) {
//						//								System.out.println(languageName+" exists");
//					}else {
//						LanguageList.add(languageName);
//						System.out.println(languageName);
//					}
//					last = allChats.get(i).getLocation();
//					if (languageName.toLowerCase().contains(lastLanguageName.toLowerCase())) {
//						lastMenuFound = "Yes";
//						System.out.println("Last Menu Found");
//						break;
//					}else {
//						//	                    	System.out.println("Last Menu NOT Found");
//					}
//				}else {
//					//							System.out.println("Size not matched");
//				}
//				Thread.sleep(1000);
//
//			}
//
//			new TouchAction(driver).press(PointOption.point(last)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(last.getX(),target+checkValue)).release().perform();
//			Thread.sleep(1000); 
//			firsttime=0;
//		}
//		System.out.println("Total Language :"+LanguageList.size());
//
//		for (int k = 0; k < LanguageList.size(); k++) {
//			System.out.println(LanguageList.get(k));
//			ActualCamera_FromLanguage_Russian.add(LanguageList.get(k));
//		}
//		System.out.println("==============================");
//		
//		driver.findElementById("text_back_okay").click();
//		Thread.sleep(1000);
//		driver.findElementById("iv_top_back").click();
//	}
//	@Test(priority=3)
//	public void Malay_Camera_Language_List() throws Exception{
//		System.out.println("TestCase 3 :: Malay_Camera_Language_List Started !!");
//		Thread.sleep(1000);
//		driver.findElementById("btn_navigation_menu_left").click();
//		Thread.sleep(2000);
//		driver.findElementsById("layout_main_iv_tv").get(0).click();
//		Thread.sleep(1000);
//		selectSystemLanguageOption("System Language","Operasi Dalam Bahasa Melayu");
//		Thread.sleep(6000);
//		driver.findElementById("btn_navigation_menu_left").click();
//		Thread.sleep(1000);
//		driver.findElementsById("layout_main_iv_tv").get(1).click();
//		Thread.sleep(2000);
////		driver.findElementById("tvLanguageTranslate").click(); // TO Language
//		driver.findElementById("tvLanguageShooting").click(); //From Language
//		Thread.sleep(2000);
////		String lastLanguageName ="Zulu"; // TO Language
//		String lastLanguageName ="Belarus"; // From Language
//		ArrayList<String> LanguageList = new ArrayList<>();
//		String languageName = "";
//		String lastMenuFound = "";
//		int tbsPosition= driver.findElementById("tabs").getLocation().getY();
//		int tbsHeight=driver.findElementById("viewpager").getLocation().getY();
//		int target=tbsPosition+tbsHeight;
//		System.out.println(target);
//
//		int firsttime=1;
//		int checkValue=0;
//		Point last = null;
//		while (lastMenuFound.isEmpty()) {
//			List<MobileElement> allChats = driver.findElementsById("parentLayout");
//			int count=allChats.size();
//			for (int i = 0; i < count; i++) {
//				if (firsttime==1) {
//					if (i==0) {
//						checkValue=allChats.get(i).getSize().getHeight();
//					}
//				}else {
//					//	            	System.out.println("running second time");
//				}
//				int itemsY=allChats.get(i).getSize().getHeight();
//
//				if (checkValue==itemsY) {
//					languageName=allChats.get(i).findElementById("lan_name").getText();
//					if (LanguageList.contains(languageName)) {
//						//								System.out.println(languageName+" exists");
//					}else {
//						LanguageList.add(languageName);
//						System.out.println(languageName);
//					}
//					last = allChats.get(i).getLocation();
//					if (languageName.toLowerCase().contains(lastLanguageName.toLowerCase())) {
//						lastMenuFound = "Yes";
//						System.out.println("Last Menu Found");
//						break;
//					}else {
//						//	                    	System.out.println("Last Menu NOT Found");
//					}
//				}else {
//					//							System.out.println("Size not matched");
//				}
//				Thread.sleep(1000);
//
//			}
//
//			new TouchAction(driver).press(PointOption.point(last)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(last.getX(),target+checkValue)).release().perform();
//			Thread.sleep(1000); 
//			firsttime=0;
//		}
//		System.out.println("Total Language :"+LanguageList.size());
//
//		for (int k = 0; k < LanguageList.size(); k++) {
//			System.out.println(LanguageList.get(k));
//			ActualCamera_FromLanguage_Malay.add(LanguageList.get(k));
//		}
//		System.out.println("========================");
//		driver.findElementById("text_back_okay").click();
//		Thread.sleep(1000);
//		driver.findElementById("iv_top_back").click();
//	}

//	@Test(priority=4)
//	public void Thai_Camera_Language_List() throws Exception{
//		System.out.println("TestCase 4 :: Thai_Camera_Language_List Started !!");
//		Thread.sleep(1000);
//		driver.findElementById("btn_navigation_menu_left").click();
//		Thread.sleep(2000);
//		driver.findElementsById("layout_main_iv_tv").get(0).click();
//		Thread.sleep(1000);
//		selectSystemLanguageOption("System Language","ใช้งานด้วยภาษาไทย");
//		Thread.sleep(6000);
//		driver.findElementById("btn_navigation_menu_left").click();
//		Thread.sleep(1000);
//		driver.findElementsById("layout_main_iv_tv").get(1).click();
//		Thread.sleep(2000);
////		driver.findElementById("tvLanguageTranslate").click(); // TO Language
//		driver.findElementById("tvLanguageShooting").click(); //From Language
//		Thread.sleep(2000);
////		String lastLanguageName ="ภาษาซูลู"; // TO Language
//		String lastLanguageName ="ภาษาเบลารุส"; // FROM Language
//		ArrayList<String> LanguageList = new ArrayList<>();
//		String languageName = "";
//		String lastMenuFound = "";
//		int tbsPosition= driver.findElementById("tabs").getLocation().getY();
//		int tbsHeight=driver.findElementById("viewpager").getLocation().getY();
//		int target=tbsPosition+tbsHeight;
//		System.out.println(target);
//
//		int firsttime=1;
//		int checkValue=0;
//		Point last = null;
//		while (lastMenuFound.isEmpty()) {
//			List<MobileElement> allChats = driver.findElementsById("parentLayout");
//			int count=allChats.size();
//			for (int i = 0; i < count; i++) {
//				if (firsttime==1) {
//					if (i==0) {
//						checkValue=allChats.get(i).getSize().getHeight();
//					}
//				}else {
//					//	            	System.out.println("running second time");
//				}
//				int itemsY=allChats.get(i).getSize().getHeight();
//
//				if (checkValue==itemsY) {
//					languageName=allChats.get(i).findElementById("lan_name").getText();
//					if (LanguageList.contains(languageName)) {
//						//								System.out.println(languageName+" exists");
//					}else {
//						LanguageList.add(languageName);
//						System.out.println(languageName);
//					}
//					last = allChats.get(i).getLocation();
//					if (languageName.toLowerCase().contains(lastLanguageName.toLowerCase())) {
//						lastMenuFound = "Yes";
//						System.out.println("Last Menu Found");
//						break;
//					}else {
//						//	                    	System.out.println("Last Menu NOT Found");
//					}
//				}else {
//					//							System.out.println("Size not matched");
//				}
//				Thread.sleep(1000);
//
//			}
//
//			new TouchAction(driver).press(PointOption.point(last)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(last.getX(),target+50)).release().perform();
//			Thread.sleep(1000); 
//			firsttime=0;
//		}
//		System.out.println("Total Language :"+LanguageList.size());
//
//		for (int k = 0; k < LanguageList.size(); k++) {
//			System.out.println(LanguageList.get(k));
//			ActualCamera_FromLanguage_Thai.add(LanguageList.get(k));
//		}
//		System.out.println("========================");
//		driver.findElementById("text_back_okay").click();
//		Thread.sleep(1000);
//		driver.findElementById("iv_top_back").click();
//	}
//	@Test(priority=5)
//	public void Portuguese_Camera_Language_List() throws Exception{
//		System.out.println("TestCase 5 :: Portuguese_Camera_Language_List Started !!");
//		Thread.sleep(1000);
//		driver.findElementById("btn_navigation_menu_left").click();
//		Thread.sleep(2000);
//		driver.findElementsById("layout_main_iv_tv").get(0).click();
//		Thread.sleep(1000);
//		selectSystemLanguageOption("System Language","Operar em português");
//		Thread.sleep(6000);
//		driver.findElementById("btn_navigation_menu_left").click();
//		Thread.sleep(1000);
//		driver.findElementsById("layout_main_iv_tv").get(1).click();
//		Thread.sleep(2000);
////		driver.findElementById("tvLanguageTranslate").click(); // TO Language
//		driver.findElementById("tvLanguageShooting").click(); //From Language
//		Thread.sleep(2000);
////		String lastLanguageName ="Zulu"; // TO Language
//		String lastLanguageName ="Bielorrusso"; // FROM Language
//		ArrayList<String> LanguageList = new ArrayList<>();
//		String languageName = "";
//		String lastMenuFound = "";
//		int tbsPosition= driver.findElementById("tabs").getLocation().getY();
//		int tbsHeight=driver.findElementById("viewpager").getLocation().getY();
//		int target=tbsPosition+tbsHeight;
//		System.out.println(target);
//
//		int firsttime=1;
//		int checkValue=0;
//		Point last = null;
//		while (lastMenuFound.isEmpty()) {
//			List<MobileElement> allChats = driver.findElementsById("parentLayout");
//			int count=allChats.size();
//			for (int i = 0; i < count; i++) {
//				if (firsttime==1) {
//					if (i==0) {
//						checkValue=allChats.get(i).getSize().getHeight();
//					}
//				}else {
//					//	            	System.out.println("running second time");
//				}
//				int itemsY=allChats.get(i).getSize().getHeight();
//
//				if (checkValue==itemsY) {
//					languageName=allChats.get(i).findElementById("lan_name").getText();
//					if (LanguageList.contains(languageName)) {
//						//								System.out.println(languageName+" exists");
//					}else {
//						LanguageList.add(languageName);
//						System.out.println(languageName);
//					}
//					last = allChats.get(i).getLocation();
//					if (languageName.toLowerCase().contains(lastLanguageName.toLowerCase())) {
//						lastMenuFound = "Yes";
//						System.out.println("Last Menu Found");
//						break;
//					}else {
//						//	                    	System.out.println("Last Menu NOT Found");
//					}
//				}else {
//					//							System.out.println("Size not matched");
//				}
//				Thread.sleep(1000);
//
//			}
//
//			new TouchAction(driver).press(PointOption.point(last)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(last.getX(),target+40)).release().perform();
//			Thread.sleep(1000); 
//			firsttime=0;
//		}
//		System.out.println("Total Language :"+LanguageList.size());
//
//		for (int k = 0; k < LanguageList.size(); k++) {
//			System.out.println(LanguageList.get(k));
//			ActualCamera_FromLanguage_Portuguese.add(LanguageList.get(k));
//		}
//		System.out.println("===============================");
//		
//		driver.findElementById("text_back_okay").click();
//		Thread.sleep(1000);
//		driver.findElementById("iv_top_back").click();
//		String filePath = System.getProperty("user.dir");
//		writeCameraLanguageList(filePath, "CameraTOLanguageList.xlsx", "Sheet1", ActualCamera_FromLanguage_Portuguese);
//	}

	// Voice Translation Language List Start below

	@Test(priority=6)
	public void Chinese_Home_Top_Language_List() throws Exception{
		System.out.println("TestCase 1 :: Chinese_Home_Top_Language_List Started");
		Thread.sleep(1000);
		try {
			driver.findElementById("btn_ok").click();
		} catch (Exception e) {
			System.out.println("first alert not appear...");
		}

		driver.findElementById("btn_navigation_menu").click();
		
//		driver.findElementById("btn_navigation_menu_left").click();
//		Thread.sleep(2000);
//		driver.findElementsById("layout_main_iv_tv").get(0).click();
//		Thread.sleep(1000);
		selectSystemLanguageOption("System Language","日本語で操作する");
		Thread.sleep(6000);
//		driver.findElementById("layout_gradient_bottom").click();
		driver.findElementById("btn_native").click();
		Thread.sleep(2000);
//		String lastLanguageName ="越南文"; // china Mode
		String lastLanguageName ="祖魯文"; // Normal Mode
		ArrayList<String> LanguageList = new ArrayList<>();
		String languageName = "";
		String lastMenuFound = "";

		int tbsPosition= driver.findElementById("tabs").getLocation().getY();
		System.out.println(tbsPosition);
		int tbsHeight=driver.findElementById("viewpager").getLocation().getY();
		System.out.println(tbsHeight);
		int target=tbsPosition+tbsHeight;
		System.out.println(target);
		int firsttime=1;
		int checkValue=0;
		Point last = null;
		while (lastMenuFound.isEmpty()) {
			List<MobileElement> allChats = driver.findElementsById("parentLayout");
			int count=allChats.size();
			for (int i = 0; i < count; i++) {
				if (firsttime==1) {
					if (i==0) {
						checkValue=allChats.get(i).getSize().getHeight();
					}
				}else {
					//              	System.out.println("running second time");
				}
				int itemsY=allChats.get(i).getSize().getHeight();

				if (checkValue==itemsY) {
					languageName=allChats.get(i).findElementById("lan_name").getText();
					if (LanguageList.contains(languageName)) {
						//							System.out.println(languageName+" exists");
					}else {
						LanguageList.add(languageName);
						System.out.println(languageName);
					}
					last = allChats.get(i).getLocation();
					if (languageName.toLowerCase().contains(lastLanguageName.toLowerCase())) {
						lastMenuFound = "Yes";
						System.out.println("Last Menu Found");
						break;
					}else {
						//                      	System.out.println("Last Menu NOT Found");
					}
				}else {
					//						System.out.println("chkvlue itemsY not matched");
				}
			}
			new TouchAction(driver).press(PointOption.point(last)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(last.getX(),target+checkValue)).release().perform();
			firsttime=0;
		}
		System.out.println("Home Language Total Menu:"+LanguageList.size());

		for (int L = 0; L < LanguageList.size(); L++) {
			System.out.println(LanguageList.get(L));
			ActualhomeTopLanguageChinese.add(LanguageList.get(L));
		}
		System.out.println("===================================");
		driver.findElementById("ib_top_back_lang_selection").click();
	}
	@Test(priority=7)
	public void Russian_Home_Top_Language_List() throws Exception{
		System.out.println("TestCase 2 :: Russian_Home_Top_Language_List Started");
		Thread.sleep(2000);
		driver.findElementById("btn_navigation_menu_left").click();
		Thread.sleep(2000);
		driver.findElementsById("layout_main_iv_tv").get(0).click();
		Thread.sleep(1000);
		selectSystemLanguageOption("System Language","Работать на русском");
		Thread.sleep(6000);
		driver.findElementById("layout_gradient_bottom").click();
		Thread.sleep(2000);
		String lastLanguageName ="Вьетнамский"; // China mode
//		String lastLanguageName ="Зулу"; 
		ArrayList<String> LanguageList = new ArrayList<>();
		String languageName = "";
		String lastMenuFound = "";
		int tbsPosition= driver.findElementById("tabs").getLocation().getY();
		System.out.println(tbsPosition);
		int tbsHeight=driver.findElementById("viewpager").getLocation().getY();
		System.out.println(tbsHeight);
		int target=tbsPosition+tbsHeight;
		System.out.println(target);
		int firsttime=1;
		int checkValue=0;
		Point last = null;
		while (lastMenuFound.isEmpty()) {
			List<MobileElement> allChats = driver.findElementsById("parentLayout");
			int count=allChats.size();
			for (int i = 0; i < count; i++) {
				if (firsttime==1) {
					if (i==0) {
						checkValue=allChats.get(i).getSize().getHeight();
					}
				}else {
					//              	System.out.println("running second time");
				}
				int itemsY=allChats.get(i).getSize().getHeight();

				if (checkValue==itemsY) {
					languageName=allChats.get(i).findElementById("lan_name").getText();

					if (LanguageList.contains(languageName)) {
						//							System.out.println(languageName+" exists");
					}else {
						LanguageList.add(languageName);
						System.out.println(languageName);
					}

					last = allChats.get(i).getLocation();
					if (languageName.toLowerCase().contains(lastLanguageName.toLowerCase())) {
						lastMenuFound = "Yes";
						System.out.println("Last Menu Found");
						break;
					}else {
						//                      	System.out.println("Last Menu NOT Found");
					}
				}else {
					//						System.out.println("chkvlue itemsY not matched");
				}
			}
			new TouchAction(driver).press(PointOption.point(last)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(last.getX(),target+checkValue)).release().perform();
			firsttime=0;
		}
		System.out.println("Home Language Total Menu:"+LanguageList.size());

		for (int L = 0; L < LanguageList.size(); L++) {
			System.out.println(LanguageList.get(L));
			ActualhomeTopLanguageRussian.add(LanguageList.get(L));
		}
		System.out.println("===================================");
		driver.findElementById("text_back_okay").click();
	}

	@Test(priority=8)
	public void Malay_Home_Top_Language_List() throws Exception{
		System.out.println("TestCase 3 :: Malay_Home_Top_Language_List Started");
		Thread.sleep(2000);
		driver.findElementById("btn_navigation_menu_left").click();
		Thread.sleep(2000);
		driver.findElementsById("layout_main_iv_tv").get(0).click();
		Thread.sleep(1000);
		selectSystemLanguageOption("System Language","Operasi Dalam Bahasa Melayu");
		Thread.sleep(6000);
		driver.findElementById("layout_gradient_bottom").click();
		Thread.sleep(2000);
		String lastLanguageName ="Vietnam"; // china mode
//		String lastLanguageName ="Zulu";
		ArrayList<String> LanguageList = new ArrayList<>();
		String languageName = "";
		String lastMenuFound = "";

		int tbsPosition= driver.findElementById("tabs").getLocation().getY();
		System.out.println(tbsPosition);

		int tbsHeight=driver.findElementById("viewpager").getLocation().getY();
		System.out.println(tbsHeight);
		int target=tbsPosition+tbsHeight;
		System.out.println(target);
		int firsttime=1;
		int checkValue=0;
		Point last = null;
		while (lastMenuFound.isEmpty()) {
			List<MobileElement> allChats = driver.findElementsById("parentLayout");
			int count=allChats.size();
			for (int i = 0; i < count; i++) {
				if (firsttime==1) {
					if (i==0) {
						checkValue=allChats.get(i).getSize().getHeight();
					}
				}else {
					//              	System.out.println("running second time");
				}
				int itemsY=allChats.get(i).getSize().getHeight();

				if (checkValue==itemsY) {
					languageName=allChats.get(i).findElementById("lan_name").getText();

					if (LanguageList.contains(languageName)) {
						//							System.out.println(languageName+" exists");
					}else {
						LanguageList.add(languageName);
						System.out.println(languageName);
					}

					last = allChats.get(i).getLocation();
					if (languageName.toLowerCase().contains(lastLanguageName.toLowerCase())) {
						lastMenuFound = "Yes";
						System.out.println("Last Menu Found");
						break;
					}else {
						//                      	System.out.println("Last Menu NOT Found");
					}
				}else {
					//						System.out.println("chkvlue itemsY not matched");
				}
			}
			new TouchAction(driver).press(PointOption.point(last)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(last.getX(),target+checkValue)).release().perform();
			firsttime=0;
		}
		System.out.println("Home Language Total Menu:"+LanguageList.size());
		for (int L = 0; L < LanguageList.size(); L++) {
			System.out.println(LanguageList.get(L));
			ActualhomeTopLanguageMalay.add(LanguageList.get(L));
		}
		System.out.println("===================================");
		driver.findElementById("text_back_okay").click();
	}

	@Test(priority=9)
	public void Thai_Home_Top_Language_List() throws Exception{
		System.out.println("TestCase 4 :: Thai_Home_Top_Language_List Started");
		Thread.sleep(2000);
		driver.findElementById("btn_navigation_menu_left").click();
		Thread.sleep(2000);
		driver.findElementsById("layout_main_iv_tv").get(0).click();
		Thread.sleep(1000);
		selectSystemLanguageOption("System Language","ใช้งานด้วยภาษาไทย");
		Thread.sleep(6000);
		driver.findElementById("layout_gradient_bottom").click();
		Thread.sleep(2000);
		String lastLanguageName ="ภาษาเวียดนาม"; // china mode
//		String lastLanguageName ="ภาษาซูลู"; // global Mode
		ArrayList<String> LanguageList = new ArrayList<>();
		String languageName = "";
		String lastMenuFound = "";
		int tbsPosition= driver.findElementById("tabs").getLocation().getY();
		System.out.println(tbsPosition);

		int tbsHeight=driver.findElementById("viewpager").getLocation().getY();
		System.out.println(tbsHeight);
		int target=tbsPosition+tbsHeight;
		System.out.println(target);
		int firsttime=1;
		int checkValue=0;
		Point last = null;
		while (lastMenuFound.isEmpty()) {
			List<MobileElement> allChats = driver.findElementsById("parentLayout");
			int count=allChats.size();
			for (int i = 0; i < count; i++) {
				if (firsttime==1) {
					if (i==0) {
						checkValue=allChats.get(i).getSize().getHeight();
					}
				}else {
					//              	System.out.println("running second time");
				}
				int itemsY=allChats.get(i).getSize().getHeight();

				if (checkValue==itemsY) {
					languageName=allChats.get(i).findElementById("lan_name").getText();

					if (LanguageList.contains(languageName)) {
						//							System.out.println(languageName+" exists");
					}else {
						LanguageList.add(languageName);
						System.out.println(languageName);
					}

					last = allChats.get(i).getLocation();
					if (languageName.toLowerCase().contains(lastLanguageName.toLowerCase())) {
						lastMenuFound = "Yes";
						System.out.println("Last Menu Found");
						break;
					}else {
						//                      	System.out.println("Last Menu NOT Found");
					}
				}else {
					//						System.out.println("chkvlue itemsY not matched");
				}
			}
			new TouchAction(driver).press(PointOption.point(last)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(last.getX(),target+50)).release().perform();
			firsttime=0;
		}
		System.out.println("Home Language Total Menu:"+LanguageList.size());

		for (int L = 0; L < LanguageList.size(); L++) {
			System.out.println(LanguageList.get(L));
			ActualhomeTopLanguageThai.add(LanguageList.get(L));
		}
		System.out.println("===================================");
		driver.findElementById("text_back_okay").click();
	}
	
	@Test(priority=10)
	public void Portuguese_Home_Top_Language_List() throws Exception{
		System.out.println("TestCase 5 :: Portuguese_Home_Top_Language_List Started");
		Thread.sleep(2000);
		driver.findElementById("btn_navigation_menu_left").click();
		Thread.sleep(2000);
		driver.findElementsById("layout_main_iv_tv").get(0).click();
		Thread.sleep(1000);
		selectSystemLanguageOption("System Language","Operar em português");
		Thread.sleep(6000);
		driver.findElementById("layout_gradient_bottom").click();
		Thread.sleep(2000);
		String lastLanguageName ="Vietnamita"; // China Mode 
//		String lastLanguageName ="Zulu";  // Global Mode
		ArrayList<String> LanguageList = new ArrayList<>();
		String languageName = "";
		String lastMenuFound = "";

		int tbsPosition= driver.findElementById("tabs").getLocation().getY();
		System.out.println(tbsPosition);

		int tbsHeight=driver.findElementById("viewpager").getLocation().getY();
		System.out.println(tbsHeight);
		int target=tbsPosition+tbsHeight;
		System.out.println(target);
		int firsttime=1;
		int checkValue=0;
		Point last = null;
		while (lastMenuFound.isEmpty()) {
			List<MobileElement> allChats = driver.findElementsById("parentLayout");
			int count=allChats.size();
			for (int i = 0; i < count; i++) {
				if (firsttime==1) {
					if (i==0) {
						checkValue=allChats.get(i).getSize().getHeight();
					}
				}else {
					//              	System.out.println("running second time");
				}
				int itemsY=allChats.get(i).getSize().getHeight();

				if (checkValue==itemsY) {
					languageName=allChats.get(i).findElementById("lan_name").getText();

					if (LanguageList.contains(languageName)) {
						//							System.out.println(languageName+" exists");
					}else {
						LanguageList.add(languageName);
						System.out.println(languageName);
					}

					last = allChats.get(i).getLocation();
					if (languageName.toLowerCase().contains(lastLanguageName.toLowerCase())) {
						lastMenuFound = "Yes";
						System.out.println("Last Menu Found");
						break;
					}else {
						//                      	System.out.println("Last Menu NOT Found");
					}
				}else {
					//						System.out.println("chkvlue itemsY not matched");
				}
			}
			new TouchAction(driver).press(PointOption.point(last)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(last.getX(),target+50)).release().perform();
			firsttime=0;
		}
		System.out.println("Home Language Total Menu:"+LanguageList.size());

		for (int L = 0; L < LanguageList.size(); L++) {
			System.out.println(LanguageList.get(L));
			ActualhomeTopLanguagePortuguese.add(LanguageList.get(L));
		}
		System.out.println("===================================");
		driver.findElementById("text_back_okay").click();
		String filePath = System.getProperty("user.dir");
		writeHomeLanguageListChinaMode(filePath, "homelanguage_list.xlsx","Sheet1", ActualhomeTopLanguagePortuguese);
	}


	public void selectSourceLanguage(String sourceLanguage) throws InterruptedException, IOException {
		try {
			driver.findElementById("tvLanguageShooting").click();
			Thread.sleep(1000);
			MobileElement setSourceLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/recycler_view\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+sourceLanguage+"\"));"));
			setSourceLanguage.click();
			driver.findElementById("ib_top_back").click();
		} catch (Exception e) {
			this.errorScreenshot(errorImagePath, sourceLanguage);
			System.out.println("Select Source Language Failed...!!!"+e);

		}
	}
	public void selectSystemLanguageOption(String sourceLanguage, String SYS_Language) throws InterruptedException, IOException {
		try {
			Thread.sleep(1000);
			MobileElement setSourceLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/settingsRecyclerView\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+sourceLanguage+"\"));"));
			setSourceLanguage.click();
			Thread.sleep(1000);
			MobileElement setSYSLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/recycler_view\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+SYS_Language+"\"));"));
			setSYSLanguage.click();
//			driver.findElementById("text_back_okay").click();
			driver.findElementById("btn_okay").click();
			Thread.sleep(1000);
			driver.findElementById("iv_top_back").click();
		} catch (Exception e) {
			this.errorScreenshot(errorImagePath, sourceLanguage);
			System.out.println("Select System Language Failed...!!!"+e);

		}
	}
	public void selectTargetLanguage(String targetLanguage) throws InterruptedException, IOException {
		try {
			driver.findElementById("tvLanguageTranslate").click();
			Thread.sleep(1000);
			MobileElement setTargetLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/recycler_view\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+targetLanguage+"\"));"));
			setTargetLanguage.click();
			driver.findElementById("ib_top_back").click();
		} catch (Exception e) {
			System.out.println("Select Target Language Failed... !!! "+e);
			this.errorScreenshot(errorImagePath, targetLanguage);
		}
	}

	
	public void backToHomeScreen() throws InterruptedException {
		String menu="no";
		while (menu.contains("no")) {
			for (int i = 0; i < backButtons.size(); i++) {
				try {

					if (backButtons.get(i).contains("//android.widget.LinearLayout/android.widget.Button")) {
						try {
							if (!driver.findElementByXPath(backButtons.get(i)).getId().isEmpty()) {
								driver.findElementByXPath(backButtons.get(i)).click();
								i=backButtons.size();
							}else {
								System.out.print("Ok Button Not Working ");
							}
						} catch (Exception e) {
							//							System.out.print("Ok Button Not found !!! ");
						}
					}
					else {
						if(!driver.findElementById(backButtons.get(i)).getId().isEmpty()) {
							if (backButtons.get(i).contains("btn_navigation_menu")) {
								menu="YES";
								i= backButtons.size();
								System.out.println("Home Activity Found !!");
							}else {
								driver.findElementById(backButtons.get(i)).click();
							}
						}
					}

				} catch (Exception e) {
					//					System.out.println("Back To Home Not working....!!!");
				}
			}			
		}				
		Thread.sleep(1000);
	}

	public void errorScreenshot(String path_screenshot,String sourceLanguage) throws IOException, InterruptedException{
		File srcFile=driver.getScreenshotAs(OutputType.FILE);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String datetime = formatter.format(calendar.getTime());
		datetime = datetime.replaceAll("[:-]", "").replaceAll("[ ]", "_");
		File targetFile=new File(errorImagePath + datetime+"_"+sourceLanguage+".jpg");
		FileUtils.copyFile(srcFile,targetFile);
		Thread.sleep(1000);
	}


	public void readHomeLanguageList(String filePath,String fileName,String sheetName) throws IOException{

		File file =    new File(filePath+"\\"+fileName);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook guru99Workbook = null;
		XSSFSheet XLSX = null;
		HSSFSheet XLS = null;

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		if(fileExtensionName.equals(".xlsx")){
			guru99Workbook = new XSSFWorkbook(inputStream);
			XLSX = (XSSFSheet) guru99Workbook.getSheet(sheetName);
			int rowCount = XLSX.getLastRowNum()-XLSX.getFirstRowNum();
			System.out.println(XLSX.getLastRowNum()+"========"+XLSX.getFirstRowNum());

			Iterator<Row> rowIterator = XLSX.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell=cellIterator.next();
					if (row.getRowNum()==0) {
						LanguageListHeader.add(cell.getStringCellValue());
					}else {
						if (cell.getColumnIndex()==0) {
							expectedLanInTraditional_Chinese.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==1) {
							expectedLanIn_Russian.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==2) {
							expectedLanIn_Malay.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==3) {
							expectedLanIn_Thai.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==4) {
							expectedLanIn_Portuguese.add(cell.getStringCellValue());
						}
					}
				}
			}
			System.out.println(LanguageListHeader);
		}	    
	} 


	public void writeHomeLanguageList(String filePath,String fileName,String sheetName,ArrayList<String> cameraLanguageNames) throws IOException{
		File file =    new File(filePath+"\\"+fileName);
		FileInputStream inputStream = new FileInputStream(file);

		Workbook guru99Workbook = null;

		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			guru99Workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = (XSSFSheet) guru99Workbook.getSheet(sheetName);   
			Row row = sheet.getRow(0);
			System.out.print(cameraLanguageNames.size());

			for (int i = 0; i < cameraLanguageNames.size(); i++) {
				int rowCount = sheet.getLastRowNum();
				Row newRow = sheet.createRow(rowCount+1);
				for(int j = 0; j < row.getLastCellNum(); j++){
					if (j==0) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(expectedLanInTraditional_Chinese.get(i));
					}else if (j==1) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(ActualhomeTopLanguageChinese.get(i));
					}else if (j==2) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(expectedLanIn_Russian.get(i));
					}else if (j==3) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(ActualhomeTopLanguageRussian.get(i));
					}else if (j==4) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(expectedLanIn_Malay.get(i));
					}else if (j==5) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(ActualhomeTopLanguageMalay.get(i));
					}else if (j==6) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(expectedLanIn_Thai.get(i));
					}else if (j==7) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(ActualhomeTopLanguageThai.get(i));
					}else if (j==8) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(expectedLanIn_Portuguese.get(i));
					}else {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(ActualhomeTopLanguagePortuguese.get(i));
					}
				}
			}
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(file);
			guru99Workbook.write(outputStream);
			outputStream.close();
		}
		else if(fileExtensionName.equals(".xls")){
			guru99Workbook = new HSSFWorkbook(inputStream);
		}    
	}

	public void readCameraLanguageList(String filePath,String fileName,String sheetName) throws IOException{

		File file =    new File(filePath+"\\"+fileName);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook guru99Workbook = null;
		XSSFSheet XLSX = null;
		HSSFSheet XLS = null;

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		if(fileExtensionName.equals(".xlsx")){
			guru99Workbook = new XSSFWorkbook(inputStream);
			XLSX = (XSSFSheet) guru99Workbook.getSheet(sheetName);
			int rowCount = XLSX.getLastRowNum()-XLSX.getFirstRowNum();
			System.out.println(XLSX.getLastRowNum()+"========"+XLSX.getFirstRowNum());

			Iterator<Row> rowIterator = XLSX.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell=cellIterator.next();
					if (row.getRowNum()==0) {
						LanguageListHeader_Camera.add(cell.getStringCellValue());
					}else {
						if (cell.getColumnIndex()==0) {
							expectedLanInTraditional_Chinese_Camera.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==1) {
							expectedLanIn_Russian_Camera.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==2) {
							expectedLanIn_Malay_Camera.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==3) {
							expectedLanIn_Thai_Camera.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==4) {
							expectedLanIn_Portuguese_Camera.add(cell.getStringCellValue());
						}
					}
				}
			}
			System.out.println(LanguageListHeader_Camera);
		}	    
	} 

	public void writeCameraLanguageList(String filePath,String fileName,String sheetName,ArrayList<String> cameraLanguageNames) throws IOException{
		File file =    new File(filePath+"\\"+fileName);
		FileInputStream inputStream = new FileInputStream(file);

		Workbook guru99Workbook = null;

		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			guru99Workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = (XSSFSheet) guru99Workbook.getSheet(sheetName);   
			Row row = sheet.getRow(0);
			System.out.print(cameraLanguageNames.size());

			for (int i = 0; i < cameraLanguageNames.size(); i++) {
				int rowCount = sheet.getLastRowNum();
				Row newRow = sheet.createRow(rowCount+1);
				for(int j = 0; j < row.getLastCellNum(); j++){
					if (j==0) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(expectedLanInTraditional_Chinese.get(i));
					}else if (j==1) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(ActualCamera_FromLanguage_Chinese.get(i));
					}else if (j==2) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(expectedLanIn_Russian.get(i));
					}else if (j==3) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(ActualCamera_FromLanguage_Russian.get(i));
					}else if (j==4) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(expectedLanIn_Malay.get(i));
					}else if (j==5) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(ActualCamera_FromLanguage_Malay.get(i));
					}else if (j==6) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(expectedLanIn_Thai.get(i));
					}else if (j==7) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(ActualCamera_FromLanguage_Thai.get(i));
					}else if (j==8) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(expectedLanIn_Portuguese.get(i));
					}else {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(ActualCamera_FromLanguage_Portuguese.get(i));
					}
				}
			}
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(file);
			guru99Workbook.write(outputStream);
			outputStream.close();
		}
		else if(fileExtensionName.equals(".xls")){
			guru99Workbook = new HSSFWorkbook(inputStream);
		}    
	}

	public void writeHomeLanguageListChinaMode(String filePath,String fileName,String sheetName,ArrayList<String> cameraLanguageNames) throws IOException{
		File file =    new File(filePath+"\\"+fileName);
		FileInputStream inputStream = new FileInputStream(file);

		Workbook guru99Workbook = null;

		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			guru99Workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = (XSSFSheet) guru99Workbook.getSheet(sheetName);   
			Row row = sheet.getRow(0);
			System.out.print(cameraLanguageNames.size());

			for (int i = 0; i < cameraLanguageNames.size(); i++) {
				int rowCount = sheet.getLastRowNum();
				Row newRow = sheet.createRow(rowCount+1);
				for(int j = 0; j < row.getLastCellNum(); j++){
					if (j==0) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(ActualhomeTopLanguageChinese.get(i));
					}else if (j==1) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(ActualhomeTopLanguageRussian.get(i));
					}else if (j==2) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(ActualhomeTopLanguageMalay.get(i));
					}else if (j==3) {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(ActualhomeTopLanguageThai.get(i));
					}else {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(ActualhomeTopLanguagePortuguese.get(i));
					}
				}
			}
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(file);
			guru99Workbook.write(outputStream);
			outputStream.close();
		}
		else if(fileExtensionName.equals(".xls")){
			guru99Workbook = new HSSFWorkbook(inputStream);
		}    
	}

	public void readCountryName(String filePath,String fileName,String sheetName) throws IOException{

		File file =    new File(filePath+"\\"+fileName);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook guru99Workbook = null;
		XSSFSheet XLSX = null;
		HSSFSheet XLS = null;

		String fileExtensionName = fileName.substring(fileName.indexOf("."));

		if(fileExtensionName.equals(".xlsx")){
			guru99Workbook = new XSSFWorkbook(inputStream);
			XLSX = (XSSFSheet) guru99Workbook.getSheet(sheetName);
			int rowCount = XLSX.getLastRowNum()-XLSX.getFirstRowNum();
			System.out.println(XLSX.getLastRowNum()+"========"+XLSX.getFirstRowNum());

			Iterator<Row> rowIterator = XLSX.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell=cellIterator.next();
					if (row.getRowNum()==0) {
						LanguageListHeader_Camera.add(cell.getStringCellValue());
					}else {
						if (cell.getColumnIndex()==0) {
							countryName_Chinese.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==1) {
							countryName_Russian.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==2) {
							countryName_Malay.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==3) {
							countryName_Thai.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==4) {
							countryName_Portuguese.add(cell.getStringCellValue());
						}
					}
				}
			}
			System.out.println(LanguageListHeader_Camera);
		}	    
	} 

	public void writeCountrySpecificLanguage(String filePath,String fileName) throws IOException{
		File file =    new File(filePath+"\\"+fileName);
		FileInputStream inputStream = new FileInputStream(file);

		Workbook guru99Workbook = null;

		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			guru99Workbook = new XSSFWorkbook(inputStream);
			guru99Workbook.createSheet("English");
			XSSFSheet sheet = (XSSFSheet) guru99Workbook.getSheet("English");

			for (int i = 0; i < countryLanguages_English.size(); i++) {
				Row newRow = sheet.createRow(i);
				System.out.println(countryLanguages_English.get(i).getCountryName());
				for(int j = 0; j <countryLanguages_English.get(i).getCountryLanguages().size()+1 ; j++){
					if (j==0) {
						Cell countryNameCell = newRow.createCell(j);
						countryNameCell.setCellValue(countryLanguages_English.get(i).getCountryName());
					}else {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(countryLanguages_English.get(i).getCountryLanguages().get(j-1));
					}
				}
			}
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(file);
			guru99Workbook.write(outputStream);
			outputStream.close();
		}
		else if(fileExtensionName.equals(".xls")){
			guru99Workbook = new HSSFWorkbook(inputStream);
		}    
	}

}

