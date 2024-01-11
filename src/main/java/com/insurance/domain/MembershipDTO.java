package com.insurance.domain;

public class MembershipDTO
{
	private Long idx;
	private String userId;
	private String userPw;
	private String userEmail;
	private String userName;
	private String userBirth;
	private String userPhone;
	
	public Long getIdx() {
		return idx;
	}

	public void setIdx(Long idx) {
		this.idx = idx;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Override
	public String toString() {
		return "MembershipDTO [idx=" + idx + ", userId=" + userId + ", userPw=" + userPw + ", userEmail=" + userEmail
				+ ", userName=" + userName + ", userBirth=" + userBirth + ", userPhone=" + userPhone + "]";
	}
	
	
}
