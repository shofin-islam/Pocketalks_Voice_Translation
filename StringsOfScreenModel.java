import java.util.ArrayList;

public class StringsOfScreenModel {

	  private ArrayList<String> screenNames;
	  private ArrayList<String> stringsOfScreen;
	  private ArrayList<String> elementsId;
	  
	  
	  public StringsOfScreenModel(ArrayList<String> screenNames , ArrayList<String> elementsId, ArrayList<String> stringsOfScreen){
		    this.screenNames = screenNames;
		    this.stringsOfScreen = stringsOfScreen;
		    this.elementsId = elementsId;
		  }
	  
	  public ArrayList<String> getScreenNames(){
		    return this.screenNames;
		  }
	  
	  public void setScreenName(ArrayList<String> screenName){
		  this.screenNames= screenName;
		  }
	  
	  public ArrayList<String> getStringsOfScreen(){
		    return this.stringsOfScreen;
		  }
	  
	  public void setStringsOfScreen(ArrayList<String> stringsOfScreen){
		  this.stringsOfScreen= stringsOfScreen;
		  }
	  
	  public ArrayList<String> getElementsId(){
		    return this.elementsId;
		  }
	  
	  public void setElementsId(ArrayList<String> elementsId){
		  this.elementsId= elementsId;
		  }
}
