package resources.datadriven;

import java.util.ArrayList;
import java.util.List;

import pojo.datadriven.GetLocation;
import pojo.datadriven.location;

public class TestDataBuild {

	
	public GetLocation AddPlaceData(String name,String language,String address)
	{
		GetLocation p =new GetLocation();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setLanguage(language);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName(name);
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		p.setTypes(myList);
		location l =new location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}
	
	
	public String DeletePlacePayload (String placeId)
	{
		return("{\r\n    \"place_id\":\""+placeId+"\"\r\n}");
	}
}
