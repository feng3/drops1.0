package bike.dns.drops.dao;

import java.util.List;

import bike.dns.drops.entity.Article;
import bike.dns.drops.web.CriteriaArticle;
import bike.dns.drops.web.Page;
import bike.dns.drops.web.SearchArticle;

public interface ArticleDAO {

	/*
	 * 根据id获取指定的Article对象
	 * @param id
	 * @return
	 */
	public abstract Article getArticle(int id);
	
	/*
	 * 根据传入的CriteriaArticle对象返回对应的 Page 对象
	 * @param ca
	 * @return
	 */
	public abstract Page<Article> getPage(CriteriaArticle ca);
	
	/*
	 * 根据传入的CriteriaArticle对象返回对应的记录数
	 * @param ca
	 * @return
	 */
	public abstract long getTotalArticleNumber(CriteriaArticle ca);
	
	/*
	 * 根据传入的CriteriaArticle 和 pageSize 返回当前页对应的List
	 * @param ca
	 * @param pageSize
	 * @return
	 */
	public abstract List<Article> getPageList(CriteriaArticle ca, int pageSize);
	
	/*
	 * 根据传入的SearchArticle对象返回对应的 Page 对象
	 * @param sa
	 * @return
	 */
	public abstract Page<Article> getSearchPage(SearchArticle sa);
	
	/*
	 * 根据传入的SearchArticle对象返回对应的记录数
	 * @param sa
	 * @return
	 */
	public abstract long getSearchTotalArticleNumber(SearchArticle sa);
	
	/*
	 * 根据传入的SearchArticle 和 pageSize 返回当前页对应的List
	 * @param sa
	 * @param pageSize
	 * @return
	 */
	public abstract List<Article> getSearchPageList(SearchArticle sa, int pageSize);
	
	/*
	 * 根据传入的 originUrl 获取记录数
	 * @param originUrl
	 * @return long
	 */
	public abstract long getTotalArticleNumberByOriginUrl(String originUrl);
	
	/*
	 * 根据传入的Article对象创建数据库记录
	 * @param article
	 * @return
	 */
	public abstract long createArticle(Article article);
}
