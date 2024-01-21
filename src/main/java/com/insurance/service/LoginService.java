package com.insurance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.domain.LoginDAO;
import com.insurance.domain.LoginDTO;

@Service
public class LoginService
{
	@Autowired
	private LoginDAO loginDAO;
	
	public LoginDTO getLoginDetail(long idx);
	
	public LoginDTO loginAction(LoginDTO loginDTO)
	{
		return loginDAO.loginAction(loginDTO);
	}
}
