import java.time.Duration;
import java.util.ArrayList;

import org.apache.commons.math3.analysis.function.Add;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class MobileData_POM {
	AndroidDriver<MobileElement> driver;

	By toolbar_title = By.id("toolbar_title");
	By tv_mobile_data_status = By.id("tv_mobile_data_status");
	By switch_mobile_data = By.id("switch_mobile_data");
	By simCardToUse = By.xpath("//android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView");
	By label_global_sim = By.id("label_global_sim");
	By connect_apn_global = By.id("connect_apn_global");
	By label_external_sim = By.id("label_external_sim");
	By connect_apn_external = By.id("connect_apn_external");
	String screenName;
	ArrayList<ArrayList<String>> allInfo = new ArrayList<ArrayList<String>>();


	public MobileData_POM(AndroidDriver<MobileElement> driver){
		this.driver = driver;
		this.screenName = "Mobile_Data";
	}
	
	public ArrayList<ArrayList<String>> getMobileDataStrings() throws InterruptedException {
		ArrayList<String> screenNames = new ArrayList<String>();
		ArrayList<String> stringsIds = new ArrayList<String>();
		ArrayList<String> strings = new ArrayList<String>();
		
		ArrayList<String> temp = new ArrayList<String>();
		
		try {
			String x = driver.findElementById("switch_mobile_data").getAttribute("checked");
			if (x.contains("true")) {
				temp.add(driver.findElement(toolbar_title).getText());
				temp.add(driver.findElement(tv_mobile_data_status).getText());
				temp.add(driver.findElement(simCardToUse).getText());
				temp.add(driver.findElement(label_global_sim).getText());
				temp.add(driver.findElement(connect_apn_global).getText());
				temp.add(driver.findElement(label_external_sim).getText());
				temp.add(driver.findElement(connect_apn_external).getText());
			}else {
				temp.add(driver.findElement(tv_mobile_data_status).getText());
				driver.findElementById("switch_mobile_data").click();
				Thread.sleep(6000);
				temp.add(driver.findElement(toolbar_title).getText());
				temp.add(driver.findElement(tv_mobile_data_status).getText());
				temp.add(driver.findElement(simCardToUse).getText());
				temp.add(driver.findElement(label_global_sim).getText());
				temp.add(driver.findElement(connect_apn_global).getText());
				temp.add(driver.findElement(label_external_sim).getText());
				temp.add(driver.findElement(connect_apn_external).getText());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			try {
				driver.findElementById("connect_apn_external").click();
				Thread.sleep(2000); 
				new TouchAction(driver).press(PointOption.point(274,286)).release().perform();
				Thread.sleep(3000); 
				temp.add(driver.findElementById("android:id/message").getText());
				temp.add(driver.findElementById("android:id/button1").getText());
				temp.add(driver.findElementById("android:id/button2").getText());
				driver.findElementById("android:id/button2").click();
				Thread.sleep(1000); 
				driver.findElementById("connect_apn_external").click();
				Thread.sleep(2000); 
				new TouchAction(driver).press(PointOption.point(265,202)).release().perform();
				Thread.sleep(5000);
				try {
					driver.findElementsById("com.sourcenext.pocketalk.settings:id/text_layout").get(0).click();
					driver.findElementsByXPath("//android.view.ViewGroup/android.widget.LinearLayout/android.widget.TextView").get(1).click();
					Thread.sleep(3000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				ArrayList<String> tmp2 = new ArrayList<String>();
				int sss = 0;
				int found = 0;
				int dd = 0;
				while (found<2) {
					java.util.List<MobileElement> allChats = driver.findElementsByXPath("//android.support.v7.widget.RecyclerView/android.widget.LinearLayout");
					Point last = null;
					for (int i = 0; i < allChats.size(); i++) {
							try {
								String menu = allChats.get(i).findElementById("android:id/title").getText();
								if (!menu.isEmpty() && !tmp2.contains(menu)) {
									tmp2.add(menu);
									last = allChats.get(i).getLocation();
									if (menu.contains("MVNO")) {
										found++;
										System.out.println("Last APN Info Found");
										
									}else if(dd==12){
										found=found+2;
									}else {
										
									}
								}else {
//									System.out.println("Unable to get text !!");
								}
							} catch (Exception e) {
								// TODO: handle exception
							}
					}
					new TouchAction(driver).press(PointOption.point(last)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(last.getX(),200)).release().perform();
					Thread.sleep(1000);
					dd++;
				}
				temp.addAll(tmp2);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Mobile Data String Start ======");
		if (temp.size()>0) {
			for (int i = 0; i < temp.size(); i++) {
				screenNames.add("MobileData");
				stringsIds.add("No_Need");
				strings.add(temp.get(i));
				System.out.println(temp.get(i));
			}
		}else {
			
		}
		System.out.println("Mobile Data String End ======");
//		this.getToolbarTitle();
//		this.getMobileDataStatusAsON();
//		this.getMobileDataStatusAsOFF();
//		this.getSIMCardToUseText();
//		this.get_label_global_sim_Text();
//		this.get_connect_apn_global_Text();
//		this.get_label_external_sim_Text();
//		this.get_connect_apn_external_Text();
//
//		for (int i = 0; i < allInfo.size(); i++) {
//			for (int j = 0; j < allInfo.get(i).size(); j++) {
//				if (j==0) {
//					screenNames.add(this.allInfo.get(i).get(j));
//				}else if (j==1) {
//					stringsIds.add(this.allInfo.get(i).get(j));
//				}else if (j==2) {
//					strings.add(this.getToolbarTitle().get(2));
//				}
//			}
//		}
		ArrayList<ArrayList<String>> information = new ArrayList<ArrayList<String>>();
		information.add(screenNames);
		information.add(stringsIds);
		information.add(strings);
		return information;
	}

	public void get_label_global_sim_Text() throws InterruptedException {
		try {
			ArrayList<String> info = new ArrayList<String>();
			info.add(this.screenName);
			info.add("label_global_sim");
			info.add(driver.findElement(label_global_sim).getText());
			allInfo.add(info);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public ArrayList<String> get_connect_apn_global_Text() throws InterruptedException {
		try {
			ArrayList<String> info = new ArrayList<String>();
			info.add(this.screenName);
			info.add("connect_apn_global");
			info.add(driver.findElement(connect_apn_global).getText());
			return info;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	public ArrayList<String> get_label_external_sim_Text() throws InterruptedException {
		try {
			ArrayList<String> info = new ArrayList<String>();
			info.add(this.screenName);
			info.add("label_external_sim");
			info.add(driver.findElement(label_external_sim).getText());
			return info;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	public ArrayList<String> get_connect_apn_external_Text() throws InterruptedException {
		try {
			ArrayList<String> info = new ArrayList<String>();
			info.add(this.screenName);
			info.add("connect_apn_external");
			info.add(driver.findElement(connect_apn_external).getText());
			return info;
		} catch (Exception e) {
			System.out.println(e);
			return null;
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

	public ArrayList<String> getMobileDataStatusAsON() throws InterruptedException {
		if (driver.findElement(switch_mobile_data).getAttribute("checked").contains("true")) {
			try {
				ArrayList<String> info = new ArrayList<String>();
				info.add(this.screenName);
				info.add("tv_mobile_data_status");
				info.add(driver.findElement(tv_mobile_data_status).getText());
				return info;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}else {
			driver.findElement(switch_mobile_data).click();
			Thread.sleep(1000);
			try {
				ArrayList<String> info = new ArrayList<String>();
				info.add(this.screenName);
				info.add("tv_mobile_data_status");
				info.add(driver.findElement(tv_mobile_data_status).getText());
				return info;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}

	}

	public ArrayList<String> getMobileDataStatusAsOFF() throws InterruptedException {
		if (driver.findElement(switch_mobile_data).getAttribute("checked").contains("true")) {
			driver.findElement(switch_mobile_data).click();
			Thread.sleep(1000);
			try {
				ArrayList<String> info = new ArrayList<String>();
				info.add(this.screenName);
				info.add("tv_mobile_data_status");
				info.add(driver.findElement(tv_mobile_data_status).getText());
				return info;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}else {
			try {
				ArrayList<String> info = new ArrayList<String>();
				info.add(this.screenName);
				info.add("tv_mobile_data_status");
				info.add(driver.findElement(tv_mobile_data_status).getText());
				return info;
			} catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
	}

	public ArrayList<String> getSIMCardToUseText() throws InterruptedException {
		try {
			ArrayList<String> info = new ArrayList<String>();
			info.add(this.screenName);
			info.add("//android.widget.ScrollView/android.widget.LinearLayout/android.widget.TextView");
			info.add(driver.findElement(simCardToUse).getText());
			return info;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

}
