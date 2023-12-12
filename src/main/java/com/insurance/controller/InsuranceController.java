package com.insurance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.insurance.domain.InsuranceDTO;
import com.insurance.service.InsuranceService;

@Controller
public class InsuranceController
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
	public String registerInsurance(final InsuranceDTO params)
	{
		try
		{
			boolean isRegistered = insuranceService.registerInsurance(params);
			
			if (isRegistered == false)
			{
				// TODO => 게시글 등록에 실패하였다는 메시지를 전달한다.
			}
		} catch (DataAccessException e) {
			// TODO => 데이터베이스 처리 과정에 문제가 발생하였다는 메시지를 전달한다.
		} catch (Exception e) {
			// TODO => 시스템에 문제가 발생하였다는 메시지를 전달한다.
		}
		return "redirect:/insurance/list.do";
	}
	
	// 게시글 목록 처리 - InsuranceController
	@GetMapping(value = "/insurance/list.do")              // @GetMapping :: GET방식의 HTTP요청 메서드
	public String openInsuranceList(Model model)           // Model :: 컨트롤러에서 화면(view)으로 데이터를 전달할 때 사용되는 인터페이스
	{
		List<InsuranceDTO> insuranceList = insuranceService.getInsuranceList(); // insuranceList :: InsuranceService에서 호출한 getInsuranceList 메서드의 실행 결과물을 저장
		model.addAttribute("insuranceList", insuranceList);
		
		return "insurance/list";       // return문 :: 컨트롤러의 리턴 문에 지정된 경로의 HTML이 화면에 출력
	}
}

