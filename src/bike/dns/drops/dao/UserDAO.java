package bike.dns.drops.dao;

import bike.dns.drops.entity.User;

public interface UserDAO {

	/*
	 * 通过用户名获取User对象
	 * @param username
	 * @return User
	 */
	public abstract User getUser(String username);
	
	/*
	 * 通过邮箱获取User对象
	 * @param email
	 * @return User
	 */
	public abstract User getUserByEmail(String email);
	
	/*
	 * 注册用户
	 * @param user
	 * @return long
	 */
	public abstract long register(User user);
	
	/*
	 * 更新用户密码
	 * @param user
	 * @return 
	 */
	public abstract void updatePassword(User user);
	
	/*
	 * 更新用户最后一次登录的地址、时间信息
	 * @param user
	 * @return 
	 */
	public abstract void updateLoginInfo(User user);
	
	
	
	
}
