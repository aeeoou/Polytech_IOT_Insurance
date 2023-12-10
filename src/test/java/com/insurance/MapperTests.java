package com.insurance;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.insurance.domain.InsuranceDTO;
import com.insurance.mapper.InsuranceMapper;

@SpringBootTest
class MapperTests
{
	@Autowired
	private InsuranceMapper insuranceMapper;
	
	@Test
	public void testOfInsert()                               // testOfInsert 게시글 작성 기능 메서드 작성
	{
		InsuranceDTO params = new InsuranceDTO();
		
		params.setIns_type("1보험종목");
		params.setIns_co("1보험회사");
		params.setContract_date("1계약체결일");
		params.setContract_no("1계약번호");
		params.setContractor("1계약자");
		params.setInsured("1피보험자"); 
		params.setProd_name("1상품명");
		params.setPayt_exp("1납&만기");
		params.setPremium(1);
		params.setMemo_col("1메모란");
		
		int result = insuranceMapper.insertInsurance(params);
		System.out.println("결과는 " + result + "입니다.");
	}
	
	@Test
	public void testOfSelectDetail()
	{
		InsuranceDTO insurance = insuranceMapper.selectInsuranceDetail((long) 1);
		
		try
		{
			String insuranceJson = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(insurance);
			
			System.out.println("========================================");
			System.out.println(insuranceJson);
			System.out.println("========================================");
		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
