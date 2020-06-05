import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class GDPR_Execute_EU {
	private static final java.awt.event.KeyEvent KeyEvent = null;
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
	public static String packageName = "com.sourcenext.pocketalks";
	public static String packageNameEU = "com.sourcenext.pocketalks";
	private static String ANDROID_PHOTO_PATH = "/mnt/sdcard/Android/data/"+packageName+"/files";
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

	public static ArrayList<String> countryName_English = new ArrayList<String>();
	public static ArrayList<String> countryName_Japanese = new ArrayList<String>();
	public static ArrayList<String> countryName_Chinese = new ArrayList<String>();
	public static ArrayList<String> countryName_Russian = new ArrayList<String>();
	public static ArrayList<String> countryName_Malay = new ArrayList<String>();
	public static ArrayList<String> countryName_Thai = new ArrayList<String>();
	public static ArrayList<String> countryName_Portuguese = new ArrayList<String>();
	public static ArrayList<ModelSubCountryList> countryLanguages_English = new ArrayList<ModelSubCountryList>();
	public static ArrayList<ModelSubCountryList> countryLanguages_Japanese = new ArrayList<ModelSubCountryList>();
	public static ArrayList<ModelSubCountryList> countryLanguages_Chinese = new ArrayList<ModelSubCountryList>();
	public static ArrayList<ModelSubCountryList> countryLanguages_Russian = new ArrayList<ModelSubCountryList>();
	public static ArrayList<ModelSubCountryList> countryLanguages_Malay = new ArrayList<ModelSubCountryList>();
	public static ArrayList<ModelSubCountryList> countryLanguages_Thai = new ArrayList<ModelSubCountryList>();
	public static ArrayList<ModelSubCountryList> countryLanguages_Portuguese = new ArrayList<ModelSubCountryList>();
	public LogEntries logEntries;

	@BeforeTest
	public void setup() throws InterruptedException, IOException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android"); 
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"8.1.0");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"0123456789ABCDEF");
		capabilities.setCapability("noReset","true"); 
		capabilities.setCapability("fullReset","false");
		capabilities.setCapability("appPackage", packageName);
		capabilities.setCapability("appActivity",packageName+".activity.MainActivity");
		capabilities.setCapability("clearDeviceLogsOnStart", true);

		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.3:4725/wd/hub"), capabilities);
		Thread.sleep(7000);
		logEntries = driver.manage().logs().get("logcat");

		System.out.println("Battery Level "+driver.getBatteryInfo().getLevel());
		System.out.println("Battery State "+driver.getBatteryInfo().getState());

		String filePath = System.getProperty("user.dir");
		String path = filePath+"\\PTS_Feb_17";
		       this.readCountryName(path, "countryName_withOrder_Implemented.xlsx", "Sheet1");
		       	this.backButtons.add("btn_navigation_menu_left");
				this.backButtons.add("ib_back");
				this.backButtons.add("iv_top_back");
				this.backButtons.add("ib_top_back");
				this.backButtons.add("discard");
				this.backButtons.add("close_dialog");
				this.backButtons.add("imagebutton_top_back");
				this.backButtons.add("btn_ok");
				this.backButtons.add("//android.widget.LinearLayout/android.widget.Button");
				this.backButtons.add("android:id/button1");
				this.backButtons.add("top_back_ok_layout");
				this.backButtons.add("text_back_okay");
				this.backButtons.add("com.sourcenext.pocketalk.settings:id/action_bar");
	}
	
	// country specific sub language list

//	@Test(priority=1)
//	public void Country_Specific_Sub_Language_List_Chinese() throws Exception{
//		try {
//			System.out.println("TestCase 1 :: Country_Specific_Sub_Language_List_Chinese");
//			Thread.sleep(1000);
////			driver.findElementById("btn_navigation_menu_left").click();
////			Thread.sleep(2000);
////			driver.findElementsById("layout_main_iv_tv").get(0).click();
////			Thread.sleep(1000);
////			selectSystemLanguageOption("System Language","Работать на русском");
////			Thread.sleep(6000);
//			driver.findElementById("layout_gradient_top").click();
//			driver.findElementById("ib_language").click();
//			Thread.sleep(2000);
//			try {
//				for (int m = 0; m < countryName_Chinese.size(); m++) {
//					selectTargetCountry(countryName_Chinese.get(m));
//					String languageName = "";
//					String lastCountryFound = "";
//					ArrayList<String> CountryList_Sub = new ArrayList<>();  
//					int asd = 1;  
//					while (asd<=2) {
//						List<MobileElement> subCountry_listView = driver.findElementsById("parentLayout");
//						int count=subCountry_listView.size();
//						Point sourcePosition=null;
//						//    	    System.out.println(count);
//						for (int i = 0; i < count; i++) {
//							int itemSize = subCountry_listView.get(i).getSize().getHeight();
//							if (90==itemSize) {
//								languageName= subCountry_listView.get(i).findElementById("lan_name").getText();
//								if (CountryList_Sub.contains(languageName)) {
//									//    						System.out.println(languageName+" exists"+i);
//								}else {
//									//    						System.out.println(languageName+"---"+i);
//									CountryList_Sub.add(languageName);
//								}
//								sourcePosition = subCountry_listView.get(i).getLocation();
//							}else {
//								//    					System.out.println("Size not matched"+itemSize);
//							}
//							Thread.sleep(1000);
//						}
//						if (count>4) {
//							new TouchAction(driver).press(PointOption.point(sourcePosition)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(400,108)).release().perform();
//							asd++;
//						}else {
//							asd++;
//						}
//					}
//					ModelSubCountryList sub = new ModelSubCountryList(countryName_Chinese.get(m),CountryList_Sub);
//					sub.setCountryLanguages(CountryList_Sub);
//					sub.setCountryName(countryName_Chinese.get(m));
//					countryLanguages_Chinese.add(sub);
//
//					driver.findElementById("ib_top_back").click();
//					Thread.sleep(1000);
//					System.out.print(countryName_Chinese.get(m)+" : ");
//					for (int p = 0; p < CountryList_Sub.size(); p++) {
//						System.out.print(CountryList_Sub.get(p)+" , ");
//					}
//					System.out.println();
//				}
//			} catch (Exception e) {
//				errorScreenshot(errorImagePath,"Chinese");
//				System.out.println(e);
//				this.backToHomeScreen();
//			}   
//
//			String filePath = System.getProperty("user.dir");
//			writeCountrySpecificLanguage(filePath, "countryLanguagesList_Evening_Last.xlsx",LanguageListHeader_Camera.get(0),countryLanguages_Chinese );
//			this.backToHomeScreen();
//		} catch (Exception e) {
//			this.backToHomeScreen();
//		}
//		
//	}
//	
//	@Test(priority=2)
//	public void Country_Specific_Sub_Language_List_Russian() throws Exception{
//		try {
//		System.out.println("TestCase 1 :: Country_Specific_Sub_Language_List_Russian");
//		Thread.sleep(1000);
//		driver.findElementById("btn_navigation_menu_left").click();
//		Thread.sleep(2000);
//		driver.findElementsById("layout_main_iv_tv").get(0).click();
//		Thread.sleep(1000);
//		selectSystemLanguageOption("System Language","Работать на русском");
//		Thread.sleep(6000);
//		driver.findElementById("layout_gradient_top").click();
//		driver.findElementById("ib_language").click();
//		Thread.sleep(2000);
//		try {
//			for (int m = 0; m < countryName_Russian.size(); m++) {
//				selectTargetCountry(countryName_Russian.get(m));
//				String languageName = "";
//				String lastCountryFound = "";
//				ArrayList<String> CountryList_Sub = new ArrayList<>();  
//				int asd = 1;  
//				while (asd<=2) {
//					List<MobileElement> subCountry_listView = driver.findElementsById("parentLayout");
//					int count=subCountry_listView.size();
//					Point sourcePosition=null;
//					//    	    System.out.println(count);
//					for (int i = 0; i < count; i++) {
//						int itemSize = subCountry_listView.get(i).getSize().getHeight();
//						if (90==itemSize) {
//							languageName= subCountry_listView.get(i).findElementById("lan_name").getText();
//							if (CountryList_Sub.contains(languageName)) {
//								//    						System.out.println(languageName+" exists"+i);
//							}else {
//								//    						System.out.println(languageName+"---"+i);
//								CountryList_Sub.add(languageName);
//							}
//							sourcePosition = subCountry_listView.get(i).getLocation();
//						}else {
//							//    					System.out.println("Size not matched"+itemSize);
//						}
//						Thread.sleep(1000);
//					}
//					if (count>4) {
//						new TouchAction(driver).press(PointOption.point(sourcePosition)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(400,108)).release().perform();
//						asd++;
//					}else {
//						asd++;
//					}
//				}
//				ModelSubCountryList sub = new ModelSubCountryList(countryName_Russian.get(m),CountryList_Sub);
//				sub.setCountryLanguages(CountryList_Sub);
//				sub.setCountryName(countryName_Russian.get(m));
//				countryLanguages_Russian.add(sub);
//
//				driver.findElementById("ib_top_back").click();
//				Thread.sleep(1000);
//				System.out.print(countryName_Russian.get(m)+" : ");
//				for (int p = 0; p < CountryList_Sub.size(); p++) {
//					System.out.print(CountryList_Sub.get(p)+" , ");
//				}
//				System.out.println();
//			}
//		} catch (Exception e) {
//			errorScreenshot(errorImagePath,"Russian");
//			System.out.println(e);
//			this.backToHomeScreen();
//		}   
//
//		String filePath = System.getProperty("user.dir");
//		writeCountrySpecificLanguage(filePath, "countryLanguagesList_Evening_Last.xlsx",LanguageListHeader_Camera.get(1),countryLanguages_Russian );
//		this.backToHomeScreen();
//		} catch (Exception e) {
//			this.backToHomeScreen();
//		}
//	}

	@Test(priority=3)
	public void Country_Specific_Sub_Language_List_Malay() throws Exception{
		try {
		System.out.println("TestCase 1 :: Country_Specific_Sub_Language_List_Malay");
		Thread.sleep(1000);
		driver.findElementById("btn_navigation_menu_left").click();
		Thread.sleep(2000);
		driver.findElementsById("layout_main_iv_tv").get(0).click();
		Thread.sleep(1000);
		selectSystemLanguageOption("System Language","Operasi Dalam Bahasa Melayu");
		Thread.sleep(6000);
		driver.findElementById("layout_gradient_top").click();
		driver.findElementById("ib_language").click();
		Thread.sleep(2000);
		try {
			for (int m = 0; m < countryName_Malay.size(); m++) {
				selectTargetCountry(countryName_Malay.get(m));
				String languageName = "";
				String lastCountryFound = "";
				ArrayList<String> CountryList_Sub = new ArrayList<>();  
				int asd = 1;  
				while (asd<=2) {
					List<MobileElement> subCountry_listView = driver.findElementsById("parentLayout");
					int count=subCountry_listView.size();
					Point sourcePosition=null;
					//    	    System.out.println(count);
					for (int i = 0; i < count; i++) {
						int itemSize = subCountry_listView.get(i).getSize().getHeight();
						if (90==itemSize) {
							languageName= subCountry_listView.get(i).findElementById("lan_name").getText();
							if (CountryList_Sub.contains(languageName)) {
								//    						System.out.println(languageName+" exists"+i);
							}else {
								//    						System.out.println(languageName+"---"+i);
								CountryList_Sub.add(languageName);
							}
							sourcePosition = subCountry_listView.get(i).getLocation();
						}else {
							//    					System.out.println("Size not matched"+itemSize);
						}
						Thread.sleep(1000);
					}
					if (count>4) {
						new TouchAction(driver).press(PointOption.point(sourcePosition)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(400,108)).release().perform();
						asd++;
					}else {
						asd++;
					}
				}
				ModelSubCountryList sub = new ModelSubCountryList(countryName_Malay.get(m),CountryList_Sub);
				sub.setCountryLanguages(CountryList_Sub);
				sub.setCountryName(countryName_Malay.get(m));
				countryLanguages_Malay.add(sub);

				driver.findElementById("ib_top_back").click();
				Thread.sleep(1000);
				System.out.print(countryName_Malay.get(m)+" : ");
				for (int p = 0; p < CountryList_Sub.size(); p++) {
					System.out.print(CountryList_Sub.get(p)+" , ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			errorScreenshot(errorImagePath,"Malay");
			System.out.println(e);
			this.backToHomeScreen();
		}   
		//    for (int i = 0; i < countryLanguages_English.size(); i++) {
		//    	System.out.print(countryLanguages_English.get(i).getCountryName()+",");
		//		for (int j = 0; j < countryLanguages_English.get(i).getCountryLanguages().size(); j++) {
		//			System.out.print(countryLanguages_English.get(i).getCountryLanguages().get(j)+",");
		//		}
		//		System.out.println();
		//	}  

		String filePath = System.getProperty("user.dir");
		writeCountrySpecificLanguage(filePath, "countryLanguagesList_Evening_Last.xlsx",LanguageListHeader_Camera.get(2),countryLanguages_Malay );
		this.backToHomeScreen();
		} catch (Exception e) {
			this.backToHomeScreen();
		}
	}

	@Test(priority=4)
	public void Country_Specific_Sub_Language_List_Portuguese() throws Exception{
		try {
		System.out.println("TestCase 1 :: Country_Specific_Sub_Language_List_Portuguese");
		Thread.sleep(1000);
		driver.findElementById("btn_navigation_menu_left").click();
		Thread.sleep(2000);
		driver.findElementsById("layout_main_iv_tv").get(0).click();
		Thread.sleep(1000);
		selectSystemLanguageOption("System Language","Operar em português");
		Thread.sleep(6000);
		driver.findElementById("layout_gradient_top").click();
		driver.findElementById("ib_language").click();
		Thread.sleep(2000);
		try {
			for (int m = 0; m < countryName_Portuguese.size(); m++) {
				selectTargetCountry(countryName_Portuguese.get(m));
				String languageName = "";
				String lastCountryFound = "";
				ArrayList<String> CountryList_Sub = new ArrayList<>();  
				int asd = 1;  
				while (asd<=2) {
					List<MobileElement> subCountry_listView = driver.findElementsById("parentLayout");
					int count=subCountry_listView.size();
					Point sourcePosition=null;
					//    	    System.out.println(count);
					for (int i = 0; i < count; i++) {
						int itemSize = subCountry_listView.get(i).getSize().getHeight();
						if (90==itemSize) {
							languageName= subCountry_listView.get(i).findElementById("lan_name").getText();
							if (CountryList_Sub.contains(languageName)) {
								//    						System.out.println(languageName+" exists"+i);
							}else {
								//    						System.out.println(languageName+"---"+i);
								CountryList_Sub.add(languageName);
							}
							sourcePosition = subCountry_listView.get(i).getLocation();
						}else {
							//    					System.out.println("Size not matched"+itemSize);
						}
						Thread.sleep(1000);
					}
					if (count>4) {
						new TouchAction(driver).press(PointOption.point(sourcePosition)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(400,108)).release().perform();
						asd++;
					}else {
						asd++;
					}
				}
				ModelSubCountryList sub = new ModelSubCountryList(countryName_Portuguese.get(m),CountryList_Sub);
				sub.setCountryLanguages(CountryList_Sub);
				sub.setCountryName(countryName_Portuguese.get(m));
				countryLanguages_Portuguese.add(sub);

				driver.findElementById("ib_top_back").click();
				Thread.sleep(1000);
				System.out.print(countryName_Portuguese.get(m)+" : ");
				for (int p = 0; p < CountryList_Sub.size(); p++) {
					System.out.print(CountryList_Sub.get(p)+" , ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			errorScreenshot(errorImagePath,"português");
			System.out.println(e);
			this.backToHomeScreen();
		}
		String filePath = System.getProperty("user.dir");
		writeCountrySpecificLanguage(filePath, "countryLanguagesList_Evening_Last.xlsx",LanguageListHeader_Camera.get(4),countryLanguages_Portuguese );
		this.backToHomeScreen();
		} catch (Exception e) {
			this.backToHomeScreen();
		}
	}
	
	@Test(priority=5)
	public void Country_Specific_Sub_Language_List_THAI() throws Exception{
		try {
		System.out.println("TestCase 1 :: Country_Specific_Sub_Language_List_THAI");
		Thread.sleep(1000);
		driver.findElementById("btn_navigation_menu_left").click();
		Thread.sleep(2000);
		driver.findElementsById("layout_main_iv_tv").get(0).click();
		Thread.sleep(1000);
		selectSystemLanguageOption("System Language","ใช้งานด้วยภาษาไทย");
		Thread.sleep(6000);
		driver.findElementById("layout_gradient_top").click();
		driver.findElementById("ib_language").click();
		Thread.sleep(2000);
		try {
			for (int m = 0; m < countryName_Thai.size(); m++) {
				selectTargetCountry(countryName_Thai.get(m));
				String languageName = "";
				String lastCountryFound = "";
				ArrayList<String> CountryList_Sub = new ArrayList<>();  
				int asd = 1;  
				while (asd<=2) {
					List<MobileElement> subCountry_listView = driver.findElementsById("parentLayout"); 
					int count=subCountry_listView.size();
					Point sourcePosition=null;
					//    	    System.out.println(count);
					for (int i = 0; i < count; i++) {
						int itemSize = subCountry_listView.get(i).getSize().getHeight();
						if (90==itemSize) {
							languageName= subCountry_listView.get(i).findElementById("lan_name").getText();
							if (CountryList_Sub.contains(languageName)) {
								//    						System.out.println(languageName+" exists"+i);
							}else {
								//    						System.out.println(languageName+"---"+i);
								CountryList_Sub.add(languageName);
							}
							sourcePosition = subCountry_listView.get(i).getLocation();
						}else {
							//    					System.out.println("Size not matched"+itemSize);
						}
						Thread.sleep(1000);
					}
					if (count>4) {
						new TouchAction(driver).press(PointOption.point(sourcePosition)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(400,108)).release().perform();
						asd++;
					}else {
						asd++;
					}
				}
				ModelSubCountryList sub = new ModelSubCountryList(countryName_Thai.get(m),CountryList_Sub);
				sub.setCountryLanguages(CountryList_Sub);
				sub.setCountryName(countryName_Thai.get(m));
				countryLanguages_Thai.add(sub);

				driver.findElementById("ib_top_back").click();
				Thread.sleep(1000);
				System.out.print(countryName_Thai.get(m)+" : ");
				for (int p = 0; p < CountryList_Sub.size(); p++) {
					System.out.print(CountryList_Sub.get(p)+" , ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			errorScreenshot(errorImagePath,"Thai");
			System.out.println(e);
			this.backToHomeScreen();
		}   
		String filePath = System.getProperty("user.dir");
		writeCountrySpecificLanguage(filePath, "countryLanguagesList_Evening_Last.xlsx",LanguageListHeader_Camera.get(3),countryLanguages_Thai );
		this.backToHomeScreen();
		} catch (Exception e) {
			this.backToHomeScreen();
		}
	}

	public void selectTargetCountry(String countryName) throws InterruptedException, IOException {
		try {
			Thread.sleep(1000);
			MobileElement setSourceLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/countryListGridView\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+countryName+"\"));"));
			setSourceLanguage.click();
		} catch (Exception e) {
			this.errorScreenshot(errorImagePath, countryName);
			System.out.println("Select countryName Failed...!!!"+e);

		}
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
			driver.findElementById("text_back_okay").click();
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

	public void imagePush(String imageName) throws InterruptedException {
		try {
			File assetDir = new File(classpathRoot, "/assets");
			File img = new File(assetDir.getCanonicalPath(), imageName);
			driver.pushFile(ANDROID_PHOTO_PATH + "/temp.jpg", img);
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Push Failed!!");

		}
	}

	public void checkImageTranslation(String tts, String sourceLanguage) throws InterruptedException, IOException {
		try {
			Thread.sleep(5000);
			java.util.List<MobileElement> trblock = driver.findElementsByXPath("//android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.TextView");
			System.out.print(trblock.size());
			System.out.print(" Block of Results, ");
			Thread.sleep(1000);
			if (trblock.size()>1) {
				for (int i = 0; i < trblock.size(); i++) {
					Thread.sleep(1000);
					try {
						trblock.get(i).click();
					} catch (Exception e) {
						this.errorScreenshot(errorImagePath, sourceLanguage);
						Thread.sleep(1000);
					}
					Thread.sleep(2000);
					if (tts.contains("YES")) {
						try {
							driver.findElementById("btn_play").click();
							//							System.out.println(logEntries.toString());
							Thread.sleep(3000);
						} catch (Exception e) {
							System.out.println("Play Button Not working...");
							Thread.sleep(1000);
							this.errorScreenshot(errorImagePath, sourceLanguage);
						}
					}else {
						System.out.println("TTS Not Supported!!");
					}
					try {
						driver.findElementById("close_dialog").click();
						Thread.sleep(1000);
					} catch (Exception e) {
						System.out.println("Dialog Close not working");
						this.errorScreenshot(errorImagePath, sourceLanguage);
						Thread.sleep(1000);

					}
				}
			}else {
				Thread.sleep(1000);
				try {
					trblock.get(0).click();
				} catch (Exception e) {
					errorScreenshot(errorImagePath, sourceLanguage);
				}


				Thread.sleep(2000);
				if (tts.contains("YES")) {
					try {
						driver.findElementById("btn_play").click();
						//						System.out.println(logEntries.toString());
						//						System.out.println(logEntries.toJson().get(0));
						//						int xxx = logEntries.getAll().size();
						//						System.out.println(logEntries.getAll().get(xxx).getMessage());
						Thread.sleep(3000);
					} catch (Exception e) {
						System.out.println("Play Button Not working...");
					}
				}else {
					System.out.println("TTS Not Supported!!");
				}
				try {
					driver.findElementById("close_dialog").click();
					Thread.sleep(1000);
				} catch (Exception e) {
					System.out.println("Dialog Close not working");
				}
			}
			try {
				driver.findElementById("iv_top_back").click();

			} catch (Exception e) {
				System.out.println("Unable to click iv_top_back"+e);

			}
		} catch (Exception e) {
			System.out.println("Image Translation Failed!!"+e);
			errorScreenshot(errorImagePath, sourceLanguage);

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
							if (backButtons.get(i).contains("btn_navigation_menu_left")) {
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
		Thread.sleep(1000);
		File srcFile=driver.getScreenshotAs(OutputType.FILE);
		Date date= Calendar.getInstance().getTime();
		String datetime = date.toString().replaceAll("[ :.]", "_");
		File targetFile=new File(path_screenshot + datetime+" "+sourceLanguage +".jpg");
		FileUtils.copyFile(srcFile,targetFile);
		Thread.sleep(1000);
	}

	public void readCameraTranslationLanguages(String filePath,String fileName,String sheetName) throws IOException{
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
			System.out.println(fromLanguage);
			System.out.println(toLanguage);
			//	    System.out.println(imageName);
			System.out.println(ttsSupported);
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

	public void writeCountrySpecificLanguage(String filePath,String fileName, String SysLanguage,ArrayList<ModelSubCountryList> ContryLanguages) throws IOException{
		File file =    new File(filePath+"\\"+fileName);
		FileInputStream inputStream = new FileInputStream(file);

		Workbook guru99Workbook = null;

		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			guru99Workbook = new XSSFWorkbook(inputStream);
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String datetime = formatter.format(calendar.getTime());
			datetime = datetime.replaceAll("[:-]", "").replaceAll("[ ]", "_");
			guru99Workbook.createSheet(SysLanguage+"_"+datetime);
			XSSFSheet sheet = (XSSFSheet) guru99Workbook.getSheet(SysLanguage);

			for (int i = 0; i < ContryLanguages.size(); i++) {
				Row newRow = sheet.createRow(i);
				System.out.println(ContryLanguages.get(i).getCountryName());
				for(int j = 0; j <ContryLanguages.get(i).getCountryLanguages().size()+1 ; j++){
					if (j==0) {
						Cell countryNameCell = newRow.createCell(j);
						countryNameCell.setCellValue(ContryLanguages.get(i).getCountryName());
					}else {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(ContryLanguages.get(i).getCountryLanguages().get(j-1));
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

	public void writeCountryNames(String filePath,String fileName, String SysLanguage,ArrayList<String> CountryNames) throws IOException{
		File file =    new File(filePath+"\\"+fileName);
		FileInputStream inputStream = new FileInputStream(file);

		Workbook guru99Workbook = null;

		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			guru99Workbook = new XSSFWorkbook(inputStream);
			guru99Workbook.createSheet(SysLanguage);
			XSSFSheet sheet = (XSSFSheet) guru99Workbook.getSheet(SysLanguage);

			for (int i = 0; i < CountryNames.size(); i++) {
				Row newRow = sheet.createRow(i);
				Cell cell = newRow.createCell(0);
				cell.setCellValue(CountryNames.get(i));
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

	public void backToHistoryList() throws InterruptedException {
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
							if (backButtons.get(i).contains("ib_back")) {
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

	public void backTo_Test_InitialScreen(String ByType, String Name) throws InterruptedException {
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
					}else {
						if(!driver.findElementById(backButtons.get(i)).getId().isEmpty()) {
							if (backButtons.get(i).contains("ib_back")) {
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
}


