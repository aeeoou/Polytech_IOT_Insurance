package com.insurance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
	public MembershipDTO selectMembershipByUserId(@Param("userId") String userId);

}