package bike.dns.drops.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	public static String getProperty(String file, String key) {

		Properties properties = new Properties();
		InputStream inputStreamReader = PropertiesUtil.class.getClassLoader().getResourceAsStream(file);
		try {
			properties.load(inputStreamReader);
		} catch (IOException e) {
			return "";
		}

		return properties.getProperty(key);
	}

}
