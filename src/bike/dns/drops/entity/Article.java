package bike.dns.drops.entity;

public class Article {
	//文章id
	private Integer id;
	//类别
	private String category;
	//原始链接
	private String originUrl;
	//作者
	private String author;
	//文章内容
	private String content;
	//文章标题
	private String title;
	//来源站点
	private String sourceSite;
	//文章编写时间
	private String createTime;
	
	public Article(){
		
	}
	
	public Article(String category, String originUrl, String author, String content, String title, String sourceSite,
			String createTime) {
		super();
		this.category = category;
		this.originUrl = originUrl;
		this.author = author;
		this.content = content;
		this.title = title;
		this.sourceSite = sourceSite;
		this.createTime = createTime;
	}

	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "Article [id=" + id + ", category=" + category + ", originUrl=" + originUrl + ", author=" + author
				+ ", content=" + content + ", title=" + title + ", sourceSite=" + sourceSite + ", createTime="
				+ createTime + "]";
	}
	

}
