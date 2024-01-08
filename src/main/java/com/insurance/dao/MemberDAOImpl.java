package com.insurance.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.insurance.vo.MemberVO;

import jakarta.inject.Inject;

@Repository
public class MemberDAOImpl implements MemberDAO
{
	@Inject SqlSession sql;
	// 회원가입
	
	@Override
	public void register(MemberVO vo) throws Exception
	{
		sql.insert("membershipMapper.register", vo);
	}
}
