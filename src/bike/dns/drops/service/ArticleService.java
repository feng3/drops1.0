package bike.dns.drops.service;

import bike.dns.drops.dao.ArticleDAO;
import bike.dns.drops.dao.impl.ArticleDAOImpl;
import bike.dns.drops.entity.Article;
import bike.dns.drops.web.CriteriaArticle;
import bike.dns.drops.web.Page;
import bike.dns.drops.web.SearchArticle;

public class ArticleService {

	private ArticleDAO articleDAO = new ArticleDAOImpl();

	public Article getArticle(int id) {

		return articleDAO.getArticle(id);
	}

	public Page<Article> getPage(CriteriaArticle ca) {

		return articleDAO.getPage(ca);

	}
	
	public Page<Article> getSearchPage(SearchArticle sa) {
		return articleDAO.getSearchPage(sa);
	}
	
	public long createArticle(Article article){
		return articleDAO.createArticle(article);
	}

	public long getTotalArticleNumberByOriginUrl(String originUrl){
		return articleDAO.getTotalArticleNumberByOriginUrl(originUrl);
	}
}
