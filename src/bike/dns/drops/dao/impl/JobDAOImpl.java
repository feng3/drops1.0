package bike.dns.drops.dao.impl;

import java.util.List;

import bike.dns.drops.dao.JobDAO;
import bike.dns.drops.entity.Job;
import bike.dns.drops.web.CriteriaJob;
import bike.dns.drops.web.Page;

public class JobDAOImpl extends BaseDAO<Job> implements JobDAO {

	@Override
	public long createJob(Job job) {
		String sql = "INSERT INTO `job`(`title`, `originUrl`, `dropId`, `category`, `submitUserName`, `submitTime`, `dealTime`, `status`, `comment`)"
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return insert(sql, job.getTitle(), job.getOriginUrl(),job.getDropId(), job.getCategory(), job.getSubmitUserName(), job.getSubmitTime(), job.getDealTime(), job.getStatus(), job.getComment());
	}

	@Override
	public void updateJobStatus(Job job) {
		String sql = "UPDATE job SET dealTime = ?, status = ? WHERE originUrl = ?";
		update(sql, job.getDealTime(), job.getStatus(), job.getOriginUrl());
	}

	@Override
	public Page<Job> getPage(CriteriaJob cj, int userType) {
		Page<Job> page = new Page<>(cj.getPageNo());
		page.setTotalItemNumber(getTotalNumber(cj, userType));
		cj.setPageNo(page.getPageNo());
		page.setList(getPageList(cj, page.getPageSize(), userType));
		return page;
	}

	@Override
	public long getTotalNumber(CriteriaJob cj, int userType) {
		String sql = "SELECT count(*) FROM job";
		if (userType == 0) {
			sql = sql + " WHERE submitUserName = ?";
			String submitUserName = cj.getUser().getUserName();
			return getSingleVal(sql, submitUserName);
		}
		return getSingleVal(sql);
	}

	@Override
	public List<Job> getPageList(CriteriaJob cj, int pageSize, int userType) {
		String sql = "SELECT `id`, `title`, `originUrl`, `dropId`, `category`, `submitUserName`, `submitTime`, `dealTime`, `status`, `comment` FROM job";
		int start = (cj.getPageNo() - 1) * pageSize;
		if (userType == 0) {
			sql = sql + " WHERE submitUserName = ? ORDER BY submitTime DESC LIMIT ?, ?";
			String submitUserName = cj.getUser().getUserName();
			return queryForList(sql, submitUserName, start, pageSize);
		}else{
			sql = sql + " ORDER BY submitTime DESC LIMIT ?, ?";
			return queryForList(sql, start, pageSize);
		}
	}

	@Override
	public Job getSingleJob(int id) {
		String sql = "SELECT `id`, `title`, `originUrl`, `dropId`, `category`, `submitUserName`, `submitTime`, `dealTime`, `status`, `comment` FROM job WHERE id = ?";
		return query(sql, id);
	}

}
