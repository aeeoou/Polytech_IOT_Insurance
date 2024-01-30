package com.insurance.service;

import java.util.List;

import com.insurance.domain.InsuranceDTO;

//챕터6 :: 서비스 영역 (Business Layer) 처리
public interface InsuranceService
{
	public boolean      registerInsurance(InsuranceDTO params);
	public InsuranceDTO getInsuranceDetail(Long idx);
	public boolean      deleteInsurance(Long idx);
	public List<InsuranceDTO> getInsuranceList(InsuranceDTO criteria);
}
