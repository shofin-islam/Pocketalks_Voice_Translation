import java.util.ArrayList;

import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class MainMenu_POM {

	AndroidDriver<MobileElement> driver;

    By login_login = By.id("authenticate");
    By email_or_user_name = By.id("user");
    By login_password = By.id("password");
    String screenName;
    ArrayList<String> mainMenuList = new ArrayList<String>();
    ArrayList<String> screenNames = new ArrayList<String>();
    ArrayList<String> elementsIdList = new ArrayList<String>();
    
    public MainMenu_POM(AndroidDriver<MobileElement> driver){
        this.driver = driver;
        this.screenName = "MainMenu";
    }
    
    public ArrayList<ArrayList<String>> getMainMenuList() throws InterruptedException{
    	try {    	
    	int menuCount = driver.findElementsById("tv_phrase_name").size();
		
		for (int i = 0; i < menuCount; i++) {
					String menu = driver.findElementsById("tv_phrase_name").get(i).getText();
					screenNames.add(screenName);
					elementsIdList.add("tv_phrase_name");
					mainMenuList.add(menu);
					Thread.sleep(1000);
				}
		}catch (Exception e) {
			// TODO: handle exception
		}
    	ArrayList<ArrayList<String>> elementInfo = new ArrayList<ArrayList<String>>();
    	elementInfo.add(screenNames);
    	elementInfo.add(elementsIdList);
    	elementInfo.add(mainMenuList);
    	return elementInfo;
    }
}
