package fileReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CSVReader {
	public HashMap<String, String> readEntitleAndApp(String input) {
		HashMap<String, String> appAndEntitleDetails = new HashMap<String, String>();
		Reader in;
		try {
			in = new FileReader("Resources/EntitleAndApp.csv");
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			for (CSVRecord record : records) {
				if (record.get("Input").trim().equalsIgnoreCase("input")) {
					appAndEntitleDetails.put("AppName", record.get("Application").trim());
					appAndEntitleDetails.put("Entitlement", record.get("Entitlement").trim());
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return appAndEntitleDetails;

	}

	public HashMap<String, String> readUserDetails(String userType) {
		HashMap<String, String> userDetails = new HashMap<String, String>();
		Reader in;
		try {
			in = new FileReader("Resources/loginDetails.csv");
			Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
			for (CSVRecord record : records) {
				if (record.get("User").trim().equalsIgnoreCase("userType")) {
					userDetails.put("UserID", record.get("UserID").trim());
					userDetails.put("Password", record.get("Password").trim());
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userDetails;

	}
}
