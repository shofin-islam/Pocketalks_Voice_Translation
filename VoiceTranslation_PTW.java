import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.offset.PointOption;

public class VoiceTranslation_PTW {
	public static AndroidDriver<MobileElement> driver;
	public static AndroidDriver<MobileElement> driverMedia;
	public static String packageName = "com.sourcenext.pocketalk";
	public static String packageNameMedia = "com.alc.filemanager";
	public String filePath = System.getProperty("user.dir");

	private PtsSupportModel_toString support;
	ArrayList<MainAndSubStringStoreModel> VoiceTrInformation = new ArrayList<MainAndSubStringStoreModel>();
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
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.2:4724/wd/hub"), capabilities);
		Thread.sleep(8000);
		support = new PtsSupportModel_toString(driver, packageName);
//		ArrayList<String> songsList = new ArrayList<String>(Arrays. asList("arabic","bangla","english"));
		
		DesiredCapabilities capabilities2 = new DesiredCapabilities();
		capabilities2.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		capabilities2.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android"); 
		capabilities2.setCapability(MobileCapabilityType.PLATFORM_VERSION,"8.1.0");
		capabilities2.setCapability(MobileCapabilityType.DEVICE_NAME,"HUAWEI Y5 Prime 2018");
		capabilities2.setCapability("udid", "5LX9K19112900172");
		capabilities2.setCapability("noReset","true"); 
		capabilities2.setCapability("fullReset","false");
		capabilities2.setCapability("appPackage", packageNameMedia);
		capabilities2.setCapability("appActivity",packageNameMedia+".activities.MainActivity");
		capabilities2.setCapability("clearDeviceLogsOnStart", true);
		capabilities2.setCapability("skipDeviceInitialization", true);
//		driverMedia = new AndroidDriver<MobileElement>(new URL("http://127.0.0.2:4724/wd/hub"), capabilities2);
//		Thread.sleep(3000);
//		this.goToMusicDirectory("Audio_PTS_STT");
	}

	@Test (priority=1)
	public void voiceTranslation() throws Exception {
		//			new TouchAction(driver).press(PointOption.point(support.getMaxPositionOfDisplay("bottom"))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(support.getMaxPositionOfDisplay("top"))).release().perform();
		//			Thread.sleep(1000);
		boolean isFirst = true;
		boolean foundLast = false;
		String lanName="";
		while (!foundLast) {
			try {
				if (isFirst) {
					this.setEnvironment("English(US)", "Afrikaans");
					lanName = "Afrikaans (Afrikaans)";
					isFirst = false;
				}else{
					lanName = this.selectNextLanguage("TO");
				}

				String tutorialString = this.longPressToGetTutorial();
				ArrayList<String> guideLineString = this.longPressToGetGuideline(2);

				this.playTargetMusic("english_audacity.mp3");
				this.longPressHomeKey(14);
				Thread.sleep(10000);
				this.stopTargetMusic();
				Thread.sleep(4000);
				try {
					if (driver.findElementById("close_dialog").isDisplayed()) {
						driver.findElementById("close_dialog").click();
						System.out.println("Translation Success...");
					}else {
						System.out.println("Fail - Voice Input ...");
					}

				} catch (Exception e) {
					System.out.println("Fail - Voice Input ...");
				}
				Thread.sleep(2000);
				ArrayList<String> engines = new ArrayList<String>();
				engines.add(this.getTranslateFromLanguageName());
				engines.add(this.getTranslateToLanguageName());
				engines.addAll(this.getEngineName(lanName));
				engines.add(tutorialString);
				engines.addAll(guideLineString);
				MainAndSubStringStoreModel ms = new MainAndSubStringStoreModel(lanName,engines );
				ms.setMainString(lanName);
				ms.setSubStringList(engines);
				VoiceTrInformation.add(ms);
				if (lanName.contains("Bengali")||lanName.contains("Amharic")) {
					foundLast = true;
					break;
				}else {}
			} catch (Exception e) {
				support.backToHomeScreen();
				support.errorScreenshot("Voice_Translation_Failed");
			}
		}
	}

	@AfterSuite
	public void tearDown() throws IOException {
		this.writeVoiceTranslationInformaton(System.getProperty("user.dir"), "VoiceTranslationResults", "English");
		driver.quit();
		driverMedia.quit();
	}

	public String longPressToGetTutorial() throws InterruptedException {
		try {
			List<String> captureImage = Arrays.asList("/dev/input/event1", "1", "106", "1");
			Map<String, Object> captureImageCmd = ImmutableMap.of("command", "sendevent","args", captureImage);
			driver.executeScript("mobile: shell", captureImageCmd);
			List<String> captureImagee = Arrays.asList("/dev/input/event1", "0", "0", "0");
			Map<String, Object> captureImageCmdd = ImmutableMap.of("command", "sendevent","args", captureImagee);
			driver.executeScript("mobile: shell", captureImageCmdd);
			List<String> captureImageee = Arrays.asList("/dev/input/event1", "1", "106", "0");
			Map<String, Object> captureImageCmddd = ImmutableMap.of("command", "sendevent","args", captureImageee);
			driver.executeScript("mobile: shell", captureImageCmddd);
			List<String> captureImageee4 = Arrays.asList("/dev/input/event1", "0", "0", "0");
			Map<String, Object> captureImageCmddd4 = ImmutableMap.of("command", "sendevent","args", captureImageee4);
			driver.executeScript("mobile: shell", captureImageCmddd4);
		} catch (Exception e) {
			System.out.println("Key Code Execution Failed :\n"+e);
		}

		try {
			Thread.sleep(1000);
			String tut = driver.findElementById("tv_tutorial_line_1").getText();
			System.out.println(tut);
			driver.findElementById("iv_close_tutorial").click();
			Thread.sleep(1000);
			return tut;
		} catch (Exception e) {
			return "";
		}
	}

	public ArrayList<String> longPressToGetGuideline(int timeInSec) throws InterruptedException {
		ArrayList<String> dataG = new ArrayList<String>(); 
		try {
			List<String> captureImage = Arrays.asList("/dev/input/event1", "1", "106", "1");
			Map<String, Object> captureImageCmd = ImmutableMap.of("command", "sendevent","args", captureImage);
			driver.executeScript("mobile: shell", captureImageCmd);
//			ArrayList<String> temp = new ArrayList<String>();
			for (int i = 0; i <timeInSec*7.1528 ; i++) {
				try {
					String x = driver.findElementById("tv_guideline").getText();
					String y = driver.findElementById("tv_guideline_example1").getText();
					String z = driver.findElementById("tv_guideline_example2").getText();
					if (!x.isEmpty() && !y.isEmpty() && !z.isEmpty()) {
						dataG.add(x);
						dataG.add(y);
						dataG.add(z);
						i=(int) (timeInSec*7.1528);
					}else {}
				} catch (Exception e) {}
				List<String> captureImagee = Arrays.asList("/dev/input/event1", "0", "0", "0");
				Map<String, Object> captureImageCmdd = ImmutableMap.of("command", "sendevent","args", captureImagee);
				driver.executeScript("mobile: shell", captureImageCmdd);
			}
			List<String> captureImageee = Arrays.asList("/dev/input/event1", "1", "106", "0");
			Map<String, Object> captureImageCmddd = ImmutableMap.of("command", "sendevent","args", captureImageee);
			driver.executeScript("mobile: shell", captureImageCmddd);
			List<String> captureImageee4 = Arrays.asList("/dev/input/event1", "0", "0", "0");
			Map<String, Object> captureImageCmddd4 = ImmutableMap.of("command", "sendevent","args", captureImageee4);
			driver.executeScript("mobile: shell", captureImageCmddd4);
			for (int kk = 0; kk < dataG.size(); kk++) {
				System.out.println(dataG.get(kk));
			}
			return dataG;
		} catch (Exception e) {
			System.out.println("Key Code Execution Failed :\n"+e);
			return dataG;
		}
	}

	public void longPressHomeKey(int timeInSec) throws InterruptedException {
		try {
			List<String> captureImage = Arrays.asList("/dev/input/event1", "1", "105", "1");
			Map<String, Object> captureImageCmd = ImmutableMap.of("command", "sendevent","args", captureImage);
			driver.executeScript("mobile: shell", captureImageCmd);
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String datetime = formatter.format(calendar.getTime());
			System.out.println(datetime);
			for (int i = 0; i <timeInSec*7.1528 ; i++) {
				List<String> captureImagee = Arrays.asList("/dev/input/event1", "0", "0", "0");
				Map<String, Object> captureImageCmdd = ImmutableMap.of("command", "sendevent","args", captureImagee);
				driver.executeScript("mobile: shell", captureImageCmdd);
			}
			List<String> captureImageee = Arrays.asList("/dev/input/event1", "1", "105", "0");
			Map<String, Object> captureImageCmddd = ImmutableMap.of("command", "sendevent","args", captureImageee);
			driver.executeScript("mobile: shell", captureImageCmddd);
			List<String> captureImageee4 = Arrays.asList("/dev/input/event1", "0", "0", "0");
			Map<String, Object> captureImageCmddd4 = ImmutableMap.of("command", "sendevent","args", captureImageee4);
			driver.executeScript("mobile: shell", captureImageCmddd4);
		} catch (Exception e) {
			System.out.println("Key Code Execution Failed :\n"+e);
		}
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String datetime = formatter.format(calendar.getTime());
		System.out.println(datetime);
	}

	@SuppressWarnings("deprecation")
	public ArrayList<String> getEngineName(String languageName) throws InterruptedException {
		System.out.println("----- getEngineName --------- "+languageName);
		ArrayList<String> engineNameSTT = new ArrayList<String>();
		ArrayList<String> PunctuationSTT = new ArrayList<String>();
		ArrayList<String> engineNameTTT = new ArrayList<String>();
		ArrayList<String> PunctuationTTT = new ArrayList<String>();
		ArrayList<String> engineNameTTS = new ArrayList<String>();
		ArrayList<String> PunctuationTTS = new ArrayList<String>();
		ArrayList<String> engineNameInfo = new ArrayList<String>();

		ArrayList<String> allEngineName = new ArrayList<String>();
		List<LogEntry> logEntries = driver.manage().logs().get("logcat").filter(Level.ALL);
		for (LogEntry logEntry : logEntries) {
			if (logEntry.getMessage().matches(".*newSttEngineImpl\\sengine\\sname\\s.*")) {
				System.out.println(logEntry.getMessage());
				engineNameSTT.add(logEntry.getMessage());
			}else {}
			if (logEntry.getMessage().matches(".*setTTTEngineName:\\s\\S.*")) {
				engineNameTTT.add(logEntry.getMessage());
				System.out.println(logEntry.getMessage());
			}else {} 
			if (logEntry.getMessage().matches(".*\\sTTSBase\\schecked\\sengine\\sname\\s\\S\\s\\S.*") || logEntry.getMessage().matches(".*\\sTTSBase\\sselected\\sengine\\sname\\s:\\s\\S.*") || logEntry.getMessage().matches(".*\\sTTS.*")) {
				engineNameTTS.add(logEntry.getMessage());
				System.out.println(logEntry.getMessage());
			}else {}
			if(logEntry.getMessage().matches(".*\\ssetTTSValue\\S\\sp.*")){
				engineNameInfo.add(logEntry.getMessage());
				System.out.println(logEntry.getMessage());
			}else {}

			if(logEntry.getMessage().matches(".*FromLang:.*")){
				PunctuationSTT.add(logEntry.getMessage());
				System.out.println(logEntry.getMessage());
			}else {}
			if(logEntry.getMessage().matches(".*\\sTTT\\stext:.*")){
				PunctuationTTT.add(logEntry.getMessage());
				System.out.println(logEntry.getMessage());
			}else {}

			if(logEntry.getMessage().matches(".*\\sTransformed\\sTTT\\stext\\sremoved\\snptc\\s\\sFrom\\sLog:\\s.*")){
				PunctuationTTS.add(logEntry.getMessage());
				System.out.println(logEntry.getMessage());
			}else {}
		}

		if (engineNameSTT.size()>0) {
			try {
				allEngineName.add(engineNameSTT.get(engineNameSTT.size()-1));
			} catch (Exception e) {
				// TODO: handle exception
			}}else {allEngineName.add("SST Missing");} 

		if (engineNameTTT.size()>0) {
			try {
				allEngineName.add(engineNameTTT.get(engineNameTTT.size()-1));
			} catch (Exception e) {
				// TODO: handle exception
			}}else {allEngineName.add("TTT Missing");}

		String data = "";
		if(engineNameTTS.size()>0) {
			if (engineNameTTS.size()<=2) {
				for (int ft = 0; ft < 2; ft++) {
					try {
						data = engineNameTTS.get(ft)+" === "; 
					} catch (Exception e) {
						System.out.println("ERROR == "+e);
					}
				}
			}else {
				for (int m = 1; m < 3; m++) {
					try {
						data = engineNameTTS.get(engineNameTTS.size()-m)+" === "; 
					} catch (Exception e) {}
				}
			}
			allEngineName.add(data);
		}else{
			allEngineName.add("TTS Missing");
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

	public String selectNextLanguage(String TOorFrom) throws InterruptedException {
		Thread.sleep(500);
		if (TOorFrom.toLowerCase().contains("to")) {
			try {
				driver.findElementById("btn_translate").click();
			} catch (Exception e) {
				// TODO: handle exception
			}}else {
				try {
					driver.findElementById("btn_native").click();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		Thread.sleep(1000);
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
			driver.findElementById("ib_top_back_lang_selection").click();
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

	public void playTargetMusic(String music) {
		try {
			MobileElement setSourceLanguage = (MobileElement) driverMedia.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageNameMedia+":id/listView\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+music+"\"));"));
			setSourceLanguage.click();
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("playTargetMusic Failed...!!!"+e);
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
				driver.findElement(By.id("btn_translate")).click();
			}else if(TOPorBOTTOM.toLowerCase().contains("bottom")) {
				driver.findElement(By.id("btn_native")).click();
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
			driver.findElementById("ib_top_back_lang_selection").click();
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
		System.out.println(this.getTranslateToLanguageName());
	}

	public String getTranslateToLanguageName() {

		try {
			return driver.findElement(By.id("btn_translate")).getText();
		} catch (Exception e) {
			return "";
		}
	}

	public String getTranslateFromLanguageName() {

		try {
			return driver.findElement(By.id("btn_native")).getText();
		} catch (Exception e) {
			return "";
		}
	}


	public void writeVoiceTranslationInformaton(String filePath,String fileName,  String outputSheetName) throws IOException{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String datetime = formatter.format(calendar.getTime());
		datetime = datetime.replaceAll("[:-]", "").replaceAll("[ ]", "_");
		fileName = fileName.concat(datetime+".xlsx");
		File file =    new File(filePath+"\\PTW_Results_March\\"+fileName);
		FileOutputStream outputStream = new FileOutputStream(file);
		Workbook myWorkbook = new XSSFWorkbook();
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			String sheetName = myWorkbook.createSheet(outputSheetName+"_"+datetime).getSheetName();
			XSSFSheet sheet = (XSSFSheet) myWorkbook.getSheet(sheetName);
			for (int i = 0; i < VoiceTrInformation.size(); i++) {
				Row newRow = sheet.createRow(i);
				System.out.println(VoiceTrInformation.get(i).getMainString());
				for(int j = 0; j <VoiceTrInformation.get(i).getSubStringList().size()+1 ; j++){
					if (j==0) {
						Cell countryNameCell = newRow.createCell(j);
						countryNameCell.setCellValue(VoiceTrInformation.get(i).getMainString());
					}else {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(VoiceTrInformation.get(i).getSubStringList().get(j-1));
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

