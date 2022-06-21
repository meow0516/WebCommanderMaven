package idv.service;

import idv.model.UserInfo;

public class UserInfoService {
	private UserInfo userInfo = new UserInfo();
	
	public void setUserInfo(String userName) {
		userInfo.setName(userName);
		userInfo.setLogin(true);
	}
	
	public UserInfo getUserInfo() {
		return this.userInfo;
	}
}
