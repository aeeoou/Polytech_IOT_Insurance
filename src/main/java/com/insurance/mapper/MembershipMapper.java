package com.insurance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.insurance.domain.MembershipDTO;

// @Mapper :: 데이터베이스와 통신하는 인터페이스임을 선언
@Mapper
public interface MembershipMapper             
{
	public int insertMembership(MembershipDTO params);
	public MembershipDTO selectMembershipDetail(long idx);
	public int updateMembership(MembershipDTO params);
	public int deleteMembership(long idx);
	public List<MembershipDTO> selectMembershipList();
	public int selectMembershipTotalCount();
	// 괄호 안 놈들 매개변수 (인자) - 로그인을 위한 메서드 추가
	public MembershipDTO selectMembershipByUserId(String userId);
	
}

/*
int 함수명(매개변수1, 매개변수2, ... ) {
	
	int aaaa = 3;
	aaaa =1000;
	return aaaa;
}

boolean 함수명2() {
	// return false;
	return true;
}
*/
