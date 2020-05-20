package fileReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	
	InputStream file;
	Properties reader = new Properties();

	/*
	 * Constructor to load the file to the reader
	 */
	public ConfigReader() {
		try {
			file = new FileInputStream("Resources\\config.properties");
			reader.load(file);
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
}
