package bike.dns.drops.web;

import bike.dns.drops.entity.User;

public class CriteriaJob {

	private User user;
	private int pageNo;
	
	
	public CriteriaJob() {
		super();
	}
	
	public CriteriaJob(User user, int pageNo) {
		super();
		this.user = user;
		this.pageNo = pageNo;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	@Override
	public String toString() {
		return "CriteriaJob [user=" + user + ", pageNo=" + pageNo + "]";
	}
	
}
