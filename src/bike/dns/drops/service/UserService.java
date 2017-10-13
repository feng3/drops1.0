package bike.dns.drops.service;

import bike.dns.drops.dao.UserDAO;
import bike.dns.drops.dao.impl.UserDAOImpl;
import bike.dns.drops.entity.User;

public class UserService {
	
	private UserDAO userDAO = new UserDAOImpl();
	
	public User getUser(String username){
		return userDAO.getUser(username);
	}

	public long register(User user){
		return userDAO.register(user);
	}
	
	public void updatePassword(User user) {
		userDAO.updatePassword(user);
	}
	
	public User getUserByEmail(String email){
		return userDAO.getUserByEmail(email);
	}
	
	public void updateLoginInfo(User user){
		userDAO.updateLoginInfo(user);
	}
}
