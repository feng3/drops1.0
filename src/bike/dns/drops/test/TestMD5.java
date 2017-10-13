package bike.dns.drops.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import bike.dns.drops.utils.MD5;

public class TestMD5 {

	@Test
	public void test() {
		String password = "123456uofwu1";
		String hash = MD5.getMD5Hash(password);
		System.out.println(hash);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));
		
	}

}
