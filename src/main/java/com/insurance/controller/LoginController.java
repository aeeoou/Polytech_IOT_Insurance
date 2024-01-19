package com.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.insurance.domain.LoginDTO;
import com.insurance.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController
{
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@PostMapping("/login")
	public String loginAction(LoginDTO loginDTO, HttpServletRequest request)
	{
		LoginDTO result = loginService.loginAction(loginDTO);
		
		if(result != null)
		{                                                
			request.getSession().setAttribute("userId", result.getUserId());
			request.getSession().setAttribute("userName", result.getUserName());
			
			return "redirect:/insurance";
		
		} else {
			if(request.getSession().getAttribute("userId") != null)
			{
				request.getSession().removeAttribute("userId");
			}
			if(request.getSession().getAttribute("userName") != null)
			{
				request.getSession().removeAttribute("userName");
			}
			
			return "redirect:/login?error=loginError";
		}
	}
	
	@GetMapping("/login")
	public String logout(HttpServletRequest request)
	{
		if(request.getSession().getAttribute("userId") != null)
		{
			request.getSession().removeAttribute("userId");
		}
		if(request.getSession().getAttribute("userName") != null)
		{
			request.getSession().removeAttribute("userName");
		}
		
		return "redirect:/insurance";
	}
}
