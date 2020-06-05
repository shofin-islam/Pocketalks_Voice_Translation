import java.util.ArrayList;

public class ModelSubCountryList {

	  private String countryName;
	  private ArrayList<String> countryLanguages;
	  
	  
	  public ModelSubCountryList(String countryName, ArrayList<String> countryLanguages){
		    this.countryName = countryName;
		    this.countryLanguages = countryLanguages;
		  }
	  
	  public String getCountryName(){
		    return this.countryName;
		  }
	  
	  public void setCountryName(String countryName){
		  this.countryName= countryName;
		  }
	  
	  public ArrayList<String> getCountryLanguages(){
		    return this.countryLanguages;
		  }
	  
	  public void setCountryLanguages(ArrayList<String> countryLanguages){
		  this.countryLanguages= countryLanguages;
		  }
}
