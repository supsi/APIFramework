package resources.datadriven;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.PrintStream;
import java.util.Properties;
import io.restassured.path.json.JsonPath;

public class Utils {
	
	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException
	{
		if(req==null)
		{
		PrintStream log= new PrintStream(new FileOutputStream("loggingnew.txt"));
		req =new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURl")).addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log)).
				setContentType(ContentType.JSON).build();
		
		return req;
		}
		return req;
	}
	
	
	public String getGlobalValue(String key) throws IOException
	{
		Properties prop = new Properties();
	    FileInputStream fis =new FileInputStream("C:\\Users\\supra\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\datadriven\\global.properties");
	    prop.load(fis);
	    return prop.getProperty(key);
	}

	
	public static String getJsonPath(Response response, String key )
	{
		
		String resp=response.asString();
		JsonPath js=new JsonPath(resp);
		return js.get(key).toString();
		
	}

}
