// Controller (흐름제어)

package com.insurance.controller;

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
			// 회원가입할 때 필요함
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
		// 실패했으니까 다시 list.do로 가라
		return "redirect:/membership/list.do";
	}
	
	// 로그인 화면 (Get)
	@GetMapping("/membership/login.do")
	public String showLoginView(Model model)
	{
		// 로그인 화면 주소
		return "insurance/login";
	}
	
	
	// 로그인 Post
	@PostMapping(value = "membership/login.do")
	public String loginMembership(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, Model model)
	{
		try
		{
			// 'membershipService'의 'login' 메서드를 호출
			boolean isLogin = membershipService.login(userId, userPw);
			
			if (isLogin)
			{
				// TODO => 로그인 성공 시 처리
				model.addAttribute("message", "로그인 성공!");
				return "redirect:/insurance/list.do";
			}
			else
			{
				// TODO => 로그인 실패 시 처리
				model.addAttribute("message", "로그인 실패. 아이디 또는 비밀번호를 확인하세요.");
			}
		}
		catch (Exception e)
		{
			// TODO => 예외 처리
			model.addAttribute("message", "로그인 중 오류 발생: " + e.getMessage());
		}
		// 로그인 결과를 보여줄 페이지로 이동
		return "redirect:/membership/login.do";
	}
	
	
	// 로그아웃
	@GetMapping("/membership/logout.do")
	public String showLogoutView(Model model)
	{
		try
		{
			membershipService.logout();
			
			return "redirect:/membership/login.do";
		}
		catch (Exception e)
		{
			// TODO -> 로그아웃 중 예외 처리
			model.addAttribute("message", "로그아웃 중 오류 발생: " + e.getMessage());
			// 로그아웃 중 에러가 발생하더라도 로그인 페이지로 리다이렉트
			return "redirect:/membership/login.do";
		}
	}
}

