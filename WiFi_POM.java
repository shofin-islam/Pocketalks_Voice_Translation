import java.util.ArrayList;

import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class WiFi_POM {
	AndroidDriver<MobileElement> driver;
	String screenName;
    ArrayList<String> wifiON = new ArrayList<String>();
    ArrayList<String> wifiOFF = new ArrayList<String>();
    String pkgName;
    
    
    public WiFi_POM(AndroidDriver<MobileElement> driver){
        this.driver = driver;
        this.screenName = "Wi-Fi";
        this.pkgName = "com.sourcenext.pocketalk.settings";
    }
    
    public ArrayList<ArrayList<String>> getWiFiStrings() throws InterruptedException {
    	ArrayList<String> screenNames = new ArrayList<String>();
		ArrayList<String> stringsIds = new ArrayList<String>();
		ArrayList<String> strings = new ArrayList<String>();
		
		ArrayList<String> tmp = new ArrayList<String>();
		
		try {
			if (driver.findElementById("com.sourcenext.pocketalk.settings:id/switch_widget").getAttribute("checked").contains("true")) {
				System.out.println("WI-FI ON");
				driver.findElementById("com.sourcenext.pocketalk.settings:id/switch_widget").click();
				Thread.sleep(2000);
				System.out.println("WI-FI OFF");
				try {
					tmp.add(driver.findElementById("com.sourcenext.pocketalk.settings:id/switch_text").getText());
					tmp.add(driver.findElementById("android:id/title").getText());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}else {
				try {
					tmp.add(driver.findElementById("com.sourcenext.pocketalk.settings:id/switch_text").getText());
					tmp.add(driver.findElementById("android:id/title").getText());
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			driver.findElementById("com.sourcenext.pocketalk.settings:id/switch_widget").click();
			Thread.sleep(6000);
			tmp.add(driver.findElementById("com.sourcenext.pocketalk.settings:id/switch_text").getText());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (tmp.size()>0) {
			for (int i = 0; i < tmp.size(); i++) {
				screenNames.add("WiFi");
				stringsIds.add("No_Need");
				strings.add(tmp.get(i));
			}
		}
		ArrayList<ArrayList<String>> information = new ArrayList<ArrayList<String>>();
		information.add(screenNames);
		information.add(stringsIds);
		information.add(strings);
		
		if (driver.findElementById("com.sourcenext.pocketalk.settings:id/switch_widget").getAttribute("checked").contains("true")) {
//			System.out.println("Leave With WI-FI ON");
		}else {
			driver.findElementById("com.sourcenext.pocketalk.settings:id/switch_widget").click();
			System.out.println("Leave With WI-FI ON By Click After Results");
			Thread.sleep(6000);
		}
		return information;
    }
    
    
    public void getWiFiStatusAsON() throws InterruptedException {
    	if (driver.findElementById("com.sourcenext.pocketalk.settings:id/switch_widget").getAttribute("checked").contains("true")) {
    		try {
    			wifiON.add(this.screenName);
    			wifiON.add("switch_text");
    			wifiON.add(driver.findElementById("com.sourcenext.pocketalk.settings:id/switch_text").getText());
    			
    		} catch (Exception e) {
    			System.out.println(e);
    		}
		}else {
			driver.findElementById("com.sourcenext.pocketalk.settings:id/switch_widget").click();
			Thread.sleep(1000);
			try {
    			wifiON.add(this.screenName);
    			wifiON.add("switch_text");
    			wifiON.add(driver.findElementById("com.sourcenext.pocketalk.settings:id/switch_text").getText());
    		} catch (Exception e) {
    			System.out.println(e);
    		}
		}
		
	}
    
    public void getWiFiStatusAsOFF() throws InterruptedException {
    	if (driver.findElementById("com.sourcenext.pocketalk.settings:id/switch_widget").getAttribute("checked").contains("true")) {
    		driver.findElementById("com.sourcenext.pocketalk.settings:id/switch_widget").click();
			Thread.sleep(5000);
    		try {
    			wifiOFF.add(this.screenName);
    			wifiOFF.add("switch_text");
    			wifiOFF.add(driver.findElementById("com.sourcenext.pocketalk.settings:id/switch_text").getText());
    		} catch (Exception e) {
    			System.out.println(e);
    		}
		}else {
			try {
    			wifiOFF.add(this.screenName);
    			wifiOFF.add("switch_text");
    			wifiOFF.add(driver.findElementById("com.sourcenext.pocketalk.settings:id/switch_text").getText());
    		} catch (Exception e) {
    			System.out.println(e);
    		}
		}
	}
}
