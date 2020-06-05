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

public class MojitalkExecute {
	private static File classpathRoot = new File(System.getProperty("user.dir"));
	public static AndroidDriver<MobileElement> driver;
	public static String packageName = "com.sourcenext.mojitalk";
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
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
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

	//-------------------First Part From Home --------------------

				@Test (priority=0)
				public void SettingsMenuStringTest() throws Exception {
					try {
						System.out.println("1 "+selectedSystemLanguage);
	//					this.selectedSystemLanguage = support.selectSystemLanguageOption("System Language","日本語で操作する");
	//					System.out.println("2 "+selectedSystemLanguage);
	//					this.selectedSystemLanguage = support.selectSystemLanguageOption("System Language","English");
						System.out.println("3 "+selectedSystemLanguage);
						
						driver.findElementById("btn_navigation_menu_left").click();
						Thread.sleep(2000);
//						driver.findElementsById("tv_phrase_name").get(0).click();
//						Thread.sleep(2000);
						ArrayList<String> screenNames = new ArrayList<String>();
						ArrayList<String> menuIds = new ArrayList<String>();
						ArrayList<String> menuNames = new ArrayList<String>();
						screenNames.add(menuSettings.getToolbarTitleString().get(0));
						menuIds.add(menuSettings.getToolbarTitleString().get(1));
						menuNames.add(menuSettings.getToolbarTitleString().get(2));
						screenNames.add(menuSettings.getMobileData().get(0));
						menuIds.add(menuSettings.getMobileData().get(1));
						menuNames.add(menuSettings.getMobileData().get(2));
						screenNames.add(menuSettings.getWiFiString().get(0));
						menuIds.add(menuSettings.getWiFiString().get(1));
						menuNames.add(menuSettings.getWiFiString().get(2));
						screenNames.add(menuSettings.getBluetoothString().get(0));
						menuIds.add(menuSettings.getBluetoothString().get(1));
						menuNames.add(menuSettings.getBluetoothString().get(2));
						ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
						if (selectedSystemLanguage.contains("Use in English")) {
							settingsMenuStored.clear();
							info = menuSettings.getSettingsMenuList("Reset");
						}else if (selectedSystemLanguage.contains("使用繁體中文操作")) {
							settingsMenuStored.clear();
							info = menuSettings.getSettingsMenuList("重設");
						}else if (selectedSystemLanguage.contains("Operar em português")) {
							settingsMenuStored.clear();
							info = menuSettings.getSettingsMenuList("Reinicializar");
						}else if (selectedSystemLanguage.contains("Работать на русском")) {
							settingsMenuStored.clear();
							info = menuSettings.getSettingsMenuList("Сбросить");
						}else if (selectedSystemLanguage.contains("ใช้งานด้วยภาษาไทย")) {
							settingsMenuStored.clear();
							info = menuSettings.getSettingsMenuList("รีเซ็ต");
						}else if (selectedSystemLanguage.contains("Operasi Dalam Bahasa Melayu")) {
							settingsMenuStored.clear();
							info = menuSettings.getSettingsMenuList("Set Semula");
						}else {
							settingsMenuStored.clear();
							info = menuSettings.getSettingsMenuList("Reset");
							System.out.println("System Language Not Stored");
						}
						screenNames.addAll(info.get(0));
						menuIds.addAll(info.get(1));
						menuNames.addAll(info.get(2));
	//					settingsMenuStored.addAll(info.get(2));
						
						StringsOfScreenModel strModel =  new StringsOfScreenModel(screenNames, menuIds,menuNames);
						strModel.setScreenName(screenNames);
						strModel.setElementsId(menuIds);
						strModel.setStringsOfScreen(menuNames);
						stringsOfScreenToExcel.add(strModel);
						System.out.println("Total Menu Count"+settingsMenuStored.size());
						support.backToHomeScreen();	
					} catch (Exception e) {
						// TODO: handle exception
					}	
				}

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
//							ScreenNameInExcel.add(cell.getStringCellValue());
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
