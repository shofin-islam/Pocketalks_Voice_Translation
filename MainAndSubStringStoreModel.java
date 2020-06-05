
import java.util.ArrayList;

public class MainAndSubStringStoreModel {

	  private String mainString;
	  private ArrayList<String> subStringList;
	  
	  
	  public MainAndSubStringStoreModel(String mainString, ArrayList<String> subStringList){
		    this.mainString = mainString;
		    this.subStringList = subStringList;
		  }
	  
	  public String getMainString(){
		    return this.mainString;
		  }
	  
	  public void setMainString(String mainString){
		  this.mainString= mainString;
		  }
	  
	  public ArrayList<String> getSubStringList(){
		    return this.subStringList;
		  }
	  
	  public void setSubStringList(ArrayList<String> subStringList){
		  this.subStringList= subStringList;
		  }
}
