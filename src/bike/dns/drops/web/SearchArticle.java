/**
 * 
 */
package bike.dns.drops.web;
/**
 * @author  作者 E-mail: 
 * @date 创建时间：2016年9月17日 上午8:15:42
 * @version 1.0 
 */

public class SearchArticle {
	private String content;
	@Override
	public String toString() {
		return "SearchArticle [content=" + content + ", pageNo=" + pageNo + "]";
	}
	private int pageNo;
	
	public String getContent() {
		if (content == null) {
			content = "";
		}else {
			content = content.replace("%", "");
			content = "%" + content + "%";
		}
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public SearchArticle(String content, int pageNo) {
		super();
		this.content = content;
		this.pageNo = pageNo;
	}
	
	
}
