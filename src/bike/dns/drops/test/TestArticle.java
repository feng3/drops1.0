package bike.dns.drops.test;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import bike.dns.drops.dao.impl.ArticleDAOImpl;
import bike.dns.drops.db.JDBCUtils;
import bike.dns.drops.entity.Article;
import bike.dns.drops.web.ConnectionContext;
import bike.dns.drops.web.CriteriaArticle;
import bike.dns.drops.web.SearchArticle;

public class TestArticle {
	ArticleDAOImpl articleDAOImpl = new ArticleDAOImpl();

	@Test
	public void testGetArticle() {
		Connection connection = JDBCUtils.getConnection();
		ConnectionContext.getInstance().bind(connection);
		Article article = articleDAOImpl.getArticle(1);
		System.out.println(article);
	}

	@Test
	public void testGetTotalArticleNumber() {
		Connection connection = JDBCUtils.getConnection();
		ConnectionContext.getInstance().bind(connection);
		CriteriaArticle criteriaArticle = new CriteriaArticle("", null, null, null, null, "", 1);
		long num = articleDAOImpl.getTotalArticleNumber(criteriaArticle);
		System.out.println(num);
	}

	@Test
	public void testGetPageList() {
		Connection connection = JDBCUtils.getConnection();
		ConnectionContext.getInstance().bind(connection);
		CriteriaArticle criteriaArticle = new CriteriaArticle("", null, null, null, null, "", 1);
		List<Article> articles = articleDAOImpl.getPageList(criteriaArticle, 10);
		System.out.println(articles);
	}

	@Test
	public void testSearchGetTotalArticleNumber() {
		Connection connection = JDBCUtils.getConnection();
		ConnectionContext.getInstance().bind(connection);
		SearchArticle searchArticle = new SearchArticle("aaa", 1);
		long num = articleDAOImpl.getSearchTotalArticleNumber(searchArticle);
		System.out.println(num);
	}

	@Test
	public void testGetSearchPageList() {
		Connection connection = JDBCUtils.getConnection();
		ConnectionContext.getInstance().bind(connection);
		SearchArticle searchArticle = new SearchArticle("aaa", 1);
		List<Article> articles = articleDAOImpl.getSearchPageList(searchArticle, 10);
		System.out.println(articles);
		String aaa = "%aaa%";
		System.out.println(aaa.replace("%", ""));
	}

	@Test
	public void testCreateArticle() {
		Connection connection = JDBCUtils.getConnection();
		ConnectionContext.getInstance().bind(connection);

		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String lastLoginTime = dataFormat.format(new Date());

		Article article = new Article("test", "test", "test", "test", "test", "test", lastLoginTime);
		System.out.println(articleDAOImpl.createArticle(article));
	}

}
