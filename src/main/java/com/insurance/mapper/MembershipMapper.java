package com.insurance.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.insurance.domain.MembershipDTO;

@Mapper                                                              // @Mapper :: 데이터베이스와 통신하는 인터페이스임을 선언
public interface MembershipMapper             
{
	public int register(MembershipDTO params);
	public int delete(MembershipDTO params);
}