package bike.dns.drops.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bike.dns.drops.entity.Article;

public class Crawler {
	//图片资源下载的根目录
	private static String BASEDIR = PropertiesUtil.getProperty("config.properties", "resourcePath");
	private static String USERAGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/44.0.2403.89 Safari/537.36";
	//css等资源替换的静态域
	private static String staticDomain = PropertiesUtil.getProperty("config.properties", "staticDomain");
	
	public static Article commonCraw(String pageURL, String category, String title, String author, String createTime) throws Exception{
		try {
			Document doc = Jsoup.connect(pageURL)
					.userAgent(USERAGENT)
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
					.header("Accept-Encoding", "gzip, deflate, sdch")
					.header("Accept-Language", "zh-CN,zh;q=0.8")
					.header("Referer", pageURL)
					.header("Cache-Control", "no-cache")
					.header("Connection", "keep-alive")
					.header("Pragma", "no-cache")
					.get();
			URL url = new URL(pageURL);
			String sourceSite = url.getHost();
			//设置 baseuri
			String baseUri = url.getProtocol() + "://" + url.getHost() + "/";
			doc.setBaseUri(baseUri);
			String content = doc.html();
			//处理文章中的图片资源， 下载到本地并替换源代码中的链接
			HashSet<String> absImgUrls = new HashSet<>();
			Elements imgs = doc.getElementsByTag("img");
			for (Element element : imgs) {
				String src = element.attr("src");
				String absSrc = element.attr("abs:src");
				if (absSrc != null && !absSrc.equals("")) {
					if (absImgUrls.contains(absSrc)) {
					}else {
						absImgUrls.add(absSrc);
						//将 content 中的 url 替换为绝对路径
						content = content.replace(src, absSrc);
					}
				}
			}
			downloadResources(absImgUrls);
			content = replaceResourceUrls(content, absImgUrls, staticDomain);
			//处理文章中的 css 资源， 下载到本地并替换源代码中的链接
			HashSet<String> absCssUrls = new HashSet<>();
			Elements csses = doc.getElementsByTag("link");
			for (Element element : csses) {
				String linkUrl = element.attr("href");
				String absLinkUrl = element.attr("abs:href");
				URL absLink = new URL(absLinkUrl);
				String path = absLink.getPath();
				if (path.endsWith(".css")) {
					if (absLinkUrl != null && !absLinkUrl.equals("")) {
						if (!absCssUrls.contains(absLinkUrl)) {
							absCssUrls.add(absLinkUrl);
							//将 content 中的 url 替换为绝对路径
							content = content.replace(linkUrl, absLinkUrl);
						}
					}
				}
				
//				if (element.attr("rel").equals("stylesheet")) {
//					String absCssUrl = element.attr("abs:href");
//					String cssUrl = element.attr("href");
//					if (absCssUrl != null && !absCssUrl.equals("")) {
//						if (absCssUrls.contains(absCssUrl)) {
//						}else {
//							absCssUrls.add(absCssUrl);
//							//将 content 中的 url 替换为绝对路径
//							content = content.replace(cssUrl, absCssUrl);
//						}
//					}
//				}
			}
			downloadResources(absCssUrls);
			//爬取 https 网站时候，将资源链接改为 http
			content = replaceResourceUrls(content, absCssUrls, staticDomain);
//			content = content.replace("https://static.ziliudi.wiki", "http://static.ziliudi.wiki");
//			System.out.println(content);
			
			return new Article(category, pageURL, author, content, title, sourceSite, createTime);
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	 * 下载图片， 规则为按照 url 的路径进行下载，以 url 的 host 为顶级文件夹
	 */
	public static void downloadResources(HashSet<String> urls) {
		for (String item : urls) {
			try {
				URL url = new URL(item);
				String[] temp = url.getPath().split("/");
				String fileName = temp[temp.length -1];
				String filePath = "";
				for (int i = 0; i < temp.length-1; i++) {
					filePath += temp[i] + "/";
				}
				String path = BASEDIR + url.getHost() + "/" + filePath;
				downloadResource(item, fileName, path);
			} catch (Exception e) {
				System.out.println("download fail: " + item);
			}
		}
	}


	/*
	 * 爬取 freebuf 的文章，并保存图片、替换 css、img 链接为本地链接
	 * @param String 要爬取的文章url
	 * @param String 文章分类
	 * @return article 返回 article 对象
	 */
	public static Article crawlFreebuf(String pageURL, String category) {
		
		try {
			Document doc = Jsoup.connect(pageURL).userAgent(USERAGENT).get();
			String content = doc.html();
			
			// 下载图片  http://image.3001.net/images/20160917/14741158708556.png!small
			HashSet<String> imgUrls = new HashSet<>();
			Elements imgs = doc.select("img[data-original]");
			for (Element img : imgs) {
				String dataOriginal = img.attr("data-original");
				if (dataOriginal.startsWith("http://image.3001.net/")) {
					String[] temp = dataOriginal.split("!");
					String imgUrl = temp[0];
					imgUrls.add(imgUrl);
					temp = imgUrl.split("/");
					String savaPath = "freebuf/" + temp[3] + "/" + temp[4];
					String fileName = temp[temp.length-1];
					downloadResource(imgUrl, fileName, BASEDIR + savaPath );
				}
			}
			content = replaceResourceUrls(content, imgUrls, staticDomain + "freebuf");

			Elements link = doc.getElementsByTag("link");
			HashSet<String> cssUrls = new HashSet<>();
			for (Element element : link) {
				String href = element.attr("href");
				cssUrls.add(href);
			}
			content = replaceCssUrls(content, cssUrls, staticDomain + "freebuf/");
			
			String title = doc.select("div.articlecontent > div.title > h2").text().trim();
			String author = doc.select("div.articlecontent > div.title > div > span.name > a").text().trim();
			String createTime = doc.select("div.articlecontent > div.title > div > span.time").text().trim();
			String sourceSite = "FREEBUF";
//			System.out.println(title);
//			System.out.println(author);
//			System.out.println(createTime);
//			System.out.println(sourceSite);
//			System.out.println(content);
			Article article = new Article(category, pageURL, author, content, title, sourceSite, createTime);
			return article;
		} catch (Exception e) {
			return null;
		}

	}
	
	/*
	 * 爬取csdn的文章，并保存图片
	 * @param String 要爬取的文章url
	 * @return boolean 返回是否爬取成功
	 */
	public static boolean crawlCSDN(String pageURL) {
		
		try {
			Document doc = Jsoup.connect(pageURL)
					.userAgent(USERAGENT)
					.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
					.header("Accept-Encoding", "gzip, deflate, sdch")
					.header("Accept-Language", "zh-CN,zh;q=0.8")
					.header("Referer", "http://blog.csdn.net/column/details/jsoup.html")
					.header("Cache-Control", "no-cache")
					.header("Connection", "keep-alive")
					.header("Pragma", "no-cache")
					.get();
			
			// 下载图片 http://img.blog.csdn.net/20160608171357716
			Elements imgs = doc.select("img");
			for (Element img : imgs) {
				String src = img.attr("src");
				if (src.startsWith("http://img.blog.csdn.net")) {
					String[] temp = src.split("/");
					String savaPath = "csdn/";
					//自己添加了后缀
					String fileName = temp[temp.length-1] + ".jpg";
					downloadResource(src, fileName, BASEDIR + savaPath );
				}
			}
			
			String title = doc.select("#article_details > div.article_title > h1 > span > a").text().trim();
			String author = doc.select("#blog_title > h2 > a").text().trim();
			String createTime = doc.select("#article_details > div.article_manage.clearfix > div.article_r > span.link_postdate").text().trim();
			String sourceSite = "CSDN";
			String content = doc.html();
			System.out.println(title);
			System.out.println(author);
			System.out.println(createTime);
			System.out.println(sourceSite);
			System.out.println(content);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	/*
	 * 爬取 www.n0tr00t.com 的文章，并保存图片
	 * @param String 要爬取的文章url
	 * @return boolean 返回是否爬取成功
	 */
	public static boolean crawlN0tr00t(String pageURL) {
		
		try {
			Document doc = Jsoup.connect(pageURL).userAgent(USERAGENT).get();
			
			// 下载图片 http://ww1.sinaimg.cn/large/c334041bjw1f7lbc6ifm6j20il06qmzx.jpg
			Elements imgs = doc.select("img");
			for (Element img : imgs) {
				String src = img.attr("src");
				if (src.contains("sinaimg.cn")) {
					String[] temp = src.split("/");
					String savaPath = "n0tr00t/" + temp[3];
					String fileName = temp[temp.length-1];
					downloadResource(src, fileName, BASEDIR + savaPath );
				}
			}
			
			String title = doc.select("title").text();
			String info = doc.select("body > div > div.container > div:nth-child(3) > pre > code").text();
			info = info.replace("\r", "").replace("\n", "");
			String[] infos = info.split("\\[\\+\\]");
			String author = infos[1].replace("Author: ", "").trim();
			String createTime = infos[4].replace("Create: ", "").trim();
			String sourceSite = "n0tr00t";
			String content = doc.html();
			System.out.println(title);
			System.out.println(author);
			System.out.println(createTime);
			System.out.println(sourceSite);
			System.out.println(content);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/*
	 * 爬取 http://www.wjdiankong.cn/ 的文章，并保存图片
	 * @param String 要爬取的文章url
	 * @return boolean 返回是否爬取成功
	 */
	public static boolean crawlZhaosi(String pageURL) {
		
		try {
			Document doc = Jsoup.connect(pageURL).userAgent(USERAGENT).get();
			
			// 下载图片 http://img.blog.csdn.net/20160915131207370?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQv/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center
			Elements imgs = doc.select("img");
			for (Element img : imgs) {
				String src = img.attr("src");
				if (src.startsWith("http://img.blog.csdn.net")) {
					String[] temp = src.split("\\?");
					String imgUrl = temp[0];
					temp = imgUrl.split("/");
					String savaPath = "wjdiankong/";
					//自己添加了后缀
					String fileName = temp[temp.length-1] + ".jpg";
					downloadResource(src, fileName, BASEDIR + savaPath );
				}
			}
			
			String title = doc.select("body > section > div.content-wrap > div > header > h1 > a").text().trim();
			String author = "jiangwei212";
			String createTime = doc.select("body > section > div.content-wrap > div > header > div > time").text().trim();
			String sourceSite = "编码美丽";
			String content = doc.html();
			System.out.println(title);
			System.out.println(author);
			System.out.println(createTime);
			System.out.println(sourceSite);
			System.out.println(content);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/*
	 * 下载资源功能， 主要是 图片和css
	 * @param String 资源url
	 * @param String 保存的文件名
	 * @param String 保存的路径
	 */
	public static void downloadResource(String urlString, String filename, String savePath) throws Exception {
		// 构造URL
		URL url = new URL(urlString);
		// 打开连接
		URLConnection con = url.openConnection();
		// 设置请求超时为5s
		con.setConnectTimeout(5 * 1000);
		//设置User-Agent
		con.setRequestProperty("User-Agent", USERAGENT);
		con.setRequestProperty("Referer", urlString);
//		con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		// 输入流
		InputStream is = con.getInputStream();

		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf = new File(savePath);
		if (!sf.exists()) {
			sf.mkdirs();
		}
		OutputStream os = new FileOutputStream(sf.getPath() + "/" + filename);
		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		// 完毕，关闭所有链接
		os.close();
		is.close();
		System.out.println("downloading:  " + urlString);
	}

	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
		}
		encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		return "";
	}

	public static void main(String[] args) throws Exception {
		
		// String url = "http://www.jb51.net/article/43485.htm";
		// System.out.println(getHTML(url, "utf-8"));
//		crawlFreebuf("http://www.freebuf.com/articles/terminal/114910.html", "");
//		crawlN0tr00t("http://www.n0tr00t.com/2016/09/07/Baidu-XSS-Tricks.html");
//		crawlCSDN("http://blog.csdn.net/disiwei1012/article/details/51614492");
//		crawlZhaosi("http://www.wjdiankong.cn/android%E7%B3%BB%E7%BB%9F%E7%AF%87%E4%B9%8B-%E8%A7%A3%E8%AF%BBams%E8%BF%9C%E7%AB%AF%E6%9C%8D%E5%8A%A1%E8%B0%83%E7%94%A8%E6%9C%BA%E5%88%B6%E4%BB%A5%E5%8F%8Aactivity%E7%9A%84%E5%90%AF%E5%8A%A8/");
	
//		replaceDomain();
		
//		downloadResource("http://www.freebuf.com/buf/plugins/wp-favorite-posts/wpfp.css", "wpfp.css", PropertiesUtil.getProperty("config.properties", "imgPath") + "freebuf/");
	
		URL url = new URL("/jslib/syntaxhighlighter/styles/shCore.css");
		System.out.println(url.getHost());
		
	}

	/*
	 * 将网页中的 css 链接替换为本站链接
	 * @param content 网页源代码
	 * @param cssUrls 需要替换的 css url 列表
	 * @param domain 要替换成的本地域名
	 * @return 返回替换后的 html 源代码
	 */
	private static String replaceCssUrls(String content, HashSet<String> cssUrls, String domain){
		for (String cssUrl : cssUrls) {
			String originUrl = cssUrl;
			String[] result = originUrl.split("/");
			String newUrl = "http://" + domain + result[result.length-1];
			content = content.replace(originUrl, newUrl);
		}
		return content;
	}
	
	/*
	 * 将网页中的 img 链接替换为本站链接
	 * @param content 网页源代码
	 * @param imgUrls 需要替换的 url 列表
	 * @param newDomain 要替换成的本地域名
	 * @return 返回替换后的 html 源代码
	 */
	private static String replaceResourceUrls(String content, HashSet<String> urls, String domain){
		for (String item : urls) {
//			System.out.println(imgUrl);
			try {
				URL url = new URL(item);
				String originHost = url.getHost();
				String originPath = url.getPath();
				//www.baidu.com 替换为 static.ziliudi.wiki/www.baidu.com
//				String newUrl = url.getProtocol() + "://" + domain + "/" + originHost + originPath;
				String newUrl = "http" + "://" + domain + "/" + originHost + originPath;
				content = content.replace(item, newUrl);
			} catch (Exception e) {
				System.out.println("replace fail: " + item);
			}
		}
		return content;
	}
	

}
