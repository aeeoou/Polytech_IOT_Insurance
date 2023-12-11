package com.insurance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.insurance.service.InsuranceService;

@Controller
public class InsuranceController
{
	@Autowired
	private InsuranceService insuranceService;

	@GetMapping(value = "/insurance/write.do")
	public String openInsuranceWrite(Model model)
	{
		String ins_type = "보험종목";
		String ins_co = "보험회사";
		String contract_date = "계약체결일";
		String contract_no = "계약번호";
		String contractor = "계약자";
		String insured = "피보험자";
		String prod_name = "상품명";
		String payt_exp = "납&만기";
		int premium = 1000;
		String memo_col = "메모란";
		
		model.addAttribute("it", ins_type);
		model.addAttribute("ic", ins_co);
		model.addAttribute("cd", contract_date);
		model.addAttribute("cn", contract_no);
		model.addAttribute("c",  contractor);
		model.addAttribute("i",  insured);
		model.addAttribute("pn", prod_name);
		model.addAttribute("pe", payt_exp);
		model.addAttribute("p", premium);
		model.addAttribute("m", memo_col);
		
		return "insurance/write";
	}
}
