import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class PTW_Language_List {
	private  ArrayList<String> backButtons = new ArrayList<String>();
	private static String errorImagePath = System.getProperty("user.dir")+"\\Screenshots\\";
	public static String packageName = "com.sourcenext.pocketalk";
	static AndroidDriver<MobileElement> driver;
	public ArrayList<String> LanguageListHeader = new ArrayList<String>();
	public ArrayList<String> expectedLanIn_Malay = new ArrayList<String>();
	public static ArrayList<String> ActualhomeTopLanguageMalay = new ArrayList<String>();
	public static ArrayList<ModelSubCountryList> countryLanguages_English = new ArrayList<ModelSubCountryList>();
	public static ArrayList<MainAndSubStringStoreModel> countryName_English = new ArrayList<MainAndSubStringStoreModel>();
	
	@BeforeSuite

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
		backButtons.add("btn_navigation_menu_left");
		backButtons.add("btn_navigation_menu");
		backButtons.add("ib_back");
		backButtons.add("iv_top_back");
		backButtons.add("ib_top_back");
		backButtons.add("ib_top_back_lang_selection");
		backButtons.add("discard");
		backButtons.add("close_dialog");
		backButtons.add("imagebutton_top_back");
		backButtons.add("btn_ok");
		backButtons.add("//android.widget.LinearLayout/android.widget.Button");
		backButtons.add("android:id/button1");

	}

	@AfterSuite
	public void tearDown() throws IOException {
		System.out.println("Country and Language Together ================================== ");
		this.writeMainAndSubStrings(System.getProperty("user.dir"), "Country_Specific_Language", "countryName_English",countryName_English);
		driver.quit();
	}

	// Voice Translation Language List Start below

	//	@Test(priority=1)
	//	public void English_Home_Top_Language_List() throws Exception{
	//		System.out.println("TestCase 1 :: English_Home_Top_Language_List Started");
	//		Thread.sleep(1000);
	//		try {
	//			driver.findElementById("btn_ok").click();
	//		} catch (Exception e) {
	//			System.out.println("first alert not appear...");
	//		}
	//
	//		driver.findElementById("btn_navigation_menu").click();
	//		Thread.sleep(1000);
	//		selectSystemLanguageOption("System Language","한국어로 사용하다");
	//		Thread.sleep(6000);
	//		driver.findElementById("btn_native").click();
	//		Thread.sleep(2000);
	////		String lastLanguageName ="越南文"; // china Mode
	//		String lastLanguageName ="힌디어"; // Normal Mode
	//		ArrayList<String> LanguageList = new ArrayList<>();
	//		String languageName = "";
	//		String lastMenuFound = "";
	//
	//		int tbsPosition= driver.findElementById("tabs").getLocation().getY();
	//		System.out.println(tbsPosition);
	//		int tbsHeight=driver.findElementById("viewpager").getLocation().getY();
	//		System.out.println(tbsHeight);
	//		int target=tbsPosition+tbsHeight;
	//		System.out.println(target);
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
	//					//              	System.out.println("running second time");
	//				}
	//				int itemsY=allChats.get(i).getSize().getHeight();
	//
	//				if (checkValue==itemsY) {
	//					languageName=allChats.get(i).findElementById("lan_name").getText();
	//					if (LanguageList.contains(languageName)) {
	//						//							System.out.println(languageName+" exists");
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
	//						//                      	System.out.println("Last Menu NOT Found");
	//					}
	//				}else {
	//					//						System.out.println("chkvlue itemsY not matched");
	//				}
	//			}
	//			new TouchAction(driver).press(PointOption.point(last)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(last.getX(),target+checkValue)).release().perform();
	//			firsttime=0;
	//		}
	//		System.out.println("Home Language Total France :"+LanguageList.size());
	//
	//		for (int L = 0; L < LanguageList.size(); L++) {
	//			System.out.println(LanguageList.get(L));
	//			ActualhomeTopLanguageChinese.add(LanguageList.get(L));
	//		}
	//		System.out.println("===================================");
	//		driver.findElementById("ib_top_back_lang_selection").click();
	//		this.backToHomeScreen();
	//	}

	@Test(priority=11)
	public void Country_Specific_Language_English() throws Exception{
		System.out.println("TestCase 11 :: Country_Specific_Language_English Started ");
		try {
			driver.findElementById("btn_navigation_menu").click();
			Thread.sleep(1000);
			selectSystemLanguageOption("System Language","Use in English");
			Thread.sleep(6000);
			driver.findElementById("btn_native").click();
			Thread.sleep(1000);
			driver.findElementById("ib_language").click();
			Thread.sleep(1500);
			String lastCountryName ="Zimbabwe";
			ArrayList<String> CountryList = new ArrayList<>();
			String countryName = "";
			String lastCountryFound = "";
			int tbsHeight= driver.findElementById("toolbar").getSize().getHeight();
			int tbsWidth= driver.findElementById("toolbar").getSize().getWidth();
			//    System.out.println(tbsHeight+"--"+tbsWidth);
			int firsttime=1;
			while (lastCountryFound.isEmpty()) {
				List<MobileElement> gridViewElements = driver.findElementsById("item_parent_layout");
				int count=gridViewElements.size();
				int gridViewSize=0;
				int targetPosition;
				Point sourcePosition=null;
				for (int i = 0; i < count; i++) {
					ArrayList<String> temp = new ArrayList<String>();
					int itemSize = gridViewElements.get(i).getSize().getHeight();
					//				System.out.print(itemSize);
					if (75==itemSize) {
						countryName= gridViewElements.get(i).findElementById("grid_item_label").getText();
						Thread.sleep(500);
						if (CountryList.contains(countryName)) {
							//					System.out.println(countryName+" exists"+i);
						}else {
							CountryList.add(countryName);
							System.out.print(countryName+" : ");
							gridViewElements.get(i).findElementById("grid_item_label").click();
							int asd = 0;
							ArrayList<String> checked = new ArrayList<String>();
							while (asd<2) {
								List<MobileElement> languagesView = driver.findElementById("recycler_view").findElementsById("parentLayout");
								
								if (languagesView.size()<5) {
									for (int j = 0; j < languagesView.size(); j++) {
										try {
											asd = 2;
											String lnname = languagesView.get(j).findElementById("lan_name").getText();
											String languageStrings[] = lnname.split(" ", 2);
											String chkString = languagesView.get(j).findElementById("lan_name").getText().replaceAll("[ ]", "_");
											if (!checked.contains(chkString)) {
												checked.add(chkString);
												temp.add(languageStrings[0]);
												temp.add(lnname);
												System.out.print(lnname+" , ");
											}else {}
										} catch (Exception e) {}
									}
									System.out.println();
								}else {
									for (int j = 0; j < languagesView.size(); j++) {
										try {
											String lnname = languagesView.get(j).findElementById("lan_name").getText();
											String languageStrings[] = lnname.split(" ", 2);
											String chkString = languagesView.get(j).findElementById("lan_name").getText().replaceAll("[ ]", "_");
											if (!checked.contains(chkString)) {
												checked.add(chkString);
												temp.add(languageStrings[0]);
												temp.add(lnname);
												System.out.print(lnname+" , ");
											}else {}
										} catch (Exception e) {}
									}
									System.out.println();
									asd++;
									new TouchAction(driver).press(PointOption.point(200,300)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(200,35)).release().perform();
									Thread.sleep(100);
								}
							}
							driver.findElementById("ib_top_back_lang_selection").click();
							Thread.sleep(500);
							MainAndSubStringStoreModel ms = new MainAndSubStringStoreModel(countryName, temp);
							ms.setMainString(countryName);
							ms.setSubStringList(temp);
							countryName_English.add(ms);
							try {
								gridViewElements=driver.findElementsById("item_parent_layout");
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
						gridViewElements=driver.findElementsById("item_parent_layout");
						sourcePosition = gridViewElements.get(i).getLocation();
						if (countryName.toLowerCase().contains(lastCountryName.toLowerCase())) {
							lastCountryFound = "Yes";
							System.out.println("Last Menu Found");
							break;
						}else {
							//          	System.out.println("Last Menu NOT Found");
						}
					}else {
						//					System.out.println("Size not matched"+gridViewSize+"---->"+itemSize);
					}
					Thread.sleep(800);
				}
				new TouchAction(driver).press(PointOption.point(sourcePosition.getX(),sourcePosition.getY()-20)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(220,30)).release().perform();
				Thread.sleep(100);
			}
			Thread.sleep(800);
			driver.findElementById("ib_top_back_lang_selection").click();
			Thread.sleep(800);
			driver.findElementById("ib_top_back").click();
		} catch (Exception e) {
	this.writeMainAndSubStrings(System.getProperty("user.dir"), "Country_Specific_Language_English", "English",countryName_English);
			this.backToHomeScreen();
		}
	}

	@Test(priority=12)
	public void Country_Specific_Language_Japanese() throws Exception{
		System.out.println("TestCase 12 :: Country_Specific_Language_Japanese Started ");
		try {
			driver.findElementById("btn_navigation_menu").click();
			Thread.sleep(1000);
			selectSystemLanguageOption("System Language","日本語で操作する");
			Thread.sleep(6000);
			driver.findElementById("btn_native").click();
			Thread.sleep(1000);
			driver.findElementById("ib_language").click();
			Thread.sleep(2000);
			String lastCountryName ="ロシア連邦";
			ArrayList<String> CountryList = new ArrayList<>();
			String countryName = "";
			String lastCountryFound = "";
			int tbsHeight= driver.findElementById("toolbar").getSize().getHeight();
			int tbsWidth= driver.findElementById("toolbar").getSize().getWidth();
			//    System.out.println(tbsHeight+"--"+tbsWidth);
			int firsttime=1;
			while (lastCountryFound.isEmpty()) {
				List<MobileElement> gridViewElements = driver.findElementsById("item_parent_layout");
				int count=gridViewElements.size();
				int gridViewSize=0;
				int targetPosition;
				Point sourcePosition=null;
				for (int i = 0; i < count; i++) {
					ArrayList<String> temp = new ArrayList<String>();
					int itemSize = gridViewElements.get(i).getSize().getHeight();
					//				System.out.print(itemSize);
					if (75==itemSize) {
						countryName= gridViewElements.get(i).findElementById("grid_item_label").getText();
						Thread.sleep(500);
						if (CountryList.contains(countryName)) {
							//					System.out.println(countryName+" exists"+i);
						}else {
							CountryList.add(countryName);
							System.out.print(countryName+" : ");
							gridViewElements.get(i).findElementById("grid_item_label").click();
							int asd = 0;
							ArrayList<String> checked = new ArrayList<String>();
							while (asd<2) {
								List<MobileElement> languagesView = driver.findElementById("recycler_view").findElementsById("parentLayout");
								if (languagesView.size()<5) {
									for (int j = 0; j < languagesView.size(); j++) {
										try {
											asd = 2;
											String lnname = languagesView.get(j).findElementById("lan_name").getText();
											String languageStrings[] = lnname.split(" ", 2);
											String chkString = languagesView.get(j).findElementById("lan_name").getText().replaceAll("[ ]", "_");
											if (!checked.contains(chkString)) {
												checked.add(chkString);
												temp.add(languageStrings[0]);
												temp.add(lnname);
												System.out.print(lnname+" , ");
											}else {}
										} catch (Exception e) {}
									}
									System.out.println();
								}else {
									for (int j = 0; j < languagesView.size(); j++) {
										try {
											String lnname = languagesView.get(j).findElementById("lan_name").getText();
											String languageStrings[] = lnname.split(" ", 2);
											String chkString = languagesView.get(j).findElementById("lan_name").getText().replaceAll("[ ]", "_");
											if (!checked.contains(chkString)) {
												checked.add(chkString);
												temp.add(languageStrings[0]);
												temp.add(lnname);
												System.out.print(lnname+" , ");
											}else {}
										} catch (Exception e) {}
									}
									System.out.println();
									asd++;
									new TouchAction(driver).press(PointOption.point(200,300)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(200,35)).release().perform();
									Thread.sleep(100);
								}
							}
							driver.findElementById("ib_top_back_lang_selection").click();
							Thread.sleep(500);
							MainAndSubStringStoreModel ms = new MainAndSubStringStoreModel(countryName, temp);
							ms.setMainString(countryName);
							ms.setSubStringList(temp);
							countryName_English.add(ms);
							try {
								gridViewElements=driver.findElementsById("item_parent_layout");
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
						gridViewElements=driver.findElementsById("item_parent_layout");
						sourcePosition = gridViewElements.get(i).getLocation();
						if (countryName.toLowerCase().contains(lastCountryName.toLowerCase())) {
							lastCountryFound = "Yes";
							System.out.println("Last Menu Found");
							break;
						}else {
							//          	System.out.println("Last Menu NOT Found");
						}
					}else {
						//					System.out.println("Size not matched"+gridViewSize+"---->"+itemSize);
					}
					Thread.sleep(800);
				}
				new TouchAction(driver).press(PointOption.point(sourcePosition.getX(),sourcePosition.getY()-20)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(220,30)).release().perform();
				Thread.sleep(100);
			}
			Thread.sleep(500);
			driver.findElementById("ib_top_back_lang_selection").click();
			Thread.sleep(500);
			driver.findElementById("ib_top_back").click();
		} catch (Exception e) {
			this.writeMainAndSubStrings(System.getProperty("user.dir"), "Country_Specific_Language_Japanese", "Japanese",countryName_English);
			this.backToHomeScreen();
		}
	}

	@Test(priority=13)
	public void Country_Specific_Language_Deutsch() throws Exception{
		try {
			System.out.println("TestCase 13 :: Country_Specific_Language_Deutsch Started ");
			driver.findElementById("btn_navigation_menu").click();
			Thread.sleep(1000);
			selectSystemLanguageOption("System Language","deutsch");
			Thread.sleep(6000);
			driver.findElementById("btn_native").click();
			Thread.sleep(1000);
			driver.findElementById("ib_language").click();
			Thread.sleep(2000);
			String lastCountryName ="Zypern";
			ArrayList<String> CountryList = new ArrayList<>();
			String countryName = "";
			String lastCountryFound = "";

			int tbsHeight= driver.findElementById("toolbar").getSize().getHeight();
			int tbsWidth= driver.findElementById("toolbar").getSize().getWidth();
			//    System.out.println(tbsHeight+"--"+tbsWidth);
			int firsttime=1;
			while (lastCountryFound.isEmpty()) {
				List<MobileElement> gridViewElements = driver.findElementsById("item_parent_layout");
				int count=gridViewElements.size();
				int gridViewSize=0;
				int targetPosition;
				Point sourcePosition=null;
				for (int i = 0; i < count; i++) {
					ArrayList<String> temp = new ArrayList<String>();
					int itemSize = gridViewElements.get(i).getSize().getHeight();
					//				System.out.print(itemSize);
					if (75==itemSize) {
						countryName= gridViewElements.get(i).findElementById("grid_item_label").getText();
						Thread.sleep(500);
						if (CountryList.contains(countryName)) {
							//					System.out.println(countryName+" exists"+i);
						}else {
							CountryList.add(countryName);
							System.out.print(countryName+" : ");
							gridViewElements.get(i).findElementById("grid_item_label").click();
							int asd = 0;
							ArrayList<String> checked = new ArrayList<String>();
							while (asd<2) {
								List<MobileElement> languagesView = driver.findElementById("recycler_view").findElementsById("parentLayout");
								if (languagesView.size()<5) {
									for (int j = 0; j < languagesView.size(); j++) {
										try {
											asd = 2;
											String lnname = languagesView.get(j).findElementById("lan_name").getText();
											String languageStrings[] = lnname.split(" ", 2);
											String chkString = languagesView.get(j).findElementById("lan_name").getText().replaceAll("[ ]", "_");
											if (!checked.contains(chkString)) {
												checked.add(chkString);
												temp.add(languageStrings[0]);
												temp.add(lnname);
												System.out.print(lnname+" , ");
											}else {}
										} catch (Exception e) {}
									}
									System.out.println();
								}else {
									for (int j = 0; j < languagesView.size(); j++) {
										try {
											String lnname = languagesView.get(j).findElementById("lan_name").getText();
											String languageStrings[] = lnname.split(" ", 2);
											String chkString = languagesView.get(j).findElementById("lan_name").getText().replaceAll("[ ]", "_");
											if (!checked.contains(chkString)) {
												checked.add(chkString);
												temp.add(languageStrings[0]);
												temp.add(lnname);
												System.out.print(lnname+" , ");
											}else {}
										} catch (Exception e) {}
									}
									System.out.println();
									asd++;
									new TouchAction(driver).press(PointOption.point(200,300)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(200,35)).release().perform();
									Thread.sleep(100);
								}
							}
							driver.findElementById("ib_top_back_lang_selection").click();
							Thread.sleep(500);
							MainAndSubStringStoreModel ms = new MainAndSubStringStoreModel(countryName, temp);
							ms.setMainString(countryName);
							ms.setSubStringList(temp);
							countryName_English.add(ms);
							try {
								gridViewElements=driver.findElementsById("item_parent_layout");
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
						gridViewElements=driver.findElementsById("item_parent_layout");
						sourcePosition = gridViewElements.get(i).getLocation();
						if (countryName.toLowerCase().contains(lastCountryName.toLowerCase())) {
							lastCountryFound = "Yes";
							System.out.println("Last Menu Found");
							break;
						}else {
							//          	System.out.println("Last Menu NOT Found");
						}
					}else {
						//					System.out.println("Size not matched"+gridViewSize+"---->"+itemSize);
					}
					Thread.sleep(500);
				}
				new TouchAction(driver).press(PointOption.point(sourcePosition.getX(),sourcePosition.getY()-20)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(220,30)).release().perform();
				Thread.sleep(100);
			}
			Thread.sleep(500);
			driver.findElementById("ib_top_back_lang_selection").click();
			Thread.sleep(500);
			driver.findElementById("ib_top_back").click();
		} catch (Exception e) {
			this.backToHomeScreen();
			this.writeMainAndSubStrings(System.getProperty("user.dir"), "Country_Specific_Language_Dutch", "Deutsh",countryName_English);
		}
	}

	@Test(priority=14)
	public void Country_Specific_Language_Espanol() throws Exception{
		try {
			System.out.println("TestCase 14 :: Country_Specific_Language_Espanol ");
			driver.findElementById("btn_navigation_menu").click();
			Thread.sleep(1000);
			selectSystemLanguageOption("System Language","español");
			Thread.sleep(6000);
			driver.findElementById("btn_native").click();
			Thread.sleep(1000);
			driver.findElementById("ib_language").click();
			Thread.sleep(2000);
			String lastCountryName ="Zimbabue";
			ArrayList<String> CountryList = new ArrayList<>();
			String countryName = "";
			String lastCountryFound = "";

			int tbsHeight= driver.findElementById("toolbar").getSize().getHeight();
			int tbsWidth= driver.findElementById("toolbar").getSize().getWidth();
			//    System.out.println(tbsHeight+"--"+tbsWidth);
			int firsttime=1;
			while (lastCountryFound.isEmpty()) {
				List<MobileElement> gridViewElements = driver.findElementsById("item_parent_layout");
				int count=gridViewElements.size();
				int gridViewSize=0;
				int targetPosition;
				Point sourcePosition=null;
				for (int i = 0; i < count; i++) {
					ArrayList<String> temp = new ArrayList<String>();
					int itemSize = gridViewElements.get(i).getSize().getHeight();
					//				System.out.print(itemSize);
					if (75==itemSize) {
						countryName= gridViewElements.get(i).findElementById("grid_item_label").getText();
						Thread.sleep(500);
						if (CountryList.contains(countryName)) {
							//					System.out.println(countryName+" exists"+i);
						}else {
							CountryList.add(countryName);
							System.out.print(countryName+" : ");
							gridViewElements.get(i).findElementById("grid_item_label").click();
							int asd = 0;
							ArrayList<String> checked = new ArrayList<String>();
							while (asd<2) {
								List<MobileElement> languagesView = driver.findElementById("recycler_view").findElementsById("parentLayout");
								if (languagesView.size()<5) {
									for (int j = 0; j < languagesView.size(); j++) {
										try {
											asd = 2;
											String lnname = languagesView.get(j).findElementById("lan_name").getText();
											String languageStrings[] = lnname.split(" ", 2);
											String chkString = languagesView.get(j).findElementById("lan_name").getText().replaceAll("[ ]", "_");
											if (!checked.contains(chkString)) {
												checked.add(chkString);
												temp.add(languageStrings[0]);
												temp.add(lnname);
												System.out.print(lnname+" , ");
											}else {}
										} catch (Exception e) {}
									}
									System.out.println();
								}else {
									for (int j = 0; j < languagesView.size(); j++) {
										try {
											String lnname = languagesView.get(j).findElementById("lan_name").getText();
											String languageStrings[] = lnname.split(" ", 2);
											String chkString = languagesView.get(j).findElementById("lan_name").getText().replaceAll("[ ]", "_");
											if (!checked.contains(chkString)) {
												checked.add(chkString);
												temp.add(languageStrings[0]);
												temp.add(lnname);
												System.out.print(lnname+" , ");
											}else {}
										} catch (Exception e) {}
									}
									System.out.println();
									asd++;
									new TouchAction(driver).press(PointOption.point(200,300)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(200,35)).release().perform();
									Thread.sleep(100);
								}
							}
							driver.findElementById("ib_top_back_lang_selection").click();
							Thread.sleep(1500);
							MainAndSubStringStoreModel ms = new MainAndSubStringStoreModel(countryName, temp);
							ms.setMainString(countryName);
							ms.setSubStringList(temp);
							countryName_English.add(ms);
							try {
								gridViewElements=driver.findElementsById("item_parent_layout");
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
						gridViewElements=driver.findElementsById("item_parent_layout");
						sourcePosition = gridViewElements.get(i).getLocation();
						if (countryName.toLowerCase().contains(lastCountryName.toLowerCase())) {
							lastCountryFound = "Yes";
							System.out.println("Last Menu Found");
							break;
						}else {
							//          	System.out.println("Last Menu NOT Found");
						}
					}else {
						//					System.out.println("Size not matched"+gridViewSize+"---->"+itemSize);
					}
					Thread.sleep(800);
				}
				new TouchAction(driver).press(PointOption.point(sourcePosition.getX(),sourcePosition.getY()-20)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(220,30)).release().perform();
				Thread.sleep(100);
			}
			Thread.sleep(500);
			driver.findElementById("ib_top_back_lang_selection").click();
			Thread.sleep(500);
			driver.findElementById("ib_top_back").click();
		} catch (Exception e) {
			this.backToHomeScreen();
			this.writeMainAndSubStrings(System.getProperty("user.dir"), "Country_Specific_Language_Spanish", "Spanish",countryName_English);
		}
	}

	@Test(priority=15)
	public void Country_Specific_Language_France() throws Exception{
		try {
			System.out.println("TestCase 15 :: Country_Specific_Language_France Started ");
			driver.findElementById("btn_navigation_menu").click();
			Thread.sleep(1000);
			selectSystemLanguageOption("System Language","français");
			Thread.sleep(6000);
			driver.findElementById("btn_native").click();
			Thread.sleep(1000);
			driver.findElementById("ib_language").click();
			Thread.sleep(2000);
			String lastCountryName ="Zimbabwe";
			ArrayList<String> CountryList = new ArrayList<>();
			String countryName = "";
			String lastCountryFound = "";

			int tbsHeight= driver.findElementById("toolbar").getSize().getHeight();
			int tbsWidth= driver.findElementById("toolbar").getSize().getWidth();
			//    System.out.println(tbsHeight+"--"+tbsWidth);
			int firsttime=1;
			while (lastCountryFound.isEmpty()) {
				List<MobileElement> gridViewElements = driver.findElementsById("item_parent_layout");
				int count=gridViewElements.size();
				int gridViewSize=0;
				int targetPosition;
				Point sourcePosition=null;
				for (int i = 0; i < count; i++) {
					ArrayList<String> temp = new ArrayList<String>();
					int itemSize = gridViewElements.get(i).getSize().getHeight();
					//				System.out.print(itemSize);
					if (75==itemSize) {
						countryName= gridViewElements.get(i).findElementById("grid_item_label").getText();
						Thread.sleep(500);
						if (CountryList.contains(countryName)) {
							//					System.out.println(countryName+" exists"+i);
						}else {
							CountryList.add(countryName);
							System.out.print(countryName+" : ");
							gridViewElements.get(i).findElementById("grid_item_label").click();
							int asd = 0;
							ArrayList<String> checked = new ArrayList<String>();
							while (asd<2) {
								List<MobileElement> languagesView = driver.findElementById("recycler_view").findElementsById("parentLayout");
								if (languagesView.size()<5) {
									for (int j = 0; j < languagesView.size(); j++) {
										try {
											asd = 2;
											String lnname = languagesView.get(j).findElementById("lan_name").getText();
											String languageStrings[] = lnname.split(" ", 2);
											String chkString = languagesView.get(j).findElementById("lan_name").getText().replaceAll("[ ]", "_");
											if (!checked.contains(chkString)) {
												checked.add(chkString);
												temp.add(languageStrings[0]);
												temp.add(lnname);
												System.out.print(lnname+" , ");
											}else {}
										} catch (Exception e) {}
									}
									System.out.println();
								}else {
									for (int j = 0; j < languagesView.size(); j++) {
										try {
											String lnname = languagesView.get(j).findElementById("lan_name").getText();
											String languageStrings[] = lnname.split(" ", 2);
											String chkString = languagesView.get(j).findElementById("lan_name").getText().replaceAll("[ ]", "_");
											if (!checked.contains(chkString)) {
												checked.add(chkString);
												temp.add(languageStrings[0]);
												temp.add(lnname);
												System.out.print(lnname+" , ");
											}else {}
										} catch (Exception e) {}
									}
									System.out.println();
									asd++;
									new TouchAction(driver).press(PointOption.point(200,300)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(200,35)).release().perform();
									Thread.sleep(100);
								}
							}
							driver.findElementById("ib_top_back_lang_selection").click();
							Thread.sleep(500);
							MainAndSubStringStoreModel ms = new MainAndSubStringStoreModel(countryName, temp);
							ms.setMainString(countryName);
							ms.setSubStringList(temp);
							countryName_English.add(ms);
							try {
								gridViewElements=driver.findElementsById("item_parent_layout");
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
						gridViewElements=driver.findElementsById("item_parent_layout");
						sourcePosition = gridViewElements.get(i).getLocation();
						if (countryName.toLowerCase().contains(lastCountryName.toLowerCase())) {
							lastCountryFound = "Yes";
							System.out.println("Last Menu Found");
							break;
						}else {
							//          	System.out.println("Last Menu NOT Found");
						}
					}else {
						//					System.out.println("Size not matched"+gridViewSize+"---->"+itemSize);
					}
					Thread.sleep(800);
				}
				new TouchAction(driver).press(PointOption.point(sourcePosition.getX(),sourcePosition.getY()-20)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(220,30)).release().perform();
				Thread.sleep(100);
			}
			Thread.sleep(500);
			driver.findElementById("ib_top_back_lang_selection").click();
			Thread.sleep(500);
			driver.findElementById("ib_top_back").click();
		} catch (Exception e) {
			this.backToHomeScreen();
			this.writeMainAndSubStrings(System.getProperty("user.dir"), "Country_Specific_Language_France", "France",countryName_English);
		}
	}

	@Test(priority=16)
	public void Country_Specific_Language_Italy() throws Exception{
		try {
			System.out.println("TestCase 16 :: Country_Specific_Language_Italy ");
			driver.findElementById("btn_navigation_menu").click();
			Thread.sleep(1000);
			selectSystemLanguageOption("System Language","italia");
			Thread.sleep(6000);
			driver.findElementById("btn_native").click();
			Thread.sleep(1000);
			driver.findElementById("ib_language").click();
			Thread.sleep(2000);
			String lastCountryName ="Zimbabwe";
			ArrayList<String> CountryList = new ArrayList<>();
			String countryName = "";
			String lastCountryFound = "";

			int tbsHeight= driver.findElementById("toolbar").getSize().getHeight();
			int tbsWidth= driver.findElementById("toolbar").getSize().getWidth();
			//    System.out.println(tbsHeight+"--"+tbsWidth);
			int firsttime=1;
			while (lastCountryFound.isEmpty()) {
				List<MobileElement> gridViewElements = driver.findElementsById("item_parent_layout");
				int count=gridViewElements.size();
				int gridViewSize=0;
				int targetPosition;
				Point sourcePosition=null;
				for (int i = 0; i < count; i++) {
					ArrayList<String> temp = new ArrayList<String>();
					int itemSize = gridViewElements.get(i).getSize().getHeight();
					//				System.out.print(itemSize);
					if (75==itemSize) {
						countryName= gridViewElements.get(i).findElementById("grid_item_label").getText();
						Thread.sleep(500);
						if (CountryList.contains(countryName)) {
							//					System.out.println(countryName+" exists"+i);
						}else {
							CountryList.add(countryName);
							System.out.print(countryName+" : ");
							gridViewElements.get(i).findElementById("grid_item_label").click();
							int asd = 0;
							ArrayList<String> checked = new ArrayList<String>();
							while (asd<2) {
								List<MobileElement> languagesView = driver.findElementById("recycler_view").findElementsById("parentLayout");
								if (languagesView.size()<5) {
									for (int j = 0; j < languagesView.size(); j++) {
										try {
											asd = 2;
											String lnname = languagesView.get(j).findElementById("lan_name").getText();
											String languageStrings[] = lnname.split(" ", 2);
											String chkString = languagesView.get(j).findElementById("lan_name").getText().replaceAll("[ ]", "_");
											if (!checked.contains(chkString)) {
												checked.add(chkString);
												temp.add(languageStrings[0]);
												temp.add(lnname);
												System.out.print(lnname+" , ");
											}else {}
										} catch (Exception e) {}
									}
									System.out.println();
								}else {
									for (int j = 0; j < languagesView.size(); j++) {
										try {
											String lnname = languagesView.get(j).findElementById("lan_name").getText();
											String languageStrings[] = lnname.split(" ", 2);
											String chkString = languagesView.get(j).findElementById("lan_name").getText().replaceAll("[ ]", "_");
											if (!checked.contains(chkString)) {
												checked.add(chkString);
												temp.add(languageStrings[0]);
												temp.add(lnname);
												System.out.print(lnname+" , ");
											}else {}
										} catch (Exception e) {}
									}
									System.out.println();
									asd++;
									new TouchAction(driver).press(PointOption.point(200,300)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(200,35)).release().perform();
									Thread.sleep(100);
								}
							}
							driver.findElementById("ib_top_back_lang_selection").click();
							Thread.sleep(500);
							MainAndSubStringStoreModel ms = new MainAndSubStringStoreModel(countryName, temp);
							ms.setMainString(countryName);
							ms.setSubStringList(temp);
							countryName_English.add(ms);
							try {
								gridViewElements=driver.findElementsById("item_parent_layout");
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
						gridViewElements=driver.findElementsById("item_parent_layout");
						sourcePosition = gridViewElements.get(i).getLocation();
						if (countryName.toLowerCase().contains(lastCountryName.toLowerCase())) {
							lastCountryFound = "Yes";
							System.out.println("Last Menu Found");
							break;
						}else {
							//          	System.out.println("Last Menu NOT Found");
						}
					}else {
						//					System.out.println("Size not matched"+gridViewSize+"---->"+itemSize);
					}
					Thread.sleep(500);
				}
				new TouchAction(driver).press(PointOption.point(sourcePosition.getX(),sourcePosition.getY()-20)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(220,30)).release().perform();
				Thread.sleep(100);
			}
			Thread.sleep(500);
			driver.findElementById("ib_top_back_lang_selection").click();
			Thread.sleep(500);
			driver.findElementById("ib_top_back").click();
		} catch (Exception e) {
			this.backToHomeScreen();
			this.writeMainAndSubStrings(System.getProperty("user.dir"), "Country_Specific_Language_Italy", "Italy",countryName_English);
		}
	}

	@Test(priority=17)
	public void Country_Specific_Language_China() throws Exception{
		try {
			System.out.println("TestCase 17 :: Country_Specific_Language_China  ");
			driver.findElementById("btn_navigation_menu").click();
			Thread.sleep(1000);
			selectSystemLanguageOption("System Language","使用中文操作");
			Thread.sleep(6000);
			driver.findElementById("btn_native").click();
			Thread.sleep(1000);
			driver.findElementById("ib_language").click();
			Thread.sleep(2000);
			String lastCountryName ="马达加斯加";
			ArrayList<String> CountryList = new ArrayList<>();
			String countryName = "";
			String lastCountryFound = "";

			int tbsHeight= driver.findElementById("toolbar").getSize().getHeight();
			int tbsWidth= driver.findElementById("toolbar").getSize().getWidth();
			//    System.out.println(tbsHeight+"--"+tbsWidth);
			int firsttime=1;
			while (lastCountryFound.isEmpty()) {
				List<MobileElement> gridViewElements = driver.findElementsById("item_parent_layout");
				int count=gridViewElements.size();
				int gridViewSize=0;
				int targetPosition;
				Point sourcePosition=null;
				for (int i = 0; i < count; i++) {
					ArrayList<String> temp = new ArrayList<String>();
					int itemSize = gridViewElements.get(i).getSize().getHeight();
					//				System.out.print(itemSize);
					if (75==itemSize) {
						countryName= gridViewElements.get(i).findElementById("grid_item_label").getText();
						Thread.sleep(500);
						if (CountryList.contains(countryName)) {
							//					System.out.println(countryName+" exists"+i);
						}else {
							CountryList.add(countryName);
							System.out.print(countryName+" : ");
							gridViewElements.get(i).findElementById("grid_item_label").click();
							int asd = 0;
							ArrayList<String> checked = new ArrayList<String>();
							while (asd<2) {
								List<MobileElement> languagesView = driver.findElementById("recycler_view").findElementsById("parentLayout");
								if (languagesView.size()<5) {
									for (int j = 0; j < languagesView.size(); j++) {
										try {
											asd = 2;
											String lnname = languagesView.get(j).findElementById("lan_name").getText();
											String languageStrings[] = lnname.split(" ", 2);
											String chkString = languagesView.get(j).findElementById("lan_name").getText().replaceAll("[ ]", "_");
											if (!checked.contains(chkString)) {
												checked.add(chkString);
												temp.add(languageStrings[0]);
												temp.add(lnname);
												System.out.print(lnname+" , ");
											}else {}
										} catch (Exception e) {}
									}
									System.out.println();
								}else {
									for (int j = 0; j < languagesView.size(); j++) {
										try {
											String lnname = languagesView.get(j).findElementById("lan_name").getText();
											String languageStrings[] = lnname.split(" ", 2);
											String chkString = languagesView.get(j).findElementById("lan_name").getText().replaceAll("[ ]", "_");
											if (!checked.contains(chkString)) {
												checked.add(chkString);
												temp.add(languageStrings[0]);
												temp.add(lnname);
												System.out.print(lnname+" , ");
											}else {}
										} catch (Exception e) {}
									}
									System.out.println();
									asd++;
									new TouchAction(driver).press(PointOption.point(200,300)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(200,35)).release().perform();
									Thread.sleep(100);
								}
							}
							driver.findElementById("ib_top_back_lang_selection").click();
							Thread.sleep(1500);
							MainAndSubStringStoreModel ms = new MainAndSubStringStoreModel(countryName, temp);
							ms.setMainString(countryName);
							ms.setSubStringList(temp);
							countryName_English.add(ms);
							try {
								gridViewElements=driver.findElementsById("item_parent_layout");
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
						gridViewElements=driver.findElementsById("item_parent_layout");
						sourcePosition = gridViewElements.get(i).getLocation();
						if (countryName.toLowerCase().contains(lastCountryName.toLowerCase())) {
							lastCountryFound = "Yes";
							System.out.println("Last Menu Found");
							break;
						}else {
							//          	System.out.println("Last Menu NOT Found");
						}
					}else {
						//					System.out.println("Size not matched"+gridViewSize+"---->"+itemSize);
					}
					Thread.sleep(800);
				}
				new TouchAction(driver).press(PointOption.point(sourcePosition.getX(),sourcePosition.getY()-20)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(220,30)).release().perform();
				Thread.sleep(100);
			}
			Thread.sleep(1000);
			driver.findElementById("ib_top_back_lang_selection").click();
			Thread.sleep(1000);
			driver.findElementById("ib_top_back").click();
		} catch (Exception e) {
			this.backToHomeScreen();
			this.writeMainAndSubStrings(System.getProperty("user.dir"), "Country_Specific_Language_China", "China",countryName_English);
		}
	}

	@Test(priority=18)
	public void Country_Specific_Language_Korea() throws Exception{
		try {
			System.out.println("TestCase 18 :: Country_Specific_Language_Korea Started ");
			driver.findElementById("btn_navigation_menu").click();
			Thread.sleep(1000);
			selectSystemLanguageOption("System Language","한국어로 사용하다");
			Thread.sleep(6000);
			driver.findElementById("btn_native").click();
			Thread.sleep(1000);
			driver.findElementById("ib_language").click();
			Thread.sleep(2000);
			String lastCountryName ="홍콩";
			ArrayList<String> CountryList = new ArrayList<>();
			String countryName = "";
			String lastCountryFound = "";

			int tbsHeight= driver.findElementById("toolbar").getSize().getHeight();
			int tbsWidth= driver.findElementById("toolbar").getSize().getWidth();
			//    System.out.println(tbsHeight+"--"+tbsWidth);
			int firsttime=1;
			while (lastCountryFound.isEmpty()) {
				List<MobileElement> gridViewElements = driver.findElementsById("item_parent_layout");
				int count=gridViewElements.size();
				int gridViewSize=0;
				int targetPosition;
				Point sourcePosition=null;
				for (int i = 0; i < count; i++) {
					ArrayList<String> temp = new ArrayList<String>();
					int itemSize = gridViewElements.get(i).getSize().getHeight();
					//				System.out.print(itemSize);
					if (75==itemSize) {
						countryName= gridViewElements.get(i).findElementById("grid_item_label").getText();
						Thread.sleep(500);
						if (CountryList.contains(countryName)) {
							//					System.out.println(countryName+" exists"+i);
						}else {
							CountryList.add(countryName);
							System.out.print(countryName+" : ");
							gridViewElements.get(i).findElementById("grid_item_label").click();
							int asd = 0;
							ArrayList<String> checked = new ArrayList<String>();
							while (asd<2) {
								List<MobileElement> languagesView = driver.findElementById("recycler_view").findElementsById("parentLayout");
								if (languagesView.size()<5) {
									for (int j = 0; j < languagesView.size(); j++) {
										try {
											asd = 2;
											String lnname = languagesView.get(j).findElementById("lan_name").getText();
											String languageStrings[] = lnname.split(" ", 2);
											String chkString = languagesView.get(j).findElementById("lan_name").getText().replaceAll("[ ]", "_");
											if (!checked.contains(chkString)) {
												checked.add(chkString);
												temp.add(languageStrings[0]);
												temp.add(lnname);
												System.out.print(lnname+" , ");
											}else {}
										} catch (Exception e) {}
									}
									System.out.println();
								}else {
									for (int j = 0; j < languagesView.size(); j++) {
										try {
											String lnname = languagesView.get(j).findElementById("lan_name").getText();
											String languageStrings[] = lnname.split(" ", 2);
											String chkString = languagesView.get(j).findElementById("lan_name").getText().replaceAll("[ ]", "_");
											if (!checked.contains(chkString)) {
												checked.add(chkString);
												temp.add(languageStrings[0]);
												temp.add(lnname);
												System.out.print(lnname+" , ");
											}else {}
										} catch (Exception e) {}
									}
									System.out.println();
									asd++;
									new TouchAction(driver).press(PointOption.point(200,300)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(200,35)).release().perform();
									Thread.sleep(100);
								}
							}
							driver.findElementById("ib_top_back_lang_selection").click();
							Thread.sleep(500);
							MainAndSubStringStoreModel ms = new MainAndSubStringStoreModel(countryName, temp);
							ms.setMainString(countryName);
							ms.setSubStringList(temp);
							countryName_English.add(ms);
							try {
								gridViewElements=driver.findElementsById("item_parent_layout");
							} catch (Exception e) {
								// TODO: handle exception
							}
						}
						gridViewElements=driver.findElementsById("item_parent_layout");
						sourcePosition = gridViewElements.get(i).getLocation();
						if (countryName.toLowerCase().contains(lastCountryName.toLowerCase())) {
							lastCountryFound = "Yes";
							System.out.println("Last Menu Found");
							break;
						}else {
							//          	System.out.println("Last Menu NOT Found");
						}
					}else {
						//					System.out.println("Size not matched"+gridViewSize+"---->"+itemSize);
					}
					Thread.sleep(500);
				}
				new TouchAction(driver).press(PointOption.point(sourcePosition.getX(),sourcePosition.getY()-20)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(220,30)).release().perform();
				Thread.sleep(100);
			}
			Thread.sleep(500);
			driver.findElementById("ib_top_back_lang_selection").click();
			Thread.sleep(500);
			driver.findElementById("ib_top_back").click();
		} catch (Exception e) {
			this.backToHomeScreen();
			this.writeMainAndSubStrings(System.getProperty("user.dir"), "Country_Specific_Language_Korea", "Korea",countryName_English);
		}
	}

	public void selectSystemLanguageOption(String sourceLanguage, String SYS_Language) throws InterruptedException, IOException {
		try {
			Thread.sleep(1000);
			MobileElement setSourceLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/settingsRecyclerView\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+sourceLanguage+"\"));"));
			setSourceLanguage.click();
			Thread.sleep(1000);
			MobileElement setSYSLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/recyclerView\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+SYS_Language+"\"));"));
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

	public void writeMainAndSubStrings(String filePath,String fileName,  String outputSheetName, ArrayList<MainAndSubStringStoreModel> myData) throws IOException{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String datetime = formatter.format(calendar.getTime());
		datetime = datetime.replaceAll("[:-]", "").replaceAll("[ ]", "_");
		fileName = fileName.concat(datetime+".xlsx");
		File file =    new File(filePath+"\\VoiceTranslationResults\\"+fileName);
		FileOutputStream outputStream = new FileOutputStream(file);
		Workbook myWorkbook = new XSSFWorkbook();
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			String sheetName = myWorkbook.createSheet(outputSheetName+"_"+datetime).getSheetName();
			XSSFSheet sheet = (XSSFSheet) myWorkbook.getSheet(sheetName);
			for (int i = 0; i < myData.size(); i++) {
				Row newRow = sheet.createRow(i);
				System.out.println(myData.get(i).getMainString());
				for(int j = 0; j <myData.get(i).getSubStringList().size()+1 ; j++){
					if (j==0) {
						Cell countryNameCell = newRow.createCell(j);
						countryNameCell.setCellValue(myData.get(i).getMainString());
					}else {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(myData.get(i).getSubStringList().get(j-1));
					}
				}
			}
			try {
				myWorkbook.write(outputStream);
				outputStream.close();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}else {
			System.out.println("File Formate Not Matched...It Must Be XLSX");
		}    
	}

}

