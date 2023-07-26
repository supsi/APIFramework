package stepDefinition.datadriven;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		//execute this code only when place id is null
		//write a code that will give you place id 
		
		stepDefinition sd = new stepDefinition();
		
		if(sd.place_id==null)
		{
		sd.add_place_payload_with("gubutu", "bengali","arambagh");
		sd.user_calls_with_htpps_request("addPlaceAPI","POST");
		sd.verify_place_id_created_maps_to_using("gubutu","getPlaceAPI");
		}
				
	}

}
