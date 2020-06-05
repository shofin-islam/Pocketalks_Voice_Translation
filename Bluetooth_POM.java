import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class Bluetooth_POM {
	
	AndroidDriver<MobileElement> driver;

	By toolbar_title = By.id("toolbar_title");
    By viewBluetoothStatus = By.id("viewBluetoothStatus");
    By switch_bluetooth = By.id("switch_bluetooth");
    By bluetooth_off_text = By.id("bluetooth_off_text");
    By pairedDevice = By.xpath("//android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]");
    By availableDevice = By.xpath("//android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]");
    
    String screenName;
    ArrayList<String> mainMenuList = new ArrayList<String>();
    
    
    public Bluetooth_POM(AndroidDriver<MobileElement> driver){
        this.driver = driver;
        this.screenName = "Bluetooth";
    }
    
	public ArrayList<ArrayList<String>> getBluetoothStrings() throws InterruptedException {
    	ArrayList<String> screenNames = new ArrayList<String>();
		ArrayList<String> stringsIds = new ArrayList<String>();
		ArrayList<String> strings = new ArrayList<String>();
		
		for (int i = 0; i < 6; i++) {
			if (i==0) {
				screenNames.add(this.getToolbarTitle().get(0));
				stringsIds.add(this.getToolbarTitle().get(1));
				strings.add(this.getToolbarTitle().get(2));
			}else if(i==1) {
				screenNames.add(this.getBluetoothStatusAsOFF().get(0));
				stringsIds.add(this.getBluetoothStatusAsOFF().get(1));
				strings.add(this.getBluetoothStatusAsOFF().get(2));
			}else if(i==2) {
				screenNames.add(this.getBluetoothOFF_SuggestionText().get(0));
				stringsIds.add(this.getBluetoothOFF_SuggestionText().get(1));
				strings.add(this.getBluetoothOFF_SuggestionText().get(2));
			}else if(i==3) {
				screenNames.add(this.getBluetoothStatusAsON().get(0));
				stringsIds.add(this.getBluetoothStatusAsON().get(1));
				strings.add(this.getBluetoothStatusAsON().get(2));
			}else if(i==4) {
				screenNames.add(this.getPairedDeviceString().get(0));
				stringsIds.add(this.getPairedDeviceString().get(1));
				strings.add(this.getPairedDeviceString().get(2));
			}else{
				screenNames.add(this.getAvailableDeviceString().get(0));
				stringsIds.add(this.getAvailableDeviceString().get(1));
				strings.add(this.getAvailableDeviceString().get(2));
			}
		}
		ArrayList<ArrayList<String>> information = new ArrayList<ArrayList<String>>();
		information.add(screenNames);
		information.add(stringsIds);
		information.add(strings);
		return information;
    }
	
public ArrayList<String> getPairedDeviceString() throws InterruptedException {
		
		if (!driver.findElement(switch_bluetooth).getText().toLowerCase().contains("on")) {
			driver.findElement(switch_bluetooth).click();
			Thread.sleep(10000);
		}else {
			System.out.println("Bluetooth ON Alreay!!");
		}
		
		List<MobileElement> elements = driver.findElementsByXPath("//android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout");
		String pdevicesString = elements.get(0).findElementByClassName("android.widget.TextView").getText();
		try {
			ArrayList<String> info = new ArrayList<String>();
			info.add(this.screenName);
			info.add("//android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout");
			info.add(pdevicesString);
			return info;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
public ArrayList<String> getAvailableDeviceString() throws InterruptedException {
	
	if (!driver.findElement(switch_bluetooth).getText().toLowerCase().contains("on")) {
		driver.findElement(switch_bluetooth).click();
		Thread.sleep(10000);
	}else {
		System.out.println("Bluetooth ON Alreay!!");
	}
	
	List<MobileElement> elements = driver.findElementsByXPath("//android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout");
	String pdevicesString = elements.get(1).findElementByClassName("android.widget.TextView").getText();
	try {
		ArrayList<String> info = new ArrayList<String>();
		info.add(this.screenName);
		info.add("//android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout");
		info.add(pdevicesString);
		return info;
	} catch (Exception e) {
		System.out.println(e);
		return null;
	}
	
}
    
	public ArrayList<String> getBluetoothOFF_SuggestionText() throws InterruptedException {
		
		if (driver.findElement(switch_bluetooth).getText().toLowerCase().contains("on")) {
//			System.out.println("ON");
			driver.findElement(switch_bluetooth).click();
			Thread.sleep(6000);
			try {
				ArrayList<String> info = new ArrayList<String>();
//				System.out.println("TRY  !!");
				System.out.println("Bluetooth off text "+driver.findElement(bluetooth_off_text).getText());
				info.add(this.screenName);
				info.add("viewBluetoothStatus");
				info.add(driver.findElement(bluetooth_off_text).getText());
				return info;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}else {
			try {
				ArrayList<String> info = new ArrayList<String>();
//				System.out.println("OFF");
				System.out.println("Bluetooth off text "+driver.findElement(bluetooth_off_text).getText());
				info.add(this.screenName);
				info.add("viewBluetoothStatus");
				info.add(driver.findElement(bluetooth_off_text).getText());
				return info;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
		
			
		
		
	}
	public ArrayList<String> getToolbarTitle() {
		try {
			ArrayList<String> info = new ArrayList<String>();
			info.add(this.screenName);
			info.add("toolbar_title");
			info.add(driver.findElement(toolbar_title).getText());
			return info;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
    public ArrayList<String> getBluetoothStatusAsON() throws InterruptedException {
    	if (driver.findElement(switch_bluetooth).getText().toLowerCase().contains("on")) {
    		try {
    			ArrayList<String> info = new ArrayList<String>();
    			info.add(this.screenName);
    			info.add("viewBluetoothStatus");
    			info.add(driver.findElement(viewBluetoothStatus).getText());
    			return info;
    		} catch (Exception e) {
    			System.out.println(e);
    			return null;
    		}
		}else {
			driver.findElement(switch_bluetooth).click();
			Thread.sleep(10000);
			try {
    			ArrayList<String> info = new ArrayList<String>();
    			info.add(this.screenName);
    			info.add("viewBluetoothStatus");
    			info.add(driver.findElement(viewBluetoothStatus).getText());
    			return info;
    		} catch (Exception e) {
    			System.out.println(e);
    			return null;
    		}
		}
		
	}
    
    public ArrayList<String> getBluetoothStatusAsOFF() throws InterruptedException {
    	if (driver.findElement(switch_bluetooth).getText().toLowerCase().contains("on")) {
    		driver.findElement(switch_bluetooth).click();
			Thread.sleep(6000);
    		try {
    			ArrayList<String> info = new ArrayList<String>();
    			info.add(this.screenName);
    			info.add("viewBluetoothStatus");
    			info.add(driver.findElement(viewBluetoothStatus).getText());
    			System.out.println(driver.findElement(viewBluetoothStatus).getText());
    			return info;
    		} catch (Exception e) {
    			System.out.println(e);
    			return null;
    		}
		}else {
			try {
    			ArrayList<String> info = new ArrayList<String>();
    			info.add(this.screenName);
    			info.add("viewBluetoothStatus");
    			info.add(driver.findElement(viewBluetoothStatus).getText());
    			return info;
    		} catch (Exception e) {
    			System.out.println(e);
    			return null;
    		}
		}
	}
}
