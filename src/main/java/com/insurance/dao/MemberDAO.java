package com.insurance.dao;


import com.insurance.vo.MemberVO;

public interface MemberDAO
{
	// 회원가입
	public void register(MemberVO vo) throws Exception;
}

