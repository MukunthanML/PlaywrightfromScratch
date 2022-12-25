package utills;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtills {

	public Properties getProperties(String propFilePath) throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(propFilePath);
		Properties prop = new Properties();
		prop.load(fis);
		return prop;
	}

	public Properties getProperties(Properties existingProp, String newPropFilePath)
			throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(newPropFilePath);

		existingProp.load(fis);
		return existingProp;
	}

}
