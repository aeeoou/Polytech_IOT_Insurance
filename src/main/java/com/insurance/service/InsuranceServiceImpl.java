package com.insurance.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurance.domain.InsuranceDTO;
import com.insurance.mapper.InsuranceMapper;

@Service
public class InsuranceServiceImpl implements InsuranceService
{
	@Autowired
	private InsuranceMapper insuranceMapper;
	
	@Override
	public boolean registerInsurance(InsuranceDTO params)
	{
		int queryResult = 0;
		
		if (params.getIdx() == null)
		{
			queryResult = insuranceMapper.insertInsurance(params);
		} else {
			queryResult = insuranceMapper.updateInsurance(params);
		}
		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public InsuranceDTO getInsuranceDetail(Long idx)
	{
		return insuranceMapper.selectInsuranceDetail(idx);
	}
	
	@Override
	public boolean deleteInsurance(Long idx)
	{
		int queryResult = 0;
		
		InsuranceDTO insurance = insuranceMapper.selectInsuranceDetail(idx);
		
		if (insurance != null && "N".equals(insurance.getDeleteYn()))
		{
			queryResult = insuranceMapper.deleteInsurance(idx);
		}
		return (queryResult == 1) ? true : false;
	}
	
	@Override
	public List<InsuranceDTO> getInsuranceList()
	{
		List<InsuranceDTO> insuranceList = Collections.emptyList();
		
		int insuranceTotalCount = insuranceMapper.selectInsuranceTotalCount();
		
		if (insuranceTotalCount > 0)
		{
			insuranceList = insuranceMapper.selectInsuranceList();
		}
		return insuranceList;
	}	
}
