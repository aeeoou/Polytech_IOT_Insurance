package com.insurance.service;

import java.util.List;

import com.insurance.domain.MembershipDTO;

//컨트롤러에서 부를 수 있는 서비스가 먼저 있어야한다.
public interface MembershipService
{
	 public boolean registerMembership(MembershipDTO params);
	 // 회원번호로 정보 가져오는 함수
	 public MembershipDTO getMembershipDetail(long idx);
	 // 회원아이디로 정보 가져오는 함수 (추가하기)
	 public MembershipDTO getMembershipByUserId(String userId);
	 
	 public boolean deleteMembership(long idx);
	 public List<MembershipDTO> getMembershipList();
	 
	 // 로그인 함수
	 public boolean login(String userId, String userPw); 
	 // 로그아웃 함수?
	 public void logout();
}