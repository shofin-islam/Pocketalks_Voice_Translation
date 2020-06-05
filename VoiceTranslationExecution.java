import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.CORBA.INITIALIZE;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.Setting;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class VoiceTranslationExecution {
	private static File classpathRoot = new File(System.getProperty("user.dir"));
	public static AndroidDriver<MobileElement> driver;
	public static AndroidDriver<MobileElement> driverMedia;
	public static String packageName = "com.sourcenext.pocketalks.us";
	public static String packageNameMedia = "com.android.music";
	public String filePath = System.getProperty("user.dir");
	public static String deviceName;

	private PtsSupportModel_toString support;
	ArrayList<MainAndSubStringStoreModel> VoiceTrInformation = new ArrayList<MainAndSubStringStoreModel>();
	ArrayList<MainAndSubStringStoreModel> ReTranslationData = new ArrayList<MainAndSubStringStoreModel>();

	@BeforeSuite
	public void setup() throws InterruptedException, IOException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android"); 
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"8.1.0");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"0123456789ABCDEF");
		capabilities.setCapability("udid", "0123456789ABCDEF");
		//		capabilities.setCapability("systemPort",8200);
		//		capabilities.setCapability("wdaStartupRetries", "4");
		//		capabilities.setCapability("wdaStartupRetryInterval", "20000");
		capabilities.setCapability("noReset","true"); 
		capabilities.setCapability("fullReset","false");
		capabilities.setCapability("appPackage", packageName);
		capabilities.setCapability("appActivity",packageName+".activity.MainActivity");
		capabilities.setCapability("clearDeviceLogsOnStart", true);
		capabilities.setCapability("skipDeviceInitialization", true);
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Thread.sleep(7000);
		support = new PtsSupportModel_toString(driver, packageName);
		deviceName = support.getDeviceNameByDisplay();
		
		
		//		ArrayList<String> songsList = new ArrayList<String>(Arrays. asList("arabic","bangla","english"));
//		DesiredCapabilities capabilities2 = new DesiredCapabilities();
//		capabilities2.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
//		capabilities2.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android"); 
//		capabilities2.setCapability(MobileCapabilityType.PLATFORM_VERSION,"7.0");
//		capabilities2.setCapability(MobileCapabilityType.DEVICE_NAME,"123123123123123");
//		capabilities2.setCapability("udid", "123123123123123");
//		capabilities2.setCapability("noReset","true"); 
//		capabilities2.setCapability("fullReset","false");
//		capabilities2.setCapability("appPackage", packageNameMedia);
//		capabilities2.setCapability("appActivity",packageNameMedia+".MusicBrowserActivity");
//		capabilities2.setCapability("skipDeviceInitialization", true);
//		driverMedia = new AndroidDriver<MobileElement>(new URL("http://127.0.0.2:4724/wd/hub"), capabilities2);
//		Thread.sleep(3000);
		//		this.goToMusicDirectory("Audio_PTS_STT");
//		this.goToMusicDirectoryV47();
	}

	@AfterSuite
	public void tearDown() throws IOException {
		this.writeVoiceTranslationInformaton("ReTranslation", "PTS_EU_Re_TranslationResults_", "ReTrns_EU_");
		driver.quit();
//		driverMedia.quit();
	}
	
	@SuppressWarnings("rawtypes")
	@Test (priority=0)
	public void ReTranslationOfVoicePTS() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		boolean isFirst = true;
		boolean foundLast = false;
		String LastSelectedLanguage="";
		String STATUS = "";
		while (!foundLast) {
			ArrayList<String> results = new ArrayList<String>();
			try {
				driver.findElement(By.id("top_sheet")).click();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("recycler_view")));
				List<MobileElement> elements = driver.findElement(By.id("recycler_view")).findElements(By.className("android.widget.LinearLayout"));
				for (int i = 0; i < elements.size(); i++) {
					try {
						String data = elements.get(i).findElementById("layout_text_holder").findElement(By.id("tv_target_language")).getText();
						
						if (!data.isEmpty() && i==elements.size()-1) {
//							System.out.println("elements.size() "+elements.size());
							System.out.println(data);
							results.add(data);
							Point p = elements.get(i).findElementById("layout_text_holder").findElement(By.id("tv_target_language")).getLocation();
							Thread.sleep(200);
							new TouchAction(driver).longPress(PointOption.point(p)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500))).moveTo(PointOption.point(p)).release().perform();
							Thread.sleep(200);
						}else {}
					} catch (Exception e) {}
				}
				if (deviceName.contains("PTS_PLUS")) {
					new TouchAction(driver).press(PointOption.point(250,325)).release().perform();
				}else if(deviceName.contains("PTS")){
					new TouchAction(driver).press(PointOption.point(235,235)).release().perform();
				}else {}
				
				Thread.sleep(1000);
				if (isFirst) {
					languageSelectionByUiAutomator("Afrikaans");
					LastSelectedLanguage="Afrikaans";
					isFirst=false;
				}else {
					LastSelectedLanguage = this.selectNextLanguageToReTranslate();
				}
				
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("close_dialog")));
				
//				Thread.sleep(10000);
				
				results.addAll(this.getEngineNameForReTranslation(LastSelectedLanguage));
				support.outputScreenshot("ReTranslation", "TTS_"+LastSelectedLanguage);
				try {
					driver.findElementById("close_dialog").click();
//					Thread.sleep(1000);
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("img_back"))).click();
					STATUS = "Translation_PASS";
//					driver.findElementById("img_back").click();
					Thread.sleep(1000);
				} catch (Exception e) {
					STATUS = "Translation_FAIL";
					support.backToHomeScreen();
				}
				results.add(STATUS);
				MainAndSubStringStoreModel ms = new MainAndSubStringStoreModel(LastSelectedLanguage,results);
				ms.setMainString(LastSelectedLanguage);
				ms.setSubStringList(results);
				ReTranslationData.add(ms);
				if (LastSelectedLanguage.contains("Zulu")||LastSelectedLanguage.contains("Zulu")) {
					System.out.println("Found Last Language ==== "+LastSelectedLanguage);
					foundLast = true;
					break;
				}else {}
			} catch (Exception e) {
				support.errorScreenshot("Re_Translation_Failed_");
				support.backToHomeScreen();
			}
			if (LastSelectedLanguage.contains("Zulu")||LastSelectedLanguage.contains("Zulu")) {
				System.out.println("Found Last Language ==== "+LastSelectedLanguage);
				foundLast = true;
			}else {}
			
			if (ReTranslationData.size()==30 || ReTranslationData.size()==60 || ReTranslationData.size()==90) {
				this.writeVoiceTranslationInformaton("ReTranslation", "PTS_Re_TranslationResults_30_60_90", "ReTrans_");
			}else {}
//			else if(ReTranslationData.size()==1 ){
//				System.out.println("1: "+driverMedia.currentActivity());
//				driverMedia.quit();
//			}else if(ReTranslationData.size()==5 ){
//				DesiredCapabilities capabilities2 = new DesiredCapabilities();
//				capabilities2.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
//				capabilities2.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android"); 
//				capabilities2.setCapability(MobileCapabilityType.PLATFORM_VERSION,"7.0");
//				capabilities2.setCapability(MobileCapabilityType.DEVICE_NAME,"123123123123123");
//				capabilities2.setCapability("udid", "123123123123123");
//				capabilities2.setCapability("noReset","true"); 
//				capabilities2.setCapability("fullReset","false");
//				capabilities2.setCapability("appPackage", packageNameMedia);
//				capabilities2.setCapability("appActivity",packageNameMedia+".MusicBrowserActivity");
//				capabilities2.setCapability("skipDeviceInitialization", true);
//				driverMedia = new AndroidDriver<MobileElement>(new URL("http://127.0.0.2:4724/wd/hub"), capabilities2);
//			}
		}		
	}
	
	public String selectNextLanguageToReTranslate() {
		List<MobileElement>	langlistview = driver.findElementById("recycler_view").findElementsById("parentLayout");
		int activeIndex = 0;
		String languageName = "";
		for (int i = 0; i < langlistview.size(); i++) {
			try {
				if (langlistview.get(i).findElementById("checkbox").isDisplayed()) {
					activeIndex=i;
				}else {
					System.out.println("No Item Selected !!!");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		try {
			languageName=langlistview.get(activeIndex+1).findElementById("lan_name").getText();
			langlistview.get(activeIndex+1).click();
			driver.findElementById("text_back_okay").click();
			Thread.sleep(1000);
			return languageName;
		} catch (Exception e) {
			return "";
		}
	}

//	@Test (priority=1)
	public void voiceTranslationPTS() throws Exception {
		ArrayList<String> languageList = new ArrayList<String>(Arrays. asList("Afrikaans","Amharic","Arabic","Bengali","Burmese","Catalan","Czech","(Dansk)", "English(India)","Japanese"));
		ArrayList<String> songsList = new ArrayList<String>(Arrays. asList("AF01","AM01","AR01","BN01","BM01","CA01","CS01","DA01", "EN01","JA01"));
		driver.setSetting(Setting.FIX_IMAGE_TEMPLATE_SIZE, true);
		driver.setSetting(Setting.IMAGE_MATCH_THRESHOLD, 0.8);
		boolean isFirst = true;
		boolean foundLast = false;
		String lanName="";
		String STATUS = "";
		int x=0;
		while (!foundLast) {
			System.out.println(driverMedia.currentActivity());
			try {
				if (isFirst) {
					this.setEnvironment("Afrikaans", "English(UK)");
					lanName = "Afrikaans";
					isFirst = false;
					System.out.println(driverMedia.currentActivity());
				}else{
//					lanName = this.selectNextLanguage("TOP");
					this.LanguageSelection("Bottom", languageList.get(x));
					lanName = languageList.get(x);
					System.out.println(driverMedia.currentActivity());
				}
				this.setTranslationSwitchButton("barrow");
				String tutorialString = this.longPressToGetTutorial();
				this.setTranslationSwitchButton("barrow");
				ArrayList<String> guideLineString = this.longPressToGetGuideline(2);
//				System.out.println("guideLineString ------ "+guideLineString.size());
				this.setTranslationSwitchButton("uparrow");
				this.playTargetMusicV47(songsList.get(x));
				ArrayList<String> engs = new ArrayList<String>();
				if (this.longPressToVoiceInput(10).size()>0) {
					System.out.println(driverMedia.currentActivity());
					Thread.sleep(7000);
					engs = this.getEngineName(lanName);
					Thread.sleep(5000);
					support.outputScreenshot("vTUI",lanName);
					this.stopTargetMusicV47();
					Thread.sleep(3000);
				}else {
					support.outputScreenshot("vTUI",lanName+"_Failed");
					Thread.sleep(1000);
					this.stopTargetMusicV47();
				}
//				this.longPressHomeKey(20);
				try {
					driver.findElementById("close_dialog").click();
					System.out.println("Translation Success...");
					STATUS="Translation-PASS";
				} catch (Exception e) {
					STATUS="Translation-FAIL";
					System.out.println("Fail - Voice Input -- close_dialog click...");
				}
				Thread.sleep(2000);
				ArrayList<String> engines = new ArrayList<String>();
				try {engines.add(this.getLanguageNameLocal("BOTTOM"));} catch (Exception e) {}
				try {engines.add(this.getLanguageNameLocal("BOTTOM"));} catch (Exception e) {}
				try {engines.add(this.getLanguageNameLocal("TOP"));} catch (Exception e) {}
				try {engines.add(this.getLanguageNameLocal("TOP"));} catch (Exception e) {}
				engines.add(STATUS);
				engines.addAll(engs);
				engines.add(tutorialString);
				engines.addAll(guideLineString);
				MainAndSubStringStoreModel ms = new MainAndSubStringStoreModel(lanName,engines);
				ms.setMainString(lanName);
				ms.setSubStringList(engines);
				VoiceTrInformation.add(ms);
				if (lanName.contains("Zulu")||lanName.contains("Japanese")) {
					System.out.println("Found Last Language ==== "+lanName);
					foundLast = true;
					break;
				}else {}
			} catch (Exception e) {
				support.backToHomeScreen();
				support.errorScreenshot("Voice_Translation_Failed");
				System.out.println("Voice_Translation_Failed....\n"+e);
			}
			
			if (lanName.contains("Zulu")||lanName.contains("Japanese")) {
				System.out.println("Found Last Language ==== "+lanName);
				foundLast = true;
			}else {}
			x++;
		}
		
	}

	private boolean setTranslationSwitchButton(String uparrowORbarrow) throws URISyntaxException, IOException, InterruptedException {

//		System.out.println("===========SET ARROW START===========");
		boolean isUP = false;
		boolean isBottom = false;
		boolean isButtonAvailable = true;
		Thread.sleep(1000);
		try {
//			System.out.println("1) Appium found Up Arrow : "+driver.findElementByImage(getReferenceImageB64("uparrow.png")).isDisplayed());
			driver.findElementByImage(getReferenceImageB64("uparrow.png")).isDisplayed();
			isUP=true;
		}catch (Exception e) {
//			System.out.println("UP-ARROW Not FOUND by IMAGE - Exception ..."); 
		}
		try {
//			System.out.println("2) Appium found Bottom Arrow : "+driver.findElementByImage(getReferenceImageB64("barrow.png")).isDisplayed());
			driver.findElementByImage(getReferenceImageB64("barrow.png")).isDisplayed();
			isBottom=true;
		}catch (Exception f) {
//			System.out.println("BOTTOM-ARROW Not FOUND by IMAGE - Exception...");
		}

		if (uparrowORbarrow.toLowerCase().contains("uparrow")) {
			if (isUP) {
//				System.out.println("UP ARROW ALREADY SELECTED...");
				return isUP;
			}else {
				try {
					driver.findElementById("arrow_iv").click();
					Thread.sleep(2000);
//					System.out.println("Arrow Button Clicked...TO GET UP ARROW NEXT TIME");
				} catch (Exception e) {
					System.out.println("ARROW Button Missing"); 
					support.errorScreenshot(uparrowORbarrow+"Button Missing");
					isButtonAvailable=false;
				}
				return this.setTranslationSwitchButton(uparrowORbarrow);
			}

		}else if (uparrowORbarrow.toLowerCase().contains("barrow")) {
			if (isBottom) {
//				System.out.println("BOTTOM ARROW ALREADY SELECTED...");
				return isBottom;
			}else {
				try {
					driver.findElementById("arrow_iv").click();
					Thread.sleep(2000);
//					System.out.println("Arrow Button Clicked...TO GET BOTTOM NEXT TIME");
				} catch (Exception e) {
					System.out.println("ARROW Button Missing"); 
					support.errorScreenshot(uparrowORbarrow+"Button Missing");
					isButtonAvailable=false;
				}
				return this.setTranslationSwitchButton(uparrowORbarrow);
			}
		}else {
			System.out.println("UP or BOTTOM Constructor Missing"); 
			support.errorScreenshot(uparrowORbarrow);
			return false;
		}

	}

	private String getReferenceImageB64(String imgName) throws URISyntaxException, IOException {
		File assetDir = new File(classpathRoot, "/assets/images");
		File img = new File(assetDir.getCanonicalPath(), imgName);
		return Base64.getEncoder().encodeToString(Files.readAllBytes(img.toPath()));
	}
	
	public String longPressToGetTutorial() throws InterruptedException, URISyntaxException, IOException {
		System.out.println("===========TUTORIAL START===========");
		try {
			List<String> captureImage = Arrays.asList("/dev/input/event1", "1", "254", "1");
			Map<String, Object> captureImageCmd = ImmutableMap.of("command", "sendevent","args", captureImage);
			driver.executeScript("mobile: shell", captureImageCmd);
			List<String> captureImagee = Arrays.asList("/dev/input/event1", "0", "0", "0");
			Map<String, Object> captureImageCmdd = ImmutableMap.of("command", "sendevent","args", captureImagee);
			driver.executeScript("mobile: shell", captureImageCmdd);
			List<String> captureImageee = Arrays.asList("/dev/input/event1", "1", "254", "0");
			Map<String, Object> captureImageCmddd = ImmutableMap.of("command", "sendevent","args", captureImageee);
			driver.executeScript("mobile: shell", captureImageCmddd);
			List<String> captureImageee4 = Arrays.asList("/dev/input/event1", "0", "0", "0");
			Map<String, Object> captureImageCmddd4 = ImmutableMap.of("command", "sendevent","args", captureImageee4);
			driver.executeScript("mobile: shell", captureImageCmddd4);
		} catch (Exception e) {
			System.out.println("Key Code Execution Failed :\n"+e);
		}

		try {
			Thread.sleep(2000);
			String tut = driver.findElementById("tv_tutorial_line_1").getText();
//			System.out.println(tut);
			driver.findElementById("iv_close_tutorial").click();
			Thread.sleep(1000);
			// ****** This Part is for set the bottom selection flag
			//				try {
			//					driver.findElementById("arrow_iv").click();
			//					Thread.sleep(1000);
			//					BottomSelected=false;
			//				} catch (Exception e) {
			//					BottomSelected=true;
			//				}

			return tut;
		} catch (Exception e) {
			Thread.sleep(1000);
			System.out.println("===========TUTORIAL END===========");
			return "";
		}
	}

	public ArrayList<String> longPressToGetGuideline(int timeInSec) throws InterruptedException, URISyntaxException, IOException {
		System.out.println("===========GUIDELINE START===========");
		ArrayList<String> dataG = new ArrayList<String>();
		int count=0;
		while (count<2) {
			boolean isTranslationProcessing = false;
			System.out.println(driverMedia.currentActivity());
			try {
				List<String> captureImage = Arrays.asList("/dev/input/event1", "1", "254", "1");
				Map<String, Object> captureImageCmd = ImmutableMap.of("command", "sendevent","args", captureImage);
				driver.executeScript("mobile: shell", captureImageCmd);
				//			ArrayList<String> temp = new ArrayList<String>();
				for (int i = 0; i <timeInSec*7.1528 ; i++) {
					try {
						String procc = driver.findElementById("translated_text").getText();
						if (!procc.isEmpty()) {
							System.out.println(procc);
							isTranslationProcessing=true;
							i=(int) (timeInSec*7.1528);
							break;
						}else {}} catch (Exception e) {}

					if (!isTranslationProcessing) {
						try {
//							System.out.println("isTranslationProcessing : "+isTranslationProcessing);
							String x = driver.findElementById("tv_guideline").getText();
							String y = driver.findElementById("tv_guideline_example1").getText();
							String z = driver.findElementById("tv_guideline_example2").getText();
							if (!x.isEmpty() && !y.isEmpty() && !z.isEmpty()) {
								dataG.add(x);
								dataG.add(y);
								dataG.add(z);
								count=count+2;
								i=(int) (timeInSec*7.1528);
							}else {
//								System.out.println("All Guideline String Not Found ");
								}
						} catch (Exception e) {
//							System.out.println("Get Guideline String Exceptions... ");
						}
					}
					List<String> captureImagee = Arrays.asList("/dev/input/event1", "0", "0", "0");
					Map<String, Object> captureImageCmdd = ImmutableMap.of("command", "sendevent","args", captureImagee);
					driver.executeScript("mobile: shell", captureImageCmdd);
				}
				List<String> captureImagee3 = Arrays.asList("/dev/input/event1", "1", "254", "0");
				Map<String, Object> captureImageCmddd = ImmutableMap.of("command", "sendevent","args", captureImagee3);
				driver.executeScript("mobile: shell", captureImageCmddd);
				List<String> captureImageee4 = Arrays.asList("/dev/input/event1", "0", "0", "0");
				Map<String, Object> captureImageCmddd4 = ImmutableMap.of("command", "sendevent","args", captureImageee4);
				driver.executeScript("mobile: shell", captureImageCmddd4);
				Thread.sleep(1000);
//				System.out.println("=====================LAST=========================="+isTranslationProcessing);
//				System.out.println("=====================LAST=========================="+count);
				count++;
				if (isTranslationProcessing) {
//					dataG.add("Translation was Processed");
					System.out.println("Media Driver Is Active With : "+driverMedia.currentActivity());
					try {
						Thread.sleep(10000);
						driver.findElementById("close_dialog").click();
						Thread.sleep(1000);
					} catch (Exception e) {
						System.out.println("Unable To Click close_dialog");
					}
				}else {
//					System.out.println("Saved Guideline Properly Or STT Not Supported");
				}
				//				return dataG;
			} catch (Exception e) {
				System.out.println("Key Code Execution Failed :\n"+e);
				support.errorScreenshot("guidelineError");
				//				return dataG;
			}
			
		}
		System.out.println("===========GUIDELINE END===========");
		if (dataG.size()==0) {
			dataG.add("Translation was Processed or STT Not Supported");
			System.out.println(driverMedia.currentActivity());
			return dataG;
		}else {
			System.out.println(driverMedia.currentActivity());
			return dataG;
		}
	}

	public ArrayList<String> longPressToVoiceInput(int timeInSec) throws InterruptedException, URISyntaxException, IOException {
		System.out.println("===========TRANSLATION START===========");
		ArrayList<String> dataT = new ArrayList<String>();
		int count=0;
		while (count<2) {
			System.out.println(driver.currentActivity());
			System.out.println("===============================TRANSLATION START=========== "+count);
			boolean isTranslationProcessing = false;
			System.out.println(driverMedia.currentActivity());
			try {
				List<String> captureImage = Arrays.asList("/dev/input/event1", "1", "254", "1");
				Map<String, Object> captureImageCmd = ImmutableMap.of("command", "sendevent","args", captureImage);
				driver.executeScript("mobile: shell", captureImageCmd);
				//			ArrayList<String> temp = new ArrayList<String>();
				for (int i = 0; i <timeInSec*7.1528 ; i++) {
					try {
						String procc = driver.findElementById("translated_text").getText();
						if (!procc.isEmpty()) {
							if (!dataT.contains(procc) && dataT.size()<5) {
								dataT.add(procc);
								System.out.println(procc);
								isTranslationProcessing=true;
							}else if(dataT.size()>3){
								i= (int) (timeInSec*7.1528);
							} else {
//								System.out.println("already exists.."); 
								}
						}else {
//							System.out.println("Speech Detection: Not Found ");
							}
						} catch (Exception e) {
//							System.out.println("Speech Detection: Exceptions");
						}
					List<String> captureImagee2 = Arrays.asList("/dev/input/event1", "0", "0", "0");
					Map<String, Object> captureImageCmdd2 = ImmutableMap.of("command", "sendevent","args", captureImagee2);
					driver.executeScript("mobile: shell", captureImageCmdd2);
				}
				List<String> captureImagee3 = Arrays.asList("/dev/input/event1", "1", "254", "0");
				Map<String, Object> captureImageCmddd3 = ImmutableMap.of("command", "sendevent","args", captureImagee3);
				driver.executeScript("mobile: shell", captureImageCmddd3);
				List<String> captureImageee4 = Arrays.asList("/dev/input/event1", "0", "0", "0");
				Map<String, Object> captureImageCmddd4 = ImmutableMap.of("command", "sendevent","args", captureImageee4);
				driver.executeScript("mobile: shell", captureImageCmddd4);
				Thread.sleep(1000);
				System.out.println("===================== Try : =========================="+count);
				System.out.println("=====================VOICE INPUT STATUS=========================="+isTranslationProcessing);
				if (isTranslationProcessing) {
					dataT.add("Translation was Processed");
					count=count+2;
					System.out.println(" Voice Input Success and Media Driver Is Active With : "+driverMedia.currentActivity());
				}else {}
			} catch (Exception e) {
				System.out.println("Key Code Execution Failed :\n"+e);
				support.errorScreenshot("Voice Input Error");
			}
			count++;
			Thread.sleep(2000);
		}
		System.out.println("===================================TRANSLATION END===========");
		if (dataT.size()>0) {
			System.out.println(driverMedia.currentActivity());
			Thread.sleep(1000);
			return dataT;
		}else {
			System.out.println("Voice Input Not Working...");
			System.out.println("Voice Input Not Working --- dataT.size() -- "+dataT.size());
			System.out.println(driverMedia.currentActivity());
			return dataT;
		}
	}
	
	public void longPressHomeKey(int timeInSec) throws InterruptedException {
		System.out.println(driverMedia.currentActivity());
		try {
			List<String> captureImage = Arrays.asList("/dev/input/event1", "1", "254", "1");
			Map<String, Object> captureImageCmd = ImmutableMap.of("command", "sendevent","args", captureImage);
			driver.executeScript("mobile: shell", captureImageCmd);
			//				Calendar calendar = Calendar.getInstance();
			//				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			//				String datetime = formatter.format(calendar.getTime());
			//				System.out.println(datetime);
			for (int i = 0; i <timeInSec*7.1528 ; i++) {
				List<String> captureImagee = Arrays.asList("/dev/input/event1", "0", "0", "0");
				Map<String, Object> captureImageCmdd = ImmutableMap.of("command", "sendevent","args", captureImagee);
				driver.executeScript("mobile: shell", captureImageCmdd);
			}
			List<String> captureImageee = Arrays.asList("/dev/input/event1", "1", "254", "0");
			Map<String, Object> captureImageCmddd = ImmutableMap.of("command", "sendevent","args", captureImageee);
			driver.executeScript("mobile: shell", captureImageCmddd);
			List<String> captureImageee4 = Arrays.asList("/dev/input/event1", "0", "0", "0");
			Map<String, Object> captureImageCmddd4 = ImmutableMap.of("command", "sendevent","args", captureImageee4);
			driver.executeScript("mobile: shell", captureImageCmddd4);
		} catch (Exception e) {
			System.out.println("Key Code Execution Failed :\n"+e);
		}
		System.out.println(driverMedia.currentActivity());
		//		Calendar calendar = Calendar.getInstance();
		//		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		//		String datetime = formatter.format(calendar.getTime());
		//		System.out.println(datetime);
	}
	
	@SuppressWarnings("deprecation")
	public ArrayList<String> getEngineNameForReTranslation(String languageName) throws InterruptedException {
		System.out.println("----- getEngineName --------- "+languageName);
		ArrayList<String> engineNameTTSChecked = new ArrayList<String>();
		ArrayList<String> engineNameTTSselected = new ArrayList<String>();

		ArrayList<String> allEngineName = new ArrayList<String>();
		List<LogEntry> logEntries = driver.manage().logs().get("logcat").filter(Level.ALL);
		for (LogEntry logEntry : logEntries) {
			
			if (logEntry.getMessage().matches(".*TTSBase\\schecked\\sengine\\sname\\s:\\s\\S.*")) {
				engineNameTTSChecked.add(logEntry.getMessage());
				//				System.out.println(logEntry.getMessage());
			}else {}
			if (logEntry.getMessage().matches(".*TTSBase\\sselected\\sengine\\sname\\s:\\s\\S.*")) {
				engineNameTTSselected.add(logEntry.getMessage());
				//				System.out.println(logEntry.getMessage());
			}else {}
		}

		if(engineNameTTSChecked.size()>0) {
			String data = "";
			try {
				if(!engineNameTTSChecked.get(engineNameTTSChecked.size()-1).isEmpty()) {
					data = engineNameTTSChecked.get(engineNameTTSChecked.size()-1);
				}else {data = "TTS Engine not found...";}
			} catch (Exception e) {
				System.out.println("engineNameTTS.size() "+engineNameTTSChecked.size());
				System.out.println("ERROR == "+e);
			}
			allEngineName.add(data);
		}else{allEngineName.add("TTS Checked Missing or Not_Supported");}

		if(engineNameTTSselected.size()>0) {
			String data = "";
			try {
				if(!engineNameTTSselected.get(engineNameTTSselected.size()-1).isEmpty()) {
					data = engineNameTTSselected.get(engineNameTTSselected.size()-1);
					if (data.contains("null")) {
						data = data.concat(" or Not_Supported");
					}else {
						data=data;
					}
				}else {data = "TTS selected not found...";}
			} catch (Exception e) {
				System.out.println("engineNameTTSselected.size() "+engineNameTTSselected.size());
				System.out.println("ERROR == "+e);
			}
			allEngineName.add(data);
		}else{
			allEngineName.add("TTS Selected Missing or Not Supported");
		}
		return allEngineName;
	}

	@SuppressWarnings("deprecation")
	public ArrayList<String> getEngineName(String languageName) throws InterruptedException {
		System.out.println("----- getEngineName --------- "+languageName);
		ArrayList<String> engineNameSTT = new ArrayList<String>();
		ArrayList<String> PunctuationSTT = new ArrayList<String>();
		ArrayList<String> engineNameTTT = new ArrayList<String>();
		ArrayList<String> PunctuationTTT = new ArrayList<String>();
		ArrayList<String> engineNameTTS = new ArrayList<String>();
		ArrayList<String> engineNameTTSselected = new ArrayList<String>();
		ArrayList<String> PunctuationTTS = new ArrayList<String>();
		ArrayList<String> engineNameInfo = new ArrayList<String>();

		ArrayList<String> allEngineName = new ArrayList<String>();
		List<LogEntry> logEntries = driver.manage().logs().get("logcat").filter(Level.ALL);
		for (LogEntry logEntry : logEntries) {
			if (logEntry.getMessage().matches(".*STTbase\\slanguage\\sname\\s:\\s\\S.*") || logEntry.getMessage().matches(".*STTbase\\slanguage\\sname\\s:\\s\\S.*")) {
				//				System.out.println(logEntry.getMessage());
				engineNameSTT.add(logEntry.getMessage());
			}else {}
			if (logEntry.getMessage().matches(".*setTTTEngineName:\\s\\S.*")) {
				engineNameTTT.add(logEntry.getMessage());
				//				System.out.println(logEntry.getMessage());
			}else {} 
			if (logEntry.getMessage().matches(".*TTSBase\\schecked\\sengine\\sname\\s:\\s\\S.*")) {
				engineNameTTS.add(logEntry.getMessage());
				//				System.out.println(logEntry.getMessage());
			}else {}
			if (logEntry.getMessage().matches(".*TTSBase\\sselected\\sengine\\sname\\s:\\s\\S.*")) {
				engineNameTTSselected.add(logEntry.getMessage());
				//				System.out.println(logEntry.getMessage());
			}else {}
			if(logEntry.getMessage().matches(".*\\ssetTTSValue\\S\\sp.*")){
				engineNameInfo.add(logEntry.getMessage());
				//				System.out.println(logEntry.getMessage());
			}else {}

			if(logEntry.getMessage().matches(".*FromLang:.*")){
				PunctuationSTT.add(logEntry.getMessage());
				//				System.out.println(logEntry.getMessage());
			}else {}
			if(logEntry.getMessage().matches(".*\\sTTT\\stext:.*")){
				PunctuationTTT.add(logEntry.getMessage());
				//				System.out.println(logEntry.getMessage());
			}else {}

			if(logEntry.getMessage().matches(".*\\sTransformed\\sTTT\\stext\\sremoved\\snptc\\s\\sFrom\\sLog:\\s.*")){
				PunctuationTTS.add(logEntry.getMessage());
				//				System.out.println(logEntry.getMessage());
			}else {}
		}

		if (engineNameSTT.size()>0) {
			try {
				allEngineName.add(engineNameSTT.get(engineNameSTT.size()-1));
			} catch (Exception e) {
				// TODO: handle exception
			}}else {allEngineName.add("STT Missing");} 

		if (engineNameTTT.size()>0) {
			try {
				allEngineName.add(engineNameTTT.get(engineNameTTT.size()-1));
			} catch (Exception e) {
				// TODO: handle exception
			}}else {allEngineName.add("TTT Missing");}

		if(engineNameTTS.size()>0) {
			String data = "";
			try {
				if(!engineNameTTS.get(engineNameTTS.size()-1).isEmpty()) {
					data = engineNameTTS.get(engineNameTTS.size()-1);
				}else {data = "TTS Engine not found...";}
			} catch (Exception e) {
				System.out.println("engineNameTTS.size() "+engineNameTTS.size());
				System.out.println("ERROR == "+e);
			}
			allEngineName.add(data);
		}else{allEngineName.add("TTS Checked Missing or Not Supported");}

		if(engineNameTTSselected.size()>0) {
			String data = "";
			try {
				if(!engineNameTTSselected.get(engineNameTTSselected.size()-1).isEmpty()) {
					data = engineNameTTSselected.get(engineNameTTSselected.size()-1);
					if (data.contains("null")) {
						data = data.concat(" or Not Supported");
					}else {
						data=data;
					}
				}else {data = "TTS selected not found...";}
			} catch (Exception e) {
				System.out.println("engineNameTTSselected.size() "+engineNameTTSselected.size());
				System.out.println("ERROR == "+e);
			}
			allEngineName.add(data);
		}else{
			allEngineName.add("TTS Selected Missing or Not Supported");
		}

		if (PunctuationSTT.size()>0) {
			try {
				allEngineName.add(PunctuationSTT.get(PunctuationSTT.size()-1));
			} catch (Exception e) {}
		}else {
			allEngineName.add("PunctuationSTT Missing");
		} 
		if (PunctuationTTT.size()>0) {
			try {
				allEngineName.add(PunctuationTTT.get(PunctuationTTT.size()-1));
			} catch (Exception e) {}
		}else {
			allEngineName.add("PunctuationTTT Missing");
		} 
		if (PunctuationTTS.size()>0) {
			try {
				allEngineName.add(PunctuationTTS.get(PunctuationTTS.size()-1));
			} catch (Exception e) {}
		}else {
			allEngineName.add("PunctuationTTS Missing");
		} 
		return allEngineName;
	}

	public String selectNextLanguage(String TOPorBOTTOM) {
		if (TOPorBOTTOM.toLowerCase().contains("top")) {
			try {
				driver.findElementById("layout_gradient_top").click();
			} catch (Exception e) {
				// TODO: handle exception
			}}else {
				try {
					driver.findElementById("layout_gradient_bottom").click();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}

		List<MobileElement>	langlistview = driver.findElementById("recycler_view").findElementsById("parentLayout");
		int activeIndex = 0;
		String languageName = "";
		for (int i = 0; i < langlistview.size(); i++) {
			try {
				if (langlistview.get(i).findElementById("checkbox").isDisplayed()) {
					activeIndex=i;
				}else {
					System.out.println("No Item Selected !!!");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		try {
			languageName=langlistview.get(activeIndex+1).findElementById("lan_name").getText();
			langlistview.get(activeIndex+1).click();
			driver.findElementById("text_back_okay").click();
			Thread.sleep(2000);
			return languageName;
		} catch (Exception e) {
			return "";
		}
	}

	public void goToMusicDirectory(String folderName) {
		try {
			MobileElement setSourceLanguage = (MobileElement) driverMedia.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageNameMedia+":id/listView\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+folderName+"\"));"));
			setSourceLanguage.click();
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Select Source Language Failed...!!!"+e);
		}
	}

	public void goToMusicDirectoryV47() {
		try {
			driverMedia.findElement(By.id("songtab")).click();
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Music Tab Selection Failed.!!!"+e);
		}
	}

	public void playTargetMusic(String music) {
		try {
			MobileElement setSourceLanguage = (MobileElement) driverMedia.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageNameMedia+":id/listView\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+music+"\"));"));
			setSourceLanguage.click();
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("playTargetMusic Failed...!!!"+e);
		}
	}

	public void playTargetMusicV47(String music) {
		try {
			MobileElement setSourceMusic = (MobileElement) driverMedia.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"android:id/list\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+music+"\"));"));
			setSourceMusic.click();
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("playTargetMusic Failed...!!!"+e);
		}
	}

	public void stopTargetMusicV47() {
		int x = 190;
		int y= 750;
		if (driverMedia.currentActivity().contains(".MediaPlaybackActivity")) {
			try {
				new TouchAction(driverMedia).press(PointOption.point(x,y)).release().perform();
				//				driverMedia.findElementById("play_background").click();
				Thread.sleep(1000);
				driverMedia.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
			} catch (Exception e) {
				System.out.println("Unable to Pause Audio. \n"+e);
			}
		}
	}

	public void stopTargetMusic() {
		int x = 359;
		int y= 1246;
		if (driverMedia.currentActivity().contains(".ui.player.MediaPlayBackActivity")) {
			try {
				new TouchAction(driverMedia).press(PointOption.point(x,y)).release().perform();
				//				driverMedia.findElementById("play_background").click();
				Thread.sleep(1000);
				driverMedia.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
			} catch (Exception e) {
				System.out.println("Unable to Pause Audio. \n"+e);
			}
		}
	}

	public void LanguageSelection(String TOPorBOTTOM, String languageName) throws Exception {
		try {
			if (TOPorBOTTOM.toLowerCase().contains("top")) {
				driver.findElement(By.id("layout_gradient_top")).click();
			}else if(TOPorBOTTOM.toLowerCase().contains("bottom")) {
				driver.findElement(By.id("layout_gradient_bottom")).click();
			}else {
				System.out.println("No Button Clicked to Select Language...");
			}
			Thread.sleep(1000);
			System.out.println("Select "+TOPorBOTTOM+" Language - "+languageName);
			this.languageSelectionByUiAutomator(languageName);
		} catch (Exception e) {
			support.backToHomeScreen();
		}
	}


	public boolean languageSelectionByUiAutomator(String languageName) {
		try {
			MobileElement setSourceLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/recycler_view\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+languageName+"\"));"));
			setSourceLanguage.click();
			driver.findElementById("text_back_okay").click();
			Thread.sleep(1000);
			return true;
		} catch (Exception e) {
			System.out.println("Select Source Language Failed...!!!"+e);
			return false;
		}
	}

	public void setEnvironment(String fromLanguageName, String toLanguageName) throws Exception {
		this.LanguageSelection("bottom", fromLanguageName);
		this.LanguageSelection("top", toLanguageName);
		Thread.sleep(2000);
		System.out.println(this.getLanguageNameLocal("top"));
	}

	public String getLanguageNameLocal(String TOPorBOTTOM) {
		if (TOPorBOTTOM.toLowerCase().contains("top")) {
			try {
				return driver.findElement(By.id("tv_translate_view")).getText();
			} catch (Exception e) {
				return "";
			}
		}else {
			try {
				return driver.findElement(By.id("tv_native_view")).getText();
			} catch (Exception e) {
				return "";
			}
		}
	}

	public String getLanguageNameSystem(String TOPorBOTTOM) {
		if (TOPorBOTTOM.toLowerCase().contains("top")) {
			try {
				return driver.findElement(By.id("tv_translate_system")).getText();
			} catch (Exception e) {
				return "";
			}
		}else {
			try {
				return driver.findElement(By.id("tv_native_system")).getText();
			} catch (Exception e) {
				return "";
			}
		}
	}

	public void writeVoiceTranslationInformaton(String dirName,String fileName,  String outputSheetName) throws IOException{
		
		File store = new File(System.getProperty("user.dir")+"/assets/"+dirName);
		if (store.exists() && store.isDirectory()) {
			  System.out.println("File Storing : "+store.getCanonicalPath());
			}else {
				boolean bool = store.mkdir();
			      if(bool){
			         System.out.println("Directory created successfully");
			      }else{
			         System.out.println("Sorry couldn’t create specified directory");
			      }
			}
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		String datetime = formatter.format(calendar.getTime());
		datetime = datetime.replaceAll("[:-]", "").replaceAll("[ ]", "_");
		fileName = fileName.concat(datetime+".xlsx");
		File file =    new File(store+"\\"+fileName);
		FileOutputStream outputStream = new FileOutputStream(file);
		Workbook myWorkbook = new XSSFWorkbook();
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			String sheetName = myWorkbook.createSheet(outputSheetName+"_"+datetime).getSheetName();
			XSSFSheet sheet = (XSSFSheet) myWorkbook.getSheet(sheetName);
			for (int i = 0; i < ReTranslationData.size(); i++) {
				Row newRow = sheet.createRow(i);
//				System.out.println(ReTranslationData.get(i).getMainString());
				for(int j = 0; j <ReTranslationData.get(i).getSubStringList().size()+1 ; j++){
					if (j==0) {
						Cell countryNameCell = newRow.createCell(j);
						countryNameCell.setCellValue(ReTranslationData.get(i).getMainString());
					}else {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(ReTranslationData.get(i).getSubStringList().get(j-1));
					}
				}
			}
//			for (int i = 0; i < VoiceTrInformation.size(); i++) {
//				Row newRow = sheet.createRow(i);
//				System.out.println(VoiceTrInformation.get(i).getMainString());
//				for(int j = 0; j <VoiceTrInformation.get(i).getSubStringList().size()+1 ; j++){
//					if (j==0) {
//						Cell countryNameCell = newRow.createCell(j);
//						countryNameCell.setCellValue(VoiceTrInformation.get(i).getMainString());
//					}else {
//						Cell cell = newRow.createCell(j);
//						cell.setCellValue(VoiceTrInformation.get(i).getSubStringList().get(j-1));
//					}
//				}
//			}
			try {
				myWorkbook.write(outputStream);
				outputStream.close();
			} catch (Exception e) {
				// TODO: handle exception
				myWorkbook.close();
			}

		}else {
			System.out.println("File Formate Not Matched...It Must Be XLSX");
		}    
	}
}

