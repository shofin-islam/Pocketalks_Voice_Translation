import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ProcessBuilder.Redirect;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.ThreeDEval;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class PTStringExecute {
	private static File classpathRoot = new File(System.getProperty("user.dir"));
	public static AndroidDriver<MobileElement> driver;
	public static String packageName = "com.sourcenext.pocketalks";
	private MenuSettingsPOM menuSettings;
	private MainMenu_POM mainMenu;
	private PtsSupportModel_toString support;
	private MobileData_POM mobileData;
	private ArrayList<String> settingsMenuStored = new ArrayList<String>();
	private ArrayList<String> resetMenuStored = new ArrayList<String>();
	private ArrayList<String> ExcelHeaderOfUiInfo = new ArrayList<String>();
	private ArrayList<String> ScreenNameInExcel = new ArrayList<String>();
	private ArrayList<String> ElementsIdInExcel = new ArrayList<String>();
	private ArrayList<String> ExpectedStringInExcel = new ArrayList<String>();
	private ArrayList<String> intialLanguageListStored = new ArrayList<String>();
	private ArrayList<String> mySpecialArray = new ArrayList<String>();
	private ArrayList<String> mccListToLock = new ArrayList<String>();

	private String selectedSystemLanguage = "Thai";
	private int systemLanguageIndex = 12;

	public ArrayList<StringsOfScreenModel> stringsOfScreenToExcel = new ArrayList<StringsOfScreenModel>();
	@BeforeTest
	public void setup() throws InterruptedException, IOException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android"); 
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"8.1.0");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"0123456789ABCDEF");
		capabilities.setCapability("udid", "0123456789ABCDEF");
		//		capabilities.setCapability("systemPort",8200);
		capabilities.setCapability("noReset","true"); 
		capabilities.setCapability("fullReset","false");
		capabilities.setCapability("appPackage", packageName);
		capabilities.setCapability("appActivity",packageName+".activity.MainActivity");
		capabilities.setCapability("clearDeviceLogsOnStart", true);
		capabilities.setCapability("skipDeviceInitialization", true);
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.3:4725/wd/hub"), capabilities);
		Thread.sleep(8000);
		menuSettings =  new MenuSettingsPOM(driver);
		mainMenu = new MainMenu_POM(driver);
		support = new PtsSupportModel_toString(driver, packageName);
		mobileData = new MobileData_POM(driver);
		String filePath = System.getProperty("user.dir");
		this.readStringInformation(filePath, "StringsInfo.xlsx", "Sheet1");
		for (int mm = 108; mm <= 121; mm++) {
			settingsMenuStored.add(ElementsIdInExcel.get(mm));
			//			System.out.println(ElementsIdInExcel.get(mm));
		}
//		ArrayList<String> tt = new ArrayList<String>(Arrays. asList("Amar Bangladesh","Bangladesh","Sonar Bangladesh"));
	}	



	//	@Test (priority=0)
	//	public void Initial_System_Language_String_Test() throws Exception {
	//		System.out.println("Initial_System_Language_String_Test() -- Started 31");
	//		ArrayList<String> screenName = new ArrayList<String>();
	//		ArrayList<String> elementsId = new ArrayList<String>();
	//		ArrayList<String> stringInfo = new ArrayList<String>();
	//		screenName.add("System_Language");
	//		elementsId.add("toolbar_title");
	//		stringInfo.add(support.getStringByIdXpath("toolbar_title", false, -1));
	//		ArrayList<String> temp = new ArrayList<String>();
	//		int asd=1;
	//		while (asd<=3) {
	//			List<MobileElement> languages = driver.findElementsById("txt_lang_name");
	//			for (int i =0; i <languages.size(); i++) {
	//				try {
	//					String s = languages.get(i).getText();
	//					if (!temp.contains(s) && !s.isEmpty()) {
	//						temp.add(s);
	//						screenName.add("System_Language");
	//						elementsId.add("txt_lang_name");
	//						stringInfo.add(s);
	//						intialLanguageListStored.add(s);
	//						System.out.println(s);
	//					}else {
	////						System.out.println("String empty or available in the temp array");
	//					}
	//				} catch (Exception e) {
	//					System.out.println("get text exception...");
	//				}
	//			}
	//			new TouchAction(driver).press(PointOption.point(support.getMaxPositionOfDisplay("bottom"))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(support.getMaxPositionOfDisplay("TOP").getX(),support.getMaxPositionOfDisplay("TOP").getY()+10)).release().perform();
	//			asd++;
	//		}
	//		StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//		model.setScreenName(screenName);
	//		model.setElementsId(elementsId);
	//		model.setStringsOfScreen(stringInfo);
	//		stringsOfScreenToExcel.add(model);
	//	}
	//
	//	@Test (priority=1)
	//	public void Initial_Setup_English_Language_String_Test() throws Exception {
	//		System.out.println("----------- Started 32");
	//		ArrayList<String> screenName = new ArrayList<String>();
	//		ArrayList<String> elementsId = new ArrayList<String>();
	//		ArrayList<String> stringInfo = new ArrayList<String>();
	//		MobileElement setSYSLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/recycler_view\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+intialLanguageListStored.get(systemLanguageIndex)+"\"));"));
	//		setSYSLanguage.click();
	//		driver.findElementById("text_okay").click();
	//		Thread.sleep(20000);
	//		try {
	//			screenName.add("Mobile_Data");
	//			elementsId.add("toolbar_title");
	//			stringInfo.add(support.getStringByIdXpath("toolbar_title", false, -1));
	//		} catch (Exception e) {
	//			// TODO: handle exception
	//		}
	//		Thread.sleep(25000);
	//		driver.findElementById("toolbar_title").click();
	//		try {
	//			screenName.add("Mobile_Data");
	//			elementsId.add("tv_fb_mobile_network");
	//			stringInfo.add(driver.findElementById("tv_fb_mobile_network").getText());
	//		} catch (Exception e) {
	//			// TODO: handle exception
	//		}
	//		Thread.sleep(25000);
	//		driver.findElementById("toolbar_title").click();
	//		Thread.sleep(15000);
	//
	//		for (int i = 104; i <=107; i++) {
	//			try {
	//				screenName.add(ScreenNameInExcel.get(i));
	//				elementsId.add(ElementsIdInExcel.get(i));
	//				stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1));
	//				System.out.println(support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1));
	//			} catch (Exception e) {
	//				// TODO: handle exception
	//			}
	//		}
	//		StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//		model.setScreenName(screenName);
	//		model.setElementsId(elementsId);
	//		model.setStringsOfScreen(stringInfo);
	//		stringsOfScreenToExcel.add(model);
	//		try {
	//			driver.findElementById("btn_next").click();
	//			Thread.sleep(4000);
	//		} catch (Exception e) {
	//			// TODO: handle exception
	//		}
	//	}
	//	@Test (priority=2)
	//	public void Initial_Wi_Fi_Screen_String_Test() throws Exception {
	//		System.out.println("Initial_Wi_Fi_Screen_String_Test ---- 33");
	//		Thread.sleep(4000);
	//		ArrayList<String> screenName = new ArrayList<String>();
	//		ArrayList<String> elementsId = new ArrayList<String>();
	//		ArrayList<String> stringInfo = new ArrayList<String>();
	//		//				try {
	//		try {
	//			screenName.add("WiFi");
	//			elementsId.add("com.sourcenext.pocketalk.settings:id/skipWifi");
	//			stringInfo.add(driver.findElementById("com.sourcenext.pocketalk.settings:id/skipWifi").getText());
	//			screenName.add("WiFi");
	//			elementsId.add("android:id/title");
	//			stringInfo.add(driver.findElementById("android:id/title").getText());
	//		} catch (Exception e) {
	//			// TODO: handle exception
	//		}
	//		WiFi_POM wifi = new WiFi_POM(driver);
	//		ArrayList<String> a = wifi.getWiFiStrings().get(0);
	//		ArrayList<String> b = wifi.getWiFiStrings().get(1);
	//		ArrayList<String> c = wifi.getWiFiStrings().get(2);
	//		StringsOfScreenModel model = new StringsOfScreenModel(a,b,c);
	//		model.setScreenName(a);
	//		model.setElementsId(b);
	//		model.setStringsOfScreen(c);
	//		stringsOfScreenToExcel.add(model);
	//		StringsOfScreenModel mdl = new StringsOfScreenModel(screenName,elementsId,stringInfo);
	//		mdl.setScreenName(screenName);
	//		mdl.setElementsId(elementsId);
	//		mdl.setStringsOfScreen(stringInfo);
	//		stringsOfScreenToExcel.add(mdl);
	//		driver.findElementById("com.sourcenext.pocketalk.settings:id/skipWifi").click();
	//		//				} catch (Exception e) {
	//		//					// TODO: handle exception
	//		//				}
	//	}
	//
	//	@Test (priority=3)
	//	public void Initial_Tutorial_Screen() throws Exception {
	//		Thread.sleep(4000);
	//		ArrayList<String> screenName = new ArrayList<String>();
	//		ArrayList<String> elementsId = new ArrayList<String>();
	//		ArrayList<String> stringInfo = new ArrayList<String>();
	//		System.out.println("---------Started 34");
	//		try {
	//			
	//			String sss = driver.findElementById("tv_tutorial_line_1").getText();
	//			mySpecialArray.add(sss);
	//			screenName.add("Tutorial");
	//			elementsId.add("tv_tutorial_line_1");
	//			stringInfo.add(sss);
	//			driver.findElementById("iv_close_tutorial").click();
	//			Thread.sleep(1000);
	//		} catch (Exception e) {
	//			// TODO: handle exception
	//		}
	//		
	//		StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//		model.setScreenName(screenName);
	//		model.setElementsId(elementsId);
	//		model.setStringsOfScreen(stringInfo);
	//		stringsOfScreenToExcel.add(model);
	////		try {
	////			List<String> captureImage = Arrays.asList("clear","com.sourcenext.pocketalks");
	////			Map<String, Object> captureImageCmd = ImmutableMap.of("command", "pm","args", captureImage);
	////			driver.executeScript("mobile: shell", captureImageCmd);
	////			Thread.sleep(2000);
	////		} catch (Exception e) {
	////			System.out.println("Cache Not Cleared Yet : "+e);
	////		}	
	//		Thread.sleep(1000);
	//		String filePath = System.getProperty("user.dir");
	//		this.writeActualStrings(filePath, "ActualStrings.xlsx",selectedSystemLanguage);
	//	}

	//		@Test (priority=35)
	//		public void Initial_Setup_Japanese_Language_String_Test() throws Exception {
	//			System.out.println("----------- Started 35");
	//			driver.startActivity(new Activity(packageName,packageName+".activity.MainActivity"));
	//			ArrayList<String> screenName = new ArrayList<String>();
	//			ArrayList<String> elementsId = new ArrayList<String>();
	//			ArrayList<String> stringInfo = new ArrayList<String>();
	//
	//			MobileElement setSYSLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/recycler_view\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+intialLanguageListStored.get(0)+"\"));"));
	//			setSYSLanguage.click();
	//			driver.findElementById("text_okay").click();
	//			Thread.sleep(2000);
	//			try {
	//				screenName.add("Mobile_Data");
	//				elementsId.add("toolbar_title");
	//				stringInfo.add(support.getStringByIdXpath("toolbar_title", false, -1));
	//			} catch (Exception e) {
	//				// TODO: handle exception
	//			}
	//			Thread.sleep(25000);
	//			driver.findElementById("toolbar_title").click();
	//			try {
	//				screenName.add("Mobile_Data");
	//				elementsId.add("tv_fb_mobile_network");
	//				stringInfo.add(driver.findElementById("tv_fb_mobile_network").getText());
	//			} catch (Exception e) {
	//				// TODO: handle exception
	//			}
	//			Thread.sleep(25000);
	//			driver.findElementById("toolbar_title").click();
	//			Thread.sleep(15000);
	//
	//			for (int i = 104; i <=107; i++) {
	//				try {
	//					screenName.add(ScreenNameInExcel.get(i));
	//					elementsId.add(ElementsIdInExcel.get(i));
	//					stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1));
	//					System.out.println(support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1));
	//				} catch (Exception e) {
	//					// TODO: handle exception
	//				}
	//			}
	//			StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//			model.setScreenName(screenName);
	//			model.setElementsId(elementsId);
	//			model.setStringsOfScreen(stringInfo);
	//			stringsOfScreenToExcel.add(model);
	//			try {
	//				driver.findElementById("btn_next").click();
	//				Thread.sleep(4000);
	//			} catch (Exception e) {
	//				// TODO: handle exception 
	//			}
	//		}
	//		@Test (priority=36)
	//		public void Initial_Wi_Fi_Screen_String_Test_Japanese() throws Exception {
	//			System.out.println("Initial_Wi_Fi_Screen_String_Test ---- 36");
	//			Thread.sleep(4000);
	//			ArrayList<String> screenName = new ArrayList<String>();
	//			ArrayList<String> elementsId = new ArrayList<String>();
	//			ArrayList<String> stringInfo = new ArrayList<String>();
	//			//				try {
	////			mySpecialArray.add("Wi-Fi Initial "+driver.findElementById("com.sourcenext.pocketalk.settings:id/skipWifi").getText());
	////			mySpecialArray.add("Wi-Fi Initial "+driver.findElementById("android:id/title").getText());
	//			
	//			try {
	//				screenName.add("WiFi");
	//				elementsId.add("com.sourcenext.pocketalk.settings:id/skipWifi");
	//				stringInfo.add(driver.findElementById("com.sourcenext.pocketalk.settings:id/skipWifi").getText());
	//				screenName.add("WiFi");
	//				elementsId.add("android:id/title");
	//				stringInfo.add(driver.findElementById("android:id/title").getText());
	//			} catch (Exception e) {
	//				// TODO: handle exception
	//			}
	//			WiFi_POM wifi = new WiFi_POM(driver);
	//			ArrayList<String> a = wifi.getWiFiStrings().get(0);
	//			ArrayList<String> b = wifi.getWiFiStrings().get(1);
	//			ArrayList<String> c = wifi.getWiFiStrings().get(2);
	//			StringsOfScreenModel model = new StringsOfScreenModel(a,b,c);
	//			model.setScreenName(a);
	//			model.setElementsId(b);
	//			model.setStringsOfScreen(c);
	//			stringsOfScreenToExcel.add(model);
	//			StringsOfScreenModel mdl = new StringsOfScreenModel(screenName,elementsId,stringInfo);
	//			mdl.setScreenName(screenName);
	//			mdl.setElementsId(elementsId);
	//			mdl.setStringsOfScreen(stringInfo);
	//			stringsOfScreenToExcel.add(mdl);
	//			driver.findElementById("com.sourcenext.pocketalk.settings:id/skipWifi").click();
	//			//				} catch (Exception e) {
	//			//					// TODO: handle exception
	//			//				}
	//		}
	//
	//		@Test (priority=37)
	//		public void Initial_Tutorial_Screen_Japanese() throws Exception {
	//			Thread.sleep(4000);
	//			ArrayList<String> screenName = new ArrayList<String>();
	//			ArrayList<String> elementsId = new ArrayList<String>();
	//			ArrayList<String> stringInfo = new ArrayList<String>();
	//			System.out.println("---------Started 37");
	//			try {
	//				
	//				String sss = driver.findElementById("tv_tutorial_line_1").getText();
	//				mySpecialArray.add(sss);
	//				screenName.add("Tutorial");
	//				elementsId.add("tv_tutorial_line_1");
	//				stringInfo.add(sss);
	//				
	//				driver.findElementById("iv_close_tutorial").click();
	//				Thread.sleep(1000);
	//			} catch (Exception e) {
	//				// TODO: handle exception
	//			}
	//			
	//			StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//			model.setScreenName(screenName);
	//			model.setElementsId(elementsId);
	//			model.setStringsOfScreen(stringInfo);
	//			stringsOfScreenToExcel.add(model);
	//			try {
	//				List<String> captureImage = Arrays.asList("clear","com.sourcenext.pocketalks");
	//				Map<String, Object> captureImageCmd = ImmutableMap.of("command", "pm","args", captureImage);
	//				driver.executeScript("mobile: shell", captureImageCmd);
	//				Thread.sleep(2000);
	//			} catch (Exception e) {
	//				System.out.println("Cache Not Cleared Yet : "+e);
	//			}	
	//			Thread.sleep(1000);
	//			
	//			System.out.println("---------Case 4--------------------------");
	//			for (int m = 0; m < stringsOfScreenToExcel.size(); m++) {
	//				for (int n = 0; n < stringsOfScreenToExcel.get(m).getStringsOfScreen().size(); n++) {
	//					System.out.println(stringsOfScreenToExcel.get(m).getStringsOfScreen().get(n));
	//				}
	//			}
	//			
	//						String filePath = System.getProperty("user.dir");
	//						this.writeActualStrings(filePath, "ActualStrings.xlsx","English");
	//			
	//		}



	//-------------------First Part From Home --------------------

	//			@Test (priority=0)
	//			public void SettingsMenuStringTest() throws Exception {
	//				try {
	//					System.out.println("1 "+selectedSystemLanguage);
	//					System.out.println("Connect wi-fi within 30 seconds.....");
	////					Thread.sleep(60000);
	////					this.selectedSystemLanguage = support.selectSystemLanguageOption("System Language","日本語で操作する");
	////					System.out.println("2 "+selectedSystemLanguage);
	////					this.selectedSystemLanguage = support.selectSystemLanguageOption("System Language","English");
	//					System.out.println("3 "+selectedSystemLanguage);
	//					
	//					driver.findElementById("btn_navigation_menu_left").click();
	//					Thread.sleep(2000);
	//					driver.findElementsById("tv_phrase_name").get(0).click();
	//					Thread.sleep(2000);
	//					ArrayList<String> screenNames = new ArrayList<String>();
	//					ArrayList<String> menuIds = new ArrayList<String>();
	//					ArrayList<String> menuNames = new ArrayList<String>();
	//					screenNames.add(menuSettings.getToolbarTitleString().get(0));
	//					menuIds.add(menuSettings.getToolbarTitleString().get(1));
	//					menuNames.add(menuSettings.getToolbarTitleString().get(2));
	//					screenNames.add(menuSettings.getMobileData().get(0));
	//					menuIds.add(menuSettings.getMobileData().get(1));
	//					menuNames.add(menuSettings.getMobileData().get(2));
	//					screenNames.add(menuSettings.getWiFiString().get(0));
	//					menuIds.add(menuSettings.getWiFiString().get(1));
	//					menuNames.add(menuSettings.getWiFiString().get(2));
	//					screenNames.add(menuSettings.getBluetoothString().get(0));
	//					menuIds.add(menuSettings.getBluetoothString().get(1));
	//					menuNames.add(menuSettings.getBluetoothString().get(2));
	//					ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
	//					if (selectedSystemLanguage.contains("Use in English")) {
	//						settingsMenuStored.clear();
	//						info = menuSettings.getSettingsMenuList("Reset");
	//					}else if (selectedSystemLanguage.contains("使用繁體中文操作")) {
	//						settingsMenuStored.clear();
	//						info = menuSettings.getSettingsMenuList("重設");
	//					}else if (selectedSystemLanguage.contains("Operar em português")) {
	//						settingsMenuStored.clear();
	//						info = menuSettings.getSettingsMenuList("Reinicializar");
	//					}else if (selectedSystemLanguage.contains("Работать на русском")) {
	//						settingsMenuStored.clear();
	//						info = menuSettings.getSettingsMenuList("Сбросить");
	//					}else if (selectedSystemLanguage.contains("ใช้งานด้วยภาษาไทย")) {
	//						settingsMenuStored.clear();
	//						info = menuSettings.getSettingsMenuList("รีเซ็ต");
	//					}else if (selectedSystemLanguage.contains("Operasi Dalam Bahasa Melayu")) {
	//						settingsMenuStored.clear();
	//						info = menuSettings.getSettingsMenuList("Set Semula");
	//					}else {
	//						settingsMenuStored.clear();
	//						info = menuSettings.getSettingsMenuList("Reset");
	//						System.out.println("System Language Not Stored");
	//					}
	//					screenNames.addAll(info.get(0));
	//					menuIds.addAll(info.get(1));
	//					menuNames.addAll(info.get(2));
	////					settingsMenuStored.addAll(info.get(2));
	//					
	//					StringsOfScreenModel strModel =  new StringsOfScreenModel(screenNames, menuIds,menuNames);
	//					strModel.setScreenName(screenNames);
	//					strModel.setElementsId(menuIds);
	//					strModel.setStringsOfScreen(menuNames);
	//					stringsOfScreenToExcel.add(strModel);
	//					System.out.println("Total Menu Count"+settingsMenuStored.size());
	//					support.backToHomeScreen();	
	//				} catch (Exception e) {
	//					// TODO: handle exception
	//				}	
	//			}

	//			@Test (priority=5)
	//			public void MainMenuStringTest() throws Exception {
	//				try {
	//					driver.findElementById("btn_navigation_menu_left").click();
	//					Thread.sleep(1000);
	//					ArrayList<ArrayList<String>> mainMenuInfo = mainMenu.getMainMenuList();
	//					StringsOfScreenModel mainMenuString =  new StringsOfScreenModel(mainMenuInfo.get(0), mainMenuInfo.get(1),mainMenuInfo.get(2));
	//					mainMenuString.setScreenName(mainMenuInfo.get(0));
	//					mainMenuString.setElementsId(mainMenuInfo.get(1));
	//					mainMenuString.setStringsOfScreen(mainMenuInfo.get(2));
	//					stringsOfScreenToExcel.add(mainMenuString);
	//					support.backToHomeScreen();
	//				} catch (Exception e) {
	//					support.backToHomeScreen();
	//				}
	//				
	//			}
	//	
	//				@Test (priority=6)
	//				public void Menu_Mobile_Data_Screen_String_Test() throws Exception {
	//					try {
	//						driver.findElementById("btn_navigation_menu_left").click();
	//						Thread.sleep(1000);
	//						driver.findElementsById("tv_phrase_name").get(0).click();
	//						Thread.sleep(2000);
	//						menuSettings.tapOnMobileDataText();
	//						Thread.sleep(5000);
	//						ArrayList<ArrayList<String>> scr = mobileData.getMobileDataStrings();
	//						StringsOfScreenModel model = new StringsOfScreenModel(scr.get(0), scr.get(1), scr.get(2));
	//						model.setScreenName(scr.get(0));
	//						model.setElementsId(scr.get(1));
	//						model.setStringsOfScreen(scr.get(2));
	//						stringsOfScreenToExcel.add(model);
	//						support.backToHomeScreen();
	//					} catch (Exception e) {
	//						support.backToHomeScreen();
	//					}
	//				}
	//				@Test (priority=7)
	//				public void Menu_Wi_Fi_Screen_String_Test() throws Exception {
	//					try {
	//						driver.findElementById("btn_navigation_menu_left").click();
	//						Thread.sleep(1000);
	//						driver.findElementsById("tv_phrase_name").get(0).click();
	//						Thread.sleep(2000);
	//						menuSettings.tapOnWiFiText();
	//						Thread.sleep(5000);
	//						WiFi_POM wifi = new WiFi_POM(driver);
	//						ArrayList<ArrayList<String>> asdf= wifi.getWiFiStrings();
	//						ArrayList<String> a = asdf.get(0);
	//						ArrayList<String> b = asdf.get(1);
	//						ArrayList<String> c = asdf.get(2);
	//						StringsOfScreenModel model = new StringsOfScreenModel(a,b,c);
	//						model.setScreenName(a);
	//						model.setElementsId(b);
	//						model.setStringsOfScreen(c);
	//						stringsOfScreenToExcel.add(model);
	//										ArrayList<String> screenName = new ArrayList<String>();
	//										ArrayList<String> elementsId = new ArrayList<String>();
	//										ArrayList<String> stringInfo = new ArrayList<String>();
	//										screenName.add("WiFi");
	//										elementsId.add("android:id/title");
	//										stringInfo.add(driver.findElementById("android:id/title").getText());
	//										StringsOfScreenModel mdl = new StringsOfScreenModel(screenName,elementsId,stringInfo);
	//										mdl.setScreenName(screenName);
	//										mdl.setElementsId(elementsId);
	//										mdl.setStringsOfScreen(stringInfo);
	//										stringsOfScreenToExcel.add(mdl);
	//						support.backToHomeScreen();
	//					} catch (Exception e) {
	//						support.backToHomeScreen();
	//					}
	//					
	//				}
	//		
	//				@Test (priority=8)
	//				public void Menu_Bluetooth_Screen_String_Test() throws Exception {
	//					try {
	//						driver.findElementById("btn_navigation_menu_left").click();
	//						Thread.sleep(1000);
	//						driver.findElementsById("tv_phrase_name").get(0).click();
	//						Thread.sleep(2000);
	//						menuSettings.tapOnBluetoothText();
	//						Thread.sleep(1000);
	//						Bluetooth_POM bluetooth = new Bluetooth_POM(driver);
	//						ArrayList<String> a = new ArrayList<String>();
	//						a.addAll(bluetooth.getBluetoothStrings().get(0));
	//						ArrayList<String> b = new ArrayList<String>();
	//						b.addAll(bluetooth.getBluetoothStrings().get(1));
	//						ArrayList<String> c = new ArrayList<String>();
	//						c.addAll(bluetooth.getBluetoothStrings().get(2));
	//						StringsOfScreenModel model = new StringsOfScreenModel(a,b,c);
	//						model.setScreenName(a);
	//						model.setElementsId(b);
	//						model.setStringsOfScreen(c);
	//						stringsOfScreenToExcel.add(model);
	//						support.backToHomeScreen();
	//					} catch (Exception e) {
	//						support.backToHomeScreen();
	//					}
	//				}
	//		
	//				@Test (priority=9)
	//				public void Menu_Brightness_Screen_String_Test() throws Exception {
	//					try {
	//						driver.findElementById("btn_navigation_menu_left").click();
	//						Thread.sleep(1000);
	//						driver.findElementsById("tv_phrase_name").get(0).click();
	//						Thread.sleep(2000);
	//						System.out.println(settingsMenuStored.get(0));
	//						support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(0));
	//						ArrayList<String> screenName = new ArrayList<String>();
	//						ArrayList<String> elementsId = new ArrayList<String>();
	//						ArrayList<String> strings = new ArrayList<String>();
	//						screenName.add("Brightness");
	//						screenName.add("Brightness");
	//						elementsId.add("android:id/alertTitle");
	//						elementsId.add("android:id/button2");
	//						strings.add(driver.findElementById("android:id/alertTitle").getText());
	//						strings.add(driver.findElementById("android:id/button2").getText());
	//						
	//						StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, strings);
	//						model.setScreenName(screenName);
	//						model.setElementsId(elementsId);
	//						model.setStringsOfScreen(strings);
	//						stringsOfScreenToExcel.add(model);
	//						support.backToHomeScreen();
	//					} catch (Exception e) {
	//						support.backToHomeScreen();
	//					}
	//					
	//				}
	//		
	//				@Test (priority=10)
	//				public void Menu_Text_Size_Screen_String_Test() throws Exception {
	//					try {
	//						driver.findElementById("btn_navigation_menu_left").click();
	//						Thread.sleep(1000);
	//						driver.findElementsById("tv_phrase_name").get(0).click();
	//						Thread.sleep(2000);
	//						support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(1));
	//						ArrayList<String> screenName = new ArrayList<String>();
	//						ArrayList<String> elementsId = new ArrayList<String>();
	//						ArrayList<String> stringInfo = new ArrayList<String>();
	//						
	//						for (int i = 0; i <= 1; i++) {
	//							screenName.add(ScreenNameInExcel.get(i));
	//							elementsId.add(ElementsIdInExcel.get(i));
	//							stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1));
	//						}
	//						try {
	//							List<MobileElement> elm = driver.findElementsByClassName("android.widget.CheckedTextView");
	//							for (int j = 0; j < elm.size(); j++) {
	//								screenName.add("Text Size");
	//								elementsId.add("android:id/text1");
	//								stringInfo.add(elm.get(j).getText());
	//								System.out.println("Text Size : "+elm.get(j).getText());
	//							}
	//						} catch (Exception e) {
	//							// TODO: handle exception
	//						}
	//						StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//						model.setScreenName(screenName);
	//						model.setElementsId(elementsId);
	//						model.setStringsOfScreen(stringInfo);
	//						stringsOfScreenToExcel.add(model);
	//						support.backToHomeScreen();
	//					} catch (Exception e) {
	//						support.backToHomeScreen();
	//					}
	//				}
	//		
	//				@Test (priority=11)
	//				public void Menu_Software_Update_String_Test() throws Exception {
	//					try {
	//						driver.findElementById("btn_navigation_menu_left").click();
	//						Thread.sleep(1000);
	//						driver.findElementsById("tv_phrase_name").get(0).click();
	//						Thread.sleep(2000);
	//						support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(2));
	//						Thread.sleep(10000);
	//						ArrayList<String> screenName = new ArrayList<String>();
	//						ArrayList<String> elementsId = new ArrayList<String>();
	//						ArrayList<String> stringInfo = new ArrayList<String>();
	//						for (int k = 4; k <= 6; k++) {
	//							screenName.add(ScreenNameInExcel.get(k));
	//							elementsId.add(ElementsIdInExcel.get(k));
	//							stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(k), false, -1));
	//							System.out.println(support.getStringByIdXpath(ElementsIdInExcel.get(k), false, -1));
	//						}
	//							StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//							model.setScreenName(screenName);
	//							model.setElementsId(elementsId);
	//							model.setStringsOfScreen(stringInfo);
	//							stringsOfScreenToExcel.add(model);
	//							support.backToHomeScreen();
	//					} catch (Exception e) {
	//						support.backToHomeScreen();
	//					}
	//					
	//					}
	//		
	//				@Test (priority=12)
	//				public void Menu_Shortcut_Screen_String_Test() throws Exception {
	//					try {
	//						driver.findElementById("btn_navigation_menu_left").click();
	//						Thread.sleep(1000);
	//						driver.findElementsById("tv_phrase_name").get(0).click();
	//						Thread.sleep(2000);
	//						support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(3));
	//						ArrayList<String> screenName = new ArrayList<String>();
	//						ArrayList<String> elementsId = new ArrayList<String>();
	//						ArrayList<String> stringInfo = new ArrayList<String>();
	//						for (int k = 2; k <= 3; k++) {
	//							screenName.add(ScreenNameInExcel.get(k));
	//							elementsId.add(ElementsIdInExcel.get(k));
	//							stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(k), false, -1));
	//						}
	//						
	//						ArrayList<String> temp = new ArrayList<String>();
	//						int asd = 1;  
	//					    while (asd<=2) {
	//					    	java.util.List<MobileElement> items = driver.findElementsById("item_name");
	//					    	for (int i = 0; i < items.size(); i++) {
	//								try {
	//									if(!items.get(i).getText().isEmpty()) {
	//										if (temp.contains(items.get(i).getText())) {
	//											System.out.println("Duplicate Value!");
	//										}else {
	//											temp.add(items.get(i).getText());
	//										}
	//									}else {
	//										System.out.println("Unable to get the text !!");
	//									}
	//								} catch (Exception e) {
	//									System.out.println(e);
	//								}
	//							}
	//				    	    new TouchAction(driver).press(PointOption.point(support.getMaxPositionOfDisplay("bottom"))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(support.getMaxPositionOfDisplay("TOP"))).release().perform();
	//				    	   asd++;
	//					    }		
	//							if (temp.size()>0) {
	//								for (int j = 0; j < temp.size(); j++) {
	//									screenName.add("Shortcut");
	//									elementsId.add("item_name");
	//									stringInfo.add(temp.get(j));
	//								}
	//							}
	//							StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//							model.setScreenName(screenName);
	//							model.setElementsId(elementsId);
	//							model.setStringsOfScreen(stringInfo);
	//							stringsOfScreenToExcel.add(model);
	//							support.backToHomeScreen();
	//					} catch (Exception e) {
	//						support.backToHomeScreen();
	//					}
	//					
	//					}
	//					
	//		
	//				@Test (priority=13)
	//				public void Menu_Lock_Screen_Settings_String_Test() throws Exception {
	//					try {
	//						driver.findElementById("btn_navigation_menu_left").click();
	//						Thread.sleep(1000);
	//						driver.findElementsById("tv_phrase_name").get(0).click();
	//						Thread.sleep(2000);
	//						support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(4));
	//						ArrayList<String> screenName = new ArrayList<String>();
	//						ArrayList<String> elementsId = new ArrayList<String>();
	//						ArrayList<String> stringInfo = new ArrayList<String>();
	//						String checked = driver.findElementById("checkbox_lock_screen_on_off").getAttribute("checked");
	//						System.out.println(checked);
	//						if (checked.contains("true")) {
	//							driver.findElementById("checkbox_lock_screen_on_off").click();
	//							Thread.sleep(1000);
	//						}else {
	//							System.out.println("Lock Screen OFF");
	//						}
	//						for (int i = 7; i <=9; i++) {
	//							screenName.add(ScreenNameInExcel.get(i));
	//							elementsId.add(ElementsIdInExcel.get(i));
	//							stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1));
	//						}
	//						driver.findElementById("checkbox_lock_screen_on_off").click();
	//						Thread.sleep(1000);
	//						for (int j = 10; j <=15; j++) {
	//							screenName.add(ScreenNameInExcel.get(j));
	//							elementsId.add(ElementsIdInExcel.get(j));
	//							stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(j), false, -1));
	//						}
	//						driver.findElementById("checkbox_display_recent_locations").click();
	//						Thread.sleep(500);
	//						screenName.add(ScreenNameInExcel.get(16));
	//						elementsId.add(ElementsIdInExcel.get(16));
	//						stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(16), false, -1));
	//				
	//						StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//						model.setScreenName(screenName);
	//						model.setElementsId(elementsId);
	//						model.setStringsOfScreen(stringInfo);
	//						stringsOfScreenToExcel.add(model);
	//						
	//						if (driver.findElementById("checkbox_lock_screen_on_off").getAttribute("checked").contains("true")) {
	//							driver.findElementById("checkbox_lock_screen_on_off").click();
	//							System.out.println("Leave Lock Screen OFF");
	//						}else {
	//							System.out.println("Leave Lock Screen OFF");
	//						}
	//						support.backToHomeScreen();
	//					} catch (Exception e) {
	//						support.backToHomeScreen();
	//					}
	//				}
	//		
	//				@Test (priority=14)
	//				public void Menu_Pocketalk_Center_String_Test() throws Exception {
	//					try {
	//					driver.findElementById("btn_navigation_menu_left").click();
	//					Thread.sleep(1000);
	//					driver.findElementsById("tv_phrase_name").get(0).click();
	//					Thread.sleep(2000);
	//					support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(5));
	//					Thread.sleep(10000);
	//					ArrayList<String> screenName = new ArrayList<String>();
	//					ArrayList<String> elementsId = new ArrayList<String>();
	//					ArrayList<String> stringInfo = new ArrayList<String>();
	//					ArrayList<String> temp = new ArrayList<String>();
	//					ArrayList<String> temp2 = new ArrayList<String>();
	//					
	//					try {
	//						temp.add(support.getStringByIdXpath("tv_a2_title", false, -1));
	//						temp2.add("tv_a2_title");
	//						System.out.println("Pocketalk Center Registered : "+support.getStringByIdXpath("tv_a2_title", false, -1));
	//					} catch (Exception e) {
	//						int asd=1;
	//						while (asd<=2) {
	//							for (int i = 17; i <=21; i++) {
	//								try {
	//									String s = support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1);
	//									if (!temp.contains(s) && !s.isEmpty()) {
	//										temp.add(s);
	//										temp2.add(ElementsIdInExcel.get(i));
	//									}else {
	//										System.out.println("String empty or available in the temp array");
	//									}
	//								} catch (Exception m) {
	//									System.out.println("get text exception...");
	//								}
	//							}
	//				    	    new TouchAction(driver).press(PointOption.point(support.getMaxPositionOfDisplay("bottom"))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(support.getMaxPositionOfDisplay("TOP"))).release().perform();
	//					    	asd++;
	//						}
	//					}
	//					
	//					for (int j = 0; j < temp.size(); j++) {
	//						screenName.add("Pocketalk_Center");
	//						elementsId.add(temp2.get(j));
	//						stringInfo.add(temp.get(j));
	//					}
	//					StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//					model.setScreenName(screenName);
	//					model.setElementsId(elementsId);
	//					model.setStringsOfScreen(stringInfo);
	//					stringsOfScreenToExcel.add(model);
	//					support.backToHomeScreen();
	//					} catch (Exception e) {
	//						support.errorScreenshot("Pocketalk_Center");
	//						System.out.println(e);
	//						support.backToHomeScreen();
	//					}
	//				}
	//		
	//				@Test (priority=15)
	//				public void Menu_Notificaton_Seetings_String_Test() throws Exception {
	//					try {
	//						driver.findElementById("btn_navigation_menu_left").click();
	//						Thread.sleep(1000);
	//						driver.findElementsById("tv_phrase_name").get(0).click();
	//						Thread.sleep(2000);
	//						support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(6));
	//						ArrayList<String> screenName = new ArrayList<String>();
	//						ArrayList<String> elementsId = new ArrayList<String>();
	//						ArrayList<String> stringInfo = new ArrayList<String>();
	//						screenName.add("Notification_Settings");
	//						elementsId.add("toolbar_title");
	//						stringInfo.add(support.getStringByIdXpath("toolbar_title", false, -1));
	//						for (int i = 0; i <=1; i++) {
	//							screenName.add("Notification_Settings");
	//							elementsId.add("text_view_notification");
	//							stringInfo.add(support.getStringByIdXpath("text_view_notification", false, i));
	//							elementsId.add("tv_notification_on_off_screen_state");
	//							stringInfo.add(support.getStringByIdXpath("tv_notification_on_off_screen_state", false, i));
	//						}
	//						driver.findElementsById("checkbox_notification").get(0).click();
	//						driver.findElementsById("checkbox_notification").get(1).click();
	//						for (int j = 0; j <=1; j++) {
	//							screenName.add("Notification_Settings");
	//							elementsId.add("tv_notification_on_off_screen_state");
	//							stringInfo.add(support.getStringByIdXpath("tv_notification_on_off_screen_state", false, j));
	//						}
	//						
	//						StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//						model.setScreenName(screenName);
	//						model.setElementsId(elementsId);
	//						model.setStringsOfScreen(stringInfo);
	//						stringsOfScreenToExcel.add(model);
	//						support.backToHomeScreen();
	//					} catch (Exception e) {
	//						support.backToHomeScreen();
	//					}
	//					
	//				}
	//		
	//				@Test (priority=16)
	//				public void Menu_Sleep_Seetings_String_Test() throws Exception {
	//					try {
	//						driver.findElementById("btn_navigation_menu_left").click();
	//						Thread.sleep(1000);
	//						driver.findElementsById("tv_phrase_name").get(0).click();
	//						Thread.sleep(2000);
	//						support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(7));
	//						ArrayList<String> screenName = new ArrayList<String>();
	//						ArrayList<String> elementsId = new ArrayList<String>();
	//						ArrayList<String> stringInfo = new ArrayList<String>();
	//						for (int i = 29; i <=30; i++) {
	//							screenName.add(ScreenNameInExcel.get(i));
	//							elementsId.add(ElementsIdInExcel.get(i));
	//							stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1));
	//						}
	//						for (int j = 0; j < 5; j++) {
	//							screenName.add(ScreenNameInExcel.get(31));
	//							elementsId.add(ElementsIdInExcel.get(31));
	//							stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(31), false, j));
	//						}
	//						StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//						model.setScreenName(screenName);
	//						model.setElementsId(elementsId);
	//						model.setStringsOfScreen(stringInfo);
	//						stringsOfScreenToExcel.add(model);
	//						support.backToHomeScreen();
	//					} catch (Exception e) {
	//						support.backToHomeScreen();
	//					}
	//					
	//				}
	//		
	//			@Test (priority=17)
	//			public void Menu_System_Language_String_Test() throws Exception {
	//				try {
	//					driver.findElementById("btn_navigation_menu_left").click();
	//					Thread.sleep(1000);
	//					driver.findElementsById("tv_phrase_name").get(0).click();
	//					Thread.sleep(2000);
	//					support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(8));
	//					ArrayList<String> screenName = new ArrayList<String>();
	//					ArrayList<String> elementsId = new ArrayList<String>();
	//					ArrayList<String> stringInfo = new ArrayList<String>();
	//					screenName.add("System_Language");
	//					elementsId.add("toolbar_title");
	//					stringInfo.add(support.getStringByIdXpath("toolbar_title", false, -1));
	//					ArrayList<String> temp = new ArrayList<String>();
	//					int asd=1;
	//					while (asd<=3) {
	//						List<MobileElement> languages = driver.findElementsById("txt_lang_name");
	//						for (int i =0; i <languages.size(); i++) {
	//							try {
	//								String s = languages.get(i).getText();
	//								if (!temp.contains(s) && !s.isEmpty()) {
	//									temp.add(s);
	//									screenName.add("System_Language");
	//									elementsId.add("txt_lang_name");
	//									stringInfo.add(s);
	//								}else {
	//									System.out.println("String empty or available in the temp array");
	//								}
	//							} catch (Exception e) {
	//								System.out.println("get text exception...");
	//							}
	//						}
	//						new TouchAction(driver).press(PointOption.point(support.getMaxPositionOfDisplay("bottom"))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(support.getMaxPositionOfDisplay("TOP"))).release().perform();
	//						asd++;
	//					}
	//					StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//					model.setScreenName(screenName);
	//					model.setElementsId(elementsId);
	//					model.setStringsOfScreen(stringInfo);
	//					stringsOfScreenToExcel.add(model);
	//					support.backToHomeScreen();
	//				} catch (Exception e) {
	//					support.backToHomeScreen();
	//				}
	//				
	//			}
	//		
	//				@Test (priority=18)
	//				public void Menu_Region_Of_Use_Screen_String_Test() throws Exception {
	//					try {
	//						driver.findElementById("btn_navigation_menu_left").click();
	//						Thread.sleep(1000);
	//						driver.findElementsById("tv_phrase_name").get(0).click();
	//						Thread.sleep(2000);
	//						support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(9));
	//						List<MobileElement> elements = driver.findElementsByXPath("//android.widget.LinearLayout/android.widget.LinearLayout");
	//						ArrayList<String> screenName = new ArrayList<String>();
	//						ArrayList<String> elementsId = new ArrayList<String>();
	//						ArrayList<String> stringInfo = new ArrayList<String>();
	//						screenName.add("Region_Of_Use");
	//						elementsId.add("android.widget.RadioButton");
	//						stringInfo.add(support.getStringByIdXpath("toolbar_title", false, -1));
	//						for (int i = 0; i < 2; i++) {
	//							screenName.add("Region_Of_Use");
	//							screenName.add("Region_Of_Use");
	//							elementsId.add("android.widget.RadioButton");
	//							elementsId.add("android.widget.TextView");
	//							stringInfo.add(elements.get(i).findElementByClassName("android.widget.RadioButton").getText());
	//							stringInfo.add(elements.get(i).findElementByClassName("android.widget.TextView").getText());
	//						}
	//						StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//						model.setScreenName(screenName);
	//						model.setElementsId(elementsId);
	//						model.setStringsOfScreen(stringInfo);
	//						stringsOfScreenToExcel.add(model);
	//						support.backToHomeScreen();
	//					} catch (Exception e) {
	//						support.backToHomeScreen();
	//					}
	//					
	//				}

	//				@Test (priority=19)
	//				public void Menu_Communication_Plan_String_Test() throws Exception {
	//					try {
	//					driver.findElementById("btn_navigation_menu_left").click();
	//					Thread.sleep(1000);
	//					driver.findElementsById("tv_phrase_name").get(0).click();
	//					Thread.sleep(2000);
	//					support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(10));
	//					Thread.sleep(10000);
	//					ArrayList<String> screenName = new ArrayList<String>();
	//					ArrayList<String> elementsId = new ArrayList<String>();
	//					ArrayList<String> stringInfo = new ArrayList<String>();
	//					for (int i = 55; i <= 56; i++) {
	//						screenName.add(ScreenNameInExcel.get(i));
	//						elementsId.add(ElementsIdInExcel.get(i));
	//						stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1));
	//					}
	//					StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//					model.setScreenName(screenName);
	//					model.setElementsId(elementsId);
	//					model.setStringsOfScreen(stringInfo);
	//					stringsOfScreenToExcel.add(model);
	//					support.backToHomeScreen();
	//					} catch (Exception e) {
	//						support.backToHomeScreen();
	//					}
	//				}
	//			
	//				@Test (priority=20)
	//				public void Menu_Device_and_License_Information_String_Test() throws Exception {
	//					try {
	//					driver.findElementById("btn_navigation_menu_left").click();
	//					Thread.sleep(1000);
	//					driver.findElementsById("tv_phrase_name").get(0).click();
	//					Thread.sleep(2000);
	//					support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(11));
	//					Thread.sleep(2000);
	//					ArrayList<String> screenName = new ArrayList<String>();
	//					ArrayList<String> elementsId = new ArrayList<String>();
	//					ArrayList<String> stringInfo = new ArrayList<String>();
	//					for (int i = 57; i <= 61; i++) {
	//						screenName.add(ScreenNameInExcel.get(i));
	//						elementsId.add(ElementsIdInExcel.get(i));
	//						stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1));
	//					}
	//					StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//					model.setScreenName(screenName);
	//					model.setElementsId(elementsId);
	//					model.setStringsOfScreen(stringInfo);
	//					stringsOfScreenToExcel.add(model);
	//					support.backToHomeScreen();
	//					} catch (Exception e) {
	////						support.backToHomeScreen();
	//					}
	//				}
	//		
	//				@Test (priority=21)
	//				public void Menu_Device_Info_String_Test() throws Exception {
	//					try {
	//					driver.findElementById("btn_navigation_menu_left").click();
	//					Thread.sleep(1000);
	//					driver.findElementsById("tv_phrase_name").get(0).click();
	//					Thread.sleep(2000);
	//					support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(11));
	//					Thread.sleep(2000);
	//					driver.findElementById("tv_device_info").click();
	//					Thread.sleep(5000);
	//					
	//					ArrayList<String> screenName = new ArrayList<String>();
	//					ArrayList<String> elementId = new ArrayList<String>();
	//					ArrayList<String> stringInfo = new ArrayList<String>();
	//					screenName.add("Device_Information");
	//					elementId.add("NO_NEED");
	//					stringInfo.add(support.getStringByIdXpath("toolbar_title", false, -1));
	//					int asd = 1;
	//					ArrayList<String> temp = new ArrayList<String>();
	//					while (asd<=2) {
	//						List<MobileElement> elements = driver.findElementsByXPath("//android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView");
	//						for (int i = 0; i < elements.size(); i++) {
	//							try {
	//								String s = elements.get(i).getText();
	//								if (!s.isEmpty() && !temp.contains(s)) {
	//									temp.add(s);
	//									screenName.add("Device_Information");
	//									elementId.add("NO_NEED");
	//									stringInfo.add(elements.get(i).getText());
	//								}
	//							} catch (Exception e) {
	//								// TODO: handle exception
	//							}
	//						}
	//						new TouchAction(driver).press(PointOption.point(support.getMaxPositionOfDisplay("bottom"))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(support.getMaxPositionOfDisplay("TOP"))).release().perform();
	//						asd++;
	//					}
	//					StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//					model.setScreenName(screenName);
	//					model.setElementsId(elementId);
	//					model.setStringsOfScreen(stringInfo);
	//					stringsOfScreenToExcel.add(model);
	//					support.backToHomeScreen();
	//					System.out.println("--------Case 21--------");
	//					for (int m = 0; m < stringsOfScreenToExcel.size(); m++) {
	//						for (int n = 0; n < stringsOfScreenToExcel.get(m).getStringsOfScreen().size(); n++) {
	//							System.out.println(stringsOfScreenToExcel.get(m).getStringsOfScreen().get(n));
	//						}
	//					}
	//					} catch (Exception e) {
	//						support.backToHomeScreen();
	//					}
	//				}
	//		
	//			@Test (priority=22)
	//			public void Menu_Support_String_Test() throws Exception {
	//				try {
	//				driver.findElementById("btn_navigation_menu_left").click();
	//				Thread.sleep(1000);
	//				driver.findElementsById("tv_phrase_name").get(0).click();
	//				Thread.sleep(2000);
	//				support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(12));
	//				ArrayList<String> screenName = new ArrayList<String>();
	//				ArrayList<String> elementId = new ArrayList<String>();
	//				ArrayList<String> stringInfo = new ArrayList<String>();
	//				for (int i = 73; i <=75; i++) {
	//					screenName.add(ScreenNameInExcel.get(i));
	//					elementId.add(ElementsIdInExcel.get(i));
	//					stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1));
	//				}
	//				StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//				model.setScreenName(screenName);
	//				model.setElementsId(elementId);
	//				model.setStringsOfScreen(stringInfo);
	//				stringsOfScreenToExcel.add(model);
	//				support.backToHomeScreen();
	//				} catch (Exception e) {
	//					support.backToHomeScreen();
	//				}
	//			}
	//	
	//		@Test (priority=23)
	//		public void Menu_Reset_String_Test() throws Exception {
	//			try {
	//			driver.findElementById("btn_navigation_menu_left").click();
	//			Thread.sleep(1000);
	//			driver.findElementsById("tv_phrase_name").get(0).click();
	//			Thread.sleep(2000);
	//			support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(13));
	//			ArrayList<String> screenName = new ArrayList<String>();
	//			ArrayList<String> elementId = new ArrayList<String>();
	//			ArrayList<String> stringInfo = new ArrayList<String>();
	//			screenName.add("Reset");
	//			elementId.add("toolbar_title");
	//			stringInfo.add(support.getStringByIdXpath("toolbar_title", false, -1));
	//			List<MobileElement> resetMenus = driver.findElementById("main_content").findElementByClassName("android.widget.LinearLayout").findElementsByClassName("android.widget.TextView");
	//			for (int i = 0; i <resetMenus.size(); i++) {
	//				screenName.add("Reset");
	//				elementId.add("NO_NEED");
	//				stringInfo.add(resetMenus.get(i).getText());
	//				resetMenuStored.add(resetMenus.get(i).getText());
	//			}
	//			StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//			model.setScreenName(screenName);
	//			model.setElementsId(elementId);
	//			model.setStringsOfScreen(stringInfo);
	//			stringsOfScreenToExcel.add(model);
	//			support.backToHomeScreen();
	//			} catch (Exception e) {
	//				support.backToHomeScreen();
	//			}
	//			String filePath = System.getProperty("user.dir");
	//			this.writeActualStrings(filePath, "StringResults_Last.xlsx",selectedSystemLanguage);
	//		}

	//		@SuppressWarnings("rawtypes")
	//		@Test (priority=24)
	//		public void Menu_Reset_Warnig_String_Test() throws Exception {
	//			try {
	//			driver.findElementById("btn_navigation_menu_left").click();
	//			Thread.sleep(1000);
	//			driver.findElementsById("tv_phrase_name").get(0).click();
	//			Thread.sleep(2000);
	//			support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(13));
	//			ArrayList<String> screenName = new ArrayList<String>();
	//			ArrayList<String> elementId = new ArrayList<String>();
	//			ArrayList<String> stringInfo = new ArrayList<String>();
	//			List<MobileElement> resetMenus = driver.findElementsByXPath("//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView");
	//			ArrayList<MainAndSubStringStoreModel> temp = new ArrayList<MainAndSubStringStoreModel>();
	//			
	//			for (int i = 0; i < resetMenus.size(); i++) {
	//				String mainS = resetMenus.get(i).getText();
	//				resetMenus.get(i).click();
	//				Thread.sleep(2000);
	//				
	//				try {
	//					String ss = support.getStringByIdXpath("android:id/button2", false, -1);
	//					if (ss.isEmpty()) {
	//						System.out.println("Inside deletion activity !!!");
	//						if (driver.findElementById("checkbox_auto_delete_status").getAttribute("checked").contains("false")) {
	//							try {
	//								screenName.add("Auto_Deletion");
	//								elementId.add("txt_auto_delete_status");
	//								stringInfo.add(support.getStringByIdXpath("txt_auto_delete_status", false, -1));
	//							} catch (Exception e) {
	//								// TODO: handle exception
	//							}
	//							driver.findElementById("checkbox_auto_delete_status").click();
	//							Thread.sleep(2000);
	//						}else {
	//							System.out.println("Auto Deletion Is ON");
	//						}
	//						
	//						ArrayList<String> autodelete = new ArrayList<String>();
	//						if (driver.findElementById("checkbox_auto_delete_status").getAttribute("checked").contains("true")) {
	//							System.out.println("Auto Deletion Is ON 222222 ");
	//							for (int j = 91; j <=97; j++) {
	//								if (j>94) {
	//									new TouchAction(driver).press(PointOption.point(support.getMaxPositionOfDisplay("BOTTOM"))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(support.getMaxPositionOfDisplay("TOP"))).release().perform(); 
	//									Thread.sleep(1000);
	//									autodelete.add(support.getStringByIdXpath(ElementsIdInExcel.get(j), false, -1));
	//								}else {
	//									autodelete.add(support.getStringByIdXpath(ElementsIdInExcel.get(j), false, -1));
	//								}
	//							}
	//							MainAndSubStringStoreModel mdl2 = new MainAndSubStringStoreModel(mainS, autodelete);
	//							mdl2.setMainString(mainS);
	//							mdl2.setSubStringList(autodelete);
	//							temp.add(mdl2);
	//						}else {
	//							System.out.println("Auto Delete Button Is OFF");
	//						}
	//						driver.findElementById("iv_top_back").click();
	//						Thread.sleep(2000);
	//						resetMenus = driver.findElementsByXPath("//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView");
	//					}else {
	//						ArrayList<String> tmp2 = new ArrayList<String>();
	//						for (int k = 82; k <=84; k++) {
	//							try {
	//								String s = support.getStringByIdXpath(ElementsIdInExcel.get(k), false, -1);
	//								if (!tmp2.contains(s) && !s.isEmpty()) {
	//									tmp2.add(s);
	//								}else {
	//									System.out.println("String empty or available in the temp array");
	//								}
	//							} catch (Exception e) {
	//								System.out.println("get text exception...");
	//							}
	//						}
	//						MainAndSubStringStoreModel mdl = new MainAndSubStringStoreModel(mainS, tmp2);
	//						mdl.setMainString(mainS);
	//						mdl.setSubStringList(tmp2);
	//						temp.add(mdl);
	//						try {
	//							driver.findElementById("android:id/button2").click();
	//							Thread.sleep(1000);
	//						} catch (Exception e) {
	//							// TODO: handle exception
	//						}
	//					}
	//				} catch (Exception e) {
	//					// TODO: handle exception
	//				}			
	//			}
	//			System.out.println("===== Reset All String ======= ");
	//			for (int kk = 0; kk < temp.size(); kk++) {
	////				System.out.println(temp.get(kk).getMainString());
	//				for (int l = 0; l < temp.get(kk).getSubStringList().size(); l++) {
	//					screenName.add(temp.get(kk).getMainString());
	//					elementId.add("NO_NEED");
	//					stringInfo.add(temp.get(kk).getSubStringList().get(l));
	//					System.out.println(temp.get(kk).getSubStringList().get(l));
	//				}
	//			}
	//			System.out.println("===== Reset All String ======= ");
	//			StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//			model.setScreenName(screenName);
	//			model.setElementsId(elementId);
	//			model.setStringsOfScreen(stringInfo);
	//			stringsOfScreenToExcel.add(model);
	//			support.backToHomeScreen();
	//			} catch (Exception e) {
	//				support.backToHomeScreen();
	//			}
	//		}

	//		@Test (priority=25)
	//		public void Medal_Badge_String_Test() throws Exception {
	//			try {
	//			driver.findElementById("btn_navigation_menu_left").click();
	//			Thread.sleep(1000);
	//			driver.findElementsById("tv_phrase_name").get(4).click();
	//			Thread.sleep(2000);
	//			ArrayList<String> screenName = new ArrayList<String>();
	//			ArrayList<String> elementId = new ArrayList<String>();
	//			ArrayList<String> stringInfo = new ArrayList<String>();
	//			ArrayList<String> temp = new ArrayList<String>();
	//			Point lastElementLocation = null;
	//			while (temp.size()<10) {
	//				System.out.println("tmp size "+temp.size());
	//				List<MobileElement> medals = driver.findElementById("recycler_view").findElementsByClassName("android.widget.LinearLayout");
	//				for (int i = 0; i < medals.size(); i++) {
	//					try {
	//						MobileElement ml = medals.get(i).findElementById("badge_name_tv");
	//						if (!temp.contains(ml.getText())) {
	//							stringInfo.add(ml.getText());
	//							temp.add(ml.getText());
	//							lastElementLocation = ml.getLocation();
	//							System.out.println("temp ["+i+"] "+ml.getText());
	//						}else {
	//	//						System.out.println("Duplicate Value");
	//						}
	//					} catch (Exception e) {
	//						System.out.println("Unable To Get Badge Namme");
	//					}
	//				}
	//				new TouchAction(driver).press(PointOption.point(lastElementLocation)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(menuSettings.getTargetPositionOfToolbar("toolbar").getX(),menuSettings.getTargetPositionOfToolbar("toolbar").getY()+20)).release().perform();
	//			}
	//			
	//			StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//			model.setScreenName(screenName);
	//			model.setElementsId(elementId);
	//			model.setStringsOfScreen(stringInfo);
	//			stringsOfScreenToExcel.add(model);
	//			support.backToHomeScreen();
	//			} catch (Exception e) {
	//				support.backToHomeScreen();
	//			}
	//		}

	//		@Test (priority=26)
	//		public void Medals_ALL_String_Test() throws Exception {
	//			try {
	//			driver.findElementById("btn_navigation_menu_left").click();
	//			Thread.sleep(1000);
	//			driver.findElementsById("tv_phrase_name").get(4).click();
	//			Thread.sleep(2000);
	//			ArrayList<String> screenName = new ArrayList<String>();
	//			ArrayList<String> elementId = new ArrayList<String>();
	//			ArrayList<String> stringInfo = new ArrayList<String>();
	//			Point lastElementLocation = null;
	//			ArrayList<String> items = new ArrayList<String>();
	//			ArrayList<MainAndSubStringStoreModel> finalDataOfMedal = new ArrayList<MainAndSubStringStoreModel>();
	//			int asd = 0;
	//			while (asd<5) {
	////				System.out.println("tmp size "+items.size());
	//				List<MobileElement> medals = driver.findElementById("recycler_view").findElementsByClassName("android.widget.LinearLayout");
	//				for (int i = 0; i < medals.size(); i++) {
	//					int sz = medals.get(i).getSize().getHeight();
	//					try {
	//						if (sz==103) {
	//							MobileElement ml = medals.get(i).findElementById("badge_name_tv");
	//							String mainMenu = "Empty";
	//							if (!items.contains(ml.getText())) {
	//							mainMenu = ml.getText();
	//							items.add(ml.getText());
	//							lastElementLocation = ml.getLocation();
	////							System.out.println("items ["+i+"] "+ml.getText());
	//							ml.click();
	//							Thread.sleep(2000);
	//							if (!driver.currentActivity().contains(".badge.activity.BadgeDetailsActivity")) {
	////								System.out.println("Details Activity Not Available !!");
	//								ArrayList<String> tmpDetails = new ArrayList<String>();
	//								tmpDetails.add("Not Achieved");
	//								MainAndSubStringStoreModel msl = new MainAndSubStringStoreModel(mainMenu, tmpDetails);
	//								msl.setMainString(mainMenu);
	//								msl.setSubStringList(tmpDetails);
	//								finalDataOfMedal.add(msl);
	//							}else {
	//								Thread.sleep(2000);
	//								//						System.out.println("driver.currentActivity()"+driver.currentActivity());
	//								ArrayList<String> tmpDetails = new ArrayList<String>();
	//								for (int j = 98; j <= 103; j++) {
	//									try {
	//										MobileElement dtls = driver.findElementById(ElementsIdInExcel.get(j));
	//										if (!tmpDetails.contains(dtls.getText())) {
	//											tmpDetails.add(dtls.getText());
	////											System.out.println("details values - "+dtls.getText());
	//										}else {
	////											System.out.println("Duplicate Details Elements!!");
	//										}
	//									} catch (Exception e) {
	//										// TODO: handle exception
	//									}
	//								}
	//								try {
	//									new TouchAction(driver).press(PointOption.point(support.getMaxPositionOfDisplay("BOTTOM"))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(support.getMaxPositionOfDisplay("TOP"))).release().perform();
	//									Thread.sleep(1000);
	//									List<MobileElement> newElements = driver.findElementById("badge_recycler_view").findElementsByClassName("android.widget.LinearLayout");
	//									System.out.println("New Elements Count"+newElements.size());
	//									if (newElements.size()>0) {
	//										System.out.println("----Multiple Badge-----");
	//										for (int rl = 0; rl < newElements.size(); rl++) {
	//											try {
	//												MobileElement m1 = newElements.get(rl).findElementById("badge_name_tv");
	//												//										MobileElement m2 = newElements.get(i).findElementById("badge_acquired_date_tv");
	//												if (!tmpDetails.contains(m1.getText())) {
	//													tmpDetails.add(m1.getText());
	//													System.out.println(m1.getText());
	//												}else {
	////													System.out.println("Duplicate Details Elements Recycler View !!");
	//												}
	//											} catch (Exception e) {
	//												// TODO: handle exception
	//											}									
	//										}
	//									}else {
	//										System.out.println("No Others Badge Found In Details");
	//									}
	//								} catch (Exception e) {
	//									// TODO: handle exception
	//								}
	//								driver.findElementById("iv_top_back").click();
	//								Thread.sleep(2000);
	//								medals = driver.findElementById("recycler_view").findElementsByClassName("android.widget.LinearLayout");
	//								for (int lo = 0; lo < tmpDetails.size(); lo++) {
	//									System.out.println("-------- Sub : "+tmpDetails.get(lo));
	//								}
	//								MainAndSubStringStoreModel msl = new MainAndSubStringStoreModel(mainMenu, tmpDetails);
	//								msl.setMainString(mainMenu);
	//								msl.setSubStringList(tmpDetails);
	//								finalDataOfMedal.add(msl);
	//							}
	//						}else {
	//							//						System.out.println("Duplicate Value");
	//						}
	//						}else {
	////							System.out.println("Medal Size not Matched");
	//						}
	//					} catch (Exception e) {
	////						System.out.println("Unable To Get Badge Namme");
	//					}
	//				}
	//				new TouchAction(driver).press(PointOption.point(lastElementLocation.getX(),lastElementLocation.getY())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(lastElementLocation.getX(),160)).release().perform();
	//				Thread.sleep(1000);
	//				asd++;
	//			}
	//			System.out.println("==---Case 21---===");
	//			for (int kk = 0; kk < finalDataOfMedal.size(); kk++) {
	//				System.out.println("Main : "+finalDataOfMedal.get(kk).getMainString());
	//				for (int ll = 0; ll < finalDataOfMedal.get(kk).getSubStringList().size(); ll++) {
	//					screenName.add(finalDataOfMedal.get(kk).getMainString());
	//					elementId.add("NO_NEED");
	//					stringInfo.add(finalDataOfMedal.get(kk).getSubStringList().get(ll));
	//					System.out.println("Sub : "+finalDataOfMedal.get(kk).getSubStringList().get(ll));
	//				}
	//			}
	//					StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//					model.setScreenName(screenName);
	//					model.setElementsId(elementId);
	//					model.setStringsOfScreen(stringInfo);
	//					stringsOfScreenToExcel.add(model);
	//					support.backToHomeScreen();
	//					System.out.println("===================== All String After Medal ================== ");
	//					for (int m = 0; m < stringsOfScreenToExcel.size(); m++) {
	//						for (int n = 0; n < stringsOfScreenToExcel.get(m).getStringsOfScreen().size(); n++) {
	//							System.out.println(stringsOfScreenToExcel.get(m).getStringsOfScreen().get(n));
	//						}
	//					}
	//					System.out.println("===================== All String ================== ");
	//					
	//					String filePath = System.getProperty("user.dir");
	//					this.writeActualStrings(filePath, "StringResults_Last.xlsx",selectedSystemLanguage);
	//					
	//			} catch (Exception e) {
	//				support.backToHomeScreen();
	//			}
	//		}

	//		@Test (priority=27)
	//		public void Special_Scenario_One() throws Exception {
	//			ArrayList<String> special =  new ArrayList<String>();
	//			try {
	//			driver.findElementById("btn_navigation_menu_left").click();
	//			Thread.sleep(1000);
	//			driver.findElementsById("tv_phrase_name").get(1).click();
	//			Thread.sleep(2000);
	//			ArrayList<String> screenName = new ArrayList<String>();
	//			ArrayList<String> elementId = new ArrayList<String>();
	//			ArrayList<String> stringInfo = new ArrayList<String>();
	//			
	//			try {
	//				special.add(driver.findElementById("tvLanguageShooting").getText());
	//				driver.findElementById("tvLanguageShooting").click();
	//				Thread.sleep(3000);
	//				special.add(driver.findElementById("toolbar_title").getText());
	//				driver.findElementById("fab_microphone").click();
	//				Thread.sleep(3000);
	//				special.add(driver.findElementById("tv_top_title").getText());
	//				special.add(driver.findElementById("tv_info").getText());
	//			} catch (Exception e) {
	//				// TODO: handle exception
	//			}
	//			if (special.size()>0) {
	//				for (int i = 0; i < special.size(); i++) {
	//					screenName.add("Camera_One");
	//					elementId.add("NA");
	//					stringInfo.add(special.get(i));
	//				}
	//			}else {
	//				//
	//			}
	//			
	//			StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//			model.setScreenName(screenName);
	//			model.setElementsId(elementId);
	//			model.setStringsOfScreen(stringInfo);
	//			stringsOfScreenToExcel.add(model);
	//			support.backToHomeScreen();
	//			} catch (Exception e) {
	//				support.backToHomeScreen();
	//			}
	//		}
	//
	//		@Test (priority=28)
	//		public void Special_Scenario_TWO() throws Exception {
	//			ArrayList<String> special =  new ArrayList<String>();
	//			try {
	//			driver.findElementById("btn_navigation_menu_left").click();
	//			Thread.sleep(1000);
	//			driver.findElementsById("tv_phrase_name").get(1).click();
	//			Thread.sleep(2000);
	//			ArrayList<String> screenName = new ArrayList<String>();
	//			ArrayList<String> elementId = new ArrayList<String>();
	//			ArrayList<String> stringInfo = new ArrayList<String>();
	//			try {
	//				new PTSSupportCamera(driver, packageName).captureImage();
	//				Thread.sleep(3000);
	//				driver.findElementById("save").click();
	//				Thread.sleep(10000);
	//				special.add(driver.findElementById("android:id/message").getText());
	//			} catch (Exception e) {
	//				// TODO: handle exception
	//			}
	//			if (special.size()>0) {
	//				for (int i = 0; i < special.size(); i++) {
	//					screenName.add("Camera_Two");
	//					elementId.add("NA");
	//					stringInfo.add(special.get(i));
	//				}
	//			}else {
	//				//
	//			}
	//			StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//			model.setScreenName(screenName);
	//			model.setElementsId(elementId);
	//			model.setStringsOfScreen(stringInfo);
	//			stringsOfScreenToExcel.add(model);
	//			support.backToHomeScreen();
	//			} catch (Exception e) {
	//				support.backToHomeScreen();
	//			}
	//		}
	//		
	//		@Test (priority=28)
	//		public void Special_Scenario_Three() throws Exception {
	//			ArrayList<String> special =  new ArrayList<String>();
	//			try {
	//				driver.findElementById("layout_gradient_top").click();
	//				Thread.sleep(1000);
	//			ArrayList<String> screenName = new ArrayList<String>();
	//			ArrayList<String> elementId = new ArrayList<String>();
	//			ArrayList<String> stringInfo = new ArrayList<String>();
	//			try {
	//				special.add(driver.findElementById("toolbar_title").getText());
	//				driver.findElementById("fab_microphone").click();
	//				Thread.sleep(3000);
	//				special.add(driver.findElementById("tv_top_title").getText());
	//				special.add(driver.findElementById("tv_info").getText());
	//				driver.findElementById("iv_top_back").click();
	//				Thread.sleep(1000);
	//				driver.findElementById("ib_language").click();
	//				Thread.sleep(2000);
	//				special.add(driver.findElementById("toolbar_title_other_language").getText());
	//				driver.findElementById("fab_microphone").click();
	//				Thread.sleep(3000);
	//				special.add(driver.findElementById("tv_top_title").getText());
	//				special.add(driver.findElementById("tv_info").getText());
	//				
	//			} catch (Exception e) {
	//				// TODO: handle exception
	//			}
	//			if (special.size()>0) {
	//				for (int i = 0; i < special.size(); i++) {
	//					screenName.add("Voice_Translation");
	//					elementId.add("NA");
	//					stringInfo.add(special.get(i));
	//				}
	//			}else {
	//				//
	//			}
	//			StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//			model.setScreenName(screenName);
	//			model.setElementsId(elementId);
	//			model.setStringsOfScreen(stringInfo);
	//			stringsOfScreenToExcel.add(model);
	//			support.backToHomeScreen();
	//			} catch (Exception e) {
	//				support.backToHomeScreen();
	//			}
	//		}
	//		
	//		@Test (priority=29)
	//		public void Convert_Units() throws Exception {
	//			ArrayList<String> special =  new ArrayList<String>();
	//			try {
	//				driver.findElementById("btn_navigation_menu_left").click();
	//				Thread.sleep(1000);
	//				driver.findElementsById("tv_phrase_name").get(2).click();
	//				Thread.sleep(6000);
	//			ArrayList<String> screenName = new ArrayList<String>();
	//			ArrayList<String> elementId = new ArrayList<String>();
	//			ArrayList<String> stringInfo = new ArrayList<String>();
	//			try {
	//				special.add(driver.findElementById("toolbar_title").getText());
	//				driver.findElementById("t9_key_unit_selection").click();
	//				Thread.sleep(2000);
	//				special.add(driver.findElementById("toolbar_title").getText());
	//				List<MobileElement> elm = driver.findElementsByXPath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout");
	//				for (int i = 0; i < elm.size(); i++) {
	//					try {
	//						special.add(elm.get(i).findElementById("tv_calc_unitName").getText());
	//					} catch (Exception e) {
	//						// TODO: handle exception
	//					}
	//				}
	//				
	//			} catch (Exception e) {
	//				// TODO: handle exception
	//			}
	//			if (special.size()>0) {
	//				for (int i = 0; i < special.size(); i++) {
	//					screenName.add("Convert_Units");
	//					elementId.add("NA");
	//					stringInfo.add(special.get(i));
	//				}
	//			}else {
	//				//
	//			}
	//			StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//			model.setScreenName(screenName);
	//			model.setElementsId(elementId);
	//			model.setStringsOfScreen(stringInfo);
	//			stringsOfScreenToExcel.add(model);
	//			support.backToHomeScreen();
	//			} catch (Exception e) {
	//				support.backToHomeScreen();
	//			}
	//		}
	//		
	//		@Test (priority=30)
	//		public void Demo_Mode_ON() throws Exception {
	//			ArrayList<String> special =  new ArrayList<String>();
	//			try {
	//				driver.findElementById("btn_navigation_menu_left").click();
	//				Thread.sleep(1000);
	//				driver.findElementsById("tv_phrase_name").get(0).click();
	//				Thread.sleep(2000);
	//			ArrayList<String> screenName = new ArrayList<String>();
	//			ArrayList<String> elementId = new ArrayList<String>();
	//			ArrayList<String> stringInfo = new ArrayList<String>();
	//			
	//			try {
	//				Thread.sleep(1000);
	//				MobileElement setLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/settingsRecyclerView\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+settingsMenuStored.get(13)+"\"));"));
	//				new TouchAction(driver).longPress(PointOption.point(setLanguage.getLocation())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(10000))).release().perform();
	//				Thread.sleep(500);
	//				special.add(driver.findElementById("android:id/message").getText());
	//				special.add(driver.findElementById("android:id/button1").getText());
	//				special.add(driver.findElementById("android:id/button2").getText());
	//				driver.findElementById("android:id/button1").click();
	//				Thread.sleep(2000);
	//				MobileElement setLanguage2 = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/settingsRecyclerView\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+settingsMenuStored.get(13)+"\"));"));
	//				new TouchAction(driver).longPress(PointOption.point(setLanguage2.getLocation())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(10000))).release().perform();
	//				Thread.sleep(500);
	//				special.add(driver.findElementById("android:id/message").getText());
	//				special.add(driver.findElementById("android:id/button1").getText());
	//				special.add(driver.findElementById("android:id/button2").getText());
	//				driver.findElementById("android:id/button1").click();
	//			} catch (Exception e) {
	//				
	//			}
	//			if (special.size()>0) {
	//				for (int i = 0; i < special.size(); i++) {
	//					screenName.add("Demo_Mode");
	//					elementId.add("NA");
	//					stringInfo.add(special.get(i));
	//				}
	//			}else {
	//				//
	//			}
	//			StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//			model.setScreenName(screenName);
	//			model.setElementsId(elementId);
	//			model.setStringsOfScreen(stringInfo);
	//			stringsOfScreenToExcel.add(model);
	//			support.backToHomeScreen();
	//			} catch (Exception e) {
	//				support.backToHomeScreen();
	//			}
	//			
	//			String filePath = System.getProperty("user.dir");
	//			this.writeActualStrings(filePath, "StringResults_Last.xlsx",selectedSystemLanguage);
	//		}
	//------------------- 2nd Part From Home --------------------
	//------------------- 2nd Part From Home --------------------
	//		@Test (priority=50)
	//		public void SettingsMenuStringTest_Malay() throws Exception {
	//			settingsMenuStored.clear();
	//			stringsOfScreenToExcel.clear();
	//			resetMenuStored.clear();
	//			intialLanguageListStored.clear();
	//			try {
	//				System.out.println("New Language Started ------ ");
	//				System.out.println("1 "+selectedSystemLanguage);
	//				this.selectedSystemLanguage = support.selectSystemLanguageOption("System Language","Operar em português");
	//				System.out.println("3 "+selectedSystemLanguage);
	//				
	//				driver.findElementById("btn_navigation_menu_left").click();
	//				Thread.sleep(2000);
	//				driver.findElementsById("tv_phrase_name").get(0).click();
	//				Thread.sleep(2000);
	//				ArrayList<String> screenNames = new ArrayList<String>();
	//				ArrayList<String> menuIds = new ArrayList<String>();
	//				ArrayList<String> menuNames = new ArrayList<String>();
	//				screenNames.add(menuSettings.getToolbarTitleString().get(0));
	//				menuIds.add(menuSettings.getToolbarTitleString().get(1));
	//				menuNames.add(menuSettings.getToolbarTitleString().get(2));
	//				screenNames.add(menuSettings.getMobileData().get(0));
	//				menuIds.add(menuSettings.getMobileData().get(1));
	//				menuNames.add(menuSettings.getMobileData().get(2));
	//				screenNames.add(menuSettings.getWiFiString().get(0));
	//				menuIds.add(menuSettings.getWiFiString().get(1));
	//				menuNames.add(menuSettings.getWiFiString().get(2));
	//				screenNames.add(menuSettings.getBluetoothString().get(0));
	//				menuIds.add(menuSettings.getBluetoothString().get(1));
	//				menuNames.add(menuSettings.getBluetoothString().get(2));
	//				ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
	//				if (selectedSystemLanguage.contains("Use in English")) {
	//					settingsMenuStored.clear();
	//					info = menuSettings.getSettingsMenuList("Reset");
	//				}else if (selectedSystemLanguage.contains("使用繁體中文操作")) {
	//					settingsMenuStored.clear();
	//					info = menuSettings.getSettingsMenuList("重設");
	//				}else if (selectedSystemLanguage.contains("Operar em português")) {
	//					settingsMenuStored.clear();
	//					info = menuSettings.getSettingsMenuList("Reinicializar");
	//				}else if (selectedSystemLanguage.contains("Работать на русском")) {
	//					settingsMenuStored.clear();
	//					info = menuSettings.getSettingsMenuList("Сбросить");
	//				}else if (selectedSystemLanguage.contains("ใช้งานด้วยภาษาไทย")) {
	//					settingsMenuStored.clear();
	//					info = menuSettings.getSettingsMenuList("รีเซ็ต");
	//				}else if (selectedSystemLanguage.contains("Operasi Dalam Bahasa Melayu")) {
	//					settingsMenuStored.clear();
	//					info = menuSettings.getSettingsMenuList("Set Semula");
	//				}else {
	//					settingsMenuStored.clear();
	//					info = menuSettings.getSettingsMenuList("Reset");
	//					System.out.println("System Language Not Stored");
	//				}
	//				screenNames.addAll(info.get(0));
	//				menuIds.addAll(info.get(1));
	//				menuNames.addAll(info.get(2));
	//				settingsMenuStored.addAll(info.get(2));
	//				StringsOfScreenModel strModel =  new StringsOfScreenModel(screenNames, menuIds,menuNames);
	//				strModel.setScreenName(screenNames);
	//				strModel.setElementsId(menuIds);
	//				strModel.setStringsOfScreen(menuNames);
	//				stringsOfScreenToExcel.add(strModel);
	//				System.out.println("Total Menu Count"+settingsMenuStored.size());
	//				support.backToHomeScreen();	
	//			} catch (Exception e) {
	//				// TODO: handle exception
	//			}	
	//		}
	//		@Test (priority=51)
	//		public void MainMenuStringTest_Malay() throws Exception {
	//			try {
	//				driver.findElementById("btn_navigation_menu_left").click();
	//				Thread.sleep(1000);
	//				ArrayList<ArrayList<String>> mainMenuInfo = mainMenu.getMainMenuList();
	//				StringsOfScreenModel mainMenuString =  new StringsOfScreenModel(mainMenuInfo.get(0), mainMenuInfo.get(1),mainMenuInfo.get(2));
	//				mainMenuString.setScreenName(mainMenuInfo.get(0));
	//				mainMenuString.setElementsId(mainMenuInfo.get(1));
	//				mainMenuString.setStringsOfScreen(mainMenuInfo.get(2));
	//				stringsOfScreenToExcel.add(mainMenuString);
	//				support.backToHomeScreen();
	//			} catch (Exception e) {
	//				support.backToHomeScreen();
	//			}
	//			
	//		}
	//
	//			@Test (priority=52)
	//			public void Menu_Mobile_Data_Screen_String_Test_Malay() throws Exception {
	//				try {
	//					driver.findElementById("btn_navigation_menu_left").click();
	//					Thread.sleep(1000);
	//					driver.findElementsById("tv_phrase_name").get(0).click();
	//					Thread.sleep(2000);
	//					menuSettings.tapOnMobileDataText();
	//					Thread.sleep(5000);
	//					ArrayList<ArrayList<String>> scr = mobileData.getMobileDataStrings();
	//					StringsOfScreenModel model = new StringsOfScreenModel(scr.get(0), scr.get(1), scr.get(2));
	//					model.setScreenName(scr.get(0));
	//					model.setElementsId(scr.get(1));
	//					model.setStringsOfScreen(scr.get(2));
	//					stringsOfScreenToExcel.add(model);
	//					support.backToHomeScreen();
	//				} catch (Exception e) {
	//					support.backToHomeScreen();
	//				}
	//			}
	//			@Test (priority=53)
	//			public void Menu_Wi_Fi_Screen_String_Test_Malay() throws Exception {
	//				try {
	//					driver.findElementById("btn_navigation_menu_left").click();
	//					Thread.sleep(1000);
	//					driver.findElementsById("tv_phrase_name").get(0).click();
	//					Thread.sleep(2000);
	//					menuSettings.tapOnWiFiText();
	//					Thread.sleep(5000);
	//					WiFi_POM wifi = new WiFi_POM(driver);
	//					ArrayList<ArrayList<String>> asdf= wifi.getWiFiStrings();
	//					ArrayList<String> a = asdf.get(0);
	//					ArrayList<String> b = asdf.get(1);
	//					ArrayList<String> c = asdf.get(2);
	//					StringsOfScreenModel model = new StringsOfScreenModel(a,b,c);
	//					model.setScreenName(a);
	//					model.setElementsId(b);
	//					model.setStringsOfScreen(c);
	//					stringsOfScreenToExcel.add(model);
	//									ArrayList<String> screenName = new ArrayList<String>();
	//									ArrayList<String> elementsId = new ArrayList<String>();
	//									ArrayList<String> stringInfo = new ArrayList<String>();
	//									screenName.add("WiFi");
	//									elementsId.add("android:id/title");
	//									stringInfo.add(driver.findElementById("android:id/title").getText());
	//									
	//									StringsOfScreenModel mdl = new StringsOfScreenModel(screenName,elementsId,stringInfo);
	//									mdl.setScreenName(screenName);
	//									mdl.setElementsId(elementsId);
	//									mdl.setStringsOfScreen(stringInfo);
	//									stringsOfScreenToExcel.add(mdl);
	//					support.backToHomeScreen();
	//				} catch (Exception e) {
	//					support.backToHomeScreen();
	//				}
	//				
	//			}
	//	
	//			@Test (priority=54)
	//			public void Menu_Bluetooth_Screen_String_Test_Malay() throws Exception {
	//				try {
	//					driver.findElementById("btn_navigation_menu_left").click();
	//					Thread.sleep(1000);
	//					driver.findElementsById("tv_phrase_name").get(0).click();
	//					Thread.sleep(2000);
	//					menuSettings.tapOnBluetoothText();
	//					Thread.sleep(1000);
	//					Bluetooth_POM bluetooth = new Bluetooth_POM(driver);
	//					ArrayList<String> a = new ArrayList<String>();
	//					a.addAll(bluetooth.getBluetoothStrings().get(0));
	//					ArrayList<String> b = new ArrayList<String>();
	//					b.addAll(bluetooth.getBluetoothStrings().get(1));
	//					ArrayList<String> c = new ArrayList<String>();
	//					c.addAll(bluetooth.getBluetoothStrings().get(2));
	//					StringsOfScreenModel model = new StringsOfScreenModel(a,b,c);
	//					model.setScreenName(a);
	//					model.setElementsId(b);
	//					model.setStringsOfScreen(c);
	//					stringsOfScreenToExcel.add(model);
	//					support.backToHomeScreen();
	//				} catch (Exception e) {
	//					support.backToHomeScreen();
	//				}
	//				
	//				System.out.println("---------Case 8--------------------------");
	//				for (int m = 0; m < stringsOfScreenToExcel.size(); m++) {
	//					for (int n = 0; n < stringsOfScreenToExcel.get(m).getStringsOfScreen().size(); n++) {
	//						System.out.println(stringsOfScreenToExcel.get(m).getStringsOfScreen().get(n));
	//					}
	//				}
	//			}
	//	
	//			@Test (priority=55)
	//			public void Menu_Brightness_Screen_String_Test_Malay() throws Exception {
	//				try {
	//					driver.findElementById("btn_navigation_menu_left").click();
	//					Thread.sleep(1000);
	//					driver.findElementsById("tv_phrase_name").get(0).click();
	//					Thread.sleep(2000);
	//					System.out.println(settingsMenuStored.get(0));
	//					support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(0));
	//					ArrayList<String> screenName = new ArrayList<String>();
	//					ArrayList<String> elementsId = new ArrayList<String>();
	//					ArrayList<String> strings = new ArrayList<String>();
	//					screenName.add("Brightness");
	//					screenName.add("Brightness");
	//					elementsId.add("android:id/alertTitle");
	//					elementsId.add("android:id/button2");
	//					strings.add(driver.findElementById("android:id/alertTitle").getText());
	//					strings.add(driver.findElementById("android:id/button2").getText());
	//					
	//					StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, strings);
	//					model.setScreenName(screenName);
	//					model.setElementsId(elementsId);
	//					model.setStringsOfScreen(strings);
	//					stringsOfScreenToExcel.add(model);
	//					support.backToHomeScreen();
	//				} catch (Exception e) {
	//					support.backToHomeScreen();
	//				}
	//				
	//			}
	//	
	//			@Test (priority=56)
	//			public void Menu_Text_Size_Screen_String_Test_Malay() throws Exception {
	//				try {
	//					driver.findElementById("btn_navigation_menu_left").click();
	//					Thread.sleep(1000);
	//					driver.findElementsById("tv_phrase_name").get(0).click();
	//					Thread.sleep(2000);
	//					support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(1));
	//					ArrayList<String> screenName = new ArrayList<String>();
	//					ArrayList<String> elementsId = new ArrayList<String>();
	//					ArrayList<String> stringInfo = new ArrayList<String>();
	//					for (int i = 0; i <= 1; i++) {
	//						screenName.add(ScreenNameInExcel.get(i));
	//						elementsId.add(ElementsIdInExcel.get(i));
	//						stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1));
	//						System.out.println(support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1));
	//					}
	//					for (int j = 0; j < driver.findElementsById("android:id/text1").size(); j++) {
	//						stringInfo.add(driver.findElementsById("android:id/text1").get(j).getText());
	//						System.out.println(driver.findElementsById("android:id/text1").get(j).getText());
	//					}
	//					StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//					model.setScreenName(screenName);
	//					model.setElementsId(elementsId);
	//					model.setStringsOfScreen(stringInfo);
	//					stringsOfScreenToExcel.add(model);
	//					support.backToHomeScreen();
	//				} catch (Exception e) {
	//					support.backToHomeScreen();
	//				}
	//				
	//			}
	//	
	//			@Test (priority=57)
	//			public void Menu_Software_Update_String_Test_Malay() throws Exception {
	//				try {
	//					driver.findElementById("btn_navigation_menu_left").click();
	//					Thread.sleep(1000);
	//					driver.findElementsById("tv_phrase_name").get(0).click();
	//					Thread.sleep(2000);
	//					support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(2));
	//					Thread.sleep(12000);
	//					ArrayList<String> screenName = new ArrayList<String>();
	//					ArrayList<String> elementsId = new ArrayList<String>();
	//					ArrayList<String> stringInfo = new ArrayList<String>();
	//					for (int k = 4; k <= 6; k++) {
	//						screenName.add(ScreenNameInExcel.get(k));
	//						elementsId.add(ElementsIdInExcel.get(k));
	//						stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(k), false, -1));
	//						System.out.println(support.getStringByIdXpath(ElementsIdInExcel.get(k), false, -1));
	//					}
	//						StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//						model.setScreenName(screenName);
	//						model.setElementsId(elementsId);
	//						model.setStringsOfScreen(stringInfo);
	//						stringsOfScreenToExcel.add(model);
	//						support.backToHomeScreen();
	//				} catch (Exception e) {
	//					support.backToHomeScreen();
	//				}
	//				
	//				}
	//	
	//			@Test (priority=58)
	//			public void Menu_Shortcut_Screen_String_Test_Malay() throws Exception {
	//				try {
	//					driver.findElementById("btn_navigation_menu_left").click();
	//					Thread.sleep(1000);
	//					driver.findElementsById("tv_phrase_name").get(0).click();
	//					Thread.sleep(2000);
	//					support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(3));
	//					ArrayList<String> screenName = new ArrayList<String>();
	//					ArrayList<String> elementsId = new ArrayList<String>();
	//					ArrayList<String> stringInfo = new ArrayList<String>();
	//					for (int k = 2; k <= 3; k++) {
	//						screenName.add(ScreenNameInExcel.get(k));
	//						elementsId.add(ElementsIdInExcel.get(k));
	//						stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(k), false, -1));
	//					}
	//					
	//					ArrayList<String> temp = new ArrayList<String>();
	//					int asd = 1;  
	//				    while (asd<=2) {
	//				    	java.util.List<MobileElement> items = driver.findElementsById("item_name");
	//				    	for (int i = 0; i < items.size(); i++) {
	//							try {
	//								if(!items.get(i).getText().isEmpty()) {
	//									if (temp.contains(items.get(i).getText())) {
	//										System.out.println("Duplicate Value!");
	//									}else {
	//										temp.add(items.get(i).getText());
	//									}
	//								}else {
	//									System.out.println("Unable to get the text !!");
	//								}
	//							} catch (Exception e) {
	//								System.out.println(e);
	//							}
	//						}
	//			    	    new TouchAction(driver).press(PointOption.point(support.getMaxPositionOfDisplay("bottom"))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(support.getMaxPositionOfDisplay("TOP"))).release().perform();
	//			    	   asd++;
	//				    }		
	//						if (temp.size()>0) {
	//							for (int j = 0; j < temp.size(); j++) {
	//								screenName.add("Shortcut");
	//								elementsId.add("item_name");
	//								stringInfo.add(temp.get(j));
	//							}
	//						}
	//						StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//						model.setScreenName(screenName);
	//						model.setElementsId(elementsId);
	//						model.setStringsOfScreen(stringInfo);
	//						stringsOfScreenToExcel.add(model);
	//						support.backToHomeScreen();
	//				} catch (Exception e) {
	//					support.backToHomeScreen();
	//				}
	//				
	//				}
	//				
	//	
	//			@Test (priority=59)
	//			public void Menu_Lock_Screen_Settings_String_Test_Malay() throws Exception {
	//				try {
	//					driver.findElementById("btn_navigation_menu_left").click();
	//					Thread.sleep(1000);
	//					driver.findElementsById("tv_phrase_name").get(0).click();
	//					Thread.sleep(2000);
	//					support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(4));
	//					ArrayList<String> screenName = new ArrayList<String>();
	//					ArrayList<String> elementsId = new ArrayList<String>();
	//					ArrayList<String> stringInfo = new ArrayList<String>();
	//					String checked = driver.findElementById("checkbox_lock_screen_on_off").getAttribute("checked");
	//					System.out.println(checked);
	//					if (checked.contains("true")) {
	//						driver.findElementById("checkbox_lock_screen_on_off").click();
	//						Thread.sleep(1000);
	//					}else {
	//						System.out.println("Lock Screen OFF");
	//					}
	//					for (int i = 7; i <=9; i++) {
	//						screenName.add(ScreenNameInExcel.get(i));
	//						elementsId.add(ElementsIdInExcel.get(i));
	//						stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1));
	//					}
	//					driver.findElementById("checkbox_lock_screen_on_off").click();
	//					Thread.sleep(1000);
	//					for (int j = 10; j <=15; j++) {
	//						screenName.add(ScreenNameInExcel.get(j));
	//						elementsId.add(ElementsIdInExcel.get(j));
	//						stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(j), false, -1));
	//					}
	//					driver.findElementById("checkbox_display_recent_locations").click();
	//					Thread.sleep(500);
	//					screenName.add(ScreenNameInExcel.get(16));
	//					elementsId.add(ElementsIdInExcel.get(16));
	//					stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(16), false, -1));
	//			
	//					StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//					model.setScreenName(screenName);
	//					model.setElementsId(elementsId);
	//					model.setStringsOfScreen(stringInfo);
	//					stringsOfScreenToExcel.add(model);
	//					
	//					if (driver.findElementById("checkbox_lock_screen_on_off").getAttribute("checked").contains("true")) {
	//						driver.findElementById("checkbox_lock_screen_on_off").click();
	//						System.out.println("Leave Lock Screen OFF");
	//					}else {
	//						System.out.println("Leave Lock Screen OFF");
	//					}
	//					support.backToHomeScreen();
	//				} catch (Exception e) {
	//					support.backToHomeScreen();
	//				}
	//			}
	//	
	//			@Test (priority=60)
	//			public void Menu_Pocketalk_Center_String_Test_Malay() throws Exception {
	//				
	//				try {
	//				driver.findElementById("btn_navigation_menu_left").click();
	//				Thread.sleep(1000);
	//				driver.findElementsById("tv_phrase_name").get(0).click();
	//				Thread.sleep(2000);
	//				support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(5));
	//				Thread.sleep(5000);
	//				ArrayList<String> screenName = new ArrayList<String>();
	//				ArrayList<String> elementsId = new ArrayList<String>();
	//				ArrayList<String> stringInfo = new ArrayList<String>();
	//				ArrayList<String> temp = new ArrayList<String>();
	//				ArrayList<String> temp2 = new ArrayList<String>();
	//				int asd=1;
	//				while (asd<=2) {
	//					for (int i = 17; i <=21; i++) {
	//						try {
	//							String s = support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1);
	//							if (!temp.contains(s) && !s.isEmpty()) {
	//								temp.add(s);
	//								temp2.add(ElementsIdInExcel.get(i));
	//							}else {
	//								System.out.println("String empty or available in the temp array");
	//							}
	//						} catch (Exception e) {
	//							System.out.println("get text exception...");
	//						}
	//					}
	//		    	    new TouchAction(driver).press(PointOption.point(support.getMaxPositionOfDisplay("bottom"))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(support.getMaxPositionOfDisplay("TOP"))).release().perform();
	//			    	asd++;
	//				}
	//				for (int j = 0; j < temp.size(); j++) {
	//					screenName.add("Pocketalk_Center");
	//					elementsId.add(temp2.get(j));
	//					stringInfo.add(temp.get(j));
	//				}
	//				StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//				model.setScreenName(screenName);
	//				model.setElementsId(elementsId);
	//				model.setStringsOfScreen(stringInfo);
	//				stringsOfScreenToExcel.add(model);
	//				support.backToHomeScreen();
	//				} catch (Exception e) {
	//					support.errorScreenshot("Pocketalk_Center");
	//					System.out.println(e);
	//					support.backToHomeScreen();
	//				}
	//			}
	//	
	//			@Test (priority=61)
	//			public void Menu_Notificaton_Seetings_String_Test_Malay() throws Exception {
	//				try {
	//					driver.findElementById("btn_navigation_menu_left").click();
	//					Thread.sleep(1000);
	//					driver.findElementsById("tv_phrase_name").get(0).click();
	//					Thread.sleep(2000);
	//					support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(6));
	//					ArrayList<String> screenName = new ArrayList<String>();
	//					ArrayList<String> elementsId = new ArrayList<String>();
	//					ArrayList<String> stringInfo = new ArrayList<String>();
	//					screenName.add("Notification_Settings");
	//					elementsId.add("toolbar_title");
	//					stringInfo.add(support.getStringByIdXpath("toolbar_title", false, -1));
	//					for (int i = 0; i <=1; i++) {
	//						screenName.add("Notification_Settings");
	//						elementsId.add("text_view_notification");
	//						stringInfo.add(support.getStringByIdXpath("text_view_notification", false, i));
	//						elementsId.add("tv_notification_on_off_screen_state");
	//						stringInfo.add(support.getStringByIdXpath("tv_notification_on_off_screen_state", false, i));
	//					}
	//					driver.findElementsById("checkbox_notification").get(0).click();
	//					driver.findElementsById("checkbox_notification").get(1).click();
	//					for (int j = 0; j <=1; j++) {
	//						screenName.add("Notification_Settings");
	//						elementsId.add("tv_notification_on_off_screen_state");
	//						stringInfo.add(support.getStringByIdXpath("tv_notification_on_off_screen_state", false, j));
	//					}
	//					
	//					StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//					model.setScreenName(screenName);
	//					model.setElementsId(elementsId);
	//					model.setStringsOfScreen(stringInfo);
	//					stringsOfScreenToExcel.add(model);
	//					support.backToHomeScreen();
	//				} catch (Exception e) {
	//					support.backToHomeScreen();
	//				}
	//				
	//			}
	//	
	//			@Test (priority=62)
	//			public void Menu_Sleep_Seetings_String_Test_Malay() throws Exception {
	//				try {
	//					driver.findElementById("btn_navigation_menu_left").click();
	//					Thread.sleep(1000);
	//					driver.findElementsById("tv_phrase_name").get(0).click();
	//					Thread.sleep(2000);
	//					support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(7));
	//					ArrayList<String> screenName = new ArrayList<String>();
	//					ArrayList<String> elementsId = new ArrayList<String>();
	//					ArrayList<String> stringInfo = new ArrayList<String>();
	//					for (int i = 29; i <=30; i++) {
	//						screenName.add(ScreenNameInExcel.get(i));
	//						elementsId.add(ElementsIdInExcel.get(i));
	//						stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1));
	//					}
	//					for (int j = 0; j < 5; j++) {
	//						screenName.add(ScreenNameInExcel.get(31));
	//						elementsId.add(ElementsIdInExcel.get(31));
	//						stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(31), false, j));
	//					}
	//					StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//					model.setScreenName(screenName);
	//					model.setElementsId(elementsId);
	//					model.setStringsOfScreen(stringInfo);
	//					stringsOfScreenToExcel.add(model);
	//					support.backToHomeScreen();
	//				} catch (Exception e) {
	//					support.backToHomeScreen();
	//				}
	//				
	//			}
	//	
	//		@Test (priority=63)
	//		public void Menu_System_Language_String_Test_Malay() throws Exception {
	//			try {
	//				driver.findElementById("btn_navigation_menu_left").click();
	//				Thread.sleep(1000);
	//				driver.findElementsById("tv_phrase_name").get(0).click();
	//				Thread.sleep(2000);
	//				support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(8));
	//				ArrayList<String> screenName = new ArrayList<String>();
	//				ArrayList<String> elementsId = new ArrayList<String>();
	//				ArrayList<String> stringInfo = new ArrayList<String>();
	//				screenName.add("System_Language");
	//				elementsId.add("toolbar_title");
	//				stringInfo.add(support.getStringByIdXpath("toolbar_title", false, -1));
	//				ArrayList<String> temp = new ArrayList<String>();
	//				int asd=1;
	//				while (asd<=3) {
	//					List<MobileElement> languages = driver.findElementsById("txt_lang_name");
	//					for (int i =0; i <languages.size(); i++) {
	//						try {
	//							String s = languages.get(i).getText();
	//							if (!temp.contains(s) && !s.isEmpty()) {
	//								temp.add(s);
	//								screenName.add("System_Language");
	//								elementsId.add("txt_lang_name");
	//								stringInfo.add(s);
	//							}else {
	//								System.out.println("String empty or available in the temp array");
	//							}
	//						} catch (Exception e) {
	//							System.out.println("get text exception...");
	//						}
	//					}
	//					new TouchAction(driver).press(PointOption.point(support.getMaxPositionOfDisplay("bottom"))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(support.getMaxPositionOfDisplay("TOP"))).release().perform();
	//					asd++;
	//				}
	//				StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//				model.setScreenName(screenName);
	//				model.setElementsId(elementsId);
	//				model.setStringsOfScreen(stringInfo);
	//				stringsOfScreenToExcel.add(model);
	//				support.backToHomeScreen();
	//			} catch (Exception e) {
	//				support.backToHomeScreen();
	//			}
	//			
	//		}
	//	
	//			@Test (priority=64)
	//			public void Menu_Region_Of_Use_Screen_String_Test_Malay() throws Exception {
	//				try {
	//					driver.findElementById("btn_navigation_menu_left").click();
	//					Thread.sleep(1000);
	//					driver.findElementsById("tv_phrase_name").get(0).click();
	//					Thread.sleep(2000);
	//					support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(9));
	//					List<MobileElement> elements = driver.findElementsByXPath("//android.widget.LinearLayout/android.widget.LinearLayout");
	//					ArrayList<String> screenName = new ArrayList<String>();
	//					ArrayList<String> elementsId = new ArrayList<String>();
	//					ArrayList<String> stringInfo = new ArrayList<String>();
	//					screenName.add("Region_Of_Use");
	//					elementsId.add("android.widget.RadioButton");
	//					stringInfo.add(support.getStringByIdXpath("toolbar_title", false, -1));
	//					for (int i = 0; i < 2; i++) {
	//						screenName.add("Region_Of_Use");
	//						screenName.add("Region_Of_Use");
	//						elementsId.add("android.widget.RadioButton");
	//						elementsId.add("android.widget.TextView");
	//						stringInfo.add(elements.get(i).findElementByClassName("android.widget.RadioButton").getText());
	//						stringInfo.add(elements.get(i).findElementByClassName("android.widget.TextView").getText());
	//					}
	//					StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//					model.setScreenName(screenName);
	//					model.setElementsId(elementsId);
	//					model.setStringsOfScreen(stringInfo);
	//					stringsOfScreenToExcel.add(model);
	//					support.backToHomeScreen();
	//				} catch (Exception e) {
	//					support.backToHomeScreen();
	//				}
	//				
	//			}
	//			
	//			@Test (priority=65)
	//			public void Menu_Communication_Plan_String_Test_Malay() throws Exception {
	//				try {
	//					
	//				
	//				driver.findElementById("btn_navigation_menu_left").click();
	//				Thread.sleep(1000);
	//				driver.findElementsById("tv_phrase_name").get(0).click();
	//				Thread.sleep(2000);
	//				support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(10));
	//				Thread.sleep(5000);
	//				ArrayList<String> screenName = new ArrayList<String>();
	//				ArrayList<String> elementsId = new ArrayList<String>();
	//				ArrayList<String> stringInfo = new ArrayList<String>();
	//				for (int i = 55; i <= 56; i++) {
	//					screenName.add(ScreenNameInExcel.get(i));
	//					elementsId.add(ElementsIdInExcel.get(i));
	//					stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1));
	//				}
	//				StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//				model.setScreenName(screenName);
	//				model.setElementsId(elementsId);
	//				model.setStringsOfScreen(stringInfo);
	//				stringsOfScreenToExcel.add(model);
	//				support.backToHomeScreen();
	//				} catch (Exception e) {
	//					support.backToHomeScreen();
	//				}
	//			}
	//		
	//			@Test (priority=66)
	//			public void Menu_Device_and_License_Information_String_Test_Malay() throws Exception {
	//				try {
	//				driver.findElementById("btn_navigation_menu_left").click();
	//				Thread.sleep(1000);
	//				driver.findElementsById("tv_phrase_name").get(0).click();
	//				Thread.sleep(2000);
	//				support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(11));
	//				Thread.sleep(5000);
	//				ArrayList<String> screenName = new ArrayList<String>();
	//				ArrayList<String> elementsId = new ArrayList<String>();
	//				ArrayList<String> stringInfo = new ArrayList<String>();
	//				for (int i = 57; i <= 61; i++) {
	//					screenName.add(ScreenNameInExcel.get(i));
	//					elementsId.add(ElementsIdInExcel.get(i));
	//					stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1));
	//				}
	//				StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementsId, stringInfo);
	//				model.setScreenName(screenName);
	//				model.setElementsId(elementsId);
	//				model.setStringsOfScreen(stringInfo);
	//				stringsOfScreenToExcel.add(model);
	//				support.backToHomeScreen();
	//				System.out.println("--------------case 20 -----------");
	//				for (int m = 0; m < stringsOfScreenToExcel.size(); m++) {
	//					for (int n = 0; n < stringsOfScreenToExcel.get(m).getStringsOfScreen().size(); n++) {
	//						System.out.println(stringsOfScreenToExcel.get(m).getStringsOfScreen().get(n));
	//					}
	//				}
	//				} catch (Exception e) {
	//					support.backToHomeScreen();
	//				}
	//			}
	//	
	//			@Test (priority=67)
	//			public void Menu_Device_Info_String_Test_Malay() throws Exception {
	//				try {
	//				driver.findElementById("btn_navigation_menu_left").click();
	//				Thread.sleep(1000);
	//				driver.findElementsById("tv_phrase_name").get(0).click();
	//				Thread.sleep(2000);
	//				support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(11));
	//				driver.findElementById("tv_device_info").click();
	//				Thread.sleep(500);
	//				
	//				ArrayList<String> screenName = new ArrayList<String>();
	//				ArrayList<String> elementId = new ArrayList<String>();
	//				ArrayList<String> stringInfo = new ArrayList<String>();
	//				screenName.add("Device_Information");
	//				elementId.add("NO_NEED");
	//				stringInfo.add(support.getStringByIdXpath("toolbar_title", false, -1));
	//				int asd = 1;
	//				ArrayList<String> temp = new ArrayList<String>();
	//				while (asd<=2) {
	//					List<MobileElement> elements = driver.findElementsByXPath("//android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView");
	//					for (int i = 0; i < elements.size(); i++) {
	//						try {
	//							String s = elements.get(i).getText();
	//							if (!s.isEmpty() && !temp.contains(s)) {
	//								temp.add(s);
	//								screenName.add("Device_Information");
	//								elementId.add("NO_NEED");
	//								stringInfo.add(elements.get(i).getText());
	//							}
	//						} catch (Exception e) {
	//							// TODO: handle exception
	//						}
	//					}
	//					new TouchAction(driver).press(PointOption.point(support.getMaxPositionOfDisplay("bottom"))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(support.getMaxPositionOfDisplay("TOP"))).release().perform();
	//					asd++;
	//				}
	//				StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//				model.setScreenName(screenName);
	//				model.setElementsId(elementId);
	//				model.setStringsOfScreen(stringInfo);
	//				stringsOfScreenToExcel.add(model);
	//				support.backToHomeScreen();
	//				System.out.println("--------Case 21--------");
	//				for (int m = 0; m < stringsOfScreenToExcel.size(); m++) {
	//					for (int n = 0; n < stringsOfScreenToExcel.get(m).getStringsOfScreen().size(); n++) {
	//						System.out.println(stringsOfScreenToExcel.get(m).getStringsOfScreen().get(n));
	//					}
	//				}
	//				} catch (Exception e) {
	//					support.backToHomeScreen();
	//				}
	//			}
	//	
	//		@Test (priority=68)
	//		public void Menu_Support_String_Test_Malay() throws Exception {
	//			try {
	//			driver.findElementById("btn_navigation_menu_left").click();
	//			Thread.sleep(1000);
	//			driver.findElementsById("tv_phrase_name").get(0).click();
	//			Thread.sleep(2000);
	//			support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(12));
	//			ArrayList<String> screenName = new ArrayList<String>();
	//			ArrayList<String> elementId = new ArrayList<String>();
	//			ArrayList<String> stringInfo = new ArrayList<String>();
	//			for (int i = 73; i <=75; i++) {
	//				screenName.add(ScreenNameInExcel.get(i));
	//				elementId.add(ElementsIdInExcel.get(i));
	//				stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(i), false, -1));
	//			}
	//			StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//			model.setScreenName(screenName);
	//			model.setElementsId(elementId);
	//			model.setStringsOfScreen(stringInfo);
	//			stringsOfScreenToExcel.add(model);
	//			support.backToHomeScreen();
	//			} catch (Exception e) {
	//				support.backToHomeScreen();
	//			}
	//		}
	//
	//	@Test (priority=69)
	//	public void Menu_Reset_String_Test_Malay() throws Exception {
	//		try {
	//		driver.findElementById("btn_navigation_menu_left").click();
	//		Thread.sleep(1000);
	//		driver.findElementsById("tv_phrase_name").get(0).click();
	//		Thread.sleep(2000);
	//		support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(13));
	//		ArrayList<String> screenName = new ArrayList<String>();
	//		ArrayList<String> elementId = new ArrayList<String>();
	//		ArrayList<String> stringInfo = new ArrayList<String>();
	//		screenName.add("Reset");
	//		elementId.add("toolbar_title");
	//		stringInfo.add(support.getStringByIdXpath("toolbar_title", false, -1));
	//		List<MobileElement> resetMenus = driver.findElementById("main_content").findElementByClassName("android.widget.LinearLayout").findElementsByClassName("android.widget.TextView");
	//		for (int i = 0; i <resetMenus.size(); i++) {
	//			screenName.add("Reset");
	//			elementId.add("NO_NEED");
	//			stringInfo.add(resetMenus.get(i).getText());
	//			resetMenuStored.add(resetMenus.get(i).getText());
	//		}
	//		StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//		model.setScreenName(screenName);
	//		model.setElementsId(elementId);
	//		model.setStringsOfScreen(stringInfo);
	//		stringsOfScreenToExcel.add(model);
	//		support.backToHomeScreen();
	//		} catch (Exception e) {
	//			support.backToHomeScreen();
	//		}
	//	}
	//
	//	@Test (priority=70)
	//	public void Menu_Reset_Warnig_String_Test_Malay() throws Exception {
	//		try {
	//		driver.findElementById("btn_navigation_menu_left").click();
	//		Thread.sleep(1000);
	//		driver.findElementsById("tv_phrase_name").get(0).click();
	//		Thread.sleep(2000);
	//		support.selectTargetStringOfList("settingsRecyclerView", settingsMenuStored.get(13));
	//		ArrayList<String> screenName = new ArrayList<String>();
	//		ArrayList<String> elementId = new ArrayList<String>();
	//		ArrayList<String> stringInfo = new ArrayList<String>();
	//		List<MobileElement> resetMenus = driver.findElementsByXPath("//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView");
	//		ArrayList<MainAndSubStringStoreModel> temp = new ArrayList<MainAndSubStringStoreModel>();
	//		for (int i = 0; i < resetMenus.size(); i++) {
	//			String mainS = resetMenus.get(i).getText();
	//			resetMenus.get(i).click();
	//			Thread.sleep(2000);
	//			try {
	//				String ss = support.getStringByIdXpath("android:id/button2", false, -1);
	//				if (ss.isEmpty()) {
	//					System.out.println("Inside deletion activity !!!");
	//					if (driver.findElementById("checkbox_auto_delete_status").getAttribute("checked").contains("false")) {
	//						try {
	//							screenName.add("Auto_Deletion");
	//							elementId.add("txt_auto_delete_status");
	//							stringInfo.add(support.getStringByIdXpath("txt_auto_delete_status", false, -1));
	//						} catch (Exception e) {
	//							// TODO: handle exception
	//						}
	//						driver.findElementById("checkbox_auto_delete_status").click();
	//						Thread.sleep(2000);
	//					}else {
	//						System.out.println("Auto Deletion Is ON");
	//					}
	//					
	//					if (driver.findElementById("checkbox_auto_delete_status").getAttribute("checked").contains("true")) {
	//						System.out.println("Auto Deletion Is ON 222222 ");
	//						for (int j = 91; j <=97; j++) {
	//							screenName.add(ScreenNameInExcel.get(j));
	//							elementId.add(ElementsIdInExcel.get(j));
	//							System.out.println("???"+ElementsIdInExcel.get(j));
	//							stringInfo.add(support.getStringByIdXpath(ElementsIdInExcel.get(j), false, -1));
	//							System.out.println("???"+support.getStringByIdXpath(ElementsIdInExcel.get(j), false, -1));
	//						}
	//					}else {
	//						System.out.println("Auto Delete Button Is OFF");
	//					}
	//					driver.findElementById("iv_top_back").click();
	//					Thread.sleep(2000);
	//					resetMenus = driver.findElementsByXPath("//android.widget.FrameLayout/android.widget.LinearLayout/android.widget.TextView");
	//				}else {
	//					ArrayList<String> tmp2 = new ArrayList<String>();
	//					for (int k = 82; k <=84; k++) {
	//						try {
	//							String s = support.getStringByIdXpath(ElementsIdInExcel.get(k), false, -1);
	//							if (!tmp2.contains(s) && !s.isEmpty()) {
	//								tmp2.add(s);
	//							}else {
	//								System.out.println("String empty or available in the temp array");
	//							}
	//						} catch (Exception e) {
	//							System.out.println("get text exception...");
	//						}
	//					}
	//					MainAndSubStringStoreModel mdl = new MainAndSubStringStoreModel(mainS, tmp2);
	//					mdl.setMainString(mainS);
	//					mdl.setSubStringList(tmp2);
	//					temp.add(mdl);
	//					try {
	//						driver.findElementById("android:id/button2").click();
	//						Thread.sleep(1000);
	//					} catch (Exception e) {
	//						// TODO: handle exception
	//					}
	//				}
	//			} catch (Exception e) {
	//				// TODO: handle exception
	//			}			
	//		}
	//		for (int kk = 0; kk < temp.size(); kk++) {
	//			System.out.println(temp.get(kk).getMainString());
	//			for (int l = 0; l < temp.get(kk).getSubStringList().size(); l++) {
	//				screenName.add(temp.get(kk).getMainString());
	//				elementId.add("NO_NEED");
	//				stringInfo.add(temp.get(kk).getSubStringList().get(l));
	//			}
	//		}
	//		StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//		model.setScreenName(screenName);
	//		model.setElementsId(elementId);
	//		model.setStringsOfScreen(stringInfo);
	//		stringsOfScreenToExcel.add(model);
	//		support.backToHomeScreen();
	//		System.out.println("==---Case 24---===");
	//		for (int m = 0; m < stringsOfScreenToExcel.size(); m++) {
	//			for (int n = 0; n < stringsOfScreenToExcel.get(m).getStringsOfScreen().size(); n++) {
	//				System.out.println("==------==="+stringsOfScreenToExcel.get(m).getStringsOfScreen().get(n));
	//			}
	//		}
	//		} catch (Exception e) {
	//			support.backToHomeScreen();
	//		}
	//		
	//	}
	//
	//	@Test (priority=71)
	//	public void Medal_Badge_String_Test_Malay() throws Exception {
	//		try {
	//		driver.findElementById("btn_navigation_menu_left").click();
	//		Thread.sleep(1000);
	//		driver.findElementsById("tv_phrase_name").get(4).click();
	//		Thread.sleep(2000);
	//		ArrayList<String> screenName = new ArrayList<String>();
	//		ArrayList<String> elementId = new ArrayList<String>();
	//		ArrayList<String> stringInfo = new ArrayList<String>();
	//		ArrayList<String> temp = new ArrayList<String>();
	//		Point lastElementLocation = null;
	//		while (temp.size()<10) {
	//			System.out.println("tmp size "+temp.size());
	//			List<MobileElement> medals = driver.findElementById("recycler_view").findElementsByClassName("android.widget.LinearLayout");
	//			for (int i = 0; i < medals.size(); i++) {
	//				try {
	//					MobileElement ml = medals.get(i).findElementById("badge_name_tv");
	//					if (!temp.contains(ml.getText())) {
	//						stringInfo.add(ml.getText());
	//						temp.add(ml.getText());
	//						lastElementLocation = ml.getLocation();
	//						System.out.println("temp ["+i+"] "+ml.getText());
	//					}else {
	////						System.out.println("Duplicate Value");
	//					}
	//				} catch (Exception e) {
	//					System.out.println("Unable To Get Badge Namme");
	//				}
	//			}
	//			new TouchAction(driver).press(PointOption.point(lastElementLocation)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(menuSettings.getTargetPositionOfToolbar("toolbar").getX(),menuSettings.getTargetPositionOfToolbar("toolbar").getY()+20)).release().perform();
	//		}
	//		
	//		StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//		model.setScreenName(screenName);
	//		model.setElementsId(elementId);
	//		model.setStringsOfScreen(stringInfo);
	//		stringsOfScreenToExcel.add(model);
	//		support.backToHomeScreen();
	//		System.out.println("==---Case 25---===");
	//		for (int m = 0; m < stringsOfScreenToExcel.size(); m++) {
	//			for (int n = 0; n < stringsOfScreenToExcel.get(m).getStringsOfScreen().size(); n++) {
	//				System.out.println("==------==="+stringsOfScreenToExcel.get(m).getStringsOfScreen().get(n));
	//			}
	//		}
	//		} catch (Exception e) {
	//			support.backToHomeScreen();
	//		}
	//	}
	//
	//	@Test (priority=72)
	//	public void Medals_ALL_String_Test_Malay() throws Exception {
	//		try {
	//		driver.findElementById("btn_navigation_menu_left").click();
	//		Thread.sleep(1000);
	//		driver.findElementsById("tv_phrase_name").get(4).click();
	//		Thread.sleep(2000);
	//		ArrayList<String> screenName = new ArrayList<String>();
	//		ArrayList<String> elementId = new ArrayList<String>();
	//		ArrayList<String> stringInfo = new ArrayList<String>();
	//		Point lastElementLocation = null;
	//		ArrayList<String> items = new ArrayList<String>();
	//		ArrayList<MainAndSubStringStoreModel> finalDataOfMedal = new ArrayList<MainAndSubStringStoreModel>();
	//		int asd = 0;
	//		while (asd<4) {
	//			System.out.println("tmp size "+items.size());
	//			List<MobileElement> medals = driver.findElementById("recycler_view").findElementsByClassName("android.widget.LinearLayout");
	//			for (int i = 0; i < medals.size(); i++) {
	//				try {
	//					MobileElement ml = medals.get(i).findElementById("badge_name_tv");
	//					String mainMenu = null;
	//					if (!items.contains(ml.getText())) {
	//						mainMenu = ml.getText();
	//						items.add(ml.getText());
	//						lastElementLocation = ml.getLocation();
	//						System.out.println("items ["+i+"] "+ml.getText());
	//						ml.click();
	//						if (!driver.currentActivity().contains(".badge.activity.BadgeDetailsActivity")) {
	//							System.out.println("Details Activity Not Available !!");
	//							ArrayList<String> tmpDetails = new ArrayList<String>();
	//							tmpDetails.add("Not Achieved");
	//							MainAndSubStringStoreModel msl = new MainAndSubStringStoreModel(mainMenu, tmpDetails);
	//							msl.setMainString(mainMenu);
	//							msl.setSubStringList(tmpDetails);
	//							finalDataOfMedal.add(msl);
	//						}else {
	//							Thread.sleep(2000);
	//							//						System.out.println("driver.currentActivity()"+driver.currentActivity());
	//							ArrayList<String> tmpDetails = new ArrayList<String>();
	//							for (int j = 98; j <= 103; j++) {
	//								try {
	//									MobileElement dtls = driver.findElementById(ElementsIdInExcel.get(j));
	//									if (!tmpDetails.contains(dtls.getText())) {
	//										tmpDetails.add(dtls.getText());
	//										System.out.println("details values - "+dtls.getText());
	//									}else {
	//										System.out.println("Duplicate Details Elements!!");
	//									}
	//								} catch (Exception e) {
	//									// TODO: handle exception
	//								}
	//							}
	//							try {
	//								List<MobileElement> newElements = driver.findElementById("badge_recycler_view").findElementsByClassName("android.widget.LinearLayout");
	//								System.out.println("New Elements Count"+newElements.size());
	//								if (newElements.size()>0) {
	//									new TouchAction(driver).press(PointOption.point(lastElementLocation)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(menuSettings.getTargetPositionOfToolbar("toolbar").getX(),menuSettings.getTargetPositionOfToolbar("toolbar").getY()+20)).release().perform();
	//									for (int rl = 0; rl < newElements.size(); rl++) {
	//										try {
	//											MobileElement m1 = newElements.get(i).findElementById("badge_name_tv");
	//											//										MobileElement m2 = newElements.get(i).findElementById("badge_acquired_date_tv");
	//											if (!tmpDetails.contains(m1.getText())) {
	//												tmpDetails.add(m1.getText());
	//												System.out.println("details values - "+m1.getText());
	//											}else {
	//												System.out.println("Duplicate Details Elements Recycler View !!");
	//											}
	//										} catch (Exception e) {
	//											// TODO: handle exception
	//										}									
	//									}
	//								}else {
	//									System.out.println("No Others Badge Found In Details");
	//								}
	//							} catch (Exception e) {
	//								// TODO: handle exception
	//							}
	//							driver.findElementById("iv_top_back").click();
	//							Thread.sleep(2000);
	//							medals = driver.findElementById("recycler_view").findElementsByClassName("android.widget.LinearLayout");
	//							for (int lo = 0; lo < tmpDetails.size(); lo++) {
	//								System.out.println("-------- Sub : "+tmpDetails.get(lo));
	//							}
	//							MainAndSubStringStoreModel msl = new MainAndSubStringStoreModel(mainMenu, tmpDetails);
	//							msl.setMainString(mainMenu);
	//							msl.setSubStringList(tmpDetails);
	//							finalDataOfMedal.add(msl);
	//						}
	//					}else {
	//						//						System.out.println("Duplicate Value");
	//					}
	//				} catch (Exception e) {
	//					System.out.println("Unable To Get Badge Namme");
	//				}
	//			}
	//			new TouchAction(driver).press(PointOption.point(lastElementLocation.getX(),lastElementLocation.getY()-20)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(menuSettings.getTargetPositionOfToolbar("toolbar").getX(),menuSettings.getTargetPositionOfToolbar("toolbar").getY()+20)).release().perform();
	//			asd++;
	//		}
	//		System.out.println("==---Case 21---===");
	//		for (int kk = 0; kk < finalDataOfMedal.size(); kk++) {
	//			System.out.println("Main : "+finalDataOfMedal.get(kk).getMainString());
	//			for (int ll = 0; ll < finalDataOfMedal.get(kk).getSubStringList().size(); ll++) {
	//				screenName.add(finalDataOfMedal.get(kk).getMainString());
	//				elementId.add("NO_NEED");
	//				stringInfo.add(finalDataOfMedal.get(kk).getSubStringList().get(ll));
	//				System.out.println("-------- Sub : "+finalDataOfMedal.get(kk).getSubStringList().get(ll));
	//			}
	//		}
	//				StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//				model.setScreenName(screenName);
	//				model.setElementsId(elementId);
	//				model.setStringsOfScreen(stringInfo);
	//				stringsOfScreenToExcel.add(model);
	//				support.backToHomeScreen();
	//				System.out.println("===================== All String ================== ");
	//				for (int m = 0; m < stringsOfScreenToExcel.size(); m++) {
	//					for (int n = 0; n < stringsOfScreenToExcel.get(m).getStringsOfScreen().size(); n++) {
	//						System.out.println(stringsOfScreenToExcel.get(m).getStringsOfScreen().get(n));
	//					}
	//				}
	//				System.out.println("===================== All String ================== ");
	//		} catch (Exception e) {
	//			support.backToHomeScreen();
	//		}
	//	}
	//	
	//	@Test (priority=73)
	//	public void Special_Scenario_One_Malay() throws Exception {
	//		ArrayList<String> special =  new ArrayList<String>();
	//		try {
	//		driver.findElementById("btn_navigation_menu_left").click();
	//		Thread.sleep(1000);
	//		driver.findElementsById("tv_phrase_name").get(1).click();
	//		Thread.sleep(2000);
	//		ArrayList<String> screenName = new ArrayList<String>();
	//		ArrayList<String> elementId = new ArrayList<String>();
	//		ArrayList<String> stringInfo = new ArrayList<String>();
	//		
	//		try {
	//			special.add(driver.findElementById("tvLanguageShooting").getText());
	//			driver.findElementById("tvLanguageShooting").click();
	//			Thread.sleep(3000);
	//			special.add(driver.findElementById("toolbar_title").getText());
	//			driver.findElementById("fab_microphone").click();
	//			Thread.sleep(3000);
	//			special.add(driver.findElementById("tv_top_title").getText());
	//			special.add(driver.findElementById("tv_info").getText());
	//		} catch (Exception e) {
	//			// TODO: handle exception
	//		}
	//		if (special.size()>0) {
	//			for (int i = 0; i < special.size(); i++) {
	//				screenName.add("Camera_One");
	//				elementId.add("NA");
	//				stringInfo.add(special.get(i));
	//			}
	//		}else {
	//			//
	//		}
	//		
	//		StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//		model.setScreenName(screenName);
	//		model.setElementsId(elementId);
	//		model.setStringsOfScreen(stringInfo);
	//		stringsOfScreenToExcel.add(model);
	//		support.backToHomeScreen();
	//		} catch (Exception e) {
	//			support.backToHomeScreen();
	//		}
	//	}
	//
	//	@Test (priority=74)
	//	public void Special_Scenario_TWO_Malay() throws Exception {
	//		ArrayList<String> special =  new ArrayList<String>();
	//		try {
	//		driver.findElementById("btn_navigation_menu_left").click();
	//		Thread.sleep(1000);
	//		driver.findElementsById("tv_phrase_name").get(1).click();
	//		Thread.sleep(2000);
	//		ArrayList<String> screenName = new ArrayList<String>();
	//		ArrayList<String> elementId = new ArrayList<String>();
	//		ArrayList<String> stringInfo = new ArrayList<String>();
	//		try {
	//			new PTSSupportCamera(driver, packageName).captureImage();
	//			Thread.sleep(3000);
	//			driver.findElementById("save").click();
	//			Thread.sleep(10000);
	//			special.add(driver.findElementById("android:id/message").getText());
	//		} catch (Exception e) {
	//			// TODO: handle exception
	//		}
	//		if (special.size()>0) {
	//			for (int i = 0; i < special.size(); i++) {
	//				screenName.add("Camera_Two");
	//				elementId.add("NA");
	//				stringInfo.add(special.get(i));
	//			}
	//		}else {
	//			//
	//		}
	//		StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//		model.setScreenName(screenName);
	//		model.setElementsId(elementId);
	//		model.setStringsOfScreen(stringInfo);
	//		stringsOfScreenToExcel.add(model);
	//		support.backToHomeScreen();
	//		} catch (Exception e) {
	//			support.backToHomeScreen();
	//		}
	//	}
	//	
	//	@Test (priority=75)
	//	public void Special_Scenario_Three_Malay() throws Exception {
	//		ArrayList<String> special =  new ArrayList<String>();
	//		try {
	//			driver.findElementById("layout_gradient_top").click();
	//			Thread.sleep(1000);
	//		ArrayList<String> screenName = new ArrayList<String>();
	//		ArrayList<String> elementId = new ArrayList<String>();
	//		ArrayList<String> stringInfo = new ArrayList<String>();
	//		try {
	//			special.add(driver.findElementById("toolbar_title").getText());
	//			driver.findElementById("ib_language").click();
	//			Thread.sleep(2000);
	//			special.add(driver.findElementById("toolbar_title_other_language").getText());
	//			special.add(driver.findElementById("display_in_english_tv").getText());
	//			driver.findElementById("fab_microphone").click();
	//			Thread.sleep(3000);
	//			special.add(driver.findElementById("tv_top_title").getText());
	//			special.add(driver.findElementById("tv_info").getText());
	//			
	//		} catch (Exception e) {
	//			// TODO: handle exception
	//		}
	//		if (special.size()>0) {
	//			for (int i = 0; i < special.size(); i++) {
	//				screenName.add("Voice_Translation");
	//				elementId.add("NA");
	//				stringInfo.add(special.get(i));
	//			}
	//		}else {
	//			//
	//		}
	//		StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//		model.setScreenName(screenName);
	//		model.setElementsId(elementId);
	//		model.setStringsOfScreen(stringInfo);
	//		stringsOfScreenToExcel.add(model);
	//		support.backToHomeScreen();
	//		} catch (Exception e) {
	//			support.backToHomeScreen();
	//		}
	//	}
	//	
	//	@Test (priority=76)
	//	public void Convert_Units_Malay() throws Exception {
	//		ArrayList<String> special =  new ArrayList<String>();
	//		try {
	//			driver.findElementById("btn_navigation_menu_left").click();
	//			Thread.sleep(1000);
	//			driver.findElementsById("tv_phrase_name").get(2).click();
	//			Thread.sleep(6000);
	//		ArrayList<String> screenName = new ArrayList<String>();
	//		ArrayList<String> elementId = new ArrayList<String>();
	//		ArrayList<String> stringInfo = new ArrayList<String>();
	//		try {
	//			special.add(driver.findElementById("toolbar_title").getText());
	//			driver.findElementById("t9_key_unit_selection").click();
	//			Thread.sleep(2000);
	//			special.add(driver.findElementById("toolbar_title").getText());
	//			List<MobileElement> elm = driver.findElementsByXPath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout");
	//			for (int i = 0; i < elm.size(); i++) {
	//				try {
	//					special.add(elm.get(i).findElementById("tv_calc_unitName").getText());
	//				} catch (Exception e) {
	//					// TODO: handle exception
	//				}
	//			}
	//			
	//		} catch (Exception e) {
	//			// TODO: handle exception
	//		}
	//		if (special.size()>0) {
	//			for (int i = 0; i < special.size(); i++) {
	//				screenName.add("Convert_Units");
	//				elementId.add("NA");
	//				stringInfo.add(special.get(i));
	//			}
	//		}else {
	//			//
	//		}
	//		StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//		model.setScreenName(screenName);
	//		model.setElementsId(elementId);
	//		model.setStringsOfScreen(stringInfo);
	//		stringsOfScreenToExcel.add(model);
	//		support.backToHomeScreen();
	//		} catch (Exception e) {
	//			support.backToHomeScreen();
	//		}
	//		
	//		System.out.println("===================== All String ================== ");
	//		for (int m = 0; m < stringsOfScreenToExcel.size(); m++) {
	//			for (int n = 0; n < stringsOfScreenToExcel.get(m).getStringsOfScreen().size(); n++) {
	//				System.out.println(stringsOfScreenToExcel.get(m).getStringsOfScreen().get(n));
	//			}
	//		}
	//		System.out.println("===================== All String ================== ");
	//	}
	//	
	//	@Test (priority=77)
	//	public void Demo_Mode_ON_Malay() throws Exception {
	//		ArrayList<String> special =  new ArrayList<String>();
	//		try {
	//			driver.findElementById("btn_navigation_menu_left").click();
	//			Thread.sleep(1000);
	//			driver.findElementsById("tv_phrase_name").get(0).click();
	//			Thread.sleep(2000);
	//		ArrayList<String> screenName = new ArrayList<String>();
	//		ArrayList<String> elementId = new ArrayList<String>();
	//		ArrayList<String> stringInfo = new ArrayList<String>();
	//		
	//		try {
	//			Thread.sleep(1000);
	//			MobileElement setLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/settingsRecyclerView\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+settingsMenuStored.get(13)+"\"));"));
	//			new TouchAction(driver).longPress(PointOption.point(setLanguage.getLocation())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(10000))).release().perform();
	//			Thread.sleep(500);
	//			special.add(driver.findElementById("android:id/message").getText());
	//			special.add(driver.findElementById("android:id/button1").getText());
	//			special.add(driver.findElementById("android:id/button2").getText());
	//			driver.findElementById("android:id/button1").click();
	//			Thread.sleep(2000);
	//			MobileElement setLanguage2 = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/settingsRecyclerView\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+settingsMenuStored.get(13)+"\"));"));
	//			new TouchAction(driver).longPress(PointOption.point(setLanguage2.getLocation())).waitAction(WaitOptions.waitOptions(Duration.ofMillis(10000))).release().perform();
	//			Thread.sleep(1000);
	//			special.add(driver.findElementById("android:id/message").getText());
	//			special.add(driver.findElementById("android:id/button1").getText());
	//			special.add(driver.findElementById("android:id/button2").getText());
	//			driver.findElementById("android:id/button1").click();
	//		} catch (Exception e) {
	//			
	//		}
	//		if (special.size()>0) {
	//			for (int i = 0; i < special.size(); i++) {
	//				screenName.add("Demo_Mode");
	//				elementId.add("NA");
	//				stringInfo.add(special.get(i));
	//			}
	//		}else {
	//			//
	//		}
	//		StringsOfScreenModel model = new StringsOfScreenModel(screenName, elementId, stringInfo);
	//		model.setScreenName(screenName);
	//		model.setElementsId(elementId);
	//		model.setStringsOfScreen(stringInfo);
	//		stringsOfScreenToExcel.add(model);
	//		support.backToHomeScreen();
	//		} catch (Exception e) {
	//			support.backToHomeScreen();
	//		}
	//		
	//		System.out.println("===================== All String ================== ");
	//		for (int m = 0; m < stringsOfScreenToExcel.size(); m++) {
	//			for (int n = 0; n < stringsOfScreenToExcel.get(m).getStringsOfScreen().size(); n++) {
	//				System.out.println(stringsOfScreenToExcel.get(m).getStringsOfScreen().get(n));
	//			}
	//		}
	//		System.out.println("===================== All String ================== ");
	//		String filePath = System.getProperty("user.dir");
	//		this.writeActualStrings(filePath, "ActualStrings.xlsx",selectedSystemLanguage);
	//	}

	public void writeActualStrings(String filePath,String fileName, String outputSheetName) throws IOException{
		File file =    new File(filePath+"\\"+fileName);
		FileInputStream inputStream = new FileInputStream(file);

		Workbook myWorkbook = null;

		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			myWorkbook = new XSSFWorkbook(inputStream);
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String datetime = formatter.format(calendar.getTime());
			datetime = datetime.replaceAll("[:-]", "").replaceAll("[ ]", "_");
			String sheetName = myWorkbook.createSheet(outputSheetName+"_"+datetime).getSheetName();
			XSSFSheet sheet = (XSSFSheet) myWorkbook.getSheet(sheetName);
			//			System.out.println("stringsOfScreenToExcel.size() "+stringsOfScreenToExcel.size());
			int m =0;
			for (int i = 0; i < stringsOfScreenToExcel.size(); i++) {
				for (int j = 0; j < stringsOfScreenToExcel.get(i).getScreenNames().size(); j++) {
					Row newRow = sheet.createRow(m);
					Cell screenName = newRow.createCell(0);
					screenName.setCellValue(stringsOfScreenToExcel.get(i).getScreenNames().get(j));
					Cell elementId = newRow.createCell(1);
					elementId.setCellValue(stringsOfScreenToExcel.get(i).getElementsId().get(j));
					Cell actualString = newRow.createCell(2);
					actualString.setCellValue(stringsOfScreenToExcel.get(i).getStringsOfScreen().get(j));
					m++;
				}
			}
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(file);
			myWorkbook.write(outputStream);
			outputStream.close();
		}else if(fileExtensionName.equals(".xls")){
			myWorkbook = new HSSFWorkbook(inputStream);
			System.out.println("XLS file");
		}    
	}

	public void readStringInformation(String filePath,String fileName,String sheetName) throws IOException{

		File file =    new File(filePath+"\\"+fileName);

		FileInputStream inputStream = new FileInputStream(file);

		Workbook guru99Workbook = null;
		XSSFSheet XLSX = null;
		HSSFSheet XLS = null;

		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		DataFormatter formatter = new DataFormatter();
		
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
						ExcelHeaderOfUiInfo.add(cell.getStringCellValue());
					}else {
						if (cell.getColumnIndex()==0) {
							ScreenNameInExcel.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==2) {
							ElementsIdInExcel.add(cell.getStringCellValue());
						}else if (cell.getColumnIndex()==4) {
							//						ExpectedStringInExcel.add(cell.get);
						}else  {
							System.out.println("Something Wrong While Read Excel...");
						}
					}
				}
			}
			System.out.println(ExcelHeaderOfUiInfo);
		}	    
	}



	public Point getElementsLastPosition(String elementId) {
		int pintX = driver.findElement(By.id(elementId)).getLocation().getX();
		int pintY = driver.findElement(By.id(elementId)).getLocation().getY();
		int width = driver.findElement(By.id(elementId)).getSize().getWidth();
		int height = driver.findElement(By.id(elementId)).getSize().getHeight();
		Point p= new Point(pintX+width, pintY+height);
		return p;
	}
}
