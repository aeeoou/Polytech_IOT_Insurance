package com.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.insurance.service.SocialService;

@Controller
@RequestMapping("/membership")
public class SocialController
{
	private final SocialService kakaoService;
	
	@Autowired
	public SocialController(SocialService kakaoService)
	{
		this.kakaoService = kakaoService;
	}
	
	@GetMapping("/login")
	public String login(Model model)
	{
		model.addAttribute("kakaoUrl", kakaoService.getKakaoLogin());
	
		// 로그인 화면 주소
		return "membership/login.do";
	}
}
