package bike.dns.drops.dao;

import java.util.List;

import bike.dns.drops.entity.Job;
import bike.dns.drops.web.CriteriaJob;
import bike.dns.drops.web.Page;

public interface JobDAO {
	
	/*
	 * 创建爬取文章的任务
	 * @param job 任务
	 * @return long
	 */
	public abstract long createJob(Job job);
	
	/*
	 * 获取 job 详情
	 * @param id  job id
	 * @return job
	 */
	public abstract Job getSingleJob(int id);
	
	/*
	 * 根据job中的url更新任务状态
	 * @param job
	 * @return
	 */
	public abstract void updateJobStatus(Job job);
	
	/*
	 * 根据传入的 CriteriaJob 对象返回对应的 Page 对象
	 * @param CriteriaJob
	 * @param userType 0:普通用户  1:管理员
	 * @return 
	 */
	public abstract Page<Job> getPage(CriteriaJob cj, int userType);
	
	/*
	 * 根据传入的 CriteriaJob 对象，返回总共的记录数
	 * @param CriteriaJob
	 * @param userType 0:普通用户  1:管理员
	 * @return
	 */
	public abstract long getTotalNumber(CriteriaJob cj, int userType); 
	
	/*
	 * 根据传入的 CriteriaJob 和 pageSize 返回当前页对应的list
	 * @param CriteriaJob
	 * @param int
	 * @param userType 0:普通用户  1:管理员
	 * @return
	 */
	public abstract List<Job> getPageList(CriteriaJob cj, int pageSize, int userType);
}
