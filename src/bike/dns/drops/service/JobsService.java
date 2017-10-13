package bike.dns.drops.service;

import bike.dns.drops.dao.impl.JobDAOImpl;
import bike.dns.drops.entity.Job;
import bike.dns.drops.web.CriteriaJob;
import bike.dns.drops.web.Page;

public class JobsService {

	private JobDAOImpl jobDAO = new JobDAOImpl();
	
	public long createJob(Job job){
		return jobDAO.createJob(job);
	}
	
	public Page<Job> getPage(CriteriaJob cj, int userType){
		return jobDAO.getPage(cj, userType);
	}
	
	public Job getSingleJob(int id) {
		return jobDAO.getSingleJob(id);
	}
	
	public void updateJobStatus(Job job){
		jobDAO.updateJobStatus(job);
	}
}
