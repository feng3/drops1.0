/**
 * 
 */
package bike.dns.drops.dao.impl;

import bike.dns.drops.dao.UserDAO;
import bike.dns.drops.entity.User;

/**
 * @author 作者 E-mail:
 * @date 创建时间：2016年9月16日 下午6:25:49
 * @version 1.0
 */

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

	@Override
	public User getUser(String username) {
		String sql = "SELECT id, username, password, salt, email, phone, createTime, lastLoginTime, registerIP, lastLoginIP, isAdmin"
				+ " from user WHERE username = ?";
		return query(sql, username);
	}

	@Override
	public long register(User user) {
		String sql = "INSERT INTO user (username, password, salt, email, phone, createTime, lastLoginTime, registerIP, lastLoginIP) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return insert(sql, user.getUserName(), user.getPassword(), user.getSalt(), user.getEmail(),
				user.getPhone(), user.getCreateTime(), user.getLastLoginTime(), user.getRegisterIP(),
				user.getLastLoginIP());
	}

	@Override
	public void updatePassword(User user) {
		String sql = "UPDATE user SET password = ?, salt = ? WHERE email = ?";
		update(sql, user.getPassword(), user.getSalt(), user.getEmail());
	}

	@Override
	public User getUserByEmail(String email) {
		String sql = "SELECT id, username, password, salt, email, phone, createTime, lastLoginTime, registerIP, lastLoginIP"
				+ " from user WHERE email = ?";
		return query(sql, email);
	}

	@Override
	public void updateLoginInfo(User user) {
		String sql = "UPDATE user SET lastLoginIP = ?, lastLoginTime = ? WHERE username = ?";
		update(sql, user.getLastLoginIP(), user.getLastLoginTime(), user.getUserName());
	}

}
