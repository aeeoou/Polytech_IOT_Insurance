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
	
	// 회원가입 페이지로 이동하기
	@GetMapping(value = "/membership/write.do")
	public String openMembershipWrite(@RequestParam(value = "userIdx", required = false) Long idx, HttpSession session, Model model)
	{
		/*
		// 로그인 된 상태인지 검사 필요
		if (session.getAttribute("loginId") == null)
		{
			System.out.println("작성 글 저장 :: 로그인 안됨");
			
			return showMessageWithRedirect("작성 글 저장 :: 로그인 필요함", "/membership/login.do", Method.GET, null, model);
		}
		System.out.println("작성 글 저장 :: 로그인 되어 있는 중이에요! 아이디 : " + session.getAttribute("loginId"));
		*/ // 여기 다시 손보기!!! 회원 가입 버튼 눌러보기
		
		// 회원가입
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
		// 회원가입 페이지로 이동
		return "insurance/membership";
	}
	
	// 데이터를 받아오는 용도 
	@PostMapping(value = "membership/register.do")
	public String registerMembership(final MembershipDTO params)
	{
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
	}
	
	//  로그인 페이지 출력
	@GetMapping("/membership/login.do")
	public String showLoginView(HttpSession session, Model model)
	{
		// 로그인 상태 확인
		if (session.getAttribute("loginId") == null)
		{
			System.out.println("현재 로그아웃 상태입니다.");
			
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
					System.out.println("현재 로그아웃 상태입니다.");
					
					session.setAttribute("loginId", userId);
				
				} else {
					System.out.println("현재 로그인 상태입니다. 로그아웃 후 시도해주세요.");
				}

				// 세션 유효시간을 60분으로 설정
//				session.setMaxInactiveInterval(3600);
				// TODO => 로그인 성공 시 처리
				model.addAttribute("message", "로그인 성공!");
				
				return "redirect:/insurance/list.do";
			
			} else {
				// 로그인 실패 시 처리
				model.addAttribute("message", "아이디(로그인 전용 아이디) 또는 비밀번호를 잘못 입력했습니다. 입력하신 내용을 다시 확인해주세요.");
				// 로그인 실패할 경우, 안내메세지와 함께 로그인 화면으로 이동한다.
				return showMessageWithRedirect("아이디(로그인 전용 아이디) 또는 비밀번호를 잘못 입력했습니다. 입력하신 내용을 다시 확인해주세요.", "/membership/login.do", Method.GET, null, model);
			}
		} catch (Exception e) {
			model.addAttribute("message", "로그인 중 오류 발생: " + e.getMessage());
			
			return showMessageWithRedirect("웹페이지-알 수 없는 이유. 원인불명", "/membership/login.do", Method.GET, null, model);	
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
			model.addAttribute("errorMessage157", "로그아웃에 실패하였습니다.");
			// 로그아웃 중 에러가 발생하더라도 로그인 페이지로 리다이렉트
			return "redirect:/membership/login.do";
		}
	}
}

