import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.Assert;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;



public class PTSSupportCamera {
	AndroidDriver<MobileElement> driver;
	public String packageName;
	private static File classpathRoot = new File(System.getProperty("user.dir"));
	private static String ANDROID_PHOTO_PATH = "/mnt/sdcard/Android/data/com.sourcenext.pocketalks/files";
	private static String errorImagePath = System.getProperty("user.dir")+"\\assets\\errorImages\\";
	private  ArrayList<String> backButtons = new ArrayList<String>();
	
	public PTSSupportCamera (AndroidDriver<MobileElement> driver, String packageName){
		this.driver = driver;
		this.packageName=packageName;
		this.backButtons.add("btn_navigation_menu_left");
		this.backButtons.add("ib_back");
		this.backButtons.add("iv_top_back");
		this.backButtons.add("ib_top_back");
		this.backButtons.add("discard");
		this.backButtons.add("close_dialog");
		this.backButtons.add("iv_close_tutorial");
		this.backButtons.add("imagebutton_top_back");
		this.backButtons.add("btn_ok");
		this.backButtons.add("//android.widget.LinearLayout/android.widget.Button");
		this.backButtons.add("android:id/button1");

	}

	public void pullCroppedImage() throws IOException {
		String filePath = System.getProperty("user.dir");
		byte[] returnData = driver.pullFile("/sdcard/Android/data/com.sourcenext.pocketalks/files/Pictures/cropped.jpg");
		BufferedImage image=ImageIO.read(new ByteArrayInputStream(returnData));
		ImageIO.write(image, "jpg", new File(filePath+"\\assets\\temp\\","cropped.jpg"));
	}

	public void pullCapturedImage() throws IOException {
		String filePath = System.getProperty("user.dir");
		byte[] returnData = driver.pullFile("/sdcard/Android/data/com.sourcenext.pocketalks/files/temp.jpg");
		BufferedImage image=ImageIO.read(new ByteArrayInputStream(returnData));
		ImageIO.write(image, "jpg", new File(filePath+"\\assets\\temp\\","temp.jpg"));
	}

	public void imagePush(String imageName) throws InterruptedException {
		try {
			File assetDir = new File(classpathRoot, "/assets/images/translation");
			File img = new File(assetDir.getCanonicalPath(), imageName);
			driver.pushFile(ANDROID_PHOTO_PATH + "/temp.jpg", img);
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Push Failed!!");
		}
	}

	public boolean captureImage(){
    	try {
			List<String> captureImage = Arrays.asList("keyevent","285");
			Map<String, Object> captureImageCmd = ImmutableMap.of("command", "input","args", captureImage);
			driver.executeScript("mobile: shell", captureImageCmd);
			Thread.sleep(2000);
			return true;
		} catch (Exception e) {
			System.out.println("Key Code Execution Failed : "+e);
			return false;
		}
    }
	public boolean imageCompare() throws URISyntaxException, IOException {
		try {
			if (this.getImageSize("temp.jpg").get(0)!=this.getImageSize("cropped.jpg").get(0)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public byte[] getReferenceImageB64(String ImageName) throws URISyntaxException, IOException {
		BufferedImage bImage = ImageIO.read(new File(classpathRoot, "/assets/temp/"+ImageName));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bImage, "jpg", bos );
		byte [] data = bos.toByteArray();
		return data;
	}

	public ArrayList<Integer> getImageSize(String imageName) throws IOException {
		ArrayList<Integer> size=new ArrayList<Integer>();
		File assetDir = new File(classpathRoot, "/assets/temp");
		BufferedImage bimg = ImageIO.read(new File(assetDir.getCanonicalPath(), imageName));
		size.add(bimg.getWidth());
		size.add(bimg.getHeight());
		return size;
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

}