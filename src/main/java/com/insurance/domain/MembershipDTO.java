package com.insurance.domain;

public class MembershipDTO
{
	private Long userIdx;
	private String userId;
	private String userPw;
	private String userEmail;
	private String userName;
	private String userBirth;
	private String userGender;
	private String userPhone;
	private String quitYn;
	
	public Long getUserIdx() {
		return userIdx;
	}
	public void setUserIdx(Long userIdx) {
		this.userIdx = userIdx;
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
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getQuitYn() {
		return quitYn;
	}
	public void setQuitYn(String quitYn) {
		this.quitYn = quitYn;
	}
	
	@Override
	public String toString() {
		return "MembershipDTO [userIdx=" + userIdx + ", userId=" + userId + ", userPw=" + userPw + ", userEmail="
				+ userEmail + ", userName=" + userName + ", userBirth=" + userBirth + ", userGender=" + userGender
				+ ", userPhone=" + userPhone + ", quitYn=" + quitYn + "]";
	}
}
	
	
	
	
	
	
	
	
