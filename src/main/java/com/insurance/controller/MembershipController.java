package com.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.insurance.domain.MembershipDTO;
import com.insurance.service.MembershipService;

@Controller
public class MembershipController
{
	@Autowired
	private MembershipService membershipService;
	
	@GetMapping(value = "/membership/write.do")
	public String openMembershipWrite(@RequestParam(value = "userIdx", required = false) Long idx, Model model)
	{	
		if (idx == null)
		{
			model.addAttribute("membership", new MembershipDTO());
		
		} else {
			MembershipDTO membership = membershipService.getMembershipDetail(idx);
			
			if (membership == null)
			{
				return "membership/write";
			}
			model.addAttribute("membership", membership);
		}
		      // html 경로
		return "insurance/membership";
	}
	// 데이터를 받아오는 용도 
	@PostMapping(value = "membership/register.do")
	public String registerMembership(final MembershipDTO params)
	{
		try
		{
			boolean isRegistered = membershipService.registerMembership(params);
			if (isRegistered == false)
			{
				// TODO => 회원가입에 실패했다는 메시지를 전달
			}
		} catch (DataAccessException e) {
			// TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달
		} catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달
		}
		return "redirect:/membership/list.do";
	}

}
