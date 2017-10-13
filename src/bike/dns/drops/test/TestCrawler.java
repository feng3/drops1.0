package bike.dns.drops.test;

import java.io.File;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import bike.dns.drops.utils.Crawler;

public class TestCrawler {

	@Test
	public void testDownloadImgs() throws Exception {
		HashSet<String> urls = new HashSet<>();
		urls.add("http://image.3001.net/images/20160917/14741158708556.png!small");
		urls.add("http://www.baidu.com/img/baidu_jgylogo3.gif");
		urls.add("http://img.blog.csdn.net/20160608171357716");
		urls.add("http://static.3001.net/css/new/bootstrap.min.css?ver=2016051701");
		Crawler.downloadResources(urls);
	}
	
	@Test
	public void testCommonCraw() throws Exception {
		Crawler.commonCraw("https://xianzhi.aliyun.com/forum/read/401.html", "", "", "", "");
	}
	
	@Test
	public void testBaseUri() throws Exception{
		File input = new File("/Users/river/Desktop/1.html");
		Document doc = Jsoup.parse(input, "UTF-8");
		System.out.println(doc.html());
		Elements css = doc.getElementsByTag("link");
		for (Element element : css) {
			System.out.println(element.attr("abs:href"));
		}
	}

}
