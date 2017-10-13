package bike.dns.drops.web;

public class CriteriaArticle {
	public CriteriaArticle(String category, String originUrl, String author, String content, String title,
			String sourceSite, int pageNo) {
		super();
		this.category = category;
		this.originUrl = originUrl;
		this.author = author;
		this.content = content;
		this.title = title;
		this.sourceSite = sourceSite;
		this.pageNo = pageNo;
	}
	private String category;
	private String originUrl;
	private String author;
	private String content;
	private String title;
	private String sourceSite;
	
	private int pageNo;
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getOriginUrl() {
		return originUrl;
	}
	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSourceSite() {
		return sourceSite;
	}
	public void setSourceSite(String sourceSite) {
		this.sourceSite = sourceSite;
	}
	
}
