package bike.dns.drops.dao.impl;

import java.util.List;

import bike.dns.drops.dao.ArticleDAO;
import bike.dns.drops.entity.Article;
import bike.dns.drops.web.CriteriaArticle;
import bike.dns.drops.web.Page;
import bike.dns.drops.web.SearchArticle;

public class ArticleDAOImpl extends BaseDAO<Article> implements ArticleDAO{

	@Override
	public Article getArticle(int id) {
			String sql = "SELECT id, category, originUrl, author, content, title, sourceSite, createTime FROM drops_simple WHERE id = ?";
			return query(sql, id);
	}

	@Override
	public Page<Article> getPage(CriteriaArticle ca) {
		Page<Article> page = new Page<>(ca.getPageNo());
		page.setTotalItemNumber(getTotalArticleNumber(ca));
		ca.setPageNo(page.getPageNo());
		page.setList(getPageList(ca, page.getPageSize()));
		return page;
	}

	//根据category、author、sourcesite三个进行过滤查询
	@Override
	public long getTotalArticleNumber(CriteriaArticle ca) {
		String sql = "SELECT count(*) FROM drops_simple WHERE ";
		if (ca.getCategory() != null & !"".equals(ca.getCategory())) {
			if (ca.getAuthor() != null & !"".equals(ca.getAuthor())) {
				if (ca.getSourceSite() != null & !"".equals(ca.getSourceSite())) {
					sql = sql + "category = ? AND author = ? AND sourceSite = ?";
					return getSingleVal(sql, ca.getCategory(), ca.getAuthor(), ca.getSourceSite());
				}else {
					sql = sql + "category = ? AND author = ?";
					return getSingleVal(sql, ca.getCategory(), ca.getAuthor());
				}
			}else {
				if (ca.getSourceSite() != null & !"".equals(ca.getSourceSite())) {
					sql = sql + "category = ? AND sourceSite = ?";
					return getSingleVal(sql, ca.getCategory(), ca.getSourceSite());
				}else {
					sql = sql + "category = ?";
					return getSingleVal(sql, ca.getCategory());
				}
			}
		}else {
			if (ca.getAuthor() != null & !"".equals(ca.getAuthor())) {
				if (ca.getSourceSite() != null & !"".equals(ca.getSourceSite())) {
					sql = sql + "author = ? AND sourceSite = ?";
					return getSingleVal(sql, ca.getAuthor(), ca.getSourceSite());
				}else {
					sql = sql + "author = ?";
					return getSingleVal(sql, ca.getAuthor());
				}
			}else {
				if (ca.getSourceSite() != null & !"".equals(ca.getSourceSite())) {
					sql = sql + "sourceSite = ?";
					return getSingleVal(sql, ca.getSourceSite());
				}else {
					sql = sql + "1=1";
					return getSingleVal(sql);
				}
			}
			
		}
		
	}

	//根据category、author、sourcesite三个进行过滤查询
	@Override
	public List<Article> getPageList(CriteriaArticle ca, int pageSize) {
		String sql = "SELECT id, category, originUrl, author, title, sourceSite, createTime FROM drops_simple WHERE ";
		int start = (ca.getPageNo() - 1) * pageSize;
		
		if (ca.getCategory() != null & !"".equals(ca.getCategory())) {
			if (ca.getAuthor() != null & !"".equals(ca.getAuthor())) {
				if (ca.getSourceSite() != null & !"".equals(ca.getSourceSite())) {
					sql = sql + "category = ? AND author = ? AND sourceSite = ? ORDER BY createTime DESC LIMIT ?, ?";
					return queryForList(sql, ca.getCategory(), ca.getAuthor(), ca.getSourceSite(), start, pageSize);
				}else {
					sql = sql + "category = ? AND author = ? ORDER BY createTime DESC LIMIT ?, ?";
					return queryForList(sql, ca.getCategory(), ca.getAuthor(), start, pageSize);
				}
			}else {
				if (ca.getSourceSite() != null & !"".equals(ca.getSourceSite())) {
					sql = sql + "category = ? AND sourceSite = ? ORDER BY createTime DESC LIMIT ?, ?";
					return queryForList(sql, ca.getCategory(), ca.getSourceSite(), start, pageSize);
				}else {
					sql = sql + "category = ? ORDER BY createTime DESC LIMIT ?, ?";
					return queryForList(sql, ca.getCategory(), start, pageSize);
				}
			}
		}else {
			if (ca.getAuthor() != null & !"".equals(ca.getAuthor())) {
				if (ca.getSourceSite() != null & !"".equals(ca.getSourceSite())) {
					sql = sql + "author = ? AND sourceSite = ? ORDER BY createTime DESC LIMIT ?, ?";
					return queryForList(sql, ca.getAuthor(), ca.getSourceSite(), start, pageSize);
				}else {
					sql = sql + "author = ? ORDER BY createTime DESC LIMIT ?, ?";
					return queryForList(sql, ca.getAuthor(), start, pageSize);
				}
			}else {
				if (ca.getSourceSite() != null & !"".equals(ca.getSourceSite())) {
					sql = sql + "sourceSite = ? ORDER BY createTime DESC LIMIT ?, ?";
					return queryForList(sql, ca.getSourceSite(), start, pageSize);
				}else {
					sql = sql + "1 = 1 ORDER BY createTime DESC LIMIT ?, ?";
					return queryForList(sql, start, pageSize);
				}
			}
			
		}
	}

	@Override
	public Page<Article> getSearchPage(SearchArticle sa) {
		Page<Article> page = new Page<>(sa.getPageNo());
		page.setTotalItemNumber(getSearchTotalArticleNumber(sa));
		sa.setPageNo(page.getPageNo());
		page.setList(getSearchPageList(sa, 20));
		return page;
	}

	@Override
	public long getSearchTotalArticleNumber(SearchArticle sa) {
		String sql = "SELECT count(*) FROM drops_simple WHERE content LIKE ?";
		return getSingleVal(sql, sa.getContent());
	}

	@Override
	public List<Article> getSearchPageList(SearchArticle sa, int pageSize) {
		String sql = "SELECT id, category, originUrl, author, title, sourceSite, createTime FROM drops_simple WHERE content LIKE ? LIMIT ?, ?";
		int start = (sa.getPageNo() - 1) * pageSize;
		return queryForList(sql, sa.getContent(), start, pageSize);
	}

	@Override
	public long createArticle(Article article) {
		String sql = "INSERT INTO `drops_simple`(`category`, `originUrl`, `author`, `content`, `title`, `sourceSite`, `createTime`)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?)";
		return insert(sql, article.getCategory(), article.getOriginUrl(), article.getAuthor(), article.getContent(), article.getTitle(), article.getSourceSite(), article.getCreateTime());
	}

	@Override
	public long getTotalArticleNumberByOriginUrl(String originUrl) {
		String sql = "SELECT count(*) FROM drops_simple WHERE originUrl = ?";
		return getSingleVal(sql, originUrl);
	}

}
