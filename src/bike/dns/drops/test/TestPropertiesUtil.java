package bike.dns.drops.test;

import org.junit.Test;

import bike.dns.drops.utils.PropertiesUtil;

public class TestPropertiesUtil {

	@Test
	public void testGetProperty(){
		String file = "config.properties";
		String key = "imgPath";
		
		System.out.println(PropertiesUtil.getProperty(file, key));
	}
}
