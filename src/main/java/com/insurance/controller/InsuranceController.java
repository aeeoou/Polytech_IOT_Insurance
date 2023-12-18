package com.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.insurance.constant.Method;
import com.insurance.domain.InsuranceDTO;
import com.insurance.service.InsuranceService;
import com.insurance.util.UiUtils;

@Controller
public class InsuranceController extends UiUtils
{
	@Autowired
	private InsuranceService insuranceService;

	@GetMapping(value = "/insurance/write.do")
	public String openInsuranceWrite(@RequestParam(value = "idx", required = false) Long idx, Model model)
	{
		if (idx == null)
		{
			model.addAttribute("insurance", new InsuranceDTO());
		} else {
			InsuranceDTO insurance = insuranceService.getInsuranceDetail(idx);
			
			if (insurance == null)
			{
				return "redirect:/insurance/list.do";
			}
			model.addAttribute("insurance", insurance);
		}
		return "insurance/write";
	}
	
	@PostMapping(value = "/insurance/register.do")                // 작성한 글을 저장하게 해주는 컨트롤러?
	public String registerInsurance(final InsuranceDTO params, Model model)
	{
		try
		{
			boolean isRegistered = insuranceService.registerInsurance(params);
			
			if (isRegistered == false)
			{
				return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/insurance/list.do", Method.GET, null, model);				                       
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/insurance/list.do", Method.GET, null, model);
			                              
		} catch (Exception e) {
			return showMessageWithRedirect("시스템에 문제가 발생하였습니다.", "/insurance/list.do", Method.GET, null, model);
		}
		return showMessageWithRedirect("게시글 등록이 완료되었습니다.", "/insurance/list.do", Method.GET, null, model);
	}
	
	// 게시글 목록 처리 - InsuranceController
	@GetMapping(value = "/insurance/list.do")              // @GetMapping :: GET방식의 HTTP요청 메서드
	public String openInsuranceList(Model model)           // Model :: 컨트롤러에서 화면(view)으로 데이터를 전달할 때 사용되는 인터페이스
	{
		List<InsuranceDTO> insuranceList = insuranceService.getInsuranceList(); // insuranceList :: InsuranceService에서 호출한 getInsuranceList 메서드의 실행 결과물을 저장
		model.addAttribute("insuranceList", insuranceList);
		
		return "insurance/list";       // return문 :: 컨트롤러의 리턴 문에 지정된 경로의 HTML이 화면에 출력
	}
	
	// 게시글 조회 처리 - InsuranceController
	@GetMapping(value = "/insurance/view.do")
	public String openInsuranceDetail(@RequestParam(value = "idx", required = false) Long idx, Model model)    // RequestParam :: 특정 게시글 조회에 필요한 게시글 번호를 파라미터 전달받는다. required 속성은 파라미터가 필수 값인지에 대한 여부이며 'idx'가 파라미터로 전달되지 않으면 에러 발생 (여기서는 파라미터가 넘어오지 않을 경우에 대해 직접 처리할 예정이므로 'required' 속성을 'false'로 지정)
	{
		if (idx == null) // if문에서 'idx'가 파라미터로 전달되지 않으면 사용자에게 적절한 메시지를 전달하고, 게시글 리스트로 리다이렉트 (정상적인 경우에는 getInsuranceDetail 메서드의 인자로 idx를 전달해서 게시글 정보를 조회. 만약 없는 게시글이거나 삭제된 게시글이라면 사용자에게 적절한 메시지를 전달하고, 게시글 리스트로 리다이렉트 마지막에는 게시글 정보를 화면(View)으로 전달하고, 게시글 상세 페이지를 리턴)
		{
			// TODO => 올바르지 않은 접근이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return "redirect:/insurance/list.do";
		}
		
		InsuranceDTO insurance = insuranceService.getInsuranceDetail(idx);
		
		if (insurance == null || "Y".equals(insurance.getDeleteYn()))
		{
			// TODO => 없는 게시글이거나, 이미 삭제된 게시글이라는 메시지를 전달하고, 게시글 리스트로 리다이렉트
			return "redirect:/insurance/list.do";
		}
		model.addAttribute("insurance", insurance);
		
		return "insurance/view";
	}
	
	// 게시글 삭제 처리 Controller (iot플랫폼실무 챕터10-2p)
	@PostMapping(value = "/insurance/delete.do")
	public String deleteInsurance(@RequestParam(value = "idx", required = false) Long idx, Model model)
	{
		if (idx == null)
		{
			return showMessageWithRedirect("올바르지 않은 접근입니다.", "/insurance/list.do", Method.GET, null, model);
		}
		try
		{
			boolean isDeleted = insuranceService.deleteInsurance(idx);
			
			if (isDeleted == false)
			{
				return showMessageWithRedirect("게시글 삭제에 실패하였습니다.", "/insurance/list.do", Method.GET, null, model);
			}
		} catch (DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제가 발생하였습니다.", "/insurance/list.do", Method.GET, null, model);
		} catch (Exception e) {
			return show
		}
		return "redirect:/insurance/list.do";
	}
}

