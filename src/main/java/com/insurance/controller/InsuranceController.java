package com.insurance.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.insurance.constant.Method;
import com.insurance.domain.InsuranceDTO;
import com.insurance.service.InsuranceService;
import com.insurance.util.UiUtils;

import jakarta.servlet.http.HttpSession;

@Controller
public class InsuranceController extends UiUtils
{
	@Autowired
	private InsuranceService insuranceService;
	
	// 게시판 글 리스트 페이지
	@GetMapping(value = "/insurance/write.do")
	public String openInsuranceWrite(@ModelAttribute("params") InsuranceDTO params, @RequestParam(value = "idx", required = false) Long idx, HttpSession session, Model model)
	{
		// 로그인 유무 확인
		if (session.getAttribute("loginId") == null)
		{   // 로그인이 안된 상태에서 글쓰기 페이지를 열 경우 안내메시지 출력과 함께 로그인 화면으로 이동한다.
			System.out.println("현재 로그아웃 상태입니다. 로그인 후 이용해주세요.");
			
			return "insurance/login";
			
		} else {
			System.out.println("현재 로그인 된 상태입니다. 아이디 : " + session.getAttribute("loginId"));
			// 글 작성
			if (idx == null)
			{
				model.addAttribute("insurance", new InsuranceDTO());
				
			} else {
				InsuranceDTO insurance = insuranceService.getInsuranceDetail(idx);
				
				if (insurance == null || "Y".equals(insurance.getDeleteYn()))
				{
					return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글이다.", "/insurance/list.do", Method.GET, null, model);
				}
				model.addAttribute("insurance", insurance);
			}
			return "insurance/write";
		}
		
		/*
		if (idx == null)
		{
			model.addAttribute("insurance", new InsuranceDTO());
		} else {
			InsuranceDTO insurance = insuranceService.getInsuranceDetail(idx);
			
			if (insurance == null || "Y".equals(insurance.getDeleteYn()))
			{
				return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글이다.", "/insurance/list.do", Method.GET, null, model);
			}
			model.addAttribute("insurance", insurance);
		}
		return "insurance/write";
		*/
	}
	
	// 작성한 글을 저장하게 해주는 컨트롤러
	@PostMapping(value = "/insurance/register.do")
	public String registerInsurance(@ModelAttribute("params") final InsuranceDTO params, HttpSession session, Model model)
	{
		Map<String, Object> pagingParams = getPagingParams(params);
		
		// 로그인 유무 확인
		if (session.getAttribute("loginId") == null)
		{
			System.out.println("작성 글 저장 :: 로그인 X");
			
			return showMessageWithRedirect("작성 글 저장 :: 로그인 필요함", "/membership/login.do", Method.GET, null, model);
		}
		System.out.println("작성 글 저장 :: 로그인 O 아이디 : " + session.getAttribute("loginId"));
		
		// 작성한 글을 저장한다.
		try
		{
			System.out.println(params);
			boolean isRegistered = insuranceService.registerInsurance(params);
			
			if (isRegistered == false)
			{
				return showMessageWithRedirect("계약 등록에 실패하였습니다.", "/insurance/list.do", Method.GET, pagingParams, model);				                       
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/insurance/list.do", Method.GET, pagingParams, model);
			                              
		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/insurance/list.do", Method.GET, pagingParams, model);
		}
		return showMessageWithRedirect("체결한 계약이 등록 완료되었습니다. 유지 관리에 힘써주세요!", "/insurance/list.do", Method.GET, pagingParams, model);
	}
	
	// 게시글 목록 페이지
	// @GetMapping :: GET방식의 HTTP요청 메서드
	@GetMapping(value = "/insurance/list.do")
	// Model :: 컨트롤러에서 화면(view)으로 데이터를 전달할 때 사용되는 인터페이스
	public String openInsuranceList(@ModelAttribute("params") InsuranceDTO params, HttpSession session, Model model)
	{
		// 로그인 유무 확인
		if (session.getAttribute("loginId") == null)
		{
			System.out.println("유지 계약 목록 페이지 :: 로그인 X");
			
			return showMessageWithRedirect("유지 계약 목록 페이지 :: 로그인 해주세요!", "/membership/login.do", Method.GET, null, model);
		}
		System.out.println("유지 계약 목록 페이지 :: 로그인 되어있습니다. 아이디 : " + session.getAttribute("loginId"));
		
		// insuranceList :: InsuranceService에서 호출한 getInsuranceList 메서드의 실행 결과물을 저장
		List<InsuranceDTO> insuranceList = insuranceService.getInsuranceList(params);
		
		model.addAttribute("insuranceList", insuranceList); 		
		
		// return문 :: 컨트롤러의 리턴 문에 지정된 경로의 HTML이 화면에 출력
		return "insurance/list";
	}
	
	// 게시글 조회 처리
	@GetMapping(value = "/insurance/view.do")
	public String openInsuranceDetail(@ModelAttribute("params") InsuranceDTO params, @RequestParam(value = "idx", required = false) Long idx, HttpSession session, Model model)    // RequestParam :: 특정 게시글 조회에 필요한 게시글 번호를 파라미터 전달받는다. required 속성은 파라미터가 필수 값인지에 대한 여부이며 'idx'가 파라미터로 전달되지 않으면 에러 발생 (여기서는 파라미터가 넘어오지 않을 경우에 대해 직접 처리할 예정이므로 'required' 속성을 'false'로 지정)
	{
		// 로그인이 되어있는지 확인
		if (session.getAttribute("loginId") == null)
		{
			System.out.println("유지 계약 목록 페이지 :: 로그인 X");
			
			return showMessageWithRedirect("유지 계약 목록 페이지 :: 로그인 해주세요!!", "/membership/login.do", Method.GET, null, model);
		}
		System.out.println("유지 계약 목록 페이지 :: 로그인 되어있습니다. 아이디 : " + session.getAttribute("loginId"));		
		
		// if문에서 'idx'가 파라미터로 전달되지 않으면 사용자에게 적절한 메시지를 전달하고, 게시글 리스트로 리다이렉트
		// 정상적인 경우에는 getInsuranceDetail 메서드의 인자로 idx를 전달해서 게시글 정보를 조회.
		// 만약 없는 게시글이거나 삭제된 게시글이라면 사용자에게 적절한 메시지를 전달하고, 게시글 리스트로 리다이렉트 마지막에는 게시글 정보를 화면(View)으로 전달하고, 게시글 상세 페이지를 리턴
		if (idx == null)
		{
			return showMessageWithRedirect("올바르지 않은 접근이다.", "/insurance/list.do", Method.GET, null, model);
		}
		
		InsuranceDTO insurance = insuranceService.getInsuranceDetail(idx);
		
		if (insurance == null || "Y".equals(insurance.getDeleteYn()))
		{
			return showMessageWithRedirect("없는 게시글이거나 이미 삭제된 게시글이다.", "/insurance/list.do", Method.GET, null, model);
		}
		model.addAttribute("insurance", insurance);
		
		return "insurance/view";
	}
	
	// 게시글 삭제 처리 (iot플랫폼실무 챕터10-2p)
	@PostMapping(value = "/insurance/delete.do")
	public String deleteInsurance(@ModelAttribute("params") InsuranceDTO params, @RequestParam(value = "idx", required = false) Long idx, HttpSession session, Model model)
	{
		// 로그인 유무 확인
		if (session.getAttribute("loginId") == null)
		{
			System.out.println("유지 계약 삭제 :: 로그인 X");
			
			return showMessageWithRedirect("유지 계약 삭제 :: 로그인 된 상태가 아닙니다.", "/membership/login.do", Method.GET, null, model);
		}
		System.out.println("유지 계약 삭제 :: 로그인 되어있습니다. 아이디 : " + session.getAttribute("loginId"));		
		
		// 게시글 삭제
		if (idx == null)
		{
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/insurance/list.do", Method.GET, null, model);
		}                                                                                                                            
	
		Map<String, Object> pagingParams = getPagingParams(params);
		
		try
		{
			boolean isDeleted = insuranceService.deleteInsurance(idx);
			
			if (isDeleted == false)
			{
				return showMessageWithRedirect("작성된 유지 계약 삭제에 실패하였습니다.", "/insurance/list.do", Method.GET, pagingParams, model);
			}
		
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/insurance/list.do", Method.GET, pagingParams, model);
		
		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/insurance/list.do", Method.GET, pagingParams, model);
		}
		
		return showMessageWithRedirect("작성된 유지 계약 삭제가 완료되었습니다. 삭제된 게시글은 복구할 수 없습니다. 한번 더 확인해주세요.", "/insurance/list.do", Method.GET, pagingParams, model);
	}
}

