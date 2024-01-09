package com.insurance.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.insurance.vo.MembershipVO;

import jakarta.inject.Inject;

@Repository
public class MembershipDAOImpl implements MembershipDAO
{
	@Inject SqlSession sql;
	// 회원가입
	
	@Override
	public void register(MembershipVO vo) throws Exception
	{
		sql.insert("membershipMapper.register", vo);
	}
}
