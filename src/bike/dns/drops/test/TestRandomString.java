package bike.dns.drops.test;

import org.junit.Test;

import bike.dns.drops.utils.RandomString;

public class TestRandomString {

	@Test
	public void test() {
		String random = RandomString.getRandomString(6);
		System.out.println(random);
	}

}
