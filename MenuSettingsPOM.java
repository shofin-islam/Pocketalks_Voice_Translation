import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class MenuSettingsPOM {

	public AndroidDriver<MobileElement> driver;

	By mobileData = By.id("tv_mobile_data");
	By wifi = By.id("tv_wifi");
	By bluetooth = By.id("tv_bluetooth");
	By toolbarTitle = By.id("toolbar_title");
	String screenName;

	public MenuSettingsPOM(AndroidDriver<MobileElement> driver){
		this.driver = driver;
		this.screenName = "Menu_Settings";
	}
	
	public ArrayList<String> getMobileData() {
		try {
			ArrayList<String> info = new ArrayList<String>();
			info.add(this.screenName);
			info.add("tv_mobile_data");
			info.add(driver.findElement(mobileData).getText());
			return info;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void tapOnMobileDataText() {
		try {
			driver.findElement(mobileData).click();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void tapOnWiFiText() {
		try {
			driver.findElement(wifi).click();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void tapOnBluetoothText() {
		try {
			driver.findElement(bluetooth).click();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public ArrayList<String> getWiFiString() {
		try {
			ArrayList<String> info = new ArrayList<String>();
			info.add(this.screenName);
			info.add("tv_wifi");
			info.add(driver.findElement(wifi).getText());
			return info;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public ArrayList<String> getBluetoothString() {
		try {
			ArrayList<String> info = new ArrayList<String>();
			info.add(this.screenName);
			info.add("tv_bluetooth");
			info.add(driver.findElement(bluetooth).getText());
			return info;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public ArrayList<String> getToolbarTitleString() {
		try {
			ArrayList<String> info = new ArrayList<String>();
			info.add(this.screenName);
			info.add("toolbar_title");
			info.add(driver.findElement(toolbarTitle).getText());
			return info;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public String getStringById(String resourceId) {
		try {
			return driver.findElement(By.id(resourceId)).getText();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}


	public ArrayList<ArrayList<String>> getSettingsMenuList(String lastMenuName) throws Exception{
		//		lastMenuName ="Reset" ;
		ArrayList<String> menuNamesList = new ArrayList<String>();
		ArrayList<String> menuNamesId = new ArrayList<String>();
		ArrayList<String> screenNames = new ArrayList<String>();
		String menuNames = "";
		String lastMenuFound = "";
		Point targetPoaitionOfToolbar = getTargetPositionOfToolbar("toolbar");
		int elementViewSize = driver.findElementsById("parentLayout").get(2).getSize().getHeight();
		
		while (lastMenuFound.isEmpty()) {
			java.util.List<MobileElement> allChats = driver.findElementsById("parentLayout");
			Point last = null;
			for (int i = 0; i < allChats.size(); i++) {
				int height = driver.findElementsById("parentLayout").get(2).getSize().getHeight();
				if (height==elementViewSize) {
					try {
						String menu = allChats.get(i).findElementsById("tv_name").get(0).getText();
						if (!menu.isEmpty() && !menuNamesList.contains(menu)) {
								screenNames.add(this.screenName);
								menuNamesId.add("tv_name");
								menuNamesList.add(menu);
								System.out.println("Seetings Menu --- "+menu);
								last = allChats.get(i).getLocation();
								
								if (menu.toLowerCase().contains(lastMenuName.toLowerCase())) {
									lastMenuFound = "Yes";
									System.out.println("Last Menu Found");
									break;
								}
						}else {
//							System.out.println("Unable to get text !!");
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}else {
//					System.out.println("Size Not Matched!!");
				}
			}
			new TouchAction(driver).press(PointOption.point(last)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(targetPoaitionOfToolbar.getX(),targetPoaitionOfToolbar.getY())).release().perform();
			Thread.sleep(1000);
		}
		ArrayList<ArrayList<String>> settingsMenuInfo = new ArrayList<ArrayList<String>>();
		settingsMenuInfo.add(screenNames);
		settingsMenuInfo.add(menuNamesId);
		settingsMenuInfo.add(menuNamesList);
		return settingsMenuInfo;
	}
	
	
	public ArrayList<String> getSettingsMenuListOnly(String lastMenuName) throws Exception{
		//		lastMenuName ="Reset" ;
		ArrayList<String> menuNamesList = new ArrayList<String>();
		String menuNames = "";
		String lastMenuFound = "";
		Point targetPoaitionOfToolbar = getTargetPositionOfToolbar("toolbar");
		int elementViewSize = driver.findElementsById("parentLayout").get(2).getSize().getHeight();
		
		while (lastMenuFound.isEmpty()) {
			java.util.List<MobileElement> allChats = driver.findElementsById("parentLayout");
			Point last = null;
			for (int i = 0; i < allChats.size(); i++) {
				int height = driver.findElementsById("parentLayout").get(2).getSize().getHeight();
				if (height==elementViewSize) {
					try {
						String menu = allChats.get(i).findElementsById("tv_name").get(0).getText();
						if (!menu.isEmpty() && !menuNamesList.contains(menu)) {
								menuNamesList.add(menu);
								last = allChats.get(i).getLocation();
								
								if (menu.toLowerCase().contains(lastMenuName.toLowerCase())) {
									lastMenuFound = "Yes";
									System.out.println("Last Menu Found");
									break;
								}
						}else {
//							System.out.println("Unable to get text !!");
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}else {
//					System.out.println("Size Not Matched!!");
				}
			}
			new TouchAction(driver).press(PointOption.point(last)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(targetPoaitionOfToolbar.getX(),targetPoaitionOfToolbar.getY())).release().perform();
			Thread.sleep(1000);
		}
		return menuNamesList;
	}

	public Point getTargetPositionOfToolbar(String elementId) {
		int pintX = driver.findElement(By.id(elementId)).getLocation().getX();
		int pintY = driver.findElement(By.id(elementId)).getLocation().getY();
		int width = driver.findElement(By.id(elementId)).getSize().getWidth();
		int height = driver.findElement(By.id(elementId)).getSize().getHeight();
		int mm = pintX+height+30;
		Point p= new Point(pintX,mm );
		return p;
	}
}
