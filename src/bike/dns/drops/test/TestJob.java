package bike.dns.drops.test;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import bike.dns.drops.dao.impl.JobDAOImpl;
import bike.dns.drops.db.JDBCUtils;
import bike.dns.drops.entity.Job;
import bike.dns.drops.entity.User;
import bike.dns.drops.web.ConnectionContext;
import bike.dns.drops.web.CriteriaJob;

public class TestJob {
	
	private JobDAOImpl jobDAOImpl = new JobDAOImpl();

	@Test
	public void testCreateJob() {
		Connection connection = JDBCUtils.getConnection();
		ConnectionContext.getInstance().bind(connection);
		
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String lastLoginTime = dataFormat.format(new Date());
		
		Job job = new Job("test1", "test11", -1, "category", "test", lastLoginTime, lastLoginTime, 0, "");
		System.out.println(jobDAOImpl.createJob(job));
	}

	@Test
	public void testUpdateJobStatus() {
		Connection connection = JDBCUtils.getConnection();
		ConnectionContext.getInstance().bind(connection);
		
		SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String lastLoginTime = dataFormat.format(new Date());
		
		Job job = new Job("test", "test1", 0, "new category", "test1", lastLoginTime, lastLoginTime, 2, null);
		jobDAOImpl.updateJobStatus(job);
	}
	
	@Test
	public void testGetPage() {
		Connection connection = JDBCUtils.getConnection();
		ConnectionContext.getInstance().bind(connection);
		
		User user = new User();
		user.setId(3);
		
		CriteriaJob cj = new CriteriaJob(user, 1);
		
		System.out.println(jobDAOImpl.getPage(cj, 0));
	}
	
	@Test
	public void testGetPageList() {
		Connection connection = JDBCUtils.getConnection();
		ConnectionContext.getInstance().bind(connection);
		
		User user = new User();
		user.setId(3);
		
		CriteriaJob cj = new CriteriaJob(user, 1);
		
		System.out.println(jobDAOImpl.getPageList(cj, 20, 0));
	}
	
	@Test
	public void testGetTotalNumber() {
		Connection connection = JDBCUtils.getConnection();
		ConnectionContext.getInstance().bind(connection);
		
		User user = new User();
		user.setId(3);
		
		CriteriaJob cj = new CriteriaJob(user, 1);
		
		System.out.println(jobDAOImpl.getTotalNumber(cj, 0));
	}
	
	@Test
	public void testGetSingleJob(){
		Connection connection = JDBCUtils.getConnection();
		ConnectionContext.getInstance().bind(connection);
		System.out.println(jobDAOImpl.getSingleJob(3));
	}
}
