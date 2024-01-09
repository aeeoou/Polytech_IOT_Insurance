package com.insurance.dao;


import com.insurance.vo.MembershipVO;

public interface MembershipDAO
{
	// 회원가입
	public void register(MembershipVO vo) throws Exception;
}

