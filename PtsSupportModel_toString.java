import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class PtsSupportModel_toString {
	AndroidDriver<MobileElement> driver;
	public String packageName;
	private static File classpathRoot = new File(System.getProperty("user.dir"));
	private static String ANDROID_PHOTO_PATH = "/mnt/sdcard/Android/data/com.sourcenext.pocketalksp/files";
	private static String errorImagePath = System.getProperty("user.dir")+"\\assets\\errorImages\\";
	private  ArrayList<String> backButtons = new ArrayList<String>();
	private  ArrayList<String> settingsBack = new ArrayList<String>();
	
	public PtsSupportModel_toString (AndroidDriver<MobileElement> driver, String packageName){
		this.driver = driver;
		this.packageName=packageName;
		this.backButtons.add("btn_navigation_menu_left");
		this.backButtons.add("ib_back");
		this.backButtons.add("iv_top_back");
		this.backButtons.add("ib_top_back");
		this.backButtons.add("discard");
		this.backButtons.add("close_dialog");
		this.backButtons.add("img_back");
		this.backButtons.add("imagebutton_top_back");
		this.backButtons.add("btn_ok");
		this.backButtons.add("//android.widget.LinearLayout/android.widget.Button");
		this.backButtons.add("android:id/button1");
		this.backButtons.add("top_back_ok_layout");
		this.backButtons.add("text_back_okay");
		this.backButtons.add("com.sourcenext.pocketalk.settings:id/action_bar");
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
					}else if (backButtons.get(i).contains("com.sourcenext.pocketalk.settings:id/action_bar")) {
						try {
							if (!driver.findElementById(backButtons.get(i)).findElementByClassName("android.widget.ImageButton").getId().isEmpty()) {
								driver.findElementById(backButtons.get(i)).findElementByClassName("android.widget.ImageButton").click();
								i=backButtons.size();
							}else {
//								System.out.print("settings back Button Not Working ");
							}
						} catch (Exception e) {
							//							System.out.print("Ok Button Not found !!! ");
						}
					}else {
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
	public boolean selectTargetStringOfList(String viewId, String targetString) throws InterruptedException, IOException {
		try {
			Thread.sleep(1000);
			MobileElement setLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+this.packageName+":id/"+viewId+"\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+targetString+"\"));"));
			setLanguage.click();
			Thread.sleep(500);
			return true;
		} catch (Exception e) {
			this.errorScreenshot(targetString);
			System.out.println("Select Target String Failed...!!!"+e);
			return false;
		}
	}
	
	public String getStringByIdXpath(String IdOrXpath,boolean xpathTrue, int indexNumber ) {
		
		if (!xpathTrue) {
			
			if (indexNumber>-1) {
				try {
					return driver.findElements(By.id(IdOrXpath)).get(indexNumber).getText();
				} catch (Exception e) {
//					System.out.println(e);
					return "";
				}
			}else {
				try {
					return driver.findElement(By.id(IdOrXpath)).getText();
				} catch (Exception e) {
//					System.out.println(e);
					return "";
				}
			}
			
		}else {
			if (indexNumber>-1) {
				try {
					return driver.findElements(By.xpath(IdOrXpath)).get(indexNumber).getText();
				} catch (Exception e) {
//					System.out.println(e);
					return "";
				}
			}else {
				try {
					return driver.findElement(By.xpath(IdOrXpath)).getText();
				} catch (Exception e) {
//					System.out.println(e);
					return "";
				}
			}
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
	
	public String getDeviceNameByDisplay() {
		Dimension windowSize = driver.manage().window().getSize();
		int height = windowSize.getHeight();
		int width = windowSize.getWidth();
		if (height==800 && width==480) {
			return "PTS_PLUS";
		}else if(height==640 && width==480) {
			return "PTS";
		}else {
			return "PTS";
		}
		
	}
	
	public void errorScreenshot(String imageName) throws IOException, InterruptedException{
		File srcFile=driver.getScreenshotAs(OutputType.FILE);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		String datetime = formatter.format(calendar.getTime());
		datetime = datetime.replaceAll("[:-]", "").replaceAll("[ ]", "_");
		File targetFile=new File(errorImagePath + datetime+"_"+imageName+".jpg");
		FileUtils.copyFile(srcFile,targetFile);
		Thread.sleep(1000);
	}
	
	public void outputScreenshot(String dirName, String imageName) throws IOException, InterruptedException{
		if (imageName.contains("/")) {
			imageName = imageName.replaceAll("[/]", "_");
		}else {
			imageName=imageName;
		}
		
		File store = new File(System.getProperty("user.dir")+"/assets/"+dirName);
		if (store.exists() && store.isDirectory()) {
//			  System.out.println(store.getCanonicalPath());
			}else {
				boolean bool = store.mkdir();
			      if(bool){
			         System.out.println("Directory created successfully");
			      }else{
			         System.out.println("Sorry couldn’t create specified directory");
			      }
			}
	      
		File srcFile=driver.getScreenshotAs(OutputType.FILE);
		Thread.sleep(1000);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		String datetime = formatter.format(calendar.getTime());
		datetime = datetime.replaceAll("[:-]", "").replaceAll("[ ]", "_");
		File targetFile=new File(store+"/" + datetime+"_"+imageName+".jpg");
		FileUtils.copyFile(srcFile,targetFile);
		Thread.sleep(1000);
	}
	
	public String selectSystemLanguageOption(String optSystemLanguage, String SYS_Language) throws InterruptedException, IOException {
		try {
			this.backToHomeScreen();
			driver.findElementById("btn_navigation_menu_left").click();
			Thread.sleep(2000);
			driver.findElementsById("tv_phrase_name").get(0).click();
			Thread.sleep(2000);
			MobileElement setSourceLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/settingsRecyclerView\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+optSystemLanguage+"\"));"));
			setSourceLanguage.click();
			Thread.sleep(1000);
			MobileElement setSYSLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/recycler_view\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+SYS_Language+"\"));"));
			setSYSLanguage.click();
			String syslng = setSYSLanguage.getText();
			Thread.sleep(2000);
			this.backToHomeScreen();
			Thread.sleep(2000);
			return syslng;
		} catch (Exception e) {
			this.errorScreenshot(optSystemLanguage);
			System.out.println("Select System Language Failed...!!!"+e);
			this.backToHomeScreen();
			Thread.sleep(2000);
			return "";
		}
	}
	
}
