package com.insurance.controller;

import org.apache.logging.log4j.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.insurance.service.MembershipService;
import com.insurance.vo.MembershipVO;

import jakarta.inject.Inject;

@Controller
@RequestMapping("/membership/*")
public class MembershipController
{
	private static final Logger logger = LoggerFactory.getLogger(MembershipController.class);
	
	@Inject
	MembershipService service;
	
	// 회원가입 get
	// :: 회원가입 폼으로 이동할 때 GET메서드를 타고 회원가입 버튼을 눌렀을 때 POST 메서드를 타게 작성
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String getRegister(Model model) throws Exception
	{
		logger.info("get register");
		// 빈 MembershipVO를 모델에 추가
		model.addAttributes("membershipVO", new MembershipVO());
		return "register";
	}
	
	// 회원가입 post
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegister(MembershipVO vo) throws Exception
	{
		logger.info("post register");
		
		service.register(vo);
		
		return null;
	}
}
