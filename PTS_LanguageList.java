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
import org.openqa.selenium.Dimension;
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

public class PTS_LanguageList {

	private  ArrayList<String> backButtons = new ArrayList<String>();
	private static String errorImagePath = System.getProperty("user.dir")+"\\Screenshots\\";
	public static String packageName = "com.sourcenext.pocketalks.us";
	static AndroidDriver<MobileElement> driver;
	ArrayList<ArrayList<String>> languageProperties = new ArrayList<ArrayList<String>>();
	ArrayList<MainAndSubStringStoreModel> ALL_LANGUAGES_HOME = new ArrayList<MainAndSubStringStoreModel>();
	ArrayList<MainAndSubStringStoreModel> ALL_LANGUAGES_CAMERA = new ArrayList<MainAndSubStringStoreModel>();
	public static ArrayList<MainAndSubStringStoreModel> countrySpecificLanguages = new ArrayList<MainAndSubStringStoreModel>();

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
		Thread.sleep(7000);
		ArrayList<String> SysLanName = new ArrayList<String>();
		ArrayList<String> VT_FIRST_GM = new ArrayList<String>();
		ArrayList<String> VT_Last_GM = new ArrayList<String>();
		ArrayList<String> CT_FIRST_From = new ArrayList<String>();
		ArrayList<String> CT_Last_From = new ArrayList<String>();
		ArrayList<String> VT_FIRST_ChinaMode = new ArrayList<String>();
		ArrayList<String> VT_Last_ChinaMode = new ArrayList<String>();
		ArrayList<String> LAST_CountryName = new ArrayList<String>();

		// -----------English Information--------------------
		SysLanName.add("English");
		VT_FIRST_GM.add("Afrikaans");
		VT_Last_GM.add("Zulu");
		LAST_CountryName.add("Zimbabwe");
		CT_FIRST_From.add("Automatic Recognition");
		CT_Last_From.add("Vietnamese");
		VT_FIRST_ChinaMode.add("Arabic");
		VT_Last_ChinaMode.add("Vietnamese");
		// ---------------JAPANESE Information---------------
		SysLanName.add("日本語で操作する");
		VT_FIRST_GM.add("アイスランド語");
		VT_Last_GM.add("ロシア語");
		LAST_CountryName.add("ロシア連邦");
		CT_FIRST_From.add("自動認識");
		CT_Last_From.add("ロシア語");
		VT_FIRST_ChinaMode.add("アラビア語");
		VT_Last_ChinaMode.add("ロシア語");
		// --------------------SPANISH Information--------------------
		SysLanName.add("español");
		VT_FIRST_GM.add("Africaans");
		VT_Last_GM.add("Zulú");
		LAST_CountryName.add("Zimbabue");
		CT_FIRST_From.add("Reconocimiento automático");
		CT_Last_From.add("Vietnamita");
		VT_FIRST_ChinaMode.add("Alemán");
		VT_Last_ChinaMode.add("Vietnamita");		
		// -------------------- Portugues Information--------------------
		SysLanName.add("português");
		VT_FIRST_GM.add("Afrikaans");
		VT_Last_GM.add("Zulu");
		LAST_CountryName.add("Zimbábue");
		CT_FIRST_From.add("Reconhecimento Automático");
		CT_Last_From.add("Bielorrusso");
		VT_FIRST_ChinaMode.add("Árabe");
		VT_Last_ChinaMode.add("Vietnamita");
		// -------------------- RUSSIAN Information--------------------
		SysLanName.add("русском");
		VT_FIRST_GM.add("Африканцы");
		VT_Last_GM.add("Зулу");
		LAST_CountryName.add("Япония");
		CT_FIRST_From.add("Автоматическое распознавание");
		CT_Last_From.add("Белорусский");
		VT_FIRST_ChinaMode.add("Арабский");
		VT_Last_ChinaMode.add("Вьетнамский");
		// -------------------- France Information--------------------
		SysLanName.add("français");
		VT_FIRST_GM.add("Afrikaans");
		VT_Last_GM.add("Zoulou");
		LAST_CountryName.add("Zimbabwe");
		CT_FIRST_From.add("Reconnaissance automatique");
		CT_Last_From.add("Vietnamien");
		VT_FIRST_ChinaMode.add("Allemand");
		VT_Last_ChinaMode.add("Vietnamien");
		// -------------------- Dutch Information--------------------
		SysLanName.add("deutsch");
		VT_FIRST_GM.add("Afrikaans");
		VT_Last_GM.add("Zulu");
		LAST_CountryName.add("Zypern");
		CT_FIRST_From.add("Automatische Erkennung");
		CT_Last_From.add("Weißrussisch");
		VT_FIRST_ChinaMode.add("Arabisch");
		VT_Last_ChinaMode.add("Vietnamesisch");
		// -------------------- ITALIY Information--------------------
		SysLanName.add("italia");
		VT_FIRST_GM.add("Afrikaans");
		VT_Last_GM.add("Zulu");
		LAST_CountryName.add("Zimbabwe");
		CT_FIRST_From.add("Riconoscimento automatico");
		CT_Last_From.add("Vietnamita");
		VT_FIRST_ChinaMode.add("Arabo");
		VT_Last_ChinaMode.add("Vietnamita");
		// -------------------- MALAY Information--------------------
		SysLanName.add("Melayu");
		VT_FIRST_GM.add("Afrikaan");
		VT_Last_GM.add("Zulu");
		LAST_CountryName.add("Zimbabwe");
		CT_FIRST_From.add("Pengecaman Automatik");
		CT_Last_From.add("Belarus");
		VT_FIRST_ChinaMode.add("Arab");
		VT_Last_ChinaMode.add("Vietnam");
		// -------------------- Chinese SIMPLIFIED Information--------------------
		SysLanName.add("使用简体中文操作");
		VT_FIRST_GM.add("南非荷兰语");
		VT_Last_GM.add("祖鲁语");
		LAST_CountryName.add("马达加斯加");
		CT_FIRST_From.add("自动识别");
		CT_Last_From.add("中文(繁体)");
		VT_FIRST_ChinaMode.add("阿拉伯语");
		VT_Last_ChinaMode.add("中文(繁体)");
		// -------------------- KOREA Information--------------------
		SysLanName.add("한국어로");
		VT_FIRST_GM.add("갈리시아어");
		VT_Last_GM.add("힌디어");
		LAST_CountryName.add("홍콩");
		CT_FIRST_From.add("자동 인식");
		CT_Last_From.add("힌디어");
		VT_FIRST_ChinaMode.add("광둥어");
		VT_Last_ChinaMode.add("헝가리어");
		// -------------------- THAI Information--------------------
		SysLanName.add("ใช้งานด้วยภาษาไทย");
		VT_FIRST_GM.add("ภาษาแอฟริกานส์");
		VT_Last_GM.add("ภาษาซูลู");
		LAST_CountryName.add("ฮังการี");
		CT_FIRST_From.add("ระบบตรวจจับอัตโนมัติ");
		CT_Last_From.add("ภาษาเบลารุส");
		VT_FIRST_ChinaMode.add("ภาษาอาหรับ");
		VT_Last_ChinaMode.add("ภาษาเวียดนาม");
		// -------------------- Chinese TRADITIONAL Information--------------------
		SysLanName.add("使用繁體中文操作");
		VT_FIRST_GM.add("南非文");
		VT_Last_GM.add("祖魯文");
		LAST_CountryName.add("汶萊");
		CT_FIRST_From.add("自動辨識");
		CT_Last_From.add("白俄羅斯文");
		VT_FIRST_ChinaMode.add("阿拉伯文");
		VT_Last_ChinaMode.add("越南文");
		//-----------ALL Information Stored Into Array --------
		languageProperties.add(SysLanName);  //0
		languageProperties.add(VT_FIRST_GM); //1
		languageProperties.add(VT_Last_GM); //2
		languageProperties.add(LAST_CountryName);//3
		languageProperties.add(CT_FIRST_From); //4
		languageProperties.add(CT_Last_From); //5
		languageProperties.add(VT_FIRST_ChinaMode); //6
		languageProperties.add(VT_Last_ChinaMode); //7

		backButtons.add("btn_navigation_menu_left");
		backButtons.add("ib_back");
		backButtons.add("iv_top_back");
		backButtons.add("ib_top_back");
		backButtons.add("discard");
		backButtons.add("close_dialog");
		backButtons.add("btn_ok");
		backButtons.add("text_back_okay");
		backButtons.add("//android.widget.LinearLayout/android.widget.Button");
		backButtons.add("android:id/button1");
		backButtons.add("com.mediatek.batterywarning:id/add");

	}

	@AfterSuite
	public void tearDown() throws IOException {
				this.writeMainAndSubStrings(System.getProperty("user.dir"), "PTS_GDPR_Languages_Global_Mode_ALL_", "HOLLGM",ALL_LANGUAGES_HOME);
//				this.writeMainAndSubStrings(System.getProperty("user.dir"), "PTS_GDPR_US_Country_Specific_Language_Global_ALL_", "COSL",countrySpecificLanguages);
				this.writeMainAndSubStrings(System.getProperty("user.dir"), "PTS_Camera_Languages", "CALI",ALL_LANGUAGES_CAMERA);
		driver.quit();
	}

	// Voice Translation Language List Start below

			@Test(priority=1)
	public void Home_Top_Language_List() throws Exception{
		Thread.sleep(2000);
		String ls = "Missed";
		try {
			for (int ii = 0; ii < languageProperties.get(0).size(); ii++) {
				ls=languageProperties.get(0).get(ii);
				System.out.println("TestCase "+ii+" :: Home_Top_Language_List..."+languageProperties.get(0).size()+"=====" +ls );
				driver.findElementById("btn_navigation_menu_left").click();
				Thread.sleep(2000);
				driver.findElementsById("layout_main_iv_tv").get(0).click();
				Thread.sleep(1000);
				this.selectSystemLanguage("System Language",languageProperties.get(0).get(ii));
				Thread.sleep(7000);
				this.setTheFirstLanguage(languageProperties.get(1).get(ii),false); // Global Mode
				String lastLanguageName =languageProperties.get(2).get(ii);       // Global Mode
				//				this.setTheFirstLanguage(languageProperties.get(6).get(ii),false);   // ***********China Mode
				//				String lastLanguageName =languageProperties.get(7).get(ii);         // ***********China Mode
				driver.findElementById("layout_gradient_bottom").click();
				Thread.sleep(2000);
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
								ArrayList<String> dummy = new ArrayList<String>();
								dummy.add(languageName);
								MainAndSubStringStoreModel masm = new MainAndSubStringStoreModel(ls, dummy);
								masm.setMainString(ls);
								masm.setSubStringList(dummy);
								ALL_LANGUAGES_HOME.add(masm);
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
				Thread.sleep(1000);
				System.out.println("Home Language Total Menu:"+LanguageList.size());
				driver.findElementById("text_back_okay").click();
				this.writeMainAndSubStrings(System.getProperty("user.dir"), "PTS_GDPR_Languages_Global_Mode_"+ls+"_", "HOLLGM",ALL_LANGUAGES_HOME);
				this.backToHomeScreen();
			}} catch (Exception e) {
				this.errorScreenshot(ls);
				this.backToHomeScreen();
			}
	}

//	@Test(priority=2)
	public void Country_Specific_Languages() throws Exception{
		String ls = "NotAvailable";
		for (int jj = 0; jj < languageProperties.get(0).size(); jj++) {
			System.out.println("TestCase "+jj+" :: Country_Specific_Languages..."+languageProperties.get(0).size()+"=====" +languageProperties.get(0).get(jj) );
			try {
				driver.findElementById("btn_navigation_menu_left").click();
				Thread.sleep(1000);
				driver.findElementsById("layout_main_iv_tv").get(0).click();
				Thread.sleep(500);
				ls=languageProperties.get(0).get(jj);
				this.selectSystemLanguage("System Language",ls);
				Thread.sleep(6000);
				driver.findElementById("layout_gradient_bottom").click();
				Thread.sleep(500);
				driver.findElementById("ib_language").click();
				Thread.sleep(1000);
				String lastCountryName =languageProperties.get(3).get(jj);
				ArrayList<String> CountryList = new ArrayList<>();
				String countryName = "";
				String lastCountryFound = "";
				while (lastCountryFound.isEmpty()) {
					List<MobileElement> gridViewElements = driver.findElementsById("item_parent_layout");
					int count=gridViewElements.size();
					Point sourcePosition=null;
					for (int i = 0; i < count; i++) {
						ArrayList<String> temp = new ArrayList<String>();
						int itemSize = gridViewElements.get(i).getSize().getHeight();
						//				System.out.print(itemSize);
						if (150==itemSize) {
							countryName= gridViewElements.get(i).findElementById("grid_item_label").getText();
							Thread.sleep(500);
							if (CountryList.contains(countryName)) {
								//					System.out.println(countryName+" exists"+i);
							}else {
								if (countryName.equals("คองโก")) {
									countryName = countryName+"_"+i;
								}else {}
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
													//													temp.add(languageStrings[0]);
													temp.add(lnname);
													System.out.print(lnname+" , ");
												}else {}
											} catch (Exception e) {}
										}
									}else {
										for (int j = 0; j < languagesView.size(); j++) {
											try {
												String lnname = languagesView.get(j).findElementById("lan_name").getText();
												String languageStrings[] = lnname.split(" ", 2);
												String chkString = languagesView.get(j).findElementById("lan_name").getText().replaceAll("[ ]", "_");
												if (!checked.contains(chkString)) {
													checked.add(chkString);
													//													temp.add(languageStrings[0]);
													temp.add(lnname);
													System.out.print(lnname+" , ");
												}else {}
											} catch (Exception e) {}
										}
										asd++;
										new TouchAction(driver).press(PointOption.point(this.getMaxPositionOfDisplay("BOTTOM"))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(this.getMaxPositionOfDisplay("TOP"))).release().perform();
										Thread.sleep(100);
									}
								}
								System.out.println();
								driver.findElementById("ib_top_back").click();
								Thread.sleep(500);
								MainAndSubStringStoreModel ms = new MainAndSubStringStoreModel(countryName, temp);
								ms.setMainString(countryName);
								ms.setSubStringList(temp);
								countrySpecificLanguages.add(ms);
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
					new TouchAction(driver).press(PointOption.point(sourcePosition.getX(),sourcePosition.getY()-50)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(sourcePosition.getX(),100)).release().perform();
					Thread.sleep(100);
				}
				Thread.sleep(800);
				driver.findElementById("ib_top_back").click();
				Thread.sleep(800);
				driver.findElementById("text_back_okay").click();
				Thread.sleep(800);
				this.writeMainAndSubStrings(System.getProperty("user.dir"), "Country_Specific_Language_US_"+ls, "CSL",countrySpecificLanguages);
				this.backToHomeScreen();
			} catch (Exception e) {
				System.out.println(e);
				this.writeMainAndSubStrings(System.getProperty("user.dir"), "Country_Specific_Language_US_"+ls, "CSL",countrySpecificLanguages);
				this.backToHomeScreen();
			}
		}
	}

	// Camera Language List Start below

			@Test(priority=3)
	public void Camera_Language_List() throws Exception{
		Thread.sleep(2000);
		for (int kk = 0; kk < languageProperties.get(0).size(); kk++) {
			try {
				System.out.println("TestCase "+kk+" :: Camera_Language_List..."+languageProperties.get(0).size()+"=====" +languageProperties.get(0).get(kk) );
				driver.findElementById("btn_navigation_menu_left").click();
				Thread.sleep(1500);
				driver.findElementsById("layout_main_iv_tv").get(0).click();
				Thread.sleep(1000);
				String ls = this.selectSystemLanguage("System Language",languageProperties.get(0).get(kk));
				Thread.sleep(6000);
				this.setTheFirstLanguage(languageProperties.get(4).get(kk),true);
				driver.findElementById("btn_navigation_menu_left").click();
				Thread.sleep(1500);
				driver.findElementsById("layout_main_iv_tv").get(1).click();
				Thread.sleep(2000);
				driver.findElementById("tvLanguageShooting").click(); //From Language
				Thread.sleep(2000);
				String lastLanguageName =languageProperties.get(5).get(kk);
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
								ArrayList<String> dummy = new ArrayList<String>();
								dummy.add(languageName);
								MainAndSubStringStoreModel masm = new MainAndSubStringStoreModel(ls, dummy);
								masm.setMainString(ls);
								masm.setSubStringList(dummy);
								ALL_LANGUAGES_CAMERA.add(masm);
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
				Thread.sleep(500);
				System.out.println("Camera :"+LanguageList.size());
				driver.findElementById("text_back_okay").click();
				Thread.sleep(1000);
				driver.findElementById("iv_top_back").click();
				this.writeMainAndSubStrings(System.getProperty("user.dir"), "PTS_Camera_Languages_"+ls+"_", "CLI",ALL_LANGUAGES_CAMERA);
				this.backToHomeScreen();
			} catch (Exception e) {
				System.out.println(e);
				this.errorScreenshot("CameraLanguageError");
				this.backToHomeScreen();
			}
		}
	}

	public void setTheFirstLanguage(String firstLanguageName, Boolean isCamera) throws InterruptedException {
		if (isCamera) {
			try {
				driver.findElementById("btn_navigation_menu_left").click();
				Thread.sleep(1000);
				driver.findElementsById("layout_main_iv_tv").get(1).click();
				Thread.sleep(1500);
				//		driver.findElementById("tvLanguageTranslate").click(); // TO Language
				driver.findElementById("tvLanguageShooting").click(); //From Language
				Thread.sleep(1500);
				try {
					MobileElement syslan = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/recycler_view\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+firstLanguageName+"\"));"));
					syslan.click();
					Thread.sleep(100);
					driver.findElementById("text_back_okay").click();
					Thread.sleep(500);
					driver.findElementById("iv_top_back").click();
					Thread.sleep(1500);
				} catch (Exception e) {
					// TODO: handle exception
				}
			} catch (Exception e) {
				this.backToHomeScreen();
			}
		}else {
			try {
				driver.findElementById("layout_gradient_bottom").click();
				Thread.sleep(2000);
				MobileElement setSourceLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/recycler_view\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+firstLanguageName+"\"));"));
				setSourceLanguage.click();
				driver.findElementById("text_back_okay").click();
				Thread.sleep(1000);
			} catch (Exception e) {
				this.backToHomeScreen();
			}
		}

	}
	public String selectSystemLanguage(String menuInSettings, String SYS_Language) throws InterruptedException, IOException {
		try {
			String lName = "";
			Thread.sleep(1000);
			MobileElement setSourceLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/settingsRecyclerView\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+menuInSettings+"\"));"));
			setSourceLanguage.click();
			Thread.sleep(1000);

			try {
				MobileElement syslan = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/recycler_view\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+SYS_Language+"\"));"));
				syslan.click();
				Thread.sleep(200);
				lName = syslan.getText();
				System.out.println(lName + " --- Language Selected");
			} catch (Exception e) {
				System.out.println("Select System Language Failed...!!!\n"+e);
			}
			//			new TouchAction(driver).press(PointOption.point(this.getMaxPositionOfDisplay("top"))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(this.getMaxPositionOfDisplay("bottom"))).release().perform();
			//			Thread.sleep(1000); 
			//			ArrayList<String> tmp = new ArrayList<String>();
			//			boolean found = false;
			//			Point lastItem = null;
			//			while (!found) {
			//				List<MobileElement> viewOfLanguages = driver.findElementsById("parentLayout");
			//				for (int i = 0; i < viewOfLanguages.size(); i++) {
			//					String x = viewOfLanguages.get(i).findElementById("txt_lang_name").getText().replaceAll("[ ]", "_");
			//					lastItem = viewOfLanguages.get(i).findElementById("txt_lang_name").getLocation();
			//					if (SYS_Language.contains(x)) {
			//						viewOfLanguages.get(i).findElementById("txt_lang_name").click();
			//						found=true;
			//						lName = viewOfLanguages.get(i).findElementById("txt_lang_name").getText();
			//						i=viewOfLanguages.size();
			//					}else {}
			//						new TouchAction(driver).press(PointOption.point(lastItem)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(this.getMaxPositionOfDisplay("top"))).release().perform();
			//						Thread.sleep(500);
			//				}
			//			}
			driver.findElementById("text_back_okay").click();
			Thread.sleep(500);
			driver.findElementById("iv_top_back").click();
			return lName;
		} catch (Exception e) {
			this.errorScreenshot(SYS_Language);
			System.out.println("Select System Language Failed...!!!\n"+e);
			this.backToHomeScreen();
			return "N/A";
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
			this.errorScreenshot(targetLanguage);
		}
	}

	public Point getMaxPositionOfDisplay(String top_bottom_left_right) {
		Dimension windowSize = driver.manage().window().getSize();
		int height = windowSize.getHeight();
		int width = windowSize.getWidth();
		int midX = width/2;
		int midY = height/2;
		int maxY = (int) (height*0.90);
		int minY = (int) (height*0.20);
		int maxX = (int) (width*0.90);
		int minX = (int) (width*0.20);
		if (top_bottom_left_right.toLowerCase().contains("top")) {
			//			System.out.println("TOP - "+midX+","+minY);
			Point p= new Point(midX, minY);
			return p;
		}else if (top_bottom_left_right.toLowerCase().contains("bottom")) {
			//			System.out.println("Bottom - "+midX+","+maxY);
			Point p= new Point(midX, maxY);
			return p;
		}else if (top_bottom_left_right.toLowerCase().contains("left")) {
			//			System.out.println("LEFT - "+minX+","+midY);
			Point p= new Point(minX, midY);
			return p;
		}else if (top_bottom_left_right.toLowerCase().contains("right")) {
			//			System.out.println("Rigth - "+maxX+","+midY);
			Point p= new Point(maxX, midY);
			return p;
		}else {
			System.out.println("Position Not Matched! Check your string. Default point is center..");
			Point p= new Point(midX, midY);
			return p;
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

	public void errorScreenshot(String imageName) throws IOException, InterruptedException{
		File srcFile=driver.getScreenshotAs(OutputType.FILE);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String datetime = formatter.format(calendar.getTime());
		datetime = datetime.replaceAll("[:-]", "").replaceAll("[ ]", "_");
		File targetFile=new File(errorImagePath + datetime+"_"+imageName+".jpg");
		FileUtils.copyFile(srcFile,targetFile);
		Thread.sleep(1000);
	}

	public void writeMainAndSubStrings(String filePath,String fileName,  String outputSheetName, ArrayList<MainAndSubStringStoreModel> myData) throws IOException{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String datetime = formatter.format(calendar.getTime());
		datetime = datetime.replaceAll("[:-]", "").replaceAll("[ ]", "_");
		fileName = datetime.concat("_"+fileName+".xlsx");
		File file =    new File(filePath+"\\GDPR_EU_US_March\\"+fileName);
		FileOutputStream outputStream = new FileOutputStream(file);
		Workbook myWorkbook = new XSSFWorkbook();
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			String sheetName = myWorkbook.createSheet(outputSheetName+"_"+datetime).getSheetName();
			XSSFSheet sheet = (XSSFSheet) myWorkbook.getSheet(sheetName);
			for (int i = 0; i < myData.size(); i++) {
				Row newRow = sheet.createRow(i);
				//				System.out.println(myData.get(i).getMainString());
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

