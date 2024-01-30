// Controller (흐름제어)
package com.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.insurance.constant.Method;
import com.insurance.domain.MembershipDTO;
import com.insurance.service.MembershipService;
import com.insurance.util.UiUtils;

import jakarta.servlet.http.HttpSession;



@Controller
// MembershipController 가 UiUtils 클래스의 모든 속성과 메서드를 상속받는다 (extends 사용하여..상속)
public class MembershipController extends UiUtils
{
	@Autowired
	private MembershipService membershipService;
	
	// 회원가입
	@GetMapping(value = "/membership/write.do")
	public String openMembershipWrite(@RequestParam(value = "userIdx", required = false) Long idx, HttpSession session, Model model)
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
		boolean isRegistered = false;
		// params가 null 인지 아닌지 확인한다.
		if (params != null)
		{
			// membershipService를 통해 params를 이용하여 회원가입을 시도
			isRegistered = membershipService.registerMembership(params);
		}
		
		// 회원가입에 실패했을 경우에 대한 처리
		if (!isRegistered)
		{
			//TODO => 회원가입에 실패했다는 메시지를 전달하는 로직을 추가
		}
		// 회원가입 성공 여부와 관계없이 list.do 페이지로 리다이렉트 한다.
		return "redirect:/membership/list.do";
		
		/*
		try
		{
			boolean isRegistered = membershipService.registerMembership(params);
			if (!isRegistered)
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
		*/
	}
	
	//  로그인 페이지 출력
	@GetMapping("/membership/login.do")
	public String showLoginView(HttpSession session, Model model)
	{
		// 로그인 상태 확인
		if (session.getAttribute("loginId")  == null)
		{
			System.out.println("로그아웃 되었습니다095");
			
			return "insurance/login";
		
		} else {
			System.out.println("로그인 된 상태, 아이디 : " + session.getAttribute("loginId"));
			
			return "redirect:/insurance/list.do";
		}
	}

	// login.html의 submit - form:post - 이곳이 실행
	@PostMapping("/membership/login.do")
	public String loginMembership(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, HttpSession session, Model model)
	{
		try
		{
			// 'membershipService'의 'login' 메서드를 호출
			boolean isLogin = membershipService.login(userId, userPw);
			
			if (isLogin)
			{
				// 세션이 없으면 새로 생성한다.
				if (session.getAttribute("loginId") == null)
				{
					System.out.println("로그인 안 된 상태200");
					
					session.setAttribute("loginId", userId);
				
				} else {
					System.out.println("로그인 된 상태면서, 주제넘게 로그인 시도 함");
				}

				// 세션 유효시간을 60분으로 설정
//				session.setMaxInactiveInterval(3600);
				// TODO => 로그인 성공 시 처리
				model.addAttribute("message", "로그인 성공!");
				
				return "redirect:/insurance/list.do";
			
			} else {
				// TODO => 로그인 실패 시 처리
				model.addAttribute("message", "로그인 실패! 아이디 또는 비밀번호를 확인하세요.");
				
				return showMessageWithRedirect("로그인 실패하였습니다.", "/membership/login.do", Method.GET, null, model);
			}
		} catch (Exception e) {
			model.addAttribute("message", "로그인 중 오류 발생: " + e.getMessage());
			
			return showMessageWithRedirect("로그인 실패하였습니다.", "/membership/login.do", Method.GET, null, model);	
		}
	}
	
	// 로그아웃
	@GetMapping("/membership/logout.do")
	public String showLogoutView(HttpSession session , Model model)
	{
		try
		{
			session.removeAttribute("loginId");
			System.out.println("정상적으로 로그아웃 되었습니다.");

			// 로그아웃 시 로그인 페이지로 리다이렉트
			return "redirect:/membership/login.do";
		
		} catch (Exception e) {
			// TODO -> 로그아웃 중 예외 처리
			// 로그아웃 실패 시 메시지 표시 등의 처리
			model.addAttribute("errorMessage3", "로그아웃에 실패하였습니다.");
			// 로그아웃 중 에러가 발생하더라도 로그인 페이지로 리다이렉트
			return "redirect:/membership/login.do";
		}
	}
}

