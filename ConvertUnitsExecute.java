import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class ConvertUnitsExecute {
	private static File classpathRoot = new File(System.getProperty("user.dir"));
	public static AndroidDriver<MobileElement> driver;
	public static String packageName = "com.sourcenext.pocketalk";

	private PtsSupportModel_toString support;

	private ArrayList<String> ExcelHeaderOfUiInfo = new ArrayList<String>();
	ArrayList<MainAndSubStringStoreModel> convertUnits = new ArrayList<MainAndSubStringStoreModel>();
	private ArrayList<String> mccListToLock = new ArrayList<String>();
	private ArrayList<String> mccCountryName = new ArrayList<String>();

	public ArrayList<StringsOfScreenModel> stringsOfScreenToExcel = new ArrayList<StringsOfScreenModel>();
	@BeforeSuite
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
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Thread.sleep(8000);
		support = new PtsSupportModel_toString(driver, packageName);
		String filePath = System.getProperty("user.dir");
		this.readStringInformation(filePath, "StringsInfo.xlsx", "Sheet3");

		ArrayList<String> tt = new ArrayList<String>(Arrays. asList("Amar Bangladesh","Bangladesh","Sonar Bangladesh"));
	}
	//	@Test (priority=0)
	//	public void testMethod() throws Exception {
	//		
	//		new TouchAction(driver).press(PointOption.point(support.getMaxPositionOfDisplay("bottom"))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(support.getMaxPositionOfDisplay("top"))).release().perform();
	//		Thread.sleep(1000);
	//		//Medal Appear here
	//		Thread.sleep(5000);
	//		System.out.println("TEST Working...");
	//		System.out.println(driver.findElementById("rl_clock").findElementById("tv_am_pm").getText());
	//		System.out.println(driver.findElementById("tv_country_name").getText());
	//		System.out.println(driver.findElementById("tv_localZone").getText());
	//		System.out.println(driver.findElementById("rl_txt_clock").findElementById("tv_am_pm").getText());
	//		System.out.println(driver.findElementById("tv_recent_city_name").getText());
	//		System.out.println(driver.findElementById("tv_localZone").getText());
	//		System.out.println(driver.currentActivity());
	//		new TouchAction(driver).press(PointOption.point(support.getMaxPositionOfDisplay("bottom"))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(support.getMaxPositionOfDisplay("top"))).release().perform();
	//		Thread.sleep(1000);
	//	}
	@Test (priority=1)
	public void Convert_Units_Default_Currency_Lock_Info_MCC() throws Exception {
		System.out.println("Convert_Units_Default_Currency_Lock_Info_MCC() -- Started ");
		new TouchAction(driver).press(PointOption.point(support.getMaxPositionOfDisplay("bottom"))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(support.getMaxPositionOfDisplay("top"))).release().perform();
		Thread.sleep(3000);
//		ArrayList<String> mccList = new ArrayList<String>();
//		mccList.add("310");
//		mccList.add("441");
//		mccList.add("262");
//		mccList.add("450");
//		mccList.add("461");
//		mccList.add("208");
//		mccList.add("214");
//		mccList.add("222");
//		mccList.add("466");
//		mccList.add("250");
//		mccList.add("502");
//		mccList.add("520");
//		mccList.add("268");
//		mccList.addAll(mccListToLock);
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

		try {
			for (int sl = 0; sl < 2; sl++) {
				//				String ms="SL";
				String ms = support.selectSystemLanguageOption("System Language",systemLanguageList.get(sl));
				Thread.sleep(3000);
				///////////////////
				driver.findElementById("btn_navigation_menu_left").click();
				Thread.sleep(1000);
				driver.findElementsById("tv_phrase_name").get(2).click();
				Thread.sleep(1000);
				for (int cl = 1; cl <=3; cl++) {
					try {
						driver.findElementById("t9_key_unit_selection").click();
					} catch (Exception e) {
						Thread.sleep(5000);
						driver.findElementById("t9_key_unit_selection").click();
					}
					Thread.sleep(500);
					List<MobileElement> calList = driver.findElementsById("linear_layout_calc");
					calList.get(cl).findElementById("tv_calc_unitName").click();
					driver.findElementById("text_back_okay").click();
					Thread.sleep(800);
					ArrayList<String> weightInfo = new ArrayList<String>();
					try {
						String nativeWeight = driver.findElementById("fromConversionTV").getText();
						String targetWeight = driver.findElementById("toConversionTV").getText();
						weightInfo.add(ms);
						weightInfo.add(nativeWeight);
						weightInfo.add(targetWeight);
					} catch (Exception e) {
						// TODO: handle exception
					}
					MainAndSubStringStoreModel wt = new MainAndSubStringStoreModel("Others", weightInfo);
					wt.setMainString("Others");
					wt.setSubStringList(weightInfo);
					convertUnits.add(wt);
				}
				support.backToHomeScreen();
				////////////////////
				driver.findElementById("btn_navigation_menu_left").click();
				Thread.sleep(1000);
				driver.findElementsById("tv_phrase_name").get(2).click();
				Thread.sleep(2000);
				driver.findElementById("t9_key_unit_selection").click();
				Thread.sleep(1000);
				driver.findElementsById("linear_layout_calc").get(0).findElementById("tv_calc_unitName").click();
				Thread.sleep(500);
				driver.findElementById("text_back_okay").click();
				Thread.sleep(1000);
				support.backToHomeScreen();
				/////////////
				for (int t = 0; t < mccListToLock.size(); t++) {
					try {
						driver.findElementById("btn_navigation_menu_left").click();
						Thread.sleep(1000);
						driver.findElementsById("tv_phrase_name").get(2).click();
						Thread.sleep(2000);
						driver.findElementById("currentLocationIV").click();
						Thread.sleep(1000);
						driver.findElementByClassName("android.widget.EditText").setValue(mccListToLock.get(t));
						Thread.sleep(1000);
						driver.findElementById("android:id/button1").click();
						Thread.sleep(2000);
						driver.findElementById("iv_top_back").click();
						Thread.sleep(1000);
						driver.pressKey(new KeyEvent().withKey(AndroidKey.POWER));
						Thread.sleep(4000);
						driver.pressKey(new KeyEvent().withKey(AndroidKey.POWER));
						//Medal and Lock Screen
						Thread.sleep(7000);
						try {
							ArrayList<String> clInfo = new ArrayList<String>();
							System.out.println("Getting Lock Screen - Clock Information...");
							clInfo.add(mccCountryName.get(t));
							clInfo.add(driver.findElementById("rl_clock").findElementById("tv_am_pm").getText());
							clInfo.add(driver.findElementById("tv_country_name").getText());
							clInfo.add(driver.findElementById("tv_localZone").getText());
							clInfo.add(driver.findElementById("rl_txt_clock").findElementById("tv_am_pm").getText());
							clInfo.add(driver.findElementById("tv_recent_city_name").getText());
							clInfo.add(driver.findElementById("tv_localZone").getText());
							new PTSSupportCamera(driver, packageName).captureImage();
//							new TouchAction(driver).press(PointOption.point(support.getMaxPositionOfDisplay("bottom"))).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(support.getMaxPositionOfDisplay("top"))).release().perform();
//							Thread.sleep(1000);
//							support.backToHomeScreen();
							MainAndSubStringStoreModel clockListInfo = new MainAndSubStringStoreModel("ClockInfo", clInfo);
							clockListInfo.setMainString("ClockInfo");
							clockListInfo.setSubStringList(clInfo);
							convertUnits.add(clockListInfo);
						} catch (Exception e) {
							// TODO: handle exception
							new PtsSupportModel_toString(driver, packageName).errorScreenshot("LockScreen");
							support.backToHomeScreen();
						}

						driver.findElementById("btn_navigation_menu_left").click();
						Thread.sleep(1500);
						driver.findElementsById("tv_phrase_name").get(2).click();
						Thread.sleep(2000);
						ArrayList<String> cUnits = new ArrayList<String>();
						try {
							String nativeCurrency = driver.findElementById("fromConversionTV").getText();
							String nativeCurrencySymbol = driver.findElementById("nativeSymbolViewTV").getText();
							String nativeCurrencyDefaultValue = driver.findElementById("currencyValueInputEditText").getText();
							String targetCurrency = driver.findElementById("toConversionTV").getText();
							String targetCurrencySymbol = driver.findElementById("targetSymbolViewTV").getText();
							String targetCurrencyDefaultValue = driver.findElementById("currencyResultEditText").getText();
							cUnits.add(mccCountryName.get(t));
							cUnits.add(mccListToLock.get(t));
							cUnits.add(nativeCurrency);
							cUnits.add(nativeCurrencySymbol);
							cUnits.add(nativeCurrencyDefaultValue);
							cUnits.add(targetCurrency);
							cUnits.add(targetCurrencySymbol);
							cUnits.add(targetCurrencyDefaultValue);
						} catch (Exception e) {
							// TODO: handle exception
							new PtsSupportModel_toString(driver, packageName).errorScreenshot("ConvertUnits");
						}
						MainAndSubStringStoreModel m = new MainAndSubStringStoreModel("ConvertUnits", cUnits);
						m.setMainString("ConvertUnits");
						m.setSubStringList(cUnits);
						convertUnits.add(m);
						support.backToHomeScreen();
					} catch (Exception e) {
						System.out.println(e);
						new PtsSupportModel_toString(driver, packageName).errorScreenshot("LockScreen_ConvertUnits");
						support.backToHomeScreen();
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
			new PtsSupportModel_toString(driver, packageName).errorScreenshot("CountrySelection");
			support.backToHomeScreen();
		}
		
		System.out.println(" ::::::::: Results :::::::::::: ");
		for (int j = 0; j < convertUnits.size(); j++) {
			System.out.print(convertUnits.get(j).getMainString());
			System.out.print(" : ");
			for (int k = 0; k < convertUnits.get(j).getSubStringList().size(); k++) {
				System.out.print(convertUnits.get(j).getSubStringList().get(k)+" , ");
			}
			System.out.println();
		}
	}

	//	@Test (priority=0)
	//	public void Convert_Units_String_Test() throws Exception {
	//		System.out.println("Convert_Units_String_Test() -- Started ");
	//		driver.findElementById("btn_navigation_menu_left").click();
	//		Thread.sleep(2000);
	//		driver.findElementsById("tv_phrase_name").get(2).click();
	//		Thread.sleep(2000);
	//		driver.findElementById("fromConversionTV").click();
	//		Thread.sleep(2000);
	//		String firstCountryName = "United Arab Emirates Dirham";
	//		String firstCurrency = "(AED)";
	//		String lastCountryName = "Zambian Kwacha";
	//		String lastCurrency = "(ZMW)";
	//		//		String lastCountryName = "Aruban Florin";
	//		//		String lastCurrency = "(AWG)";
	//		MobileElement setSourceLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/unitListRecyclerView\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+firstCountryName+"\"));"));
	//		setSourceLanguage.click();
	//		Thread.sleep(1000);
	//		String ss = setSourceLanguage.getText();
	//		driver.findElementById("text_back_okay").click();
	//		Thread.sleep(2000);
	//		ArrayList<MainAndSubStringStoreModel> convertUnits = new ArrayList<MainAndSubStringStoreModel>();
	//		ArrayList<String> tmp1 = new ArrayList<String>();
	//		try {
	//			String nativeCurrency = driver.findElementById("fromConversionTV").getText();
	//			String nativeCurrencySymbol = driver.findElementById("nativeSymbolViewTV").getText();
	//			String nativeCurrencyDefaultValue = driver.findElementById("currencyValueInputEditText").getText();
	//			String targetCurrency = driver.findElementById("toConversionTV").getText();
	//			String targetCurrencySymbol = driver.findElementById("targetSymbolViewTV").getText();
	//			String targetCurrencyDefaultValue = driver.findElementById("currencyResultEditText").getText();
	//			tmp1.add(firstCurrency);
	//			tmp1.add(nativeCurrency);
	//			tmp1.add(nativeCurrencySymbol);
	//			tmp1.add(nativeCurrencyDefaultValue);
	//			tmp1.add(targetCurrency);
	//			tmp1.add(targetCurrencySymbol);
	//			tmp1.add(targetCurrencyDefaultValue);
	//		} catch (Exception e) {
	//			// TODO: handle exception
	//		}
	//
	//		MainAndSubStringStoreModel m = new MainAndSubStringStoreModel(ss, tmp1);
	//		m.setMainString(firstCountryName);
	//		m.setSubStringList(tmp1);
	//		convertUnits.add(m);
	//
	//		while (!lastCurrency.isEmpty()) {
	//			Thread.sleep(1000);
	//			driver.findElementById("fromConversionTV").click();
	//			Thread.sleep(1000);
	//			ArrayList<String> tmp = new ArrayList<String>();
	//			try {
	//				List<MobileElement> viewElements2 = driver.findElementById("unitListRecyclerView").findElementsByClassName("android.widget.LinearLayout");
	//				int activeIndex = 0;
	//				for (int i = 0; i < viewElements2.size(); i++) {
	//					try {
	//						if (viewElements2.get(i).findElementById("check_unit").isDisplayed()) {
	//							activeIndex=i;
	//						}else {
	//							System.out.println("No Item Selected !!!");
	//						}
	//					} catch (Exception e) {
	//						// TODO: handle exception
	//					}
	//				}
	//
	//				String ms = viewElements2.get(activeIndex+1).findElementById("tv_unit_name").getText();
	//				System.out.println(ms);
	//				String ss1 = viewElements2.get(activeIndex+1).findElementById("tv_unit_code").getText();
	//				tmp.add(ss1);
	//				viewElements2.get(activeIndex+1).click();
	//				driver.findElementById("text_back_okay").click();
	//				Thread.sleep(2000);
	//				try {
	//					String nativeCurrency = driver.findElementById("fromConversionTV").getText();
	//					String nativeCurrencySymbol = driver.findElementById("nativeSymbolViewTV").getText();
	//					String nativeCurrencyDefaultValue = driver.findElementById("currencyValueInputEditText").getText();
	//					String targetCurrency = driver.findElementById("toConversionTV").getText();
	//					String targetCurrencySymbol = driver.findElementById("targetSymbolViewTV").getText();
	//					String targetCurrencyDefaultValue = driver.findElementById("currencyResultEditText").getText();
	//					tmp.add(nativeCurrency);
	//					tmp.add(nativeCurrencySymbol);
	//					tmp.add(nativeCurrencyDefaultValue);
	//					tmp.add(targetCurrency);
	//					tmp.add(targetCurrencySymbol);
	//					tmp.add(targetCurrencyDefaultValue);
	//				} catch (Exception e) {
	//					// TODO: handle exception
	//				}
	//				MainAndSubStringStoreModel m2 = new MainAndSubStringStoreModel(ms, tmp);
	//				m2.setMainString(ms);
	//				m2.setSubStringList(tmp);
	//				convertUnits.add(m2);
	//
	//				String chkT= ms.replaceAll("[ ]", "_");
	//				String chkE= lastCountryName.replaceAll("[ ]", "_");
	//
	//				if (ss1.contains(lastCurrency) && chkT.equals(chkE)) {
	//					lastCurrency="";
	//				}else {
	//					//						System.out.println("Last Item Not Found...");
	//				}
	//			} catch (Exception e) {
	//				System.out.println("get text exception...");
	//			}
	//
	//		}
	//
	//		for (int j = 0; j < convertUnits.size(); j++) {
	//			System.out.print(convertUnits.get(j).getMainString());
	//			System.out.print(" : ");
	//			for (int k = 0; k < convertUnits.get(j).getSubStringList().size(); k++) {
	//				System.out.print(convertUnits.get(j).getSubStringList().get(k)+" , ");
	//			}
	//			System.out.println();
	//		}
	//	}

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
						if (cell.getColumnIndex()==2) {
							mccListToLock.add(formatter.formatCellValue(cell));
							
						}else if(cell.getColumnIndex()==3) {
							mccCountryName.add(formatter.formatCellValue(cell));
						}else{
//							System.out.println("No Need....");
						}
					}
				}
			}
			System.out.println(ExcelHeaderOfUiInfo);
		}	    
	}
	
	@AfterSuite
	public void tearDown() throws IOException {
		this.writeConvertUnitsInformaton(System.getProperty("user.dir"), "TestResults", "Demo");
		driver.quit();
	}
	
	public void writeConvertUnitsInformaton(String filePath,String fileName,  String outputSheetName) throws IOException{
		fileName = fileName.concat(".xlsx");
		File file =    new File(filePath+"\\"+fileName);
		FileOutputStream outputStream = new FileOutputStream(file);
		Workbook myWorkbook = new XSSFWorkbook();
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String datetime = formatter.format(calendar.getTime());
			datetime = datetime.replaceAll("[:-]", "").replaceAll("[ ]", "_");
			String sheetName = myWorkbook.createSheet(outputSheetName+"_"+datetime).getSheetName();
			XSSFSheet sheet = (XSSFSheet) myWorkbook.getSheet(sheetName);

			for (int i = 0; i < convertUnits.size(); i++) {
				Row newRow = sheet.createRow(i);
				System.out.println(convertUnits.get(i).getMainString());
				for(int j = 0; j <convertUnits.get(i).getSubStringList().size()+1 ; j++){
					if (j==0) {
						Cell countryNameCell = newRow.createCell(j);
						countryNameCell.setCellValue(convertUnits.get(i).getMainString());
					}else {
						Cell cell = newRow.createCell(j);
						cell.setCellValue(convertUnits.get(i).getSubStringList().get(j-1));
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

