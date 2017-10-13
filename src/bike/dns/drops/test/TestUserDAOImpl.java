/**
 * 
 */
package bike.dns.drops.test;

import java.sql.Connection;

import org.junit.Test;

import bike.dns.drops.dao.impl.UserDAOImpl;
import bike.dns.drops.db.JDBCUtils;
import bike.dns.drops.entity.User;
import bike.dns.drops.web.ConnectionContext;

/**
 * @author  作者 E-mail: 
 * @date 创建时间：2016年9月16日 下午6:45:26
 * @version 1.0 
 */
/**
 * @author river
 *
 */
public class TestUserDAOImpl {

	@Test
	public void testGetUser() {
		Connection connection = JDBCUtils.getConnection();
		ConnectionContext.getInstance().bind(connection);
		UserDAOImpl userDAOImpl = new UserDAOImpl();
		User user = userDAOImpl.getUser("admin");
		System.out.println(user);
	}

}
