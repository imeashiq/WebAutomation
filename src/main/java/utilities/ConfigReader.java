package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	InputStream config, objectrepo;
	Properties reader = new Properties();
	Properties objectProperty = new Properties();
	
	/*
	 * Constructor to load the properties
	 */
	public ConfigReader() {
		try {
			objectrepo = new FileInputStream(System.getProperty("user.dir") + "/Resources/objectRepository.properties");
			objectProperty.load(objectrepo);
			config = new FileInputStream(System.getProperty("user.dir") + "/Resources/config.properties");
			reader.load(config);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getBaseURL() {
		return reader.getProperty("BaseURL");
	}

	public String getUsername() {
		return reader.getProperty("Username");
	}

	public String getPassword() {
		return reader.getProperty("Password");
	}
	
	public String getObjectProperty(String propertyName) {
		return objectProperty.getProperty(propertyName);
	}
}
