package com.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.insurance.domain.LoginDTO;
import com.insurance.domain.MembershipDTO;
import com.insurance.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController
{
	@Autowired
	private LoginService loginService;
	
	@GetMapping(value = "/login/write.do")
	public String openLoginWrite(@RequestParam(value = "userIdx", required = false) Long idx, Model model)
	{
		if (idx == null)
		{
			model.addAttribute("login", new LoginDTO());
			
		} else {
			LoginDTO login = loginService.getLoginDetail(idx);
				
			if (login == null)
			{
				return "login/write";
			}
			model.addAttribute("login", login);
		}
	    // html 경로
		return "insurance/login";
	}
	
	
	
	
	@PostMapping("/login")
	public String loginAction(LoginDTO loginDTO, HttpServletRequest request)
	{
		LoginDTO result = loginService.loginAction(loginDTO);
		
		if(result != null)
		{                                                
			request.getSession().setAttribute("userId", result.getUserId());
			request.getSession().setAttribute("userName", result.getUserName());
			
			return "insurance/login";
		
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
