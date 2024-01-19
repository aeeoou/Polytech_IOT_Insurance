package com.insurance.domain;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAO
{
	@Autowired
	private SqlSession sqlSession;
	
	public LoginDTO loginAction(LoginDTO loginDTO)
	{
		return sqlSession.selectOne("login.loginAction", loginDTO);
	}
}
