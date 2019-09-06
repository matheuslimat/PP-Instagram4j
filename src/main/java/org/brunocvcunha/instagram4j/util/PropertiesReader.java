package org.brunocvcunha.instagram4j.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

	public Properties getProperties(String filename) throws IOException{
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data.properties");
		
		Properties prop = new Properties();
		prop.load(inputStream);
		
		return prop;
	}
}
