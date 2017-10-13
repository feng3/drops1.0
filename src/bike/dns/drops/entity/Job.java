package bike.dns.drops.entity;

//用户提交的抓取文章任务
public class Job {
	//任务id
	private Integer id;
	//标题
	private String title;
	//url
	private String originUrl;
	//drop id
	private int dropId;
	//类别
	private String category;
	//提交者id
	private String submitUserName;
	//提交时间
	private String submitTime;
	//处理时间
	private String dealTime;
	//任务状态 0： 未处理的任务  1： 已经爬取的任务  2： 删除的任务
	private int status;
	//备注
	private String comment;
	
	public Job(){
		
	}

	public Job(String title, String originUrl, int dropId, String category, String submitUserName, String submitTime,
			String dealTime, int status, String comment) {
		super();
		this.title = title;
		this.originUrl = originUrl;
		this.dropId = dropId;
		this.category = category;
		this.submitUserName = submitUserName;
		this.submitTime = submitTime;
		this.dealTime = dealTime;
		this.status = status;
		this.comment = comment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginUrl() {
		return originUrl;
	}

	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}

	public int getDropId() {
		return dropId;
	}

	public void setDropId(int dropId) {
		this.dropId = dropId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubmitUserName() {
		return submitUserName;
	}

	public void setSubmitUserName(String submitUserName) {
		this.submitUserName = submitUserName;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Job [id=" + id + ", title=" + title + ", originUrl=" + originUrl + ", dropId=" + dropId + ", category="
				+ category + ", submitUserName=" + submitUserName + ", submitTime=" + submitTime + ", dealTime="
				+ dealTime + ", status=" + status + ", comment=" + comment + "]";
	}

}
