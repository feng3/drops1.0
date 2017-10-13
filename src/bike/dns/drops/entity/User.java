package bike.dns.drops.entity;

public class User {
	//用户id
	private Integer id;
	//用户名
	private String userName;
	//用户密码hash
	private String password;
	//密码盐值
	private String salt;
	//用户邮箱
	private String email;
	//用户手机
	private String phone;
	//注册时间
	private String createTime;
	//最后一次登录时间
	private String lastLoginTime;
	//注册IP
	private String registerIP;
	//最后一次登录IP
	private String lastLoginIP;
	//是否为管理员
	private int isAdmin;
	
	public User(){
		
	}
	
	public User(String userName, String password, String salt, String email, String phone,
			String createTime, String lastLoginTime, String registerIP, String lastLoginIP, int isAdmin) {
		super();
		this.userName = userName;
		this.password = password;
		this.salt = salt;
		this.email = email;
		this.phone = phone;
		this.createTime = createTime;
		this.lastLoginTime = lastLoginTime;
		this.registerIP = registerIP;
		this.lastLoginIP = lastLoginIP;
		this.isAdmin = isAdmin;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getRegisterIP() {
		return registerIP;
	}

	public void setRegisterIP(String registerIP) {
		this.registerIP = registerIP;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}
	
	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", salt=" + salt + ", email=" + email + ", phone=" + phone + ", createTime=" + createTime + ", lastLoginTime="
				+ lastLoginTime + ", registerIP=" + registerIP + ", lastLoginIP=" + lastLoginIP + ", isAdmin=" + isAdmin
				+ "]";
	}

}
