package com.moneytransfer.util;


import java.util.HashMap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

/*Class to read DB configs.
*Uses PropertiesConfiguration to ensure file is read only when it is modified
*and not always.
*/
public class ConfigResource {
	
	private static PropertiesConfiguration configuration = null;
	private static ConfigResource resource = null;
	
    private ConfigResource() throws ConfigurationException {
    	
    	configuration = new PropertiesConfiguration("src/main/resources/DBconfig.properties");
		configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
	}
    
    //synchronized to ensure only single instance is created
    public static synchronized ConfigResource getInstance() throws ConfigurationException{
    	if(resource==null)
    		resource = new ConfigResource();
    	return resource;
    }
    
 
  //this block can be leveraged to fetch all properties at once
    public HashMap<String, String> getProperties()
    {
    	HashMap<String, String> configs = new HashMap<String, String>();
    	configs.put("h2_driver",(String) configuration.getProperty("h2_driver"));
    	configs.put("h2_connection_url",(String) configuration.getProperty("h2_connection_url"));
    	configs.put("h2_user",(String) configuration.getProperty("h2_user"));
    	configs.put("h2_password",(String) configuration.getProperty("h2_password"));
    	
    	return configs;
    }

    //get individual property from file
	public String getValueForProperty(String PropertyKey) {
		String value =(String) configuration.getProperty(PropertyKey);
		return value;
	}


}
